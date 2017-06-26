<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta charset="utf-8" />
	<title>代办事宜</title>
	<script type="text/javascript">
//        $(document).ready(function() {
//
//        });
//        function page(n,s){
//            $("#pageNo").val(n);
//            $("#pageSize").val(s);
//            $("#searchForm").submit();
//            return false;
//        }
	</script>
	<link href="${ctxStatic}/css/global.css" type="text/css" rel="stylesheet" />
	<link href="${ctxStatic}/css/reset.css" type="text/css" rel="stylesheet" />
</head>
<body>
<div id="xpjContainer">
	<div id="xpjContent">
		<ul class="nav nav-tabs">
			<li class="active"><a href="${ctx}/cRkckddinfo/shenhelist/"></a></li>
		</ul>
		<form:form id="searchForm" modelAttribute="cRkckddinfo" action="${ctx}/ck/cRkckddinfo/shenhelist" method="post" class="breadcrumb form-search">
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
			<input name="state" type="hidden" value="${cRkckddinfo.state}"/>
			<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
			<ul class="ul-form">
				<li class="clearfix"></li>
			</ul>
		</form:form>
		<ul id="menuContent">
			<li>
				<a href="${ctx}/ck/cRkckddinfo/cgList?state=1">
					<img src="${ctxStatic}/images/rksh.jpg" alt="入库审核" />
					<h3>入库审核(<span>${ruku}</span>)</h3>
				</a>
				<%--<a href="#">--%>
					<%--<img src="${ctxStatic}/images/yksh.jpg" alt="移库审核" />--%>
					<%--<h3>移库审核(<span>0</span>)</h3>--%>
				<%--</a>--%>
				<a href="${ctx}/ck/cRkckddinfo/libraryList?state=2">
					<img src="${ctxStatic}/images/cksh.jpg" alt="出库审核" />
					<h3>出库审核(<span>${a}</span>)</h3>
				</a>
				<a href="${ctx}/ck/cRkckddinfo/libraryList?state=5">
					<img src="${ctxStatic}/images/thsh.jpg" alt="退货审核" />
					<h3>退货审核(<span>${th}</span>)</h3>
				</a>
			</li>
			<li>
				<%--<a href="#">--%>
					<%--<img src="${ctxStatic}/images/yksq.jpg" alt="移库申请" />--%>
					<%--<h3>移库申请(<span>0</span>)</h3>--%>
				<%--</a>--%>
				<%--<a href="${ctx}/ck/cRkckddinfo/libraryList?state=5">--%>
					<%--<img src="${ctxStatic}/images/thsq.jpg" alt="退货申请" />--%>
					<%--<h3>退货申请(<span>0</span>)</h3>--%>
				<%--</a>--%>
				<a href="${ctx}/ck/cRkckddinfo/libraryList?state=4">
					<img src="${ctxStatic}/images/bfsh.jpg" alt="报废审核" />
					<h3>报废审核(<span>${bf}</span>)</h3>
				</a>
				<%--<a href="#">--%>
					<%--<img src="${ctxStatic}/images/pysh.jpg" alt="盘盈审核" />--%>
					<%--<h3>盘盈审核(<span>0</span>)</h3>--%>
				<%--</a>--%>
			</li>
			<li class="clearBorder">
				<%--<a href="#">--%>
					<%--<img src="${ctxStatic}/images/pksh.jpg" alt="盘亏审核" />--%>
					<%--<h3>盘亏审核(<span>0</span>)</h3>--%>
				<%--</a>--%>
				<%--<a href="#">--%>
					<%--<img src="${ctxStatic}/images/cwsh.jpg" alt="财务审核" />--%>
					<%--<h3>财务审核(<span>0</span>)</h3>--%>
				<%--</a>--%>
					<a href="${ctx}/ck/cStore/list">
					<img src="${ctxStatic}/images/khsh.jpg" alt="客户审核" />
					<h3>客户审核(<span>0</span>)</h3>
				</a>
				<%--<a href="#">--%>
					<%--<img src="${ctxStatic}/images/fhsk.jpg" alt="发货收款" />--%>
					<%--<h3>发货收款(<span>0</span>)</h3>--%>
				<%--</a>--%>
			</li>
		</ul>
	</div>
</div>
</body>
</html>