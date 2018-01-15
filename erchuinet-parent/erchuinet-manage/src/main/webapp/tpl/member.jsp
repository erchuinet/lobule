<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script id="list-tpl" charset="UTF-8" type="text/html" data-params='{"url":"./data/member.json","dataName":"member","pageid":"#page"}'>
    {{# layui.each(d.list, function(index, item){ }}
    <tr>
        <td><input type="checkbox" name="id" value="{{ item.id}}" lay-skin="primary"></td>
        <td>{{ item.id}}</td>
        <td>
            <div class="img"><img src="{{ item.avatar}}" alt="..." class="img-thumbnail"></div>
        </td>
        <td class="edit" data-params='{"url":"./data/success.json","data":"id={{ item.id}}","field":"name"}'>{{ item.name}}</td>
        <td class="edit" data-params='{"url":"./data/success.json","data":"id={{ item.id}}","field":"tel"}'>{{ item.tel}}</td>
        <td class="txt-center">{{#if (item.sex == 1){ }}男 {{# }else if(item.sex==0){ }}女{{# }else{ }} 保密{{# } }}</td>
        <td><input type="checkbox" name="status" lay-skin="switch" lay-text="启用|禁用" value="1" {{#if (item.status){ }}checked="checked" {{# } }} lay-filter="ajax" data-params='{"url":"./data/success.json","data":"id={{ item.id}}"}'> </td>
        <td>
            <div class="layui-btn-group">
                <button class="layui-btn layui-btn-mini modal" data-params='{"content": ".add-subcat", "title": "编辑{{item.name}}","type":"1","data":"id={{ item.id}}","area":"700px,auto","action":"edit","bind":true}'>
                        <i class="iconfont">&#xe653;</i>编辑
                    </button>
                <button class="layui-btn layui-btn-mini layui-btn-danger ajax" data-params='{"url": "./data/success.json","data":"id={{item.id}}","action":"del"}'>
                        <i class="iconfont">&#xe626;</i>删除
                    </button>
            </div>
        </td>
    </tr>
    {{# }); }}
</script>