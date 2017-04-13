<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/4/12 0012
  Time: 上午 9:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>总订单管理</title>
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

        $('#fddid').on('click', function() {     //页面上的button按钮的click事件，重新获取参数后发送参数，然后重新加载数据。
            keyword = $("#jjinputid").val();       //获取输入框内容
            cchl="jijin";
            $("#dataGrid").jqGrid('setGridParam',{
                datatype:'json',
                postData:{'keyword':encodeURI(encodeURI(keyword))}, //发送数据
                page:1
            }).trigger("reloadGrid"); //重新载入
        });
        var lastsel;
        function loadXMLDoc() {

//            alert(ids)
            jQuery("#dataGrid").jqGrid(
                {
                    url : '${ctx}/ck/cRkckddinfo/checkId',
                    datatype : "json",
                    postData: {
                        ids:$("#fddid").val(),
                    },
                    mtype: 'POST',
                    colNames : [ 'id','编号','商品名称','house','金额','数量','实际金额'],
                    colModel : [
                        {name : 'id',index : 'id',width : 240},
                        {name : 'ddbh',index : 'ddbh',width : 140},
                        {name : 'goods.name',index : 'goods.name',width : 70},
                        {name : 'house.name',index : 'house.name',width : 50},
                        {name : 'je',index : 'je',width : 80,align : "right"},
                        {name : 'nub',index : 'nub',width : 80,align : "right",editable : true},
                        {name : 'rksjcbj',index : 'rksjcbj',width : 80,align : "right"}
//                        {name : 'note',index : 'note',width : 150,sortable : false}
                    ],
//
                    sortname : 'id',
                    viewrecords : true,
                });
            jQuery("#dataGrid").jqGrid('navGrid');
            jQuery("#dataGrid").jqGrid('setGridParam',{
                postData:{'ids':$("#fddid").val()}, //发送数据
            }).trigger("reloadGrid");
        }

    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li><a href="${ctx}/ck/cRkckddinfo/returnGoodsList">总订单列表</a></li>
    <li class="active"><a href="${ctx}/ck/cRkckddinfo/returnGoodsForm?id=${cRkckddinfo.id}">总订单<shiro:hasPermission name="ck:cRkckddinfo:edit">${not empty cRkckddinfo.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="ck:cRkckddinfo:edit">查看</shiro:lacksPermission></a></li>
</ul><br/>
<form:form id="inputForm" modelAttribute="cRkckddinfo" action="${ctx}/ck/cRkckddinfo/save" method="post" class="form-horizontal">
    <sys:message content="${message}"/>
    <div class="control-group">
        <label class="control-label">总订单编号：</label>
        <div class="controls">
            <form:select path="id" class="input-xlarge required" id="fddid" onclick="loadXMLDoc()">
                <form:option value="" label="请选择"></form:option>
                <form:options items="${orderIdList}" itemLabel="ddbh" itemValue="id" htmlEscape="false"/>
            </form:select>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>
    <table id="dataGrid"></table>
    <div id="pager2"></div>
    <link href="${ctxStatic}/jqGrid/4.6/css/ui.jqgrid.css" type="text/css" rel="stylesheet" />
    <script src="${ctxStatic}/jqGrid/4.7/js/jquery.jqGrid.js" type="text/javascript"></script>
    <script src="${ctxStatic}/jqGrid/4.7/js/jquery.jqGrid.extend.js" type="text/javascript"></script>
    <div class="control-group">
        <label class="control-label">付款账户：</label>
        <div class="controls">
            <form:select path="fAccount.bankCode" class="input-xlarge required">
                <form:option value="" label="请选择"></form:option>
                <form:options items="${IDcarddList}" itemLabel="bankCode" itemValue="bankCode" htmlEscape="false"/>
            </form:select>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">退货金额：</label>
        <div class="controls">
            <form:input path="je" htmlEscape="false" class="input-xlarge "/>
        </div>
    </div><div class="control-group">
        <label class="control-label">存入仓库：</label>
        <div class="controls">
            <form:select path="cHouse.id">
                <form:option value="" label="请选择"></form:option>
                <form:options items="${houseList}" itemLabel="name" itemValue="id" htmlEscape="false"></form:options>
            </form:select>
        </div>
    </div>

    <%--<div class="form-actions">--%>
        <%--<shiro:hasPermission name="ck:cRkckddinfo:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>--%>
        <%--<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>--%>
    <%--</div>--%>
</form:form>
</body>
</html>
