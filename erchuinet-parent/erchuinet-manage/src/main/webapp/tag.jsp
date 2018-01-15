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
                    <form class="layui-form" data-params='{"dataName":"tags","action":"list"}' action="./data/data.json" method="get">
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
                        <button class="layui-btn layui-btn-small layui-btn-danger ajax-all" data-params='{"url": "./data/del.json","dataName":"tags","key":"id","action":"del"}'>
                            <i class="iconfont">&#xe626;</i> 删除
                        </button>

                        <button class="layui-btn layui-btn-small layui-btn-normal ajax-all" data-params='{"url": "./data/success.json","data":"status=0","dataName":"tags","key":"id"}'>
                            <i class="layui-icon">&#x1005;</i> 禁用
                        </button>
                        <button class="layui-btn layui-btn-small modal" data-params='{"content": ".add-subcat", "title": "添加标签","type":"1","action":"add","url":"./data/add.json"}'>
                            <i class="iconfont">&#xe649;</i> 添加
                        </button>
                    </div>
                    <div class="layui-form">
                        <table id="example jq-event" class="layui-table jq-even" data-params='{"dataName":"tags","key":"id"}'>
                            <thead>
                                <tr>
                                    <th width="30"><input type="checkbox" id="checkall" data-name="id" lay-filter="check" lay-skin="primary"></th>
                                    <th width="80"><span class="order" data-params='{"field":"id","sort":"asc"}'> 序号</span></th>
                                    <th><span class="order" data-params='{"field":"title","sort":"asc"}'>名称</span></th>
                                    <th width="70">热度</th>
                                    <th width="80">状态</th>
                                    <th width="142">操作</th>
                                </tr>
                            </thead>
                            <tbody id="list"></tbody>
                        </table>

                    </div>

                    <div class="text-right" id="page"></div>
                </section>
            </div>
        </div>
    </div>
    <div class="add-subcat">
        <form id="form1" class="layui-form" data-params='{"dataName":"tags","key":"id","action":"add"}' action="./data/add.json" method="get">
            <div class="layui-form-item">
                <label class="layui-form-label">标签名称</label>
                <div class="layui-input-block">
                    <input type="text" name="title" required jq-verify="required" jq-error="请输入标签名称" placeholder="请输入标签名称" autocomplete="off" class="layui-input ">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">热度</label>
                <div class="layui-input-inline">
                    <input type="text" name="order" required jq-verify="number" value="100" jq-error="标签热度必须为数字" placeholder="标签热度" autocomplete="off" class="layui-input ">
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
        </form>
    </div>
</body>
<!--#include file="tpl/tag.html"-->
<jsp:include page="tpl/tag.jsp" />
<script src="js/layui/layui.js"></script>
<!--#include file="common/version.html"-->
<jsp:include page="common/version.jsp" />
<script>
    layui.use('list');
</script>

</html>