$(function (){
	$.ajax({
		type: "POST",
		url: "../base/BaseCustomerInfo_queryTopData.do",
	    error: function(request) {
	        window.location.href="../start/login.html";
	    },
	    success: function(data) {
   		 	var dateObject = data;
	    	if(dateObject.success==false){
	    		 window.location.href="../start/login.html";
	    	}else{
	    		var sessionUserInfo = dateObject.data;
	    		var nickName = sessionUserInfo.nickName;
	    		$("#userId").html(sessionUserInfo.userId);
	    		$("#commonId").html(sessionUserInfo.userId);
     			$("#userNameId").html(nickName);
     			
     			$("#nickName").html(nickName);
     			$("#corporateName").html(sessionUserInfo.corporateName);
     			$("#city").html(sessionUserInfo.city);
     			$("#requestNumber").html(sessionUserInfo.requestNumber);
     			$("#useNumber").html(sessionUserInfo.useNumber);
     			$("#rechargeCount").html(sessionUserInfo.rechargeCount);
     			if(sessionUserInfo.applyCount == "0"){
     				$("#applyCount").html("暂无应用");
     			}else{
     				$("#applyCount").html(sessionUserInfo.applyCount+"个应用");
     			}
     			//客户ID setCookie
     			setCookie("commonId",sessionUserInfo.userId,7);
	    	}
	    }
	});
});

//使用定时器每秒向div写入当前时间
setInterval("getDate()",1000);

function getDate(){
	//获取当前时间
	var date = new Date();
	//格式化为本地时间格式
	var date1 = date.toLocaleString();
	//将时间写入div
	$("#now").html(date1);
}
