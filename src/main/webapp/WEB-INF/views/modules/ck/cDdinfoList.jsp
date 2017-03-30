<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>订单管理</title>
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
		<li class="active"><a href="${ctx}/ck/cDdinfo/">订单列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="cDdinfo" action="${ctx}/ck/cDdinfo/" method="post" class="breadcrumb form-search">
		<input name="rkckddinfo.id" type="hidden" value="${cDdinfo.rkckddinfo.id}"/>
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>仓库：</label>
				<form:input path="house.id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>商品：</label>
				<form:input path="goods.id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>供应商：</label>
				<form:input path="supplier.id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>客户：</label>
				<form:input path="store.id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>订单编号</th>
				<th>仓库</th>
				<th>商品</th>
				<th>供应商</th>
				<th>客户</th>
				<th>金额</th>
				<th>入库出库时间</th>
				<th>数量</th>
				<th>入库前成本价</th>
				<th>入库实际成本价</th>
				<th>修改时间</th>
				<th>备注</th>
				<shiro:hasPermission name="ck:cDdinfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="cDdinfo">
			<tr>
				<td>
					${cDdinfo.ddbh}
				</td>
				<td>
					${cDdinfo.house.name}
				</td>
				<td>
					${cDdinfo.goods.name}
				</td>
				<td>
					${cDdinfo.supplier.name}
				</td>
				<td>
					${cDdinfo.store.name}
				</td>
				<td>
					${cDdinfo.je}
				</td>
				<td>
					<fmt:formatDate value="${cDdinfo.rkckdate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${cDdinfo.nub}
				</td>
				<td>
					${cDdinfo.rkqcbj}
				</td>
				<td>
					${cDdinfo.rksjcbj}
				</td>
				<td>
					<fmt:formatDate value="${cDdinfo.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${cDdinfo.remarks}
				</td>
				<shiro:hasPermission name="ck:cDdinfo:edit"><td>
    				<c:if test="${cDdinfo.rkckddinfo.issp!='1'}"><a href="${ctx}/ck/cDdinfo/form?id=${cDdinfo.id}">修改</a></c:if>
					<a href="${ctx}/ck/cDdinfo/delete?id=${cDdinfo.id}" onclick="return confirmx('确认要删除该订单吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>