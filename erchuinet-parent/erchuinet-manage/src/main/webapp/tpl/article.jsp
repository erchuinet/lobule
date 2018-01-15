<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script id="list-tpl" charset="UTF-8" type="text/html" data-params='{"url":"./data/article.json","pageid":"#page","dataName":"article"}'>
    {{# layui.each(d.list, function(index, item){ }}
    <tr>
        <td><input type="checkbox" name="id" value="{{ item.id}}" lay-skin="primary"></td>
        <td class="txt-center">{{ item.id}}</td>
        <td class="edit" data-params='{"url":"./data/success.json","field":"title","data":"id={{ item.id}}","dataName":"article","key":"id"}'>{{ item.title}}</td>
        <td>{{ item.from}}</td>
        <td>{{ item.author}}</td>
        <td>{{ setCat(item.cat_id,"articleCat") }}</td>
        <td><input type="text" class="layui-input sort ajax-blur" name="order" value="{{ item.order}}" data-params='{"url":"./data/success.json","data":"id={{  item.id }}","loading":"false","dataName":"article","key":"id"}'> </td>
        <td><input type="checkbox" name="command" lay-skin="switch" lay-text="是|否" {{#if (parseInt(item.command)){ }}checked="checked" {{# } }} lay-filter="ajax" data-params='{"url":"./data/success.json","data":"id={{ item.id}}","dataName":"article","key":"id" }'
                value="1">
        </td>
        <td><input type="checkbox" name="switch" lay-skin="switch" lay-text="已审|未审" {{#if (parseInt(item.switch)){ }}checked="checked" {{# } }} lay-filter="ajax" data-params='{"url":"./data/success.json","data":"id={{ item.id}}","dataName":"article","key":"id" }'
                value="1">
        </td>
        <td>
            <div class="layui-btn-group">
                <button class="layui-btn layui-btn-mini modal" data-params='{"content": "article-edit.html","type":"2", "title": "{{item.title}}","full":"true","key":"id","data":"id={{item.id}}"}'>
                        <i class="iconfont">&#xe653;</i>编辑
                    </button>
                <button class="layui-btn layui-btn-mini layui-btn-danger ajax" data-params='{"url": "./data/success.json","confirm":"true","action":"del","data":"id={{ item.id}}","dataName":"article","key":"id"}'>
                        <i class="iconfont">&#xe626;</i>删除
                    </button>
            </div>
        </td>
    </tr>
    {{# }); }}
</script>