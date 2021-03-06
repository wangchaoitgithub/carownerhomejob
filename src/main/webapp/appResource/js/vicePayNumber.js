$(document).ready(function() {
    var commonId = getCookie("commonId");
    rechargeRecord(commonId);
    getApplyInfo()
});

function rechargeRecord(customerId) {
    $("#jqGrid").jqGrid({
        url: baseURL+'vice/VicePayNumber_pageListAll.do',
        postData:{'customerId': customerId},
        datatype: "json",
        colModel: [
            { label: '记录ID', name: 'id', index: "id", width: 30, key: true },
            // { label: '创建人', name: 'createOperator', width: 30},
            { label: '创建时间', name: 'createTime',formatter:function(cellvalue, options, row)
                {return new Date(cellvalue).Format('yyyy-MM-dd hh:mm:ss')}, width: 50 },
            // { label: '最后修改人', name: 'lastOperator' , sortable: false, width: 60 },
            // { label: '最后修改时间', name: 'lastModifyTime',formatter:function(cellvalue, options, row)
            //     {return new Date(cellvalue).Format('yyyy-MM-dd hh:mm:ss')},  width: 50 },

            { label: '组织id', name: 'orgId', width: 30 },
            { label: '组织名称', name: 'orgName', width: 50 },
            { label: '用户id', name: 'userId', width: 50 },
            { label: '用户名称', name: 'nickName', width: 50 },
            { label: '选购的副牌号', name: 'viceNumber', width: 50 },
            { label: '状态', name: 'state', width: 60},
            { label: '快递-收件人', name: 'expressName', sortable: false, width: 50 },
            { label: '快递-手机号', name: 'expressPhone', width: 50 },
            { label: '快递-收货地址', name: 'expressAddress', width: 50 },
            { label: '支付的钱', name: 'payMoney', width: 50 },
            { label: '我方生成的订单id', name: 'payWeOrderId', width: 50 },
            { label: '微信的订单id', name: 'payWeixinOrderId', width: 50 }
            // { label: '微信支付 回调状态', name: 'payWeixinStatus', width: 50 }
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
            userId:null,
            createTime:null,
            orgId:null,
            state:null,
            viceNumber:null,
            expressName:null,
            expressPhone:null,
            expressAddress:null,
            payMoney:null,
            payWeOrderId:null,
            orgName:null,
            nickName:null,
            payWeixinOrderId:null
        }
    },
    methods: {
        query: function () {
            vm.reload();
        },
        reload: function () {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam','page');
            // var likeName = $(".form-control").val();
            var userId = $("#userId").val();
            var userNameCode = $("#userName").val();
            var userName = encodeURI(userNameCode);
            var starDate = $("#starDate").val();
            var endDate = $("#endDate").val();
            var orgIds = $("#orgIds").val();
            var payWeOrderId = $("#payWeOrderId").val();
            var payWeixinOrderId = $("#payWeixinOrderId").val();
            var viceNumber = $("#viceNumber").val();
            var expressPhone = $("#expressPhone").val();
            var expressNameCode = $("#expressName").val();
            var expressName = encodeURI(expressNameCode);
            var state = $("#state").val();
            var orgNameCode = $("#orgName").val();
            var orgName = encodeURI(orgNameCode);
            $("#jqGrid").jqGrid('setGridParam',{
                postData:{/*'likeName': likeName,*/'userId': userId,'starDate':starDate,
                    'endDate':endDate,'orgIds':orgIds,'payWeOrderId': payWeOrderId,'payWeixinOrderId':payWeixinOrderId
                    ,'userName':userName,'viceNumber':viceNumber,'expressPhone':expressPhone,'expressName':expressName,
                    'state':state,'orgName':orgName
                },
                page:page
            }).trigger("reloadGrid");
        }
    }
});

function getApplyInfo() {
    $.ajax({
        type: "POST",
        url: baseURL + "vice/VicePayNumber_selectInfo.do",
        data: vm.customer,
        success: function(r) {
            var text = "<option  value ='' >请选择组织</option>";
            if (r.success && r.data != null) {
                var customerInfo = r.data;
                $.each(customerInfo, function (i, info) {
                    text += "<option value='" + info.orgId + "'>" + info.orgName + "</option>";
                })
                $("#orgIds").html(text);
            } else {
                $("#orgIds").html(text);
            }
        }
    });
}