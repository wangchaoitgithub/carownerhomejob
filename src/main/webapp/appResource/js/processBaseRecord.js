$(document).ready(function() {
    var commonId = getCookie("commonId");
    rechargeRecord(commonId);
});

function rechargeRecord(customerId) {
    $("#jqGrid").jqGrid({
        url: baseURL+'process/ProcessBaseRecord_pageListAll.do',
        postData:{'customerId': customerId},
        datatype: "json",
        colModel: [
            { label: '记录ID', name: 'id', index: "id", width: 30, key: true },
            { label: '创建人', name: 'createOperator', width: 50},
            { label: '创建时间', name: 'createTime',formatter:function(cellvalue, options, row)
                {return new Date(cellvalue).Format('yyyy-MM-dd hh:mm:ss')}, width: 50 },
            { label: '最后修改人', name: 'lastOperator' , sortable: false, width: 40 },
            { label: '最后修改时间', name: 'lastModifyTime',formatter:function(cellvalue, options, row)
                {return new Date(cellvalue).Format('yyyy-MM-dd hh:mm:ss')},  width: 50 },

            { label: '审核人id', name: 'nextCheckUserId', width: 40 },
            { label: '业务类型', name: 'businessType', sortable: false, width: 50 },
            { label: '业务id', name: 'businessId', width: 50 },
            { label: '类型', name: 'processType', width: 50 },
            { label: '审核结果', name: 'result', width: 40},
            { label: '审核意见', name: 'remarkCheck', width: 60},
            { label: '审核结果', name: 'status', width: 40},
            { label: '上一条流程记录的id', name: 'lastProcessId', width: 40}
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
            nextCheckUserId:null,
            businessType:null,
            businessId:null,
            processType:null,
            result:null,
            remarkCheck:null,
            status:null,
            lastProcessId:null
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
            $("#jqGrid").jqGrid('setGridParam',{
                postData:{/*'likeName': likeName,*/'userId': userId,'starDate':starDate,'endDate':endDate},
                page:page
            }).trigger("reloadGrid");
        }
    }
});