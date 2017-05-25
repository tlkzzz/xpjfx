<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>客户优惠</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function derive(){
        var form = $("#searchForm");
        window.open('${ctx}/cw/fDiscount/khyhexcel?'+form.serialize());
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/cw/fDiscount/discountReport">客户优惠</a></li>
		<li ><a href="${ctx}/ck/cDdinfo/discountDDinfoReport">客户商品优惠</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="fDiscount" action="${ctx}/cw/fDiscount/discountReport" method="post" class="breadcrumb form-search">
		<ul class="ul-form">
			<li><label>报废时间：</label>
				<input name="startDate" id="startDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					   value="<fmt:formatDate value="${fDiscount.startDate}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd', maxDate:'#F{$dp.$D(\'endDate\')}',isShowClear:false});"/>
				<input name="endDate" id="endDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					   value="<fmt:formatDate value="${fDiscount.endDate}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd', minDate:'#F{$dp.$D(\'startDate\')}',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input id=""  class="btn btn-primary" type="button" onclick="derive()" value="导出"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTableOne" class="table table-bordered">
		<thead>
		<tr>长沙市优彼食品客户优惠一览表</tr></br>
			<tr>开始时间：<fmt:formatDate value="${fDiscount.startDate}" pattern="yyyy-MM-dd"/> |结束时间：<fmt:formatDate value="${fDiscount.endDate}" pattern="yyyy-MM-dd"/></tr>
			<tr>
				<th>行</th>
				<th>客户名称</th>
				<th>订单编号</th>
				<th>优惠金额</th>
				<th>优惠时间</th>
				<th>备注</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${list}" var="fDiscount" varStatus="status">
			<tr>
				<td>
					${status.index+1}
				</td>
				<td>
					${fDiscount.store.name}
				</td>
				<td>
					${fDiscount.ddid.ddbh}
				</td>
				<td>
					<fmt:formatNumber value="${fDiscount.yhje}" pattern="#.####"/>
				</td>
				<td>
					<fmt:formatDate value="${fDiscount.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fDiscount.remarks}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</body>
</html>