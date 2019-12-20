$(document).ready(function() {
    var id = getCookie("id");
    rechargeRecord(id);
});

function rechargeRecord(id) {
    $("#jqGrid").jqGrid({
        url: baseURL+'org/OrgBaseInfo_pageListAll.do',
        postData:{'id': id},
        datatype: "json",
        colModel: [
            { label: '记录ID', name: 'id', index: "id", width: 30, key: true },
            { label: '组织名称（车友会名称）', name: 'orgName', width: 40
            },
            { label: '组织介绍', name: 'orgDesc', width: 90 },
            { label: '组织类型', name: 'orgType', sortable: false, width: 40 },
            { label: '群主手机号', name: 'groupUserPhonenum', width: 50 },
            { label: '所在城市', name: 'city', width: 30 },

            { label: '状态', name: 'state', width: 30}
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
            orgName:null,
            orgDesc:null,
            orgType:null,
            groupUserPhonenum:null,
            city:null,
            state:null
        }
    },
    methods: {
        query: function () {
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
            vm.customer = rowData;
        },
        saveOrUpdate: function () {
            var msg = vm.check();
            if(!isBlank(msg)){
                $.jGrowl(msg);
                return;
            }
            var url = "org/OrgBaseInfo_update.do";
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
        reload: function () {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam','page');
            var likeName = $("#likeName").val();
            var operatorsId = $("#nameSimple option:selected").val();
            $("#jqGrid").jqGrid('setGridParam',{
                postData:{'likeName': likeName, 'operatorsId':operatorsId},
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