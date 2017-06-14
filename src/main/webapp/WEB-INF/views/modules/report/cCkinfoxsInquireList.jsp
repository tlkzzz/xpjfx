<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>销售单查询</title>
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
		<li class="active"><a href="${ctx}/ck/cCkinfo/xsInquire">出库信息列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="cCkinfo" action="${ctx}/ck/cCkinfo/xsInquire" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>供应商：</label>
				<form:select path="supplier.id">
					<form:option value="" label="请选择"></form:option>
					<form:options items="${supplierList}" itemLabel="name" itemValue="id" htmlEscape="false"></form:options>
				</form:select>
			</li>
			<li><label>商品：</label>
				<form:select path="goods.id">
					<form:option value="" label="请选择"></form:option>
					<form:options items="${goodsList}" itemLabel="name" itemValue="id" htmlEscape="false"></form:options>
				</form:select>
			</li>
			<li><label>仓库：</label>
				<form:select path="house.id">
					<form:option value="" label="请选择"></form:option>
					<form:options items="${houseList}" itemLabel="name" itemValue="id" htmlEscape="false"></form:options>
				</form:select>
			</li>
			<li><label>客户：</label>
				<form:select path="store.id">
					<form:option value="" label="请选择"></form:option>
					<form:options items="${storeList}" itemLabel="name" itemValue="id" htmlEscape="false"></form:options>
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
				<th>商品</th>
				<th>金额</th>
				<th>出库前成本价</th>
				<th>数量</th>
				<th>供应商</th>
				<th>客户</th>
				<th>出库时间</th>
				<th>出库方式</th>
				<th>是否审批</th>
				<th>经手人</th>
				<th>备注信息</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="cCkinfo">
			<tr>
				<td>
					${cCkinfo.house.name}
				</td>
				<td>
					${cCkinfo.goods.name}
				</td>
				<td>
					${cCkinfo.je}
				</td>
				<td>
					${cCkinfo.ckqcbj}
				</td>
				<td>
					${cCkinfo.nub}
				</td>
				<td>
					${cCkinfo.supplier.name}
				</td>
				<td>
					${cCkinfo.store.name}
				</td>
				<td>
					<fmt:formatDate value="${cCkinfo.ckdate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fns:getDictLabel(cCkinfo.state, "ckState", "")}
				</td>
				<td>
					${fns:getDictLabel(cCkinfo.issp, "storeState", "")}
				</td>
				<td>
					${cCkinfo.jsr.name}
				</td>
				<td>
					${cCkinfo.remarks}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>