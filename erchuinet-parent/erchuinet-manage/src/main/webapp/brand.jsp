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
                    <form class="layui-form" data-params='{"dataName":"brand","action":"list"}' action="./data/data.json" method="get">
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
                                <div class="layui-input-inline">
                                    <input class="layui-input start-date" name="start_date" placeholder="入驻时间">
                                </div>
                                <div class="layui-input-inline">
                                    <input class="layui-input end-date" name="end_date" placeholder="入驻时间">
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
                        <button class="layui-btn layui-btn-small layui-btn-danger ajax-all" data-params='{"url": "./data/del.json","dataName":"brand","key":"id","action":"del"}'>
                            <i class="iconfont">&#xe626;</i> 删除
                        </button>
                        <button class="layui-btn layui-btn-small layui-btn-normal ajax-all" data-params='{"url": "./data/success.json","data":"switch=1","dataName":"brand","key":"id"}'>
                            <i class="layui-icon">&#x1005;</i> 审核
                        </button>
                        <button class="layui-btn layui-btn-small modal" data-params='{"content": ".add-subcat", "title": "添加品牌","type":"1","action":"add","key":"id"}'>
                            <i class="iconfont">&#xe649;</i> 添加
                        </button>
                    </div>
                    <div class="layui-form">
                        <table id="example" class="layui-table jq-even" data-params='{"dataName":"brand","key":"id"}'>
                            <thead>
                                <tr>
                                    <th width="30"><input type="checkbox" id="checkall" data-name="id" lay-filter="check" lay-skin="primary"></th>
                                    <th width="60"><span class="order" data-params='{"field":"id","sort":"asc"}'> 序号</span></th>
                                    <th width="100">LOGO</th>
                                    <th><span class="order" data-params='{"field":"title","sort":"asc"}'> 名称</span>
                                    </th>
                                    <th width="70"><span class="order" data-params='{"field":"order","sort":"asc"}'> 排序</span></th>
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

    <div class="add-subcat">
        <form id="form1" class="layui-form" data-params='{"dataName":"brand","key":"id","action":"add"}' action="./data/add.json">
            <div class="layui-form-item">
                <label class="layui-form-label">品牌名称</label>
                <div class="layui-input-block">
                    <input type="text" name="title" jq-verify="required" jq-error="请输入品牌名称" placeholder="请输入品牌名称" autocomplete="off" class="layui-input ">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">所属分类</label>
                <div class="layui-input-inline">
                    <select name="city" jq-verify="required" hidden-required="true" jq-error="请输入分类" lay-filter="verify">
                        <option ></option>
                        <option value="1">北京</option>
                        <option value="2">上海</option>
                        <option value="3">广州</option>
                        <option value="4">深圳</option>
                        <option value="5">杭州</option>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">上传图片</label>
                <div class="layui-input-block">
                    <input type="file" name="file" class="layui-upload-file">
                    <input type="hidden" name="img" class="img" jq-verify="required" jq-error="请上传图片" error-id="img-error">
                    <p id="img-error" class="error"></p>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">排序</label>
                <div class="layui-input-inline">
                    <input type="text" name="sorts" required jq-verify="number" value="100" jq-error="排序必须为数字" placeholder="分类排序" autocomplete="off" class="layui-input ">
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
        </form>
    </div>

</body>
<!--#include file="tpl/brand.html"-->
<jsp:include page="tpl/brand.jsp" />
<script src="js/layui/layui.js"></script>
<!--#include file="common/version.html"-->
<jsp:include page="common/version.jsp" />
<script>
    layui.use('list');
</script>

</html>