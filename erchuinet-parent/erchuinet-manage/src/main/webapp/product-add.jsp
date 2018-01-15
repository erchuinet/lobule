<!--#include file="common/header.html"-->
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:include page="common/header.jsp" />
<link rel="stylesheet" type="text/css" href="css/webuploader.css?v=0.1.5" media="all">

<body>
    <div class="container-fluid larry-wrapper">
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-12">
                <section class="panel panel-padding">
                    <form id="form1" class="layui-form" data-params='{"dataName":"products","key":"id","action":"add"}' action="./data/add.json">
                        <div class="layui-form-item">
                            <label class="layui-form-label">标题</label>
                            <div class="layui-input-block">
                                <input type="text" name="title" required jq-verify="username" jq-error="请输入标题" placeholder="请输入标题" autocomplete="off" class="layui-input ">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">促销标题</label>
                            <div class="layui-input-block">
                                <input type="text" name="title" required jq-verify="username" jq-error="请输入标题" placeholder="请输入标题" autocomplete="off" class="layui-input ">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">关键字</label>
                            <div class="layui-input-block">
                                <input type="text" name="text" required jq-verify="required|number" jq-error="必须填写|必须是数字地" placeholder="请输入关键字，多个请用英文逗号隔开" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item ">
                            <label class="layui-form-label">描述</label>
                            <div class="layui-input-block">
                                <textarea name="desc" placeholder="请输入描述" class="layui-textarea"></textarea>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">所属分类</label>
                            <div class="layui-input-inline">
                                <select name="cat_id" jq-verify="required" jq-error="请输入分类" lay-filter="verify">
                                    <option value=""></option>
                                    <option value="0">北京</option>
                                    <option value="1">上海</option>
                                    <option value="2">广州</option>
                                    <option value="3">深圳</option>
                                    <option value="4">杭州</option>
                                </select>
                            </div>
                        </div>
                        <div class="layui-form-item" id="citys">
                            <label class="layui-form-label">产地</label>
                            <div class="layui-input-inline">
                                <select name="province" jq-verify="required" jq-error="请选择省份" lay-filter="citys">
                                    <option value="0">请选择</option>                      
                                </select>
                            </div>
                            <div class="layui-input-inline">
                                <select name="city" jq-verify="required" jq-error="请选择城市" lay-filter="citys">
                                    <option value="0">请选择</option>                      
                                </select>
                            </div>
                            <div class="layui-input-inline">
                                <select name="area" jq-verify="required" jq-error="请选择地区" lay-filter="citys">
                                    <option value="0">请选择</option>                      
                                </select>
                            </div>
                        </div>
                        <div class="layui-form-item ">
                            <label class="layui-form-label">市场价</label>
                            <span class="left-icon"><i class="iconfont">&#xe690;</i></span>
                            <div class="layui-input-inline">
                                <input type="text" placeholder="请输入市场价" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item ">

                            <label class="layui-form-label">销售价</label>
                            <span class="left-icon"><i class="iconfont">&#xe690;</i></span>
                            <div class="layui-input-inline">
                                <input type="text" placeholder="请输入销售价" autocomplete="off" class="layui-input">
                            </div>

                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label">审核</label>
                            <div class="layui-input-inline">
                                <input type="radio" name="status" title="是" value="1" checked />
                                <input type="radio" name="status" title="否" value="0" />
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">上架</label>
                            <div class="layui-input-inline">
                                <input type="radio" name="lk" title="是" value="1" checked />
                                <input type="radio" name="lk" title="否" value="0" />
                            </div>
                        </div>



                        <div class="layui-form-item">
                            <label class="layui-form-label">上传图片</label>
                            <div class="layui-input-block">
                                <div id="uploader" class="wu-example">
                                    <div class="queueList">
                                        <div id="dndArea" class="placeholder">
                                            <div id="filePicker"></div>
                                            <p>或将照片拖到这里，单次最多可选300张</p>
                                        </div>
                                    </div>
                                    <div class="statusBar" style="display:none;">
                                        <div class="progress">
                                            <span class="text">0%</span>
                                            <span class="percentage"></span>
                                        </div>
                                        <div class="info"></div>
                                        <div class="btns">
                                            <div id="filePicker2"></div>
                                            <div class="uploadBtn">开始上传</div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">内容</label>
                            <div class="layui-input-block">
                                <script id="content" name="content" type="text/plain"></script>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <div class="layui-input-block">
                                <button class="layui-btn" jq-submit data-ajax="true" lay-filter="submit">立即提交</button>
                                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                            </div>
                        </div>
                    </form>
                </section>
            </div>
        </div>
    </div>
</body>
<script type="text/javascript" src="js/layui/layui.js"></script>
<script src="js/common.js"></script>
<script src="js/lib/ueditor/ueditor.config.js"></script>
<script src="js/lib/ueditor/ueditor.all.min.js"></script>
<!--#include file="common/version.html"-->
<jsp:include page="common/version.jsp" />
<script>
    var $, JQquery;
    layui.use('product');
    var ue = UE.getEditor('content');
</script>

</html>