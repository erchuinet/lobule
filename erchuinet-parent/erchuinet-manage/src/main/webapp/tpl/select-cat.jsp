<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script id="select-tpl" type="text/html" charset="UTF-8">
    <option value="0">顶级分类</option>
    {{# layui.each(d.list, function(index, item){ }}
    <option value="{{ item.id}}" data-level="{{ item.level}}"> {{ strRepeat('&nbsp;&nbsp;',item.level)}} {{# if(item.level>0){ }} ├&nbsp; {{# } }} {{ item.title}}</option>
    {{# }); }}
</script>