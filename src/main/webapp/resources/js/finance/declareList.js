	var urls = "";
	$(document).ready(function() { 
		//当选择完文件后的操作
		$("input",$("#uploadFileid").next("span")).change(function(){
			upload1();
		});
		//查询列表信息
		queryAction(0,'','');
		//改变数据为删除状态
		$("#declareDel").val(1);
		//隐藏恢复按钮
		$("div.datagrid-toolbar [id ='recovery']").eq(0).hide();

        // 批量下载方法
        $("#downloadAllFiles").click(function(){
            // 获取操作对应的菜单ID
            urls = getPowerMenuId();

            // 获取fileList下的tr个数
            var fileLen=$("#fileList").find("tr").length;
            if(fileLen>0){
                // 获取所有上传文件id
                var fileids="";
                for(var i=0;i<fileLen;i++){
                    if(fileids==""){
                        fileids=$("#fileIds"+i).val();
                    }else{
                        fileids=fileids+","+$("#fileIds"+i).val();
                    }
                }
                window.open("common/downloadAllFiles?fileIds="+fileids+urls);
            }
        });
	});
	
	//检索
	function doSearch(){
		//获取搜索的name值
		var getName= $('#searchboxId').searchbox('getName');
		//获取搜索框中的内容
		var getValue= $('#searchboxId').searchbox('getValue');
		queryAction(0,getName,getValue);
	}
	
	//调用后台方法
	function queryAction(accountDel,getName,getValue){
		// 获取操作对应的菜单ID
		urls = getPowerMenuId();
        // 根据用户角色和和用户账号查询列表
        var queryParam="";
        var baseInfoId=$("#baseInfoId").val();
        var role=$("#role").val();
        if(role==3 || role==4){
            queryParam="&baseInfoId="+baseInfoId;
        }
		//1.首先获取当前页号和每页显示条数
		$("#dg").datagrid({  
			url:'faDeclare/declareList?declareDel='+accountDel+"&getName="+getName+"&getValue="+getValue +queryParam+ urls,
			//加载列表数据
			 columns : [ [ {
		         field : 'ck',
		         checkbox:true,
		         align : 'center',
		     },{
				field : 'projectId',
                title : 'ID',
                width : 240,
                align : 'center',
                hidden: 'hidden'
            },{
                field : 'projectName',
                title : '项目名称',
                width : 80,
                sortable: true,
                formatter : function(value,row,index){  
               	if(value!=null && value!=''){
    				    return value
    				 } else{return '--'}
                    // 'a'+'<br/>'+'b'
                }
            },{
                 field : 'projectAmount',
                 title : '项目总金额',
                 width : 80,
                 sortable: true,
                 formatter : function(value,row,index){
                     if(value!=null && value!=''){
                         return value
                     } else{return '--'}
                 }
             },{
                 field : 'typeAMoney',
                 title : '调研、访问与论文发表',
                 width : 80,
                 sortable: true,
                 formatter : function(value,row,index){
                     if(value!=null && value!=''){
                     	 console.log(row);
                         return '总金额：'+value +'<br/>'+'报销金额：'+row.rbAmountA+'<br/>'+'剩余金额：'+row.surplusAmountA
                     } else{return '--'}
                 }
             }, {
                 field : 'typeBMoney',
                 title : '学术会议',
                 width : 80,
                 sortable: true,
                 formatter : function(value,row,index){
                     if(value!=null && value!=''){
                         return '总金额：'+value +'<br/>'+'报销金额：'+row.rbAmountB+'<br/>'+'剩余金额：'+row.surplusAmountB
                     } else{return '--'}
                 }
             }, {
                 field : 'typeCMoney',
                 title : '数据、图书及打印',
                 width : 80,
                 sortable: true,
                 formatter : function(value,row,index){
                     if(value!=null && value!=''){
                         return '总金额：'+value +'<br/>'+'报销金额：'+row.rbAmountC+'<br/>'+'剩余金额：'+row.surplusAmountC
                     } else{return '--'}
                 }
             },
            //      {
            //      field : 'typeDMoney',
            //      title : '项目金额D',
            //      width : 80,
            //      sortable: true,
            //      formatter : function(value,row,index){
            //          if(value!=null && value!=''){
            //              return '总金额：'+value +'<br/>'+'报销金额：'+row.rbAmountD+'<br/>'+'剩余金额：'+row.surplusAmountD
            //          } else{return '--'}
            //      }
            // },
                 {
                 field : 'projectSurplusAmount',
                 title : '合计剩余',
                 width : 80,
                 sortable: true,
                 formatter : function(value,row,index){
                     if(value!=null && value!=''){
                         return value
                     } else{return '--'}
                     // 'a'+'<br/>'+'b'
                 }
             },{
                 field : 'projectTime',
                 title : '立项时间',
                 width : 80,
                 sortable :true,
                 formatter : function(value){
                     if(value!=null && value!=''){
                         var date = new Date(value);
                         var y = date.getFullYear();
                         var m = date.getMonth() + 1;
                         var d = date.getDate();
                         return y + '-' +m + '-' + d;
                     }else{return '--'}
                 }
             },{
                 field : 'endTime',
                 title : '结项时间',
                 width : 80,
                 sortable :true,
                 formatter : function(value){
                     if(value!=null && value!=''){
                         var date = new Date(value);
                         var y = date.getFullYear();
                         var m = date.getMonth() + 1;
                         var d = date.getDate();
                         return y + '-' +m + '-' + d;
                     }else{return '--'}
                 }
             }
            ] ],
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

	
	var num=0;
	var url;
	
	function excel(){
		$("#dlgExcel").dialog("open");
	}

    function excelAll(){
        $("#dlgExcelAll").dialog("open");
    }

	//导出excle
	function exportExcel(){
        var baseInfoId=$("#baseInfoId").val();
        var paramForExcel = "";
        var role=$("#role").val();
        if(role==3 || role==4){
            paramForExcel="&baseInfoId="+baseInfoId;
        }
		$("#dlgExcel").dialog("close");
		//获取搜索的name值
		var getName= $('#searchboxId').searchbox('getName');
		//获取搜索框中的内容
		var getValue= $('#searchboxId').searchbox('getValue');
		getValue=getValueFunction(getName, getValue);
		
		var url = "ReportServer?reportlet=faDeclareRecord.cpt&" + getName + "=" + getValue +paramForExcel+ "&format=excel&extype=simple&__filename__=" + cjkEncode("个人报销履历列表");
		window.open(url);
}

    function exportExcelAll(){
        var baseInfoId=$("#baseInfoId").val();
        var paramForExcel = "";
        var role=$("#role").val();
        if(role==3 || role==4){
            paramForExcel="&baseInfoId="+baseInfoId;
        }
        $("#dlgExcelAll").dialog("close");
        //获取搜索的name值
        var getName= $('#searchboxId').searchbox('getName');
        //获取搜索框中的内容
        var getValue= $('#searchboxId').searchbox('getValue');
        getValue=getValueFunction(getName, getValue);

        var url = "ReportServer?reportlet=faGetAll.cpt&" + getName + "=" + getValue +paramForExcel+ "&format=excel&extype=simple&__filename__=" + cjkEncode("个人资金统计列表");
        window.open(url);
    }
	function getValueFunction(name, value){
        return value;
    }

    // 上传材料
    function upload(){
        //获取选择上传数据id
        var row = $('#dg').datagrid('getSelected');
        //表名
        var tableName="fa_declare_record"
        $("#relevanceId").val(row.projectId);
        $("#tableName").val(tableName);
        uploadList(tableName,row.projectId);
    }

    function uploadList(tableName,projectId){
        // 获取操作对应的菜单ID
        urls = getPowerMenuId();

        var queryParam="";
        var baseInfoId=$("#baseInfoId").val();
        var role=$("#role").val();
        if(role==3 || role==4){
            queryParam="&baseInfoId="+baseInfoId;
        }
        console.log("baseInfoId"+baseInfoId);
        console.log(role);
        console.log(queryParam);
        $('#fileList').empty();
        // 首先根据id查询附件
        $.ajax({
            url:"common/fileList?relevanceId="+projectId+"&tableName="+tableName + queryParam + urls,
            type:"post",
            success:function (data){
                $("#dlgUpload").dialog({
                    //modal:true,遮罩层
                    left: 141,
                    onClose: function(){
                        win.window('destroy');//关闭即销毁
                    },
                });
                if(data!=null && data.length>0){
                	console.log(data);
                    for(var i=0;i<data.length;i++){
                        var t=format(data[i].fileCreateTime);
                        $("#fileList").append(
                            "<tr>" +
                            "<td>" +
                            "<input type='hidden' id='fileIds"+i+"' value='"+data[i].fileId+"'>"+
                            "<input type='text' value='"+data[i].fileName+"' style='width:100%;border: 1px solid #BDC7D8;' readonly>"+
                            "</td>" +
                            "<td>" +
                            "<select id='selector"+i+"' value='"+data[i].fileType+"' panelMaxHeight='70px' style='width:100%;border: 1px solid #BDC7D8;' readonly>"+
                            "<option value=''>无</option>" +
                            "<option value='A'>调研、访问与论文发表</option>" +
                            "<option value='B'>学术会议</option>" +
                            "<option value='C'>数据、图书及打印</option>" +
                            // "<option value='D'>项目金额D</option>" +
                            "</select>"+
                            "</td>" +
                            "<td width='10px'>" +
                            "<input type='text' value='"+data[i].fileContent+"'style='border: none #BDC7D8;width: 100%' readonly>"+
                            "</td>" +
                            "<td>" +
                            ""+t+""+
                            "</td>" +
                            "<td width='10px'>" +
                            "<input type='text' value='"+data[i].status+"'style='border: none #BDC7D8;width: 100%' readonly>"+
                            "</td>" +
                            "<td width='30px'>" +
                            "<a href='javascript:void(0)' onclick='downUploadFile(\""+data[i].fileId+"\")'>下载</a>&nbsp;"+
                            "<a href='javascript:void(0)' onclick='viewFile(\""+data[i].filePath+"\")'>查看</a>&nbsp;"+
                            "<a href='javascript:void(0)' onclick='delFile(\""+data[i].fileId+"\")'>删除</a>"+
                            "</td>" +
                            "</tr>");
                        $("#selector"+i).val(data[i].fileType);
                    }
                }
                $("#dlgUpload").dialog("open");

                // 修改按钮样式
                $(window.parent.document).find("div[id='tabs']").find("div:first").next().children().each(function(){
                    var styles= $(this).attr("style");
                    if(styles.indexOf("block") >= 0){
                        var documents = $($($(this).find("iframe"))[0].contentDocument).find("span[class='fileManage']");
                        $($($($(documents).next().children()[2]).children()[0]).children()[0]).attr("style", "margin-top: 0;");
                        $($($(documents).next().children()[0]).children()[0]).attr("style", "margin-top: 0;");
                    }
                });
            }
        });
    }

    // 选择完文件后,触发事件
    function upload1(){
        $($($("#dlgUpload1").prev().children()[1]).find("a")).bind("click",function(){
            $($("input",$("#uploadFileid").next("span"))[1]).val("");
        });
        $('#dlgUpload1').dialog('open');
        //获取上传文件路径
        var fileUrl=$("#uploadFileid").filebox("getValue");
        //获取文件名
        var strFileName=fileUrl.replace(/^.+?\\([^\\]+?)(\.[^\.\\]*?)?$/gi,"$1");  //正则表达式获取文件名，不带后缀
        //获取文件后缀
        var FileExt=fileUrl.replace(/.+\./,"");   //正则表达式获取后缀
        $("#fileFullName").textbox().textbox("setValue",strFileName+"."+FileExt);
    }

    function delUploadFile(){
        $('#dlgUpload1').dialog('close');
        $($("input",$("#uploadFileid").next("span"))[1]).val("");
    }

    //上传文件
    function saveUploadFile(){
        // 获取操作对应的菜单ID
        urls = getPowerMenuId();

        var fileType=$("#fileType").val();//选择的类别
        var fileContent=$("#applyAmount").val();//文件描述
        var fileFullName=$("#fileFullName").val()// 获取文件名称

        var queryParam="";
        var baseInfoId=$("#baseInfoId").val();
        var role=$("#role").val();
        console.log("baseInfoId"+$("#baseInfoId").val())
        // alert(baseInfoId);
        if(role==3 || role==4){
            queryParam="&baseInfoId="+baseInfoId;
        }

        if(fileFullName!=""){
            $("#fmUpload").ajaxSubmit({
                type: "POST",
                url:"common/uploadFile?pathWay=declareList/sr0&fileFullName="+fileFullName+"&tableName=fa_declare_record&fileType="+fileType+"&relevanceId=1&fileContent="+fileContent+queryParam + urls,
                dataType: "json",
                success: function(data){
                    if(data.status == 1){
                        // 关闭附件信息弹框
                        $('#dlgUpload1').dialog('close');
                        var tableName=$("#tableName").val();
                        var baseInfoId=$("#relevanceId").val();
                        uploadList(tableName,baseInfoId);
                        // $('#dg').datagrid('reload');
                        /* $.messager.show({
                             title: '提示',
                         });*/
                        $($("input",$("#uploadFileid").next("span"))[1]).val("");
                    } else {
                        $.messager.show({
                            title: '提示',
                            msg: data.text
                        });
                    }
                }
            });
        }
    }


    //下载文件
    function downUploadFile(fileId){
        // 获取操作对应的菜单ID
        urls = getPowerMenuId();
        window.open("common/downloadFile?fileId="+fileId + urls);
    }
    //删除文件
    function delFile(fileId){
        // 获取操作对应的菜单ID
        urls = getPowerMenuId();
            $.messager.confirm('确认',"确认要删除吗?",function(r){
                if (r){
                    $.post('common/delFileForDeclare?fileId='+fileId + urls,function(data){
                        if(data.status == 1){
                            $('#dg').datagrid('reload');
                            var tableName=$("#tableName").val();
                            var baseInfoId=$("#relevanceId").val();
                            uploadList(tableName,baseInfoId);
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

    }
