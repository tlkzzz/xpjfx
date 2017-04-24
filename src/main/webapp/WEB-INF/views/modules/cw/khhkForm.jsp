<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>收入记录管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function() {
            //$("#name").focus();
            $("#inputForm").validate({
                submitHandler: function(form){
                    loading('正在提交，请稍等...');
                    form.submit();
                },
                errorContainer: "#messageBox",
                errorPlacement: function(error, element) {
                    $("#messageBox").text("输入有误，请先更正。");
                    if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
                        error.appendTo(element.parent().parent());
                    } else {
                        error.insertAfter(element);
                    }
                }
            });
        });
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li><a href="${ctx}/cw/fIncomeRecord/khhk">客户还款记录列表</a></li>
    <li class="active"><a href="${ctx}/cw/fIncomeRecord/khhkForm?id=${fIncomeRecord.id}">客户还款<shiro:hasPermission name="cw:fIncomeRecord:edit">${not empty fIncomeRecord.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="cw:fIncomeRecord:edit">查看</shiro:lacksPermission></a></li>
</ul><br/>
<form:form id="inputForm" modelAttribute="fIncomeRecord" action="${ctx}/cw/fIncomeRecord/khhksave" method="post" class="form-horizontal">
    <form:hidden path="id"/>
    <sys:message content="${message}"/>
    <div class="control-group">
        <label class="control-label">订单ID：</label>
        <div class="controls">
            <form:select path="orderId" class="input-xlarge">
                <form:option value="" label="请选择"></form:option>
                <form:options items="${orderIdList}" itemLabel="id" itemValue="id" htmlEscape="false"/>
            </form:select>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">收入帐号：</label>
        <div class="controls">
            <form:select path="incomeAccount" class="input-xlarge">
                <form:option value="" label="请选择"></form:option>
                <form:options items="${IDcarddList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
            </form:select>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">来往帐号：</label>
        <div class="controls">
        <form:select path="traverAccount" class="input-xlarge">
            <form:option value="" label="请选择"></form:option>
            <form:options items="${IDcarddList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
        </form:select>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">收入金额：</label>
        <div class="controls">
            <form:input path="incomeMoney" htmlEscape="false" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">收入时间：</label>
        <div class="controls">
            <input name="incomeDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
                   value="<fmt:formatDate value="${fIncomeRecord.incomeDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">收入方式：</label>
        <div class="controls">
            <form:select path="incomeMode" class="input-xlarge required">
                <form:option value="" label="请选择"/>
                <form:options items="${fns:getDictList('expenMode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
            </form:select>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">备注：</label>
        <div class="controls">
            <form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
        </div>
    </div>
    <div class="form-actions">
        <shiro:hasPermission name="cw:fIncomeRecord:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
</form:form>
</body>
</html>