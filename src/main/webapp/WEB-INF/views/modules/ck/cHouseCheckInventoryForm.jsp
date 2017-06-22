<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>盘点抄帐管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
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
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/ck/cHouseCheckInventory/">盘点抄帐列表</a></li>
		<li class="active"><a href="${ctx}/ck/cHouseCheckInventory/form?id=${cHouseCheckInventory.id}">盘点抄帐<shiro:hasPermission name="ck:cHouseCheckInventory:edit">${not empty cHouseCheckInventory.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="ck:cHouseCheckInventory:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="cHouseCheckInventory" action="${ctx}/ck/cHouseCheckInventory/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">仓库：</label>
			<div class="controls">
				<form:select path="house.id" cssClass="required">
					<form:option value="" label="请选择"></form:option>
					<form:options items="${houseList}" itemValue="id" itemLabel="name" htmlEscape="false"></form:options>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
	<div class="control-group">
		<label class="control-label">商品分类：</label>
			<div class="controls">
				<sys:treeselect id="gclassId" name="gclass.id" value="${cHouseCheckInventory.gclass.id}" labelName="gclass.name" labelValue="${cHouseCheckInventory.gclass.name}"
								title="商品分类选择" url="/ck/cGclass/treeData" cssClass="input-medium"/>
			</div>
	</div>
		<div class="control-group">
			<label class="control-label">盘点时间：</label>
			<div class="controls">
				<input name="checkDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${cHouseCheckInventory.checkDate}" pattern="yyyy-MM-dd"/>"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="ck:cHouseCheckInventory:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>