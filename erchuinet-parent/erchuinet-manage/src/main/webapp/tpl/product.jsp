<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script id="list-tpl" charset="UTF-8" type="text/html" data-params='{"url":"./data/article.json","dataName":"products","pageid":"#page"}'>

    {{# layui.each(d.list, function(index, item){ }}
    <tr>
        <td><input type="checkbox" name="id" value="{{ item.id}}" lay-skin="primary"></td>
        <td>{{ item.id}}</td>
        <td>
            <div class="img"><img src="{{ item.img }}" alt="..." class="img-thumbnail"></div>
        </td>
        <td class="edit" data-params='{"url":"./data/success.json","field":"title","data":"id={{ item.id}}"}'>{{ item.title}}</td>
        <td>￥{{ item.price}}</td>
        <td><input type="text" class="layui-input ajax-blur" name="order" value="{{ item.id}}" data-params='{"url":"./data/success.json","data":"id={{ item.id}}"}'> </td>
        <td><input type="checkbox" name="switch" lay-skin="switch" value="1" lay-text="已审|待审" {{#if (item.switch){ }}checked="checked" {{# } }} lay-filter="ajax" data-params='{"url":"./data/success.json","data":"id={{ item.id}}&name=paco"}'> </td>
        <td>
            <div class="layui-btn-group">
                <button class="layui-btn layui-btn-mini modal" data-params='{"content": "product-edit.html", "title": "{{item.title}}","type":"2","data":"id={{ item.id }}","select":"#select-tpl","selectid":"#pid-select"}'>
                        <i class="iconfont">&#xe653;</i>编辑
                    </button>
                <button class="layui-btn layui-btn-mini layui-btn-danger ajax" data-params='{"url": "./data/del.json","data":"id={{ item.id }}","action":"del"}'>
                        <i class="iconfont">&#xe626;</i>删除
                    </button>
            </div>
        </td>
    </tr>
    {{# }); }}
</script>