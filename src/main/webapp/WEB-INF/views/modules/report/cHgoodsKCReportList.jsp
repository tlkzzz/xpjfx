<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>库存报表</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li <c:if test="${empty type||type eq '1'}">class="active"</c:if>><a href="${ctx}/ck/cHgoods/kcReport">库存明细</a></li>
		<li <c:if test="${not empty type&&type eq '2'}">class="active"</c:if>><a href="${ctx}/ck/cHgoods/kcReport?type=2">商品汇总</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="cHgoods" action="${ctx}/ck/cHgoods/kcReport" method="post" class="breadcrumb form-search">
		<ul class="ul-form">
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
	<c:if test="${empty type||type eq '1'}">
	<table id="contentTableOne" class="table table-bordered">
		<thead>
		<tr>长沙市优彼食品库存报表-商品明细</tr></br>
		<tr>仓库：全部仓库</tr>
			<tr>
				<th>行</th>
				<th>仓库名称</th>
				<th>商品编号</th>
				<th>商品名称</th>
				<th>规格型号</th>
				<th>今日库存</th>
				<th>可用库存</th>
				<th>成本价格</th>
				<th>销售金额</th>
				<th>成本金额</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${list}" var="cHgoods" varStatus="status">
			<tr>
				<td>
					${status.index+1}
				</td>
				<td>
					${cHgoods.house.name}
				</td>
				<td>
					${cHgoods.goods.sort}
				</td>
				<td>
					${cHgoods.goods.name}
				</td>
				<td>
					${cHgoods.goods.spec.name}
				</td>
				<td>
					${cHgoods.unit}
				</td>
				<td>
					${cHgoods.unit}
				</td>
				<td>
					${cHgoods.goods.cbj}
				</td>
				<td>
					<fmt:formatNumber value="${cHgoods.goods.sj*cHgoods.nub}" pattern="#.####"/>
				</td>
				<td>
					<fmt:formatNumber value="${cHgoods.goods.cbj*cHgoods.nub}" pattern="#.####"/>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</c:if>
	<c:if test="${not empty type&&type eq '2'}">
	<table id="contentTableOne" class="table table-bordered">
		<thead>
		<tr>长沙市优彼食品库存报表-商品汇总</tr></br>
		<tr>仓库：全部仓库</tr>
			<tr>
				<th>行</th>
				<th>商品编号</th>
				<th>商品名称</th>
				<th>规格型号</th>
				<th>今日库存</th>
				<th>可用库存</th>
				<th>销售金额</th>
				<th>成本金额</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${list}" var="cHgoods" varStatus="status">
			<tr>
				<td>
					${status.index+1}
				</td>
				<td>
					${cHgoods.goods.sort}
				</td>
				<td>
					${cHgoods.goods.name}
				</td>
				<td>
					${cHgoods.goods.spec.name}
				</td>
				<td>
					${cHgoods.unit}
				</td>
				<td>
					${cHgoods.unit}
				</td>
				<td>
					<fmt:formatNumber value="${cHgoods.goods.sj*cHgoods.nub}" pattern="#.####"/>
				</td>
				<td>
					<fmt:formatNumber value="${cHgoods.goods.cbj*cHgoods.nub}" pattern="#.####"/>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</c:if>	<c:if test="${not empty type&&type eq '3'}">
	<table id="contentTableOne" class="table table-bordered">
		<thead>
		<tr>长沙市优彼食品库存报表-品牌汇总</tr></br>
		<tr>仓库：全部仓库</tr>
			<tr>
				<th>行</th>
				<th>品牌名称</th>
				<th>数量</th>
				<th>销售金额</th>
				<th>成本金额</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${list}" var="cHgoods" varStatus="status">
			<tr>
				<td>
					${status.index+1}
				</td>
				<td>
					${cHgoods.goods.bands.name}
				</td>
				<td>
					${cHgoods.unit}
				</td>
				<td>
					<fmt:formatNumber value="${cHgoods.goods.sj*cHgoods.unit}" pattern="#.####"/>
				</td>
				<td>
					<fmt:formatNumber value="${cHgoods.goods.cbj*cHgoods.unit}" pattern="#.####"/>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</c:if>
</body>
</html>