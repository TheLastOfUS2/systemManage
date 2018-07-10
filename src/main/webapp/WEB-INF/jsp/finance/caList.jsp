<!DOCTYPE html PUBLIC "-W3CDTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <base href="${basePath}" />
    <jsp:include page="/WEB-INF/jsp/common/_include.jsp" />
    <!--页面功能块动作事件-->
    <script type="text/javascript" src="resources/js/jquery-1.7.1.min.js"></script>
    <!--easyUI布局插件-->
    <script type="text/javascript" src="resources/App_Addins/easyUI/jquery.easyui.min.js"></script>
    <!-- 本页js -->
    <script type="text/javascript" src="resources/js/finance/caList.js"></script>
    <!-- 分页 -->
    <script type="text/javascript" src="resources/js/easyui-lang-zh_CN.js"></script>
    <!-- 时间戳日期格式化-->
    <script type="text/javascript" src="resources/js/formatTime.js"></script>
    <!-- 导出 cjk编码转换 -->
    <script type="text/javascript" src="resources/js/cjkEncode.js"></script>
    <!-- 上传-->
    <script type="text/javascript" src="resources/js/jquery-form.js"></script>
    <!-- 密码加密-->
    <script type="text/javascript" src="resources/js/md5.js"></script>
    <!-- 选择作者 和 弹框居中样式-->
    <script type="text/javascript" src="resources/js/searchName.js"></script>
</head>
<body style="overflow-x : hidden;  overflow-y : hidden; ">
<!-- fit属性是指自适应填充父级框 --><!-- pagination="true" --><!--  pagination="true"：列表下显示分页 -->
<!-- scrollbarSize:0:去掉空白滚动条 -->
<!-- 基本信息列表 -->
<table id="dg" title="资金分配列表"
       data-options="fit:true,toolbar:toolbar,method:'get', fitColumns:true,rownumbers: true,
	   iconCls: 'icon-save', border: true,pagination: true,collapsible: false,pageSize:30, scrollbarSize :0" toolbar="#dlg-toolbar"   style="overflow: hidden">
    <thead>
    <input type="hidden" id="caDel"/>
    </thead>
</table>
<!-- 基本信息列表 -->

<%--基本信息列表上方的添加等按钮--%>
<div id="dlg-toolbar" style="padding:5px;height:auto;display: none">
    <table cellpadding="0" cellspacing="0">
        <tr>
            <%--<td>--%>
            <%--<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="add()">新增</a>--%>
            <%--</td>--%>
            <td>
            <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="edit()">编辑</a>
            </td>
            <%--<td><div class="datagrid-btn-separator"></div></td>--%>
            <%--<td>--%>
            <%--<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="del()">删除</a>--%>
            <%--</td>--%>
            <%--<td>--%>
            <%--<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-trash',plain:true" onclick="trash()">回收站</a>--%>
            <%--</td>--%>
            <%--<td>--%>
            <%--<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true" id="recovery" onclick="recovery()">恢复</a>--%>
            <%--</td>--%>
            <%--<td><div class="datagrid-btn-separator"></div></td>--%>
            <%--<td>--%>
            <%--<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-xls',plain:true" onclick="excel()">导出</a>--%>
            <%--</td>--%>
            <td><!-- prompt:'请输入检索关键字', -->
                <input id="searchboxId" class="easyui-searchbox" data-options="menu:'#mm',searcher:doSearch" style="width:200px"></input>
                <div id="mm">
                    <div data-options="name:'projectName'">项目名</div>
                    <div data-options="name:'projectApprovedOutlay'">项目总金额(万)</div>
                    <div data-options="name:'baseInfoName'">项目第一负责人</div>
                    <div data-options="name:'projectTime'">立项时间</div>
                    <div data-options="name:'projectKnotTime'">结项时间</div>
                    <%--<div data-options="name:'baseInfoName'">绑定用户姓名</div>--%>
                </div>
            </td>
        </tr>
    </table>
