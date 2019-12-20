$(document).ready(function() {
	var commonId = getCookie("commonId");
	rechargeRecord(commonId);
	getApplyInfo(commonId);
});

function rechargeRecord(customerId) {
    $("#jqGrid").jqGrid({
        url: baseURL+'phone/PhoneSmsRecord_pageListAll.do',
        postData:{'customerId': customerId},
        datatype: "json",
        colModel: [
			{ label: '记录ID', name: 'id', index: "id", width: 30, key: true },
			{ label: '创建时间', name: 'createTime', formatter:function(cellvalue, options, row)
				{return new Date(cellvalue).Format('yyyy-MM-dd hh:mm:ss')}, width: 50 
			},
			{ label: '获取手机号时间', name: 'getTime', formatter:function(cellvalue, options, row)
				{return new Date(cellvalue).Format('yyyy-MM-dd hh:mm:ss')}, width: 50 
			},
			{ label: '获取验证码时间', name: 'doneTime', formatter:function(cellvalue, options, row)
				{return new Date(cellvalue).Format('yyyy-MM-dd hh:mm:ss')}, width: 50 
			},
            { label: '验证码', name: 'verificationCode', sortable: false, width: 20 },
			{ label: '手机号', name: 'phoneNumber', width: 50 },
			{ label: 'UUID', name: 'uuid', width: 50 },
			{ label: '应用名称', name: 'applyName', width: 50}
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
        },
        exportData: function() {
        	var applyName = $("#applyName option:selected").val();
        	var starDate = $("#starDate").val();
        	var endDate = $("#endDate").val();
        	if(isBlank(starDate) || isBlank(starDate)){
        		$.jGrowl("起始时间/截止时间-不鞥为空");
        		return;
        	}
        	window.open("../phone/PhoneSmsRecord_excelExport.do?"+"applyName="+applyName+"&starDate="+starDate+"&endDate="+endDate);
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
        		  text += "<option value='" + info.appid + "'>" + info.name + "</option>";
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