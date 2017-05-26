<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商品价格一览表</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function derive(){
        var form = $("#searchForm");
        window.open('${ctx}/ck/cGoods/goodslistexcel?'+form.serialize());
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ck/cGoods/goodsAnalysis">商品价格一览表</a></li>
	</ul>

	<ul>
		<li class="btns"><input id=""  class="btn btn-primary" type="button" onclick="derive()" value="导出"/>
		</li>
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
				<th>成本小单价</th>
				<th>成本中单价</th>
				<th>成本大单价</th>
				<th>参考成本单价</th>
				<th>销售小单价</th>
				<th>销售中单价</th>
				<th>销售大单价</th>
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
					<c:if test="${fn:length(cGoods.spec.arrSpec)>2}">
					<fmt:formatNumber value="${cGoods.spec.arrSpec[2]*cGoods.cbj}" pattern="#.####"/>
					</c:if>
				</td>

				<td>
					<c:if test="${fn:length(cGoods.spec.arrSpec)<3}">
						<fmt:formatNumber value="${cGoods.spec.arrSpec[1]*cGoods.cbj}" pattern="#.####"/>
					</c:if>
					<c:if test="${fn:length(cGoods.spec.arrSpec)>2}">
						<fmt:formatNumber value="${cGoods.spec.arrSpec[1]*cGoods.spec.arrSpec[2]*cGoods.cbj}" pattern="#.####"/>
					</c:if>
				</td>
				<td>
					<fmt:formatNumber value="${cGoods.ckcbj}" pattern="#.####"/>
				</td>
				<td>
					<fmt:formatNumber value="${cGoods.sj}" pattern="#.####"/>
				</td>
				<td>
					<c:if test="${fn:length(cGoods.spec.arrSpec)>2}">
						<fmt:formatNumber value="${cGoods.spec.arrSpec[2]*cGoods.sj}" pattern="#.####"/>
					</c:if>
				</td>
				<td>
					<c:if test="${fn:length(cGoods.spec.arrSpec)<3}">
						<fmt:formatNumber value="${cGoods.spec.arrSpec[1]*cGoods.sj}" pattern="#.####"/>
					</c:if>
					<c:if test="${fn:length(cGoods.spec.arrSpec)>2}">
						<fmt:formatNumber value="${cGoods.spec.arrSpec[1]*cGoods.spec.arrSpec[2]*cGoods.sj}" pattern="#.####"/>
					</c:if>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</body>
</html>