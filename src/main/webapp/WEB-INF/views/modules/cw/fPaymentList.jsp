<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>付款管理</title>
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
		<li class="active"><a href="${ctx}/cw/fPayment/">付款列表</a></li>
		<shiro:hasPermission name="cw:fPayment:edit"><li><a href="${ctx}/cw/fPayment/form">付款添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="fPayment" action="${ctx}/cw/fPayment/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>备注</th>
				<th>修改时间</th>
				<shiro:hasPermission name="cw:fPayment:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="fPayment">
			<tr>
				<td><a href="${ctx}/cw/fPayment/form?id=${fPayment.id}">
					${fPayment.remarks}
				</a></td>
				<td>
					<fmt:formatDate value="${fPayment.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="cw:fPayment:edit"><td>
    				<a href="${ctx}/cw/fPayment/form?id=${fPayment.id}">修改</a>
					<a href="${ctx}/cw/fPayment/delete?id=${fPayment.id}" onclick="return confirmx('确认要删除该付款吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>