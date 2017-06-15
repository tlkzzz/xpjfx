<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>购物车管理</title>
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
		function closejBox() {
			top.$.jBox.close(true);
        }
        function submitForm() {
            $.ajax({
                cache: true,
                type: "POST",
				dataType: "json",
                url:"${ctx}/ck/cShop/save",
                data:$('#inputForm').serialize(),
                async: false,
                success: function(data) {
                    if(data) {
                        top.$.jBox.tip("保存成功","系统提示","warning");
                        window.location.reload();
                    }
                },
                error: function() {
                    top.$.jBox.tip("保存错误，请重新提交","系统提示","warning")
                }
            });
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ck/cShop/cgAdd">订单<shiro:hasPermission name="ck:cCginfo:edit">${not empty cShop.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="ck:cCginfo:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="cShop" action="${ctx}/ck/cShop/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="state"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">商品：</label>
			<div class="controls">
				<form:select path="goods.id" cssClass="required">
					<form:option value="" label="请选择"></form:option>
					<form:options items="${goodsList}" itemLabel="name" itemValue="id" htmlEscape="false"></form:options>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">需求时间：</label>
			<div class="controls">
				<input name="xqdate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					   value="<fmt:formatDate value="${cShop.xqdate}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd', minDate:'%y-%M-%d',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">数量：</label>
			<div class="controls">
				<form:input path="nub" htmlEscape="false" maxlength="11" digits="true" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">最小单位*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">金额：</label>
			<div class="controls">
				<form:input path="je" htmlEscape="false" number="true" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">优惠金额：</label>
			<div class="controls">
				<form:input path="yhje" htmlEscape="false" number="true" class="input-xlarge"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="button" value="保 存" onclick="submitForm()"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="closejBox()"/>
		</div>
	</form:form>
</body>
</html>