<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:include page="common/header.jsp" />
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>jQadmin后台模板</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <!-- load css -->
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" media="all">
    <link rel="stylesheet" type="text/css" href="css/font/iconfont.css" media="all">
    <link rel="stylesheet" type="text/css" href="css/layui.css" media="all">
    <link rel="stylesheet" type="text/css" href="css/main.css?v1.0.0" media="all">
</head>

<body>
    <div class="container-fluid larry-wrapper">
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-12">
                <!--头部搜索-->
                <section class="panel panel-padding">
                    <form class="layui-form" data-params='{"dataName":"role","key":"id","action":"list"}' action="./data/role.json">
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
                        <button class="layui-btn layui-btn-small layui-btn-danger ajax-all" data-params='{"url": "./data/del.json","dataName":"role","key":"id","action":"del"}'>
                            <i class="iconfont">&#xe626;</i> 删除
                        </button>
                        <button class="layui-btn layui-btn-small layui-btn-normal ajax-all" data-name="checkbox" data-params='{"url": "./data/success.json","data":"status=0","dataName":"role","key":"id"}'>
                            <i class="layui-icon">&#x1005;</i> 禁用
                        </button>
                        <button class="layui-btn layui-btn-small modal" data-params='{"content": "admin-role-add.html", "title": "添加角色","full":"true","action":"add","dataName":"role","key":"id","type":2}'>
                            <i class="iconfont">&#xe649;</i> 添加角色
                        </button>
                    </div>
                    <div class="layui-form">
                        <table id="example" class="layui-table jq-even" data-params='{"dataName":"role","key":"id"}'>
                            <thead>
                                <tr>
                                    <th width="30"><input type="checkbox" id="checkall" data-name="id" lay-filter="check" lay-skin="primary"></th>
                                    <th width="60"><span class="order" data-params='{"field":"id","sort":"asc"}'>序号</span></th>
                                    <th>角色名称</th>
                                    <th>描述</th>
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
</body>
<!--#include file="tpl/role.html"-->
<script src="js/layui/layui.js"></script>
<!--#include file="common/version.html"-->
<script>
    layui.use('list');
</script>

</html>