<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>收款管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
                    submitForm();
                    closeLoading();
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
		function submitForm() {
			$.ajax({
			    cache:true,
				dataType:"text",
				type:"POST",
				data:$("#inputForm").serialize(),
				url:$("#inputForm").attr("action"),
				async:false,
				success:function (data) {
			        if(data!='') {
			            var datas = data.split(",");
                        var url = "${ctx}/ck/cRkckddinfo/saveCgInfo?receipt.id="+datas[0]+"&cHouse.id="+datas[1];
                        if(top!=self){
                            window.parent.setMainFrame(url);
                        }else {
                            window.location.href = url;
                        }
                        top.$.jBox.close(true);
                    }else {
                        top.$.jBox.tip("保存错误，请重新提交","系统提示","warning");
					}
                },
				error:function () {
                    top.$.jBox.tip("保存错误，请重新提交","系统提示","warning");
                }
			})
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
	</ul><br/>
	<form:form id="inputForm" modelAttribute="fReceipt" action="${ctx}/ck/cRkckddinfo/submitOrderSave" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">出货仓库：</label>
			<div class="controls">
				<form:select path="houseId" class="required">
					<form:option value="" label="请选择"></form:option>
					<form:options items="${houseList}" itemLabel="name" itemValue="id" htmlEscape="false"></form:options>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">来往单位：</label>
			<div class="controls">
				<form:select path="travelUnit.id" class="required">
					<form:option value="" label="请选择"></form:option>
					<form:options items="${storeList}" itemLabel="name" itemValue="id" htmlEscape="false"></form:options>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">来往帐号：</label>
			<div class="controls">
				<form:input path="travelAccount" htmlEscape="false" maxlength="100" class="input-xlarge"/>
				<span class="help-inline"><font color="red">*</font> </span>
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
			<label class="control-label">收款帐号：</label>
			<div class="controls">
				<form:input path="receiptAccount" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">收款方式：</label>
			<div class="controls">
				<form:select path="receiptMode" cssClass="required">
					<form:option value="" label="请选择"></form:option>
					<form:options items="${fns:getDictList('expenMode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">合同金额：</label>
			<div class="controls">
				<form:input path="htje" htmlEscape="false" readonly="true" maxlength="20" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">实收金额：</label>
			<div class="controls">
				<c:if test="${toDiscount}"><form:input path="je" htmlEscape="false" maxlength="20" class="input-xlarge required"/></c:if>
				<c:if test="${!toDiscount}"><form:input path="je" htmlEscape="false" readonly="true" maxlength="20" class="input-xlarge required"/></c:if>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">收款日期：</label>
			<div class="controls">
				<input name="receiptDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					   value="<fmt:formatDate value="${fReceipt.receiptDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="ck:cCkinfo:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="top.$.jBox.close(true)"/>
		</div>
	</form:form>
</body>
</html>