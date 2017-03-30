<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>仓库管理</title>
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
        function changeMianStock(id,classId) {
			if(id!=''&&classId!=''){
			    $.ajax({
			        url:"${ctx}/ck/cHouse/changeIsMainStock",
					type:"POST",
					dataType:"json",
					data:{id:id},
					success:function (data) {
						if(data){
						    $(".icon-ok").attr("class","icon-remove");
						    $("#"+classId).attr("class","icon-ok");
						}
                    }
				})
			}
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ck/cHouse/">仓库列表</a></li>
		<shiro:hasPermission name="ck:cHouse:edit"><li><a href="${ctx}/ck/cHouse/form">仓库添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="cHouse" action="${ctx}/ck/cHouse/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>仓库名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>仓库编号：</label>
				<form:input path="code" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>仓库名称</th>
				<th>仓库编号</th>
				<th>创建时间</th>
				<th>备注</th>
				<shiro:hasPermission name="ck:cHouse:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="cHouse">
			<tr>
				<td><a href="${ctx}/ck/cHouse/form?id=${cHouse.id}">
					${cHouse.name}
				</a>
				</td>
				<td>
				 ${cHouse.code}
				</td>
		<!--		<td>
					<a href="javascript:void(0)" onclick="changeMianStock('${cHouse.id}','Class${cHouse.id}')">
						<i <c:if test="${cHouse.state=='1'}">class="icon-ok"</c:if>
						<c:if test="${cHouse.state=='0'}">class="icon-remove"</c:if>
						   id="Class${cHouse.id}"></i></a>
				</td>
				-->
				<td>
					<fmt:formatDate value="${cHouse.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${cHouse.remarks}
				</td>
				<shiro:hasPermission name="ck:cHouse:edit"><td>
    				<a href="${ctx}/ck/cHouse/form?id=${cHouse.id}">修改</a>
					<a href="${ctx}/ck/cHouse/delete?id=${cHouse.id}" onclick="return confirmx('确认要删除该仓库吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>