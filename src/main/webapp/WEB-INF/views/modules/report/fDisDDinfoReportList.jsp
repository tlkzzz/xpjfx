<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>客户优惠</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/cw/fDiscount/discountReport">客户优惠</a></li>
		<li class="active"><a href="${ctx}/ck/cDdinfo/discountDDinfoReport">客户商品优惠</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="cDdinfo" action="${ctx}/ck/cDdinfo/discountDDinfoReport" method="post" class="breadcrumb form-search">
		<ul class="ul-form">
			<li><label>报废时间：</label>
				<input name="startDate" id="startDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					   value="<fmt:formatDate value="${cDdinfo.startDate}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd', maxDate:'#F{$dp.$D(\'endDate\')}',isShowClear:false});"/>
				<input name="endDate" id="endDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					   value="<fmt:formatDate value="${cDdinfo.endDate}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd', minDate:'#F{$dp.$D(\'startDate\')}',isShowClear:false});"/>
			</li>
			<li><label>商品名称：</label>
				<form:select path="goods.id">
					<form:option value="" label="请选择"/>
					<form:options items="${goodsList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>仓库名称：</label>
				<form:select path="house.id">
					<form:option value="" label="请选择"/>
					<form:options items="${houseList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTableTwo" class="table table-bordered">
		<thead>
		<tr>长沙市优彼食品客户商品优惠</tr></br>
			<tr>开始时间：<fmt:formatDate value="${cDdinfo.startDate}" pattern="yyyy-MM-dd"/> |结束时间：<fmt:formatDate value="${cDdinfo.endDate}" pattern="yyyy-MM-dd"/></tr>
			<tr>
				<th>行</th>
				<th>客户名称</th>
				<th>商品名称</th>
				<th>规格型号</th>
				<th>规格数量</th>
				<th>最小单位数量</th>
				<th>售价</th>
				<th>优惠金额</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${list}" var="cDdinfo" varStatus="status">
			<tr>
				<td>
					${status.index+1}
				</td>
				<td>
					${cDdinfo.store.name}
				</td>
				<td>
					${cDdinfo.goods.name}
				</td>
				<td>
					${cDdinfo.goods.spec.name}
				</td>
				<td>
					${cDdinfo.type}
				</td>
				<td>
					${cDdinfo.nub}
				</td>
				<td>
					<fmt:formatNumber value="${cDdinfo.je}" pattern="#.####"/>
				</td>
				<td>
					<fmt:formatNumber value="${cDdinfo.yhje}" pattern="#.####"/>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</body>
</html>