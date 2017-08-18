<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>

<head>
	<title>欠供应商款记录</title>
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
                top.$.jBox.open("iframe:${ctx}/cw/fArrears/gyList?arrearsType.id="+id, "供应商欠款列表", 1000, $(top.document).height()-180, {
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
		<li class="active"><a href="${ctx}/cw/fArrears/storeList">欠供应商款记录</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="fArrears" action="${ctx}/cw/fArrears/storeList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>欠款单位：</label>
				<form:input path="arrearsUnit" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>欠款供应商：</label>
				<form:input path="unitName" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>欠款方式：</label>
				<form:select path="arrearsMode" >
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('arrearsMode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>供应商</th>
				<th>欠款方式</th>
				<th>欠款金额</th>
				<th>欠款日期</th>
				<th>备注</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="fArrears">
			<tr>
				<td><a href="javascript:void(0)" onclick="viewSubOrder('${fArrears.id}')">
						${fArrears.unitName}
				</a></td>
				<td>
					${fns:getDictLabel(fArrears.arrearsMode, "arrearsMode", "")}
				</td>
				<td>
					${fArrears.total}
				</td>
				<td>
					<fmt:formatDate value="${fArrears.arrearsDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fArrears.remarks}
				</td>
			</tr>
		</c:forEach>
		</tbody>
		<tr>
			<td colspan="2"><b>合计</b></td>
			<td colspan="3"><b>${Sum}￥</b></td>
		</tr>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>