$(document).ready(function() {
    var id = getCookie("id");
    getApplyInfo();
    rechargeRecord(id);
});

function rechargeRecord(id) {
    $("#jqGrid").jqGrid({
        url: baseURL+'org/OrgBaseInfo_pageListAll.do',
        postData:{'id': id},
        datatype: "json",
        colModel: [
            { label: '记录ID', name: 'id', index: "id", width: 30, key: true },
            { label: '创建人', name: 'createOperator', width: 40},
            { label: '是否被删除', name: 'isDeleted', width: 40},
            { label: '创建时间', name: 'createTime', width: 40},
            { label: '组织名称（车友会名称）', name: 'orgName', width: 40},
            { label: '群主Id', name: 'groupUserid', width: 40},
            { label: '群主姓名', name: 'nickName', width: 40},
            { label: '群主微信', name: 'groupUserWeixinid', width: 40},
            { label: '群主是否拥有高权限', name: 'groupIfHighAuthority', width: 40},
            { label: '我平台方管理员Id', name: 'managerUserid', width: 40},
            { label: 'logo大图片地址', name: 'logoImgUrlMax', width: 40},
            { label: '关联车友会微信公众号', name: 'weixinPublicName', width: 40},
            { label: '本组织加入的认证用户多少', name: 'personCountAuth', width: 40},
            { label: '本组织游览用户多少', name: 'personCountRead', width: 40},
            { label: '是否是平台默认的车友会', name: 'isDefault', width: 40},
            { label: '本组织关联的车系', name: 'carTypeId', width: 40},
            { label: '副牌最小数', name: 'viceNumberMin', width: 40},
            { label: '副牌最大数', name: 'viceNumberMax', width: 40},
            { label: '组织类型', name: 'orgType', sortable: false, width: 40 },
            { label: '群主手机号', name: 'groupUserPhonenum', width: 50 },
            { label: '状态', name: 'state', width: 30},

            { label: '所在省', name: 'province', width: 30,hidden:true},
            { label: '新进入者的欢迎语', name: 'welcomeWord', width: 30,hidden:true},
            { label: '组织类型（车友会/其他）', name: 'orgType', width: 30,hidden:true},
            { label: '群规', name: 'orgRule', width: 30,hidden:true},
            { label: '所在城市', name: 'city', width: 30,hidden:true},
            { label: '关联的车友会自己微信公众号的id', name: 'weixinPublicId', width: 30,hidden:true},
            { label: '关联的微信公众号主页的http地址', name: 'weixinPublicHttpUrl', width: 30,hidden:true},
            { label: '关联的微信公众号的logo图片地址', name: 'weixinPublicLogo', width: 30,hidden:true},
            { label: '顶级车型', name: 'typeName', width: 30,hidden:true},
            { label: '描述', name: 'orgDesc', width: 30,hidden:true}
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
            testJqfrid();       /*是否被删除*/
            testJqfrid2();      /*群主是否拥有高权限*/
            testJqfrid3();      /*是否是平台默认的车友会*/
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


            if(rowData.groupIfHighAuthority == "n"){
                dataRow = {
                    groupIfHighAuthority :"否"
                };
                cssprop = {};
            }else{
                dataRow = {
                    groupIfHighAuthority :"是"

                };
                cssprop = {};
            }
            $("#jqGrid").jqGrid('setRowData', rowIds[i], dataRow, cssprop);
        }
    }

}

function testJqfrid3(){
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

            if(rowData.isDefault == "n"){
                dataRow = {
                    isDefault :"否"
                };
                cssprop = {};
            }else{
                dataRow = {
                    isDefault :"是"

                };
                cssprop = {};
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
            orgName:null,
            orgDesc:null,
            orgType:null,
            groupUserPhonenum:null,
            city:null,
            createOperator:null,
            isDeleted:null,
            createTime:null,
            groupUserid:null,
            nickName:null,
            groupUserWeixinid:null,
            groupIfHighAuthority:null,
            managerUserid:null,
            logoImgUrlMax:null,
            weixinPublicName:null,
            personCountAuth:null,
            personCountRead:null,
            isDefault:null,
            carTypeId:null,
            viceNumberMin:null,
            viceNumberMax:null,
            welcomeWord:null,
            orgRule:null,
            province:null,
            weixinPublicId:null,
            weixinPublicLogo:null,
            weixinPublicHttpUrl:null,
            state:null,
            typeName:null

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

function getApplyInfo() {
    $.ajax({
        type: "POST",
        url: baseURL + "org/OrgBaseInfo_selectInfo.do",
        success: function(r) {
            var text = "<option  value ='' >请选择车型</option>";
            if (r.success && r.data != null) {
                var customerInfo = r.data;
                $.each(customerInfo, function (i, info) {
                    text += "<option value='" + info.id + "'>" + info.typeName + "</option>";
                })
                $("#carTypeId").html(text);
            } else {
                $("#carTypeId").html(text);
            }
        }
    });
}