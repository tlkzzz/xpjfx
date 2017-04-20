<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>固定资产登记管理</title>
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
		<li class="active"><a href="${ctx}/cw/fFixedAssets/">固定资产登记列表</a></li>
		<shiro:hasPermission name="cw:fFixedAssets:edit"><li><a href="${ctx}/cw/fFixedAssets/form">固定资产登记添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="fFixedAssets" action="${ctx}/cw/fFixedAssets/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>资产名称：</label>
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
				<th>资产名称</th>
				<th>归属部门</th>
				<th>负责人</th>
				<th></th>
				<th>备注</th>
				<th>修改时间</th>
				<shiro:hasPermission name="cw:fFixedAssets:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="fFixedAssets">
			<tr>
				<td><a href="${ctx}/cw/fFixedAssets/form?id=${fFixedAssets.id}">
					${fFixedAssets.name}
				</a></td>
				<td>
               ${fFixedAssets.office.id}
                </td>
                <td>
                     ${fFixedAssets.fzr.id}
                 </td>
				<td>
					${fFixedAssets.remarks}
				</td>
				<td>
					<fmt:formatDate value="${fFixedAssets.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="cw:fFixedAssets:edit"><td>
    				<a href="${ctx}/cw/fFixedAssets/form?id=${fFixedAssets.id}">修改</a>
					<a href="${ctx}/cw/fFixedAssets/delete?id=${fFixedAssets.id}" onclick="return confirmx('确认要删除该固定资产登记吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>