$(document).ready(function() {
	var commonId = getCookie("commonId");
	rechargeRecord(commonId);
});

function rechargeRecord(customerId) {
    $("#jqGrid").jqGrid({
        url: baseURL+'customer/CustomerRechargeRecord_pageListAll.do',
        postData:{'customerId': customerId},
        datatype: "json",
        colModel: [
			{ label: '记录ID', name: 'id', index: "id", width: 30, key: true },
            { label: '创建时间', name: 'createTime', formatter:function(cellvalue, options, row)
                {return new Date(cellvalue).Format('yyyy-MM-dd hh:mm:ss')}, width: 50
            },
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
            /*根据时间查询函数*/
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam','page');
        	var likeName = $(".form-control").val();
        	$("#jqGrid").jqGrid('setGridParam',{
        	    postData:{'likeName': likeName/*,'customerId': customerId*/},
        	    page:page
        	}).trigger("reloadGrid");
        }
    }
});