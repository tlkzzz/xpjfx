<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>微信红包管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function () {
                $("#searchForm").attr("action","${ctx}/test/shopWxpacket/exportExcel");
                $("#searchForm").submit();
            });
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/test/shopWxgoods/");
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/test/shopWxgoods/">微信红包列表</a></li>
		<shiro:hasPermission name="test:shopWxgoods:edit"><li><a href="${ctx}/test/shopWxgoods/form">微信红包添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="shopWxgoods" action="${ctx}/test/shopWxgoods/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li class="btns">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
				<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>商品编号</th>
				<th>名称</th>
				<th>销售区域</th>
				<th>生产商</th>
				<th>状态</th>
				<th>生产时间</th>
				<th>活动名称</th>
				<shiro:hasPermission name="test:shopWxgoods:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="shopWxgoods">
			<tr>
				<td><a href="${ctx}/test/shopWxgoods/form?id=${shopWxgoods.id}">
					${shopWxgoods.spbh}
				</a></td>
				<td>
					${shopWxgoods.name}
				</td>
				<td>
					${shopWxgoods.xsqy}
				</td>
				<td>
					${shopWxgoods.scs}
				</td>
				<td>
					${shopWxgoods.state}
				</td>
				<td>
					${shopWxgoods.scsj}
				</td>
				<td>
					${shopWxgoods.hdmc}
				</td>
				<shiro:hasPermission name="test:shopWxgoods:edit"><td>
    				<a href="${ctx}/test/shopWxgoods/form?id=${shopWxgoods.id}">修改</a>
					<a href="${ctx}/test/shopWxgoods/delete?id=${shopWxgoods.id}" onclick="return confirmx('确认要删除该微信红包吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>