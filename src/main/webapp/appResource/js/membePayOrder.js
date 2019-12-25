$(document).ready(function() {
    var commonId = getCookie("commonId");
    rechargeRecord(commonId);
});

function rechargeRecord(customerId) {
    $("#jqGrid").jqGrid({
        url: baseURL+'membe/MembePayOrder_pageListAll.do',
        postData:{'customerId': customerId},
        datatype: "json",
        colModel: [
            { label: '记录ID', name: 'id', index: "id", width: 30, key: true },
            { label: '创建人', name: 'createOperator',width: 50 },
            { label: '创建时间', name: 'createTime',formatter:function(cellvalue, options, row)
                {return new Date(cellvalue).Format('yyyy-MM-dd hh:mm:ss')}, width: 50 },
            // { label: '记录最后修改人', name: 'lastOperator' , sortable: false, width: 60 },
            // { label: '最后修改时间', name: 'lastModifyTime',formatter:function(cellvalue, options, row)
            //     {return new Date(cellvalue).Format('yyyy-MM-dd hh:mm:ss')},  width: 50 },
            { label: '组织id', name: 'orgId', width: 30 },
            { label: '用户id', name: 'userId', width: 30},
            { label: '支付的钱', name: 'payMoney', width: 50},
            { label: '产品ID', name: 'productId', width: 40 },
            { label: '加会员期', name: 'addMonthCount', sortable: false, width: 50 },
            // { label: '加前的到期日期', name: 'preMemberDate', width: 50 },
            { label: '加之后的到期日期', name: 'afterMemberDate', width: 50 },
            { label: '生成的订单id', name: 'payWeOrderId', width: 60},
            { label: '微信订单id', name: 'payWeixinOrderId', width: 60},
            // { label: '微信支付回调状态', name: 'payWeixinStatus', width: 60},
            { label: '订单状态', name: 'status', width: 60}
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
            createOperator:null,
            createTime:null,
            orgId:null,
            userId:null,
            payMoney:null,
            productId:null,
            addMonthCount:null,
            afterMemberDate:null,
            payWeOrderId:null,
            payWeixinOrderId:null,
            status:null
        }
    },
    methods: {
        query: function () {
            vm.reload();
        },
        reload: function () {           //根据订单id查
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam','page');
            // var likeName = $(".form-control").val();
            var payWeOrderId = $("#payWeOrderId").val();
            var payWeixinOrderId = $("#payWeixinOrderId").val();
            var starDate = $("#starDate").val();
            var endDate = $("#endDate").val();
            var createOperatorCode = $("#createOperator").val();
            var createOperator = encodeURI(createOperatorCode);
            $("#jqGrid").jqGrid('setGridParam',{
                postData:{/*'likeName': likeName,*/'payWeOrderId': payWeOrderId,'payWeixinOrderId':payWeixinOrderId,
                    'starDate':starDate,'endDate':endDate,'createOperator':createOperator},
                page:page
            }).trigger("reloadGrid");
        }
    }
});