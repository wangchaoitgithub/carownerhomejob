$(document).ready(function() {
    var id = getCookie("id");
    rechargeRecord(id);
});

function rechargeRecord(id) {
    $("#jqGrid").jqGrid({
        url: baseURL+'secret/SecretWordRecord_pageListAll.do',
        postData:{'id': id},
        datatype: "json",
        colModel: [
            { label: '记录ID', name: 'id', index: "id", width: 30, key: true },
            { label: '创建人', name: 'createOperator',
                /*formatter:function(cellvalue, options, row)
                {return new Date(cellvalue).Format('yyyy-MM-dd hh:mm:ss')},*/ width: 50
            },
            { label: '创建时间', name: 'createTime',formatter:function(cellvalue, options, row)
                {return new Date(cellvalue).Format('yyyy-MM-dd hh:mm:ss')}, width: 50 },
            { label: '记录最后修改人', name: 'lastOperator' , sortable: false, width: 60 },
            { label: '最后修改时间', name: 'lastModifyTime',formatter:function(cellvalue, options, row)
                {return new Date(cellvalue).Format('yyyy-MM-dd hh:mm:ss')},  width: 50 },
            { label: '发布者的userid', name: 'userId', width: 50 },
            { label: '接收人的userid', name: 'receiveUserId', width: 50},
            { label: '悄悄话', name: 'secretWord', width: 50},
            { label: '状态', name: 'status', width: 50}
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
            receiveUserId:null,
            secretWord:null,
            status:null
        }
    },
    methods: {
        query: function () { /*根据secretWord查*/
            vm.reload();
        },
        update: function () {
            var userId = getSelectedRow();
            if(userId == null){
                return ;
            }
            vm.showList = false;
            vm.title = "修改";
            var rowData = $("#jqGrid").jqGrid('getRowData',userId);
            // postData:{'secretWord': vm.customer.secretWord};
            vm.customer = rowData;
        },
        saveOrUpdate: function () {
            var msg = vm.check();
            if(!isBlank(msg)){
                $.jGrowl(msg);
                return;
            }
            var url = "secret/SecretWordRecord_update.do";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                data: vm.customer,
                success: function(r){
                    if(r.success){
                        $.jGrowl("操作成功");
                        vm.reload();
                    }else{
                        $.jGrowl("操作失败");
                    }
                }
            });
        },
        reload: function () {       /*根据secretWord查*/
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam','page');
            var secretWord = $("#secretWord").val();
            var operatorsId = $("#nameSimple option:selected").val();
            var starDate = $("#starDate").val();
            var endDate = $("#endDate").val();
            $("#jqGrid").jqGrid('setGridParam',{
                postData:{'secretWord': secretWord,'starDate':starDate,'endDate':endDate},
                page:page
            }).trigger("reloadGrid");
        },
        check: function () {
            var errorMsg = "";
            if(isBlank(vm.customer.id) || vm.customer.id =='null'){
                errorMsg = "运营商—不能为空";
            }
            return errorMsg;
        }
    }
});