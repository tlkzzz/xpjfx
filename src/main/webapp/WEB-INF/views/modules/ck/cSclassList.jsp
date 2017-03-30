<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>客户分类管理</title>
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
		<li class="active"><a href="${ctx}/ck/cSclass/">客户分类列表</a></li>
		<shiro:hasPermission name="ck:cSclass:edit"><li><a href="${ctx}/ck/cSclass/form">客户分类添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="cSclass" action="${ctx}/ck/cSclass/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>分类名称：</label>
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
				<th>分类名称</th>
				<th>创建时间</th>
				<th>备注</th>
				<shiro:hasPermission name="ck:cSclass:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="cSclass">
			<tr>
				<td><a href="${ctx}/ck/cSclass/form?id=${cSclass.id}">
					${cSclass.name}
				</a></td>
				<td>
					<fmt:formatDate value="${cSclass.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${cSclass.remarks}
				</td>
				<shiro:hasPermission name="ck:cSclass:edit"><td>
    				<a href="${ctx}/ck/cSclass/form?id=${cSclass.id}">修改</a>
					<a href="${ctx}/ck/cSclass/delete?id=${cSclass.id}" onclick="return confirmx('确认要删除该客户分类吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>