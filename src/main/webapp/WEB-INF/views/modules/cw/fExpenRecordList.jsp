<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>支出记录管理</title>
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
		<li class="active"><a href="${ctx}/cw/fExpenRecord/">支出记录列表</a></li>
		<!-- <shiro:hasPermission name="cw:fExpenRecord:edit"><li><a href="${ctx}/cw/fExpenRecord/form">支出记录添加</a></li></shiro:hasPermission> -->
	</ul>
	<form:form id="searchForm" modelAttribute="fExpenRecord" action="${ctx}/cw/fExpenRecord/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>订单编号</th>
				<th>支出帐号</th>
				<th>来往帐号</th>
				<th>支出金额</th>
				<th>支出方式</th>
				<th>支出时间</th>
				<th>备注</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="fExpenRecord">
			<tr>
				<td>
					${empty fExpenRecord.ddbh?"采购订单":fExpenRecord.ddbh}
				</td>
				<td>
					${fExpenRecord.expenAccount}
				</td>
				<td>
					${fExpenRecord.travelAccount}
				</td>
				<td>
					${fExpenRecord.expenMoney}
				</td>
				<td>
					${fns:getDictLabel(fExpenRecord.expenMode, "expenMode", "")}
				</td>
				<td>
					<fmt:formatDate value="${fExpenRecord.expenDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fExpenRecord.remarks}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>