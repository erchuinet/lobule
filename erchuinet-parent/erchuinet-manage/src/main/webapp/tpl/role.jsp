<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script id="list-tpl" charset="UTF-8" type="text/html" data-params='{"url":"./data/role.json","dataName":"role","key":"id","pageid":"#page"}'>
    {{# layui.each(d.list, function(index, item){ }}
    <tr>
        <td><input type="checkbox" name="id" value="{{ item.id}}" lay-skin="primary"></td>
        <td>{{ item.id}}</td>
        <td><span class="edit" data-params='{"url":"./data/success.json","field":"name","data":"id={{ item.id}}"}'>{{item.name}}</span></td>
        <td>{{ item.desc}}</td>
        <td><input type="checkbox" name="status" lay-skin="switch" lay-text="启用|禁用" value="!" {{#if (item.status){ }}checked="checked" {{# } }} lay-filter="ajax" data-params='{"url":"./data/success.json","data":"id={{ item.id}}"}'> </td>
        <td>
            <div class="layui-btn-group">
                <button class="layui-btn layui-btn-mini modal" data-params='{"content": "admin-role-edit.html", "title": "{{item.name}}","full":"true","data":"id={{ item.id }}","action":"edit","bind":true,"type":2}'>
                        <i class="iconfont">&#xe653;</i>编辑
                    </button>
                <button class="layui-btn layui-btn-mini layui-btn-danger ajax" data-params='{"url": "./data/del.json","action":"del","data":"id={{ item.id }}"}'>
                        <i class="iconfont">&#xe626;</i>删除
                    </button>
            </div>
        </td>
    </tr>
    {{# }); }}
</script>