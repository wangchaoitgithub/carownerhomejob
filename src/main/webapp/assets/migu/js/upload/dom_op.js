/* 
 * dom_op.js
 * 上传页面常用的dom操作方法
 * @author benhaixu
 */
var dom_op = {
    'g_lastPercentMs': 0,
    'g_lastPercent': 0,
    'g_video_info': '',
    'transcode_types': ["流畅", "标清", "高清", "超清", "原画质"],
    'uploadList': [],
    'speedActiveFlag': false,
    'cat_dom': '',
    'publicValue':0,
    'FlagValue':42,
    //打开弹窗
    'openAddPopup': function (inputFileObj) {
        //console.log('用户打开添加弹窗');
        //console.log(inputFileObj);
        //初始化dom
        this.initAddPopup('myAddDiv', inputFileObj);
        $.openPopupLayer({
            name: "myAddPopup",
            width: 920,
            target: "myAddDiv"
        });

    },
    //根据视频初始化弹窗
    'initAddPopup': function (popupId, inputFileObj, saveFileListData) {
        var fileSelect = inputFileObj[0];
        var files = fileSelect.files;

        var fileArray = Array.prototype.slice.call(files, 0);
        for (var i = 0; i < fileArray.length; i++) {

            if (this.filterFileByName(fileArray[i].name)) {
                this.uploadList.push(fileArray[i]);
            }
        }
        fileSelect.outerHTML = fileSelect.outerHTML;// IE chrome
        fileSelect.value = "";//火狐
        var addList = $("#" + popupId).find('#addList');
        //if have the last data,use the last data init AddPopup
        if (saveFileListData && saveFileListData.length != 0) {

        } else {
            addList.children().each(function () {
                if ($(this).attr('id') != 'addItem') {
                    $(this).remove();
                }
            });
        }

        var addItem = addList.find("#addItem");
        //根据catalog.js catalogsJson 重新加载分类
        catalogsJson = eval(catalogsJson);
        if (catalogsJson) {
            var selObj = addItem.find('.info_slide').find('#add_catalog');
            var allCataLogs = $("#" + popupId).find("#allCataLogs");
            selObj.find("option").remove();
            allCataLogs.find("option").remove();
            for (var i = 0; i < catalogsJson.length; i++) {
                var value = catalogsJson[i].id;
                var text = catalogsJson[i].text;
                selObj.append("<option style=\"text-overflow:ellipsis;overflow:hidden;white-space:nowrap;width:110px;\" value='" + value + "'>" + text + "</option>");
                allCataLogs.append("<option style=\"text-overflow:ellipsis;overflow:hidden;white-space:nowrap;width:110px;\" value='" + value + "'>" + text + "</option>");
            }
        }



        for (var i = 0; i < fileArray.length; i++) {
            if (this.filterFileByName(fileArray[i].name)) {
                var newItem = addItem.clone();
                newItem.prop('display', 'inline');
                newItem.prop('id', addItem.prop('id') + i);

                newItem.find('.item_title').find('#file_name').html(fileArray[i].name);
                newItem.find('.info_slide').find('#name').attr('value', fileArray[i].name);
                newItem.find('.item_title').find('#file_name').attr('value', fileArray[i].name);

                newItem.find('.item_title').find('.d2').html(this.readablizeBytes(fileArray[i].size));
                newItem.find('.item_title').find('#sizeValue').html(fileArray[i].size);
                $(addItem).before($(newItem));
                $(newItem).show();
            }
        }
        this.g_video_info = '';
    },
    //关闭添加窗口
    'closeAddPopup': function () {
        $.closePopupLayer('myAddPopup');
        var fileSelect = document.getElementById('file');
        fileSelect.outerHTML = fileSelect.outerHTML;// IE chrome
        fileSelect.value = "";//火狐
        this.uploadList = [];
    },
    //添加窗口 展开编辑
    'infoSlideToggle': function (obj) {
        var el = $(obj).parent().parent();
        el.next().slideToggle();
        el.toggleClass("open");
        if (el.hasClass("open")) {
            /*$(obj).html("完成编辑<i class='fa fa-chevron-down'></i>");*/
        } else {
            /*$(obj).html("展开编辑<i class='fa fa-chevron-down'></i>");*/
        }
    },
    //移除上传视频dom
    'removeItem': function (obj) {
        var fileSelect = document.getElementById('file');
        //console.log(fileSelect);
        var files = fileSelect.files;
        var item = $(obj).parent().parent().parent().parent();
        var index = item.attr('id').replace(/addItem/, '');
        delete this.uploadList[index]
        var addList = $("#popupLayer_myAddPopup").find('#addList');
        var len = addList.children().length;
        if(len <= 2){
            $.closePopupLayer('myAddPopup');
            this.uploadList = [];
        }
        item.remove();
        

    },
    'file_upload_op': function (popupId) {
        var addList = $("#popupLayer_myAddPopup").find('#addList');
        var len = addList.children().length;
        var isfilenamenull = 0;
        addList.children().each(function (index) {
            if (index != (len - 1)) {
                //console.log($(this).find('.info_slide').find('#name').attr('value'));
                if ($(this).find('.info_slide').find('#name').attr('value') == "") {
                    alert("主题名不可为空，请重新输入");
                    isfilenamenull = 1;
                    return false;
                }
            }
        });
        if (isfilenamenull == 1) {
            return false;
        }
        $.closePopupLayer('myAddPopup');
        $("#add_btn").addClass('hidden');
        $("#upload_list").removeClass('hidden');
//	var fileSelect = document.getElementById('file');
//	var files = fileSelect.files;

        var uploadlistdl = $("#uploadlist").find('dl');
        //clearUploadListId(uploadlistdl);
        var begin = uploadlistdl.children().length - 1;
        addList.children().each(function (index) {
            if (index != (len - 1)) {
                var addItem = uploadlistdl.find("#addItem");
                var newItem = addItem.clone();
                newItem.prop('display', 'inline');
                //newItem.prop('id', "uploadfile_"+ $(this).attr('id').replace("addItem",""));
                newItem.prop('id', "uploadfile_" + (begin + index));

                newItem.find('.item_title').find('#file_name').html($(this).find('.item_title').find('#file_name').attr('value'));
                newItem.find('.info_slide').find('#name').html($(this).find('.info_slide').find('#name').attr('value'));

                newItem.find('.item_title').find('#uploadState').css("display", "inline");
                newItem.find('.item_title').find('#uploadState').html("正在分析文件");
                newItem.find('.item_title').find('#cancelUpload').css("display", "inline");
                //newItem.find('.item_title').find('#reUpload').css("display","inline");
                newItem.find('.item_title').find('#upload_idx').html((begin + index));

                newItem.find('.item_title').find('.d2').html($(this).find('.item_title').find('.d2').html());
                newItem.find('.item_title').find('#sizeValue').html($(this).find('.item_title').find('#sizeValue').html());
                //newItem.find('.d3').html(25);
                //newItem.find('.d3').find("#catalogsValue").html(25);
                var val = $(this).find('.info_slide').find('#cur_catalog').attr("value");
                newItem.find('.info_slide').find("#catalogsValue").html(val);
                var cat_name = $(this).find('.info_slide').find('#cur_catalog').html();
                if (val == '0')
                    cat_name = "默认分类";
                newItem.find('.info_slide').find('#catalog').html(cat_name);
                //console.log($(this).find('.d3').html());
                //alert($(this).find('.d3').find('#Select2').find("option:selected").attr("value"));

                var tags = $(this).find('.info_slide').find('#tags').attr('value');
                newItem.find('.info_slide').find('#tags').html(tags == "" ? '&nbsp' : tags).attr("title", tags == "" ? '&nbsp' : tags);

                //判断是否上线
                var file_publish=$(this).find('.transPublicOne').find('span').html()
                if(file_publish==42){
                    newItem.find('.info_slide').find('#file_publish').html('否');
                }
                if(file_publish==41){
                    newItem.find('.info_slide').find('#file_publish').html('是');
                }
                newItem.find('.info_slide').find("#ispublicValue").html(file_publish);

                //判断是否转码
                var transflag=$(this).find('.transFlagOne').find('span').html()
                if(transflag==0){
                    newItem.find('.info_slide').find("#transflag").html('是');
                    //显示要转的视频类型
                    var transcodes = [];
                    $(this).find('.info_slide').find('#transcode_type').children('a').each(function (index) {
                        //获取对应的index值
                        transcodes[index] = $(this).attr('value');
                    });

                    newItem.find("#slide_right").children('a').each(function (index) {
                        if(transcodes[index]==0||transflag==0){
                            $(this).addClass('bnt_close');
                        }
                        $(this).attr('value', transcodes[index]);
                    });
                }
                if(transflag==1){
                    newItem.find('.info_slide').find("#transflag").html('否');
                    //显示要转的视频类型
                    var transcodes = [];
                    $(this).find('.info_slide').find('#transcode_type').children('a').each(function (index) {
                        //获取对应的index值
                        transcodes[index] = $(this).attr('value');
                    });

                    newItem.find("#slide_right").children('a').each(function (index) {
                        if(transcodes[index]==0||transflag==0){
                            $(this).addClass('bnt_close');
                        }
                        $(this).attr('value', 0);
                    });
                }

                newItem.find('.info_slide').find("#transcodingValue").html(transflag);

                //alert("Alert text");
                $(addItem).before($(newItem));
                $(newItem).show();
            }
        });
        dom_op.uploadListOppChange();
        //console.log('this is dom_op startUpload');

    },
    '_removeDeleteLink': function (idx) {
        $("#uploadfile_" + idx).find('#delete').html("");
    },

    //过滤视频内容 只允许以下格式
    'filterFileByName': function (name) {
        var names = ['asf', 'avi', 'flv', 'swf', 'rmvb', 'mkv', 'mpeg', 'mov', 'mp4', '3gp', 'ts', 'wmv', 'rm', 'vob'];
        for (var i = 0; i < names.length; i++) {
            if (name.toLowerCase().endWith(names[i])) {
                return true;
            }
        }
        return false;
    },
    //将字节转化成其他单位
    'readablizeBytes': function (bytes) {
        var s = ['Bytes', 'KB', 'MB', 'GB', 'TB', 'PB'];
        var e = Math.floor(Math.log(bytes) / Math.log(1024));
        return (bytes / Math.pow(1024, Math.floor(e))).toFixed(2) + " " + s[e];
    },
    //将bit位转化成其他单位
    'readablizeBit': function (bit) {
        var s = ['bit', 'Kb', 'Mb', 'Gb', 'Tb', 'Pb'];
        var e = Math.floor(Math.log(bit) / Math.log(1024));
        return (bit / Math.pow(1024, Math.floor(e))).toFixed(2) + " " + s[e];

    },
    //显示上传视频进度与速度
    '_uploadSpeed': function (per, unUploadSize, speed) {
        if (file_upload.g_uploading_flag == 0) {
            return;
        }
        if (speed && dom_op.readablizeBit(speed) && speed > 0.001 && ((unUploadSize / speed) < 3600 * 24)) {
            $("#uploadfile_" + file_upload.idx).find("#uploadSpeed").html("上传速度：" + dom_op.readablizeBit(speed) + "ps");
        } else {
            $("#uploadfile_" + file_upload.idx).find("#uploadSpeed").html("上传速度：0kb/s");
        }

        $("#uploadfile_" + file_upload.idx).find('#p_percent').css("width", per + "%");
        $("#uploadfile_" + file_upload.idx).find('#percent').text(per + "%");

        $("#uploadfile_" + file_upload.idx).find('.item_title').find("#uploadSpeed").css("display", "inline");

        $("#uploadfile_" + file_upload.idx).find('.info_slide').find("#transcode_type").each(function (index) {
            $(this).attr('value', $(this).attr('value'));
        })
    },
    'md5_show': function (idx, info) {
        $('#uploadfile_' + idx + ' #uploadState').show().html("正在分析文件" + info);

    },
    '_percent': function (idx, per) {
        $('#uploadfile_' + idx + ' #uploadState').hide();
        if (file_upload.idx >= 0) {

            var d = new Date(), currentMs = d.getTime(), delta = currentMs - this.g_lastPercentMs;
            var thistitle = "";
            if (delta > 100 && delta < 20000 && per > this.g_lastPercent) {

                $("#uploadfile_" + file_upload.idx).find('.item_title').find('#uploadState').css("display", "none");

            }

            if (per == 100) {
                thistitle = "上传已经完成";
            }

            if (per != this.g_lastPercent) {
                this.g_lastPercent = per;
                this.g_lastPercentMs = currentMs;

                if (file_upload.g_uploading_flag != 0) {
                    if (thistitle != "") {
                        $("#uploadfile_" + file_upload.idx).find('.item_title').attr("title", thistitle);
                    }
                }
            }
        }

        $("#uploadfile_" + file_upload.idx).find('#p_percent').css("width", per + "%");
        $("#uploadfile_" + file_upload.idx).find('#percent').html(per + "%");
    },
    //进度条展示
    '_progress': function (idx) {

    },
    //计算上传任务总数并展示
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
                $("#uploadListOpp").css("display", "inline");
            }
        });
        //alert(size);
        if (count < 1) {
            $("#uploadListOpp").css("display", "none");
        } else {
            $("#uploadListOpp").css("display", "inline");
            $("#uploadListOpp").find("#count").html(count);
            $("#uploadListOpp").find("#size").html(this.readablizeBytes(size));
        }
    },
    //获取点播目录分类
    'catalogSlideToggle': function (obj) {
        var ul = $("#catalog_select");
        if (ul.css("display") == "none") {
            ul.css("left", $(obj).offset().left);
            var anchorTop = $(obj).offset().top + $(obj).get(0).offsetHeight;
            ul.css("top", anchorTop);
            ul.slideDown("fast");
        } else {
            ul.slideUp("fast");
        }
        cur_catalog_obj = $(obj).find('#cur_catalog');
        return false;

    },
    //上传选项：清晰度按钮 选择
    'toggleTranscodeType': function (obj) {
        console.log(obj);
        if ($(obj).attr('value') == '1') {
            $(obj).attr('value', '0');
        } else {
            $(obj).attr('value', '1');
        }
    },
    //单选 需要上传的任务
    'selectOne': function (cb) {
        if (!cb.checked) {
            $("#popupLayer_myAddPopup #CheckboxAll").attr('checked', false);
        }
        var cbinput = $(cb).find('input')[0];
        var f = cbinput.form;
        var c = false;
        var n = 0;
        for (i = 0; i < f.length; i++) {
            if (f.elements[i].type == "checkbox") {
                if (f.elements[i].checked == true) {
                    c = true;
                    n++;
                    //break;
                }
            }
        }
        var checked=$(cb).find('input')[0].checked;
        if(checked==true){
            $(cb).css('background','url(../assets/migu/images/button_bg.png) 50% 50% no-repeat');
            $("#popupLayer_myAddPopup #CheckedNumberUp").html("已选择" + n + "个文件");
            $("#popupLayer_myAddPopup #batch").removeClass("hidden");
            $("#popupLayer_myAddPopup #multi_edit").slideDown();
        }
        if(checked==false){
            $(cb).css('background','#fff');
            $("#popupLayer_myAddPopup #CheckedNumberUp").html("全部文件");
            /*$("#popupLayer_myAddPopup #batch").addClass("hidden");*/
            $("#popupLayer_myAddPopup #multi_edit").slideUp();
        }
    },
    
    //全选 需要上传的任务
    'selectAll': function (cb) {
        var checked=$(cb).find('input')[0].checked;
        list = $("#popupLayer_myAddPopup #addList").children();
        if(checked==true){
            //显示批量
            $(cb).css('background','url(../assets/migu/images/button_bg.png) 50% 50% no-repeat');
            $('.selectOne').find('input').prop("checked", true);
            $('.selectOne').css('background','url(../assets/migu/images/button_bg.png) 50% 50% no-repeat');
            $("#popupLayer_myAddPopup #CheckedNumberUp").html("已选择" + (list.length - 1) + "个文件");
            $("#popupLayer_myAddPopup #batch").removeClass("hidden");
            $("#popupLayer_myAddPopup #multi_edit").slideDown();
        }
        if(checked==false){
            //隐藏批量
            $(cb).css('background','#fff');
            $('.selectOne').find('input').removeAttr("checked");
            $('.selectOne').css('background','#fff');
            $("#popupLayer_myAddPopup #CheckedNumberUp").html("全部视频");
            $("#popupLayer_myAddPopup #multi_edit").slideUp();
        }

        /*var list = $("#popupLayer_myAddPopup #addList").children();
        $("#popupLayer_myAddPopup #addList input:checkbox:not(:last)").attr('checked', cb.checked);
        //}
        if (cb.checked) {
            //显示批量删除
            $("#popupLayer_myAddPopup #CheckedNumberUp").html("已选择" + (list.length - 1) + "个文件");
            $("#popupLayer_myAddPopup #batch").removeClass("hidden");
            $("#popupLayer_myAddPopup #multi_edit").slideDown();
        } else {
            //隐藏批量删除
            $("#popupLayer_myAddPopup #CheckedNumberUp").html("全部视频");
            $("#popupLayer_myAddPopup #multi_edit").slideUp();
        }*/
    },
    //给添加新分类
    'hideCatalogPop': function () {
        $("#catalog_select").slideUp("fast");
    },
    'create_first_cat': function () {
        var ref = $.jstree.reference(this.cat_dom);
        var sel = ref.create_node("0", {"type": "file"});
        if (sel) {
            ref.edit(sel);
        }
    },
    //上传任务列表中的人物信息展示
    'uploadSlideToggle': function (obj) {
        var el = $(obj).parent().parent();
        el.next().slideToggle();
        el.toggleClass("open");
        if (el.hasClass("open")) {
            $(obj).html("收起视频信息");
        } else {
            $(obj).html("展开视频信息");
        }
    },
    //从上传任务列表中选中一个任务
    'selectOneUpload': function (cb) {
        var checked=$(cb).find('input')[0].checked;
        if(checked==true){
            $(cb).css('background','url(../assets/migu/images/button_bg.png) 50% 50% no-repeat');
        }
        if(checked==false){
            $(cb).css('background','#fff');
        }
        dom_op.showUploadBatch();
    },
    //全选上传任务列表中的任务
    'selectAllUploads': function (cb) {
        var list = $("#uploadlist dl").children();
        $("#uploadlist input:checkbox:not(:last)").attr('checked', cb.checked);

        var checked=$(cb).find('input')[0].checked;
        if(checked==true){
            $(cb).css('background','url(../assets/migu/images/button_bg.png) 50% 50% no-repeat');
            $('.selectOneUpload').find('input').prop("checked", true);
            $('.selectOneUpload').css('background','url(../assets/migu/images/button_bg.png) 50% 50% no-repeat');
        }
        if(checked==false){
            $(cb).css('background','#fff');
            $('.selectOneUpload').find('input').removeAttr("checked");
            $('.selectOneUpload').css('background','#fff'); //隐藏批量删除
        }
        dom_op.showUploadBatch();
    },
    //展示从上传任务列表中选中一个任务的信息
    'showUploadBatch': function () {
        var c = false;
        $("#uploadlist dl").children().each(function () {
            if (($(this).css("display") != "none") && ($(this).find(".item_title").find(".check").css("display") != "none") && ($(this).find(".item_title").find("#Checkbox1")[0].checked)) {
                c = true;
            }
        });
        if (c == true) {
            $("#upload_list #uploadListCmds").css("display", "block");
        }
        else {
            $("#upload_list #uploadListCmds").css("display", "none");
        }
    },
    //单个确认/取消任务上传任务
    'openUploadBatchDelPopup': function () {
        $.openPopupLayer({
            name: "myUploadBatchDelPopup",
            width: 400,
            target: "myUploadDelBatchDiv"
        });
    },
    //批量确认/取消任务上传任务
    'cancelUploadings': function () {
        $.closePopupLayer('myUploadBatchDelPopup');
        var uploadlistdl = $("#uploadlist").find('dl');
        //cancel panding upload task first
        uploadlistdl.children().each(function (index) {
            if (($(this).find(".item_title").find("#Checkbox1").css("display") != "none") && ($(this).find(".item_title").find("#Checkbox1")[0].checked)) {
                if (((index != file_upload.g_upload_idx - 1) && (file_upload.g_uploading_flag > 0)) || (file_upload.g_uploading_flag == 0)) {
                    $(this).attr('id', $(this).attr("id") + "remove");
                    $(this).css("display", "none");
                }
            }
        });
        dom_op.uploadListOppChange();
        uploadlistdl.children().each(function (index) {
            if (($(this).find(".item_title").find("#Checkbox1").css("display") != "none") && ($(this).find(".item_title").find("#Checkbox1")[0].checked)) {
                if ((index == file_upload.g_upload_idx - 1) && (file_upload.g_uploading_flag > 0)) {
                    $(this).attr('id', $(this).attr("id") + "remove");
                    $(this).css("display", "none");
                    dom_op.cancelCurrentUploading();
                    $("#uploadfile_" + (file_upload.g_upload_idx - 1)).find('.item_title').find(".check").find("input").attr('checked', false);
                }
            }
        });
        $("#upload_list #CheckboxAll").attr('checked', false);
        $("#upload_list #uploadListCmds").css("display", "none");
    },
    //取消上传任务成功后返回
    'cancelCurrentUploading': function () {
        dom_op._info(file_upload.g_upload_idx - 1, "取消中");
        $("#uploadfile_" + (file_upload.g_upload_idx - 1)).find('#cancelUpload').html('上传已取消');
        $("#uploadfile_" + (file_upload.g_upload_idx - 1)).find('.item_title').attr("title", "");
        if (3 == file_upload.g_uploading_flag) {
            dom_op.finish_uploading_leave_cmds();
        } else {
            file_upload.g_uploading_flag = 2;
        }
    },
    'finish_uploading_leave_cmds': function () {
        file_upload.g_uploading_flag = 0;
        file_upload.g_upload_idx = $("#uploadlist").children().children("dd").length - 1;//记住最后一个标记，为以后上传做起始值

        $("#file").prop('disabled', false);
        var fileSelect = document.getElementById('file');

        fileSelect.outerHTML = fileSelect.outerHTML;// IE chrome
        fileSelect.value = "";//火狐
        this.clearTimer();
        file_upload.fristUploadFlag = true;
    },
    'clearTimer': function () {
        if (file_upload.timerId) {
            clearInterval(file_upload.timerId);
        }
    },
    //无法田间正在上传的任务
    'unUpload': function () {
        var fileSelect = document.getElementById('file');
        //console.log(fileSelect);
        var files = fileSelect.files;
        if (files.length > 0) {
            //$("#file").prop('disabled', true);
            alert("当前文件正在上传中,无法添加文件");
            return false;
        }
        return true;
    },
    //上传完成操作
    '_uploadDone': function (idx) {
        dom_op._removeDeleteLink(file_upload.idx);
    },
    //上传任务列表中显示上传完成
    '_showUploadOver': function (idx) {
        $("#uploadfile_" + file_upload.idx).find('.item_title').find('#uploadState').css("display", "inline");
        $("#uploadfile_" + file_upload.idx).find('.item_title').find('#uploadState').css("color", "#0EAD31");
        $("#uploadfile_" + file_upload.idx).find('.item_title').find('#uploadState').html("上传完成");
        $("#uploadfile_" + file_upload.idx).find('.item_title').find('#percent').css("display", "none");
        //$("#uploadfile_"+idx).find('.item_title').find("#uploadSpeed").css("display","none");
        $("#uploadfile_" + file_upload.idx).find('.item_title').find("#uploadSpeed").html("上传完成，正在进行审核，可在<a href='/video.html' target='_blank'>视频管理</a>中查看文件状态");
        $("#uploadfile_" + file_upload.idx).find('.item_title').find('.check').find('#Checkbox1').css("display", "none");
        $("#uploadfile_" + file_upload.idx).find('.item_title').find('.check').find('#CheckEmpty').css("display", "block");
        $("#uploadfile_" + file_upload.idx).find('.item_title').find('#cancelUpload').css("display", "none");
        $("#uploadfile_" + file_upload.idx).find('.item_title').find('#p_percent').css("background", "#67D84C");
    },
    //上传任务列表中显示上传失败
    '_showUploadFailed': function (idx) {
        $("#uploadfile_" + file_upload.idx).find('.item_title').find('#uploadState').css("display", "inline");
        $("#uploadfile_" + file_upload.idx).find('.item_title').find('#uploadState').css("color", "red");
        $("#uploadfile_" + file_upload.idx).find('.item_title').find('#uploadState').html("上传失败");
        $("#uploadfile_" + file_upload.idx).find('.item_title').find('#percent').css("display", "none");
        $("#uploadfile_" + file_upload.idx).find('.item_title').find("#uploadSpeed").css("display", "none");
        $("#uploadfile_" + file_upload.idx).find('.item_title').find('#btn_reupload').css("display", "inline");
        $("#uploadfile_" + file_upload.idx).find('.item_title').find('#cancelUpload').css("display", "none");
        $("#uploadfile_" + file_upload.idx).find('.item_title').find('#reUpload').css("display", "inline");
        $("#uploadfile_" + file_upload.idx).find('.item_title').find('#p_percent').css("background", "#CC2C2C");
    },
    //完成上传
    'finish_uploading': function () {
        dom_op.finish_uploading_leave_cmds();
        fristUploadFlag = true;
    },
    //继续上传下一个任务
    'upLoadNextFile': function () {
        if (!this.uploadList || this.uploadList.length == 0) {
            console.log("uploadList size is 0");
            return;
        }

        if (file_upload.g_upload_idx < this.uploadList.length) {
            if (typeof $("#uploadfile_" + file_upload.g_upload_idx).attr('id') != 'undefined') {
                dom_op._removeDeleteLink(file_upload.g_upload_idx);
                file_upload.uploadOneFile(this.uploadList[file_upload.g_upload_idx], file_upload.g_upload_idx);
                //console.log(file_upload.g_upload_idx);
                file_upload.g_upload_idx++;
            } else {
                file_upload.g_upload_idx++;
                this.upLoadNextFile();
            }
            //alert(g_upload_idx);
        } else {
            dom_op.finish_uploading();
        }
    },
    '_info': function (idx, info) {

        if (file_upload.idx == -1) {
            var uploadlistdl = $("#uploadlist").find('dl');
            uploadlistdl.children().each(function (index) {

                if (($(this).attr('id').indexOf("uploadfile") > -1) && found) {
                    $(this).find('#delete').html(info);

                }

                if ($(this).attr('id') == ("uploadfile_" + (file_upload.g_upload_idx - 1))) {
                    found = true;
                }
            });

        } else if (idx == -2) { // dont cancel current, using g_upload_idx !!!!
            var uploadlistdl = $("#uploadlist").find('dl');
            var found = false;
            uploadlistdl.children().each(function (index) {
                if (($(this).attr('id').indexOf("uploadfile") > -1) && found) {
                    $(this).find('#delete').html(info);
                } else {
                    console.log($(this).attr('id') + "  should be ignored");
                }
                if ($(this).attr('id') == ("uploadfile_" + (file_upload.g_upload_idx - 1))) {
                    found = true;
                }
            });

        } else if (idx == -3) { // dont cancel current, using g_upload_idx !!!!
            var uploadlistdl = $("#uploadlist").find('dl');
            var found = false;
            uploadlistdl.children().each(function (index) {
                if (($(this).attr('id').indexOf("uploadfile") > -1) && ($(this).attr('id') == ("uploadfile_" + (file_upload.g_upload_idx - 1)))) {
                    $(this).find('#delete').html(info);
                }
            });

        } else {
            $("#uploadfile_" + file_upload.idx).find('#delete').html(info);
        }
    },
    'netLink':function(){
        $("#netWarning").css('display','none');
    },
    'chtransflag':function(){
        console.log("!!!!!!!!!!!");
        var selected_val = document.getElementById('transflag').value;
        console.log(selected_val);
        if (selected_val==0){
            $('#tanscodeCtrl').css('display','block');
        }else {
            $('#tanscodeCtrl').css('display','none');
        }
    },
    'transBgAll':function(herf,number){
        var disp =  $(herf).find('span').html();
        // var transflag_value = document.getElementById("transflag1");
        if (disp == 1) {
            $(herf).css('backgroundImage',"url('../assets/migu/images/btn_open.png')");
            if(number=="1"){
                $(herf).parent().parent().parent().next().css("display","block");
            }else{
                $(herf).parent().parent().parent().next().css("display","block");
            }
            $(herf).find('span').html(0);
            this.publicValue=0

        }
        if (disp == 0) {
            $(herf).css('backgroundImage',"url('../assets/migu/images/btn_close.png')");
            if(number=="1"){
                $(herf).parent().parent().parent().next().css("display","none");
            }else{
                $(herf).parent().parent().parent().next().css("display","none");
            }
            $(herf).find('span').html(1);
            this.publicValue=1;
        }
    },

    'transPublicAll':function(herf,number){
        // var transpublic_value = document.getElementById("ispublicValue0");
        var disp =  $(herf).find('span').html();
        if (disp == 42){
            $(herf).css('backgroundImage',"url('../assets/migu/images/btn_open.png')");
            $(herf).find('span').html(41);
            if(number=='1'){
                this.FlagValue=41;
            }
            // transpublic_value.options[0].value = 41;
            // this.publicValue = transpublic_value.options[0].value;
            // console.log(this.publicValue);
        }
        if(disp == 41){
            $(herf).css('backgroundImage',"url('../assets/migu/images/btn_close.png')");
            $(herf).find('span').html(42);
            if(number=='1'){
                this.FlagValue=42;
            }
            // transpublic_value.options[1].value = 42;
            // this.publicValue = transpublic_value.options[1].value;
            // console.log(this.publicValue);
        }
    },
    'transBgOne':function(bg){
        console.log(bg);
        var disp = $("#tanscodeType").css("display");
        var transflag_value = 0;
        console.log(disp);
        $('#transflag1').find("option").value = 1;
        if (disp == "none") {
            $("#addList #transBgOne").css('backgroundImage',"url('../assets/migu/images/btn_open.png')");
            $('#addList .i1 #transPublicOne').css("display","block");
            $('#transflag1').find("option").value = 0;
        }
        if (disp == "block") {
            $("#addList #transBgOne").css('backgroundImage',"url('../assets/migu/images/btn_close.png')");
            $('#addList .i1 #transPublicOne').css("display","none");
            $('#transflag1').find("option").value = 1;
        }
    },
}