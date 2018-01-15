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
                    <form class="layui-form" data-params='{"dataName":"products","action":"list"}' action="./data/data.json" method="get">
                        <div class="layui-form">
                            <div class="layui-inline">
                                <select name="city" lay-verify="required">
                                    <option value="0">请选择分类</option>
                                    <option value="010">北京</option>
                                    <option value="021">上海</option>
                                    <option value="0571">杭州</option>
                                </select>
                            </div>
                            <div class="layui-inline">
                                <select name="status" lay-verify="required">
                                    <option value="021">上架</option>
                                    <option value="0571">下架</option>
                                </select>
                            </div>
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
                    <div class="group-button">
                        <button class="layui-btn layui-btn-small layui-btn-danger ajax-all" data-params='{"url": "./data/del.json","action":"del","dataName":"products","key":"id"}'>
                            <i class="iconfont">&#xe626;</i> 删除
                        </button>
                        <button class="layui-btn layui-btn-small layui-btn-normal ajax-all" data-params='{"url": "./data/success.json","data":"sell=1","dataName":"products","key":"id"}'>
                            <i class="iconfont">&#xe603;</i> 上架
                        </button>
                        <button class="layui-btn layui-btn-small layui-btn-normal ajax-all" data-params='{"url": "./data/success.json","data":"switch=1","dataName":"products","key":"id"}'>
                            <i class="layui-icon">&#x1005;</i> 审核
                        </button>
                        <button class="layui-btn layui-btn-small modal" data-params='{"content": "product-add.html", "title": "添加文章","type":"2"}'>
                            <i class="iconfont">&#xe649;</i> 添加
                        </button>
                    </div>
                    <div class="layui-form">
                        <table id="example" class="layui-table jq-even" data-params='{"dataName":"products","key":"id"}'>
                            <thead>
                                <tr>
                                    <th width="30"><input type="checkbox" id="checkall" data-name="id" lay-filter="check" lay-skin="primary"></th>
                                    <th width="80">序号</th>
                                    <th>缩略图</th>
                                    <th>标题</th>
                                    <th>价格</th>
                                    <th width="70">排序</th>
                                    <th width="80">审核</th>
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
<!--#include file="tpl/product.html"-->
<script src="js/layui/layui.js"></script>
<!--#include file="common/version.html"-->
<jsp:include page="common/version.jsp" />
<script>
    layui.use('list');
</script>

</html>