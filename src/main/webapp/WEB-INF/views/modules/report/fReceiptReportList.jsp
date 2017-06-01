<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>应收款报表</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function derive(){
        var form = $("#searchForm");
        window.open('${ctx}/cw/fReceipt/yfkexcel?'+form.serialize());
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li <c:if test="${empty type ||type eq '1'}">class="active"</c:if>><a href="${ctx}/cw/fReceipt/reportList">应收款明细</a></li>
		<li <c:if test="${not empty type&&type eq '2'}">class="active"</c:if>><a href="${ctx}/cw/fReceipt/reportList?type=2">应收款汇总</a></li>
		<li <c:if test="${not empty type&&type eq '3'}">class="active"</c:if>><a href="${ctx}/cw/fReceipt/reportList?type=3">应收款欠款明细</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="fReceipt" action="${ctx}/ck/fReceipt/reportList" method="post" class="breadcrumb form-search">
		<input name="type" type="hidden" value="${type}"/>
		<ul class="ul-form">
			<li><label>报废时间：</label>
				<input name="startDate" id="startDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					   value="<fmt:formatDate value="${fReceipt.startDate}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd', maxDate:'#F{$dp.$D(\'endDate\')}',isShowClear:false});"/>
				<input name="endDate" id="endDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					   value="<fmt:formatDate value="${fReceipt.endDate}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd', minDate:'#F{$dp.$D(\'startDate\')}',isShowClear:false});"/>
			</li>
			<li><label>客户名称：</label>
				<form:select path="travelUnit.id">
					<form:option value="" label="请选择"/>
					<form:options items="${storeList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input id=""  class="btn btn-primary" type="button" onclick="derive()" value="导出"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<c:if test="${empty type || type eq '1'}">
	<table id="contentTableOne" class="table table-bordered">
		<thead>
		<tr>长沙市优彼食品应收款明细表</tr></br>
			<tr>开始时间：<fmt:formatDate value="${fReceipt.startDate}" pattern="yyyy-MM-dd"/> |结束时间：<fmt:formatDate value="${fReceipt.endDate}" pattern="yyyy-MM-dd"/></tr>
			<tr>
				<th>行</th>
				<th>收款日期</th>
				<th>客户名称</th>
				<th>订单单号</th>
				<th>实付金额</th>
				<th>合同金额</th>
				<th>经手人</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${list}" var="fReceipt" varStatus="status">
			<tr>
				<td>
					${status.index+1}
				</td>
				<td>
					<fmt:formatDate value="${fReceipt.receiptDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fReceipt.travelUnit.name}
				</td>
				<td>
					${fReceipt.ddbh}
				</td>
				<td>
					<fmt:formatNumber value="${fReceipt.je}" pattern="#.####"/>
				</td>
				<td>
					<fmt:formatNumber value="${fReceipt.htje}" pattern="#.####"/>
				</td>
				<td>
					${fReceipt.jsr.name}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</c:if>
	<c:if test="${not empty type && type eq '2'}">
	<table id="contentTableTwo" class="table table-bordered">
		<thead>
		<tr>长沙市优彼食品应收款汇总表</tr></br>
			<tr>开始时间：<fmt:formatDate value="${fReceipt.startDate}" pattern="yyyy-MM-dd"/> |结束时间：<fmt:formatDate value="${fReceipt.endDate}" pattern="yyyy-MM-dd"/></tr>
			<tr>
				<th>行</th>
				<th>客户类别</th>
				<th>客户名称</th>
				<th>应收款</th>
				<th>实收款</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${list}" var="fReceipt" varStatus="status">
			<tr>
				<td>
					${status.index+1}
				</td>
				<td>
					${fReceipt.travelUnit.sclass.name}
				</td>
				<td>
					${fReceipt.travelUnit.name}
				</td>
				<td>
					<fmt:formatNumber value="${fReceipt.htje}" pattern="#.####"/>
				</td>
				<td>
					<fmt:formatNumber value="${fReceipt.je}" pattern="#.####"/>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</c:if>
	<c:if test="${not empty type && type eq '3'}">
	<table id="contentTableTwo" class="table table-bordered">
		<thead>
		<tr>长沙市优彼食品应收款欠款明细</tr></br>
			<tr>开始时间：<fmt:formatDate value="${fReceipt.startDate}" pattern="yyyy-MM-dd"/> |结束时间：<fmt:formatDate value="${fReceipt.endDate}" pattern="yyyy-MM-dd"/></tr>
			<tr>
				<th>行</th>
				<th>客户类别</th>
				<th>客户名称</th>
				<th>订单单号</th>
				<th>欠款日期</th>
				<th>应收款</th>
				<th>实收款</th>
				<th>欠款金额</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${list}" var="fReceipt" varStatus="status">
			<tr>
				<td>
					${status.index+1}
				</td>
				<td>
					${fReceipt.travelUnit.sclass.name}
				</td>
				<td>
					${fReceipt.travelUnit.name}
				</td>
				<td>
					${fReceipt.ddbh}
				</td>
				<td>
					<fmt:formatDate value="${fReceipt.receiptDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatNumber value="${fReceipt.htje}" pattern="#.####"/>
				</td>
				<td>
					<fmt:formatNumber value="${fReceipt.je}" pattern="#.####"/>
				</td>
				<td>
					<fmt:formatNumber value="${fReceipt.receiptMode}" pattern="#.####"/><%--欠款金额查询出来后保存在receiptMode字段中--%>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</c:if>
</body>
</html>