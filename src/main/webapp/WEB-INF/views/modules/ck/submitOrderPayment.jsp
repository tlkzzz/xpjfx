<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>付款管理</title>
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
	<form:form id="inputForm" modelAttribute="payment" action="${ctx}/ck/cRkckddinfo/submitOrderPaymentSave" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">仓库：</label>
			<div class="controls">
				<form:select path="houseId" class="required">
					<form:option value="" label="请选择"/>
					<form:options items="${houseList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">科目编码：</label>
			<div class="controls">
				<sys:treeselect id="subjectCode" name="subjectCode.id" value="${fPayment.subjectCode.id}" labelName="subjectCode.name" labelValue="${fPayment.subjectCode.name}"
								title="科目编码" url="/ck/cKm/treeData" cssClass="required" allowClear="true"/>
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
			<label class="control-label">合同金额：</label>
			<div class="controls">
				<form:input path="htje" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">实付金额：</label>
			<div class="controls">
				<form:input path="je" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">付款日期：</label>
			<div class="controls">
				<input name="paymentDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${fPayment.paymentDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">单据编号：</label>
			<div class="controls">
				<form:input path="paymentCode" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">付款帐号：</label>
			<div class="controls">
				<form:input path="paymentAccount" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">来往帐号：</label>
			<div class="controls">
				<form:input path="travelAccount" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">付款方式：</label>
			<div class="controls">
				<form:select path="paymentMode" cssClass="required">
					<form:option value="" label="请选择"></form:option>
					<form:options items="${fns:getDictList('paymentMode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
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
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="cw:fPayment:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>