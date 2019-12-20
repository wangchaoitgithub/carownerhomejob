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
            { label: '创建人', name: 'createOperator', width: 50},
            { label: '创建时间', name: 'createTime',formatter:function(cellvalue, options, row)
                {return new Date(cellvalue).Format('yyyy-MM-dd hh:mm:ss')}, width: 50 },
            { label: '记录最后修改人', name: 'lastOperator' , sortable: false, width: 60 },
            { label: '最后修改时间', name: 'lastModifyTime',formatter:function(cellvalue, options, row)
                {return new Date(cellvalue).Format('yyyy-MM-dd hh:mm:ss')},  width: 50 },

            { label: '创建者id', name: 'userId', width: 50 },
            { label: '回复的哪个人的id', name: 'receiveUserId', sortable: false, width: 20 },
            { label: '评论内容', name: 'comment', width: 50 },
            { label: '回复之前评论的id', name: 'replyCommentId', width: 50 },
            { label: 'ip地址', name: 'ip', width: 60},
            { label: '评论数量', name: 'commentCount', width: 60},
            { label: '点赞数量', name: 'goodCount', width: 60}
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
            userId:null,
            comment:null,
            replyCommentId:null,
            ip:null,
            commentCount:null,
            goodCount:null
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
            var userId = $("#userId").val();
            var starDate = $("#starDate").val();
            var endDate = $("#endDate").val();
            $("#jqGrid").jqGrid('setGridParam',{
                postData:{'likeName': likeName,'starDate':starDate,'endDate':endDate,'userId':userId},
                page:page
            }).trigger("reloadGrid");
        }
    }
});