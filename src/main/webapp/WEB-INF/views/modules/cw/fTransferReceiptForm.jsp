<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>转账调账管理</title>
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
		<li><a href="${ctx}/cw/fTransferAccount/?transferType=${fTransferAccount.transferType}">应付款${fTransferAccount.transferType eq '1'?'增加':'减少'}列表</a></li>
		<li class="active"><a href="${ctx}/cw/fTransferAccount/receiptForm?transferType=${fTransferAccount.transferType}">应付款${fTransferAccount.transferType=='1'?'增加':'减少'}</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="fTransferAccount" action="${ctx}/cw/fTransferAccount/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="transferType"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">订单选择：</label>
			<div class="controls">
				<form:select path="orderId" cssclass="required">
					<form:option value="" label="请选择"></form:option>
					<form:options items="${orderList}" itemLabel="ddbh" itemValue="id" htmlEscape="false"></form:options>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">科目编码：</label>
			<div class="controls">
				<sys:treeselect id="subjectCode" name="subjectCode.id" value="${fReceipt.subjectCode.id}" labelName="subjectCode.name" labelValue="${fReceipt.subjectCode.name}"
								title="科目编码" url="/ck/cKm/treeData" cssClass="required" allowClear="true"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">来往单位：</label>
			<div class="controls">
				<form:select path="travelUnit" class="required">
					<form:option value="" label="请选择"></form:option>
					<form:options items="${storeList}" itemLabel="name" itemValue="id" htmlEscape="false"></form:options>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">转出帐户：</label>
			<div class="controls">
				<form:input path="outAccount" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">转入账户：</label>
			<div class="controls">
				<form:input path="inAccount" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">交易金额：</label>
			<div class="controls">
				<form:input path="transMoney" htmlEscape="false" number="true" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">转账日期：</label>
			<div class="controls">
				<input name="transferDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${fTransferAccount.transferDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">经手人：</label>
			<div class="controls">
				<form:input path="jsr" htmlEscape="false" maxlength="64" class="input-xlarge "/>
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