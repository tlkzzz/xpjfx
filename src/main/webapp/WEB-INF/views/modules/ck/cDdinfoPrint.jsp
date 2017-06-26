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
		<div class="heard">
			<p class="text1">打印日期:<fmt:formatDate value="${date}" pattern="yyyy-MM-dd HH:mm:ss"/>   &nbsp;&nbsp;&nbsp;第1页,共1页</p>
			<p class="text2">新平江分销平台</p>
		</div>
		<h1>长沙优彼食品-销售单</h1>
		<p>出库单号: ${cRkckddinfo.ddbh}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;出库日期:<fmt:formatDate value="${cRkckddinfo.rkckdate}" pattern="yyyy-MM-dd HH:mm:ss"/>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;发货仓库:${cRkckddinfo.cHouse.name}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;业务员:${cRkckddinfo.createBy.name}<br>
			购货单位:${store.name}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;电话:${store.dh}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;详细地址:${store.dz}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;联系人:${store.lxr}
		</p>
		<table width="100%" border="1px" cellspacing="0" cellpadding="0">
			<tr class="center">
				<td>行</td>
				<td>零售条码</td>
				<td>商品名称</td>
				<td>规格型号</td>
				<td>销售数量</td>
				<td>数量</td>
				<td>单位</td>
				<td>单价</td>
				<td>金额</td>
				<td>生产日期</td>
				<td>备注</td>
			</tr>
			<c:forEach items="${list}" var="dd" varStatus="status">
			<tr>
				<td>${status.index+1}</td>
				<td>${dd.goods.tm}</td>
				<td>${dd.goods.name}</td>
				<td>${dd.goods.spec.name}</td>
				<td>${dd.type}</td>
				<td>${dd.nub}</td>
				<td>${dd.goods.small.name}</td>
				<td>${dd.je}</td>
				<td>${dd.je*dd.nub}</td>
				<td>2017-06-14</td>
				<td>${dd.remarks}</td>
			</tr>
			</c:forEach>
			<tr><td colspan="11"><h2>合计金额:${CNMoney}(¥${sumMoney})&nbsp;合计数量(小):${sumNub}<br><br>合计:${sumMoney}元</h2></td>
			</tr>
			<tr><td colspan= "11">经手人:${cRkckddinfo.createBy.name}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;仓库管理员:
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;制单人:${user.name}
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;配送业务员:</td></tr>
		</table>
		<p>备注:销售:快速发货样品</p>
	</div>
</body>
</html>