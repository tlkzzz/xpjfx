<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商品销售分析</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li <c:if test="${empty type ||type eq '1'}">class="active"</c:if>><a href="${ctx}/ck/cDdinfo/goodsSalesAnalysis">年度查询</a></li>
		<li <c:if test="${not empty type&&type eq '2'}">class="active"</c:if>><a href="${ctx}/ck/cDdinfo/goodsSalesAnalysis?type=2">月度查询</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="cDdinfo" action="${ctx}/ck/cDdinfo/goodsSalesAnalysis" method="post" class="breadcrumb form-search">
		<input name="type" type="hidden" value="${type}"/>
		<ul class="ul-form">
			<li><label>时间：</label>
				<input name="rkckdate" id="rkckdate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					   <c:if test="${empty type ||type eq '1'}">
					   value="<fmt:formatDate value="${cDdinfo.rkckdate}" pattern="yyyy"/>"
					   onclick="WdatePicker({dateFmt:'yyyy',isShowClear:false});"</c:if>
						<c:if test="${not empty type&&type eq '2'}">
						value="<fmt:formatDate value="${cDdinfo.rkckdate}" pattern="yyyy-MM"/>"
						onclick="WdatePicker({dateFmt:'yyyy-MM', isShowClear:false});"</c:if>/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<c:if test="${empty type || type eq '1'}">
	<table id="contentTable" class="table table-bordered">
		<thead>
		<tr>长沙市优彼食品<fmt:formatDate value="${cDdinfo.rkckdate}" pattern="yyyy"/>年度商品销售分析</tr></br>
			<tr>
				<th rowspan="2">年份</th>
			</tr>
			<tr>
				<c:forEach items="${mapList[0].cdList}" varStatus="status" var="cd">
					<th colspan="8" style="text-align: center">${mapList[0].date}年${status.index+1}月</th>
				</c:forEach>
			</tr>
			<tr><th>商品名称,规格</th>
				<c:forEach items="${mapList[0].cdList}" varStatus="status" var="cd">
				<th>销售数量</th>
				<th>销售金额</th>
				<th>还货数量</th>
				<th>还货金额</th>
				<th>退货数量</th>
				<th>退货金额</th>
				<th>合计数量</th>
				<th>合计金额</th>
				</c:forEach>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${mapList}" var="map">
			<tr>
				<td>
					${map.cDdinfo.goods.name}，${map.cDdinfo.goods.spec.name}
				</td>
				<c:forEach items="${map.cdList}" var="cd" varStatus="status">
				<td>
					${cd.nub}
				</td>
				<td>
					<fmt:formatNumber value="${cd.je}" pattern="#.####"/>
				</td>
				<td>
					<%--没有还货--%>
				</td>
				<td>
					<%--没有还货--%>
				</td>
				<td>
					${cd.thsl}
				</td>
				<td>
					<fmt:formatNumber value="${cd.thje}" pattern="#.####"/>
				</td>
				<td>
					<fmt:formatNumber value="${cd.nub-cd.thsl}" pattern="#.####"/>
				</td>
				<td>
					<fmt:formatNumber value="${cd.je-cd.thje}" pattern="#.####"/>
				</td>
				</c:forEach>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</c:if>
	<c:if test="${not empty type && type eq '2'}">
	<table id="contentTabl" class="table table-bordered">
		<thead>
		<tr>长沙市优彼食品<fmt:formatDate value="${cDdinfo.rkckdate}" pattern="yyyy-MM"/>月度商品销售分析</tr></br>
		<tr>
			<th rowspan="2">月份</th>
		</tr>
		<tr>
			<c:forEach items="${mapList[0].cdList}" varStatus="status" var="cd">
				<th colspan="8" style="text-align: center">${mapList[0].date}月${status.index+1}日</th>
			</c:forEach>
		</tr>
		<tr><th>商品名称，规格</th>
			<c:forEach items="${mapList[0].cdList}" varStatus="status" var="cd">
			<th>销售数量</th>
			<th>销售金额</th>
			<th>还货数量</th>
			<th>还货金额</th>
			<th>退货数量</th>
			<th>退货金额</th>
			<th>合计数量</th>
			<th>合计金额</th>
			</c:forEach>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${mapList}" var="map">
			<tr>
				<td>
					${map.cDdinfo.goods.name}，${map.cDdinfo.goods.spec.name}
				</td>
				<c:forEach items="${map.cdList}" var="cd" varStatus="status">
					<td>
						${cd.nub}
					</td>
					<td>
						<fmt:formatNumber value="${cd.je}" pattern="#.####"/>
					</td>
					<td>
						<%--没有还货--%>
					</td>
					<td>
						<%--没有还货--%>
					</td>
					<td>
						${cd.thsl}
					</td>
					<td>
						<fmt:formatNumber value="${cd.thje}" pattern="#.####"/>
					</td>
					<td>
						<fmt:formatNumber value="${cd.nub-cd.thsl}" pattern="#.####"/>
					</td>
					<td>
						<fmt:formatNumber value="${cd.je-cd.thje}" pattern="#.####"/>
					</td>
				</c:forEach>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</c:if>
</body>
</html>