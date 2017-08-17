<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>入库查询</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/css/jjj.css">
    <link href="${ctxStatic}/My97DatePicker/skin/WdatePicker.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="${ctxStatic}/jquery/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="${ctxStatic}/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            $(".ui-dialog-close").click(function () {
                $("#rkdmx").attr("src","");
                $(".details").css("display","none");
            });
            showSubOrder('${page.list[0].id}',null);
        });
        function Show(id) {
            if(id=='')return;
            $("#rkdmx").attr("src","RKDDetails?id="+id);
            $("#content").css("width","900px");
            $(".details").css("display","block");
        }
        function showSubOrder(id,ele) {
            if(id=="")return;
            if(ele!=null) {
                $(".lk-selected001").removeClass("lk-selected001");
                ele.addClass("lk-selected001");
            }
            $("#tbHeader").empty();
            $.get("${ctx}/ck/cDdinfo/findListByCrk?rkckddinfo.id="+id,function (data) {
                if(data!=null){
                    var sumMoney = 0;
                    $.each(data,function (i,cd) {
                        var total = parseInt(cd.nub)*parseFloat(cd.je);
                        sumMoney += total;
                        var remarks = (cd.remarks!=null)?cd.remarks:'';
                        var bigPrice = parseInt(eval(cd.goods.spec.name))*parseFloat(cd.je);
                        var smallPriceUnit = (cd.goods.spec.name.split("*").length>2)?cd.je+'/'+cd.goods.small.name:cd.je+'/'+cd.goods.zong.name;
                        var text = '<tr><td style="padding: 6px 3px;"><table><tbody><tr><td><span class="label label-info" style="font-size: 9px; background-color: #F0AD4E;">'+(i+1)+'</span></td>'+
                            '<td style="font-weight: bold;">'+cd.goods.name+'</td></tr><tr><td></td><td style="color: #ACACAC">'+cd.goods.spec.name+'</td></tr><tr><td></td><td><div style="width: 130px; color: #ACACAC">'+
                            '<span id="rptProductIn_ctl01_lblAllTM" title="'+cd.ddbh+'">'+cd.ddbh+'</span></div></td></tr></tbody></table></td><td style="text-align: right;" title="换算率: '+eval(cd.goods.spec.name)+'">'+
                            cd.type+'</td><td style="text-align: right;" data-lk="price"><div title="'+bigPrice+'">'+bigPrice+'/'+cd.goods.big.name+'</div><div style="color: #acacac" title="'+cd.je+'">'+smallPriceUnit+'</div></td>'+
                            '<td style="text-align: right; padding-right: 3px;" data-lk="je">'+total+'</td></tr>'+
                            '<tr style="border-bottom: 1px solid #DDDDDD"><td colspan="4" style="padding: 0px 18px; text-align: left;"><table><tbody><tr><td>'+'<div style="padding: 0px;"><div title="'+cd.house.name+
                            '"><span id="rptProductIn_ctl01_lblCKMC" style="color: #337AB7; font-size: 12px;">'+cd.house.name+'</span>&nbsp;&nbsp;&nbsp;&nbsp;</div></div></td><td><div title="备注：'+remarks+
                            '"><div id="Anthem_rptProductIn_ctl01_pnlBZ__">'+remarks+'</div></div></td><td><div title="生产日期："></div></td></tr></tbody></table></td></tr>';
                        $("#tbHeader").append(text);
                    });
                    $("#lblInProductCount").text(data.length);
                    $("#lblInProductAmount").text(sumMoney);
                }else {
                    $("#lblInProductCount").text(0);
                    $("#lblInProductAmount").text(0);
                }
            })
        }
        function AuditingPage(id,state) {
            if(id==""||state=="")return;
            var pageName = (state=="1")?"rkrdOrder":(state=="3")?"qtrkOrder":(state=="5")?"jhthOrder":"";
            if(pageName=="")return;
            $("#rkdmx").attr("src","order/"+pageName+"?lx=0&id="+id);
            $("#content").css("width","1100px");
            $(".details").css("display","block");
        }
        function page(n,s){
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").submit();
            return false;
        }
    </script>
