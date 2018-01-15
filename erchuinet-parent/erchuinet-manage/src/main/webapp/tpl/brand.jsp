<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script id="list-tpl"  charset="UTF-8" type="text/html" data-params='{"url":"./data/data.json","pageid":"#page","dataName":"brand"}'>

    {{# layui.each(d.list, function(index, item){ }}
    <tr>
        <td><input type="checkbox" name="id" value="{{ item.id}}" lay-skin="primary"></td>
        <td>{{ item.id}}</td>
        <td>
            <div class="img"> <img src="images/upload.jpg" alt="..." class="img-thumbnail"> </div>

        </td>
        <td class="edit" data-params='{"url":"./data/success.json","data":"id={{ item.id}}","field":"title"}'>{{ item.title}}</td>
        <td><input type="text" class="layui-input ajax-blur" name="order" value="{{ item.id}}" data-params='{"url":"./data/success.json","data":"id={{item.id}}"}'> </td>
        <td><input type="checkbox" name="switch" lay-skin="switch" lay-text="已审|待审" value="1" {{#if (item.switch){ }}checked="checked" {{# } }} lay-filter="ajax" data-params='{"url":"./data/success.json","data":"id={{ item.id}}"}'> </td>
        <td>
            <div class="layui-btn-group">
                <button class="layui-btn layui-btn-mini modal" data-params='{"content": ".add-subcat", "title": "编辑{{item.title}}","type":"1","action":"edit","bind":true,"data":"id={{item.id}}"}'>
                        <i class="iconfont">&#xe653;</i>编辑
                    </button>
                <button class="layui-btn layui-btn-mini layui-btn-danger ajax" data-params='{"url": "./data/del.json","data":"id={{ item.id}}","action":"del"}'>
                        <i class="iconfont">&#xe626;</i>删除
            </div>        </button>
        </td>
    </tr>
    {{# }); }}
</script>