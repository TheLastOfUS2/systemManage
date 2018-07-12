var urls = "";
$(document).ready(function () {
    //当选择完文件后的操作
    $("input", $("#uploadFileid").next("span")).change(function () {
        upload1();
    });
    //查询列表信息
    queryAction(0, '', '');
    //改变数据为删除状态
    $("#recordDel").val(1);
    //隐藏恢复按钮
    $("div.datagrid-toolbar [id ='recovery']").eq(0).hide();

});

//检索
function doSearch() {
    //获取搜索的name值
    var getName = $('#searchboxId').searchbox('getName');
    //获取搜索框中的内容
    var getValue = $('#searchboxId').searchbox('getValue');
    queryAction(0, getName, getValue);
}

//调用后台方法
function queryAction(recordDel, getName, getValue) {
    // 获取操作对应的菜单ID
    urls = getPowerMenuId();
    //1.首先获取当前页号和每页显示条数
    $("#dg").datagrid({
        url: 'faCheck/checkList?recordDel=' + recordDel + "&getName=" + getName + "&getValue=" + getValue + urls,
        //加载列表数据
        columns: [[{
            field: 'ck',
            checkbox: true,
            align: 'center',
        }, {
            field: 'recordId',
            title: 'ID',
            width: 240,
            align: 'center',
            hidden: 'hidden'
        }, {
            field: 'projectName',
            title: '项目名',
            width: 80,
            sortable: true,
            formatter: function (value, row, index) {
                if (value != null && value != '') {
                    return value + "<input type='hidden' id='fileIds" + index + "' value='" + row.fileId + "'>"
                } else {
                    return '--'
                }
            }
        }, {
            field: 'baseInfoName',
            title: '申报人',
            width: 80,
            sortable: true,
            formatter: function (value, row, index) {
                if (value != null && value != '') {
                    return value
                } else {
                    return '--'
                }
            }
        }, {
            field: 'rbAmount',
            title: '申报金额',
            width: 80,
            sortable: true,
            formatter: function (value, row, index) {
                if (value != null && value != '') {
                    return value
                } else {
                    return '--'
                }
            }
        }, {
            field: 'recordType',
            title: '资金类型',
            width: 80,
            sortable: true,
            formatter: function (value, row, index) {
                if (value != null && value != '') {
                    if (value == 'A') {
                        return '调研、访问与论文发表'
                    } else if (value == 'B') {
                        return '学术会议'
                    }
                    else if (value == 'C') {
                        return '数据、图书及打印'
                    }
                    // else if (value == 'D') {
                    //     return '资金类型D'
                    // }
                } else {
                    return '--'
                }
            }
        }, {
            field: 'recordStatus',
            title: '报销状态',
            width: 80,
            sortable: true,
            formatter: function (value, row, index) {
                if (value != null && value != '') {
                    if (value == '0') {
                        return '申请中'
                    } else if (value == '1') {
                        return '已通过'
                    } else if (value == '2') {
                        return '已驳回'
                    }
                } else {
                    return '--'
                }
            }
        }, {
            field: 'todo',
            title: '操作',
            width: 80,
            sortable: true,
            formatter: function (value, row, index) {
                if (row.recordStatus != null && row.recordStatus != '') {
                    var successStatus = '1';
                    var failStatus = '2';
                    if (row.recordStatus == '0') {
                        return "<a href='javascript:void(0)' onclick='downUploadFile(\"" + row.fileId + "\")'>下载</a>&nbsp;" +
                            "<a href='javascript:void(0)' onclick='viewFile(\"" + row.filePath + "\")'>查看</a>&nbsp;" +
                            "<a href='javascript:void(0)' onclick='updateStatus(\"" + row.recordId + "\",1)'>通过</a>&nbsp;" +
                            "<a href='javascript:void(0)' onclick='updateStatus(\"" + row.recordId + "\",2)'>驳回</a>&nbsp;"
                    } else {
                        return "<a href='javascript:void(0)' onclick='downUploadFile(\"" + row.fileId + "\")'>下载</a>&nbsp;" +
                            "<a href='javascript:void(0)' onclick='viewFile(\"" + row.filePath + "\")'>查看</a>&nbsp;"
                    }
                } else {
                    return "<a href='javascript:void(0)' onclick='downUploadFile(\"" + row.fileId + "\")'>下载</a>&nbsp;" +
                        "<a href='javascript:void(0)' onclick='viewFile(\"" + row.filePath + "\")'>查看</a>&nbsp;"
                }
            }
        }]],
        /*datagrid绑定右键菜单*/
        onRowContextMenu: function (e, rowIndex, rowData) { //右键时触发事件
            //三个参数：e里面的内容很多，真心不明白，rowIndex就是当前点击时所在行的索引，rowData当前行的数据
            e.preventDefault(); //阻止浏览器捕获右键事件
            $(this).datagrid("clearSelections"); //取消所有选中项
            $(this).datagrid("selectRow", rowIndex); //根据索引选中该行
            $('#menu').menu('show', {
                //显示右键菜单
                left: e.pageX,//在鼠标点击处显示菜单
                top: e.pageY
            });
            e.preventDefault(); //阻止浏览器自带的右键菜单弹出
        }
        /*datagrid绑定右键菜单*/
    });
}


