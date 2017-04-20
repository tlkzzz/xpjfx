<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>固定资产登记管理</title>
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
		<li><a href="${ctx}/cw/fFixedAssets/">固定资产登记列表</a></li>
		<li class="active"><a href="${ctx}/cw/fFixedAssets/form?id=${fFixedAssets.id}">固定资产登记<shiro:hasPermission name="cw:fFixedAssets:edit">${not empty fFixedAssets.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="cw:fFixedAssets:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="fFixedAssets" action="${ctx}/cw/fFixedAssets/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">资产名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
                <span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">购买日期：</label>
			<div class="controls">
				<input name="payDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${fFixedAssets.payDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">折算金额：</label>
			<div class="controls">
				<form:input path="total" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
        			<label class="control-label">归属部门:</label>
        			<div class="controls">
                        <sys:treeselect id="office" name="office.id" value="${user.office.id}" labelName="office" labelValue="${user.office.name}"
        					title="部门" url="/sys/office/treeData?type=2"  notAllowSelectParent="true"/>
        			</div>
        		</div>
		 <div class="control-group">
                        <label class="control-label">负责人：</label>
                        <div class="controls">
                            <sys:treeselect id="user" name="user.id" value="${testData.user.id}" labelName="fzr" labelValue="${testData.user.name}"
                                            title="用户" url="/sys/office/treeData?type=3" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
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
			<shiro:hasPermission name="cw:fFixedAssets:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>