</head>
<body style="cursor: auto;">
<div style="top: -500px; position: absolute; height: 400px">
</div>
<!-- 复选框 操作区 -->
<div id="divZT" style="margin-right: 10px;">
    <table id="divBody1" width="100%" border="0" cellpadding="0" cellspacing="0">
        <tbody><tr>
            <td valign="top">
                <!-- 入库信息 -->
                <div style="position: relative; margin-top: 10px; margin-left: 10px;">
                    <!-- Nav tabs -->
                    <ul id="myTab" class="nav nav-tabs" role="tablist">
                        <li <c:if test="${empty cRkckddinfo.state}">class="active"</c:if> style="width: 90px;">
                            <a href="${ctx}/ck/cRkckddinfo/rkOrderSelect?lx=0">全部&nbsp;
                                <span class="badge" style="position: absolute; padding: 0px; background-color: #FF6400; color: #fff; font-weight: bold; top: 3px; text-align: center; z-index: 999">
                                        <div style="margin: 0px 5px; line-height: 1.4">
                                            <span id="Anthem_lblqb__"><span id="lblqb">${allNotIsspCount}</span></span>
                                        </div>
                                    </span>
                            </a>
                        </li>
                        <li <c:if test="${cRkckddinfo.state eq '1'}">class="active"</c:if> style="width: 120px;">
                            <a href="${ctx}/ck/cRkckddinfo/rkOrderSelect?lx=0&state=1">进货录单&nbsp;
                                <span class="badge" style="position: absolute; padding: 0px; background-color: #FF6400; color: #fff; font-weight: bold; top: 3px; text-align: center; z-index: 999">
                                        <div style="margin: 0px 5px; line-height: 1.4">
                                            <span id="Anthem_lbljh__"><span id="lbljh">${ckNotIsspCount}</span></span>
                                        </div>
                                    </span>
                            </a>
                        </li>
                        <li <c:if test="${cRkckddinfo.state eq '3'}">class="active"</c:if> style="width: 125px;">
                            <a href="${ctx}/ck/cRkckddinfo/rkOrderSelect?lx=0&state=3">其他入库&nbsp;
                                <span class="badge" style="position: absolute; padding: 0px; background-color: #FF6400; color: #fff; font-weight: bold; top: 3px; text-align: center; z-index: 999">
                                            <div style="margin: 0px 5px; line-height: 1.4">
                                                <span id="Anthem_lblqtrk__"><span id="lblqtrk">${qtNotIsspCount}</span></span>
                                            </div>
                                        </span>
                            </a>
                        </li>
                        <li <c:if test="${cRkckddinfo.state eq '5'}">class="active"</c:if> style="width: 125px;">
                            <a href="${ctx}/ck/cRkckddinfo/rkOrderSelect?lx=0&state=5">进货退货&nbsp;
                                <span class="badge" style="position: absolute; padding: 0px; background-color: #FF6400; color: #fff; font-weight: bold; top: 3px; text-align: center; z-index: 999">
                                            <div style="margin: 0px 5px; line-height: 1.4">
                                                <span id="Anthem_lbljhth__"><span id="lbljhth">${thNotIsspCount}</span></span>
                                            </div>
                                        </span>
                            </a>
                        </li>
                        <li <c:if test="${cRkckddinfo.state eq '0'}">class="active"</c:if> style="width: 125px;">
                            <a href="${ctx}/ck/cRkckddinfo/rkOrderSelect?lx=0&state=0">临时入库&nbsp;
                                <span class="badge" style="position: absolute; padding: 0px; background-color: #FF6400; color: #fff; font-weight: bold; top: 3px; text-align: center; z-index: 999">
                                            <div style="margin: 0px 5px; line-height: 1.4">
                                                <span id="Anthem_lblhhrk__"><span id="lblhhrk">${lsNotIsspCount}</span></span>
                                            </div>
                                        </span>
                            </a>
                        </li>
                    </ul>
                    <div id="helpTip" style="position: absolute; padding: 0px; color: #088AE3; font-size: 13px; top: 8px; right: 0px; text-align: right; z-index: 999" data-hasqtip="0">
                        <a href="javascript: void(0);" style="font-size: 13px; text-decoration: none">帮助
                            <img style="padding-bottom: 3px;" src="${ctxStatic}/images/shipintb.png">
                        </a>
                    </div>
                </div>

                <div class="panel panel-default" style="width: 100%; margin-right: 10px; border-top: none;">
                    <div id="zbBodyDiv" class="panel-body" style="padding: 0px; height: 582px; overflow: auto;">
                        <div id="Anthem_pnlSearch__"><div id="pnlSearch">
                        <form:form id="searchForm" modelAttribute="cRkckddinfo" action="${ctx}/ck/cRkckddinfo/rkOrderSelect" method="post" class="breadcrumb form-search">
                            <table cellpadding="0" cellspacing="0" style="margin-left: 10px; margin-top: 10px;">
                                <tbody><tr>
                                    <input name="lx" type="hidden" value="${cRkckddinfo.lx}"/>
                                    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
                                    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
                                    <td style="padding-right: 5px;">
                                        <div class="input-group">
                                            <div class="input-group-btn">
                                                <button type="button" class="btn btn-default lk-p5 dropdown-toggle" data-toggle="dropdown" aria-expanded="false" style="height: 34px; background-color: #EEEEEE;">
                                                    入库日期 <span class="caret"></span>
                                                </button>
                                            </div>
                                            <input name="startDate" id="startDate" type="text" readonly="readonly" maxlength="20" class="form-control lk-p2" style="width:80%;"
                                                   value="<fmt:formatDate value='${cRkckddinfo.startDate}' pattern='yyyy-MM-dd HH:mm:ss'/>"
                                                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss', maxDate:'#F{$dp.$D(\'endDate\')}',isShowClear:false});"/>
                                        </div>
                                    </td>
                                    <td style="padding-right: 5px;">
                                        <div class="input-group">
                                            <span class="input-group-addon lk-p5">到</span>
                                            <input name="endDate" id="endDate" type="text" readonly="readonly" maxlength="20" class="form-control lk-p2" style="width:80%;"
                                                   value="<fmt:formatDate value='${cRkckddinfo.endDate}' pattern='yyyy-MM-dd HH:mm:ss'/>"
                                                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss', minDate:'#F{$dp.$D(\'startDate\')}',isShowClear:false});"/>
                                        </div>
                                    </td>
                                    <td style="padding-right: 5px; display: block;">
                                        <div class="input-group">
                                            <span class="input-group-addon lk-p5">类型</span>
                                            <span id="Anthem_rkorjhthsh__">
                                                <form:select path="state" class="form-control lk-p2" style="width:100px;">
                                                    <form:option value="" selected="selected">请选择</form:option>
                                                    <form:options items="${fns:getDictList('ckState')}" itemLabel="label" itemValue="value" htmlEscape="false"></form:options>
                                                </form:select>
                                            </span>
                                        </div>
                                    </td>
                                    <td style="padding-right: 10px;">
                                        <div class="input-group">
                                            <span class="input-group-addon lk-p3">审核状态</span>
                                            <span id="Anthem_dropSHZT__">
                                              <form:select path="issp" class="form-control lk-p2" style="width:100px;">
                                                  <form:option selected="selected" value="">全部</form:option>
                                                  <form:options items="${fns:getDictList('storeState')}" itemLabel="label" itemValue="value" htmlEscape="false"></form:options>
                                              </form:select>
                                            </span>
                                        </div>
                                    </td>
                                    <td style="width: 250px; padding-right: 10px;">
                                        <div id="searchTip" class="input-group">
                                            <div class="input-group-btn">
                                                <button type="submit" class="btn btn-success" style="border-top-left-radius: 0px;border-top-right-radius: 0px; border-bottom-left-radius: 0px;border-bottom-right-radius: 0px;">查询(C)</button>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                                </tbody>
                            </table>

                        </div></div>
                        <div id="divMore" style="margin-top: 2px;">
                            <table border="0" style="margin-left: 10px;">
                                <tbody><tr>
                                    <td class="lk-pl10">
                                        <div class="input-group">
                                            <span class="input-group-addon lk-p5">仓库</span>
                                            <span id="Anthem_dropCK__">
                                                 <form:select path="cHouse.id" class="form-control lk-p5" style="width:100px;">
                                                     <form:option selected="selected" value="">请选择</form:option>
                                                     <form:options items="${houseList}" itemLabel="name" itemValue="id" htmlEscape="false"></form:options>
                                                 </form:select>
                                            </span>
                                        </div>
                                    </td>
                                    <td id="Td1" class="lk-pl10">
                                        <div class="input-group">
                                            <span class="input-group-addon lk-p5">供应商</span>
                                            <span id="Anthem_dropGYS__">
                                                <form:select path="supplier.id" class="form-control lk-p5" style="width:100px;">
                                                    <form:option selected="selected" value="">请选择</form:option>
                                                    <form:options items="${supplierList}" itemLabel="name" itemValue="id" htmlEscape="false"></form:options>
                                                </form:select>
                                            </span>
                                        </div>
                                    </td>
                                </tr>
                                </tbody></table>
                            </form:form>
                        </div>
                        <div style="padding: 10px 10px">
                            <div id="Anthem_grid__"><div>
                                <table class="lk-table003 table-hover" cellspacing="0" border="0" id="grid" style="width:100%;border-collapse:collapse;">
                                    <tbody>
                                    <tr class="table-header">
                                        <th scope="col">
                                            <input type="checkbox" id="chkCheckAll" name="chkAllSelect" title="全选/取消全选">
                                        </th>
                                        <th scope="col" style="width:65px;">入库单号</th>
                                        <th scope="col">入库仓库</th>
                                        <th scope="col">供应商</th>
                                        <th scope="col">入库日期/备注</th>
                                        <th scope="col">总金额</th>
                                        <th scope="col">操作人</th>
                                        <th scope="col">审核</th>
                                        <th scope="col">操作</th>
                                    </tr>
                                    <c:forEach items="${page.list}" var="crk" varStatus="status">
                                        <tr <c:if test="${status.first}">class="lk-selected001"</c:if> onclick="showSubOrder('${crk.id}',$(this))">
                                            <td align="center" style="width:35px;">
                                                <input type="checkbox" name="chkSelect" value="${crk.id}" style="margin: 2px 0px 0px 1px"/>
                                            </td>
                                            <td align="center">
                                                <div title="入库单号：${crk.ddbh}">
                                                    <a href="javascript:Show('${crk.id}');">${crk.ddbh}</a>
                                                </div>
                                            </td>
                                            <td>${crk.cHouse.name}</td>
                                            <td>${crk.supplier.name}</td>
                                            <td align="left" style="width:155px;">
                                                <div title="入库日期：<fmt:formatDate value='${crk.rkckdate}' pattern='yyyy-MM-dd HH:mm'/>"><fmt:formatDate value='${crk.rkckdate}' pattern='MM-dd HH:mm'/></div>
                                                <div style="color: #ACACAC; text-align: left" title="备注：${crk.remarks}">
                                                    <span id="grid_ctl02_Label1">${crk.remarks}</span>
                                                </div>
                                            </td>
                                            <td align="right">${crk.je}</td>
                                            <td align="center">
                                                <div title="操作人：${crk.createBy.name}">${crk.createBy.name}</div>
                                            </td>
                                            <td align="center" valign="middle" style="width:80px;">
                                                <div <c:if test="${crk.issp eq '0'}">onClick="AuditingPage('${crk.id}','${crk.state}')"</c:if>>
                                                    ${fns:getDictLabel(crk.issp, "storeState", "")}
                                                </div>
                                            </td>
                                            <td align="left" style="width:80px;">
                                                <div style="width: 45px;">
                                                    <div style="margin-top: -3px;padding-left:5px; text-align: center; float: left;" title="追加备注">
                                                        <a href="javascript:Show('${crk.id}');"><img src="${ctxStatic}/images/addBZ.png" style="border-width:0px;"></a>&nbsp;&nbsp;
                                                    </div>
                                                </div>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            </div>
                            <div id="Anthem_myPager_pnlPager__"><div id="myPager_pnlPager" class="lk-mt10 lk-mb10">
                                <table><tbody><tr><div class="pagination">${page}</div></tr></tbody></table>
                            </div>
                            </div>
                        </div>
                    </div>
                </div>
            </td>
            <!-- 已选择 商品  -->
            <td valign="top">
                <div class="panel panel-default" style="min-width: 400px; margin-left: 10px; margin-top: 10px;">
                    <div class="panel-heading">
                        <table style="width: 100%;">
                            <tbody>
                            <tr>
                                <td>商品明细
                                </td>
                                <td align="right" style="font-size: 12px; color: #ff6400">
                                    共<span id="Anthem_lblInProductCount__"><span id="lblInProductCount" class="lk-b">0</span></span>条<span id="spanJE">，
                                     <span id="Anthem_lblInProductAmount__"><span id="lblInProductAmount" class="lk-b">0</span></span>元</span>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <div id="mxBodyDiv" class="panel-body lk-p0" style="padding: 0px; overflow: auto; height: 580px;">
                        <div id="Anthem_panelProudctIn__"><div id="panelProudctIn">
                            <div id="Anthem_rptProductIn__">
                                <table style="width: 100%; font-size: 12px; color: #333333" class="">
                                    <thead><tr style="text-align: center; background-color: #FAFAFA; border-bottom: 1px solid #ccc">
                                        <td style="width: 150px">商品</td>
                                        <td style="width: 70px">数量</td>
                                        <td style="width: 100px" class="flag">单价(元)</td>
                                        <td style="width: 80px">金额(元)</td>
                                    </tr>
                                    </thead>
                                    <tbody id="tbHeader"></tbody>
                                </table>
                            </div>

                        </div></div>
                    </div>
                </div>
            </td>
        </tr>
        </tbody></table>
