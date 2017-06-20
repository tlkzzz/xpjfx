<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>优惠表管理</title>
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
		<li class="active"><a href="${ctx}/cw/fDiscount/">优惠表列表</a></li>
	<!--	<shiro:hasPermission name="cw:fDiscount:edit"><li><a href="${ctx}/cw/fDiscount/form">优惠表添加</a></li></shiro:hasPermission> -->
	</ul>
	<form:form id="searchForm" modelAttribute="fDiscount" action="${ctx}/cw/fDiscount/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">

			<li><label>客户：</label>
				<form:select path="store.id">
					<form:option value="" label="请选择"></form:option>
					<form:options items="${storeList}" itemLabel="name" itemValue="id" htmlEscape="false"></form:options>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>订单编号</th>
			    <th>客户名称</th>
			    <th>优惠金额</th>
			    <th>类型</th>
				<th>修改时间</th>
				<shiro:hasPermission name="cw:fDiscount:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="fDiscount">
			<tr>
			    <td>${fDiscount.ddid.ddbh}</td>
				<td>${fDiscount.store.name}</td>
			    <td>${fDiscount.yhje}</td>
			    <td>${fDiscount.lx}</td>
				<td>
					<fmt:formatDate value="${fDiscount.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="cw:fDiscount:edit"><td>
					<a href="${ctx}/cw/fDiscount/delete?id=${fDiscount.id}" onclick="return confirmx('确认要删除该优惠表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>