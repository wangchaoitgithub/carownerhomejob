<?php
/*
 * 上传demo
 * 实现页面上传的客户端demo
 * @author benhaixu
 */
header('content_type:text/html;charset=utf-8');
?>
<!DOCTYPE HTML>
<meta charset="utf-8"/>
<html>
    <link rel="stylesheet" href="./css/demand/font-awesome.css" />
    <link rel="stylesheet" href="./css/demand/font-awesome.min.css"/>
    <link rel="stylesheet" href="./css/common/common.min.css" />
    <link rel="stylesheet" href="./css/demand/upload.css" />
    <link rel="stylesheet" href="./css/demand/jstree/themes/default/style.min.css" />


    <body>
        <div class="demand">
            <span id="uid" style="display:none"><?php echo isset($_GET['uid']) ? intval($_GET['uid']) : 0; ?></span>
            <span id="atoken" style="display:none"><?php echo isset($_GET['atoken']) ? $_GET['atoken'] : ''; ?></span>
            <span id="ftoken" style="display:none"><?php echo isset($_GET['ftoken']) ? $_GET['ftoken'] : ''; ?></span>
            <span id="msg" style="display:none"></span>

            <div class="page">
                <div class="video clear">
                    <div class="upload">
                        <div id="add_btn">
                            <div class="upload_head">
                                咪咕云上传demo
                            </div>
                            <div class="upload_body">
                                <a href="javascript:;" class="file">

                                </a>
                                <div class="btn_upload">
                                    <a>上传视频</a>
                                    <input type="file" id="file" name="files[]" multiple />
                                </div>
                                <p class="warang">目前系统仅支持*.mkv;*.flv;*.asf;*.3gp;*.mp4;*.mov;*.rm;*.f4v;*.rmvb<br/>;*.avi;*.ts;*.wmv的视频格式上传，上传前确保您所上传的视频码率大于<br/>200kbps，否则会导致转码失败</p>
                            </div>
                        </div>
                        <div id="upload_list" class="hidden">
                            <div class="upload_head">
                                已上传视频<span id="total_upload_count">0</span>个，共计<span id="total_used_space">0</span>G
                                <a href="/account/productPage.html?type=2" style="color: #333;border: 1px solid #e6e6e6;font-size: 14px;padding: 4px;font-weight: bolder;margin-left: 40px;">增加空间</a>
                                <span class="input-file" type="button"  style="margin-left:20px!important;vertical-align: middle;" onclick="dom_op.unUpload(this);">
                                    <a href="javascript:;" class="button">继续添加视频</a>
                                    <input type="file" style="height:50px;width:100%;" id="continue_file" name="files[]" multiple="">
                                </span>
                            </div>
                            <h3>
                                <div class="ac">
                                    <!--<input type="checkbox" name="Checkbox1222" id="CheckboxAll" onclick="dom_op.selectAllUploads(this);" style="margin-left: 20px;" />-->
                                    <div onclick="dom_op.selectAllUploads(this)" class="selectAll">
                                        <input type="checkbox" id="CheckboxAll"  name="ipt_checkbox"/>
                                    </div>全部
                                </div>
                                <div id="uploadListCmds" style="display:none;">
                                    <a href="javascript:;" class="button cancel_current" onclick="dom_op.openUploadBatchDelPopup();return false;" title="批量取消上传">批量取消上传</a>
                                </div>
                                <i class="count" id="uploadListOpp" >
                                    已添加<font class="red"  id="count">0</font>个文件,总大小<font id="size" class="red">0KB</font>
                                </i>
                            </h3>
                            <div class="clear"></div>
                            <div class="list clear" id="uploadlist" onchange="dom_op.uploadListOppChange(this);return false;" onpropertychange="dom_op.uploadListOppChange(this);return false;" >
                                <dl >
                                    <dd id="addItem" style="display:none;">
                                        <div class="item_title">
                                            <div class="l1">
                                                <div class="i2">
                                                    <div class="" id="file_name"></div>
                                                    <i class="d2" >700MB</i>
                                                    <span style="display:none;" id="sizeValue">0</span>
                                                </div>
                                                <i id="uploadState" style="display:none;">等待上传</i>
                                                <span id="percent"></span>
                                            </div>
                                            <!--<br class="clear" />-->
                                            <div class="l2">
                                                <div class="check">
                                                    <!--<input id="Checkbox1" class="input_check" type="checkbox" onclick="dom_op.selectOneUpload(this);"/>-->
                                                    <div class="selectOneUpload"  onchange="dom_op.selectOneUpload(this);">
                                                        <input class="input_Upload" id="Checkbox1"  type="checkbox"  name="ipt_checkbox"/>
                                                    </div>
                                                    <span id="CheckEmpty" style="display:none; width: 13px; height: 13px;"></span>
                                                </div>
                                                <li class="progress"><span id="p_percent" class="percentage" style="width: 0%;"></span></li>
                                            </div>
                                            <div class="l3">
                                                <a class="menu_slide" href="javascript:;" onclick="dom_op.uploadSlideToggle(this);">展开视频信息<img src="./images/btn_down.png" /></a><!--<i class="fa fa-chevron-down"></i>-->
                                                <div class="size" >
                                                    <i class="hidden" id="uploadSpeed">0Mbps</i>
                                                    <i class="hidden" id="cancelUpload">
                                                        <a href="javascript:;" id="btn_cancel" onclick="javascript:file_upload.openCancelUploadPopup(this);return false;" class="red">取消上传</a>
                                                    </i>
                                                    <span id='upload_idx' style="display:none"></span>
                                                    <i class="hidden" id="reUpload">
                                                        <span class="input-file" type="button"  style="margin-left:20px!important;vertical-align: middle;" onclick="dom_op.unUpload(this);">
                                                            <a href="javascript:;" id="btn_cancel">重新上传</a>
                                                            <input type="file" style="height:50px;width:100%;" id="continue_file" name="files[]" multiple="">
                                                        </span>
                                                    </i>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="info_slide" style="display:none;">
                                            <li>
                                                <div id="slide_left" style="width: 49%;float: left;">
                                                    <i class="i1"> 主题名：</i><i id="name"></i>
                                                </div>
                                                <div id="slide_right" style="width: 49%;float: right;">
                                                    <div class="i1"> 转码：</div><i id="transflag"></i>
                                                    <span style="display:none;" id="transcodingValue">1</span>
                                                </div>
                                            </li>
                                            <li>
                                                <div id="slide_left" style="width: 49%;float: left;">
                                                    <i class="i1"> 分类：</i><i id="catalog"></i>
                                                    <span style="display:none;" id="catalogsValue">0</span>
                                                </div>
                                                <div id="slide_right" style="width: 49%;float: right;">
                                                    <div class="i1" > 上线：</div><i id="file_publish"></i>
                                                    <span style="display:none;" id="ispublicValue">42</span>
                                                </div>
                                            </li>
                                            <li>
                                                <div id="slide_left" style="width: 49%;float: left;">
                                                    <i class="i1"> 标签：</i><i id="tags"></i>
                                                </div>
                                                <div id="slide_right" class="upload_transcodetype" style="width: 49%;float: right;">
                                                    <i class="i1"> 转码类型：</i>

                                                </div>
                                            </li>

                                        </div>
                                    </dd>
                                </dl>
                                <div id="msg" width="100%">  </div>
                            </div>
                            <div class="upload_footer">
                                <span class="input-file" type="button"  style="vertical-align: middle;" onclick="return dom_op.unUpload(this);">
                                    <a href="javascript:;" class="button">继续添加视频</a>
                                    <input type="file" style="height:50px;width:100%;" id="continue_file" name="files[]" multiple="">
                                </span>
                            </div>
                        </div>
                    </div>

                    <!-- 添加弹出 -->
                    <div id="myAddDiv">
                        <div class="popup add">
                            <div class="header">
                                <h3>选择上传的视频</h3>
                                <a href="javascript:;" onclick="dom_op.closeAddPopup();" title="关闭" class="close">
                                </a>
                                <br class="clear" />
                            </div>

                            <div class="popup-body">
                                <form>
                                    <div class="selectlist clear" id="selectlist" style="overflow-y:auto;overflow-x: hidden;" onscroll="dom_op.hideCatalogPop();">
                                        <div class="lh">
                                            <div class="ac">
                                                <div onclick="dom_op.selectAll(this);" class="selectAll">
                                                    <input type="checkbox" id="CheckboxAll"  name="ipt_checkbox"/>
                                                </div>
                                                <i id="CheckedNumberUp"> 全部视频 </i>
                                            </div>
                                           
                                        </div>

                                        <dl id="addList">
                                            <dd id="addItem" style="display:none;">
                                                <ul>
                                                    <div class="item_title">
                                                        <div class="check">
                                                            <!--<input id="Checkbox1" type="checkbox" onclick="dom_op.selectOne(this);" style="margin-top: 9px;"/>-->
                                                            <div class="selectOne"  onchange="dom_op.selectOne(this);">
                                                                <input class="input_inner " id="Checkbox1"  type="checkbox"  name="ipt_checkbox"/>
                                                            </div>
                                                        </div>
                                                        <div class="i2">
                                                            <div class="" id="file_name"></div>
                                                        </div>
                                                        <div class="i3">
                                                            <a class="menu_slide" href="javascript:;"  onclick="dom_op.infoSlideToggle(this);"><!--<i class="fa fa-chevron-down"></i>--><img src="./images/btn_edit_normal.png" /></a>
                                                            <a class="item_clear" href="javascript:;" onclick="javascript:dom_op.removeItem(this);"><img src="./images/btn_delete_normal.png"></a>
                                                        </div>
                                                        <div class="i4">
                                                            <i class="d2" >700MB</i>
                                                            <span style="display:none;" id="sizeValue">0</span>
                                                        </div>
                                                    </div>
                                                    <div class="info_slide" style="display: none">
                                                        <li style="display: none;">
                                                            <i class="i1">* 主题名：</i><input id="name" type="text" class="input"/>
                                                        </li>
                                                        <li>
                                                            <i class="i1">  分类：</i>
                                                            <a class="styled-select" id="btn_select" onclick="dom_op.catalogSlideToggle(this);">
                                                                <span class="cur-select" id="cur_catalog" value="0">请选择分类</span>
                                                            </a>
                                                        </li>
                                                        <li>
                                                            <i class="i1">  标签：</i><input id="tags"  name="tags" type="text" class="input" placeholder="请输入标签名称"/>
                                                        </li>
                                                        <li>
                                                            <div class="i1">
                                                                <div class="l">
                                                                    <div class="i1"><i>*</i> 转码：</div>
                                                                    <div class="transFlagOne"  onclick="dom_op.transBgAll(this, '2');">
                                                                        <span style="display: none">1</span>
                                                                    </div>
                                                                </div>
                                                                <div class="r">
                                                                    <div class="i1" ><i>*</i> 上线：</div>
                                                                    <div class="transPublicOne" onclick="dom_op.transPublicAll(this);">
                                                                        <span style="display: none">42</span>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </li>
                                                        <li class="tanscodeType" id="tanscodeType">
                                                            <div class="i1">转码类型：</div><i class="txt"></i>
                                                            <div class="transcode_type" id="transcode_type">
                                                            </div>
                                                        </li>
                                                    </div>
                                                </ul>
                                            </dd>
                                        </dl>
                                    </div>
                                </form>
                                <p class="btn">
                                    <span  onclick="file_upload.file_upload('myAddDiv');" style="margin-left:20px!important;vertical-align: middle;position: relative;display: inline-block;" title="开始上传"><a href="javascript:;" class="button" >开始上传</a></span>
                                    <span class="input-file" type="button"  style="margin-left:20px!important;vertical-align: middle;" onclick="return dom_op.unUpload(this);"></span>
                                </p>
                            </div>
                        </div>
                    </div>
                    <!-- 取消弹出 -->
                    <div id="myCancelDiv">
                        <div class="popup">
                            <div class="popup-header">
                                <h2>取消上传</h2>
                                <a style="float:right;margin-right:5px;font-size:18px;"href="javascript:;" onclick="$.closePopupLayer('myCancelUploadPopup');;" title="关闭" class="close">
                                </a>
                                <br class="clear" />
                            </div>
                            <div class="popup-body">
                                <div style="text-align:center; margin-top: 20px; margin-bottom:10px;font-size: 16px;">
                                    <i >确定需要取消上传吗？</i><br>
                                    <i style="font-size:14px;color:#808080;">取消上传后将删除该文件的所有信息</i>
                                </div>
                            </div>
                            <div class="popup-footer" style="text-align: center;margin: 0 80px;clear: both;overflow: hidden;">
                                <a  href="javascript:file_upload.cancelUploadFile();"title="确定" class="ok" style=" width: 100px;height: 38px;line-height: 38px; background: #f2b602;color: #fff; display: block;text-align: center;font-size: 16px; float: left;border-radius: 3px;">确定</a>
                                <a href="javascript:;" class="cancel" onclick="$.closePopupLayer('myCancelUploadPopup');" title="取消" style=" width: 98px;height: 38px;line-height: 38px;border: 1px solid #cccccc;color: #333333;display: block;text-align: center;font-size: 16px;float: left; margin-left: 30px; border-radius: 3px;">取消</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div id="myCatDelDiv">
            <div class="popup">
                <div class="popup-header">
                    <h2>删除分类</h2><a href="javascript:;" class="close" onclick="$.closePopupLayer('myCatDelPopup')" title="关闭窗口"></a>
                </div>
                <div class="popup-body myCatDelDiv">
                    <p>您确认删除“<span id='catalog_name'>xxx</span>”分类吗？<br>删除该分类后，该分类下的视频文件将处于"默认分类"状态</p>
                    <span id='catalog_id' style="display:none"></span>
                    <span id='catalog_parentFlag' style="display:none"></span>
                </div>
                <div class="popup-footer">
                    <a  href="javascript:;" onclick="catalog.delete_catalog();" title="确定" class="ok">确定</a>
                    <a href="javascript:;" class="cancel" onclick="$.closePopupLayer('myCatDelPopup')" title="取消">取消</a>
                </div>
            </div>
        </div>

        <!-- 批量删除弹出 -->
        <div id="myUploadDelBatchDiv">
            <div class="popup">
                <div class="popup-header">
                    <h2>批量取消上传</h2><a href="javascript:;" class="close" onclick="$.closePopupLayer('myUploadBatchDelPopup')" title="关闭窗口"></a>
                </div>
                <div class="popup-body myUploadDelBatchDiv">
                    <p>您确认取消这些上传操作吗？</p>
                    <span id='video_id' style="display:none"></span>
                </div>
                <div class="popup-footer">
                    <a  href="javascript:;" onclick="dom_op.cancelUploadings();" title="确定" class="ok">确定</a>
                    <a href="javascript:;" class="cancel" onclick="$.closePopupLayer('myUploadBatchDelPopup')" title="取消">取消</a>
                </div>
            </div>
        </div>

        <!--检查网络信息提示-->
        <div id="netWarning" onclick="dom_op.netChat();">
            <h3 id="netBreak">网络连接失败，请检查网络后重试</h3>
        </div>

        <div id="catalog_select" style="display: none;">
            <div id="catalog_container">
            </div>
            <a href="javascript:;" class="button" style="float: left; margin-left: 20px; margin-top: 14px;" onclick="catalog.create_first_cat();" title="添加新分类">+ 添加新分类<i class="glyphicon glyphicon-asterisk"></a>
        </div>
    </body>
    <script type="text/javascript" src="./js/jquery/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="./js/jquery/jquery.cookie.js"></script>
    <script type="text/javascript" src="./js/jstree/jstree1.min.js"></script>
    <script type="text/javascript" src="./js/jquery/jquery.jmpopups-0.5.1.rk.js"></script>
    <script type="text/javascript" src="./js/upload/sparkmd5.js"></script>
    <script type="text/javascript" src="./js/upload/md5_min.js"></script>
    <script type="text/javascript" src="./js/upload/catalog.js"></script>
    <!--页面初始化js-->
    <script type="text/javascript" src="./js/upload/page_init.js"></script>
    <!--常用的dom操作方法-->
    <script type="text/javascript" src="./js/upload/dom_op.js"></script>
    <script type="text/javascript" src="./js/upload/file_upload.js"></script>

    <!-- <script type="text/javascript" src="js/dom.js"></script> -->
    <script>


    </script>

</html>
