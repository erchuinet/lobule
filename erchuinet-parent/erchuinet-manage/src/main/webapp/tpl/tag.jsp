<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script id="list-tpl" charset="UTF-8" type="text/html" data-params='{"url":"./data/data.json","dataName":"tags","pageid":"#page"}'>
    {{# layui.each(d.list, function(index, item){ }}
    <tr>
        <td><input type="checkbox" name="id" value="{{ item.id}}" lay-skin="primary"></td>
        <td class="txt-center">{{ item.id}}</td>
        <td class="edit" data-params='{"url":"./data/success.json","field":"title","data":"id={{ item.id}}"}'>{{ item.title}}</td>
        <td><input type="text" class="layui-input ajax-blur" name="order" value="{{ item.order}}" data-params='{"url":"./data/success.json","data":"id={{ item.id }}"}'> </td>
        <td><input type="checkbox" name="status" lay-skin="switch" lay-text="启用|禁用" {{#if (item.status){ }}checked="checked" {{# } }} lay-filter="ajax" data-params='{"url":"./data/success.json","data":"id={{ item.id}}"}' value="1"> </td>
        <td>
            <div class="layui-btn-group">
                <button class="layui-btn layui-btn-mini modal" data-params='{"content": ".add-subcat","area":"600px,300px","title":"编辑{{ item.title}}","data":"id={{ item.id }}","bind":"true","type":"1","action":"edit","url":"./data/success.json"}'>
                        <i class="iconfont">&#xe653;</i>编辑
                    </button>
                <button class="layui-btn layui-btn-mini layui-btn-danger ajax" data-params='{"url": "./data/success.json","action":"del","data":"id={{ item.id}}"}'>
                        <i class="iconfont">&#xe626;</i>删除
                    </button>
            </div>
        </td>
    </tr>
    {{# }); }}
</script>