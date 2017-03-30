<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>车辆人员管理</title>
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
		<li class="active"><a href="${ctx}/ck/cCarUser/">车辆人员列表</a></li>
		<shiro:hasPermission name="ck:cCarUser:edit"><li><a href="${ctx}/ck/cCarUser/form">车辆人员添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="cCarUser" action="${ctx}/ck/cCarUser/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>车辆：</label>
				<form:input path="car.id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>人员：</label>
				<form:input path="user.id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>人员</th>
				<th>修改时间</th>
				<th>备注</th>
				<shiro:hasPermission name="ck:cCarUser:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="cCarUser">
			<tr>
				<td><a href="${ctx}/ck/cCarUser/form?id=${cCarUser.id}">
					${cCarUser.user.id}
				</a></td>
				<td>
					<fmt:formatDate value="${cCarUser.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${cCarUser.remarks}
				</td>
				<shiro:hasPermission name="ck:cCarUser:edit"><td>
    				<a href="${ctx}/ck/cCarUser/form?id=${cCarUser.id}">修改</a>
					<a href="${ctx}/ck/cCarUser/delete?id=${cCarUser.id}" onclick="return confirmx('确认要删除该车辆人员吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>