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
                    <form class="layui-form" data-params='{"dataName":"articleCat","action":"list"}' action="./data/cat.json">
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
                        <button class="layui-btn layui-btn-small layui-btn-danger ajax-all" data-params='{"url": "./data/del.json","dataName":"articleCat","key":"id","action":"del"}'>
                            <i class="iconfont">&#xe626;</i> 删除
                        </button>

                        <button class="layui-btn layui-btn-small layui-btn-normal ajax-all" data-params='{"url": "./data/success.json","data":"switch=1","dataName":"articleCat","key":"id"}'>
                            <i class="layui-icon">&#x1005;</i> 状态
                        </button>
                        <button class="layui-btn layui-btn-small modal" data-params='{"content":".add-subcat","area":"600px,430px","title":"添加分类","action":"add"}'>
                            <i class="iconfont">&#xe649;</i> 添加
                        </button>
                    </div>
                    <div class="layui-form">
                        <table id="example" class="layui-table jq-even" data-params='{"dataName":"articleCat","key":"id"}'>
                            <thead>
                                <tr>
                                    <th width="30"><input type="checkbox" id="checkall" data-name="id" lay-filter="check" lay-skin="primary"></th>
                                    <th width="70"><span class="order" data-params='{"field":"id","sort":"asc"}'> 序号</span></th>
                                    <th><span class="order" data-params='{"field":"title","sort":"asc"}'>名称</span></th>
                                    <th width="70" class="txt-center">排序</th>
                                    <th width="80" class="txt-center">状态</th>
                                    <th width="220" class="txt-center">操作</th>
                                </tr>
                            </thead>
                            <tbody id="list"></tbody>
                        </table>
                    </div>
                </section>
            </div>
        </div>
    </div>
    <div class="add-subcat">
        <form id="form1" class="layui-form" data-params='{"dataName":"articleCat","key":"id","bind":true,"action":"add","pid":"pid"}' action="./data/add.json" method="GET">
            <div class="layui-form-item">
                <label class="layui-form-label">父级分类</label>
                <div class="layui-input-inline">
                    <select name="pid" id="pid-select" jq-verify="required" jq-error="请输入分类" lay-filter="pid-select">
                        <option value="0">顶级分类</option>
                    </select>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">分类名称</label>
                <div class="layui-input-block">
                    <input type="text" name="title" required jq-verify="required" jq-error="请输入分类名称" placeholder="请输入分类名称" autocomplete="off" class="layui-input ">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">排序</label>
                <div class="layui-input-inline">
                    <input type="text" name="order" required jq-verify="number" value="100" jq-error="排序必须为数字" placeholder="分类排序" autocomplete="off" class="layui-input ">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">状态</label>
                <div class="layui-input-inline">
                    <input type="radio" name="switch" title="启用" value="1" checked />
                    <input type="radio" name="switch" title="禁用" value="0" />
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
<!--#include file="tpl/cat.html"-->
<jsp:include page="tpl/cat.jsp" />
<script src="js/common.js"></script>
<!--#include file="common/version.html"-->
<jsp:include page="common/version.jsp" />
<script>
    layui.use('list');
</script>

</html>