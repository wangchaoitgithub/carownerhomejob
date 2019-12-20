var file_upload = {
    'file': {},
    'fristUploadFlag': true,
    'g_uploading_flag': 0,
    'g_upload_idx': 0,
    'currentChunk': 0, //当前处于第几分片,
    'total_block':0,//文件总的分片数
    'chunkSize': 2 * 1024 * 1024,
    'blocks': 0,
    'blocksize': 0,
    'blobSlice': File.prototype.mozSlice || File.prototype.webkitSlice || File.prototype.slice,
    'idx': 0,
    'chunks': 0,
    'uid': 0,
    'atoken': '',
    'ftoken': '',
    'gRef_cancelUpload': '',
    'gIdx_cancelUpload': '',
    'lastSlice': 0,
    'retryCnt': 0,
    'timerId': '',
    'uploaded': 0,//已上传文件大小
    'upSpeed': 0,//上传文件的速度
    'lastUpTime': 0,//上次上次的时间
    'file_clone_key':'',


    //开始上传视频主入口
    'file_upload': function (popupId) {
        //console.log('I am access main');
        dom_op.file_upload_op(popupId);//展示上传的item
        if (this.fristUploadFlag) { //第一次 进入 触发fileReader.onload事件 计算文件MD5总值
            this.g_uploading_flag = 1;//标示为可以上传
            file_upload.upLoadNextFile();
            this.fristUploadFlag = false;
        }
        this.uploadListOppChange();
    },
    //上传 不知道什么作用
    'uploadListOppChange': function () {
        var size = 0;
        var count = 0;
        $("#uploadlist dl").children().each(function (index) {
            //有一个隐藏的addItem项,从0开始计算
            if ($(this).attr("id").indexOf("remove") > 0) {
                //当莫一项移除了不在计算范围
                return;
            }
            if ($(this).css("display") == "none") {
                return;
            }
            size += parseInt($(this).find('#sizeValue').html());

            count++;
            if (count == 1) {
                $("#dom_op.uploadListOpp").css("display", "inline");
            }
        });
        //alert(size);
        if (count < 1) {
            $("#dom_op.uploadListOpp").css("display", "none");
        } else {
            $("#dom_op.uploadListOpp").css("display", "inline");
            $("#dom_op.uploadListOpp").find("#count").html(count);
            $("#dom_op.uploadListOpp").find("#size").html(dom_op.readablizeBytes(size));
        }
    },
    /* 'begin_uploading': function () {
     this.g_uploading_flag = 1;//标示为可以上传
     },*/
    'upLoadNextFile': function () {

        if (!dom_op.uploadList || dom_op.uploadList.length == 0) {
            console.log("dom_op.uploadList size is 0");
            return;
        }

        if (this.g_upload_idx < dom_op.uploadList.length) {
            this.currentChunk = 0;//重新开始新的视频上传 重置当前分片的值
            if (typeof $("#uploadfile_" + this.g_upload_idx).attr('id') != 'undefined') {
                dom_op._removeDeleteLink(this.g_upload_idx);
                this.uploadOneFile(dom_op.uploadList[this.g_upload_idx], this.g_upload_idx);
                this.g_upload_idx++;
            } else {
                console.log('upLoadNextFile this is flag');
                this.g_upload_idx++;
                this.upLoadNextFile();
            }
            //alert(g_upload_idx);	
        } else {
            dom_op.finish_uploading();
        }
    },
    'uploadOneFile': function (file, idx) {
        //console.log(file);
        if (!file) {
        return;
        }
        this.file = file;
        var file_clone = {};
        file_clone.name = file.name;
        file_clone.size = file.size;
        file_clone.type = file.type;
        file_clone.lastModified = file.lastModified;
        this.file_clone_key = hex_md5(JSON.stringify(file_clone));
        //console.log(this.file_clone_key);
        var file_info = $.cookie(this.file_clone_key);
        if(typeof(file_info) == 'undefined'){


            //dom_op._info(idx, "计算md5中");
            this.generate_md5(this.gen_md5_callback, idx);
        }else{
            file_info = JSON.parse(file_info);
            console.log(file_info);
           this.gen_md5_callback(file_info.md5sum,idx);
        }
    },
    'generate_md5': function (callback, idx) {
        var fileReader = new FileReader()

        chunks = Math.ceil(this.file.size / this.chunkSize),
            //spark = new SparkMD5(); 实例化MD5类
            spark = new SparkMD5.ArrayBuffer();

        fileReader.onload = function (e) {
            spark.append(e.target.result);
            dom_op.md5_show(idx, Math.ceil(file_upload.currentChunk * 100 / chunks) + "%");
            if (file_upload.currentChunk < chunks) {
                file_upload.currentChunk++;
                //console.log('access loadNextMd5:');
                file_upload.loadNextMd5(fileReader);
            } else {
                file_upload.currentChunk = 0;//计算完MD5值 重置当前分片的值
                md5sum = spark.end();
                var cookie_val = {
                    'md5sum':md5sum
                };

               $.cookie( file_upload.file_clone_key,JSON.stringify(cookie_val),{ expires: 0.25 });
                //amsg('MD5 hash:' + md5);
                callback(md5sum, idx);
            }
        };
        this.loadNextMd5(fileReader);
    },
    //处理单片文件
    'loadNextMd5': function (fileReader) {
        if (this.g_uploading_flag == 0) {
            dom_op._info(-1, "已取消");
            return;
        } else if (this.g_uploading_flag == 2) {
            this.g_uploading_flag = 1;
            dom_op._info(file_upload.idx, "已取消");
            this.upLoadNextFile();
            return;
        }
        var start = this.currentChunk * this.chunkSize, end = start + this.chunkSize >= this.file.size ? this.file.size : start + this.chunkSize;
        fileReader.readAsArrayBuffer(this.blobSlice.call(this.file, start, end));
    },
    'gen_md5_callback': function (md5sum, idx) {
        //凑齐 调用 创建任务接口的参数
        //console.log('md5 计算完成 我是回调 我要开始调用创建任务接口了');
        public_flag = 1, catalog_id = 0, trans_flag = "", tag = "", desc = "";
        //alert(md5sum);
        public_flag = parseInt($("#uploadfile_" + idx).find("#ispublicValue").text());

        trans_version = "";
        $("#uploadfile_" + idx).find('.info_slide').find('.upload_transcodetype').children('a').each(function (index) {
            if ($(this).attr('value') == 1) {
                if (index == 0) {
                    trans_version = "0";
                } else if (trans_version == "") {
                    trans_version = index;
                } else {
                    trans_version = trans_version + "," + index;
                }
            }
        });
        catalog_id = parseInt($("#uploadfile_" + idx).find("#catalogsValue").text());
        trans_flag = parseInt($("#uploadfile_" + idx).find("#transcodingValue").text());
        if (!catalog_id) {
            catalog_id = 0;
        }
        tag = $("#uploadfile_" + idx).find("#tags").text();
        tag = encodeURIComponent(tag);


        var filename = $("#uploadfile_" + idx).find("div#file_name").text();
        var name = $('#uploadfile_' + idx).find("i#name").text();
        filename = encodeURIComponent(filename);
        name = encodeURIComponent(name);

        file_upload._create_task(idx, md5sum, filename, name, public_flag, trans_flag, trans_version, catalog_id, tag, desc);

    },
    //调用创建上传任务接口
    '_create_task': function (idx, md5, filename, name, public_flag, trans_flag, trans_version, catalog_id, tag, desc) {

        var create_task_param = {
            user_id: page_init.uid,
            atoken: page_init.atoken,
            ftoken: page_init.ftoken,
            filename: filename,
            titlename: name,
            title: name,
            file_size: this.file.size,
            md5: md5,
            public_flag: public_flag,
            trans_flag: trans_flag,
            trans_version: trans_version,
            catalog_id: catalog_id,
            tag: tag,
            desc: desc
        }
        //var geturl = U1SERVER+"/create_task?user_id=" + uid + "&atoken="+ atoken +"&filename="+ filename +"&titlename="+ name+"&title="+ name+"&file_size="+file.size+"&md5="+ md5 +"&public_flag="+public_flag+"&trans_flag="+trans_flag+"&trans_version="+trans_version+"&catalog_id="+ catalog_id +"&tag="+tag+"&desc=" + desc +"&ftoken="+ ftoken;
        var create_task_url = U1SERVER + "/create_task";
        //现在开始ajax 请求创建任务接口
        this.idx = idx;

        $.ajax({
            type: "get",
            timeout:5000,
            data: {
                user_id: page_init.uid,
                atoken: page_init.atoken,
                ftoken: page_init.ftoken,
                filename: filename,
                titlename: name,
                title: name,
                file_size: this.file.size,
                md5: md5,
                public_flag: public_flag,
                trans_flag: trans_flag,
                trans_version: trans_version,
                catalog_id: catalog_id,
                tag: tag,
                desc: desc
            },
            url: create_task_url,
            success: function (data) {
                if (data.ret == 0) {
                    dom_op._info(file_upload.idx, "上传中");
                    file_upload.vid = data.result.vid;
                    file_upload.blocksize = data.result.blocksize;
                    file_upload.blocks = data.result.blocks;
                    file_upload.task_id = data.result.task_id;
                    file_upload.total_block = data.result.total_block;
                    dom_op._uploadSpeed(0, 0, 0);
                    file_upload.upload_file();

                } else {
                    var inner_write = '<a href="#" id="btn_cancel" onclick="javascript:file_upload.openCancelUploadPopup(this);return false;" class="red">取消上传</a>';
                    $("#uploadfile_" + this.idx).find('#delete')[0].innerHTML = inner_write;
                    $("#uploadfile_" + this.idx).find('#cancelUpload')[0].innerHTML = inner_write;
                }
            },
            dataType: 'json',
            error: function(err){
                //alert(err);
                console.log();
            },
            complete: function (XMLHttpRequest,status) {
                if(status=='timeout') {
                    alert("创建任务时超时，请检查网络");
                }
            }
        });
    },
    //上传视频内容到上传接口
    'upload_file': function () {
        //try {
        var downloaded = 0;
        var downloadTimer, uploadTimer;
        var downSpeed = 0;
        var lastDownTime = 0;

        var loaded = 0;
        var step = this.blocksize;
        var total = this.file.size;
        var start = 0;
        var reader = new FileReader();
        var currentChunk = 0, chunks = Math.ceil(this.file.size / this.blocksize);
        this.chunks = chunks;
        dom_op._percent(this.idx, 0);
        reader.onprogress = function (e) {
            loaded += e.loaded;
        };

        reader.onload = function (e) {
            spark = new SparkMD5.ArrayBuffer();
            spark.append(e.target.result);
            md5sum = spark.end();
            //实例化 ajax对象
            var XMLHttp = new XMLHttpRequest();
            var upload = XMLHttp.upload;
            var file_content_param = {
                'user_id': page_init.uid,
                'atoken': page_init.atoken,
                'ftoken': page_init.ftoken,

                'task_id': file_upload.task_id,
                'vid': file_upload.vid,
                'block': file_upload.currentChunk + 1,
                'blocksize': file_upload.blocksize,
                'uploaded': file_upload.currentChunk * file_upload.blocksize,
                'md5sum': md5sum
            }
            if(file_upload.currentChunk + 1 <= file_upload.total_block){
                url = U1SERVER + "/file_content?task_id=" + file_upload.task_id + "&user_id=" + page_init.uid + "&vid=" + file_upload.vid + "&block=" + (file_upload.currentChunk + 1) + "&blocksize=" + file_upload.blocksize + "&atoken=" + page_init.atoken + "&ftoken=" + page_init.ftoken + "&uploaded=" + (file_upload.currentChunk * file_upload.blocksize) + "&md5sum=" + md5sum;

                //ajax 请求file_content接口
                var block_content = new Uint8Array(e.target.result);//待发送的分片内容

                XMLHttp.addEventListener("progress", this.updateProgress, false);
                XMLHttp.addEventListener("onErrorResend", this.transferFailed, false);
                XMLHttp.addEventListener("abort", this.transferCanceled, false);
                //ajax请求
                XMLHttp.open("POST", url);
                XMLHttp.setRequestHeader("Content-Type", "application/octet-stream");
                XMLHttp.overrideMimeType("application/octet-stream");
                XMLHttp.send(block_content);
                var upload_status = 1;//默认失败
                XMLHttp.onreadystatechange = function () {
                    if (XMLHttp.readyState == 4 && (XMLHttp.status == 200)) {
                        var Data = JSON.parse(XMLHttp.responseText);
                        dom_op._progress(file_upload.idx);
                        if (typeof Data['ret'] != 'undefined' && Data['ret'] == 0) {
                            result = Data['result'];
                            if ((file_upload.currentChunk + 1) * file_upload.blocksize >= file_upload.file.size) {
                                var upload_status = 0;
                                if (file_upload.file.size <= 0) {
                                    upload_status = 1;
                                    dom_op._info(file_upload.idx, "上传失败");
                                } else {
                                    upload_status = 0;
                                    dom_op._info(file_upload.idx, "上传完成");
                                }
                                dom_op._percent(file_upload.idx, 100);
                                //上传完成 删除该任务的cookie
                                $.cookie(file_upload.file_clone_key, '', { expires: -1 });
                                file_upload.upload_done(file_upload.idx, file_upload.task_id, page_init.uid, page_init.atoken, page_init.ftoken, file_upload.vid, upload_status);

                            } else {

                                file_upload.currentChunk++;
                                var percent_upload = Math.floor(file_upload.currentChunk * 100 / file_upload.chunks);

                                dom_op._percent(file_upload.idx, percent_upload);
                                file_upload.uploaded = 0;
                                lastDownTime = 0;
                                file_upload.loadNextSlice(reader);

                            }

                        } else {
                            upload_status = 3;//失败
                            dom_op._info(file_upload.idx, "上传失败");
                            file_upload.upload_done(file_upload.idx, file_upload.task_id, page_init.uid, page_init.atoken, page_init.ftoken, file_upload.vid, 3);//上传完成函数

                        }
                    } else {
                        //console.log('not ready yet');
                    }
                };
                XMLHttp.upload.addEventListener('progress', function (evt) {
                    //传一块计算一下速度
                    if (evt.loaded == evt.total) {
                        var endTime = (new Date()).getTime();
                        if ((endTime > file_upload.lastUpTime) && (evt.loaded > file_upload.uploaded)) {
                            file_upload.upSpeed = ((evt.loaded - file_upload.uploaded) * 1000) / (endTime - file_upload.lastUpTime);
                        } else {
                            file_upload.upSpeed = 0;
                        }
                        file_upload.uploaded = evt.loaded;
                        file_upload.lastUpTime = endTime;
                        dom_op._uploadSpeed(Math.floor(file_upload.currentChunk * 100 / file_upload.chunks), total - (file_upload.currentChunk * file_upload.blocksize), file_upload.upSpeed);
                    }
                });
            }else{
                dom_op._info(file_upload.idx, "上传完成");
                dom_op._percent(file_upload.idx, 100);
                //上传完成 删除该任务的cookie
                $.cookie(file_upload.file_clone_key, '', { expires: -1 });
                file_upload.upload_done(file_upload.idx, file_upload.task_id, page_init.uid, page_init.atoken, page_init.ftoken, file_upload.vid, 0);
            }

        }
        this.loadNextSlice(reader);
        dom_op.clearTimer(this.timerId);
        timerId = setInterval(function () {
            if (!dom_op.speedActiveFlag) {
                //$("#uploadSpeed").html("0kb/s");
                //$("#uploadTime").html("--:--:--");
            }
            //debug_log("Timer running");
            dom_op.speedActiveFlag = false;

        }, 10 * 1000);
    },
    //取消上传 确认框
    'openCancelUploadPopup': function (ref) {
        $.openPopupLayer({
            name: "myCancelUploadPopup",
            width: 400,
            target: "myCancelDiv"
        });
        this.gIdx_cancelUpload = $(ref).closest('dd').find('#upload_idx').html();
        this.gRef_cancelUpload = ref;
        //debug_log("gIdx_cancelUpload: " + gIdx_cancelUpload + ",g_upload_idx: " + g_upload_idx);
        //$("#popupLayer_myCancelUploadPopup #pop_video_id").html(id);
        $("#popupLayer_myCancelUploadPopup").find(".header").find('a').blur();
    },
    //取消上传
    'cancelUploadFile': function () {
        $.closePopupLayer('myCancelUploadPopup');
        if (this.gRef_cancelUpload != "") {
            this._removePendingFile(this.gRef_cancelUpload);
        }
        //uploading file
        if (this.gIdx_cancelUpload == (this.g_upload_idx - 1) && this.g_uploading_flag == 1) {
            this.cancelCurrentUploading();
        }
    },
    //移除dom文档
    '_removePendingFile': function (ref) {
        $(ref).closest('dd').attr('id', $(ref).closest('dd').attr("id") + "remove");
        $(ref).closest('dd').css("display", "none");
        dom_op.uploadListOppChange();
    },
    //移除dom文档 接口通知 取消上传
    'cancelCurrentUploading': function () {
        //_info(g_upload_idx - 1, "取消中");
        $("#uploadfile_" + (this.g_upload_idx - 1)).find('#cancelUpload').html('上传已取消');
        //	_info(g_upload_idx - 1, "已取消");
        $("#uploadfile_" + (this.g_upload_idx - 1)).find('.item_title').attr("title", "");
        if (this.g_uploading_flag == 3) {
            dom_op.finish_uploading_leave_cmds();
        } else {
            this.g_uploading_flag = 2;
        }
    },


    'loadNextSlice': function (reader) {
        if (this.g_uploading_flag == 0) {
            file_upload.cancelCurrentUploadNotify(this.idx, this.task_id, this.uid, this.atoken, this.ftoken, this.vid);//发送取消上传指令给服务器
            dom_op._info(-1, "已取消");
            dom_op._info(-3, "已取消");
            return;
        } else if (this.g_uploading_flag == 2) {
            this.g_uploading_flag = 1;
            file_upload.cancelCurrentUploadNotify(this.idx, this.task_id, this.uid, this.atoken, this.ftoken, this.vid);//发送取消上传指令给服务器
            //$("#uploadTime").parent().css("display","none");
            dom_op._info(this.idx, "已取消");
            this.upLoadNextFile();
            return;
        }
        if (this.lastSlice != this.currentChunk) {
            this.lastSlice = this.currentChunk;
            this.retryCnt = 0;
        } else {
            this.retryCnt++;
            if (this.retryCnt > 20) {
                alert("网络情况堪忧,停止重试");
                return;
            }
        }
        if (this.blocks[0] > 0) {
            //已经上传过 断点续传
            this.currentChunk = this.blocks[0];
            this.blocks[0] = 0;
        } else {
            //this.currentChunk++;
            /*if ((this.currentChunk + 1) * this.blocksize >= this.file.size) {
                var upload_status = 0;
                if (this.file.size <= 0) {
                    upload_status = 1;
                    dom_op._info(this.idx, "上传失败");
                } else {
                    upload_status = 0;
                    dom_op._info(this.idx, "上传完成");
                }

                dom_op._percent(this.idx, 100);

            } else {*/

                dom_op._percent(this.idx, Math.floor(this.currentChunk * 100 / this.chunks));
            /*}*/
        }

        var start = this.currentChunk * this.blocksize;
        var end = start + this.blocksize >= this.file.size ? this.file.size : start + this.blocksize;
        //reader.readAsBinaryString(blobSlice.call(file, start, end));//ie 10 not work
        reader.readAsArrayBuffer(this.blobSlice.call(this.file, start, end));
        //amsg("File read:" + start + " To " + end);
    },
    //上传事件监听
    'percentComplete': function (evt) {
        console.log('The transfer is complete.');
    },
    //转码失败
    'transferFailed': function (ext) {
        console.log('An error occurred while transferring the file.');
    },
    //转码取消
    'transferCanceled': function (ext) {

        console.log('The transfer has been canceled by the user');
    },
    //取消上传视频任务
    'cancelCurrentUploadNotify': function (idx, task_id, uid, atoken, ftoken, vid) {
        //console.log('Cancel current upload notify and the param is:');
        var cancel_task_param = {
            idx: file_upload.idx,
            task_id: file_upload.task_id,
            user_id: page_init.uid,
            atoken: page_init.atoken,
            ftoken: page_init.ftoken,
            vid: file_upload.vid
        }
        //console.log(cancel_task_param);
        var cancel_task_url = U1SERVER + "/update_status?cmd=2";
        $.ajax({
            type: "get",
            data: {
                idx: file_upload.idx,
                task_id: file_upload.task_id,
                user_id: page_init.uid,
                atoken: page_init.atoken,
                ftoken: page_init.ftoken,
                vid: file_upload.vid
            },
            url: cancel_task_url,
            datatype: 'json',
            success: function (data) {
                var data = JSON.parse(data);
                if (data.ret == 0) {
                    console.log("通知服务器成功");
                    $.cookie(file_upload.file_clone_key, '', { expires: -1 });
                    //amsg("cancelUpload  ok");
                } else {
                    console.log("通知服务器失败" + data.msg);
                }
            },
            error: function (e) {
                console.log(e);
            }
        });
    },
    //上传视频完成
    'upload_done': function (idx, task_id, uid, atoken, ftoken, vid, upload_status) {
        var done_task_param = {
            user_id: uid,
            task_id: task_id,
            cmd: upload_status,
           atoken:atoken,
            ftoken:ftoken
        }
        var done_task_url = U1SERVER + "/update_status";
        //console.log('上传任务完成传参：'+done_task_param);
        $.ajax({
            type: "get",
            data: {
                user_id: uid,
                task_id: task_id,
                cmd: upload_status,
                atoken:atoken,
                ftoken:ftoken
            },
            url: done_task_url,
            dataType: 'json',
            success: function (data) {
                dom_op._uploadDone(file_upload.idx);
                dom_op._info(file_upload.idx);
                if (upload_status == 0) {
                    dom_op._showUploadOver(file_upload.idx);
                } else {
                    dom_op._showUploadFailed(file_upload.idx);
                }

                if (this.g_uploading_flag == 3) {
                    dom_op.finish_uploading();
                } else {
                    dom_op.upLoadNextFile();
                }
            },
            error: function (err) {
                console.log(err);
            }
        });
    }


}