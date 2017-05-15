<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>业务员订单</title>
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
	<li class="active"><a href="${ctx}/ck/cDdinfo/bfInquiret">报废单列表</a></li>
</ul>
	<form:form id="searchForm" modelAttribute="cDdinfo" action="${ctx}/ck/cDdinfo/ywylistInquire" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input name="type" type="hidden" value="${type}"/>
		<ul class="ul-form">
			<li><label>商品：</label>
				<form:select path="goods.id">
					<form:option value="" label="请选择"></form:option>
					<form:options items="${goodsList}" itemValue="id" itemLabel="name"></form:options>
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
				<th>商品类别</th>
				<th>品名,规格</th>
				<th>单位</th>
				<th>合计金额</th>
				<th>业务员</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${list}" var="cDdinfo" varStatus="status">
				<tr>
					<td>
							${cDdinfo.goods.name}
					</td>
					<td>
						${cDdinfo.goods.spec.name}
					</td>
					<td>
							${cDdinfo.goods.big.name}
					</td>
					<td>
						<fmt:formatNumber value="${cDdinfo.je}" pattern="#.####"/>
					</td>
					<c:forEach items="${userList}" var="cDdinfo">
					<td>
							${cDdinfo.createBy.name}
					</td>
				</tr>
					</c:forEach>
			</c:forEach>
			</tbody>
		</table>
</body>
</html>