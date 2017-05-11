<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>业务员订单查询</title>
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
        function inHouse(id) {
            $.jBox.open("iframe:${ctx}/ck/cHgoods/cgInHouse?cGddId="+id,
                "采购入库",$(top.document).width()-420,$(top.document).height()-120,{
                    buttons:{"确定":true}, loaded:function(h){
                        $(".jbox-content", top.document).css("overflow-y","hidden");
                    },closed:function () {
                        $("#searchForm").submit();
                    }
                });
        }
        function changeState(id,state) {
            top.$.jBox.confirm("确认要修改订单状态吗？","系统提示",function(v,h,f) {
                if(v=="ok"){
                    $.ajax({
                        url:"${ctx}/ck/cCgzbinfo/changeState",
                        dataType:"json",
                        type:"POST",
                        data:{id:id,state:state},
                        success:function (data) {
                            if(data){
                                $("#searchForm").submit();
                            }
                        }
                    });
                }
            });
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ck/cCgzbinfo/ywyInquire">业务员订单查询</a></li>
	</ul>

	<form:form id="searchForm" modelAttribute="cCgzbinfo" action="${ctx}/ck/cCgzbinfo/ywyInquire" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>商品：</label>
				<form:select path="goods.id">
					<form:option value="" label="请选择"></form:option>
					<form:options items="${goodsList}" itemValue="id" itemLabel="name"></form:options>
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
				<th>商品</th>
				<th>采购数量</th>
				<th>供应商</th>
				<th>价格</th>
				<th>实际入库量</th>
				<th>入库时间</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="cCgzbinfo">
			<tr>
				<td>
					${cCgzbinfo.goods.name}
				</td>
				<td>
					${cCgzbinfo.nub}
				</td>
				<td>
					<c:if test="${not empty cCgzbinfo.rkinfo}">${cCgzbinfo.rkinfo.supplier.name}</c:if>
				</td>
				<td>
					${cCgzbinfo.jg}
				</td>
				<td>
					${cCgzbinfo.rknub}
				</td>
				<td>
					<fmt:formatDate value="${cCgzbinfo.rkDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>