<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>入库管理</title>
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
		<li><a href="${ctx}/ck/cHgoods/list">库存列表</a></li>
		 <shiro:hasPermission name="ck:cCgzbinfo:edit"><li><a href="${ctx}/ck/cCgzbinfo/form">采购入库</a></li></shiro:hasPermission>
		<li class="active"><a href="${ctx}/ck/cHgoods/form?id=${cHgoods.id}">其他入库</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="cHgoods" action="${ctx}/ck/cHgoods/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="rkState" value="1"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">商品：</label>
			<div class="controls">
				<form:select path="goods.id" class="required">
					<form:option value="" label="请选择"></form:option>
					<form:options items="${goodsList}" itemLabel="name" itemValue="id" htmlEscape="false"></form:options>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">仓库：</label>
			<div class="controls">
				<form:select path="house.id" class="required">
					<form:option value="" label="请选择" ></form:option>
					<form:options items="${houseList}" itemLabel="name" itemValue="id" htmlEscape="false"></form:options>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">成本价：</label>
			<div class="controls">
				<form:input path="cbj" class="required" number="true" maxlength="12" htmlEscape="true"></form:input>
				<span class="help-inline"><font color="red">* 保留两位小数</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">数量：</label>
			<div class="controls">
				<form:input path="nub" class="required" digits="true" maxlength="11" htmlEscape="true"></form:input>
				<span class="help-inline"><font color="red">* 最小单位计量</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">预警数：</label>
			<div class="controls">
				<form:input path="yjnub" digits="true" maxlength="11" htmlEscape="true"></form:input>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="ck:cHgoods:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>