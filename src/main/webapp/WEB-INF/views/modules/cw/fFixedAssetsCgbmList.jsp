<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>固定资产采购变卖管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		   function auditingState(storeId,tdId) {
        			if(storeId!=''){
        			    $.ajax({
        			        url:"${ctx}/cw/fFixedAssetsCgbm/shenhe",
        					dataType:"json",
        					data:{id:storeId},
        					type:"POST",
        					success:function (data) {
        						if(data)$("#"+tdId).text("审核通过");
                                $("#messageBox").text();
                                top.$.jBox.tip("审核通过成功！",'warning');
                                window.location.reload();
                            },
        					error:function () {
                                top.$.jBox.tip("审核失败，请刷新后重试！",'warning');
                            }
        				});
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
		<li class="active"><a href="${ctx}/cw/fFixedAssetsCgbm/">固定资产采购变卖列表</a></li>
		<shiro:hasPermission name="cw:fFixedAssetsCgbm:edit"><li><a href="${ctx}/cw/fFixedAssetsCgbm/form">固定资产采购变卖添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="fFixedAssetsCgbm" action="${ctx}/cw/fFixedAssetsCgbm/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>

		<ul class="ul-form">
			<li><label>资产名称：</label>
        				<form:select path="ffixedassets.id">
        					<form:option value="" label="请选择"></form:option>
        					<form:options items="${FFixedAssetslist}" itemLabel="name" itemValue="id" htmlEscape="false"/>
        				</form:select>
        			</li>

                  <li><label>是否审批：</label>
                              				<form:select path="approvalStatus" >
                              					<form:option value="" label="请选择"/>
                              					<form:options items="${fns:getDictList('storeState')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
			    <th>资产名称</th>
			    <th>付款方式</th>
			    <th>审核状态</th>
			    <th>审核人</th>
				<th>备注</th>
				<th>修改时间</th>
				<shiro:hasPermission name="cw:fFixedAssetsCgbm:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="fFixedAssetsCgbm" varStatus="status">
			<tr>

			<td>
				${fFixedAssetsCgbm.ffixedassets.name}
			</td>
			  <td>
                                    ${fns:getDictLabel(fFixedAssetsCgbm.paymentMode, "paymentMode", "")}
                         </td>
			<td id="approvalStatus${status.index}">
            ${fns:getDictLabel(fFixedAssetsCgbm.approvalStatus, "storeState", "")}
           </td>
            <td>
            ${fFixedAssetsCgbm.auditor}
            </td>
				<td><a href="${ctx}/cw/fFixedAssetsCgbm/form?id=${fFixedAssetsCgbm.id}">
					${fFixedAssetsCgbm.remarks}
                         				</a>
                         				</td>
				<td>
					<fmt:formatDate value="${fFixedAssetsCgbm.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="cw:fFixedAssetsCgbm:edit"><td>
    				<a href="${ctx}/cw/fFixedAssetsCgbm/form?id=${fFixedAssetsCgbm.id}">修改</a>
					<a href="${ctx}/cw/fFixedAssetsCgbm/delete?id=${fFixedAssetsCgbm.id}" onclick="return confirmx('确认要删除该固定资产采购变卖吗？', this.href)">删除</a>
			 <c:if test="${fFixedAssetsCgbm.approvalStatus!='1'}"><shiro:hasPermission name="cw:fFixedAssetsCgbm:auditing">
                                                        <a onclick="auditingState('${fFixedAssetsCgbm.id}','approvalStatus${status.index}')">审核通过</a>
                                                    </shiro:hasPermission></c:if>
					</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>