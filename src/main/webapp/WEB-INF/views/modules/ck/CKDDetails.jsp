<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head id="Head1">
    <title>入库单信息</title>
    <meta name="decorator" content="default"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/css/jjj.css">
    <link href="${ctxStatic}/css/bootstrap.css" type="text/css" rel="stylesheet">
    <link href="${ctxStatic}/css/bootstrap-switch.css" type="text/css" rel="stylesheet">
    <link href="${ctxStatic}/css/lk-common.css" type="text/css" rel="stylesheet">
    <link href="${ctxStatic}/css/lk-main.css" type="text/css" rel="stylesheet">
    <script type="text/javascript">
        function resize(id) {
            var ele = $("#"+id);
            if(ele.css("display")=="none"){
                ele.css("display","block");
            }else {
                ele.css("display","none");
            }
        }
    </script>
</head>
<body>
<form name="form1" method="post" action="orderRemarkChange" id="form1">
    <div id="divZT" style="margin: 10px;">
        <div class="panel panel-default">
            <div class="panel-heading" style="font-weight: bold;">
                <table width="100%">
                    <tbody><tr>
                        <td>
                            <div>
                                <a class="expand" href="javascript:resize('divbody1');"><span>基本信息</span><img class="icon" title="折叠" src="${ctxStatic}/images/roll_up2.png" border="0" id="divTitle1"></a>
                            </div>
                        </td>
                        <td style="text-align: right">
                            <a class="btn btn-success btn-xs" onclick="Showprint(&#39;0&#39;);return false;">&nbsp;打&nbsp;印&nbsp;</a>
                            <a class="btn btn-success btn-xs" onclick="Showprint(&#39;1&#39;)">&nbsp;预&nbsp;览&nbsp;</a>
                            <span id="Anthem_btnAddRemark__"><input type="submit" name="btnAddRemark" value="追加备注(A)" onclick="" id="btnAddRemark" accesskey="A" class="btn btn-primary btn-xs"></span>
                        </td>
                    </tr>
                    </tbody></table>
            </div>
            <div id="divbody1" class="panel-body">
                <div id="Anthem_pnlEdit__"><div id="pnlEdit">

                    <table style="font-size: 13px; height: 100%; width: 100%;">
                        <tbody><tr>
                            <td style="width: 80px">销售编号：
                            </td>
                            <td style="width: 200px;" align="left">
                                <span id="lblCKDH">无</span>
                            </td>
                            <td style="width: 80px">订单编号：
                            </td>
                            <td style="width: 200px;" align="left">
                                <span id="lblDDBH">${cRkckddinfo.ddbh}</span>
                            </td>
                            <td style="width: 80px">仓库：
                            </td>
                            <td align="left" style="width: 200px;">
                                <span id="lblCKMC">${cRkckddinfo.cHouse.name}</span>
                            </td>
                            <%--<td style="width: 80px"></td>--%>
                            <%--<td style="width: 200px;"></td>--%>
                        </tr>
                        <tr>
                            <td>客户类型：
                            </td>
                            <td>
                                <span id="lblKHLBMC">无</span>
                            </td>
                            <td>客户名称：
                            </td>
                            <td align="left">
                                <span id="lblKHMC">${cRkckddinfo.store.name}</span>
                            </td>
                            <td>业务员：
                            </td>
                            <td align="left">
                                <span id="lblYWYXM">${cRkckddinfo.createBy.name}</span>
                            </td>
                        </tr>

                        <tr>
                            <td>金&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;额：
                            </td>
                            <td align="left">
                                <span id="lblHJJE">${cRkckddinfo.je}</span>
                            </td>
                            <td>实收金额：
                            </td>
                            <td align="left">
                                <span id="lblSJK">${cRkckddinfo.htje}</span>
                            </td>
                            <td>应收款：
                            </td>
                            <td align="left">
                                <span id="lblYSK">${cRkckddinfo.receipt.je}</span>
                            </td>
                        </tr>

                        <tr>
                            <td>操&nbsp;&nbsp;作&nbsp;&nbsp;人：
                            </td>
                            <td align="left">
                                <span id="lblCZR">${cRkckddinfo.createBy.name}</span>
                            </td>
                            <td>操&nbsp;&nbsp;作&nbsp;&nbsp;人：
                            </td>
                            <td align="left">
                                <span id="lblQRR">${cRkckddinfo.createBy.name}</span>
                            </td>
                            <td>日&nbsp;&nbsp;&nbsp;&nbsp;期：
                            </td>
                            <td align="left">
                                <span id="lblRQ"><fmt:formatDate value="${cRkckddinfo.rkckdate}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
                            </td>
                        </tr>
                        <tr>
                            <td>备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：
                            </td>
                            <td colspan="5" align="left">
                                <input type="hidden" name="id" value="${cRkckddinfo.id}">
                                <span id="Anthem_txtBZ__"><textarea name="remarks" rows="2" cols="20" id="txtBZ" autocomplete="OFF" style="height:50px;width:100%;border: 1px solid #ccc">${cRkckddinfo.remarks}</textarea></span>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                </div>
            </div>
        </div>
        <div class="panel panel-default" style="margin-top: 10px">
            <div class="panel-heading" style="font-weight: bold;">
                <a class="expand" href="javascript:resize('divbody2');"><span>单据明细</span><img class="icon" title="折叠" src="${ctxStatic}/images/roll_up2.png" border="0" id="divTitle2"></a>
            </div>
            <div id="divbody2" class="panel-body">
                <div id="Anthem_pnlEdit2__"><div id="pnlEdit2">

                    <div id="Anthem_grid__"><div>
                        <table class="lk-table003 table-hover" cellspacing="0" rules="all" border="1" id="grid" style="border-collapse:collapse;">
                            <tbody>
                            <tr class="table-header">
                                <th scope="col">序号</th>
                                <th scope="col">商品</th>
                                <th scope="col">数量</th>
                                <th scope="col">单价</th>
                                <th scope="col">金额</th>
                                <th scope="col">销售类型</th>
                                <th scope="col">备注</th>
                            </tr>
                            <c:forEach items="${cdList}" varStatus="status" var="cd">
                                <tr>
                                    <td align="center" style="width:35px;">${status.index+1}</td>
                                    <td>
                                        <div>
                                        ${cd.goods.name}
                                        </div>
                                        <div style="color: #ACACAC">
                                                ${cd.goods.spec.name}
                                        </div>
                                    </td>
                                    <%--<td>${cd.goods.spec.name}</td>--%>
                                    <td align="right"> ${cd.nub}</td>
                                    <td align="right">${cd.je}</td>
                                    <td align="right">${cd.nub*cd.je}</td>
                                    <td align="right">无</td>
                                    <td align="left" style="width:120px;">
                                        <div style="text-align: left;" title="备注：">
                                            <input type="hidden" name="ids" value="${cd.id}"/>
                                            <span><input name="remark" type="text" maxlength="250" value="${cd.remarks}" style="width: 120px; background-color: #fff; border: 1px solid #acacac;"></span>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    </div>
                </div>
                </div>
            </div>
        </div>
        <%--<div id="divKM" class="panel panel-default" style="margin-top: 10px">
            <div class="panel-heading" style="font-weight: bold;">
                <a class="expand" href="javascript:_resize(3);"><span>科目信息</span><img class="icon" title="折叠" src="${ctxStatic}/images/roll_up2.png" border="0" id="divTitle3"></a>
            </div>
            <div id="divbody3" class="panel-body">
                <div id="Anthem_pnlEdit3__"><div id="pnlEdit3">

                    <div id="Anthem_gridKM__"><div>
                        <table class="lk-table003 table-hover" cellspacing="0" border="0" id="gridKM" style="width:100%;border-collapse:collapse;">
                            <tbody><tr class="table-header">
                                <th scope="col" style="width:35px;">序号</th><th scope="col">科目编码</th><th scope="col">核算单位</th><th scope="col">科目名称</th><th scope="col">借方金额</th><th scope="col">贷方金额</th>
                            </tr><tr>
                                <td align="center">
                                    1
                                </td><td>0103</td><td>新平江</td><td>库存商品</td><td align="right"></td><td align="right">455.04</td>
                            </tr><tr>
                                <td align="center">
                                    2
                                </td><td>0201</td><td>新平江</td><td>应付款合计</td><td align="right">455.04</td><td align="right"></td>
                            </tr><tr style="background-color: #EAEAEA;font-weight: bold;font-variant: normal;color: #000000;text-align: right;font-size: 14px">
                                <td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td align="left">合计</td><td>455.04</td><td>455.04</td>
                            </tr>
                            </tbody></table>
                    </div></div>

                </div></div>
            </div>
        </div>--%>
    </div>
</form>
</body></html>