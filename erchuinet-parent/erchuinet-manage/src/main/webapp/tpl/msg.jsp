<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script id="list-tpl" charset="UTF-8" type="text/html" data-params='{"url":"./data/data.json","dataName":"msg","key":"id","pageid":"#page"}'>


    {{# layui.each(d.list, function(index, item){ }}
    <tr>
        <td><input type="checkbox" name="id" value="{{ item.id}}" lay-skin="primary"></td>
        <td>{{ item.id}}</td>

        <td><i class="iconfont color-yellow-green">&#xe63c;</i> {{ item.title}} </td>
        <td> 张三</td>
        <td>
            <button class="layui-btn layui-btn-mini layui-btn-danger ajax" data-params='{"url": "./data/del.json","data":"id={{item.id}}","action":"del"}'>
                        <i class="iconfont">&#xe626;</i>删除
                    </button></td>
        </td>
    </tr>
    {{# }); }}
</script>