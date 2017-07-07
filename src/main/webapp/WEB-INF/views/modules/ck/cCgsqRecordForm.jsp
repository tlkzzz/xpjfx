<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>订单申请记录表管理</title>
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

        function getld(){
            var goods = $("#goods").val();
            if(goods==""){
                $("#spec").text("");
                return ;
			}
            $.ajax({
                    url:"${ctx}/ck/cCgsqRecord/ldlist",
                    type:"POST",
                    dataType:"json",
                    data:{goods:goods},
                    success:function(data) {
                        if(data==false || data==""){
                            $("#goods").text("");
						}else {
                    // 转换类型
							var goods = eval(data);
                            $("#spec").text(goods.spec.name);
						}

                    }
                }
            )};
	</script>
	<inp
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/ck/cCgsqRecord/">订单申请记录表列表</a></li>
		<li class="active"><a href="${ctx}/ck/cCgsqRecord/form?id=${cCgsqRecord.id}">订单申请记录表<shiro:hasPermission name="ck:cCgsqRecord:edit">${not empty cCgsqRecord.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="ck:cCgsqRecord:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="cCgsqRecord" action="${ctx}/ck/cCgsqRecord/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">商品：</label>
			<div class="controls">
				<form:select path="goods.id" required="true" onchange="getld()" id="goods">
					<form:option value="" label="请选择"></form:option>
					<form:options items="${goodsList}" itemLabel="name" itemValue="id" htmlEscape="false"></form:options>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">规格：</label>
			<div class="controls">
				<span id="spec"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">数量：</label>
			<div class="controls">
				<form:input path="nub" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="ck:cCgsqRecord:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>