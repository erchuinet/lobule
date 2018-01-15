<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script id="list-tpl" charset="UTF-8" type="text/html" data-params='{"url":"./data/cat.json","dataName":"articleCat","select":"#select-tpl","selectid":"#pid-select"}'>
    {{# layui.each(d.list, function(index, item){ }}
    <tr>
        <td><input type="checkbox" name="id" value="{{ item.id}}" lay-skin="primary"></td>
        <td class="txt-center">{{ item.id}}</td>
        <td>{{ strRepeat('&nbsp;&nbsp;',item.level)}} {{# if(item.level>0){ }} ├&nbsp; {{# } }}<span class="edit" data-params='{"url":"./data/success.json","field":"title","data":"id={{ item.id}}"}'> {{ item.title}}</span></td>
        <td><input type="text" class="layui-input sort ajax-blur" name="order" value="{{ item.order}}" data-params='{"url":"./data/success.json","data":"id={{ item.id}}"}'> </td>
        <td><input type="checkbox" name="switch" lay-skin="switch" lay-text="ON|OFF" {{#if ( parseInt(item.switch)){ }}checked="checked" {{# } }} lay-filter="ajax" value="1" data-params='{"url":"./data/success.json","data":"id={{ item.id}}"}'> </td>
        <td>
            <div class="layui-btn-group">
                <button class="layui-btn layui-btn-mini layui-btn-normal modal" data-params='{"content":".add-subcat","area":"600px,430px","title":"添加{{ item.title}}的子分类","data":"pid={{ item.id }}&level={{ item.level+1 }}","action":"add","url":"./data/add.json"}'>
                            <i class="iconfont">&#xe649;</i>添加子分类
                        </button>
                <button class="layui-btn layui-btn-mini modal" data-params='{"url": "./data/success.json","content": ".add-subcat","area":"600px,1000px","title":"编辑{{ item.title}}分类","type":"1","data":"id={{ item.id }}","form":"#form1","bind":"true","fillAll":true,"action":"edit"}'>
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

<script id="select-tpl" type="text/html" data-params='{"dataName":"articleCat"}'>
    <option value="0" data-level="0">顶级分类</option>
    {{# layui.each(d.list, function(index, item){ }}
    <option value="{{ item.id}}" data-level="{{ item.level}}"> {{ strRepeat('&nbsp;&nbsp;',item.level)}} {{# if(item.level>0){ }} ├&nbsp; {{# } }} {{ item.title}}</option>
    {{# }); }}
</script>