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
			<div class="heard">
				<p class="text1">打印日期:<fmt:formatDate value="${date}" pattern="yyyy-MM-dd HH:mm:ss"/>   &nbsp;&nbsp;&nbsp;第1页,共1页</p>
				<p class="text2">新平江分销平台</p>
			</div>
			<h1>长沙优彼食品    采购入库单</h1>
			<p>入库单号: ${cCgzbinfo.orderCode}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;入库日期:<fmt:formatDate value="${cCgzbinfo.rkDate}" pattern="yyyy-MM-dd"/><br>
				入库仓库:${cCgzbinfo.house.name}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;供应商:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;制单人:${user.name}
			</p>
			<table width="100%" border="1px" cellspacing="0" cellpadding="0">
				<tr class="center">
					<td>行</td>
					<td>零售条码</td>
					<td>商品名称</td>
					<td>规格型号</td>
					<td>入库数量</td>
					<td>数量</td>
					<td>单位</td>
					<td>保质期</td>
					<td>单价</td>
					<td>金额</td>
					<td>备注</td>
				</tr>
				<c:forEach items="${list}" var="cc" varStatus="status">
				<tr>
					<td>${status.index}</td>
					<td>${cc.goods.tm}</td>
					<td>${cc.goods.name}</td>
					<td>${cc.goods.spec.name}</td>
					<td>${cc.rknub}</td>
					<td>${cc.goods.cd}</td>
					<td>${cc.goods.small.name}</td>
					<td>${cc.goods.bzq}</td>
					<td>${cc.jg}</td>
					<td>${cc.goods.cd*cc.jg}</td>
					<td>${cc.remarks}</td>
				</tr>
				</c:forEach>
				<tr><td colspan="11"><h2>合计金额:${CNMoney}(¥${sumMoney})&nbsp;合计数量(小):${sumNub}</h2></td>
				</tr>
				<tr><td colspan="11"><h2>入库总金额:${sumMoney}元,   应付:${sumMoney}元</h2></td>
				</tr>
				<tr><td colspan="11">经手人:${cCgzbinfo.createBy.name}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;仓库管理员:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;会计签字:</td></tr>
			</table>
			<p>-手工入库</p>
		</div>
	</div>
</body>
</html>