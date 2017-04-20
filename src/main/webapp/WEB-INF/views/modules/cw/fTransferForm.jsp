<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>转账管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$.validator.addMethod("noEqual",function (value,element,params) {
                if(value==$(params).val())return false;return true;
            },"转入账户不能为转出账户");
			$("#inputForm").validate({
                rules:{
                    inAccount:{
                        noEqual:"#outAccount"
                    }
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
		<li><a href="${ctx}/cw/fTransferAccount/?transferType=${fTransferAccount.transferType}">转账列表</a></li>
		<li class="active"><a href="${ctx}/cw/fTransferAccount/transferForm?transferType=${fTransferAccount.transferType}">转账</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="fTransferAccount" action="${ctx}/cw/fTransferAccount/receiptSave" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="transferType"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">科目编码：</label>
			<div class="controls">
				<sys:treeselect id="subjectCode" name="subjectCode.id" value="${fTransferAccount.subjectCode.id}" labelName="subjectCode.name" labelValue="${fTransferAccount.subjectCode.name}"
								title="科目编码" url="/ck/cKm/treeData" cssClass="required" allowClear="true"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">转出账户：</label>
			<div class="controls">
				<form:select path="fAccount.name" class="input-xlarge">
					<form:option value="" label="请选择"></form:option>
					<form:options items="${accountList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">转入账户：</label>
			<div class="controls">
				<form:select path="fAccount.name" class="input-xlarge">
					<form:option value="" label="请选择"></form:option>
					<form:options items="${accountList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">转出金额：</label>
			<div class="controls">
				<form:input path="transMoney" htmlEscape="false" number="true" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">转账日期：</label>
			<div class="controls">
				<input name="transferDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${fTransferAccount.transferDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">经手人：</label>
			<div class="controls">
				<sys:treeselect id="jsr" name="jsr.id" value="${fTransferAccount.jsr.id}" labelName="jsr.name" labelValue="${fTransferAccount.jsr.name}"
								title="经手人" url="/sys/office/treeData?type=3" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
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
			<shiro:hasPermission name="cw:fTransferAccount:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>