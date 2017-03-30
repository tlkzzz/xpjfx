<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>供应商管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
			rules: {
                      name: {
                         remote: "${ctx}/ck/cSupplier/checkName?id="+$("#id").val(),
                         },
                       code: {
                        remote: "${ctx}/ck/cSupplier/checkcode?id="+$("#id").val(),
                                },
                       	},
               messages: {
                       name: {remote: "供应商名称已存在"},
                       code: {remote: "供应商编码已存在"},
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
		<li><a href="${ctx}/ck/cSupplier/">供应商列表</a></li>
		<li class="active"><a href="${ctx}/ck/cSupplier/form?id=${cSupplier.id}">供应商<shiro:hasPermission name="ck:cSupplier:edit">${not empty cSupplier.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="ck:cSupplier:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="cSupplier" action="${ctx}/ck/cSupplier/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
        			<label class="control-label">供应商名称：</label>
        			<div class="controls">
        				<form:input path="name" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
        				<span class="help-inline"><font color="red">*</font> </span>
        			</div>
        		</div>
		<div class="control-group">
			<label class="control-label">供应商编码：</label>
			<div class="controls">
				<form:input path="code" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
        	<label class="control-label">电话：</label>
        	<div class="controls">
        <form:input path="dh" phone="true" htmlEscape="false" maxlength="64" class="input-xlarge "/>
        		</div>
        	</div>
		<div class="control-group">
        						<label class="control-label">开户账号：</label>
        						<div class="controls">
        							<form:input path="khzh" creditcard="true" htmlEscape="false" maxlength="64" class="input-xlarge "/>
        						</div>
        					</div>
       <div class="control-group">
			<label class="control-label">身份证照:</label>
			<div class="controls">
				<form:hidden path="sfzz" htmlEscape="false" maxlength="255" class="input-xlarge"/>
				<sys:ckfinder input="sfzz" type="images" uploadPath="/ck/cSupplier" selectMultiple="true"/>
				<!--<span class="help-inline">建议Logo大小：1000 × 145（像素）</span>-->
			</div>
		</div>
	 <div class="control-group">
    			<label class="control-label">营业执照:</label>
    			<div class="controls">
    				<form:hidden path="yyzz" htmlEscape="false" maxlength="255" class="input-xlarge"/>
    				<sys:ckfinder input="yyzz" type="images" uploadPath="/ck/cSupplier"/>
    			</div>
    		</div>
		<div class="control-group">
			<label class="control-label">供应商简称：</label>
			<div class="controls">
				<form:input path="gysjc" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">联系人：</label>
			<div class="controls">
				<form:input path="lxr" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">详细地址：</label>
			<div class="controls">
				<form:input path="xxdz" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">开户行：</label>
			<div class="controls">
				<form:input path="khh" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">开户人：</label>
			<div class="controls">
				<form:input path="khr" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">公司主页：</label>
			<div class="controls">
				<form:input path="gszy" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
        	<label class="control-label">电子邮件：</label>
        		<div class="controls">
        	<form:input path="email" email="true" htmlEscape="false" maxlength="64" class="input-xlarge "/>
        	</div>
        </div>
			<div class="control-group">
            						<label class="control-label">邮政编码：</label>
            						<div class="controls">
            							<form:input path="yzbm" ZipCode="true" htmlEscape="false" maxlength="64" class="input-xlarge "/>
            						</div>
            					</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="ck:cSupplier:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>