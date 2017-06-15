<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>业务员销售查询</title>
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
        function viewSubOrder(id){
			if(id!=''){//iframe打开子订单列表
                top.$.jBox.open("iframe:${ctx}/ck/cDdinfo/cgDdList?rkckddinfo.id="+id, "订单列表", 1000, $(top.document).height()-180, {
                    buttons:{"确定":"ok"}, loaded:function(h){
                        $(".jbox-content", top.document).css("overflow-y","hidden");
                    }
                });
        	}
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ck/cRkckddinfo/clerkSales">业务员销售列表	</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="cRkckddinfo" action="${ctx}/ck/cRkckddinfo/clerkSales" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input name="state" type="hidden" value="${cRkckddinfo.state}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>订单号</th>
				<th>审批状态</th>
				<th>审批人</th>
				<th>审批时间</th>
				<th>创建时间</th>
				<th>备注</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="cRkckddinfo">
			<tr>
				<td><a href="javascript:void(0)" onclick="viewSubOrder('${cRkckddinfo.id}')">
					${cRkckddinfo.ddbh}
				</a></td>
				<td>
					${fns:getDictLabel(cRkckddinfo.issp, "storeState", "")}
				</td>
				<td>
					${cRkckddinfo.spr.name}
				</td>
				<td>
					<fmt:formatDate value="${cRkckddinfo.spsj}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${cRkckddinfo.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${cRkckddinfo.remarks}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>