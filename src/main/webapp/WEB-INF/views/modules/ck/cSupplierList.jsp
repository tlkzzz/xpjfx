<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>供应商管理</title>
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
                            $("#searchForm").attr("action","${ctx}/ck/cSupplier/exportFile");
                            $("#searchForm").submit();
                            //还原默认action
                            $("#searchForm").attr("action","${ctx}/ck/cSupplierlist");
                        }
                    },{buttonsFocus:1});
                    $('.jbox-body .jbox-icon').css('top','55px');
                }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ck/cSupplier/">供应商列表</a></li>
		<shiro:hasPermission name="ck:cSupplier:edit"><li><a href="${ctx}/ck/cSupplier/form">供应商添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="cSupplier" action="${ctx}/ck/cSupplier/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>供应商编码：</label>
				<form:input path="code" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>供应商名称：</label>
            	<form:input path="name" htmlEscape="false" maxlength="64" class="input-medium"/>
            </li>
			<li><label>联系人：</label>
				<form:input path="lxr" htmlEscape="false" maxlength="150" class="input-medium"/>
			</li>
			<li class="btns">
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
			    <th>供应商名称</th>
				<th>供应商编码</th>
				<th>备注</th>
				<th>电话</th>
				<th>联系人</th>
				<th>电子邮件</th>
				<th>创建时间</th>
                				<th>备注</th>
				<shiro:hasPermission name="ck:cSupplier:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="cSupplier">
			<tr>
				<td><a href="${ctx}/ck/cSupplier/form?id=${cSupplier.id}">
				 ${cSupplier.name}

				</a></td>
				<td>
                 ${cSupplier.code}
                </td>
				<td>
					${cSupplier.gysjc}
				</td>
				<td>
                	${cSupplier.dh}
                </td>
                 <td>
                	${cSupplier.lxr}
                </td>
                <td>
                    ${cSupplier.email}
                </td>
                <td>
                    <fmt:formatDate value="${cSupplier.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
                </td>
                <td>
                	${cSupplier.remarks}
                </td>
				<shiro:hasPermission name="ck:cSupplier:edit"><td>
    				<a href="${ctx}/ck/cSupplier/form?id=${cSupplier.id}">修改</a>
					<a href="${ctx}/ck/cSupplier/delete?id=${cSupplier.id}" onclick="return confirmx('确认要删除该供应商吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>