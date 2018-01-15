<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script id="list-tpl" type="text/html" data-params='{"url":"{:url("index")}","pageid":"#page","dataName":"amdinData"}'>
    <table id="example" class="layui-table lay-even" data-name="amdinData" data-tplid="list-tpl">
        <thead>
            <tr>
                <th width="30"><input type="checkbox" id="checkall" data-name="checkbox" lay-filter="check" lay-skin="primary"></th>
                <th width="60">序号</th>
                <th>帐号</th>
                <th>姓名</th>
                <th>加入时间</th>
                <th width="80">状态</th>
                <th width="150">操作</th>
            </tr>
        </thead>
        <tbody>
            {{# layui.each(d.list, function(index, item){ }}
            <tr>
                <td><input type="checkbox" name="checkbox" value="{{ item.id}}" lay-skin="primary"></td>
                <td>{{ item.id}}</td>
                <td>{{ item.title}}</td>
                <td>张三</td>
                <td>2017-02-24</td>
                <td><input type="checkbox" name="switch" lay-skin="switch" lay-text="启用|禁用" {{#if (item.switch){ }}checked="checked" {{# } }} lay-filter="ajax" data-params='{"url":"{saveadmin}","data":"id={{ item.id}}"}'> </td>
                <td>
                    <button class="layui-btn layui-btn-mini modal-catch" data-params='{"content": ".add-subcat","area":"500px,400px","title":"编辑{{ item.title}}管理员","key":"id={{ item.id}}"}'>
                        <i class="iconfont">&#xe653;</i>编辑
                    </button>
                    <button class="layui-btn layui-btn-mini layui-btn-danger ajax" data-params='{"url": "{:url("deladmin")}","data":"id={{ item.id }}"}'>
                        <i class="iconfont">&#xe626;</i>删除
                    </button></td>
                </td>
            </tr>
            {{# }); }}
        </tbody>

    </table>
</script>