<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>科目类别表管理343</title>
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
		<li class="active"><a href="${ctx}/ck/cKm/">科目类别列表</a></li>
		<shiro:hasPermission name="ck:cKm:edit"><li><a href="${ctx}/ck/cKm/form">科目类别添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="cKm" action="${ctx}/ck/cKm/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>科目名称：</label>
				<form:input path="kmname" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>科目类别ID</th>
				<th>科目编号</th>
				<th>科目名称</th>
				<th>账户</th>
				<th>余额</th>
				<th>创建时间</th>
				<th>创建人</th>
				<th>修改时间</th>
				<th>备注</th>
				<shiro:hasPermission name="ck:cKm:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="cKm">
			<tr>
				<td><a href="${ctx}/ck/cKm/form?id=${cKm.id}">
					${cKm.kmlbid}
				</a></td>
				<td>
					${cKm.kmnb}
				</td>
				<td>
					${cKm.kmname}
				</td>
				<td>
					${cKm.zh}
				</td>
				<td>
					${cKm.je}
				</td>
				<td>
					<fmt:formatDate value="${cKm.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${cKm.createBy.id}
				</td>
				<td>
					<fmt:formatDate value="${cKm.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${cKm.remarks}
				</td>
				<shiro:hasPermission name="ck:cKm:edit"><td>
    				<a href="${ctx}/ck/cKm/form?id=${cKm.id}">修改</a>
					<a href="${ctx}/ck/cKm/delete?id=${cKm.id}" onclick="return confirmx('确认要删除该科目类别表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>