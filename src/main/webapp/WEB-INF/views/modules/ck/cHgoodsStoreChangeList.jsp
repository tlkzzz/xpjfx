<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>库存调整管理</title>
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
        function openForm(id) {
            top.$.jBox.open("iframe:${ctx}/ck/cHgoods/changeStoreForm?id="+id, "调整库存", 700, $(top.document).height()-180, {
                buttons:{"确定":"ok"},loaded:function(h){
                    $(".jbox-content", top.document).css("overflow-y","hidden");
                }
            });
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ck/cHgoods/changeStore">库存列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="cHgoods" action="${ctx}/ck/cHgoods/changeStore" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>商品：</label>
				<form:select path="goods.id">
					<form:option value="" label="请选择"></form:option>
					<form:options items="${goodsList}" itemLabel="name" itemValue="id" htmlEscape="false"></form:options>
				</form:select>
			</li>
			<li><label>仓库：</label>
				<form:select path="house.id">
					<form:option value="" label="请选择"></form:option>
					<form:options items="${houseList}" itemLabel="name" itemValue="id" htmlEscape="false"></form:options>
				</form:select>
			</li>
			<li >
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>所在仓库</th>
				<th>商品分类</th>
				<th>商品编码</th>
				<th>商品名称</th>
				<th>规格</th>
				<th>数量</th>
				<th>成本价</th>
				<th>售价</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="cHgoods">
			<tr>
				<td>
					${cHgoods.house.name}
				</td>
				<td>
					${cHgoods.goods.gclass.name}
				</td>
				<td>
					${cHgoods.goods.sort}
				</td>
				<td>
					${cHgoods.goods.name}
				</td>
				<td>
					${cHgoods.goods.spec.name}
				</td>
				<td>
					${cHgoods.unit}
				</td>
				<td>
					￥${cHgoods.goods.cbj}
				</td>
				<td>
					￥${cHgoods.goods.sj}
				</td>
				<td>
					<shiro:hasPermission name="ck:cHgoods:edit">
						<a href="javascript:void(0)" onclick="openForm('${cHgoods.id}')">调整库存</a>
					</shiro:hasPermission>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>