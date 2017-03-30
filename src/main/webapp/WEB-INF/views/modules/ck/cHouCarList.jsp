<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>仓库车辆管理</title>
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
		<li class="active"><a href="${ctx}/ck/cHouCar/">仓库车辆列表</a></li>
		<shiro:hasPermission name="ck:cHouCar:edit"><li><a href="${ctx}/ck/cHouCar/form">仓库车辆添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="cHouCar" action="${ctx}/ck/cHouCar/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>仓库：</label>
				<form:select path="house.id">
					<form:option value="" label="请选择"></form:option>
					<form:options items="${houseList}" itemLabel="name" itemValue="id" htmlEscape="false"></form:options>
				</form:select>
			</li>
			<li><label>车辆：</label>
				<form:select path="car.id">
					<form:option value="" label="请选择"></form:option>
					<form:options items="${carList}" itemLabel="name" itemValue="id" htmlEscape="false"></form:options>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>仓库</th>
				<th>车辆</th>
				<th>人员</th>
				<th>创建时间</th>
				<th>备注</th>
				<shiro:hasPermission name="ck:cHouCar:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="cHouCar">
			<tr>
				<td><a href="${ctx}/ck/cHouCar/form?id=${cHouCar.id}">
					${cHouCar.house.name}
				</a></td>
				<td>
					${cHouCar.car.name}
				</td>
				<td>
					<c:if test="${not empty cHouCar.userList}"><c:forEach items="${cHouCar.userList}" var="user" varStatus="status">${user.name}<c:if test="${!status.last}">，</c:if></c:forEach></c:if>
				</td>
				<td>
					<fmt:formatDate value="${cHouCar.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${cHouCar.remarks}
				</td>
				<shiro:hasPermission name="ck:cHouCar:edit"><td>
    				<a href="${ctx}/ck/cHouCar/form?id=${cHouCar.id}">修改</a>
					<a href="${ctx}/ck/cHouCar/delete?id=${cHouCar.id}" onclick="return confirmx('确认要删除该仓库车辆吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>