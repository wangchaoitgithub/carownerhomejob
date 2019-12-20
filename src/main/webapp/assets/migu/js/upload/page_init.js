/* 
 * page_init.js
 * 页面初始化js
 * @author benhaixu
 */
/*http://localhost:8080/mghdCrm/migu/Migu_MiGuLogin.do?uname=89389407@qq.com&passwd=726119*/
/*http://localhost:8080/mghdCrm/migu/Migu_MiGuCatalogQuery.do?uid=89389407@qq.com&token=726119*/
var page_init = {
	'uid': 0,
	'atoken': '',
	'ftoken': '',
	//弹窗初始化
	'popups_init': function() {
		$.setupJMPopups({
			screenLockerBackground: "#000",
			screenLockerOpacity: "0.4"
		});
	},
	'openCatPopup': function() {
		$.openPopupLayer({
			name: "myCatPopup",
			width: 437,
			target: "myCatDiv"
		});
	},
	're_onclick': function() {
		document.onclick = function(e) {
			e = e || window.event; // 事件
			var target = e.target || e.srcElement; // 获得事件源
			if(!document.getElementById("popupLayer_myCatDelPopup") && $(target).attr('id') != 'btn_select' && $(target).attr('id') != 'cur_catalog' && $(target).closest('#catalog_select').length == 0 && !$(target).parent().hasClass('jstree-node')) {
				if($("#catalog_select").css("display") != "none")
					$("#catalog_select").slideUp("fast");
			}
		};
	},
	'catalogs_load': function() { //ajax调用获取视频分类
		$.ajax({
			async: true,
			type: "GET",
			url: "../migu/Migu_MiGuCatalogQuery.do?uid=" + page_init.uid + "&token=" + page_init.atoken,
			timeout: 10000,
			dataType: "json",
			success: function(ret) {
				{
					var json = JSON.stringify(ret);
					/*catalogsJson = json;*/
					json = json.replace(/&nbsp;/g, '');
					catalogsJson = ret;
					catalog.createJSTrees1(ret);
				}
			},
			error: function(xhr, ajaxOptions, thrownError) {
				console.log(xhr.status);
				console.log(thrownError);
			},
			complete: function(XMLHttpRequest, status) {
				//请求完成后最终执行参数
				if(status == 'timeout') {
					alert("请求分类目录超时，请检查网络");
				}
			}
		});

	},

	/*获取转码类型*/
	'get_trans_query_version': function() {
		$.ajax({
			async: true,
			type: "GET",
			url: "../migu/Migu_MiGuTemplate.do?uid=" + page_init.uid + "&token=" + page_init.atoken,
			timeout: 6000,
			dataType: "json",
			success: function(ret) {

				console.log(ret);
				if(ret.ret == 0) {
					var data = ret.result;
					var content_list = "";
					for(var i = 0; i < data.length; i++) {
						content_list += '<a href="javascript:;" class="button"  data-id="' + data[i].vtype + '" value="' + data[i].flag + '" onclick="dom_op.toggleTranscodeType(this);">' + data[i].templateName + '</a>';
					}
					$('.transcode_type').append(content_list);
				} else {
					console.error("转码类似获取失败");
				}
			},
			error: function(xhr, ajaxOptions, thrownError) {
				console.log(xhr.status);
				console.log(thrownError);
			},
			complete: function(XMLHttpRequest, status) {
				//请求完成后最终执行参数
				if(status == 'timeout') {
					alert("请求分类目录超时，请检查网络");
				}
			}
		});
	},
	'page_listening': function() {
		$("#file").on("change", function() {
			dom_op.openAddPopup($("#file"));
		});
		$("#popupLayer_myAddPopup #continue_file").on("change", function() {
			dom_op.initAddPopup("popupLayer_myAddPopup", $("#popupLayer_myAddPopup #continue_file"), saveFileListData($("#popupLayer_myAddPopup")))
		});
		$("#upload_list .upload_head #continue_file").on("change", function() {
			dom_op.openAddPopup($("#upload_list .upload_head #continue_file"));
		});
		$("#upload_list .upload_footer #continue_file").on("change", function() {
			dom_op.openAddPopup($("#upload_list .upload_footer #continue_file"));
		});
		$("#upload_list .size #continue_file").on("change", function() {
			dom_op.openAddPopup($("#upload_list .size #continue_file"));
		});
	},
	'string_call': function() {
		String.prototype.endWith = function(s) {
			if(s == null || s == "" || this.length == 0 || s.length > this.length)
				return false;
			if(this.substring(this.length - s.length) == s)
				return true;
			else
				return false;
		}
	},
	'param_init': function() {
		this.uid = $("#uid").html();
		this.atoken = $("#atoken").html();
		this.ftoken = $("#ftoken").html();
	},
	'checkNet': function() {
		window.addEventListener("offline", function(e) {
			$("#netWarning").css('display', 'block');
			console.log("网络断开，请检查网络");
			//alert("网络断开，请检查网络");
		}, false);
		window.addEventListener("online", function(e) {
			$("#netWarning").find('#netBreak').html("网络连接正常，可重试之前的操作");
			$("#netWarning").css('display', 'block');
			$("#netBreak").css('color', '#00CD00');
			setTimeout("dom_op.netLink()", 3000);
			//alert("网络断开，请检查网络");
		}, false);
	},
	'cat_dom': function() {
		$("#menu_upload").find('.Iconlist').addClass('Iconlistopen');
		$("#menu_upload").addClass("open");
		$(".sidebar a").attr("onclick", "return sidebar_click(this);");
		cat_dom = "#catalog_container";
	}
}

