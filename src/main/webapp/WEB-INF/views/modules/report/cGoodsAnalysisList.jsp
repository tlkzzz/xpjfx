<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商品价格一览表</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ck/cGoods/goodsAnalysis">商品价格一览表</a></li>
	</ul>
	<sys:message content="${message}"/>
	<table id="contentTableOne" class="table table-bordered">
		<thead>
		<tr>长沙市优彼食品-商品价格一览表</tr></br>
			<tr>
				<th>行</th>
				<th>商品分类</th>
				<th>品牌</th>
				<th>商品名称</th>
				<th>商品编码</th>
				<th>规格型号</th>
				<th>成本单价</th>
				<th>参考成本价</th>
				<th>销售单价</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${list}" var="cGoods" varStatus="status">
			<tr>
				<td>
					${status.index+1}
				</td>
				<td>
					${cGoods.gclass.name}
				</td>
				<td>
					${cGoods.bands.name}
				</td>
				<td>
					${cGoods.name}
				</td>
				<td>
					${cGoods.sort}
				</td>
				<td>
					${cGoods.spec.name}
				</td>
				<td>
					<fmt:formatNumber value="${cGoods.cbj}" pattern="#.####"/>
				</td>
				<td>
					<fmt:formatNumber value="${cGoods.sj}" pattern="#.####"/>
				</td>
				<td>
					<fmt:formatNumber value="${cGoods.ckcbj}" pattern="#.####"/>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</body>
</html>