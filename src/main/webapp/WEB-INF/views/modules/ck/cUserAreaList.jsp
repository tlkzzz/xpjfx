<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>人员区域管理</title>
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
		<li class="active"><a href="${ctx}/ck/cUserArea/">人员区域列表</a></li>
		<shiro:hasPermission name="ck:cUserArea:edit"><li><a href="${ctx}/ck/cUserArea/form">人员区域添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="cUserArea" action="${ctx}/ck/cUserArea/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>区域主键：</label>
				<form:input path="areaId" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>人员主键：</label>
				<form:input path="userid" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>修改时间</th>
				<th>备注</th>
				<shiro:hasPermission name="ck:cUserArea:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="cUserArea">
			<tr>
				<td><a href="${ctx}/ck/cUserArea/form?id=${cUserArea.id}">
					<fmt:formatDate value="${cUserArea.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</a></td>
				<td>
					${cUserArea.remarks}
				</td>
				<shiro:hasPermission name="ck:cUserArea:edit"><td>
    				<a href="${ctx}/ck/cUserArea/form?id=${cUserArea.id}">修改</a>
					<a href="${ctx}/ck/cUserArea/delete?id=${cUserArea.id}" onclick="return confirmx('确认要删除该人员区域吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>