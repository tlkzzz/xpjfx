<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>安全库存预警报表</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
			function derive(){
        var form = $("#searchForm");
        window.open('${ctx}/ck/cHgoods/aqkExcel?'+form.serialize());
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ck/cHgoods/aqkcReport">安全库存预警</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="cHgoods" action="${ctx}/ck/cHgoods/aqkcReport" method="post" class="breadcrumb form-search">
		<ul class="ul-form">
			<li><label>商品名称：</label>
				<form:select path="goods.id">
					<form:option value="" label="请选择"/>
					<form:options items="${goodsList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>仓库名称：</label>
				<form:select path="house.id">
					<form:option value="" label="请选择"/>
					<form:options items="${houseList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input id=""  class="btn btn-primary" type="button" onclick="derive()" value="导出"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTableOne" class="table table-bordered">
		<thead>
		<tr>安全库存预警报表</tr></br>
			<tr>
				<th>行</th>
				<th>仓库名称</th>
				<th>商品名称</th>
				<th>规格型号</th>
				<th>大单位</th>
				<th>安全库存</th>
				<th>当前库存</th>
				<th>差量</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${list}" var="cHgoods" varStatus="status">
			<tr>
				<td>
					${status.index+1}
				</td>
				<td>
					${cHgoods.house.name}
				</td>
				<td>
					${cHgoods.goods.name}
				</td>
				<td>
					${cHgoods.goods.spec.name}
				</td>
				<td>
					${cHgoods.goods.big.name}
				</td>
				<td>
					${cHgoods.yjnub}
				</td>
				<td>
					${cHgoods.nub}
				</td>
				<td>
					<c:if test="${not empty cHgoods.yjnub}">
					<fmt:formatNumber value="${cHgoods.yjnub-cHgoods.nub}" pattern="#"/>
					</c:if>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</body>
</html>