$(document).ready(function() {
	var commonId = getCookie("commonId");
	rechargeRecord(commonId);
	getApplyInfo(commonId);
});

function rechargeRecord(customerId) {
    $("#jqGrid").jqGrid({
        url: baseURL+'order/OrderBalanceRecord_pageListAll.do',
        postData:{'customerId': customerId},
        datatype: "json",
        colModel: [
			{ label: '记录ID', name: 'id', index: "id", width: 30, key: true },
			{ label: '创建时间', name: 'createTime', formatter:function(cellvalue, options, row)
				{return new Date(cellvalue).Format('yyyy-MM-dd hh:mm:ss')}, width: 50 
			},
			{ label: '应用名称', name: 'applyName', width: 50 },
			{ label: '交易前的剩余量', name: 'currentBalance', width: 50 },
            { label: '交易类型', name: 'type', sortable: false, width: 20 },
			{ label: '交易数量', name: 'count', width: 50 },
			{ label: '交易后的剩余量', name: 'afterBalance', width: 50 },
			{ label: '原因', name: 'reason', width: 60}
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
        	name:null,
        	operatorsId:null,
        	customerId:null,
        	smsChannelNumber:null,
        	smsKeyword:null,
        	desc:null,
        	phoneIndex:null,
        	overTime:null
        }
    },
    methods: {
        query: function () {
            vm.reload();
        },
        reload: function () {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam','page');
        	var likeName = $("#applyName option:selected").val();
        	var starDate = $("#starDate").val();
        	var endDate = $("#endDate").val();
        	$("#jqGrid").jqGrid('setGridParam',{
        	    postData:{'likeName': likeName,'starDate': starDate,'endDate': endDate},
        	    page:page
        	}).trigger("reloadGrid");
        }
    }
});


function getApplyInfo(customerId) {
  $.ajax({
      type: "POST",
      url: baseURL + "base/BaseApplyInfo_selectInfo.do",
      data: "customerId="+customerId,
      success: function(r){
    	  var text = "<option  value ='' selected>请选择应用</option>";
          if(r.success  && r.data != null){
        	  var customerInfo = r.data;
        	  $.each(customerInfo, function(i, info) {
        		  text += "<option value='" + info.id + "'>" + info.name + "</option>";
        	  })
        	  $("#applyName").html(text);
          }else{
        	  $("#applyName").html(text);
          }
      }
  });
}

/*
$(document).ready(function() {
	getOperatorsInfo();
});
function getOperatorsInfo() {
  $.ajax({
      type: "POST",
      url: baseURL + "base/BaseOperatorsInfo_selectInfo.do",
      data: vm.customer,
      success: function(r){
    	  var text = "<option  value ='null' selected>请选择运营商</option>";
          if(r.success && r.data != null){
        	  var operators = r.data;
        	  $.each(operators, function(i, info) {
        		  text += "<option value='" + info.id + "'>" + info.nameSimple + "</option>";
        	  })
        	  $("#operatorsId").html(text);
          }else{
        	  $("#operatorsId").html();
          }
      }
  });
}*/