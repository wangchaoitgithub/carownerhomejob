$(document).ready(function() {
    var commonId = getCookie("commonId");
    rechargeRecord(commonId);
});

function rechargeRecord(customerId) {
    $("#jqGrid").jqGrid({
        url: baseURL+'communication/CommunicationCommentRecord_pageListAll.do',
        postData:{'customerId': customerId},
        datatype: "json",
        colModel: [
            { label: '记录ID', name: 'id', index: "id", width: 30, key: true },
            { label: '创建人', name: 'createOperator', width: 50,hidden:true },
            { label: '评论者id', name: 'userId', width: 30},
            { label: '评论人', name: 'nickName', width: 50},
            { label: '创建时间', name: 'createTime',formatter:function(cellvalue, options, row)
                {return new Date(cellvalue).Format('yyyy-MM-dd hh:mm:ss')}, width: 50 },
            { label: '记录最后修改人', name: 'lastOperator' , sortable: false, width: 60,hidden:true },
            { label: '最后修改时间', name: 'lastModifyTime',formatter:function(cellvalue, options, row)
                {return new Date(cellvalue).Format('yyyy-MM-dd hh:mm:ss')},  width: 50 ,hidden:true},
            { label: '回复的哪个人的id', name: 'receiveUserId', sortable: false, width: 30 },

            { label: '回复的哪个人的名称', name: 'receiveUserName', sortable: false, width: 50 },
            { label: '评论内容', name: 'comment', width: 50 },
            { label: '回复之前评论的id', name: 'replyCommentId', width: 50 },
            { label: 'ip地址', name: 'ip', width: 40},
            { label: '评论数量', name: 'commentCount', width: 20},
            { label: '点赞数量', name: 'goodCount', width: 20},
            { label: '业务id', name: 'recordId', width: 20},
            { label: '类型', name: 'type', width: 40}
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

            if(rowData.type == "secretWord"){
                dataRow = {
                    type :"悄悄话"
                };
                cssprop = {};
            }else if (rowData.type == "activity") {
                dataRow = {
                    type :"群活动"
                };
                cssprop = { };
            }else if (rowData.type == "forum"){
                dataRow = {
                    type :"论坛帖子"
                };cssprop = {};
            } else if (rowData.type == "comment") {
                dataRow = {
                    type :"评论"
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
            userId:null,
            comment:null,
            replyCommentId:null,
            receiveUserId:null,
            ip:null,
            commentCount:null,
            goodCount:null,
            nickName:null,
            type:null,
            receiveUserName:null,
            recordId:null
        }
    },
    methods: {
        query: function () {
            vm.reload();
        },
        reload: function () {           //根据userid和创建时间查询
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam','page');
            var likeName = $(".form-control").val();
            var nickNameCode = $("#nickName").val();
            var nickName = encodeURI(nickNameCode);
            var userId = $("#userId").val();
            var starDate = $("#starDate").val();
            var endDate = $("#endDate").val();
            var receiveUserId = $("#receiveUserId").val();
            var type = $("#type").val();
            var receiveUserNameCode = $("#receiveUserName").val();
            var receiveUserName = encodeURI(receiveUserNameCode);
            $("#jqGrid").jqGrid('setGridParam',{
                postData:{'likeName': likeName,'starDate':starDate,'endDate':endDate,'type':type,
                    'userId':userId,'nickName':nickName,'receiveUserId':receiveUserId,'receiveUserName':receiveUserName},
                page:page
            }).trigger("reloadGrid");
        }
    }
});