<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script id="list-tpl" charset="UTF-8" type="text/html" data-params='{"url":"./data/menu.json","dataName":"permision","callBack":"bindRole"}'>
    {{# layui.each(d.list, function(index, item){ }}
    <div class="jq-role-inline">
        <fieldset class="layui-elem-field">
            <legend>{{ item.name}}</legend>
            <div class="layui-field-box">
                {{# if(item.sub && item.sub.length>0){ }}
                <ul>
                    {{# layui.each(item.sub, function(index2, item2){ }}
                    <li><input type="checkbox" name="role[]" value="{{item2.id}}" title="{{item2.name}}" lay-skin="primary" lay-filter="role" /> {{# if(item2.sub && item2.sub.length>0){ }} {{# layui.each(item2.sub, function(index3, item3){ }}
                        <dl> <input type="checkbox" name="role[]" value="{{item3.id}}" title="{{item3.name}}" lay-skin="primary" lay-filter="role" /> {{# if(item3.sub && item3.sub.length>0){ }} {{# layui.each(item3.sub, function(index4, item4){ }}
                            <dd><input type="checkbox" name="role[]" value="{{item4.id}}" title="{{item4.name}}" lay-skin="primary" lay-filter="role" /></dd>
                            {{# }) }} {{# } }}
                        </dl>
                        {{# }) }} {{# } }}

                    </li>
                    {{# }) }}
                </ul>
                {{# } }}
            </div>
        </fieldset>
    </div>
    {{# }) }}
</script>