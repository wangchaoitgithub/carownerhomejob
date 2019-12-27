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
            // { label: '创建人', name: 'createOperator', width: 50},
            { label: '创建时间', name: 'createTime',formatter:function(cellvalue, options, row)
                {return new Date(cellvalue).Format('yyyy-MM-dd hh:mm:ss')}, width: 50 },
            { label: '最后修改人', name: 'lastOperator' , sortable: false, width: 40 },
            { label: '最后修改时间', name: 'lastModifyTime',formatter:function(cellvalue, options, row)
                {return new Date(cellvalue).Format('yyyy-MM-dd hh:mm:ss')},  width: 50 },

            { label: '审核人id', name: 'userId', width: 40 },
            { label: '下一个审核人id', name: 'nextCheckUserId', width: 40 },
            { label: '审核人名称', name: 'nickName', width: 40 },
            { label: '审核业务类型', name: 'businessType', sortable: false, width: 50 },
            { label: '业务id', name: 'businessId', width: 50 },
            { label: '类型', name: 'processType', width: 50 },
            { label: '审核结果', name: 'result', width: 40},
            { label: '审核意见', name: 'remarkCheck', width: 60},
            { label: '流程状态', name: 'status', width: 40},
            { label: '上一条流程记录的id', name: 'lastProcessId', width: 40},
            { label: '备用字段', bak1: 'lastProcessId', width: 40}
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
            testJqfrid();
            testJqfrid2();
        }
    });
}

function testJqfrid(){
    //拿到grid对象
    var obj = $("#jqGrid");
//获取grid表中所有的rowid值
    var rowIds = obj.getDataIDs();
//初始化一个数组arrayData容器，用来存放rowData
    //var arrayData = new Array();
    var cssprop = {
    };
    if (rowIds.length > 0) {
        for (var i = 0; i < rowIds.length; i++) {
            //rowData=obj.getRowData(rowid);//这里rowid=rowIds[i];
            var rowData = obj.getRowData(rowIds[i]);
            // alert(rowData.nickName);
            var dataRow;
            if(rowData.businessType == "auth"){
                dataRow = {
                    businessType :"认证"
                };
                cssprop = {};
            }else if (rowData.businessType == "userInfoUpdate") {
                dataRow = {
                    businessType :"用户信息修改"
                };
                cssprop = { };
            }else if (rowData.businessType == "buyViceNumber"){
                dataRow = {
                    businessType :"副牌"
                };cssprop = {};
            } else if (rowData.businessType == "help") {
                dataRow = {
                    businessType :"求助"
                };cssprop = {};
            }else if (rowData.businessType == "forum") {
                dataRow = {
                    businessType :"发帖"
                };cssprop = {};
            }else if (rowData.businessType == "comment") {
                dataRow = {
                    businessType :"评论"
                };cssprop = {};
            }else if (rowData.businessType == "addGroupActivity") {
                dataRow = {
                    businessType :"导入群活动"
                };cssprop = {};
            }
            $("#jqGrid").jqGrid('setRowData', rowIds[i], dataRow, cssprop);
        }
    }
}



function testJqfrid2(){
    //拿到grid对象
    var obj = $("#jqGrid");
//获取grid表中所有的rowid值
    var rowIds = obj.getDataIDs();
//初始化一个数组arrayData容器，用来存放rowData
    //var arrayData = new Array();
    var cssprop = {
    };
    if (rowIds.length > 0) {
        for (var i = 0; i < rowIds.length; i++) {
            //rowData=obj.getRowData(rowid);//这里rowid=rowIds[i];
            var rowData = obj.getRowData(rowIds[i]);
            // alert(rowData.nickName);
            var dataRow;

            if(rowData.processType == "submit"){
                dataRow = {
                    processType :"提交"
                };
                cssprop = {};
            }else if (rowData.processType == "firstCheck") {
                dataRow = {
                    processType :"初审"
                };
                cssprop = { };
            }else if (rowData.processType == "secondCheck"){
                dataRow = {
                    processType :"终审"
                };cssprop = {};
            }
            $("#jqGrid").jqGrid('setRowData', rowIds[i], dataRow, cssprop);
        }
    }
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
            userId:null,
            nextCheckUserName:null,
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
            var nextCheckUserId = $("#nextCheckUserId").val();
            var businessType = $("#businessType").val();
            var result = $("#result").val();
            var status = $("#status").val();
            var lastProcessId = $("#lastProcessId").val();
            $("#jqGrid").jqGrid('setGridParam',{
                postData:{/*'likeName': likeName,*/'userId': userId,'starDate':starDate
                    ,'endDate':endDate,'nextCheckUserId':nextCheckUserId,'businessType':businessType,
                    'result':result,'status':status,'lastProcessId':lastProcessId},
                page:page
            }).trigger("reloadGrid");
        }
    }
});
