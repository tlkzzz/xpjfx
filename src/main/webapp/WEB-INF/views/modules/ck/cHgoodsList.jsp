<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>库存管理</title>
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

          function derive(){
                            $.jBox.confirm("确认要导出数据吗？","系统提示",function(v,h,f){
                                if(v=="ok"){
                                    $("#searchForm").attr("action","${ctx}/ck/cHgoods/exportFile");
                                    $("#searchForm").submit();
                                    //还原默认action
                                    $("#searchForm").attr("action","${ctx}/ck/cHgoodslist");
                                }
                            },{buttonsFocus:1});
                            $('.jbox-body .jbox-icon').css('top','55px');
                        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ck/cHgoods/list">库存列表</a></li>
        <%--<shiro:hasPermission name="ck:cCgzbinfo:edit"><li><a href="${ctx}/ck/cCgzbinfo/form">采购入库</a></li></shiro:hasPermission>--%>
		<shiro:hasPermission name="ck:cHgoods:edit"><li><a href="${ctx}/ck/cHgoods/form">其他入库</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="cHgoods" action="${ctx}/ck/cHgoods/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>商品名称：</label>
				<form:input path="goods.name" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>仓库名称：</label>
				<form:input path="house.name" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li>最后入库时间：
			&nbsp;&nbsp;<input id="updateDate"  name="updateDate"  type="text" readonly="readonly" maxlength="64" class="input-medium Wdate" style="width:163px;"
            				value="<fmt:formatDate value="${cHgoods.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
            					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});"/>
                </li>
			<li >
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
			<input id=""  class="btn btn-primary" type="button" onclick="derive()" value="导出"/>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>所在仓库</th>
				<th>商品名称</th>
				<th>商品分类</th>
				<th>规格</th>
				<th>数量</th>
				<th>预警数</th>
				<th>商品编码</th>
				<th>品牌</th>
				<th>条码</th>
				<th>供应商</th>
				<th>成本价</th>
				<th>售价</th>
				<th>最后入库时间</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="cHgoods">
			<tr>
				<td>
					${cHgoods.house.name}
				</td>
				<td>
					${cHgoods.goods.name}
				</td>
				<td>
					${cHgoods.goods.gclass.name}
				</td>
				<td>
					${cHgoods.goods.spec.name}
				</td>
				<td>
					${cHgoods.unit}
				</td>
				<td>
					${cHgoods.yjnub}
				</td>
				<td>
					${cHgoods.goods.sort}
				</td>
				<td>
					${cHgoods.goods.bands.name}
				</td>
				<td>
					${cHgoods.goods.tm}
				</td>
				<td>
					${cHgoods.goods.supplier.name}
				</td>
				<td>
					￥${cHgoods.goods.cbj}
				</td>
				<td>
					￥${cHgoods.goods.sj}
				</td>
				<td>
                   <fmt:formatDate value="${cHgoods.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
                </td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>