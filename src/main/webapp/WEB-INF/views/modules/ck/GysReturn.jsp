<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/4/12 0012
  Time: 上午 9:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>退货订单</title>
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
            alert(id);

            top.$.jBox.open("iframe:${ctx}/ck/cDdinfo/Gysthsp?ids="+id, "退货单审核", 700, $(top.document).height()-180, {
                buttons:{"确定":"ok"}, loaded:function(h) {
                    $(".jbox-content", top.document).css("overflow-y", "hidden");
                }
            });

        }
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li class="active"><a href="${ctx}/cw/fPayment/returnGoodsList">销售退货单</a></li>
    <li><a href="${ctx}/ck/cRkckddinfo/returnGoodsForm">销售退货单添加</a></li>
</ul>
<form:form id="searchForm" modelAttribute="fReceipt" action="${ctx}/cw/fReceipt/GysReturn" method="post" class="breadcrumb form-search">
    <input name="id" type="hidden" value="${fReceipt.id}"/>
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <%--<ul class="ul-form">--%>
    <%--<li><label>仓库：</label>--%>
    <%--<form:input path="house.id" htmlEscape="false" maxlength="64" class="input-medium"/>--%>
    <%--</li>--%>
    <%--<li><label>商品：</label>--%>
    <%--<form:input path="goods.id" htmlEscape="false" maxlength="64" class="input-medium"/>--%>
    <%--</li>--%>
    <%--<li><label>供应商：</label>--%>
    <%--<form:input path="supplier.id" htmlEscape="false" maxlength="64" class="input-medium"/>--%>
    <%--</li>--%>
    <%--<li><label>客户：</label>--%>
    <%--<form:input path="store.id" htmlEscape="false" maxlength="64" class="input-medium"/>--%>
    <%--</li>--%>
    <%--<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>--%>
    <%--<li class="clearfix"></li>--%>
    <%--</ul>--%>
</form:form>
<sys:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>收款日期</th>
        <th>订单编号</th>
        <th>创建时间</th>
        <th>创建人</th>
        <th>审批人</th>
        <shiro:hasPermission name="cw:fReceipt:edit"><th>操作</th></shiro:hasPermission>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="fReceipt">
        <tr>
            <td>
                <fmt:formatDate value="${fReceipt.receiptDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
            <td>
                    ${fReceipt.ddbh}
            </td>
            <td>
                <fmt:formatDate value="${fReceipt.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
            <td>
                    ${fReceipt.createBy.id}
            </td>

            <td>
                    ${fReceipt.jsr.name}
            </td>
            <td>
                    <%--${ctx}/ck/cDdinfo/thsp?ids=${fPayment.id}--%>
                <c:if test="${fReceipt.approvalStatus eq '0'}"><a href="javascript:void(0)" onclick="viewSubOrder('${fReceipt.id}')">审批</a></c:if>
                <c:if test="${fReceipt.approvalStatus eq '1'}">审批通过</c:if>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>