<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>账户管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {

//            jQuery.validator.addMethod("sss", function(value, element) {
//				return value!='';
//            }, "bankName不可为空！");

            jQuery.validator.addMethod("isspec", function(value, element) {
                var reg = /^\d{1,9}(\.\d{1,4})?$/;
                return  reg.test(value);
            }, "请输入最多4位小数的14位数值！");

            $("#no").focus();
            $("#inputForm").validate({

                rules: {
                    bankCode: {remote: "${ctx}/cw/fAccount/checkBankCode?id="+$("#id").val()},
                    accountBalance:{
                        isspec :true
                    },
//                    bankName:{sss:true},
//                    bankCode:{sss:true},
                },

                messages: {
                    bankCode: {remote: "银行卡号已存在"},
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
        function hide(){
            var accountType=document.getElementById("accountType").value;
            if(accountType!="1"){
                document.getElementById("DbankName").style.visibility = "hidden";
                document.getElementById("DbankName").style.height = "1px";
                document.getElementById("DbankCode").style.visibility = "hidden";
                document.getElementById("DbankCode").style.height = "1px";
                $("#bankName").removeClass("required");
                $("#bankCode").removeClass("required");
            }else if(accountType="1"){
                document.getElementById("DbankName").style.visibility = "visible";
                document.getElementById("DbankName").style.height = "30px";
                document.getElementById("DbankCode").style.visibility = "visible";
                document.getElementById("DbankCode").style.height = "30px";
                $("#bankName").attr("class","required");
                $("#bankCode").attr("class","required");
            }else if(accountType=""){
                document.getElementById("DbankName").style.visibility = "hidden";
                document.getElementById("DbankName").style.height = "1px";
                document.getElementById("DbankCode").style.visibility = "hidden";
                document.getElementById("DbankCode").style.height = "1px";
                $("#bankName").removeClass("required");
                $("#bankCode").removeClass("required");
            }

        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/cw/fAccount/">账户管理列表</a></li>
		<li class="active"><a href="${ctx}/cw/fAccount/form?id=${fAccount.id}">账户管理<shiro:hasPermission name="cw:fAccount:edit">${not empty fAccount.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="cw:fAccount:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="fAccount" action="${ctx}/cw/fAccount/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">账户类型：</label>
			<div class="controls">
				<form:select path="accountType" class="required" onclick="hide()" id="accountType">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('expenMode')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">账户名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="100"  class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">帐户余额：</label>
			<div class="controls">
				<form:input path="accountBalance"  number="true"  value="0" id="accountBalance" htmlEscape="false" class="input-xlarge " />
				<span class="help-inline" id="b"> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group"  id="DbankName">
			<label class="control-label">开户行：</label>
			<div class="controls">
				<form:input path="bankName" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group" id="DbankCode">
			<label class="control-label">银行卡号：</label>
			<div class="controls">
				<form:input path="bankCode"  htmlEscape="false" maxlength="100" class="input-xlarge " creditcard="true"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="cw:fAccount:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>