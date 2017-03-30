<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>仓库管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
    		$(document).ready(function() {
            			$("#no").focus();
            			$("#inputForm").validate({
            		rules: {
            			    name: {
            				   remote: "${ctx}/ck/cHouse/checkName?id="+$("#id").val(),
            					  },
            				code: {
                               remote: "${ctx}/ck/cHouse/checkcode?id="+$("#id").val(),
                                   },
            				},
            				messages: {
            					name: {remote: "仓库名已存在"},
            					code: {remote: "仓库编号已存在"},
            				},
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
		<li><a href="${ctx}/ck/cHouse/">仓库列表</a></li>
		<li class="active"><a href="${ctx}/ck/cHouse/form?id=${cHouse.id}">仓库<shiro:hasPermission name="ck:cHouse:edit">${not empty cHouse.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="ck:cHouse:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="cHouse" action="${ctx}/ck/cHouse/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="state" value="0"/>
		<sys:message content="${message}"/>

			<div class="control-group">
        			<label class="control-label">仓库名称:</label>
        			<div class="controls">
        				<form:input path="name" htmlEscape="false" maxlength="50" class="input-xlarge required"/>
        				<span class="help-inline"><font color="red">*</font> </span>
        			</div>
        		</div>

		<div class="control-group">
			<label class="control-label">仓库编号：</label>
			<div class="controls">
				<form:input path="code" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="ck:cHouse:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>