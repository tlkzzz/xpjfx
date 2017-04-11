<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>优惠表管理</title>
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
		<li><a href="${ctx}/cw/fDiscount/">优惠表列表</a></li>
		<li class="active"><a href="${ctx}/cw/fDiscount/form?id=${fDiscount.id}">优惠表<shiro:hasPermission name="cw:fDiscount:edit">${not empty fDiscount.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="cw:fDiscount:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="fDiscount" action="${ctx}/cw/fDiscount/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">优惠金额：</label>
			<div class="controls">
				<form:input path="yhje" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">类型：</label>
			<div class="controls">
				<form:input path="lx" htmlEscape="false" maxlength="1" class="input-xlarge "/>
			</div>
		</div>
		   <div class="control-group">
                                 <label class="control-label">客户名称：</label>
                                 <div class="controls">
                                 <form:select path="storeid.id" >
                                <form:options items="${cStorelist}" itemLabel="name" itemValue="id" htmlEscape="false"/>
                               </form:select>
                              </div>
                      </div>
		 <div class="control-group">
                                   <label class="control-label">订单编号：</label>
                                     <div class="controls">
                                      <form:select path="ddid.id" >
                                   <form:options items="${cRkckddinfolist}" itemLabel="ddbh" itemValue="id" htmlEscape="false"/>
                                 </form:select>
                                      </div>
                              </div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:input path="remaks" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="cw:fDiscount:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>