<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>入库报表</title>
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
		<li class="active"><a href="${ctx}/ck/cRkinfo/rkReport">入库报表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="cRkinfo" action="${ctx}/ck/cRkinfo/rkReport" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>开始时间：</label>
				<input name="startDate" id="startDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					   value="<fmt:formatDate value="${cRkinfo.startDate}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd', maxDate:'#F{$dp.$D(\'endDate\')}',isShowClear:false});"/>
			</li>
			<li><label>截止时间：</label>
				<input name="endDate" id="endDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					   value="<fmt:formatDate value="${cRkinfo.endDate}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd', minDate:'#F{$dp.$D(\'startDate\')}',isShowClear:false});"/>
			</li>
			<li><label>商品名称：</label>
				<form:input path="goods.name" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>仓库名称：</label>
				<form:input path="house.name" htmlEscape="false" maxlength="64" class="input-medium"/>
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
				<th>入库数量</th>
				<th>入库后的总数量</th>
				<th>入库前成本价</th>
				<th>入库成本价</th>
				<th>入库类型</th>
				<th>入库时间</th>
				<th>备注</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="cRkinfo">
			<tr>
				<td>
						${cRkinfo.house.name}
				</td>
				<td>
					${cRkinfo.goods.name}
				</td>
				<td>
					${cRkinfo.rknub}
				</td>
				<td>
					${cRkinfo.rkhnub}
				</td>
				<td>
					${cRkinfo.rkqcbj}
				</td>
				<td>
					${cRkinfo.rkhcbj}
				</td>
				<td>
					${fns:getDictLabel(cRkinfo.state, "rkState", "")}
				</td>
				<td>
					<fmt:formatDate value="${cRkinfo.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${cRkinfo.remarks}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>