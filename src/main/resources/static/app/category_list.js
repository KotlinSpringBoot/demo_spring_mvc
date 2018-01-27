var App = {
    initBootstrapTable: function () {
        var columns = []
        columns.push(
            {
                title: "ID",
                field: "id",
                align: 'left',
                valign: 'middle',
                formatter: function (value, row, index) {
                    return value;
                }
            },
            {
                title: "名称",
                field: "name",
                align: 'left',
                valign: 'middle',
                formatter: function (value, row, index) {
                    return value;
                }
            },
            {
                title: "简介",
                field: "detail",
                align: 'left',
                valign: 'middle',
                formatter: function (value, row, index) {
                    return value;
                }
            },
            {
                title: "编码",
                field: "code",
                align: 'left',
                valign: 'middle',
                formatter: function (value, row, index) {
                    return value;
                }
            }
        )

        $.extend($.fn.bootstrapTable.defaults, $.fn.bootstrapTable.locales['zh-CN'])
        var searchText = $('.search').find('input').val()
        $('#App').bootstrapTable({
            url: '/wiki/api/category/search',
            sidePagination: "server",
            queryParamsType: 'pageNo,pageSize,type',
            method: 'get',
            striped: true,     //是否显示行间隔色
            cache: false,      //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,  //是否显示分页（*）
            paginationPreText: ' 上一页',
            paginationNextText: '下一页',
            search: true,
            searchText: searchText,
            searchAlign: 'right',
            searchOnEnterKey: false,
            trimOnSearch: true,
            pageNumber: 1,     //初始化加载第一页，默认第一页
            pageSize: 10,      //每页的记录行数（*）
            pageList: [10, 20, 50, 100, 200], // 可选的每页数据
            totalField: 'totalElements', // 所有记录 count
            dataField: 'content', //后端 json 对应的表格List数据的 key
            columns: columns,
            smartDisplay: true,
            queryParams: function (params) {
                // 处理查询参数
                return {
                    pageSize: params.pageSize,
                    pageNo: params.pageNumber - 1,
                    sortName: params.sortName,
                    sortOrder: params.sortOrder,
                    searchText: params.searchText,
                    type:$('#type').val()
                }
            },
            responseHandler: function (response) {
                console.log(response)
                // 类似 Filter，处理响应数据；记得要返回 response
                return response
            }
        })
    },

    init: function () {
        App.initBootstrapTable()
    }
}

$(App.init())