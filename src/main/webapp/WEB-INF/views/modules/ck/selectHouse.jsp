<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>仓库选择</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {

		});
		function submitForm() {
		    var id = $("#id").val();
            if(id!=''){
                var url = "${ctx}/ck/cRkckddinfo/saveCgInfo?cHouse.id="+id;
                if(top!=self){
                    window.parent.setMainFrame(url);
                }else {
                    window.location.href = url;
                }
                top.$.jBox.close(true);
            }else{
                top.$.jBox.tip("请选择仓库", "系统提示", "warning");
            }
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
	</ul><br/>
	<form:form id="inputForm" modelAttribute="house" method="post" class="form-horizontal">
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">仓库选择：</label>
			<div class="controls">
				<form:select path="id" id="id" class="required">
					<form:option value="" label="请选择"></form:option>
					<form:options items="${houseList}" itemLabel="name" itemValue="id" htmlEscape="false"></form:options>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="ck:cCkinfo:edit"><input id="btnSubmit" class="btn btn-primary" type="button" value="保 存" onclick="submitForm()"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="top.$.jBox.close(true)"/>
		</div>
	</form:form>
</body>
</html>