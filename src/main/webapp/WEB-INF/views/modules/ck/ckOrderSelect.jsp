<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>入库查询</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%--<meta name="decorator" content="default"/>--%>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/css/jjj.css">
    <link href="${ctxStatic}/My97DatePicker/skin/WdatePicker.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="${ctxStatic}/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="${ctxStatic}/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {

        });
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
                        <li class="active" style="width: 70px;">
                            <a>全部&nbsp;
                                <span class="badge" style="position: absolute; padding: 0px; background-color: #FF6400; color: #fff; font-weight: bold; top: 3px; text-align: center; z-index: 999">
                                        <div style="margin: 0px 5px; line-height: 1.4">
                                            <span id="Anthem_lblqb__"><span id="lblqb">4</span></span>
                                        </div>
                                    </span>
                            </a>
                        </li>
                        <li style="width: 70px;">
                            <a>进货&nbsp;
                                <span class="badge" style="position: absolute; padding: 0px; background-color: #FF6400; color: #fff; font-weight: bold; top: 3px; text-align: center; z-index: 999">
                                        <div style="margin: 0px 5px; line-height: 1.4">
                                            <span id="Anthem_lbljh__"><span id="lbljh">1</span></span>
                                        </div>
                                    </span>
                            </a>
                        </li>
                        <li style="width: 95px;">
                            <a>期初入库&nbsp;
                                <span class="badge" style="position: absolute; padding: 0px; background-color: #FF6400; color: #fff; font-weight: bold; top: 3px; text-align: center; z-index: 999">
                                        <div style="margin: 0px 5px; line-height: 1.4">
                                            <span id="Anthem_lblqcrk__"><span id="lblqcrk"></span></span>
                                        </div>
                                    </span>
                            </a>
                        </li>
                        <li style="width: 95px;">
                            <a>其他入库&nbsp;
                                <span class="badge" style="position: absolute; padding: 0px; background-color: #FF6400; color: #fff; font-weight: bold; top: 3px; text-align: center; z-index: 999">
                                            <div style="margin: 0px 5px; line-height: 1.4">
                                                <span id="Anthem_lblqtrk__"><span id="lblqtrk"></span></span>
                                            </div>
                                        </span>
                            </a>
                        </li>
                        <li style="width: 95px;">
                            <a>进货退货&nbsp;
                                <span class="badge" style="position: absolute; padding: 0px; background-color: #FF6400; color: #fff; font-weight: bold; top: 3px; text-align: center; z-index: 999">
                                            <div style="margin: 0px 5px; line-height: 1.4">
                                                <span id="Anthem_lbljhth__"><span id="lbljhth"></span></span>
                                            </div>
                                        </span>
                            </a>
                        </li>
                        <li style="width: 95px;">
                            <a>换货入库&nbsp;
                                <span class="badge" style="position: absolute; padding: 0px; background-color: #FF6400; color: #fff; font-weight: bold; top: 3px; text-align: center; z-index: 999">
                                            <div style="margin: 0px 5px; line-height: 1.4">
                                                <span id="Anthem_lblhhrk__"><span id="lblhhrk"></span></span>
                                            </div>
                                        </span>
                            </a>
                        </li>
                        <li style="width: 100px;">
                            <a>移库入库&nbsp;
                                <span class="badge" style="position: absolute; padding: 0px; background-color: #FF6400; color: #fff; font-weight: bold; top: 3px; text-align: center; z-index: 999">
                                            <div style="margin: 0px 5px; line-height: 1.4">
                                                <span id="Anthem_lblykrk__"><span id="lblykrk">3</span></span>
                                            </div>
                                        </span>
                            </a>
                        </li>
                    </ul>
                    <div id="helpTip" style="position: absolute; padding: 0px; color: #088AE3; font-size: 13px; top: 8px; right: 0px; text-align: right; z-index: 999" data-hasqtip="0">
                        <a href="javascript: void(0);" style="font-size: 13px; text-decoration: none">帮助
                            <img style="padding-bottom: 3px;" src="images/shipintb.png">
                        </a>
                    </div>
                </div>

                <div class="panel panel-default" style="width: 100%; margin-right: 10px; border-top: none;">
                    <div id="zbBodyDiv" class="panel-body" style="padding: 0px; height: 582px; overflow: auto;">
                        <div id="Anthem_pnlSearch__"><div id="pnlSearch">
                        <form:form id="searchForm" modelAttribute="cRkckddinfo" action="${ctx}/ck/cCar/" method="post" class="breadcrumb form-search">
                            <table cellpadding="0" cellspacing="0" style="margin-left: 10px; margin-top: 10px;">
                                <tbody><tr>
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
                                    <c:forEach items="${page.list}" var="crk">
                                        <tr style="vnd.ms-excel.numberformat:@" class="lk-selected001">
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
                                                <div id="Anthem_grid_ctl02_pnlTop__">${fns:getDictLabel(crk.issp, "storeState", "")}</div>
                                            </td>
                                            <td align="left" style="width:80px;">
                                                <div style="width: 45px;">
                                                    <div style="margin-top: -3px;padding-left:5px; text-align: center; float: left;" title="追加备注">
                                                        <a href="javascript:void(0);">追加备注<img src="${ctxStatic}/images/addBZ.png" style="border-width:0px;"></a>&nbsp;&nbsp;
                                                    </div>
                                                </div>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    <%--列子开始--%>
                                    <tr style="vnd.ms-excel.numberformat:@">
                                        <td align="center" style="width:35px;">
                                            <input type="checkbox" name="chkSelect" id="chkSelect" fieldvalue="5ff85f48-da38-d06b-9447-a78a0084fb88" style="margin: 2px 0px 0px 1px">
                                        </td><td align="center">
                                        <div title="入库单号：4377">
                                            <a href="javascript:Show('5ff85f48-da38-d06b-9447-a78a0084fb88');">
                                                4377</a>
                                        </div></td><td>东二区（邹赣）-仓库</td><td>无</td><td align="left" style="width:155px;">
                                        <div title="入库日期：2017-07-07 08:04">07-07 08:04</div>
                                        <div style="color: #ACACAC; text-align: left" title="备注：终端移库。">
                                            <span id="grid_ctl03_Label1">终端移库。</span>
                                        </div>

                                    </td><td align="right">
                                        70.00
                                        <span id="Anthem_grid_ctl03_lblRKLX__"><span id="grid_ctl03_lblRKLX" style="display: none">202</span></span>
                                    </td><td align="center">
                                        <div title="操作人：管理员">
                                            管理员
                                            <div title="打印次数：0" style="color: #ACACAC; text-align: center">
                                                <span style="color:#D9534F">未打印</span>
                                            </div>
                                        </div></td><td align="center" valign="middle" style="width:80px;">
                                        <div id="Anthem_grid_ctl03_pnlTop__"></div>
                                        <!-- 未审核 已审核 -->
                                        <div id="Anthem_grid_ctl03_pnlExamineState__"></div>

                                    </td><td align="left" style="width:80px;">

                                        <div id="Anthem_grid_ctl03_pnlPrint__"><div id="grid_ctl03_pnlPrint">

                                            <a href="javascript:void(0);">打印</a>
                                            /
                                            <a href="javascript:void(0);">预览</a>

                                        </div></div>
                                        <div style="width: 45px;">
                                            <div style="margin-top: -3px;padding-left:5px; text-align: center; float: left;" title="追加备注">
                                                <a href="javascript:void(0);">
                                                    <img id="grid_ctl03_imageAddMark" src="images/addBZ.png" alt="追加备注" style="border-width:0px;"></a>&nbsp;&nbsp;
                                            </div>
                                            <div id="Anthem_grid_ctl03_pnlCopyRKD__"><div id="grid_ctl03_pnlCopyRKD" title="复制单据" style="text-align: left; float: right;">

                                                <a href="javascript:void(0);">
                                                </a>

                                            </div></div>
                                        </div>
                                    </td>
                                    </tr>
                                    <%--列子结束--%>

                                    </tbody>
                                </table>
                            </div>
                            </div>

                            <style>
                                .btnPageSize { background-color: #fff; border: 0px; width: 100%; height: 28px; line-height: 28px; }
                                .btnPageSize:hover { background-color: #eee; }
                            </style>
                            <div id="Anthem_myPager_pnlPager__"><div id="myPager_pnlPager" class="lk-mt10 lk-mb10">
                                <table><tbody><tr><div class="pagination">${page}</div></tr></tbody></table>
                                <span id="Anthem_myPager_hfChangePageSize__"><input type="hidden" name="myPager$hfChangePageSize" id="myPager_hfChangePageSize"></span>
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
                            <tbody><tr>
                                <td>商品明细
                                </td>
                                <td align="right" style="font-size: 12px; color: #ff6400">共
                                    <span id="Anthem_lblInProductCount__"><span id="lblInProductCount" class="lk-b">2</span></span>
                                    条<span id="spanJE">，
                                                            <span id="Anthem_lblInProductAmount__"><span id="lblInProductAmount" class="lk-b">315.00</span></span>
                                                元</span>
                                </td>
                            </tr>
                            </tbody></table>
                    </div>
                    <div id="mxBodyDiv" class="panel-body lk-p0" style="padding: 0px; overflow: auto; height: 580px;">
                        <div id="Anthem_panelProudctIn__"><div id="panelProudctIn">

                            <div id="Anthem_rptProductIn__">
                                <table id="tbHeader" style="width: 100%; font-size: 12px; color: #333333" class="">
                                    <tbody><tr style="text-align: center; background-color: #FAFAFA; border-bottom: 1px solid #ccc">
                                        <td style="width: 150px">商品
                                        </td>
                                        <td style="width: 70px">数量
                                        </td>
                                        <td style="width: 100px" class="flag">单价(元)
                                        </td>
                                        <td style="width: 80px">金额(元)
                                        </td>
                                    </tr>

                                    <tr>
                                        <td style="padding: 6px 3px;">
                                            <table>
                                                <tbody><tr>
                                                    <td><span class="label label-info" style="font-size: 9px; background-color: #F0AD4E;">1</span></td>
                                                    <td style="font-weight: bold;">华德手撕肉干-香辣味450g</td>
                                                </tr>
                                                <tr>
                                                    <td></td>
                                                    <td style="color: #ACACAC">
                                                        1*26*30
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td></td>
                                                    <td>
                                                        <div style="width: 130px; color: #ACACAC">
                                                            <span id="rptProductIn_ctl01_lblProductId" style="display: none">e263d5ab-5f3b-414b-86dc-a69c00be78a8</span>
                                                            <span id="rptProductIn_ctl01_lblAllTM" title="6938890500109,6938890500054,">6938890500109...</span>
                                                            <input type="hidden" name="rptProductIn$ctl01$hdfAllTM" id="rptProductIn_ctl01_hdfAllTM" value="6938890500109,6938890500054,">
                                                        </div>
                                                    </td>
                                                </tr>
                                                </tbody></table>
                                        </td>
                                        <td style="text-align: right;" title="换算率: 780">
                                            5盒
                                        </td>
                                        <td style="text-align: right;" data-lk="price">
                                            <div title="1092.00">
                                                1092.00
                                                /
                                                件
                                            </div>
                                            <div style="color: #acacac" title="1.40">
                                                1.40
                                                /
                                                包
                                            </div>
                                        </td>
                                        <td style="text-align: right; padding-right: 3px;" data-lk="je">
                                            210.00
                                        </td>
                                    </tr>
                                    <tr style="border-bottom: 1px solid #DDDDDD">
                                        <td colspan="4" style="padding: 0px 18px; text-align: left;">
                                            <table>
                                                <tbody><tr>
                                                    <td>
                                                        <div style="padding: 0px;">
                                                            <div title="人库仓库：电动1仓（吴志敏）仓库">
                                                                <span id="rptProductIn_ctl01_lblCKMC" style="color: #337AB7; font-size: 12px;">电动1仓（吴志敏）仓库</span>
                                                                &nbsp;&nbsp;&nbsp;&nbsp;
                                                            </div>
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <div title="备注：">
                                                            <div id="Anthem_rptProductIn_ctl01_pnlBZ__"></div>
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <div title="生产日期：2000-01-01">

                                                        </div>
                                                    </td>
                                                </tr>
                                                </tbody></table>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td style="padding: 6px 3px;">
                                            <table>
                                                <tbody><tr>
                                                    <td><span class="label label-info" style="font-size: 9px; background-color: #F0AD4E;">2</span></td>
                                                    <td style="font-weight: bold;">玉峰香辣鱼（盒装）</td>
                                                </tr>
                                                <tr>
                                                    <td></td>
                                                    <td style="color: #ACACAC">
                                                        1*12*30
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td></td>
                                                    <td>
                                                        <div style="width: 130px; color: #ACACAC">
                                                            <span id="rptProductIn_ctl02_lblProductId" style="display: none">72dd24fe-1c0f-40aa-8ef8-a69a00b6a340</span>
                                                            <span id="rptProductIn_ctl02_lblAllTM" title="6930487946567,6930487946666,">6930487946567...</span>
                                                            <input type="hidden" name="rptProductIn$ctl02$hdfAllTM" id="rptProductIn_ctl02_hdfAllTM" value="6930487946567,6930487946666,">
                                                        </div>
                                                    </td>
                                                </tr>
                                                </tbody></table>
                                        </td>
                                        <td style="text-align: right;" title="换算率: 360">
                                            5盒
                                        </td>
                                        <td style="text-align: right;" data-lk="price">
                                            <div title="252.00">
                                                252.00
                                                /
                                                件
                                            </div>
                                            <div style="color: #acacac" title="0.70">
                                                0.70
                                                /
                                                包
                                            </div>
                                        </td>
                                        <td style="text-align: right; padding-right: 3px;" data-lk="je">
                                            105.00
                                        </td>
                                    </tr>
                                    <tr style="border-bottom: 1px solid #DDDDDD">
                                        <td colspan="4" style="padding: 0px 18px; text-align: left;">
                                            <table>
                                                <tbody><tr>
                                                    <td>
                                                        <div style="padding: 0px;">
                                                            <div title="人库仓库：电动1仓（吴志敏）仓库">
                                                                <span id="rptProductIn_ctl02_lblCKMC" style="color: #337AB7; font-size: 12px;">电动1仓（吴志敏）仓库</span>
                                                                &nbsp;&nbsp;&nbsp;&nbsp;
                                                            </div>
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <div title="备注：">
                                                            <div id="Anthem_rptProductIn_ctl02_pnlBZ__"></div>
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <div title="生产日期：2000-01-01">

                                                        </div>
                                                    </td>
                                                </tr>
                                                </tbody></table>
                                        </td>
                                    </tr>

                                    </tbody></table>
                            </div>

                        </div></div>
                    </div>
                </div>
            </td>
        </tr>
        </tbody></table>
</div>
<%--<div tabindex="-1" aria-labelledby="title:rkdmx" aria-describedby="content:rkdmx" class="ui-popup ui-popup-modal ui-popup-show ui-popup-focus" role="alertdialog" style="position: absolute; outline: 0px; left: 349px; top: 46px; z-index: 1051;"><div i="dialog" class="ui-dialog"><div class="ui-dialog-arrow-a"></div><div class="ui-dialog-arrow-b"></div><table class="ui-dialog-grid"><tbody><tr><td i="header" class="ui-dialog-header"><button i="close" class="ui-dialog-close" title="cancel">×</button><div i="title" class="ui-dialog-title" id="title:rkdmx">入库单详细信息</div></td></tr><tr><td i="body" class="ui-dialog-body" style="padding: 0px;"><div i="content" class="ui-dialog-content" id="content:rkdmx" style="width: 900px; height: 550px;"><iframe src="RKDDetails.html" name="rkdmx" width="100%" height="100%" allowtransparency="yes" frameborder="no" scrolling="auto"></iframe></div></td></tr><tr><td i="footer" class="ui-dialog-footer" style="display: none;"><div i="statusbar" class="ui-dialog-statusbar" style="display: none;"></div><div i="button" class="ui-dialog-button"></div></td></tr></tbody></table></div></div><div tabindex="0" style="z-index: 1050; position: fixed; left: 0px; top: 0px; width: 100%; height: 100%; overflow: hidden; -webkit-user-select: none; opacity: 0.5; background: rgb(0, 0, 0);"></div>--%>


</body>
</html>
