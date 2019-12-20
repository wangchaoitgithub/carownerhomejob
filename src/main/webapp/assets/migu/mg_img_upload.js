var mg_img_upload = {
    'file_reader': {},
    file_list: {},
    'blobSlice': File.prototype.mozSlice || File.prototype.webkitSlice || File.prototype.slice,
    'server': "",
    'callback': '',
    'progress':'',
    'mark':'',
    onprogress:function(evt){
        var percent = evt.loaded/evt.total*100;
        var jindu = percent.toFixed(2);
        mg_img_upload.progress(jindu);
    },
    'create': function (data) {
       if(data.mark!=undefined){
           this.mark=data.mark;
        }
        if (data.server) {
            this.server = data.server;
        } else {
            throw new Error("param_err_server")
        }

        if (data.callback) {
            this.callback = data.callback;
        } else {
            throw new Error("param_err_callback")
        }
        if (data.file_obj) {
            this.file_list = data.file_obj[0].files;
        } else {
            throw new Error("param_err_file_obj")
        }
        if(data.progress){
            this.progress = data.progress;
        }
        this.file_reader = new FileReader();

        return mg_img_upload;
    },
    'upload': function () {
        var fileArray = Array.prototype.slice.call(this.file_list, 0);
        this.file_reader.readAsArrayBuffer(this.blobSlice.call(fileArray[0]));

        this.file_reader.onload = function (e) {
            var block_content = new Uint8Array(e.target.result);
            $.ajax({
                type: 'POST',
                url: mg_img_upload.server,
                data: block_content,
                processData: false, // 不会将 data 参数序列化字符串
                contentType: false, // 根据表单 input 提交的数据使用其默认的 contentType
                success: function (data) {
                    if(mg_img_upload.mark!=""){
                        obj=JSON.parse(data)
                        obj.mark=mg_img_upload.mark;
                        mg_img_upload.callback(obj);
                    }else {
                        mg_img_upload.callback(data);
                    }
                },
                'error': function () {
                    mg_img_upload.callback({'ret': -1, 'msg': '请求失败，请稍后再试'});
                },
                xhr: function(){  
                var xhr = $.ajaxSettings.xhr();  
                    if(mg_img_upload.progress && xhr.upload) {  
                     xhr.upload.addEventListener("progress" ,mg_img_upload.onprogress, false);  
                     return xhr;  
                    }  
               }

            });
        }


    }

}
