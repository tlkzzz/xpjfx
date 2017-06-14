<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商品管理</title>
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
        function changeGoodsShow(goodsId,id,state) {
			if(id==""){
			    if(top.confirm("还未填写商城商品信息，是否前往填写？")){
					window.location.href = "${ctx}/p/shopGoods/form?id="+goodsId;
				}
			    return;
			}
			if(state==""){
			    top.$.jBox.tip("状态不能为空！","warning","系统提示！")
			}
			$.ajax({
			    url:"${ctx}/p/shopGoods/changeGoodsShow",
				type:"POST",
				dataType:"json",
				data:{id:id,goodsShow:state},
				success:function (data) {
					if(data!="true"){
					    top.$.jBox.tip(data,"warning","系统提示！");
					}
                }
			})
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ck/cGoods/list">商品列表</a></li>
		<shiro:hasPermission name="ck:cGoods:edit"><li><a href="${ctx}/ck/cGoods/form">商品添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="cGoods" action="${ctx}/ck/cGoods/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>商品名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>商品编码：</label>
				<form:input path="sort" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>条码：</label>
				<form:input path="tm" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>品牌：</label>
				<form:select path="bands.id">
					<form:option value="" label="请选择"></form:option>
					<form:options items="${bandsList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>供应商：</label>
				<form:select path="supplier.id">
					<form:option value="" label="请选择"></form:option>
					<form:options items="${supplierList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
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
				<th>商品名称</th>
				<th>商品分类</th>
				<th>规格</th>
				<th>单位</th>
				<th>商品编码</th>
				<th>品牌</th>
				<th>条码</th>
				<th>供应商</th>
				<th>成本价</th>
				<th>售价</th>
				<th>预警售价</th>
				<th>参考成本价</th>
				<th>创建时间</th>
				<th>是否上架</th>
				<th>备注</th>
				<shiro:hasPermission name="ck:cGoods:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="cGoods">
			<tr>
				<td><a href="${ctx}/ck/cGoods/form?id=${cGoods.id}">
					${cGoods.name}
				</a></td>
				<td>
					${cGoods.gclass.name}
				</td>
				<td>
					${cGoods.spec.name}
				</td>
				<td>
					${cGoods.unit.name}
				</td>
				<td>
					${cGoods.sort}
				</td>
				<td>
					${cGoods.bands.name}
				</td>
				<td>
					${cGoods.tm}
				</td>
				<td>
					${cGoods.supplier.name}
				</td>
				<td>
					￥${cGoods.cbj}
				</td>
				<td>
					￥${cGoods.sj}
				</td>
				<td>
                	￥${cGoods.yjsj}
                </td>
                <td>
                    ￥${cGoods.ckcbj}
                </td>
				<td>
					<fmt:formatDate value="${cGoods.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${cGoods.shopGoods.goodsShow}
				</td>
				<td>
					${cGoods.remarks}
				</td>
				<shiro:hasPermission name="ck:cGoods:edit"><td>
    				<a href="${ctx}/ck/cGoods/form?id=${cGoods.id}">修改</a>
					<c:if test="${cGoods.shopGoods.goodsShow=='1'}">
						<a href="javascript:void(0)" onclick="changeGoodsShow('${cGoods.id}','${cGoods.shopGoods.id}','0')">下架</a>
					</c:if>
					<c:if test="${cGoods.shopGoods.goodsShow=='0'||cGoods.shopGoods.goodsShow==null}">
						<a href="javascript:void(0)" onclick="changeGoodsShow('${cGoods.id}','${cGoods.shopGoods.id}','1')">上架</a>
					</c:if>
					<a href="${ctx}/ck/cGoods/delete?id=${cGoods.id}" onclick="return confirmx('确认要删除该商品吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>