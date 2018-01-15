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
                    <form class="layui-form" data-params='{"dataName":"msg","action":"list"}' action="./data/data.json">
                        <div class="layui-form">

                            <div class="layui-inline">
                                <div class="layui-input-inline">
                                    <input class="layui-input start-date" name="start_date" placeholder="开始时间">
                                </div>
                                <div class="layui-input-inline">
                                    <input class="layui-input end-date" name="end_date" placeholder="结束时间">
                                </div>
                            </div>
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
                        <button class="layui-btn layui-btn-small layui-btn-danger ajax-all" data-params='{"url": "./data/del.json","dataName":"msg","key":"id","action":"del"}'>
                            <i class="iconfont">&#xe626;</i> 删除
                        </button>
                        <button class="layui-btn layui-btn-small layui-btn-normal ajax" data-name="checkbox" data-params='{"url": "/php/test.php","data":"id=1&name=hao&checkbox=6,9,0&va=23"}'>
                            <i class="iconfont">&#xe63c;</i> 收件箱
                        </button>
                        <button class="layui-btn layui-btn-small layui-btn-normal ajax" data-name="checkbox" data-params='{"url": "/php/test.php","data":"id=1&name=hao&checkbox=6,9,0&va=23"}'>
                            <i class="layui-icon">&#xe609;</i> 发件箱
                        </button>
                        <button class="layui-btn layui-btn-small layui-btn-normal ajax" data-name="checkbox" data-params='{"url": "/php/test.php","data":"id=1&name=hao&checkbox=6,9,0&va=23"}'>
                            <i class="iconfont">&#xe64c;</i> 草稿箱
                        </button>
                        <button class="layui-btn layui-btn-small modal" data-params='{"content":".add-subcat","area":"500px,480px", "title":"发信息","type":"1"}'>
                            <i class="iconfont">&#xe649;</i> 发信息
                        </button>
                    </div>
                    <div class="layui-form">

                        <table id="example" class="layui-table jq-even" data-params='{"dataName":"msg","key":"id"}'>
                            <thead>
                                <tr>
                                    <th width="30"><input type="checkbox" id="checkall" data-name="id" lay-filter="check" lay-skin="primary"></th>
                                    <th width="60">序号</th>
                                    <th>主题</th>
                                    <th width="100">发件人</th>
                                    <th width="70">操作</th>
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
        <form id="form1" class="layui-form" data-params='{"dataName":"msg","key":"id","action":"add"}' action="./data/add.json">
            <div class="layui-form-item">
                <label class="layui-form-label">标题</label>
                <div class="layui-input-block">
                    <input type="text" name="title" required jq-verify="required" jq-error="请输入品牌名称" placeholder="请输入品牌名称" autocomplete="off" class="layui-input ">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">收信者</label>
                <div class="layui-input-inline">
                    <input type="text" name="accepter" jq-verify="required" value="" jq-error="收信者名称" placeholder="收信者名称" autocomplete="off" class="layui-input ">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">状态</label>
                <div class="layui-input-inline">
                    <input type="radio" name="status" title="草稿" value="1" checked />
                    <input type="radio" name="status" title="发送" value="0" />
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
<!--#include file="tpl/msg.html"-->
<jsp:include page="tpl/msg.jsp" />
<script src="js/layui/layui.js"></script>
<!--#include file="common/version.html"-->
<script>
    layui.use('list');
</script>

</html>