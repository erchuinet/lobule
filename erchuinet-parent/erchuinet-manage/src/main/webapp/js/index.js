/*
 * @Author: Paco
 * @Date:   2017-01-31
 * @lastModify 2017-03-19
 * +----------------------------------------------------------------------
 * | jqadmin [ jq酷打造的一款懒人后台模板 ]
 * | Copyright (c) 2017 http://jqadmin.jqcool.net All rights reserved.
 * | Licensed ( http://jqadmin.jqcool.net/licenses/ )
 * | Author: Paco <admin@jqcool.net>
 * +----------------------------------------------------------------------
 */

layui.define(['jquery', 'jqmenu', 'layer'], function (exports) {
    var $ = layui.jquery,
        menu = layui.jqmenu,
        layer = layui.layer,
        mainMenu = new menu();
    jqIndex = function () { };
    top.global.menu = mainMenu;

    /**
     *@todo 初始化方法
     */
    jqIndex.prototype.init = function () {

        mainMenu.init('#menu-tpl',{icon:true,fresh:false});
        this.showMenu();
        this.refresh();

        $('.my-tips').click(function () {
            var l = layer.open({
                type: 1,
                title: "",
                shade: false,
                shadeClose: false,
                area: ['346px', 'auto'],
                content: $('.my-tip')
            });
        })
    }

    /**
     *@todo 绑定刷新按钮单击事件
     */
    jqIndex.prototype.refresh = function () {
        $('.fresh-btn').bind("click", function () {
            $('.jqadmin-body .layui-show').children('iframe')[0].contentWindow.location.reload(true);
        })
    }

    /**
     *@todo 绑定左侧菜单显示隐藏按钮单击事件
     */
    jqIndex.prototype.showMenu = function () {
        $('.menu-type').bind("click", function () {
            var locationShowType = layui.data('showType');
            var showType = locationShowType.moveType ? locationShowType.moveType==1?2:1 : 2;
            layui.data('showType', {
                key: 'moveType',
                value: showType
            });
            mainMenu.menuShowType();
        })
    }

    var index = new jqIndex();
    index.init();
    exports('index', {});
});