<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>总订单管理</title>
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
                top.$.jBox.open("iframe:${ctx}/ck/cDdinfo/cgDdList?rkckddinfo.id="+id, "订单列表", 700, $(top.document).height()-180, {
                    buttons:{"确定":"ok"}, loaded:function(h){
                        $(".jbox-content", top.document).css("overflow-y","hidden");
                    }
                });
        	}
        }
        function auditing(id,state) {
            top.$.jBox.confirm("确认审批通过吗？","系统提示",function(v,h,f) {
                if(v=="ok"){
                    $.ajax({
                        url:"${ctx}/ck/cCgzbinfo/auditing?id="+id+"&state="+state,
                        type:"pose",
                        dataType:"json",
                        success:function (data) {
                            if(data){
                                top.$.jBox.tip("审核成功","系统提示","warning");
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
		<li class="active"><a href="${ctx}/ck/cRkckddinfo/cgList?state=${cRkckddinfo.state}">总订单列表</a></li>
		<shiro:hasPermission name="ck:cCginfo:edit"><li><a href="${ctx}/ck/cShop/cgAdd">订单添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="cRkckddinfo" action="${ctx}/ck/cRkckddinfo/cgList" method="post" class="breadcrumb form-search">
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
				<shiro:hasPermission name="ck:cCginfo:edit"><th>操作</th></shiro:hasPermission>
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
					${cRkckddinfo.spr}
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
				<shiro:hasPermission name="ck:cCginfo:edit"><td>
					<c:if test="${cRkckddinfo.issp!='1'}"><a href="javascript:void(0)" onclick="auditing('${cRkckddinfo.id}','${cRkckddinfo.state}')">审批</a></c:if>
					<a href="${ctx}/ck/cRkckddinfo/delete?id=${cRkckddinfo.id}" onclick="return confirmx('确认要删除该总订单吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>