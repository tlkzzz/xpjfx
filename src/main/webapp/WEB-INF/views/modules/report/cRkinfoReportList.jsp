<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>入库报表</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function derive(){
        var form = $("#searchForm");
        window.open('${ctx}/ck/cRkinfo/rkExcel?'+form.serialize());
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li <c:if test="${empty type ||type eq '1'}">class="active"</c:if>><a href="${ctx}/ck/cRkinfo/rkReport">入库报表</a></li>
		<li <c:if test="${not empty type&&type eq '2'}">class="active"</c:if>><a href="${ctx}/ck/cRkinfo/rkReport?type=2">商品汇总</a></li>
		<li <c:if test="${not empty type&&type eq '3'}">class="active"</c:if>><a href="${ctx}/ck/cRkinfo/rkReport?type=3">单据明细</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="cRkinfo" action="${ctx}/ck/cRkinfo/rkReport" method="post" class="breadcrumb form-search">
		<input name="type" type="hidden" value="${type}"/>
		<ul class="ul-form">
			<li><label>开始时间：</label>
				<input name="startDate" id="startDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					   value="<fmt:formatDate value="${cRkinfo.startDate}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd', maxDate:'#F{$dp.$D(\'endDate\')}',isShowClear:false});"/>
			</li>
			<li><label>截止时间：</label>
				<input name="endDate" id="endDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					   value="<fmt:formatDate value="${cRkinfo.endDate}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd', minDate:'#F{$dp.$D(\'startDate\')}',isShowClear:false});"/>
			</li>
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
	<c:if test="${empty type || type eq '1'}">
	<table id="contentTableOne" class="table table-bordered">
		<thead>
		<tr>长沙市优彼食品入库报表-商品明细</tr></br>
			<tr>开始时间：<fmt:formatDate value="${cRkinfo.startDate}" pattern="yyyy-MM-dd"/> |结束时间：<fmt:formatDate value="${cRkinfo.endDate}" pattern="yyyy-MM-dd"/></tr>
			<tr>
				<th>行</th>
				<th>仓库名称</th>
				<th>商品名称</th>
				<th>规格型号</th>
				<th>入库数量</th>
				<th>单价</th>
				<th>金额</th>
				<th>入库类型</th>
				<th>入库时间</th>
				<th>备注</th>
				<th>操作人</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${list}" var="cRkinfo" varStatus="status">
			<tr>
				<td>
					${status.index+1}
				</td>
				<td>
					${cRkinfo.house.name}
				</td>
				<td>
					${cRkinfo.goods.name}
				</td>
				<td>
					${cRkinfo.goods.spec.name}
				</td>
				<td>
					${cRkinfo.rknub}
				</td>
				<td>
					${cRkinfo.rkhcbj}
				</td>
				<td>
					<fmt:formatNumber value="${cRkinfo.rknub*cRkinfo.rkhcbj}" pattern="#.####"/>
				</td>
				<td>
					${fns:getDictLabel(cRkinfo.state, "rkState", "")}
				</td>
				<td>
					<fmt:formatDate value="${cRkinfo.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${cRkinfo.remarks}
				</td>
				<td>
					${cRkinfo.createBy.name}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</c:if>
	<c:if test="${not empty type && type eq '2'}">
	<table id="contentTableTwo" class="table table-bordered">
		<thead>
		<tr>长沙市优彼食品入库报表-商品汇总</tr></br>
			<tr>开始时间：<fmt:formatDate value="${cRkinfo.startDate}" pattern="yyyy-MM-dd"/> |结束时间：<fmt:formatDate value="${cRkinfo.endDate}" pattern="yyyy-MM-dd"/></tr>
			<tr>
				<th>行</th>
				<th>商品大类</th>
				<th>商品小类</th>
				<th>商品名称</th>
				<th>规格型号</th>
				<th>入库数量</th>
				<th>金额</th>
				<th>成本金额</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${list}" var="cRkinfo" varStatus="status">
			<tr>
				<td>
					${status.index+1}
				</td>
				<td>
					${cRkinfo.goods.gclass.parent.name}
				</td>
				<td>
					${cRkinfo.goods.gclass.name}
				</td>
				<td>
					${cRkinfo.goods.name}
				</td>
				<td>
					${cRkinfo.goods.spec.name}
				</td>
				<td>
					${cRkinfo.rknub}
				</td>
				<td>
					${cRkinfo.goods.sj}
				</td>
				<td>
					${cRkinfo.total}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</c:if>
	<c:if test="${not empty type && type eq '3'}">
	<table id="contentTableTree" class="table table-bordered">
		<thead>
		<tr>长沙市优彼食品入库报表-单据明细</tr></br>
			<tr>开始时间：<fmt:formatDate value="${cRkinfo.startDate}" pattern="yyyy-MM-dd"/> |结束时间：<fmt:formatDate value="${cRkinfo.endDate}" pattern="yyyy-MM-dd"/></tr>
			<tr>
				<th>行</th>
				<th>仓库名称</th>
				<th>入库类型</th>
				<th>供应商</th>
				<th>入库时间</th>
				<th>金额</th>
				<th>成本金额</th>
				<th>实付款</th>
				<th>备注</th>
				<th>操作人</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${list}" var="cRkinfo" varStatus="status">
			<tr>
				<td>
					${status.index+1}
				</td>
				<td>
					${cRkinfo.house.name}
				</td>
				<td>
					${fns:getDictLabel(cRkinfo.state, "rkState", "")}
				</td>
				<td>
					${cRkinfo.supplier.name}
				</td>
				<td>
					<fmt:formatDate value="${cRkinfo.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${cRkinfo.rknub*cRkinfo.goods.sj}
				</td>
				<td>
					<fmt:formatNumber value="${cRkinfo.rknub*cRkinfo.rkhcbj}" pattern="#.####"/>
				</td>
				<td>
                    <fmt:formatNumber value="${cRkinfo.rknub*cRkinfo.rkhcbj}" pattern="#.####"/>
				</td>
				<td>
					${cRkinfo.remarks}
				</td>
				<td>
					${cRkinfo.createBy.name}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</c:if>
</body>
</html>