<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>

<head>
	<title>欠款记录管理</title>
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
		<li class="active"><a href="${ctx}/cw/fArrears/">欠款记录列表</a></li>
	<!--	<shiro:hasPermission name="cw:fArrears:edit"><li><a href="${ctx}/cw/fArrears/form">欠款记录添加</a></li></shiro:hasPermission> -->
	</ul>
	<form:form id="searchForm" modelAttribute="fArrears" action="${ctx}/cw/fArrears/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>欠款单位：</label>
				<form:input path="arrearsUnit" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>欠款单位</th>
				<th>备注</th>
				<th>修改时间</th>
				<shiro:hasPermission name="cw:fArrears:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="fArrears">
			<tr>
				<td><a href="${ctx}/cw/fArrears/form?id=${fArrears.id}">
					${fArrears.arrearsUnit}
				</a></td>
				<td>
					${fArrears.remarks}
				</td>
				<td>
					<fmt:formatDate value="${fArrears.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="cw:fArrears:edit"><td>
    				<a href="${ctx}/cw/fArrears/form?id=${fArrears.id}">修改</a>
					<a href="${ctx}/cw/fArrears/delete?id=${fArrears.id}" onclick="return confirmx('确认要删除该欠款记录吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>