<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>入库管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		var oldNub = ${cHgoods.nub};
		$(document).ready(function() {

		});
		function ajaxSubmit() {
			var nub = $("#nub").val();
			ret = /^[0-9]*[1-9][0-9]*$/;
			if(!ret.test(nub)){
                top.$.jBox.tip("只能输入正整数","系统提示","warning");
                return;
			}
			if(nub<0){
			    top.$.jBox.tip("数量不能小于0","系统提示","warning");
			    return;
			}
			$.ajax({
			    cache:true,
			    url:"${ctx}/ck/cHgoods/changeStoreSave",
				type:"POST",
				dataType:"text",
				data:$("#inputForm").serialize(),
				async:false,
				success:function (data) {
					if(data=="true"){
						top.$.jBox.tip("调整库存成功", "系统提示","warning");
                        top.$.jBox.close();
//                        top.parent.reloadFrom();
//                        $("#searchForm",document.parentWindow).submit();
//						parent.reloadFrom();
					}else {
                        top.$.jBox.tip(data, "系统提示","warning");
                        window.parent.reloadFrom();
					}
                }
			});
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
	</ul><br/>
	<form:form id="inputForm" modelAttribute="cHgoods" action="${ctx}/ck/cHgoods/changeStoreSave" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="unit"/><%--保存的是库存数量传到后台做对比--%>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="">仓库：${cHgoods.house.name}-->商品：${cHgoods.goods.name}</label>
		</div>
		<div class="control-group">
			<label class="control-label">现有库存：${cHgoods.nub}</label>
		</div>
		<div class="control-group">
			<label class="control-label">修改后库存：</label>
			<div class="controls">
				<form:input path="nub" id="nub" class="required" digits="true" maxlength="11" htmlEscape="true"></form:input>
				<span class="help-inline"><font color="red">* 最小单位计量</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="ck:cHgoods:edit"><input class="btn btn-primary" type="button" onclick="ajaxSubmit()" value="保 存"/>&nbsp;</shiro:hasPermission>
		</div>
	</form:form>
</body>
</html>