<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>采购订单管理</title>
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
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li <c:if test="${empty type ||type eq '1'}">class="active"</c:if>><a href="${ctx}/ck/cCgzbinfo/xslist">品项统计</a></li>
		<li <c:if test="${not empty type&&type eq '2'}">class="active"</c:if>><a href="${ctx}/ck/cCgzbinfo/xslist?type=2">大类别统计</a></li>
			<li <c:if test="${not empty type&&type eq '3'}" >class="active" </c:if>><a href="${ctx}/ck/cCgzbinfo/xslist?type=3">小类别统计</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="cCgzbinfo" action="${ctx}/ck/cCgzbinfo/xslist" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input name="type" type="hidden" value="${type}"/>
		<ul class="ul-form">
			<li><label>报废时间：</label>
				<input name="startDate" id="startDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					   value="<fmt:formatDate value="${cCgzbinfo.startDate}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd', maxDate:'#F{$dp.$D(\'endDate\')}',isShowClear:false});"/>

				<input name="endDate" id="endDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					   value="<fmt:formatDate value="${cCgzbinfo.endDate}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd', minDate:'#F{$dp.$D(\'startDate\')}',isShowClear:false});"/>
			</li>
			<li><label>商品：</label>
				<form:select path="goods.id">
					<form:option value="" label="请选择"></form:option>
					<form:options items="${goodsList}" itemValue="id" itemLabel="name"></form:options>
				</form:select>
			</li>
			<li class="control-group">
				<label class="control-label">商品类别:</label>
				<li class="controls">
					<sys:treeselect id="gclass" name="goods.gclass.id" value="${cCgzbinfo.goods.gclass.parent.id}" labelName="goods.gclass.parent.name" labelValue="${cCgzbinfo.goods.gclass.parent.name}"
									title="父级编号" url="/ck/cGclass/treeData" extId="${cCgzbinfo.goods.gclass.id}" cssClass="" allowClear="true"/>
				</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<c:if test="${empty type || type eq '1'}">
		<table id="contentTable" class="table table-striped table-bordered table-condensed">
			<thead>
			<tr>长沙市优彼食品业务员品项销售表-品项统计</tr></br>
			<tr>开始时间：<fmt:formatDate value="${cCgzbinfo.startDate}" pattern="yyyy-MM-dd"/> |结束时间：<fmt:formatDate value="${cCgzbinfo.endDate}" pattern="yyyy-MM-dd"/></tr>
			<tr>
				<th>行</th>
				<th>商品类别</th>
				<th>品名,规格</th>
				<th>大单位</th>
				<th>合计金额</th>
				<th>管理员</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${list}" var="cCgzbinfo" varStatus="status">
				<tr>
					<td>
							${status.index+1}
					</td>
					<td>
							${cCgzbinfo.goods.gclass.parent.name}
					</td>
					<td>
							${cCgzbinfo.goods.name},${cCgzbinfo.goods.spec.name}
					</td>
					<td>
							${cCgzbinfo.goods.small.name}
					</td>
					<td>
							${cCgzbinfo.rknub*cCgzbinfo.jg}
					</td>
					<td>
							${cCgzbinfo.goods.name}
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</c:if>

	<c:if test="${not empty type && type eq '2'}">
		<table id="contentTable" class="table table-striped table-bordered table-condensed">
			<thead>
			<tr>长沙市优彼食品业务员品项销售表-大类别</tr></br>
			<tr>开始时间：<fmt:formatDate value="${cCgzbinfo.startDate}" pattern="yyyy-MM-dd"/> |结束时间：<fmt:formatDate value="${cCgzbinfo.endDate}" pattern="yyyy-MM-dd"/></tr>
			<tr>
				<th>行</th>
				<th>商品类别</th>
				<th>合计金额</th>
				<th>管理员</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${list}" var="cCgzbinfo" varStatus="status">
				<tr>
					<td>
							${status.index+1}
					</td>
					<td>
				           ${cCgzbinfo.goods.gclass.parent.name}
					</td>
					<td>
							${cCgzbinfo.rknub*cCgzbinfo.jg}
					</td>
					<td>
							${cCkinfo.store.name}
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</c:if>

	<c:if test="${not empty type && type eq '3'}">
		<table id="contentTable" class="table table-striped table-bordered table-condensed">
			<thead>
			<tr>长沙市优彼食品业务员品项销售表-小类别统计</tr></br>
			<tr>开始时间：<fmt:formatDate value="${cCgzbinfo.startDate}" pattern="yyyy-MM-dd"/> |结束时间：<fmt:formatDate value="${cCgzbinfo.endDate}" pattern="yyyy-MM-dd"/></tr>
			<tr>
				<th>行</th>
				<th>商品类别</th>
				<th>合计金额</th>
				<th>管理员</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${list}" var="cCgzbinfo" varStatus="status">
			<tr>
					<td>
							${status.index+1}
					</td>
					<td>
							${cCgzbinfo.goods.gclass.name}
					</td>
					<td>
							${cCgzbinfo.nub*cCgzbinfo.jg}
					</td>
					<td>
							${cCgzbinfo.goods.gclass.name}
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</c:if>

</body>
</html>