window.onbeforeunload = function() {
	if(file_upload.g_uploading_flag != 0) {
		return "有文件正在上传，离开此页将会取消当前上传";
	}
};
//初始化服务器地址
var U1SERVER = window.location.protocol + "//www.migucloud.com/vodupload";

var browser = {
	versions: function() {
		var u = navigator.userAgent,
			app = navigator.appVersion;
		return { //移动终端浏览器版本信息
			trident: u.indexOf('Trident') > -1, //IE内核
			presto: u.indexOf('Presto') > -1, //opera内核
			webKit: u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核
			gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1, //火狐内核
			mobile: !!u.match(/AppleWebKit.*Mobile.*/) || !!u.match(/AppleWebKit/), //是否为移动终端
			ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端
			android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, //android终端或者uc浏览器
			iPhone: u.indexOf('iPhone') > -1 || u.indexOf('Mac') > -1, //是否为iPhone或者QQHD浏览器
			iPad: u.indexOf('iPad') > -1, //是否iPad
			webApp: u.indexOf('Safari') == -1 //是否web应该程序，没有头部与底部
		};
	}(),
	language: (navigator.browserLanguage || navigator.language).toLowerCase()
}
var explorer = navigator.userAgent,
	browse;
//console.log(explorer);
//alert(explorer);
if(explorer.indexOf("Trident") >= 0 || explorer.indexOf("MSIE") >= 0) {
	//ie
	browse = "ie";
} else if(explorer.indexOf("iPhone") >= 0) {
	// Iphone
	browse = "iPhone";
} else if(explorer.indexOf("Firefox") >= 0) {
	// firefox 火狐
	browse = "Firefox";
} else if(explorer.indexOf("Chrome") >= 0) {
	//Chrome 谷歌
	browse = "Chrome";
} else if(explorer.indexOf("Opera") >= 0) {
	//Opera
	browse = "Opera";
} else if(explorer.indexOf("Safari") >= 0) {
	//Safari苹果
	browse = "Safari";
} else if(explorer.indexOf("Netscape") >= 0) {
	//Netscape网景
	browse = "Netscape";
}

function loadCss(file) {
	var head = document.getElementsByTagName('head').item(0);
	css = document.createElement('link');
	css.href = file;
	css.rel = 'stylesheet';
	css.type = 'text/css';
	head.appendChild(css);
}
$(init);

function init() {
	$.ajax({
		async: true,
		type: "GET",
		url: "../migu/Migu_MiGuLogin.do?uname=sfk1987&passwd=726119",
		timeout: 6000,
		dataType: "json",
		success: function(ret) {
			if(ret.ret == 0) {
				$("#uid").html(ret.result.user_info.uid);
				$("#atoken").html(ret.result.atoken);
				$("#ftoken").html(ret.result.atoken);
				page_init.re_onclick(); //重写onclick事件
				page_init.popups_init(); //弹窗初始化
				page_init.param_init(); //初始化参数
				page_init.catalogs_load(); //加载目录分页
				page_init.get_trans_query_version(); //加载转码类型
				page_init.string_call(); //重写string方法
				page_init.page_listening(); //监听用户上传等事件
				page_init.checkNet(); //检查网络是否断开
				page_init.cat_dom();
			} else {
				console.error(ret.msg);
			}

		},
		error: function(xhr, ajaxOptions, thrownError) {
			console.log(xhr.status);
			console.log(thrownError);
		}
	});
}