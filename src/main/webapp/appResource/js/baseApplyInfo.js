$(document).ready(function() {
	var commonId = getCookie("commonId");
	applyInfo(commonId);
	getOperatorsInfo();
});

function applyInfo(customerId) {
	$("#jqGrid").jqGrid({
        url: baseURL+'base/BaseApplyInfo_pageListAll.do',
        postData:{'customerId': customerId},
        datatype: "json",
        colModel: [
			{ label: '应用ID', name: 'id', index: "id", width: 25, key: true },
			{ label: '创建时间', name: 'createTime', formatter:function(cellvalue, options, row)
				{return new Date(cellvalue).Format('yyyy-MM-dd hh:mm:ss')}, width: 50
			},
            { label: '应用名称', name: 'name', width: 50 },
            { label: '', name: 'operatorsId',hidden: true },
			{ label: '运营商', name: 'nameSimple', width: 40 },
		/*	{ label: '客户ID', name: 'customerId', width: 30 },*/
			{ label: '请求手机号次数', name: 'requestNumber', width: 60 },
			{ label: '获取验证码次数', name: 'useNumber', width: 60},
			/*{ label: '短信通道号', name: 'smsChannelNumber', width: 30},
			{ label: '短信关键字', name: 'smsKeyword', width: 45},*/
			{ label: '应用描述', name: 'desc', width: 50},
			/*{ label: '手机号索引值', name: 'phoneIndex', width: 30},
			{ label: '短信过期时间', name: 'overTime', width: 30},*/
			{ label: 'appid', name: 'appid', width: 60},
			{ label: '密钥', name: 'appsecret', width: 60}
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "data.list",
            page: "data.currPage",
            total: "data.totalPage",
            records: "data.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
}

var vm = new Vue({
    el:'#rrapp',
    data:{
        showList: true,
        title:null,
        customer:{
        	id:null,
        	operatorsId:null,
        	desc:null
        }
    },
    methods: {
        query: function () {
            vm.reload();
        },
        update: function () {
            var userId = getSelectedRow();
            if(userId == null){
                return ;
            }
            vm.showList = false;
            vm.title = "修改";
            var rowData = $("#jqGrid").jqGrid('getRowData',userId);
            vm.customer = rowData;
        },
        saveOrUpdate: function () {
        	var msg = vm.check();
        	if(!isBlank(msg)){
        		$.jGrowl(msg);
        		return;
        	}
            var url = "base/BaseApplyInfo_update.do";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                data: vm.customer,
                success: function(r){
                    if(r.success){
                    	$.jGrowl("操作成功");
                        vm.reload();
                    }else{
                    	$.jGrowl("操作失败");
                    }
                }
            });
        },
        reload: function () {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam','page');
        	var likeName = $("#likeName").val();
        	var operatorsId = $("#nameSimple option:selected").val();
        	$("#jqGrid").jqGrid('setGridParam',{
        	    postData:{'likeName': likeName, 'operatorsId':operatorsId, 'customerId': customerId},
        	    page:page
        	}).trigger("reloadGrid");
        },
        check: function () {
        	var errorMsg = "";
        	if(isBlank(vm.customer.operatorsId) || vm.customer.operatorsId =='null'){
        		errorMsg = "运营商—不能为空";
        	}
    		return errorMsg;
        }
    }
});


function getOperatorsInfo() {
	  $.ajax({
	      type: "POST",
	      url: baseURL + "base/BaseOperatorsInfo_selectInfo.do",
	      data: vm.customer,
	      success: function(r){
	    	  var text = "<option  value ='' selected>请选择运营商</option>";
	          if(r.success && r.data != null){
	        	  var operators = r.data;
	        	  $.each(operators, function(i, info) {
	        		  text += "<option value='" + info.id + "'>" + info.nameSimple + "</option>";
	        	  })
	        	  $("#operatorsId").html(text);
	        	  $("#nameSimple").html(text);
	          }else{
	        	  $("#operatorsId").html();
	        	  $("#nameSimple").html(text);
	          }
	      }
	  });
	}