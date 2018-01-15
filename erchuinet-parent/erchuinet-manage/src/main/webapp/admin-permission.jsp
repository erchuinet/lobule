<!--#include file="common/header.html"-->
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:include page="common/header.jsp" />
<body>
    <div class="container-fluid larry-wrapper">
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-12">
                <!--头部搜索-->
                <section class="panel panel-padding">
                    <form class="layui-form" data-params='{"dataName":"permission-list","key":"id","action":"list"}' action="./data/permission.json">
                        <div class="layui-form">
                            <div class="layui-inline">
                                <div class="layui-input-inline">
                                    <input class="layui-input" name="keyword" placeholder="关键字">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <button lay-submit class="layui-btn" lay-filter="search">查找</button>
                            </div>
                        </div>
                    </form>
                </section>

                <!--列表-->
                <section class="panel panel-padding">
                    <div class="group-button">
                        <button class="layui-btn layui-btn-small layui-btn-danger ajax-all" data-params='{"url": "./data/del.json","dataName":"permission-list","key":"id","action":"del"}'>
                            <i class="iconfont">&#xe626;</i> 删除
                        </button>

                        <button class="layui-btn layui-btn-small layui-btn-normal ajax-all"  data-params='{"url": "./data/success.json","data":"status=1","dataName":"permission-list","key":"id"}'>
                            <i class="layui-icon">&#x1005;</i> 启用
                        </button>
                        <button class="layui-btn layui-btn-small modal" data-params='{"content":".add-subcat","area":"600px,430px","title":"添加分类","action":"add","data":"level=0"}'>
                            <i class="iconfont">&#xe649;</i> 添加
                        </button>
                    </div>
                    <div class="layui-form">
                        <table id="example" class="layui-table jq-even" data-params='{"dataName":"permission-list","key":"id"}'>
                            <thead>
                                <tr>
                                    <th width="30"><input type="checkbox" id="checkall" data-name="id" lay-filter="check" lay-skin="primary"></th>
                                    <th width="70"><span class="order" data-params='{"field":"id","sort":"asc"}'> 序号</span></th>
                                    <th>权限名称</th>
                                    <th>对应的ACTION</th>
                                    <th>附加条件</th>
                                    <th width="80">状态</th>
                                    <th width="210">操作</th>
                                </tr>
                            </thead>
                            <tbody id="list">
                            </tbody>
                        </table>
                    </div>

                    <div class="text-right" id="page"></div>
                </section>
            </div>
        </div>
    </div>
    <div class="add-subcat">
        <form id="form1" class="layui-form"   data-params='{"dataName":"permission-list","key":"id","bind":true,"action":"add","pid":"pid"}' action="./data/add.json" method="GET">
            <div class="layui-form-item">
                <label class="layui-form-label">父级分类</label>
                <div class="layui-input-inline">
                    <select name="pid" id="pid-select" jq-verify="required" jq-error="请输入分类" lay-filter="pid-select">
                        <option value="0">顶级分类</option>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">权限名称</label>
                <div class="layui-input-block">
                    <input type="text" name="title" required jq-verify="required" jq-error="请输入权限名称" placeholder="请输入权限名称" autocomplete="off"
                        class="layui-input ">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">规则</label>
                <div class="layui-input-inline">
                    <input type="text" name="rule" placeholder="规则" autocomplete="off" class="layui-input ">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">附加条件</label>
                <div class="layui-input-inline">
                    <input type="text" name="condition" placeholder="附加条件" autocomplete="off" class="layui-input ">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">状态</label>
                <div class="layui-input-inline">
                    <input type="radio" name="status" title="启用" value="1" checked />
                    <input type="radio" name="status" title="禁用" value="0" />
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" jq-submit jq-filter="submit">立即提交</button>
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </div>
             <input type="hidden" name="level" value="0" />
        </form>
    </div>
</body>
<script id="list-tpl" type="text/html" data-params='{"url":"./data/permission.json","dataName":"permission-list","select":"#select-tpl","selectid":"#pid-select"}'>
    {{# layui.each(d.list, function(index, item){ }}
    <tr>
        <td><input type="checkbox" name="id" value="{{ item.id}}" lay-skin="primary"></td>
        <td>{{ item.id}}</td>
        <td>{{ strRepeat('&nbsp;&nbsp;',item.level)}} {{# if(item.level>0){ }} ├&nbsp; {{# } }} <span class="edit" data-params='{"url":"./data/success.json","field":"title","data":"id={{ item.id}}"}'>{{ item.title}}</span></td>
        <td>{{item.rule}}</td>
        <td>{{item.condition}}</td>
        <td><input type="checkbox" name="status" lay-skin="switch" lay-text="启用|禁用" value="1" {{#if (item.status){ }}checked="checked"
                {{# } }} lay-filter="ajax" data-params='{"url":"./data/success.json","data":"id={{ item.id}}"}'> </td>
        <td>
            <div class="layui-btn-group">
                <button class="layui-btn layui-btn-mini layui-btn-normal modal" data-params='{"content":".add-subcat","area":"600px,430px","title":"添加{{ item.title}}的子分类","data":"pid={{ item.id }}&level={{ item.level+1 }}","action":"add","url":"./data/add.json"}'>
                        <i class="iconfont">&#xe649;</i>添加子项
                    </button>
                <button class="layui-btn layui-btn-mini modal" data-params='{"url": "./data/success.json","content": ".add-subcat","area":"600px,1000px","title":"编辑{{ item.title}}分类","type":"1","data":"id={{ item.id }}","form":"#form1","bind":"true","fillAll":true,"action":"edit"}'>
                        <i class="iconfont">&#xe653;</i>编辑
                    </button>
                <button class="layui-btn layui-btn-mini layui-btn-danger ajax" data-params='{"url": "./data/del.json","data":"id={{ item.id}}","action":"del"}'>
                        <i class="iconfont">&#xe626;</i>删除
                    </button>
            </div>
        </td>
    </tr>
    {{# }); }}
</script>
<script id="select-tpl" type="text/html" data-params='{"dataName":"permission-list"}'>
    <option value="0" data-level="0">顶级分类</option>
    {{# layui.each(d.list, function(index, item){ }}
    <option value="{{ item.id}}" data-level="{{ item.level}}"> {{ strRepeat('&nbsp;&nbsp;',item.level)}} {{# if(item.level>0){ }} ├&nbsp; {{# } }} {{ item.title}}</option>
    {{# }); }}
</script>
<script src="./js/layui/layui.js"></script>
<script src="./js/common.js"></script>
<!--#include file="common/version.html"-->
<script>
    layui.use('list');

</script>

</html>