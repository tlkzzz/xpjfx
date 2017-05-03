<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>移库报表</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li <c:if test="${empty type ||type eq '1'}">class="active"</c:if>><a href="${ctx}/ck/cDdinfo/scrapList">商品明细</a></li>
		<li <c:if test="${not empty type&&type eq '2'}">class="active"</c:if>><a href="${ctx}/ck/cDdinfo/scrapList?type=2">商品汇总</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="cYkinfo" action="${ctx}/ck/cDdinfo/scrapList" method="post" class="breadcrumb form-search">
		<input name="type" type="hidden" value="${type}"/>
		<ul class="ul-form">
			<li><label>报废时间：</label>
				<input name="startDate" id="startDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					   value="<fmt:formatDate value="${cYkinfo.startDate}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd', maxDate:'#F{$dp.$D(\'endDate\')}',isShowClear:false});"/>
				<input name="endDate" id="endDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					   value="<fmt:formatDate value="${cYkinfo.endDate}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd', minDate:'#F{$dp.$D(\'startDate\')}',isShowClear:false});"/>
			</li>
			<li><label>商品名称：</label>
				<form:select path="goods.id">
					<form:option value="" label="请选择"/>
					<form:options items="${goodsList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>仓库名称：</label>
				<form:select path="startHouse.id">
					<form:option value="" label="请选择"/>
					<form:options items="${houseList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<c:if test="${empty type || type eq '1'}">
	<table id="contentTableOne" class="table table-bordered">
		<thead>
		<tr>长沙市优彼食品移库报表-商品明细</tr></br>
			<tr>开始时间：<fmt:formatDate value="${cYkinfo.startDate}" pattern="yyyy-MM-dd"/> |结束时间：<fmt:formatDate value="${cYkinfo.endDate}" pattern="yyyy-MM-dd"/></tr>
			<tr>
				<th>行</th>
				<th>仓库名称</th>
				<th>商品名称</th>
				<th>规格型号</th>
				<th>数量</th>
				<th>成本单价</th>
				<th>成本金额</th>
				<th>操作人</th>
				<th>操作时间</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${list}" var="cYkinfo" varStatus="status">
			<tr>
				<td>
					${status.index+1}
				</td>
				<td>
					${cYkinfo.startHouse.name}
				</td>
				<td>
					${cYkinfo.goods.name}
				</td>
				<td>
					${cYkinfo.goods.spec.name}
				</td>
				<td>
					${cYkinfo.specNub}
				</td>
				<td>
					${cYkinfo.goods.cbj}
				</td>
				<td>
					<fmt:formatNumber value="${cYkinfo.nub*cYkinfo.goods.cbj}" pattern="#.####"/>
				</td>
				<td>
					${cYkinfo.createBy.name}
				</td>
				<td>
					<fmt:formatDate value="${cYkinfo.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</c:if>
	<c:if test="${not empty type && type eq '2'}">
	<table id="contentTableTwo" class="table table-bordered">
		<thead>
		<tr>长沙市优彼食品移库报表-商品汇总</tr></br>
			<tr>开始时间：<fmt:formatDate value="${cYkinfo.startDate}" pattern="yyyy-MM-dd"/> |结束时间：<fmt:formatDate value="${cYkinfo.endDate}" pattern="yyyy-MM-dd"/></tr>
			<tr>
				<th>行</th>
				<th>仓库</th>
				<th>商品名称</th>
				<th>规格型号</th>
				<th>数量</th>
				<th>小单位数量</th>
				<th>小单位</th>
				<th>成本金额</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${list}" var="cYkinfo" varStatus="status">
			<tr>
				<td>
					${status.index+1}
				</td>
				<td>
					${cYkinfo.startHouse.name}
				</td>
				<td>
					${cYkinfo.goods.name}
				</td>
				<td>
					${cYkinfo.goods.spec.name}
				</td>
				<td>
					${cYkinfo.specNub}
				</td>
				<td>
					${cYkinfo.nub}
				</td>
				<td>
					${cYkinfo.goods.small.name}
				</td>
				<td>
					<fmt:formatNumber value="${cYkinfo.goods.cbj*cYkinfo.nub}" pattern="#.####"/>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</c:if>
</body>
</html>