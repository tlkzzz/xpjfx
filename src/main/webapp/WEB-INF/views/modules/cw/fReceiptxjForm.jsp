<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>现金费用单</title>
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
		<li><a href="${ctx}/cw/fReceipt/list?receiptType=${fReceipt.receiptType}">现金费用列表</a></li>
			<li class="active"><a href="${ctx}/cw/cCkinfo/xjform?receiptType=${fReceipt.receiptType}">现金费用单</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="fReceipt" action="${ctx}/cw/fReceipt/xjsave" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">收款日期：</label>
			<div class="controls">
				<input name="receiptDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${fReceipt.receiptDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">单据编号：</label>
			<div class="controls">
				<form:input path="receiptCode" htmlEscape="false" maxlength="100" class="input-xlarge " required="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">收款方式：</label>
			<div class="controls">
                           <form:select path="receiptMode" >
                          <form:option value="" label="请选择"/>
                         <form:options items="${fns:getDictList('receiptMode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                         </form:select>
                                     </div>
		</div>
		 <div class="control-group">
                <label class="control-label">经手人：</label>
                <div class="controls">
                    <sys:treeselect id="user" name="user.id" value="${testData.user.id}" labelName="jsr" labelValue="${testData.user.name}"
                                    title="用户" url="/sys/office/treeData?type=3" cssClass="input-small" allowClear="true" notAllowSelectParent="true" />
                    <span class="help-inline"><font color="red">*</font> </span>
                </div>
            </div>
		<div class="control-group">
			<label class="control-label">合同金额：</label>
			<div class="controls">
				<form:input path="htje" htmlEscape="false" class="input-xlarge " required="true"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
                <label class="control-label">科目编码：</label>
                <div class="controls">
                    <sys:treeselect id="parent" name="parent.id" value="${cKm.parent.id}" labelName="subjectCode" labelValue="${cKm.parent.name}"
                                    title="科目编码" url="/ck/cKm/treeData" extId="${cKm.id}" cssClass="" allowClear="true"/>
                </div>
            </div>
		<div class="control-group">
			<label class="control-label">收款金额：</label>
			<div class="controls">
				<form:input path="je" htmlEscape="false" class="input-xlarge " required="true"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">附加说明：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="cw:fReceipt:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>