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
		<li class="active"><a href="${ctx}/report/cDdinfo/listInquire">订单列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="cDdinfo" action="${ctx}/ck/cDdinfo/listInquire" method="post" class="breadcrumb form-search">
		<input name="rkckddinfo.id" type="hidden" value="${cDdinfo.rkckddinfo.id}"/>
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>商品：</label>
				<form:select path="goods.id">
					<form:option value="" label="请选择"></form:option>
					<form:options items="${storesList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
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
				<th>商品</th>
				<th>规格</th>
				<th>退货数量</th>
				<th>数量</th>
				<th>单位</th>
				<th>单价</th>
				<th>金额</th>

			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="cDdinfo">
			<tr>
				<td>
					${cDdinfo.goods.name}
				</td>
				<td>
				    ${cDdinfo.goods.spec.name}
				</td>
				<td>
					${cDdinfo.thsl}
			    </td>
				<td>
				    ${cDdinfo.nub}
				</td>
				<td>
				   ${cDdinfo.goods.unit.name}
				</td>
				<td>
				   ${cDdinfo.rksjcbj}
				</td>
				<td>
				   ${cDdinfo.je}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>