<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>销售单打印</title>
	<script src="${ctxStatic}/jqprint/jquery-1.4.4.min.js" type="text/javascript"></script>
	<script src="${ctxStatic}/jqprint/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
	<script src="${ctxStatic}/jqprint/jquery.jqprint-0.3.js" type="text/javascript"></script>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/diao.css">
	<script language="javascript">
        function printPage(){
            $("#print").jqprint();
        }
	</script>
</head>
<body>
	<div>
		<input type="button" value="打印" onclick="printPage()" />
	</div>
	<div id="print">
		<div>
			<h1>新平江食品贸易商行&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;欠款</h1>
			<table width="100%" border="1px" cellspacing="0" cellpadding="0">
				<tr>
					<td colspan="2">单据日期:<fmt:formatDate value="${date}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td colspan="4" rowspan="2">送货地址:${store.dz}</td>
					<td colspan="2">单据编号:${cRkckddinfo.ddbh}</td>
				</tr>
				<tr>
					<td colspan="2">客户:${store.name}</td>
					<td colspan="2">经手人:${cRkckddinfo.createBy.name}</td>
				</tr>
				<tr>
					<td colspan="2">客户电话:${store.phone}</td>
					<td colspan="4">客户地址:${store.dz}</td>
					<td colspan="2">区域电话:${store.dh}</td>
				</tr>
				<tr class="center">
					<td>行号</td>
					<td>商品名称</td>
					<td>规格</td>
					<td>单位</td>
					<td>单价</td>
					<td>数量</td>
					<td>金额</td>
					<td>活动</td>
				</tr>
				<c:forEach items="${list}" var="cd" varStatus="status">
				<tr>
					<td>${status.index+1}</td>
					<td>${cd.goods.name}</td>
					<td>${cd.goods.spec.name}</td>
					<td>${cd.goods.big.name}|${cd.goods.zong.name}|${cd.goods.small.name}</td>
					<td>${cd.je}</td>
					<td>${cd.nub}</td>
					<td>${cd.je*cd.nub}</td>
					<td></td>
				</tr>
				</c:forEach>
				<tr>
					<td colspan="5">共小计:</td>
					<td>${sumNub}</td>
					<td>${sumMoney}</td>
					<td></td>
				</tr>
				<tr>
					<td colspan="2">合计数量:${sumNub}</td>
					<td colspan="4">本单金额:${sumMoney}</td>
					<td colspan="2">本单金额大写:${CNMoney}</td>
				</tr>
			</table>
			<h3>地址:高桥大市场酒水食品城23栋11号</h3>
			<h3 style="margin-top: 10px">备注:请于15天之内与本公司联系  联系电话0731-85985923<br>投诉电话:0731-85714846</h3>
		</div>
	</div>
</body>
</html>