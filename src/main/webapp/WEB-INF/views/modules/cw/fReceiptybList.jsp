<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>一般费用单管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		 function auditingState(storeId,tdId) {
                			if(storeId!=''){
                			    $.ajax({
                			        url:"${ctx}/cw/fReceipt/shenhe",
                					dataType:"json",
                					data:{id:storeId},
                					type:"POST",
                					success:function (data) {
                					 return confirmx('确定审核吗', "${ctx}/cw/fReceipt/yblist?receiptType=${fReceipt.receiptType}")
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
		<li class="active"><a href="${ctx}/cw/fReceipt/yblist?receiptType=${fReceipt.receiptType}">一般费用列表</a></li>
	    <shiro:hasPermission name="cw:fReceipt:edit"><li><a href="${ctx}/cw/fReceipt/ybform?receiptType=${fReceipt.receiptType}">一般费用</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="fReceipt" action="${ctx}/cw/fReceipt/ybform" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>收款日期：</label>
				<input name="receiptDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${fReceipt.receiptDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>单据编号：</label>
				<form:input path="receiptCode" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			    <th>收款类型</th>
			    <th>来往账号</th>
			    <th>收款帐号</th>
			    <th>收款方式</th>
				<th>单据编号</th>
				<th>来往单位</th>
				<th>审核状态</th>
			    <th>收款日期</th>
				<th>创建时间</th>
				<th>备注</th>
				<shiro:hasPermission name="cw:fReceipt:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="fReceipt" varStatus="status">
			<tr>
			     <td>
                  ${fns:getDictLabel(fReceipt.receiptType, "receiptType", "")}
                 </td>
			    <td>
                 ${fReceipt.travelAccount}
                </td>
			    <td>
                 ${fReceipt.receiptAccount}
               </td>
			    <td>
                   ${fns:getDictLabel(fReceipt.receiptMode, "receiptMode", "")}
                 </td>
				<td>
					${fReceipt.receiptCode}
				</td>
				<td>
					${fReceipt.travelUnit.id}
				</td>
				<td id="approvalStatus${status.index}">
                            ${fns:getDictLabel(fReceipt.approvalStatus, "storeState", "")}
                   </td>
				<td>
                <fmt:formatDate value="${fReceipt.receiptDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
                				</td>
				<td>
					<fmt:formatDate value="${fReceipt.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
						${fReceipt.remarks}
				</td>
				<shiro:hasPermission name="cw:fReceipt:edit"><td>
					<a href="${ctx}/cw/fReceipt/delete?id=${fReceipt.id}" onclick="return confirmx('确认要删除该收款吗？', this.href)">删除</a>
				 <c:if test="${fReceipt.approvalStatus!='1'}"><shiro:hasPermission name="cw:fReceipt:auditing">
                                                                        <a onclick="auditingState('${fReceipt.id}','approvalStatus${status.index}')" >审核通过</a>
                                                                    </shiro:hasPermission></c:if>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>