var num = 0;
var url;


function downloadCheckList() {
    // 获取操作对应的菜单ID
    urls = getPowerMenuId();

    //获取是在未被删除数据的列表还是已被删除数据的列表
    var checkListDel = $("#checkDel").val();
    //返回选中多行
    var selRow = $('#dg').datagrid('getSelections');
    if (selRow != "") {
        var temID = "";
        //批量获取选中行的评估模板ID
        for (i = 0; i < selRow.length; i++) {
            if (temID == "") {
                temID = selRow[i].fileId;
            } else {
                temID = selRow[i].fileId + "," + temID;
            }
        }
        window.open("common/downloadAllFiles?fileIds=" + temID + urls);
    } else {
        $.messager.alert('提示信息', '未选中任何记录!', 'info');
    }
}


function excel() {
    $("#dlgExcel").dialog("open");
}

//导出excle
function exportExcel() {
    $("#dlgExcel").dialog("close");
    //获取搜索的name值
    var getName = $('#searchboxId').searchbox('getName');
    //获取搜索框中的内容
    var getValue = $('#searchboxId').searchbox('getValue');
    getValue = getValueFunction(getName, getValue);

    var url = "ReportServer?reportlet=faDeclareRecordForAdmin.cpt&" + getName + "=" + getValue + "&format=excel&extype=simple&__filename__=" + cjkEncode("报销履历列表");
    window.open(url);
}

function getValueFunction(name, value) {
    //著作类型
    if (name == "recordStatus") {
        if (value == "申请中") {
            value = 0;
        } else if (value == "已通过") {
            value = 1;
        } else if (value == "已驳回") {
            value = 2;
        } else {
            value = 99;
        }
    } else if (name == "recordType") {
        if (value == "调研、访问与论文发表") {
            value = 'A';
        } else if (value == "学术会议") {
            value = 'B';
        } else if (value == "数据、图书及打印") {
            value = 'C';
        }
        // else if (value == "资金类型D") {
        //     value = 'D';
        // }
        else {
            value = 99;
        }
    }
    return value;
}

//下载文件
function downUploadFile(fileId) {
    // 获取操作对应的菜单ID
    urls = getPowerMenuId();
    alert(fileId);
    window.open("common/downloadFile?fileId=" + fileId + urls);
}

//更改审批状态（1.通过，2.驳回）
function updateStatus(recordId, recordStatus) {
    // window.alert(recordId);
    // window.alert(recordStatus);
    // 获取操作对应的菜单ID
    urls = getPowerMenuId();
    var checkDel = $("#checkDel").val();
    $.post('faCheck/updateStatus?recordId=' + recordId + '&recordStatus=' + recordStatus + urls, function (data) {
        $('#dg').datagrid('reload');
        $.messager.show({
            title: '提示',
            msg: data.text
        });
    }, 'json');
}


function edit() {
    // 获取操作对应的菜单ID
    urls = getPowerMenuId();

    var row = $('#dg').datagrid('getSelected');
    if (row) {
        num = 1;
        $("#rbAmountEdit").val(row.rbAmount);
        $("#recordIdEdit").val(row.recordId);
        $("#dlg").dialog({
            title: "财务审批-" + "修改金额",
            iconCls: "icon-edit",
            collapsible: true,
            /* minimizable: true,
             maximizable: true,*/
            resizable: true,
            onClose: function () {
                win.window('destroy');//关闭即销毁
            },
        });
        $("#dlg").dialog("open");
        // 弹框居中
        dlgCenter();
        // $.ajax({
        //     url:"project/projectViewForCaList?path=editProject&projectId="+ row.recordId + urls,
        //     type:"post",
        //     success:function (data){
        //         if(data!=null && data != ""){
        //             //将列表中的类型放到新增信息form中
        //             $('#fm').form('load', data);
        //             $('#projectId').val(row.projectId);
        //             $('#baseInfoPath').val(1);
        //             //每次进来设置collaboratorNum为0
        //             collaboratorNum=0;
        //             //加载对应通讯信息
        //             pageDisplay(data);
        //
        //             //负责人赋值
        //             setBaseInfoName();
        //             //加载合作者数据
        //             if(data.amCollaborators!=null && data.amCollaborators.length>0){
        //                 editCollaborator(data);
        //             }
        //             $("#dlg").dialog({
        //                 title: "资金分配-"+"-金额分配",
        //                 iconCls : "icon-edit",
        //                 collapsible: true,
        //                 /* minimizable: true,
        //                  maximizable: true,*/
        //                 resizable: true,
        //                 onClose: function(){
        //                     win.window('destroy');//关闭即销毁
        //                 },
        //             });
        //             $("#dlg").dialog("open");
        //             // 弹框居中
        //             dlgCenter();
        //             //点击保存按钮后跳转路径;collaboratorType:著作
        //             url = "project/saveOrUpProjectForCaList?collaboratorType=0" + urls;
        //         }else {
        //             // 重定向到登录页
        //             window.open('http://xrj.widonet.com/www/dongcai/','_top');
        //         }
        //     }
        // });
    } else {
        $.messager.alert('提示信息', '未选中任何记录!', 'info');
    }
}

