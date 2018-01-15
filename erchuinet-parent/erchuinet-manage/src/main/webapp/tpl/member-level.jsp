<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script id="list-tpl" charset="UTF-8" type="text/html" data-params='{"url":"./data/member-level.json","dataName":"level","pageid":"#page"}'>

    {{# layui.each(d.list, function(index, item){ }}
    <tr>
        <td><input type="checkbox" name="id" value="{{ item.id}}" lay-skin="primary"></td>
        <td>{{ item.id}}</td>
        <td>
            <img src="{{ item.icon }}" alt="..." class="img-thumbnail">
        </td>
        <td>{{ item.name }}</td>
        <td>{{item.experience}}</td>
        <td><input type="checkbox" name="status" lay-skin="switch" value="1" lay-text="启用|禁用" {{#if (item.status){ }}checked="checked" {{# } }} lay-filter="ajax" data-params='{"url":"./data/success.json","data":"id={{ item.id}}"}'> </td>
        <td>
            <div class="layui-btn-group">
                <button class="layui-btn layui-btn-mini modal" data-params='{"content": ".add-subcat", "title": "编辑{{item.title}}","type":"1","data":"id={{ item.id}}","area":"700px,auto","action":"edit","bind":true}'>
                        <i class="iconfont">&#xe653;</i>编辑
                    </button>
                <button class="layui-btn layui-btn-mini layui-btn-danger ajax" data-params='{"url": "./data/del.json","data":"id={{item.id}}","action":"del"}'>
                        <i class="iconfont">&#xe626;</i>删除
                    </button>
            </div>
        </td>
    </tr>
    {{# }); }}
</script>