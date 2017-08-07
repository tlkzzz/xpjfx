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
<ul class="nav nav-tabs">
    <li class="active"><a href="${ctx}/cw/fPayment/sflist?paymentType=${fPayment.paymentType}">付款列表</a></li>
    </ul>
<form:form id="searchForm" modelAttribute="fPayment" action="${ctx}/cw/fPayment/sflist" method="post" class="breadcrumb form-search">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <ul class="ul-form">
        <li><label>付款日期：</label>
            <input name="paymentDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
                   value="<fmt:formatDate value="${fPayment.paymentDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
        </li>
        <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
        <li class="clearfix"></li>
    </ul>
</form:form>
<sys:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>类型</th>
        <th>收付款日期</th>
        <th>单据编号</th>
        <th>来往单位</th>
        <th>来往账号</th>
        <th>付款帐号</th>

    </tr>
    </thead>
    <tbody>
    <c:forEach items="${fPaymentList}" var="fPayment">
        <tr>
            <c:if test="${fPayment.fybs==1}">
                <td>
                    付款
                </td>
            </c:if>
            <c:if test="${fPayment.fybs==2}">
                <td>
                    收款
                </td>
            </c:if>
            <td>
                <fmt:formatDate value="${fPayment.paymentDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
            <td>
                    ${fPayment.ddbh}
            </td>
            <td>
                    ${fPayment.csName}
            </td>
            <td>
                    ${fPayment.faName}
            </td>
            <td>
                    ${fPayment.paymentAccount}
            </td>


        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>