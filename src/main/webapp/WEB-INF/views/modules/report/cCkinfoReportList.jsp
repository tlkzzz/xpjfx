<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>出库信息管理</title>
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
		<li <c:if test="${empty type ||type eq '1'}">class="active"</c:if>><a href="${ctx}/ck/cCkinfo/rkReport">商品明细</a></li>
		<li <c:if test="${not empty type&&type eq '2'}">class="active"</c:if>><a href="${ctx}/ck/cCkinfo/rkReport?type=2">客户汇总</a></li>
		<li <c:if test="${not empty type&&type eq '3'}">class="active"</c:if>><a href="${ctx}/ck/cCkinfo/rkReport?type=3">客户商品汇总</a></li>
		<li <c:if test="${not empty type&&type eq '4'}">class="active"</c:if>><a href="${ctx}/ck/cCkinfo/rkReport?type=4">客户大类汇总</a></li>
		<li <c:if test="${not empty type&&type eq '5'}">class="active"</c:if>><a href="${ctx}/ck/cCkinfo/rkReport?type=5">商品汇总</a></li>
		<li <c:if test="${not empty type&&type eq '6'}">class="active"</c:if>><a href="${ctx}/ck/cCkinfo/rkReport?type=6">仓库商品汇总</a></li>
		<li <c:if test="${not empty type&&type eq '7'}">class="active"</c:if>><a href="${ctx}/ck/cCkinfo/rkReport?type=7">品牌汇总</a></li>
	<!--	<li <c:if test="${not empty type&&type eq '8'}">class="active"</c:if>><a href="${ctx}/ck/cCkinfo/rkReport?type=8">商品销售类型汇总</a></li> -->
		<li <c:if test="${not empty type&&type eq '9'}">class="active"</c:if>><a href="${ctx}/ck/cCkinfo/rkReport?type=9">单据明细</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="cCkinfo" action="${ctx}/ck/cCkinfo/rkReport" method="post" class="breadcrumb form-search">
		<input name="type" type="hidden" value="${type}"/>
		<ul class="ul-form">
			<li><label>开始时间：</label>
				<input name="startDate" id="startDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					   value="<fmt:formatDate value="${cCkinfo.startDate}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd', maxDate:'#F{$dp.$D(\'endDate\')}',isShowClear:false});"/>
			</li>
			<li><label>截止时间：</label>
				<input name="endDate" id="endDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					   value="<fmt:formatDate value="${cCkinfo.endDate}" pattern="yyyy-MM-dd"/>"
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
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<c:if test="${empty type || type eq '1'}">
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
		<tr>长沙市优彼食品销售报表-商品明细</tr></br>
		<tr>开始时间：<fmt:formatDate value="${cCkinfo.startDate}" pattern="yyyy-MM-dd"/> |结束时间：<fmt:formatDate value="${cCkinfo.endDate}" pattern="yyyy-MM-dd"/></tr>
		<tr>
			    <th>行</th>
				<th>业务员</th>
				<th>仓库</th>
				<th>客户</th>
				<th>商品</th>
				<th>规格型号</th>
				<th>数量</th>
				<th>单价</th>
				<th>成本价</th>
				<th>毛利</th>
				<th>操作人</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${list}" var="cCkinfo" varStatus="status">
			<tr>
				<td>
					${status.index+1}
				</td>
				<td>
						${cCkinfo.jsr.name}
				</td>
				<td>
					${cCkinfo.house.name}
				</td>
				<td>
				   ${cCkinfo.store.name}
				</td>
				<td>
					${cCkinfo.goods.name}
				</td>
				<td>
					${cCkinfo.goods.spec.name}
				</td>
				<td>
					${cCkinfo.nub}
				</td>
				<td>
					${cCkinfo.goods.sj}
				</td>
				<td>
					${cCkinfo.goods.cbj}
				</td>
				<td>
					${cCkinfo.goods.sj-cCkinfo.goods.cbj}
				</td>
				<td>
					${cCkinfo.jsr.name}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</c:if>
	<c:if test="${not empty type && type eq '2'}">
		<table id="contentTableTwo" class="table table-bordered">
			<thead>
			<tr>长沙市优彼食品移库报表-客户汇总</tr></br>
			<tr>开始时间：<fmt:formatDate value="${cCkinfo.startDate}" pattern="yyyy-MM-dd"/> |结束时间：<fmt:formatDate value="${cCkinfo.endDate}" pattern="yyyy-MM-dd"/></tr>
			<tr>
				<th>客户大类</th>
				<th>客户小类</th>
				<th>客户名称</th>
				<th>联系人</th>
				<th>联系电话</th>
				<th>详细地址</th>
				<th>客户卡号</th>
				<th>销售金额</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${list}" var="cCkinfo" varStatus="status">
				<tr>
					<td>
							${cCkinfo.store.area.parent.name}
					</td>
					<td>
							${cCkinfo.store.area.name}
					</td>
					<td>
							${cCkinfo.store.name}
					</td>
					<td>
							${cCkinfo.store.lxr}
					</td>
					<td>
							${cCkinfo.store.dh}
					</td>
					<td>
							${cCkinfo.store.dz}
					</td>
					<td>
							${cCkinfo.store.khhzh}
					</td>
					<td>
							${cCkinfo.goods.cbj}
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</c:if>
	<c:if test="${not empty type && type eq '3'}">
		<table id="contentTableTwo" class="table table-bordered">
			<thead>
			<tr>长沙市优彼食品移库报表-客户商品汇总</tr></br>
			<tr>开始时间：<fmt:formatDate value="${cCkinfo.startDate}" pattern="yyyy-MM-dd"/> |结束时间：<fmt:formatDate value="${cCkinfo.endDate}" pattern="yyyy-MM-dd"/></tr>
			<tr>
				<th>行</th>
				<th>客户大类</th>
				<th>客户小类</th>
				<th>客户名称</th>
				<th>联系人</th>
				<th>联系电话</th>
				<th>详细地址</th>
				<th>条码</th>
				<th>商品编号</th>
				<th>商品大类</th>
				<th>商品小类</th>
				<th>商品名称</th>
				<th>规格名称</th>
				<th>大单位</th>
				<th>中单位</th>
				<th>小单位</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${list}" var="cCkinfo" varStatus="status">
				<tr>
					<td>
							${status.index+1}
					</td>
					<td>
							${cCkinfo.store.area.parent.name}
					</td>
					<td>
							${cCkinfo.store.area.name}
					</td>
					<td>
							${cCkinfo.store.name}
					</td>
					<td>
							${cCkinfo.store.lxr}
					</td>
					<td>
							${cCkinfo.store.dh}
					</td>
					<td>
							${cCkinfo.store.dz}
					</td>
					<td>
							${cCkinfo.goods.tm}
					</td>
					<td>
							${cCkinfo.goods.sort}
					</td>
					<td>
							${cCkinfo.goods.gclass.parent.name}
					</td>
					<td>
							${cCkinfo.goods.gclass.name}
					</td>
					<td>
							${cCkinfo.goods.name}
					</td>
					<td>
							${cCkinfo.goods.spec.name}
					</td>
					<td>
							${cCkinfo.goods.big.name}
					</td>
					<td>
							${cCkinfo.goods.zong.name}
					</td>
					<td>
							${cCkinfo.goods.small.name}
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</c:if>
	<c:if test="${not empty type && type eq '4'}">
		<table id="contentTableTwo" class="table table-bordered">
			<thead>
			<tr>长沙市优彼食品移库报表-客户大类商品汇总</tr></br>
			<tr>开始时间：<fmt:formatDate value="${cCkinfo.startDate}" pattern="yyyy-MM-dd"/> |结束时间：<fmt:formatDate value="${cCkinfo.endDate}" pattern="yyyy-MM-dd"/></tr>
			<tr>
				<th>行</th>
				<th>客户大类</th>
				<th>商品名称</th>
				<th>规格型号</th>
				<th>销售数量</th>
				<th>销售金额</th>
				<th>退货数量</th>
				<th>退货金额</th>
				<th>总数量</th>
				<th>总金额</th>
				<th>成本金额</th>
				<th>毛利金额</th>
				<th>毛利率</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${list}" var="cCkinfo" varStatus="status">
				<tr>
					<td>
							${status.index+1}
					</td>
					<td>
							${cCkinfo.store.area.parent.name}
					</td>
					<td>
							${cCkinfo.goods.name}
					</td>
					<td>
							${cCkinfo.goods.spec.name}
					</td>
					<td>
							${cCkinfo.nub}
					</td>
					<td>
							${cCkinfo.goods.sj}
					</td>
					<td>
							${cCkinfo.ddinfo.thsl}
					</td>

					<td>
							${cCkinfo.ddinfo.thje}
					</td>
					<td>
							${cCkinfo.nub}
					</td>
					<td>
							${cCkinfo.goods.sj}
					</td>
					<td>
							${cCkinfo.goods.cbj}
					</td>
					<td>
						<fmt:formatNumber value="${cCkinfo.goods.sj-cCkinfo.goods.cbj}" pattern="#.####"/>
					</td>
					<td>
						<fmt:formatNumber value="${((cCkinfo.goods.sj-cCkinfo.goods.cbj)/cCkinfo.goods.sj)}" />%
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</c:if>
	<c:if test="${not empty type && type eq '5'}">
		<table id="contentTableTwo" class="table table-bordered">
			<thead>
			<tr>长沙市优彼食品移库报表-商品汇总</tr></br>
			<tr>开始时间：<fmt:formatDate value="${cCkinfo.startDate}" pattern="yyyy-MM-dd"/> |结束时间：<fmt:formatDate value="${cCkinfo.endDate}" pattern="yyyy-MM-dd"/></tr>
			<tr>
				<th>行</th>
				<th>商品名称</th>
				<th>规格型号</th>
				<th>销售数量</th>
				<th>销售金额</th>
				<th>退货数量</th>
				<th>退货金额</th>
				<th>均价</th>
				<th>总数量</th>
				<th>总金额</th>
				<th>成本金额</th>
				<th>毛利金额</th>
				<th>毛利率</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${list}" var="cCkinfo" varStatus="status">
				<tr>
					<td>
							${status.index+1}
					</td>
					<td>
							${cCkinfo.goods.name}
					</td>
					<td>
							${cCkinfo.goods.spec.name}
					</td>
					<td>
							${cCkinfo.nub}
					</td>
					<td>
							${cCkinfo.goods.sj}
					</td>
					<td>
							${cCkinfo.ddinfo.thsl}
					</td>

					<td>
							${cCkinfo.ddinfo.thje}
					</td>
					<td>
						<fmt:formatNumber value="${cCkinfo.goods.sj/cCkinfo.nub}" />
					</td>
					<td>

							${cCkinfo.nub}
					</td>
					<td>
							${cCkinfo.goods.sj}
					</td>
					<td>
							${cCkinfo.goods.cbj}
					</td>
					<td>
						<fmt:formatNumber value="${cCkinfo.goods.sj-cCkinfo.goods.cbj}" pattern="#.####"/>
					</td>
					<td>
						<fmt:formatNumber value="${((cCkinfo.goods.sj-cCkinfo.goods.cbj)/cCkinfo.goods.sj)}" />%
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</c:if>
	<c:if test="${not empty type && type eq '6'}">
		<table id="contentTableTwo" class="table table-bordered">
			<thead>
			<tr>长沙市优彼食品移库报表-仓库商品汇总</tr></br>
			<tr>开始时间：<fmt:formatDate value="${cCkinfo.startDate}" pattern="yyyy-MM-dd"/> |结束时间：<fmt:formatDate value="${cCkinfo.endDate}" pattern="yyyy-MM-dd"/></tr>
			<tr>
				<th>行</th>
				<th>仓库</th>
				<th>商品名称</th>
				<th>规格型号</th>
				<th>销售数量</th>
				<th>销售金额</th>
				<th>退货数量</th>
				<th>退货金额</th>
				<th>总数量</th>
				<th>总金额</th>
				<th>成本金额</th>
				<th>毛利金额</th>
				<th>毛利率</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${list}" var="cCkinfo" varStatus="status">
				<tr>
					<td>
							${status.index+1}
					</td>
					<td>
							${cCkinfo.house.name}
					</td>
					<td>
							${cCkinfo.goods.name}
					</td>
					<td>
							${cCkinfo.goods.spec.name}
					</td>
					<td>
							${cCkinfo.nub}
					</td>
					<td>
							${cCkinfo.goods.sj}
					</td>
					<td>
							${cCkinfo.ddinfo.thsl}
					</td>

					<td>
							${cCkinfo.ddinfo.thje}
					</td>
					<td>

							${cCkinfo.nub}
					</td>
					<td>
							${cCkinfo.goods.sj}
					</td>
					<td>
							${cCkinfo.goods.cbj}
					</td>
					<td>
						<fmt:formatNumber value="${cCkinfo.goods.sj-cCkinfo.goods.cbj}" pattern="#.####"/>
					</td>
					<td>
						<fmt:formatNumber value="${((cCkinfo.goods.sj-cCkinfo.goods.cbj)/cCkinfo.goods.sj)}" />%
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</c:if>
	<c:if test="${not empty type && type eq '7'}">
		<table id="contentTableTwo" class="table table-bordered">
			<thead>
			<tr>长沙市优彼食品移库报表-品牌汇总</tr></br>
			<tr>开始时间：<fmt:formatDate value="${cCkinfo.startDate}" pattern="yyyy-MM-dd"/> |结束时间：<fmt:formatDate value="${cCkinfo.endDate}" pattern="yyyy-MM-dd"/></tr>
			<tr>
				<th>行</th>
				<th>品牌名称</th>
				<th>銷售数量</th>
				<th>销售金额</th>
				<th>退货数量</th>
				<th>退货金额</th>
				<th>总数量</th>
				<th>总金额</th>
				<th>成本金额</th>
				<th>促销成本</th>
				<th>毛利金额</th>
				<th>毛利率</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${list}" var="cCkinfo" varStatus="status">
				<tr>
					<td>
							${status.index+1}
					</td>
					<td>
							${cCkinfo.goods.bands.name}
					</td>
					<td>
							${cCkinfo.nub}
					</td>
					<td>
							${cCkinfo.goods.sj}
					</td>
					<td>
							${cCkinfo.ddinfo.thsl}
					</td>

					<td>
							${cCkinfo.ddinfo.thje}
					</td>
					<td>
							${cCkinfo.nub}
					</td>
					<td>
							${cCkinfo.goods.sj}
					</td>
					<td>

							${cCkinfo.goods.cbj}
					</td>
					<td>
							${cCkinfo.ddinfo.yhje}
					</td>
					<td>
							${cCkinfo.goods.sj-cCkinfo.goods.cbj}
					</td>
					<td>
						<fmt:formatNumber value="${((cCkinfo.goods.sj-cCkinfo.goods.cbj)/cCkinfo.goods.sj)}" />%
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</c:if>
	<c:if test="${not empty type && type eq '8'}">
		<table id="contentTableTwo" class="table table-bordered">
			<thead>
			<tr>长沙市优彼食品移库报表-商品销售类型汇总</tr></br>
			<tr>开始时间：<fmt:formatDate value="${cCkinfo.startDate}" pattern="yyyy-MM-dd"/> |结束时间：<fmt:formatDate value="${cCkinfo.endDate}" pattern="yyyy-MM-dd"/></tr>
			<tr>
				<th>行</th>
				<th>客户大类</th>
				<th>商品名称</th>
				<th>规格型号</th>
				<th>销售数量</th>
				<th>销售金额</th>
				<th>退货数量</th>
				<th>退货金额</th>
				<th>总数量</th>
				<th>总金额</th>
				<th>成本金额</th>
				<th>毛利金额</th>
				<th>毛利率</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${list}" var="cCkinfo" varStatus="status">
				<tr>
					<td>
							${status.index+1}
					</td>
					<td>
							${cCkinfo.store.name}
					</td>
					<td>
							${cCkinfo.store.name}
					</td>
					<td>
							${cCkinfo.store.name}
					</td>
					<td>
							${cCkinfo.store.lxr}
					</td>
					<td>
							${cCkinfo.store.dh}
					</td>
					<td>
							${cCkinfo.store.dz}
					</td>
					<td>
							${cCkinfo.goods.tm}
					</td>
					<td>
							${cCkinfo.goods.sort}
					</td>
					<td>
							${cCkinfo.goods.gclass.parent.name}
					</td>
					<td>
							${cCkinfo.goods.gclass.name}
					</td>
					<td>
							${cCkinfo.goods.name}
					</td>
					<td>
							${cCkinfo.goods.spec.name}
					</td>
					<td>
							${cCkinfo.goods.big.name}
					</td>
					<td>
							${cCkinfo.goods.zong.name}
					</td>
					<td>
							${cCkinfo.goods.small.name}
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</c:if>
	<c:if test="${not empty type && type eq '9'}">
		<table id="contentTableTwo" class="table table-bordered">
			<thead>
			<tr>长沙市优彼食品移库报表-单据明细</tr></br>
			<tr>开始时间：<fmt:formatDate value="${cCkinfo.startDate}" pattern="yyyy-MM-dd"/> |结束时间：<fmt:formatDate value="${cCkinfo.endDate}" pattern="yyyy-MM-dd"/></tr>
			<tr>
				<th>行</th>
				<th>出库时间</th>
				<th>业务员</th>
				<th>仓库</th>
				<th>金额</th>
				<th>优惠金额</th>
				<th>优惠后金额</th>
				<th>成本金额</th>
				<th>毛利</th>
				<th>毛利率</th>
				<th>操作人</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${list}" var="cCkinfo" varStatus="status">
				<tr>
					<td>
							${status.index+1}
					</td>
					<td>
						<fmt:formatDate value="${cCkinfo.ckdate}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td>
							${cCkinfo.jsr.name}
					</td>
					<td>
							${cCkinfo.house.name}
					</td>
					<td>
							${cCkinfo.goods.sj}
					</td>
					<td>
							${cCkinfo.ddinfo.yhje}
					</td>
					<td>
							${cCkinfo.goods.sj-cCkinfo.ddinfo.yhje}
					</td>
					<td>
							${cCkinfo.goods.cbj}
					</td>
					<td>
							${cCkinfo.goods.sj-cCkinfo.goods.cbj}
					</td>
					<td>
						<fmt:formatNumber value="${((cCkinfo.goods.sj-cCkinfo.goods.cbj)/cCkinfo.goods.sj)}" />%
					</td>
                    <td>
							${cCkinfo.jsr.name}
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</c:if>
	<div class="pagination">${page}</div>
</body>
</html>