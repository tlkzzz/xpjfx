<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>移库记录管理</title>
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
		<li class="active"><a href="${ctx}/report/cYkinfo/ckInquire">移库记录</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="cYkinfo" action="${ctx}/ck/cYkinfo/ckInquire" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>起始仓库：</label>
				<form:input path="startHouse.name" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>结束仓库：</label>
				<form:input path="endHouse.name" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>商品：</label>
				<form:input path="goods.name" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>起始仓库</th>
				<th>结束仓库</th>
				<th>商品</th>
				<th>数量</th>
				<th>移库时间</th>
				<th>备注</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="cYkinfo">
			<tr>
				<td>
					${cYkinfo.startHouse.name}
				</td>
				<td>
					${cYkinfo.endHouse.name}
				</td>
				<td>
					${cYkinfo.goods.name}
				</td>
				<td>
					${cYkinfo.nub}
				</td>
				<td>
					<fmt:formatDate value="${cYkinfo.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${cYkinfo.remarks}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>