function del(){
    //获取是在未被删除数据的列表还是已被删除数据的列表
    // 获取操作对应的菜单ID
    urls = getPowerMenuId();

    var recordDel=$("#recordDel").val();
    //返回选中多行
    var selRow = $('#dg').datagrid('getSelections');
    if (selRow!=""){
        var temID="";
        //批量获取选中行的评估模板ID
        for (i = 0; i < selRow.length;i++) {
            if (temID =="") {
                temID = selRow[i].recordId;
            } else {
                temID = selRow[i].recordId + "," + temID;
            }
        }
        var delTitle;
        if(recordDel==1){
            delTitle="确认要删除吗?"
        }else{
            delTitle="删除后将无法恢复,确认删除吗?"
        }
        $.messager.confirm('确认',delTitle,function(r){
            if (r){
                $.post('faCheck/deleteRecord?recordDel='+recordDel + urls,{recordId:temID},function(data){
                    if(data.status == 1){
                        $('#dg').datagrid('reload');
                        $.messager.show({
                            title: '提示',
                            msg: data.text
                        });
                    } else {
                        $.messager.show({
                            title: '提示',
                            msg: data.text
                        });
                    }
                },'json');
            }
        });
    }else{
        $.messager.alert('提示信息','未选中任何记录!','info');
    }
}

function trash(){
    //查询已删除数据列表
    queryAction(1);
    //彻底删除数据
    $("#recordDel").val(2);
    //显示恢复按钮
    $("div.datagrid-toolbar [id ='recovery']").eq(0).show();
}

function recovery(){
    //将选中的数据恢复成未删除状态
    // 获取操作对应的菜单ID
    urls = getPowerMenuId();

    //返回选中多行
    var selRow = $('#dg').datagrid('getSelections');
    if (selRow!=""){
        var temID="";
        //批量获取选中行的评估模板ID
        for (i = 0; i < selRow.length;i++) {
            if (temID =="") {
                temID = selRow[i].recordId;
            } else {
                temID = selRow[i].recordId + "," + temID;
            }
        }
        $.messager.confirm('确认','确定要恢复吗?',function(r){
            if (r){
                $.post('faCheck/deleteRecord?recordDel=0' + urls,{recordId:temID},function(data){
                    if(data.status == 1){
                        $('#dg').datagrid('reload');
                        $.messager.show({
                            title: '提示',
                            msg: data.text
                        });
                    } else {
                        $.messager.show({
                            title: '提示',
                            msg: data.text
                        });
                    }
                },'json');
            }
        });
    }else{
        $.messager.alert('提示信息','未选中任何记录!','info');
    }
}

//保存信息
function saveUser() {
    urls = getPowerMenuId();
    rbAmount= $('#rbAmountEdit').val();
    recordId= $('#recordIdEdit').val();
    $.post('faCheck/updateAmount?recordId=' + recordId + '&rbAmount=' + rbAmount + urls, function (data) {
        $('#dg').datagrid('reload');
        $('#dlg').dialog('close');
        $.messager.show({
            title: '提示',
            msg: data.text
        });
    }, 'json');
    // $.ajax({
    //     type:"post",
    //     url:"/faCheck/updateAmount",
    //     data: {
    //         rbAmount: $('#rbAmountEdit').val(),
    //         recordId: $('#recordIdEdit').val()
    //     },
    //     success:function (result) {
    //         $('#dg').datagrid('reload');    // reload the user data
    //         $('#dlg').dialog('close');        // close the dialog
    //     }
    // });
}



