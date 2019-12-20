/* 
 * catalog.js
 * 视频分类相关js
 * @author benhaixu
 */
var catalog = {
    'id': '',
    'parentFlag': '',
    //创建分类dom操作
    'createJSTrees1': function (fristData) {
        var addFlag = false;
        var addNode_parentId;
        var catalogsCount;
        var cat_dom = "#catalog_container";

        $(cat_dom).jstree({
            'core': {
                'strings': {
                    'Newnode': '新建分类'
                },
                'data': {
                    "url": "../migu/Migu_queryCategories.do",
                    "dataType": "json",
                    dataFilter: function (data, type) {
                        //console.log(data);
                        var json = JSON.stringify(data);
                        catalogsJson = data;
                        json = json.replace(/&nbsp;/g, '');
                        /*catalogsCount = json;*/
                        return eval(json);
                    }
                   
                },
                'check_callback': function (op, node, par, pos, more) {
                    if (op === "delete_node") {
                        if (node.parent == "#") {

                            alert("根目录无法删除");
                            return false;

                        } else {
                            if (!isNaN(node.id)) {
                                return catalog.openDelPopup(node.id, node.text, node.parent);
                            }
                        }
                    } else if (op === "create_node") {
                        if (node.text == "") {
                            node.text = "新建分类";
                        }
                    }
                },
                'themes': {
                    'responsive': false
                },
            },
            "numbering": fristData,
            types: {
                "root": {
                    select_node: false
                }
            },
            "editbutton": fristData,
            types: {
                "root": {
                    select_node: false
                }
            },
            "removebutton": fristData,
            types: {
                "root": {
                    select_node: false
                }
            },
            "addbutton": fristData,
            types: {
                "root": {
                    select_node: false
                }
            },
            "plugins": ['wholerow', 'themes', 'state', "search", "numbering", "editbutton", "removebutton", "addbutton"]
        }).unbind("delete_node.jstree").bind('delete_node.jstree',
            function (e, data) {
            }).unbind("create_node.jstree").bind('create_node.jstree',
            function (e, data) {
                addFlag = true;
                addNode_parentId = data.parent;
            }).unbind("rename_node.jstree").bind('rename_node.jstree',
            function (e, data) {
                if (addFlag) {
                    addFlag = false;
                    var node_id;
                    catalog.add(addNode_parentId, data.text, function (ret) {
                        var json = JSON.parse(ret);
                        console.log(json.catalog_id);
                        if (json.catalog_id) {
                            data.instance.set_id(data.node.id, json.catalog_id);
                        } else {
                            data.instance.delete_node (data.node);
                            if (json.code == '0') {
                                alert("当前目录下，类名重复");
                            } else {
                                alert("添加失败，请重试");
                            }
                        }
                    });
                } else {
                    catalog.rename_node(data.node.id, data.text, data.parent);

                }
            }
        ).unbind("move_node.jstree").bind('move_node.jstree',
            function (e, data) {
                this.move_node(data.parent, data.node.id, data.old_parent);

            }
        ).unbind("copy_node.jstree").bind('copy_node.jstree',
            function (e, data) {
                //console.log("copy_node.jstree");
                //console.log(data);

            }
        ).bind('click.jstree',
            function (e, data) {
                if (($(e.target).hasClass('jstree-anchor') || $(e.target).hasClass('jstree-numbering') ) && (cat_dom == '#popupLayer_myEditPopup #catalog_container' || cat_dom == '#catalog_container') && $(e.target).parent().attr('id') != '9998') {
                    //console.log("a.jstree-anchor.jstree-clicked");
                    var tar_obj = $(e.target).hasClass('jstree-numbering') ? $(e.target).parent() : $(e.target);
                    var child_cat = tar_obj.text();
                    if (child_cat.indexOf('(') >= 0)
                        child_cat = child_cat.substring(0, child_cat.indexOf('('));
                    //console.log("child_cat = " + child_cat);
                    var par_cat = tar_obj.parent().parent().siblings('a').text();
                    if (par_cat.indexOf('(') >= 0)
                        par_cat = par_cat.substring(0, par_cat.indexOf('('));
                    //console.log("par_cat = " + par_cat);
                    cur_catalog_obj.attr('value', tar_obj.parent().attr('id'));
                    if (tar_obj.parent().parent().parent().attr('id') != '0') {
                        cur_catalog_obj.html(par_cat + "/" + child_cat);
                    } else {
                        cur_catalog_obj.html(child_cat);
                    }
                }
            }
        );

    },
    'getCatalogsJson': function () {
        $.ajax({
            timeout:5000,
            async: true,
            type: "GET",
            url: "/dianbo/categories/",
            dataType: "json",

            success: function (ret) {
                console.log(ret);
                var json = JSON.stringify(ret);
                catalogsJson = json;
            },
            error: function (xhr, ajaxOptions, thrownError) {
                console.log(xhr.status);
                console.log(thrownError);
            },
            complete: function (XMLHttpRequest,status) {
                if(status=='timeout'){
                    //超时,status还有success,error等值的情况
                    alert("请求分类信息超时，请检查网络！");
                }
            }
        });
    },
    'create_first_cat': function () {
        var ref = $.jstree.reference(cat_dom);
        var sel = ref.create_node("0", {"type": "file"});
        if (sel) {
            ref.edit(sel);
        }
    },
    'add': function (parent_id, name, callback) {
        this.nodeOpp("create_node", parent_id, name, callback);
    },
    'nodeOpp': function (cmd, parent_id, name, callback) {
        //console.log("cmd -->" + cmd);
        var x = catalog.getXmlhttp(function (ret) {
            //console.log(ret);
            if (callback) {
                callback(ret);
                catalog.loadTree();
            } else {
                catalog.loadTree();
            }
        });
        if (cmd == "create_node") {
            name = name.replace(/ /g, "%20");
            var data = {
                "parent_id": parent_id,
                "name": name,
            };
            // console.log(data);
            var string = JSON.stringify(data);
            x.open('POST', '/add_catalog.ajax', true);
        } else if (cmd == "remove_node") {
            var data = {
                "catalog_id": parent_id
            };
            //console.log(data);
            var string = JSON.stringify(data);
            x.open('POST', '/remove_catalog.ajax', true);
        } else if (cmd == "rename_node") {
            name = name.replace(/ /g, "%20");
            var data = {
                "catalog_id": parent_id,
                "name": name,
            };
            // console.log(data);
            var string = JSON.stringify(data);
            x.open('POST', '/rename_catalog.ajax', true);

        } else if (cmd == "move_node") {
            //parent_id catalog_id
            var data = {
                "parent_id": parent_id,
                "catalog_id": name,
            };
            var string = JSON.stringify(data);
            x.open('POST', '/move_catalog.ajax', true);
        }
        x.setRequestHeader('Content-type', 'application/json; charset=utf-8');
        x.send(string);
        return false;
    },
    'loadTree': function () {
        $.ajax({
            timeout:5000,
            async: true,
            type: "GET",
            url: "/catalogs.ajax",
            dataType: "json",

            success: function (ret) {
                var json = JSON.stringify(ret);
                json = json.replace(/&nbsp;/g, '');
                catalog.reloadJSTrees(json);
                return json;
            },

            error: function (xhr, ajaxOptions, thrownError) {
                console.log(xhr.status);
                console.log(thrownError);
            },
            complete: function (XMLHttpRequest,status) {
                if(status=='timeout'){
                    //超时,status还有success,error等值的情况
                    alert("请求分类信息超时，请检查网络！");
                }
            }
        });
    },
    'reloadJSTrees': function (jsonData) {
        jsonData = eval(jsonData);
        var tree = $.jstree.reference(cat_dom);
        tree.refresh();
    },
    'getXmlhttp': function (callback) {
        var xmlhttp;
        if (window.XMLHttpRequest) { // code for IE7+, Firefox, Chrome, Opera, Safari
            xmlhttp = new XMLHttpRequest();
        } else { // code for IE6, IE5
            xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }

        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                callback(xmlhttp.responseText);
                //alert(xmlhttp.responseText);
            }
        }
        return xmlhttp;
    },
    //重命名目录名称
    'rename_node': function (catalog_id, newName, parentFlag) {
        if (catalog.parentFlag == "#") {
            catalog.loadTree();
            alert("根目录无法修改名字");
            return false;
        } else {
            catalog.nodeOpp("rename_node", catalog_id, newName);
            return true;
        }
    },
    //移动目录位置
    'move_node': function () {
        if (catalog.parentFlag == "#") {
            catalog.loadTree();
            alert("根目录无法移动");
            return false;
        } else {
            catalog.nodeOpp("move_node", parent_id, catalog_id);
            return true;
        }
    },
    //删除目录
    'delete_catalog': function () {
        var id = $("#popupLayer_myCatDelPopup #catalog_id").html(), parentFlag = $("#popupLayer_myCatDelPopup #catalog_parentFlag").html();
        catalog.remove_node(id, parentFlag, function (ret) {
            $.closePopupLayer('myCatDelPopup');
        });
    },
    'remove_node': function (catalog_id, parentFlag, callback) {
        //console.log("remove_node--> catalog_id " + catalog_id + " parentFlag " + parentFlag);
        if (catalog_id == "" || isNaN(catalog_id)) {
            $.closePopupLayer('myCatDelPopup');
            return;
        }
        if (parentFlag == "" || isNaN(parentFlag)) {
            $.closePopupLayer('myCatDelPopup');
            alert("根目录无法删除");
            catalog.loadTree();
            return false;
        } else {
            catalog.nodeOpp("remove_node", catalog_id, '', callback);
            return true;
        }
    },
    //显示带标签的数量
    'jstree-numbering': function ($, undefined) {

        "use strict";
        var span = document.createElement('SPAN');
        span.className = "jstree-numbering";

        $.jstree.defaults.numbering = {};
        $.jstree.plugins.numbering = function (options, parent) {
            this.teardown = function () {
                if (this.settings.questionmark) {
                    this.element.find(".jstree-numbering").remove();
                }
                parent.teardown.call(this);
            };
            this.get_number = function (obj) {
                obj = this.get_node(obj);
                var number = obj.data;
                return "(" + number + ")";
            };
            this.redraw_node = function (obj, deep, callback, force_draw) {
                var i, j, tmp = null, elm = null, org = this.get_number(obj), node_id;
                obj = parent.redraw_node.call(this, obj, deep, callback, force_draw);
                if (obj) {
                    for (i = 0, j = obj.childNodes.length; i < j; i++) {
                        if (obj.childNodes[i] && obj.childNodes[i].className && obj.childNodes[i].className.indexOf("jstree-anchor") !== -1) {
                            tmp = obj.childNodes[i];
                            node_id = obj.id;
                            break;
                        }
                    }
                    if (tmp && node_id != 9998) {
                        elm = span.cloneNode(true);
                        elm.innerHTML = org;
                        $(elm).insertAfter($(tmp.childNodes[tmp.childNodes.length - 1]));
                    }
                }
                return obj;
            };
        };
    }(jQuery),
    //可编辑目录
    'jstree-edit': function ($, undefined) {
        "use strict";
        var el = document.createElement('SPAN');
        el.className = "jstree-edit";

        $.jstree.defaults.editbutton = {};
        $.jstree.plugins.editbutton = function (options, parent) {
            this.bind = function () {
                parent.bind.call(this);
                this.element
                    .on("click.jstree", ".jstree-edit", $.proxy(function (e) {
                        this.edit(this.get_node($(e.currentTarget).parent()));
                    }, this));
            };
            this.teardown = function () {
                if (this.settings.questionmark) {
                    this.element.find(".jstree-edit").remove();
                }
                parent.teardown.call(this);
            };
            this.redraw_node = function (obj, deep, callback, force_draw) {
                var i, j, tmp = null, elm = null, node_id;
                obj = parent.redraw_node.call(this, obj, deep, callback, force_draw);
                if (obj) {
                    for (i = 0, j = obj.childNodes.length; i < j; i++) {
                        if (obj.childNodes[i] && obj.childNodes[i].className && obj.childNodes[i].className.indexOf("jstree-anchor") !== -1) {
                            tmp = obj.childNodes[i];
                            node_id = obj.id;
                            break;
                        }
                    }
                    if (tmp && node_id != 9998) {
                        elm = el.cloneNode(true);
                        $(elm).insertBefore($(tmp).parent().children()[1]);
                    }
                }
                return obj;
            };
        };
    }(jQuery),
    //删除此目录
    'jstree-remove': function ($, undefined) {
        "use strict";
        var el1 = document.createElement('SPAN');
        el1.className = "jstree-remove";

        $.jstree.defaults.removebutton = {};
        $.jstree.plugins.removebutton = function (options, parent) {
            this.bind = function () {
                parent.bind.call(this);
                this.element
                    .on("click.jstree", ".jstree-remove", $.proxy(function (e) {
                        this.delete_node(this.get_node($(e.currentTarget).parent()));
                    }, this));
            };
            this.teardown = function () {
                if (this.settings.questionmark) {
                    this.element.find(".jstree-remove").remove();
                }
                parent.teardown.call(this);
            };
            this.redraw_node = function (obj, deep, callback, force_draw) {
                var i, j, tmp = null, elm = null, node_id;
                obj = parent.redraw_node.call(this, obj, deep, callback, force_draw);
                if (obj) {
                    for (i = 0, j = obj.childNodes.length; i < j; i++) {
                        if (obj.childNodes[i] && obj.childNodes[i].className && obj.childNodes[i].className.indexOf("jstree-anchor") !== -1) {
                            tmp = obj.childNodes[i];
                            node_id = obj.id;
                            break;
                        }
                    }
                    if (tmp && node_id != 9998) {
                        elm = el1.cloneNode(true);
                        $(elm).insertBefore($(tmp).parent().children()[1]);
                    }
                }
                return obj;
            };
        };
    }(jQuery),
    'openDelPopup': function (id, name, parentFlag) {
        $.openPopupLayer({
            name: "myCatDelPopup",
            width: 437,
            target: "myCatDelDiv"
        });
        $("#popupLayer_myCatDelPopup #catalog_id").html(id);
        $("#popupLayer_myCatDelPopup #catalog_name").html(name);
        $("#popupLayer_myCatDelPopup #catalog_parentFlag").html(parentFlag);
        return false;
    },
    //添加子分类
    'jstree-add': function () {
        "use strict";
        var el1 = document.createElement('A');
        el1.className = "jstree-add";
        el1.innerHTML = "+ 添加子分类";

        $.jstree.defaults.addbutton = {};
        $.jstree.plugins.addbutton = function (options, parent) {
            this.bind = function () {
                parent.bind.call(this);
                this.element
                    .on("click.jstree", ".jstree-add", $.proxy(function (e) {
                        var par = $(e.currentTarget).closest("ul").parent();
                        var sel = this.create_node(par, {"type": "file"});
                        if (sel) {
                            this.edit(sel);
                        }
                    }, this));
            };
            this.teardown = function () {
                if (this.settings.questionmark) {
                    this.element.find(".jstree-add").remove();
                }
                parent.teardown.call(this);
            };
            this.redraw_node = function (obj, deep, callback, force_draw) {
                var i, j, tmp = null, elm = null, node_id;
                obj = parent.redraw_node.call(this, obj, deep, callback, force_draw);
                if (obj) {
                    for (i = 0, j = obj.childNodes.length; i < j; i++) {
                        if (obj.childNodes[i] && obj.childNodes[i].className && obj.childNodes[i].className.indexOf("jstree-anchor") !== -1) {
                            tmp = obj.childNodes[i];
                            node_id = obj.id;
                            break;
                        }
                    }
                    if (tmp && node_id == 9998) {
                        elm = el1.cloneNode(true);
                        $(elm).insertAfter($(tmp));
                    }
                }
                return obj;
            };
        };
    }(jQuery),
    'move_node': function (parent_id, catalog_id, parentFlag) {
        if (parentFlag == "#") {
            catalog.loadTree();
            alert("根目录无法移动");
            return false;
        } else {
            catalog.nodeOpp("move_node", parent_id, catalog_id);
            return true;
        }
    }
}