<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>付款管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		 function auditingState(storeId,tdId) {
                        			if(storeId!=''){
                        			    $.ajax({
                        			        url:"${ctx}/cw/fPayment/shenhe",
                        					dataType:"json",
                        					data:{id:storeId},
                        					type:"POST",
                        					success:function (data) {
                        					 return confirmx('确定审核吗', "${ctx}/cw/fPayment/list");
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
<!--	<ul class="nav nav-tabs">
		 <c:if test="${fPayment.paymentType eq '6' }"><li class="active"><a href="${ctx}/cw/fPayment/list?paymentType=${fPayment.paymentType}">现金费用列表</a></li></c:if>
		 <c:if test="${fPayment.paymentType eq '7'}"><li class="active"><a href="${ctx}/cw/fPayment/list?paymentType=${fPayment.paymentType}">一般费用列表</a></li></c:if>
		 <c:if test="${fPayment.paymentType eq '8' }"><li class="active"><a href="${ctx}/cw/fPayment/list?paymentType=${fPayment.paymentType}">其他费用列表</a></li></c:if>
		<c:if test="${fPayment.paymentType eq '6' }"><shiro:hasPermission name="cw:fPayment:edit"><li><a href="${ctx}/cw/fPayment/xjform?paymentType=${fPayment.paymentType}">现金费用</a></li></shiro:hasPermission></c:if>
        <c:if test="${fPayment.paymentType eq '7'}"><shiro:hasPermission name="cw:fPayment:edit"><li><a href="${ctx}/cw/fPayment/ybform?paymentType=${fPayment.paymentType}">一般费用</a></li></shiro:hasPermission></c:if>
        <c:if test="${fPayment.paymentType eq '8'}"><shiro:hasPermission name="cw:fPayment:edit"><li><a href="${ctx}/cw/fPayment/qtform?paymentType=${fPayment.paymentType}">其他费用</a></li></shiro:hasPermission></c:if>
	</ul>
-->
<ul class="nav nav-tabs">
     <li class="active"><a href="${ctx}/cw/fPayment/list?paymentType=${fPayment.paymentType}">付款列表</a></li>
	<shiro:hasPermission name="cw:fPayment:edit"><li><a href="${ctx}/cw/fPayment/xjform">现金费用</a></li></shiro:hasPermission>
	<shiro:hasPermission name="cw:fPayment:edit"><li><a href="${ctx}/cw/fPayment/ybform">一般费用</a></li></shiro:hasPermission>
	<shiro:hasPermission name="cw:fPayment:edit"><li><a href="${ctx}/cw/fPayment/qtform">其他费用</a></li></shiro:hasPermission>
</ul>
	<form:form id="searchForm" modelAttribute="fPayment" action="${ctx}/cw/fPayment/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>付款日期：</label>
				<input name="paymentDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${fPayment.paymentDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>单据编号：</label>
				<form:input path="paymentCode" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>来往账号：</label>
				<form:input path="travelAccount" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>付款账号：</label>
				<form:input path="paymentAccount" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			    <th>付款类型</th>
			<!--     <c:if test="${fn:contains('0,1,2,3,4,5,7,8',fPayment.paymentType)}">
               </c:if> -->
				<th>来往账号</th>
				<th>付款帐号</th>
				<th>来往单位</th>
				<th>付款日期</th>
				<th>单据编号</th>
				<th>审核状态</th>
				<th>付款方式</th>
				<th>合同金额</th>
				<th>金额</th>
				<th>创建时间</th>
				<th>备注</th>
				<shiro:hasPermission name="cw:fPayment:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="fPayment" varStatus="status">
			<tr>
			 <td>
                        ${fns:getDictLabel(fPayment.paymentType, "receiptType", "")}
             </td>
                <!--  <c:if test="${fn:contains('0,1,2,3,4,5,7,8',fPayment.paymentType)}">
				  </c:if> -->
                              <td>
                               ${fPayment.travelAccount}
                              </td>
             			    <td>
                              ${fPayment.paymentAccount}
                            </td>
                            <td>
                            	${fPayment.travelUnit.id}
                            	</td>
				<td>
					<fmt:formatDate value="${fPayment.paymentDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</td>
				<td>
						${fPayment.paymentCode}
				</td>
				<td id="approvalStatus${status.index}">
                        ${fns:getDictLabel(fPayment.approvalStatus, "storeState", "")}
                </td>
				<td>
						${fns:getDictLabel(fPayment.paymentMode, "paymentMode", "")}
				</td>
				<td>
						${fPayment.htje}
				</td>
				<td>
						${fPayment.je}
				</td>
				<td>
					<fmt:formatDate value="${fPayment.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
						${fPayment.remarks}
				</td>
				<shiro:hasPermission name="cw:fPayment:edit"><td>
    				<%--<a href="${ctx}/cw/fPayment/form?id=${fPayment.id}">修改</a>--%>
					<a href="${ctx}/cw/fPayment/delete?id=${fPayment.id}" onclick="return confirmx('确认要删除该付款吗？', this.href)">删除</a>
			        <c:if test="${fPayment.approvalStatus!='1'}"><shiro:hasPermission name="cw:fPayment:auditing">
                      <a onclick="auditingState('${fPayment.id}','approvalStatus${status.index}')">审核通过</a>
                     </shiro:hasPermission></c:if>
            </td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
		<tr>
			<td colspan="8"><b>合计</b></td>
			<td colspan="1"><b>${htSum}￥</b></td>
			<td colspan="4"><b>${Sum}￥</b></td>
		</tr>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>