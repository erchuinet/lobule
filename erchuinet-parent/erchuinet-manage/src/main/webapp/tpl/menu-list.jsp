<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script id="menu-list-tpl" type="text/html" charset="UTF-8">

    {{# layui.each(d.list, function(index, item){ }}
    <li>
        <a href="javascript:;" data-url="{{item.href}}" data-title="{{item.title}}">
            <i class="iconfont " data-icon='{{item.icon}}'>{{item.icon}}</i>
            <span>{{item.title}}</span>
        </a>
    </li>
    {{# }); }}

</script>