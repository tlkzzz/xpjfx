<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>${fTransferAccount.transferType eq '0'?'转账':'调账'}管理</title>
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
		<li class="active"><a href="">${fTransferAccount.transferType eq '0'?'转账':'调账'}列表</a></li>
		<shiro:hasPermission name="cw:fTransferAccount:edit"><c:if test="${fTransferAccount.transferType eq '0'}"><li><a href="${ctx}/cw/fTransferAccount/transferForm">转账</a></li></c:if></shiro:hasPermission>
		<shiro:hasPermission name="cw:fTransferAccount:edit"><c:if test="${fTransferAccount.transferType eq '1'}"><li><a href="${ctx}/cw/fTransferAccount/receiptForm?transferType=1">应收款增加</a></li></c:if></shiro:hasPermission>
		<shiro:hasPermission name="cw:fTransferAccount:edit"><c:if test="${fTransferAccount.transferType eq '2'}"><li><a href="${ctx}/cw/fTransferAccount/receiptForm?transferType=2">应收款减少</a></li></c:if></shiro:hasPermission>
		<shiro:hasPermission name="cw:fTransferAccount:edit"><c:if test="${fTransferAccount.transferType eq '3'}"><li><a href="${ctx}/cw/fTransferAccount/paymentAddForm">应付款增加</a></li></c:if></shiro:hasPermission>
		<shiro:hasPermission name="cw:fTransferAccount:edit"><c:if test="${fTransferAccount.transferType eq '4'}"><li><a href="${ctx}/cw/fTransferAccount/paymentReduceForm">应付款减少</a></li></c:if></shiro:hasPermission>
		<shiro:hasPermission name="cw:fTransferAccount:edit"><c:if test="${fTransferAccount.transferType eq '5'}"><li><a href="${ctx}/cw/fTransferAccount/capitalAddForm">资金增加</a></li></c:if></shiro:hasPermission>
		<shiro:hasPermission name="cw:fTransferAccount:edit"><c:if test="${fTransferAccount.transferType eq '6'}"><li><a href="${ctx}/cw/fTransferAccount/capitalReduceForm">资金减少</a></li></c:if></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="fTransferAccount" action="${ctx}/cw/fTransferAccount/?transferType=${fTransferAccount.transferType}" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>来往单位：</label>
				<form:input path="travelUnit" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>转出账户：</label>
				<form:input path="outAccount" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>转入账户：</label>
				<form:input path="inAccount" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>来往单位</th>
				<th>转出账户</th>
				<th>转入账户</th>
				<th>交易金额</th>
				<th>交易时间</th>
				<th>经手人</th>
				<th>审核状态</th>
				<th>审核人</th>
				<th>备注</th>
				<th>修改时间</th>
				<%--<shiro:hasPermission name="cw:fTransferAccount:edit"><th>操作</th></shiro:hasPermission>--%>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="fTransferAccount">
			<tr>
				<td>
					${fTransferAccount.travelUnit}
				</td>
				<td>
					${fTransferAccount.outAccount}
				</td>
				<td>
					${fTransferAccount.inAccount}
				</td>
				<td>
					${fTransferAccount.transMoney}
				</td>
				<td>
					<fmt:formatDate value="${fTransferAccount.transferDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fTransferAccount.jsr.name}
				</td>
				<td>
					${fns:getDictLabel(fTransferAccount.approvalStatus, "storeState", "")}
				</td>
				<td>
					${fTransferAccount.auditor.name}
				</td>
				<td>
					${fTransferAccount.remarks}
				</td>
				<td>
					<fmt:formatDate value="${fTransferAccount.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<%--<shiro:hasPermission name="cw:fTransferAccount:edit"><td>
    				<a href="${ctx}/cw/fTransferAccount/form?id=${fTransferAccount.id}">修改</a>
					<a href="${ctx}/cw/fTransferAccount/delete?id=${fTransferAccount.id}" onclick="return confirmx('确认要删除该转账调账吗？', this.href)">删除</a>
				</td></shiro:hasPermission>--%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>