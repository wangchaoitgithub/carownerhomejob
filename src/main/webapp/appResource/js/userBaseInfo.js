$(document).ready(function() {
    var id = getCookie("id");
    rechargeRecord(id);
});

function rechargeRecord(id) {

    $("#jqGrid").jqGrid({
        url: baseURL+'user/UserBaseInfo_pageListAll.do',
        postData:{'id':id},
        datatype: "json",
        colModel: [
            { label: '用户ID', name: 'id', index: "id", width: 30, key: true },
            { label: '账户状态', name: 'isDeleted', width: 30},
            { label: '称昵/绰号', name: 'nickName',
                /*formatter:function(cellvalue, options, row)
                {return new Date(cellvalue).Format('yyyy-MM-dd hh:mm:ss')},*/ width: 50
            },
            { label: '性别', name: 'sex', width: 20 },
            { label: '状态', name: 'state', sortable: false, width: 20 },
            { label: '群副牌号', name: 'viceNumber', width: 40 },
            { label: '所在城市', name: 'city', width: 40 },
            { label: '个性签名', name: 'signDesc', width: 80}

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
            if(rowData.isDeleted == "n"){
                dataRow = {
                    isDeleted :"有效"
                };
                cssprop = {
                };
           }else{
                dataRow = {
                    isDeleted :"已封号"

                };
                cssprop = {
                    color :"#FF0000"
                };
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
            nickName:null,
            sex:null,
            state:null,
            viceNumber:null,
            city:null,
            desc:null,
            signDesc:null,
            isDeleted:null
        }
    },
    methods: {
        query: function () { /*根据userid和nickname查询*/
            vm.reload();
        },
        update: function () {
            var Id = getSelectedRow();  //拿到选中的userId
            if(Id == null){
                return ;
            }
            vm.id = Id;
            vm.showList = false;
            vm.title = "修改";
            var rowData = $("#jqGrid").jqGrid('getRowData',Id);
            vm.customer = rowData;
        },

        /*解封或封号*/
        deleItem: function () {
            var Id = getSelectedRow();  //拿到选中的userId

            var obj = $("#jqGrid");
            var rowid = obj.jqGrid('getGridParam', 'selrow');
            var rowData=obj.jqGrid('getRowData',rowid);


            var isDeleted = rowData.isDeleted;
            if(Id == null){
                return ;
            }
            var isDele;
            if (isDeleted == "已封号") {
                isDele = "解封";
            }else if (isDeleted == "有效"){
                isDele = "封禁";
            }else{

            }
            var i = confirm("确定"+  isDele +"id为" + Id +"的用户吗？");
            if (i==true)
            {
                vm.id = Id;
                var rowData = $("#jqGrid").jqGrid('getRowData',Id);
                vm.customer = rowData;

                var msg = vm.check();
                if(!isBlank(msg)){
                    $.jGrowl(msg);
                    return;
                }
                var url = "user/UserBaseInfo_delete.do";
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
            }
            else
            {
                vm.reload();
            }

        },

        saveOrUpdate: function () {         //确认修改
            var msg = vm.check();
            if(!isBlank(msg)){
                $.jGrowl(msg);
                return;
            }
            var url = "user/UserBaseInfo_update.do";
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



    /*根据userid和nickname查询*/
        reload: function () {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam','page');      //当前页数
            var nickName = $("#nickName").val();
            var userId = $("#userId").val();
            $("#jqGrid").jqGrid('setGridParam',{
                postData:{'nickName': nickName,'uid': userId},
                page:page
            }).trigger("reloadGrid");
        },

        check: function () {
            var errorMsg = "";
            if(isBlank(vm.customer.id) || vm.customer.id =='null'){
                errorMsg = "用户—不能为空";
            }
            return errorMsg;
        },



    }
});

