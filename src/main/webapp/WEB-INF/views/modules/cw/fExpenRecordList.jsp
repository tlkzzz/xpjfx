<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>支出记录管理</title>
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
		<li class="active"><a href="${ctx}/cw/fExpenRecord/">支出记录列表</a></li>
		<!-- <shiro:hasPermission name="cw:fExpenRecord:edit"><li><a href="${ctx}/cw/fExpenRecord/form">支出记录添加</a></li></shiro:hasPermission> -->
	</ul>
	<form:form id="searchForm" modelAttribute="fExpenRecord" action="${ctx}/cw/fExpenRecord/" method="post" class="breadcrumb form-search">
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
				<th>创建时间</th>
				<th>备注</th>
				<shiro:hasPermission name="cw:fExpenRecord:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="fExpenRecord">
			<tr>
				<td>
					<fmt:formatDate value="${fExpenRecord.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td><a href="${ctx}/cw/fExpenRecord/form?id=${fExpenRecord.id}">
						${fExpenRecord.remarks}
				</a></td>
				<shiro:hasPermission name="cw:fExpenRecord:edit"><td>
    				<a href="${ctx}/cw/fExpenRecord/form?id=${fExpenRecord.id}">修改</a>
					<a href="${ctx}/cw/fExpenRecord/delete?id=${fExpenRecord.id}" onclick="return confirmx('确认要删除该支出记录吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>