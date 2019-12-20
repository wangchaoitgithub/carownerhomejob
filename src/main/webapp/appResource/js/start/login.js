/**
 * yipeng
 * 2018-10-25  
 */
$(document).ready(function(){ 
  $("#submitBtnId").click(function(){
	  login();
  });
});
$(document).keypress(function(e) {
	// 回车键事件 
	if(e.which == 13) {
		login();
	}
});
var base;
function login() {
	var loginName = $("#usernameId").val();
    var password = $("#passwordId").val();
    $.ajax({
    	url:"../base/BaseCustomerInfo_login.do?loginName="+loginName+"&password="+password,
    	error:function(){
           alert("发生错误！");
        },
        success:function(data){
        	console.log(data);
        	base = data;
        	var result = data;
        	console.log(result.success);
        	console.log(result.data);
        	if(result.success){
        		window.location.href="../menu/baseCustomerInfo.html";
        	}else{
        		alert(result.data);
        	}
        }
    });
}