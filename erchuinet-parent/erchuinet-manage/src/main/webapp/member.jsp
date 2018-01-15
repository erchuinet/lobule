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
                    <form class="layui-form" action="./data/member.json" method="get" data-params='{"dataName":"member","action":"list"}'>
                        <div class="layui-form">
                            <div class="layui-inline">
                                <select name="city" lay-verify="required">
                                    <option value="0">请选择地区</option>
                                    <option value="010">北京</option>
                                    <option value="021">上海</option>
                                    <option value="0571">杭州</option>
                                </select>
                            </div>
                            <div class="layui-inline">
                                <div class="layui-input-inline">
                                    <input class="layui-input start-date" name="start_date" placeholder="注册时间">
                                </div>
                                <div class="layui-input-inline">
                                    <input class="layui-input end-date" name="end_date" placeholder="注册时间">
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
                        <button class="layui-btn layui-btn-small layui-btn-danger ajax-all" data-name="id" data-params='{"url": "./data/del.json","action":"del","dataName":"member","key":"id"}'>
                            <i class="iconfont">&#xe626;</i> 删除
                        </button>
                        <button class="layui-btn layui-btn-small layui-btn-normal ajax-all" data-name="id" data-params='{"url": "./data/success.json","data":"status=1","dataName":"member","key":"id"}'>
                            <i class="layui-icon">&#x1005;</i> 审核
                        </button>
                        <button class="layui-btn layui-btn-small modal" data-params='{"content": ".add-subcat", "title": "添加会员","type":"1","area":"700px,auto","data":"id={{item.id}}","dataName":"member"}'>
                            <i class="iconfont">&#xe649;</i> 添加
                        </button>
                    </div>
                    <div class="layui-form">
                        <table id="example" class="layui-table jq-even" data-params='{"dataName":"member","key":"id"}'>
                            <thead>
                                <tr>
                                    <th width="30"><input type="checkbox" id="checkall" data-name="id" lay-filter="check" lay-skin="primary"></th>
                                    <th width="80"><span class="order" data-params='{"field":"id","sort":"asc"}'>序号</span></th>
                                    <th width="100">头像</th>
                                    <th>姓名</th>
                                    <th>手机</th>
                                    <th width="70">性别</th>
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
        <form id="form1" class="layui-form" data-params='{"dataName":"member","key":"id","bind":true,"action":"add"}' action="./data/add.json">
            <div class="layui-form-item">
                <label class="layui-form-label">姓名</label>
                <div class="layui-input-block">
                    <input type="text" name="name" required jq-verify="required" jq-error="请输入姓名" placeholder="请输入姓名" autocomplete="off" class="layui-input ">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">所属地区</label>
                <div class="layui-input-inline">
                    <select name="city" jq-verify="required" jq-error="请选择地区" lay-filter="verify">
                        <option value=""></option>
                        <option value="0">北京</option>
                        <option value="1">上海</option>
                        <option value="2">广州</option>
                        <option value="3">深圳</option>
                        <option value="4">杭州</option>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">上传头像</label>
                <div class="layui-input-block">
                    <input type="file" name="file" class="layui-upload-file">
                    <input type="hidden" name="avatar" jq-verify="required" jq-error="请上传头像" error-id="img-error">
                    <p id="img-error"></p>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">手机</label>
                <div class="layui-input-inline">
                    <input type="text" name="tel" jq-verify="phone" jq-error="手机号码格式不对" placeholder="请输入手机号码" autocomplete="off" class="layui-input ">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">邮箱</label>
                <div class="layui-input-inline">
                    <input type="text" name="sorts" required jq-verify="email" jq-error="邮箱格式不对" placeholder="请输入邮箱" autocomplete="off" class="layui-input ">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">性别</label>
                <div class="layui-input-block">
                    <input type="radio" name="sex" title="男" value="1" checked="checked" />
                    <input type="radio" name="sex" title="女" value="0" />
                    <input type="radio" name="sex" title="保密" value="2" />
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">状态</label>
                <div class="layui-input-inline">
                    <input type="radio" name="status" title="启用" value="1" />
                    <input type="radio" name="status" title="禁用" value="0" />
                </div>
            </div>
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">备注</label>
                <div class="layui-input-block">
                    <textarea name="desc" placeholder="请输入内容" class="layui-textarea"></textarea>
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
<!--#include file="tpl/member.html"-->
<script src="js/layui/layui.js"></script>
<!--#include file="common/version.html"-->
<script>
    layui.use('list');
</script>

</html>