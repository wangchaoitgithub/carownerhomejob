$(document).ready(function() {
    var commonId = getCookie("commonId");
    rechargeRecord(commonId);
    getApplyInfo();
});

function rechargeRecord(customerId) {
    $("#jqGrid").jqGrid({
        url: baseURL+'vice/ViceSeeRecord_pageListAll.do',
        postData:{'customerId': customerId},
        datatype: "json",
        colModel: [
            { label: '记录ID', name: 'id', index: "id", width: 30, key: true },
            { label: '创建时间', name: 'createTime',formatter:function(cellvalue, options, row)
                {return new Date(cellvalue).Format('yyyy-MM-dd hh:mm:ss')}, width: 60 },
            // { label: '创建人', name: 'createOperator', width: 30},
            // { label: '记录最后修改人', name: 'lastOperator' , sortable: false, width: 50 },
            // { label: '最后修改时间', name: 'lastModifyTime',formatter:function(cellvalue, options, row)
            //     {return new Date(cellvalue).Format('yyyy-MM-dd hh:mm:ss')},  width: 60 },
            { label: '组织id', name: 'orgId', width: 50 },
            { label: '组织名称', name: 'orgName', width: 50 },
            { label: '创建者id', name: 'userId', width: 50 },
            { label: '创建者名称', name: 'nickName', width: 50 },
            { label: '接收人Id', name: 'receiveUserId', width: 50 },
            { label: '接收人名称', name: 'receiveUserName', width: 50 },
            { label: '副牌', name: 'viceNumber', width: 30},
            { label: '车照图片', name: 'carPhotoUrl', sortable: false, width: 50 },
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
            lastOperator:null,
            lastModifyTime:null,
            orgId:null,
            userId:null,
            receiveUserId:null,
            viceNumber:null,
            orgName:null,
            nickName:null,
            receiveUserName:null,
            carPhotoUrl:null
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
            var starDate = $("#starDate").val();
            var endDate = $("#endDate").val();
            var orgIds = $("#orgIds").val();
            $("#jqGrid").jqGrid('setGridParam',{
                postData:{/*'likeName': likeName,*/'userId': userId,'starDate':starDate,
                    'endDate':endDate,'orgIds':orgIds},
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