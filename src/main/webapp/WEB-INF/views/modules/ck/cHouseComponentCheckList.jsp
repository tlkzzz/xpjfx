<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>分量盘点管理</title>
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
		<li class="active"><a href="${ctx}/ck/cHouseComponentCheck/">分量盘点列表</a></li>
		<shiro:hasPermission name="ck:cHouseComponentCheck:edit"><li><a href="${ctx}/ck/cHouseComponentCheck/form">分量盘点添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="cHouseComponentCheck" action="${ctx}/ck/cHouseComponentCheck/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>仓库：</label>
				<form:select path="house.id">
					<form:option value="" label="请选择"></form:option>
					<form:options items="${houseList}" itemLabel="name" itemValue="id" htmlEscape="false"></form:options>
				</form:select>
			</li>
			<li><label>盘点时间：</label>
				<input name="checkDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${cHouseComponentCheck.checkDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>状态：</label>
				<form:select path="state" >
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('storeState')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>仓库</th>
				<th>成本总价值</th>
				<th>销售总价值</th>
				<th>盘点时间</th>
				<th>修改时间</th>
				<th>备注</th>
				<shiro:hasPermission name="ck:cHouseComponentCheck:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="cHouseComponentCheck">
			<tr>
				<td><a href="${ctx}/ck/cHouseComponentCheck/form?id=${cHouseComponentCheck.id}">
					${cHouseComponentCheck.house.name}
				</a></td>
				<td>
					${cHouseComponentCheck.cbzje}
				</td>
				<td>
					${cHouseComponentCheck.sszje}
				</td>
				<td>
					<fmt:formatDate value="${cHouseComponentCheck.checkDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${cHouseComponentCheck.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${cHouseComponentCheck.remarks}
				</td>
				<shiro:hasPermission name="ck:cHouseComponentCheck:edit"><td>
    				<a href="${ctx}/ck/cHouseComponentCheck/form?id=${cHouseComponentCheck.id}">修改</a>
					<a href="${ctx}/ck/cHouseComponentCheck/delete?id=${cHouseComponentCheck.id}" onclick="return confirmx('确认要删除该分量盘点吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>