<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>单位管理</title>
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
		<li class="active"><a href="${ctx}/ck/cUnit/">单位列表1</a></li>
		<shiro:hasPermission name="ck:cUnit:edit"><li><a href="${ctx}/ck/cUnit/form">单位添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="cUnit" action="${ctx}/ck/cUnit/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>单位名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>单位名称</th>
				<th>创建时间</th>
				<th>备注</th>
				<shiro:hasPermission name="ck:cUnit:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="cUnit">
			<tr>
				<td><a href="${ctx}/ck/cUnit/form?id=${cUnit.id}">
					${cUnit.name}
				</a></td>
				<td>
					<fmt:formatDate value="${cUnit.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${cUnit.remarks}
				</td>
				<shiro:hasPermission name="ck:cUnit:edit"><td>
    				<a href="${ctx}/ck/cUnit/form?id=${cUnit.id}">修改</a>
					<a href="${ctx}/ck/cUnit/delete?id=${cUnit.id}" onclick="return confirmx('确认要删除该单位表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>