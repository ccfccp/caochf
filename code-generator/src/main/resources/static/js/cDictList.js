$(function () {
    $("#jqGrid").jqGrid({
        url: 'cdict/select',
        datatype: "json",
        colModel: [
            {label: '字典内码', name: 'dictId', width: 100, key: true},
            {label: '字典代码', name: 'dictCode', width: 70},
            {label: '字典名称', name: 'dictName', width: 100},
            {label: '有效性', name: 'isActive', width: 100},
            {label: '排序号', name: 'sortNo', width: 100}
        ],
        viewrecords: true,
        height: 385,
        rowNum: 10,
        rowList: [10, 30, 50, 100, 200],
        rownumbers: true,
        rownumWidth: 25,
        autowidth: true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader: {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames: {
            page: "page",
            rows: "limit",
            order: "order"
        },
        gridComplete: function () {
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }
    });
});

var vm = new Vue({
    el: '#rrapp',
    data: {
        q: {
            tableName: null
        }
    },
    methods: {
        query: function () {
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {'dictId': vm.q.dictId},
                page: 1
            }).trigger("reloadGrid");
        },
        generator: function () {
            var tableNames = getSelectedRows();
            if (tableNames == null) {
                return;
            }
            var moduleName = vm.q.moduleName;
            var packageName = vm.q.package;
            var author = vm.q.author;

            if (moduleName == undefined) {
                moduleName = "";
            }
            if (packageName == undefined) {
                packageName = "";
            }
            if (author == undefined) {
                author = "";
            }
            // location.href = "sys/generator/code?tables=" + tableNames.join() + "&moduleName=" + moduleName + "&packageName=" + packageName + "&author=" + author;
            location.href = "nature/cdict/select";
        }
    }
});

