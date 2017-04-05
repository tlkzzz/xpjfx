<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>待摊费用管理</title>
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
		<li class="active"><a href="${ctx}/cw/fPropaidExpenses/">待摊费用列表</a></li>
		<shiro:hasPermission name="cw:fPropaidExpenses:edit"><li><a href="${ctx}/cw/fPropaidExpenses/form">待摊费用添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="fPropaidExpenses" action="${ctx}/cw/fPropaidExpenses/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>待摊名称：</label>
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
				<th>待摊名称</th>
				<th>备注</th>
				<th>修改时间</th>
				<shiro:hasPermission name="cw:fPropaidExpenses:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="fPropaidExpenses">
			<tr>
				<td><a href="${ctx}/cw/fPropaidExpenses/form?id=${fPropaidExpenses.id}">
					${fPropaidExpenses.name}
				</a></td>
				<td>
					${fPropaidExpenses.remarks}
				</td>
				<td>
					<fmt:formatDate value="${fPropaidExpenses.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="cw:fPropaidExpenses:edit"><td>
    				<a href="${ctx}/cw/fPropaidExpenses/form?id=${fPropaidExpenses.id}">修改</a>
					<a href="${ctx}/cw/fPropaidExpenses/delete?id=${fPropaidExpenses.id}" onclick="return confirmx('确认要删除该待摊费用吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>