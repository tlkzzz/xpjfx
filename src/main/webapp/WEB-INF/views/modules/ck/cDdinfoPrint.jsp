<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>销售单打印</title>
	<meta name="decorator" content="default"/>
	<style>
		li{ float:left; list-style:none}
		ul{width:500px;}
	</style>
	<script type="text/javascript">
	</script>
</head>
<body>
	<div>
		<ul>
			<li><label>打印时间：</label><fmt:formatDate value="${date}" pattern="yyyy-MM-dd HH:mm:ss"/></li>
			<li><label>第1页，共1页</label></li>
			<li><label>新平江分销平台</label></li>
		</ul></br>
		<ul>
			<li>长沙优彼食品-销售单</li>
		</ul></br>
		<ul>
			<li><label>出库单号：</label>${cRkckddinfo.ddbh}</li>
			<li><label>出库日期：</label><fmt:formatDate value="${cRkckddinfo.spsj}" pattern="yyyy-MM-dd HH:mm:ss"/></li>
			<li><label>发货仓库：</label>${cRkckddinfo.cHouse.name}</li>
			<li><label>业务员：</label>${cRkckddinfo.createBy.name}</li>
		</ul></br>
		<ul>
			<li><label>购货单位：</label>${cRkckddinfo.createBy.name}</li>
			<li><label>电话：</label></li>
			<li><label>详细地址：</label></li>
			<li><label>联系人：</label></li>
		</ul>
	</div>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>行</th>
				<th>零售条码</th>
				<th>商品名称</th>
				<th>规格型号</th>
				<th>销售数量</th>
				<th>数量</th>
				<th>单位</th>
				<th>单价</th>
				<th>金额</th>
				<th>生产日期</th>
				<th>备注</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${list}" var="cDdinfo" varStatus="status">
			<tr>
				<td>
					${status.index+1}
				</td>
				<td>
					${cDdinfo.goods.tm}
				</td>
				<td>
					${cDdinfo.goods.name}
				</td>
				<td>
					${cDdinfo.goods.spec.name}
				</td>
				<td>
					${cDdinfo.type}
				</td>
				<td>
					${cDdinfo.nub}
				</td>
				<td>
					${cDdinfo.goods.small.name}
				</td>
				<td>
					<fmt:formatNumber value="${cDdinfo.je}" pattern="#.####"/>
				</td>
				<td>
					<fmt:formatNumber value="${cDdinfo.je*cDdinfo.nub}" pattern="#.####"/>
				</td>
				<td>
					<fmt:formatDate value="${cDdinfo.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${cDdinfo.remarks}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div>

	</div>
</body>
</html>