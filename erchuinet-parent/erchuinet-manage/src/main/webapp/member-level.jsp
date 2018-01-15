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
                    <form class="layui-form" data-params='{"dataName":"level","key":"id","action":"list"}' action="./data/member-level.json">
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
                        <button class="layui-btn layui-btn-small layui-btn-danger ajax-all"  data-params='{"url": "./data/del.json","action":"del","dataName":"level","key":"id"}'>
                            <i class="iconfont">&#xe626;</i> 删除
                        </button>
                        <button class="layui-btn layui-btn-small layui-btn-normal ajax-all"  data-params='{"url": "./data/success.json","data":"status=1","dataName":"level","key":"id"}'>
                            <i class="layui-icon">&#x1005;</i> 启用
                        </button>
                        <button class="layui-btn layui-btn-small modal" data-params='{"content": ".add-subcat", "title": "添加会员","type":"1","area":"700px,auto","action":"add"}'>
                            <i class="iconfont">&#xe649;</i> 添加
                        </button>
                    </div>
                    <div class="layui-form">
                        <table id="example" class="layui-table jq-even" data-params='{"dataName":"level","key":"id"}'>
                            <thead>
                                <tr>
                                    <th width="30"><input type="checkbox" id="checkall" data-name="id" lay-filter="check" lay-skin="primary"></th>
                                    <th width="60"><span class="order" data-params='{"field":"id","sort":"asc"}'>序号</span></th>
                                    <th width="100">等级图标</th>
                                    <th>等级名称</th>
                                    <th>所需经验</th>
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
        <form id="form1" class="layui-form" data-params='{"dataName":"level","key":"id","bind":true,"action":"add"}' action="./data/add.json">
            <div class="layui-form-item">
                <label class="layui-form-label">等级名称</label>
                <div class="layui-input-block">
                    <input type="text" name="name" required jq-verify="required" jq-error="请输入等级名称" jq-url="./data/success.json" placeholder="请输入等级名称" autocomplete="off" class="layui-input ">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">等级图标</label>
                <div class="layui-input-block">
                    <input type="file" name="file" class="layui-upload-file">
                    <input type="hidden" name="icon" class="img" jq-verify="required" jq-error="请上传等级图标" error-id="img-error">
                    <p id="img-error"></p>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">所需经验</label>
                <div class="layui-input-inline">
                    <input type="text" name="experience" jq-verify="number" jq-error="必须是分数" placeholder="请输入所需经验" autocomplete="off" class="layui-input ">
                </div>
            </div>


            <div class="layui-form-item">
                <label class="layui-form-label">状态</label>
                <div class="layui-input-inline">
                    <input type="radio" name="status" title="启用" value="1" checked />
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
<!--#include file="tpl/member-level.html"-->
<script src="js/layui/layui.js"></script>
<!--#include file="common/version.html"-->
<script>
    layui.use('list');
</script>

</html>