</div>
<div tabindex="-1" aria-labelledby="title:rkdmx" aria-describedby="content:rkdmx" class="details ui-popup ui-popup-modal ui-popup-show ui-popup-focus" role="alertdialog" style="position: absolute; outline: 0px; left: 5px; top: 1px; z-index: 1051;display:none;">
    <div i="dialog" class="ui-dialog">
        <div class="ui-dialog-arrow-a"></div>
        <div class="ui-dialog-arrow-b"></div>
        <table class="ui-dialog-grid">
            <tbody>
            <tr>
                <td i="header" class="ui-dialog-header">
                    <button i="close" class="ui-dialog-close" title="cancel">×</button>
                    <div i="title" class="ui-dialog-title" id="title:rkdmx">入库单详细信息</div>
                </td>
            </tr>
            <tr>
                <td i="body" class="ui-dialog-body" style="padding: 0px;">
                    <div class="ui-dialog-content" id="content" style="width: 900px; height: 550px;">
                        <iframe src="" id="rkdmx" name="rkdmx" width="100%" height="100%" allowtransparency="yes" frameborder="no" scrolling="auto"></iframe>
                    </div>
                </td>
            </tr>
            <tr>
                <td i="footer" class="ui-dialog-footer" style="display: none;">
                    <div i="statusbar" class="ui-dialog-statusbar" style="display: none;"></div>
                    <div i="button" class="ui-dialog-button"></div>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
<div tabindex="0" class="details" style="z-index: 1050; position: fixed; left: 0px; top: 0px; width: 100%; height: 100%; overflow: hidden; -webkit-user-select: none; opacity: 0.5; background: rgb(0, 0, 0);display:none;"></div>


</body>
</html>
