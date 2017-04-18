<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>供货上门</title>
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
        function setSpec(id) {
            if(id!=''){
                $.ajax({
                    url:"${ctx}/ck/cGoods/getGoods",
                    type:"POST",
                    dataType:"json",
                    data:{id:id},
                    success:function (data) {
                        if(data!=null){
                            $("#spec").text("规格："+data.spec.name);
                        }
                    }
                })
            }else {
                $("#spec").text("");
            }
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
	<li ><a href="${ctx}/ck/cHgoods/list">库存列表</a></li>
            <li class="active"><a href="${ctx}/ck/cCgzbinfo/form">采购入库</a></li>
    		<shiro:hasPermission name="ck:cHgoods:edit"><li><a href="${ctx}/ck/cHgoods/form">其他入库</a></li></shiro:hasPermission>
    		<shiro:hasPermission name="ck:cHgoods:edit"><li><a href="${ctx}/ck/cHgoods/move">移库</a></li></shiro:hasPermission>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="cCgzbinfo" action="${ctx}/ck/cCgzbinfo/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="state" value="2"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">仓库：</label>
			<div class="controls">
				<form:select path="house.id" cssclass="input-xlarge required">
					<form:option value="" label="请选择"></form:option>
					<form:options items="${houseList}" itemValue="id" itemLabel="name" htmlEscape="false"></form:options>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商品：</label>
			<div class="controls">
				<form:select path="goods.id" cssclass="input-xlarge required" onchange="setSpec($(this).val())">
					<form:option value="" label="请选择"></form:option>
					<form:options items="${goodsList}" itemValue="id" itemLabel="name" htmlEscape="false"></form:options>
				</form:select><span id="spec"></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">供应商：</label>
			<div class="controls">
				<form:select path="rkinfo.supplier.id" cssclass="input-xlarge required">
					<form:option value="" label="请选择"></form:option>
					<form:options items="${goodsList}" itemValue="id" itemLabel="name" htmlEscape="false"></form:options>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">采购数量：</label>
			<div class="controls">
				<form:input path="nub" htmlEscape="false" maxlength="11" class="input-xlarge " required="true"/>
					<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">价格：</label>
			<div class="controls">
				<form:input path="jg" htmlEscape="false" class="input-xlarge " required="true"/>
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
			<shiro:hasPermission name="ck:cCgzbinfo:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>