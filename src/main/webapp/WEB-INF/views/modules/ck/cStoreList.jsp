<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>客户管理</title>
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
        function auditingState(storeId,tdId) {
			if(storeId!=''){
			    $.ajax({
			        url:"auditingState",
					dataType:"json",
					data:{id:storeId},
					type:"POST",
					success:function (data) {
						if(data)$("#"+tdId).text("审核通过");
                        $("#messageBox").text();
                        top.$.jBox.tip("审核通过成功！",'warning');
                    },
					error:function () {
                        top.$.jBox.tip("审核失败，请刷新后重试！",'warning');
                    }
				});
			}
        }

           function derive(){
                            $.jBox.confirm("确认要导出数据吗？","系统提示",function(v,h,f){
                                if(v=="ok"){
                                    $("#searchForm").attr("action","${ctx}/ck/cStore/exportFile");
                                    $("#searchForm").submit();
                                    //还原默认action
                                    $("#searchForm").attr("action","${ctx}/ck/cStorelist");
                                }
                            },{buttonsFocus:1});
                            $('.jbox-body .jbox-icon').css('top','55px');
                        }
        	</script>
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ck/cStore/list">客户列表</a></li>
		<shiro:hasPermission name="ck:cStore:edit"><li><a href="${ctx}/ck/cStore/form">客户添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="cStore" action="${ctx}/ck/cStore/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>店铺名称：</label>
				<form:input path="dpmc" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>客户名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>区域：</label>
               <sys:treeselect id="areaId" name="area.id" value="${cStore.area.id}" labelName="area.name" labelValue="${cStore.area.name}"
               	title="区域选择" url="/sys/area/treeData" cssClass="input-medium"/>
			</li>
			<li><label>是否审批：</label>
            				<form:select path="state" >
            					<form:option value="" label=""/>
            					<form:options items="${fns:getDictList('storeState')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
            				</form:select>
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
			    <th>详细地址</th>
				<th>客户名称</th>
                <th>客户分类</th>
                <th>店铺名称</th>
                <th>手机号码</th>
				<th>审核状态</th>
				<th>采购员</th>
				<th>采购员联系方式</th>
				<th>额度</th>
				<th>销售渠道</th>
				<th>进货渠道</th>
				<th>创建时间</th>
				<th>备注</th>
				<shiro:hasPermission name="ck:cStore:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="cStore" varStatus="status">
			<tr>
            	<td>
                    ${cStore.dz}
                </td>
				<td><a href="${ctx}/ck/cStore/form?id=${cStore.id}">
					${cStore.name}
                </a></td>
                <td>
                    ${cStore.sclass.name}
                </td>
                <td>
                    ${cStore.dpmc}
                </td>
                <td>
                     ${cStore. phone}
                </td>
				<td id="state${status.index}">
					${fns:getDictLabel(cStore.state, "storeState", "")}
				</td>
				<td>
                   ${cStore. cgy}
                </td>
                 <td>
                   ${cStore. cgydh}
                </td>
                <td>
                  ${cStore. edu}
                </td>
                 <td>
                  ${cStore. xsqd}
                 </td>
                  <td>
                  ${cStore. jhqd}
                 </td>
				<td>
					<fmt:formatDate value="${cStore.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${cStore.remarks}
				</td>
				<shiro:hasPermission name="ck:cStore:edit"><td>
    				<a href="${ctx}/ck/cStore/form?id=${cStore.id}">修改</a>
					<a href="${ctx}/ck/cStore/delete?id=${cStore.id}" onclick="return confirmx('确认要删除该客户吗？', this.href)">删除</a>
					  <c:if test="${cStore.state!='1'}"><shiro:hasPermission name="ck:cStore:auditing">
                                            <a onclick="auditingState('${cStore.id}','state${status.index}')">审核通过</a>
                                        </shiro:hasPermission></c:if>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>