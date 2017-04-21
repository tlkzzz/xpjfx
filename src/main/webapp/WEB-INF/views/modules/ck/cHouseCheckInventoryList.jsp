<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>盘点抄帐管理</title>
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
        function openHgoodsList(id) {
            top.$.jBox.open("iframe:${ctx}/ck/cHouseCheckInventory/inventoryList?id="+id, "抄帐列表", 700, $(top.document).height()-180, {
                buttons:{"确定":"ok"},loaded:function(h){
                    $(".jbox-content", top.document).css("overflow-y","hidden");
                }
            });
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ck/cHouseCheckInventory/">盘点抄帐列表</a></li>
		<shiro:hasPermission name="ck:cHouseCheckInventory:edit"><li><a href="${ctx}/ck/cHouseCheckInventory/form">盘点抄帐添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="cHouseCheckInventory" action="${ctx}/ck/cHouseCheckInventory/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>仓库：</label>
				<form:input path="house.id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>商品分类：</label>
				<form:input path="gclass.id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>盘点时间：</label>
				<input name="checkDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${cHouseCheckInventory.checkDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>状态：</label>
				<form:input path="state" htmlEscape="false" maxlength="1" class="input-medium"/>
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
				<th>商品分类</th>
				<th>盘点时间</th>
				<th>状态</th>
				<th>修改时间</th>
				<th>备注</th>
				<shiro:hasPermission name="ck:cHouseCheckInventory:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="cHouseCheckInventory">
			<tr>
				<td><a href="javascript:void(0)" onclick="openHgoodsList('${cHouseCheckInventory.id}')">
					${cHouseCheckInventory.house.name}
				</a></td>
				<td>
					${cHouseCheckInventory.gclass.name}
				</td>
				<td>
					<fmt:formatDate value="${cHouseCheckInventory.checkDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${cHouseCheckInventory.state}
				</td>
				<td>
					<fmt:formatDate value="${cHouseCheckInventory.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${cHouseCheckInventory.remarks}
				</td>
				<shiro:hasPermission name="ck:cHouseCheckInventory:edit"><td>
    				<a href="${ctx}/ck/cHouseCheckInventory/form?id=${cHouseCheckInventory.id}">修改</a>
					<a href="${ctx}/ck/cHouseCheckInventory/delete?id=${cHouseCheckInventory.id}" onclick="return confirmx('确认要删除该盘点抄帐吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>