</div>
<!-- 新增、编辑基本信息 -->
<div id="dlg" class="easyui-dialog"  data-options="closed:true,buttons:'#dlg-buttons'" style='display: none'>
    <div style="margin: 13px;">
        <div ><h2 style="border-bottom:1px solid #999">资金分配</h2></div>
        <form id="fm" method="post">
            <!-- 基本信息id -->
            <span style="display: none"><input type="text" id="projectId" name="projectId"></span>
            <!-- 基本信息类型 -->
            <span style="display: none"><input type="text" name="projectType" id="projectType"></span>
            <!-- 用来判断是弹窗是新增还是编辑 -->
            <span style="display: none"><input type="text" id="baseInfoPath"></span>
            <!-- 刚进入编辑页面时,存放合作者id -->
            <span style="display: none">
	        	<input type="text" id="collaboratorIds" name="collaboratorIds">
                <!-- 存放选择人员的文本框名称 -->
	         	<input type="text" id="name">
	        </span>
            <div id="ListForm" class="ListForm" style="text-align:left; width:95%;margin:0px auto; ">
                <!-- 通讯录信息 -->
                <div id="Staff" class="Staff" style="width:100%">
                    <table id='Staff1' class='StaffInfo'>
                        <tbody>
                        <tr>
                            <td width='120' style='text-align:right;'>
                                <label class='in_label'>项目名称:</label>
                            </td>
                            <td width='180'>
                                <input type='text' id='projectName' name='projectName' class='easyui-textbox' readonly='true'>
                            </td>
                        </tr>
                        <tr>
                            <td width='120' style='text-align:right;'>
                                <label class='in_label'>批准经费(万):</label>
                            </td>
                            <td width='180'>
                                <input class='easyui-textbox' id='projectApprovedOutlay' name='projectApprovedOutlay' style='width:139px' precision='2' data-options='min:0.00,max:10000' readonly='true'/>
                            </td>
                        </tr>
                        <tr>
                            <td width='120' style='text-align:right;'>
                                <label class='in_label'>项目第一负责人:</label>
                            </td>
                            <td width='180'>
                                <input type='text' id='baseInfoName' name='baseInfoName' readonly='true'/>
                                <input type='hidden' id='baseInfoNameId' name='baseInfoId'/>
                            </td>
                            <td width='120' style='text-align:right;'>
                                <label class='in_label'>分配金额(万):</label>
                            </td>
                            <td width='180'>
                                <input class='easyui-numberspinner' id='baseInfoAmount' name='baseInfoAmount' style='width:139px' precision='2' data-options='min:0.00,max:10000'/>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <!-- 通讯录信息 -->
            </div>
            <!-- 合作者-->
            <div class="model_list" id="collaborator" ></div>
        </form>
    </div>
</div>
<!-- 底部按钮 -->
<div id="dlg-buttons">
    <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveUser()" style="width:90px">保存</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</a>
</div>
<!-- 底部按钮 -->
<!-- 新增、编辑基本信息 -->

<!-- 删除提示框 -->
<div id="dlgClose" class="easyui-dialog"  data-options="closed:true" >
    <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveUser()" style="width:90px">保存</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</a>
</div>
<!-- 删除提示框 -->

<!-- 导出提示框 -->
<div id="dlgExcel" class="easyui-dialog" title="系统提示" data-options="iconCls:'icon-save',closed:true" style="width:260px;height:130px;padding:20px;text-align: center">
    文件生成成功,
    <a href='javascript:void(0)' class='easyui-linkbutton' data-options='plain:true' style="width:70px;height:30px;" onclick="exportExcel()">点此下载</a>
</div>
<!-- 导出提示框 -->

<!-- datagrid绑定右键菜单 -->
<div id="menu" class="easyui-menu" style="width: 30px; display: none;">
    <!--具体的菜单事件请自行添加，跟toolbar的方法是基本一样的-->
    <div id="btn_Modify" data-options="iconCls:'icon-edit'" onclick="edit()">编辑</div>
</div>
<!-- datagrid绑定右键菜单 -->

</body>
</html>
