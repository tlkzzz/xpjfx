<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>账户管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/cw/fAccount/">账户管理列表</a></li>
		<shiro:hasPermission name="cw:fAccount:edit"><li><a href="${ctx}/cw/fAccount/form">账户管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="fAccount" action="${ctx}/cw/fAccount/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>账户名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>账户名称</th>
				<th>创建时间</th>
				<th>备注</th>
				<shiro:hasPermission name="cw:fAccount:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="fAccount">
			<tr>
				<td><a href="${ctx}/cw/fAccount/form?id=${fAccount.id}">
					${fAccount.name}
				</a></td>
				<td>
					<fmt:formatDate value="${fAccount.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fAccount.remarks}
				</td>
				<shiro:hasPermission name="cw:fAccount:edit"><td>
    				<a href="${ctx}/cw/fAccount/form?id=${fAccount.id}">修改</a>
					<a href="${ctx}/cw/fAccount/delete?id=${fAccount.id}" onclick="return confirmx('确认要删除该账户管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>