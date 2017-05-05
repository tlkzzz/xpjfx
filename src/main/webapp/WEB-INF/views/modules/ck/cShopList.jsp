<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>购物车管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
        var listCount = 0;
        var state = ${cShop.state};
        $(document).ready(function() {
			listCount = ${page.count};
			if(listCount==0){
                openAddForm('${cShop.id}','${cShop.state}');
            }
		});
		function openAddForm(id,state) {
            top.$.jBox.open("iframe:${ctx}/ck/cShop/form?id="+id+"&state="+state, "订单添加", 700, $(top.document).height()-180, {
                buttons:{"确定":"ok"}, submit:function (v,f,h) {
                    if(v=='ok')location.reload();
                },loaded:function(h){
                    $(".jbox-content", top.document).css("overflow-y","hidden");
                }
            });
        }
        function submitOrder() {
            if(listCount>0){
                if(state==2||state==3||state==9){
                    //填写收款信息后提交订单
                    top.$.jBox.open("iframe:${ctx}/ck/cRkckddinfo/submitOrder", "提交订单", 700, $(top.document).height()-180, {
                        buttons:{"确定":"ok"}, submit:function (v,f,h) {
                            if(v=='ok')location.reload();
                        },loaded:function(h){
                            $(".jbox-content", top.document).css("overflow-y","hidden");
                        }
                    });
                }else if(state==5){//退货付款
                    //填写收款信息后提交订单
                    top.$.jBox.open("iframe:${ctx}/ck/cRkckddinfo/submitOrderPayment", "提交订单", 700, $(top.document).height()-180, {
                        buttons:{"确定":"ok"}, submit:function (v,f,h) {
                            if(v=='ok')location.reload();
                        },loaded:function(h){
                            $(".jbox-content", top.document).css("overflow-y","hidden");
                        }
                    });
                }else{
                    //选择仓库后提交订单
                    top.$.jBox.open("iframe:${ctx}/ck/cRkckddinfo/selectHouse", "仓库选择", 700, $(top.document).height()-180, {
                        buttons:{"确定":"ok"},loaded:function(h){
                            $(".jbox-content", top.document).css("overflow-y","hidden");
                        }
                    });
                }
            }else {
                top.$.jBox.tip("请添加订单后再提交！","系统提示","warning");
            }
        }
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
		<li><a href="${ctx}/ck/cRkckddinfo/${urlParam}?state=${cShop.state}">
			<c:if test="${cShop.state eq '0'}">临时采购列表</c:if>
			<c:if test="${cShop.state eq '1'}">采购入库列表</c:if>
			<c:if test="${cShop.state eq '2'}">出库录单列表</c:if>
			<c:if test="${cShop.state eq '3'}">其他出库列表</c:if>
			<c:if test="${cShop.state eq '4'}">报废录单列表</c:if>
			<c:if test="${cShop.state eq '5'}">退货录单列表</c:if>
			<c:if test="${cShop.state eq '9'}">预售单列表</c:if>
		</a></li>
		<li class="active"><a href="">订单添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="cShop" action="${ctx}/ck/cShop/cgAdd" method="post" class="breadcrumb form-search">
		<input name="state" type="hidden" value="${cShop.state}"/>
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns"><input class="btn btn-primary" type="button" onclick="submitOrder()" value="提交订单"/></li>
			<li class="btns"><input class="btn btn-primary" type="button" onclick="openAddForm('','${cShop.state}')" value="添加"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>订单编号</th>
				<th>商品</th>
				<th>供应商</th>
				<th>客户</th>
				<th>数量</th>
				<th>金额</th>
				<th>需求时间</th>
				<th>修改时间</th>
				<th>备注</th>
				<shiro:hasPermission name="ck:cCginfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="cShop">
			<tr>
				<td><a href="javascript:void(0)" onclick="openAddForm('${cShop.id}')">
					${cShop.spbh}
				</a></td>
				<td>
						${cShop.goods.name}
				</td>
				<td>
					${cShop.supplier.name}
				</td>
				<td>
					${cShop.store.name}
				</td>
				<td>
					${cShop.nub}
				</td>
				<td>
					${cShop.je}
				</td>
				<td>
					<fmt:formatDate value="${cShop.xqdate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${cShop.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${cShop.remarks}
				</td>
				<shiro:hasPermission name="ck:cCginfo:edit"><td>
    				<a href="javascript:void(0)" onclick="openAddForm('${cShop.id}','')">修改</a>
					<a href="${ctx}/ck/cShop/delete?id=${cShop.id}" onclick="return confirmx('确认要删除该订单吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>