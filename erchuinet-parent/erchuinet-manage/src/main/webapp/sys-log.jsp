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
                    <form class="layui-form" data-params='{"dataName":"log","key":"id","action":"list"}' action="./data/data.json">
                        <div class="layui-form">

                            <div class="layui-inline">
                                <div class="layui-input-inline">
                                    <input class="layui-input start-date" name="start_date" placeholder="开始日">
                                </div>
                                <div class="layui-input-inline">
                                    <input class="layui-input end-date" name="end_date" placeholder="截止日">
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
                    <div id="list" class="layui-form"></div>

                    <div class="text-right" id="page"></div>
                </section>
            </div>
        </div>
    </div>
</body>
<script id="list-tpl" type="text/html" data-params='{"url":"./data/data.json","dataName":"log","key":"id","pageid":"#page"}'>
    <table id="example" class="layui-table lay-even">
        <thead>
            <tr>
                <th width="60">序号</th>
                <th>管理员</th>
                <th>描述</th>
                <th width="180">日期</th>
            </tr>
        </thead>
        <tbody>

            {{# layui.each(d.list, function(index, item){ }}
            <tr>
                <td>{{ item.id}}</td>
                <td>admin</td>
                <td>{{ item.title}}</td>
                <td>2017-02-24</td>
            </tr>
            {{# }); }}
        </tbody>

    </table>
</script>
<script src="js/layui/layui.js"></script>
<!--#include file="common/version.html"-->
<jsp:include page="common/version.jsp" />
<script>
    layui.use('list');
</script>

</html>