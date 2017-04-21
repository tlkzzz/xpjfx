<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>仓库总盘点管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
                rules:{
                    "house.id":{
                        remote:{url:'${ctx}/ck/cHouseComponentCheck/checkHouse'}
                    }
                },
                messages:{
                    "house.id":{
                        remote:"已进行分量盘点"
                    }
				},
				submitHandler: function(form){
                    checkInventory();
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
        function checkInventory() {
            var houseId = $("#houseId").val();
            if(houseId!=""){
                $.ajax({
                    url:"${ctx}/ck/cHouseCheckInventory/checkHouse",
                    type:"POST",
                    async:false,
                    dataType:"text",
                    data:{'house.id':houseId},
                    success:function (data) {
                        if(data=="true"){
                            top.jBox.tip("请先进行抄帐后再进行盘点！","系统提示","warning");
                            $("#inputForm").removeAttr("action");
                        }else {
                            $("#inputForm").attr("action","${ctx}/ck/cHouseCheck/save");
                        }
                    }
                });
            }
        };
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/ck/cHouseCheck/">仓库总盘点列表</a></li>
		<li class="active"><a href="${ctx}/ck/cHouseCheck/form?id=${cHouseCheck.id}">仓库总盘点<shiro:hasPermission name="ck:cHouseCheck:edit">${not empty cHouseCheck.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="ck:cHouseCheck:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="cHouseCheck" action="${ctx}/ck/cHouseCheck/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">仓库：</label>
			<div class="controls">
				<form:select path="house.id" id="houseId" cssClass="required" onchange="checkInventory()">
					<form:option value="" label="请选择"/>
					<form:options items="${houseList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="ck:cHouseCheck:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>