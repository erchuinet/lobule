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
                <section class="panel panel-padding">
                    <form id="form1" class="layui-form" data-params='{"action":"nodo"}' action="./data/success.json">
                        <div class="layui-tab" lay-filter="check">
                            <ul class="layui-tab-title">
                                <li class="layui-this" lay-id="1">基本设置</li>
                                <li lay-id="2">安全设置</li>
                                <li lay-id="3">邮箱设置</li>
                                <li lay-id="4">其它设置</li>
                            </ul>
                            <div class="layui-tab-content">
                                <div class="layui-tab-item layui-show">
                                    <div class="layui-form-item">
                                        <label class="layui-form-label">网站名称</label>
                                        <div class="layui-input-block">
                                            <input type="text" name="name" required jq-verify="required" jq-error="请输入网站名称" placeholder="请输入网站名称" autocomplete="off" class="layui-input ">
                                        </div>
                                    </div>
                                    <div class="layui-form-item">
                                        <label class="layui-form-label">关键字</label>
                                        <div class="layui-input-block">
                                            <input type="text" name="name" required jq-verify="required" jq-error="请输入关键字" placeholder="请输入关键字" autocomplete="off" class="layui-input ">
                                        </div>
                                    </div>
                                    <div class="layui-form-item ">
                                        <label class="layui-form-label">描述</label>
                                        <div class="layui-input-block">
                                            <textarea name="desc" placeholder="请输入描述" class="layui-textarea"></textarea>
                                        </div>
                                    </div>
                                    <div class="layui-form-item ">
                                        <label class="layui-form-label">统计代码</label>
                                        <div class="layui-input-block">
                                            <textarea name="desc" placeholder="统计代码" class="layui-textarea"></textarea>
                                        </div>
                                    </div>
                                    <div class="layui-form-item layui-form-text">
                                        <label class="layui-form-label">底部代码</label>

                                    </div>
                                </div>
                                <div class="layui-tab-item">
                                    <div class="layui-form-item ">
                                        <label class="layui-form-label">受限IP列表</label>
                                        <div class="layui-input-block">
                                            <textarea name="desc" placeholder="统计代码" class="layui-textarea"></textarea>
                                        </div>
                                    </div>
                                    <div class="layui-form-item">
                                        <label class="layui-form-label">登录次数</label>
                                        <div class="layui-input-block">
                                            <input type="text" name="name" required jq-verify="required" jq-error="必须是数字" placeholder="最大登录失败次数" autocomplete="off" class="layui-input ">
                                        </div>
                                    </div>
                                </div>
                                <div class="layui-tab-item">
                                    <div class="layui-form-item">
                                        <label class="layui-form-label">发送模式</label>
                                        <div class="layui-input-block">
                                            <input type="text" name="name" required jq-verify="required" jq-error="必须是数字" placeholder="最大登录失败次数" autocomplete="off" class="layui-input ">
                                        </div>
                                    </div>
                                    <div class="layui-form-item">
                                        <label class="layui-form-label">SPTM</label>
                                        <div class="layui-input-block">
                                            <input type="text" name="name" required jq-verify="required" jq-error="请输入SPTM服务器地址" placeholder="SPTM服务器地址" autocomplete="off" class="layui-input ">
                                        </div>
                                    </div>
                                    <div class="layui-form-item">
                                        <label class="layui-form-label">SMTP端口</label>
                                        <div class="layui-input-block">
                                            <input type="text" name="name" required jq-verify="required" jq-error="请输入SMTP端口" placeholder="SMTP端口" autocomplete="off" class="layui-input ">
                                        </div>
                                    </div>

                                    <div class="layui-form-item">
                                        <label class="layui-form-label">发件邮箱</label>
                                        <div class="layui-input-block">
                                            <input type="text" name="name" required jq-verify="required" jq-error="请输入发件邮箱" placeholder="发件邮箱帐号" autocomplete="off" class="layui-input ">
                                        </div>
                                    </div>

                                    <div class="layui-form-item">
                                        <label class="layui-form-label">邮箱密码</label>
                                        <div class="layui-input-block">
                                            <input type="text" name="name" required jq-verify="required" jq-error="发件邮箱密码" placeholder="发件邮箱密码" autocomplete="off" class="layui-input ">
                                        </div>
                                    </div>

                                    <div class="layui-form-item">
                                        <label class="layui-form-label">收件邮箱</label>
                                        <div class="layui-input-block">
                                            <input type="text" name="name" required jq-verify="required" jq-error="收件邮箱" placeholder="收件邮箱" autocomplete="off" class="layui-input ">
                                        </div>
                                    </div>

                                </div>
                                <div class="layui-tab-item">
                                    <div class="layui-form-item">
                                        <label class="layui-form-label">允许评论</label>
                                        <div class="layui-input-block">
                                            <input type="checkbox" name="checkbox" value="1" title="是">
                                        </div>
                                    </div>
                                    <div class="layui-form-item">
                                        <label class="layui-form-label">网站关闭</label>
                                        <div class="layui-input-block">
                                            <input type="checkbox" name="checkbox" value="1" title="是">
                                        </div>
                                    </div>
                                    <div class="layui-form-item">
                                        <label class="layui-form-label">开放注册</label>
                                        <div class="layui-input-block">
                                            <input type="checkbox" name="checkbox" value="1" title="是">
                                        </div>
                                    </div>
                                    <div class="layui-form-item">
                                        <label class="layui-form-label">开启缓存</label>
                                        <div class="layui-input-block">
                                            <input type="checkbox" name="checkbox" value="1" title="是">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <div class="layui-input-block">
                                <button class="layui-btn" jq-submit jq-filter="submit" jq-tab="true">立即提交</button>
                                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                            </div>
                        </div>
                    </form>
                </section>
            </div>
        </div>
    </div>
</body>

<script src="js/layui/layui.js"></script>
<!--#include file="common/version.html"-->
<jsp:include page="common/version.jsp" />
<script>
    layui.use('simpleform');
</script>

</html>