<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>规格管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//自定义验证
		jQuery.validator.addMethod("isspec", function(value, element) {
		  var regPhone =/^1+\*[1-9]\d*$/;
		  var reg = /^1+\*[1-9]\d*\*[1-9]\d*$/;
		  return  regPhone.test(value) || reg.test(value);
	}, "请正确填写规格1*10或者1*100*1000");
        			$("#no").focus();
        			$("#inputForm").validate({
        				rules: {
                                 	name: {remote: "${ctx}/ck/cSpec/checkName?id="+$("#id").val(),
                          				isspec : true
                                  					}
                                  				},
                                  	messages: {
                                  		name: {remote: "规格名已存在"},
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
		<li><a href="${ctx}/ck/cSpec/">规格列表</a></li>
		<li class="active"><a href="${ctx}/ck/cSpec/form?id=${cSpec.id}">规格<shiro:hasPermission name="ck:cSpec:edit">${not empty cSpec.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="ck:cSpec:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="cSpec" action="${ctx}/ck/cSpec/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">规格名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="ck:cSpec:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>