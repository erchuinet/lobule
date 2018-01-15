<!--#include file="common/header.html"-->
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:include page="common/header.jsp" />
<!DOCTYPE html>
<body>
    <div class="container-fluid larry-wrapper">
        <!--顶部统计数据预览 -->
        <div class="row">
            <div class="col-xs-6 col-sm-4 col-md-2">
                <section class="panel">
                    <div class="symbol bgcolor-blue"> <i class="iconfont">&#xe672;</i>
                    </div>
                    <div class="value tab-menu">
                        <a href="javascript:;" data-url="user-info.html" data-parent="true" data-title="用户总量"><i class="iconfont " data-icon='&#xe672;'></i>
                            <h1>10</h1>
                        </a>

                        <a href="javascript:;" data-url="user-info.html" data-parent="true" data-title="用户总量"> <i class="iconfont " data-icon='&#xe672;'></i><span>用户总量</span></a>

                    </div>
                </section>
            </div>
            <div class="col-xs-6 col-sm-4 col-md-2">
                <section class="panel">
                    <div class="symbol bgcolor-commred"> <i class="iconfont">&#xe674;</i>
                    </div>
                    <div class="value tab-menu">
                        <a href="javascript:;" data-url="user-info.html" data-parent="true" data-title="今日注册"> <i class="iconfont " data-icon='&#xe674;'></i>
                            <h1>10</h1>
                        </a>

                        <a href="javascript:;" data-url="user-info.html" data-parent="true" data-title="今日注册"> <i class="iconfont " data-icon='&#xe674;'></i><span>今日注册</span></a>

                    </div>
                </section>
            </div>

            <div class="col-xs-6 col-sm-4 col-md-2">
                <section class="panel">
                    <div class="symbol bgcolor-dark-green"> <i class="iconfont">&#xe6bc;</i>
                    </div>
                    <div class="value tab-menu">
                        <a href="javascript:;" data-url="user-info.html" data-parent="true" data-title="文章总数"> <i class="iconfont " data-icon='&#xe6bc;'></i>
                            <h1>10</h1>
                        </a>
                        <a href="javascript:;" data-url="user-info.html" data-parent="true" data-title="文章总数"> <i class="iconfont " data-icon='&#xe6bc;'></i><span>文章总数</span></a>
                    </div>
                </section>
            </div>

            <div class="col-xs-6 col-sm-4 col-md-2">
                <section class="panel">
                    <div class="symbol bgcolor-yellow-green"> <i class="iconfont">&#xe649;</i>
                    </div>
                    <div class="value tab-menu">
                        <a href="javascript:;" data-url="user-info.html" data-parent="true" data-title="今日新增"> <i class="iconfont " data-icon='&#xe649;'></i>
                            <h1>10</h1>
                        </a>
                        <a href="javascript:;" data-url="user-info.html" data-parent="true" data-title="今日新增"> <i class="iconfont " data-icon='&#xe649;'></i><span>今日新增</span></a>
                    </div>
                </section>
            </div>

            <div class="col-xs-6 col-sm-4 col-md-2">
                <section class="panel">
                    <div class="symbol bgcolor-orange"> <i class="iconfont">&#xe638;</i>
                    </div>
                    <div class="value tab-menu">
                        <a href="javascript:;" data-url="user-info.html" data-parent="true" data-title="评论总数"> <i class="iconfont " data-icon='&#xe638;'></i>
                            <h1>10</h1>
                        </a>
                        <a href="javascript:;" data-url="user-info.html" data-parent="true" data-title="评论总数"> <i class="iconfont " data-icon='&#xe638;'></i><span>评论总数</span></a>
                    </div>
                </section>
            </div>

            <div class="col-xs-6 col-sm-4 col-md-2">
                <section class="panel">
                    <div class="symbol bgcolor-yellow"> <i class="iconfont">&#xe669;</i>
                    </div>
                    <div class="value tab-menu">
                        <a href="javascript:;" data-url="user-info.html" data-parent="true" data-title="今日评论"> <i class="iconfont " data-icon='&#xe669;'></i>
                            <h1>10</h1>
                        </a>
                        <a href="javascript:;" data-url="user-info.html" data-parent="true" data-title="今日评论"> <i class="iconfont " data-icon='&#xe669;'></i><span>今日评论</span></a>
                    </div>
                </section>
            </div>

        </div>
        <div class="row">
            <div class="col-xs-12 col-md-6">

                <section class="panel log">
                    <div class="panel-heading">
                        更新日志
                        <a href="javascript:;" class="pull-right panel-toggle"><i class="iconfont">&#xe604;</i></a>
                    </div>
                    <div class="panel-body">
                        <h2>jQadmin后台模板v2.0.0 2017-07-29</h2>
                        <ul>
                            <li> 使用json数据模拟真实操作</li>
                            <li> 页面更简化</li>
                            <li> 支持客户端或服务端分页</li>
                            <li> 加入省市区联动选择</li>
                            <li> 更新权限选择模块</li>
                            <li> 添加了一些钩子函数</li>
                            <li> 更新的太多，不记得了，自己体验吧</li>
                        </ul>
                        <h2>jQadmin后台模板v1.3.4r2 2017-05-09</h2>
                        <ul>
                            <li> 修复弹窗过高，挣出屏幕BUG</li>
                        </ul>

                        <h2>jQadmin后台模板v1.3.4r1 2017-05-08</h2>
                        <ul>
                            <li> 修复火狐刷新打开菜单列表出错BUG</li>
                        </ul>

                        <h2>jQadmin后台模板v1.3.4r 2017-05-05</h2>
                        <ul>
                            <li> 修复列表编辑时异步没有提交值错误</li>
                            <li> 添加打开页面时加载动画效果</li>
                        </ul>

                        <h2>jQadmin后台模板v1.3.4</h2>
                        <ul>
                            <li> 修复列表页搜索时分页不跳转到第一页的BUG</li>
                            <li> 添加表格按字段排序功能</li>
                            <li> 添加表格编辑框</li>
                            <li> 添加ajax回调时是否关闭回调参数</li>
                            <li> 添加弹窗标题自定义</li>
                            <li> 添加异步后是否刷新列表参数，列表刷新不支持iframe模式</li>
                            <li> 改进表单数据填充方式</li>
                            <li> 添加刷新后重新打开原来打开的窗口</li>
                            <li> 添加打开窗口列表，便于快捷切换</li>
                            <li> 所有ajax请求改为请求json数据</li>
                            <li> 修正弹窗后表单不重置错误</li>
                        </ul>
                        <h2>jQadmin后台模板v1.3.3 2017-03-22 </h2>
                        <ul>
                            <li> 修复表单提交关闭不了窗口的BUG</li>
                            <li> 修正菜单选项卡不能关闭BUG</li>
                            <li> 修正菜单右键弹出菜单bug</li>
                            <li> 修复首页的统计链接</li>
                            <li> 添加看云使用手册</li>
                        </ul>
                        <p>更多帮助文档请移步到 <a href="http://www.kancloud.cn/jqcool/jqadmin" target="_blank" class="layui-btn layui-btn-small">使用手册</a></p>
                    </div>
                </section>
            </div>


            <div class="col-xs-12 col-md-6">
                <section class="panel">
                    <div class="panel-heading">
                        网站信息
                        <a href="javascript:;" class="pull-right panel-toggle"><i class="iconfont">&#xe604;</i></a>
                    </div>
                    <div class="panel-body">
                        <table class="layui-table">
                            <tbody>
                                <tr>
                                    <td>
                                        <strong>软件名称</strong>：

                                    </td>
                                    <td>
                                        <a href="http://jqadmin.jqcool.net">jqadmin后台模板</a>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <strong>软件版本</strong>：

                                    </td>
                                    <td>
                                        V2.0.0
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <strong>开发作者</strong>：
                                    </td>
                                    <td>Paco</td>
                                </tr>
                                <tr>
                                    <td>
                                        <strong>软件官网</strong>：
                                    </td>
                                    <td>
                                        <a href="http://jqadmin.jqcool.net" target="_blank">jqadmin</a>
                                        <a class="layui-btn  layui-btn-small  layui-btn-danger" href="http://www.kancloud.cn/jqcool/jqadmin" target="_blank">使用手册1.3.4</a>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <strong>模板下载</strong>：
                                    </td>
                                    <td><a href="https://git.oschina.net/jqcool/jqadmin" target="_blank" class="layui-btn layui-btn-small">码云下载</a>
                                        <a href="https://jqadmin.jqcool.net/jqadmin-2.0.0.zip" target="_blank" class="layui-btn layui-btn-small">2.0.0版</a>
                                        <a href="https://jqadmin.jqcool.net/jqadmin-1.3.4r2.zip" target="_blank" class="layui-btn layui-btn-small">1.3.4r2版</a>
                                        <a href="https://jqadmin.jqcool.net/jqadmin-no-ssi-1.3.4r2.zip" target="_blank" class="layui-btn layui-btn-small">1.3.4r2无ssi版</a>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <strong>QQ讨论群</strong>：
                                    </td>
                                    <td>
                                        <a target="_blank" href="//shang.qq.com/wpa/qunwpa?idkey=bb0dbfcf87d269f825807a1ba7ff6a1c09d07b98b724192ed325e840284b4821"><img border="0" src="//pub.idqqimg.com/wpa/images/group.png" alt="jQ酷" title="jQ酷"></a> 入群答案：jqadmin</td>
                                </tr>
                                <tr>
                                    <td>
                                        <strong>服务器环境</strong>：
                                    </td>
                                    <td>windows</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </section>
                <section class="panel">
                    <div class="panel-heading">
                        数据统计
                        <a href="javascript:;" class="pull-right panel-toggle"><i class="iconfont">&#xe604;</i></a>
                    </div>
                    <div class="panel-body">
                        <div class="echarts" id="echarts"></div>
                    </div>
                </section>

            </div>
        </div>

    </div>

    <div class="testcatch" style="display: none;">
        <p>这是一个捕获弹窗</p>
    </div>
</body>
<script type="text/javascript" src="js/layui/layui.js"></script>
<!--#include file="common/version.html"-->
<jsp:include page="common/version.jsp" />
<script>
    layui.use(['main', 'echart']);
</script>

</html>