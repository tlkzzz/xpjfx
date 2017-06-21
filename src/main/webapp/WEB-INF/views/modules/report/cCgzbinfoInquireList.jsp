<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>采购单查询</title>
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
          function derive(){
        var form = $("#searchForm");
        window.open('${ctx}/ck/cCgzbinfo/cgExcel?'+form.serialize());
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/report/cCgzbinfo/ckInquire">采购单列表</a></li>
	</ul>

	<form:form id="searchForm" modelAttribute="cCgzbinfo" action="${ctx}/ck/cCgzbinfo/ckInquire" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>商品：</label>
				<form:select path="goods.id">
					<form:option value="" label="请选择"></form:option>
					<form:options items="${goodsList}" itemValue="id" itemLabel="name"></form:options>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input id=""  class="btn btn-primary" type="button" onclick="derive()" value="导出"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>商品</th>
				<th>采购数量</th>
				<th>供应商</th>
				<th>价格</th>
				<th>实际入库量</th>
				<th>入库时间</th>
				<th>备注</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="cCgzbinfo">
			<tr>
				<td>
					${cCgzbinfo.goods.name}
				</td>
				<td>
					${cCgzbinfo.nub}
				</td>
				<td>
					<c:if test="${not empty cCgzbinfo.rkinfo}">${cCgzbinfo.rkinfo.supplier.name}</c:if>
				</td>
				<td>
					${cCgzbinfo.jg}
				</td>
				<td>
					${cCgzbinfo.rknub}
				</td>
				<td>
					<fmt:formatDate value="${cCgzbinfo.rkDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${cCgzbinfo.remarks}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>