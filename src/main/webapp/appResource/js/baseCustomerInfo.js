
var vm = new Vue({
    el:'#rrapp',
    data:{
        showList: false,
        title:"客户信息",
        customer:{
        	nickName:null,
        	corporateName:null,
        	city:null,
        	applyCount:null,
        	requestNumber:null,
        	useNumber:null,
        	rechargeCount:null
        }
    },
    mounted: function () {
    	this.$nextTick(function () {
    		vm.query();
    	})
    },
    filters:{
        filterApplyCount:function (applyCount) {
        	if(applyCount == null){
        		return "";
        	}
        	if(applyCount == 0){
        		return "暂无应用";
        	}else{
        		return applyCount+"个应用";
        	}
        }
    },
    methods: {
        query: function () {
        	//
        	$.ajax({
      	      type: "POST",
      	      url: baseURL + "base/BaseCustomerInfo_getInfo.do",
      	      success: function(r){
      	    	  if(r.success){
      	    		  vm.customer = r.data;
      	    	  }
      	      }
      	  });
        }
    }
});


