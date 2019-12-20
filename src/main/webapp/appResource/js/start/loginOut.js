$('#loginOutId').click(function(){
	 $.ajax({
        url:"../base/BaseCustomerInfo_loginOut.do",
        error:function(){
        	$.jGrowl("Error:退出失败");
        	setTimeout(signOut,1000);
        },
        success: function(data) {
	    	if(!data.success){
	    		$.jGrowl("Error：退出失败");
	    		setTimeout(signOut,1000);
	    	}else{
	    		$.jGrowl("退出成功");
	    		setTimeout(signOut,1000);
	    	}
	    }
	 }); 
});

function signOut() {
	window.location.href="../start/login.html";
}