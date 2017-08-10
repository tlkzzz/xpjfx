<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<!-- saved from url=(0076)http://youbi.liankai.com/KuCunManage/XSSH.aspx?timestamp=0.22613723576068878 -->
<html xmlns="http://www.w3.org/1999/xhtml" ng-app="angularApp" class="ng-scope"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8"><style type="text/css">[ng\:cloak],[ng-cloak],[data-ng-cloak],[x-ng-cloak],.ng-cloak,.x-ng-cloak,.ng-hide:not(.ng-hide-animate){ !important;}ng\:form{display:block;}</style><title>
    出库查询
</title><link rel="stylesheet" type="text/css" href="${ctxStatic}/css/qqqq.css">
    <script src="${ctxStatic}/jquery/jquery-1.8.3.min.js" type="text/javascript"></script>
    <link href="${ctxStatic}/jquery-jbox/2.3/Skins/Bootstrap/jbox.min.css" rel="stylesheet" />
    <script src="${ctxStatic}/jquery-jbox/2.3/jquery.jBox-2.3.min.js" type="text/javascript"></script>


    <script src="${ctxStatic}/css/CoolFold.js"></script>
    <script src="${ctxStatic}/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
    <link href="${ctxStatic}/My97DatePicker/skin/WdatePicker.css" type="text/css" rel="stylesheet"/>
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
            $("#rkdmx").attr("src","xqlist?id="+id);
            $(".details").css("display","block");
        }
        function showSubOrder(id,ele,name,store,rkckdate) {
            if(id=="")return;
            if(ele!=null) {
                $(".lk-selected001").removeClass("lk-selected001");
                ele.addClass("lk-selected001");
            }
            $("#kehu").empty();
            $("#sj").empty();
            $("#ywy").empty();
            $("#tbHeader").empty();
            $("#lblInProductCount").empty();
            $("#lblInProductAmount").empty();
            $.get("${ctx}/ck/cDdinfo/findListByCrk?rkckddinfo.id="+id,function (data) {
                if(data!=null){
                    var sumMoney = 0;
                    if(rkckdate==''){
                        var t="";
                    }else {
                        var mydate = new Date(rkckdate);
                        var t = mydate.toLocaleString();
                    }
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
                    $("#kehu").text(store);
                    $("#sj").text(t);
                    $("#ywy").text(name);
                }else {
                    $("#lblInProductCount").text(0);
                    $("#lblInProductAmount").text(0);
                }
            })
        }
        function page(n,s){
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").submit();
            return false;
        }
        function chaxun(id){
            alert(ckname)
            $.ajax({
                url:"${ctx}/ck/cRkckddinfo/query",
                type:"POST",
                dataType:"json",
                data:{id:id},
                success:function(data){

                    }
        }
        )}
    </script>
    <style type="text/css">
        .positionRight {
            position: absolute;
            right: 0px;
            top: 0px;
        }

        .shadow {
            -moz-box-shadow: -2px -2px 5px #ddd;
            -webkit-box-shadow: -2px -2px 5px #ddd;
            box-shadow: -2px -2px 5px #ddd;
            background-color: white;
        }

        .colorRed {
            color: #D9534F;
        }


        .bgColorGray {
            background-color: #F9F9F9;
        }


        /*新的页面

         */
        .positionRight {
            position: absolute;
            right: 0px;
            top: 0px;
        }

        .shadow {
            -moz-box-shadow: -2px -2px 5px #ddd;
            -webkit-box-shadow: -2px -2px 5px #ddd;
            box-shadow: -2px -2px 5px #ddd;
            background-color: white;
        }

        .colorRed {
            color: #D9534F;
        }
        ::-webkit-scrollbar{
            width: 3px;
            height: 3px;
        }
        ::-webkit-scrollbar-thumb {
            background-color: rgba(0, 0, 0, 0.2);
        }
        ::-webkit-scrollbar-corner{
            background-color: rgba(0, 0, 0, 0.2);
        }
        .bgColorGray {
            background-color: #F9F9F9;
        }
    </style>
</head>
<%--<body ng-controller="mainCtrl" onload="StartPoint()" class="ng-scope">--%>
<!-- 复选框 操作区 -->
<div style="padding: 10px;">
    <table style="width: 100%">
        <tbody>
        <tr>
            <td style="vertical-align: top; width: 280px;">
                <div style="width: 280px; background-color: white;">
                    <!-- Nav tabs -->
                    <ul class="nav nav-tabs nav_pt5" role="tablist" style="width: 270px;">
                        <li ng-class="{&#39;active&#39;:jbxx.type == &#39;0&#39;}" ng-click="selectType(&#39;0&#39;)" class="active"><a>业务员</a></li>
                        <li ng-class="{&#39;active&#39;:jbxx.type == &#39;2&#39;}" ng-click="selectType(&#39;2&#39;)"><a>客户</a></li>
                    </ul>
                    <!-- 左边合计表格 -->
                    <div ng-show="jbxx.type == &#39;0&#39;" style="margin-right: 10px; border-width: 0px 1px 1px; border-right-style: solid; border-bottom-style: solid; border-left-style: solid; border-right-color: rgb(221, 221, 221); border-bottom-color: rgb(221, 221, 221); border-left-color: rgb(221, 221, 221);height: 584px;">
                        <table cellspacing="0" width="100%" cellpadding="0">
                            <tbody><tr>
                                <td>
                                    <!--第一栏-->
                                    <div ng-class="{&#39;lk-selected001&#39; : selectYWYID == item.ywyid}" style="border-bottom: 1px solid #e0e0e0; padding: 4px; cursor: pointer;">
                                        <!--合计-->
                                        <table style="width: 100%; font-size: 14px; height: 30px">
                                            <tbody><tr>
                                                <td style="text-align: left; font-weight: bold;"><span>合计</span></td>
                                                <td style="text-align: right;">
                                                    <!-- ngIf: item.zje != 0 --><span>
                                                                <span style="color: #FF5500; font-weight: bold;"><fmt:formatNumber value="${sum}" pattern="#.####"/> </span><span style="color: #ACACAC;">元</span>
                                                            </span><!-- end ngIf: item.zje != 0 -->
                                                </td>
                                                <td style="width: 30px; text-align: right">
                                                    <!-- ngIf: item.qbcksl>0 --><div>
                                                    <span class="badge ng-binding" style="border-radius: 8px; background-color: #ff5500;">${cd}</span>
                                                </div><!-- end ngIf: item.qbcksl>0 -->

                                                </td>
                                            </tr>
                                            </tbody>
                                        </table>
                                        <!--销 还 处 其他-->
                                        <table ng-class="{true:&#39;&#39;,false:&#39;TwoLevel-Item-Select-body&#39;}[selectYWYID == item.ywyid]" style="font-size: 12px; width: 100%;">
                                            <tbody><tr style="height: 22px">
                                                <td style="width: 25%; padding-left: 4px" title="销售出库">销:<span class="ng-binding">${cd}</span></td>
                                                <td width="25%" title="还货出库">还:<span class="ng-binding">--</span></td>
                                                <td width="25%" title="处理出库">处:<span class="ng-binding">--</span></td>
                                                <td style="text-align: left; padding-right: 4px;" title="其他出库">其他:<span class="ng-binding">--</span></td>
                                            </tr>
                                            </tbody></table>
                                    </div>
                                    <!--第二栏-->
                                    <div style="border-bottom: 1px solid #e0e0e0; padding: 4px; cursor: pointer;" class="ng-scope">
                                        <table style="width: 100%; font-size: 14px; height: 30px">
                                            <tbody>
                                            <c:forEach items="${list}" var="crk" varStatus="status">
                                            <tr <c:if test="${status.first}">class="lk-selected001"</c:if> onclick="chaxun(${crk.createBy.id})">
                                                <td style="text-align: left; font-weight: bold;"><span class="ng-binding" id="ckname">${crk.createBy.name}</span></td>
                                                <td style="text-align: right;">
                                                    <!-- ngIf: item.zje != 0 -->
                                                    <span class="ng-scope">
                                                                <span style="color: #FF5500; font-weight: bold;" class="ng-binding"><fmt:formatNumber value="${sum}" pattern="#.####"/></span><span style="color: #ACACAC;">元</span>
                                                            </span>
                                                </td>
                                                <!--红圆2-->
                                                <td style="width: 30px; text-align: right">
                                                    <div class="ng-scope">
                                                        <span class="badge ng-binding" style="border-radius: 8px; background-color: #ff5500;">${cd}</span>
                                                    </div>
                                                </td>
                                            </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                            <!--销 还 处 其他-->
                                            <table style="font-size: 12px; width: 100%;" class="TwoLevel-Item-Select-body">
                                                <tbody>
                                                <tr style="height: 22px">
                                                    <td style="width: 25%; padding-left: 4px" title="销售出库">销:<span class="ng-binding">${cd}</span></td>
                                                    <td width="25%" title="还货出库">还:<span class="ng-binding">--</span></td>
                                                    <td width="25%" title="处理出库">处:<span class="ng-binding">--</span></td>
                                                    <td style="text-align: left; padding-right: 4px;" title="其他出库">其他:<span class="ng-binding">--</span></td>
                                                </tr>
                                                </tbody></table>
                                    </div>
                                </td>
                            </tr>
                            </tbody></table>
                    </div>
                </div>
            </td>

            <td valign="top">
                <!--顶部-->
                <div style="position: relative;">
                    <!-- 菜单列表 -->
                    <ul id="rightTab" class="nav nav-tabs nav_pt5" role="tablist">
                        <li>
                            <a style="width: 75px">全部
                                <!-- ngIf: ckSl.qbck > 0 --><span style="padding: 0px; background-color: #FF6400; color: #fff; font-weight: bold; position: absolute; top: 6px; z-index: 1;border-radius: 8px;">
                                            <div style="margin: 0px 5px; line-height: 1.4;">
                                                <span class="ng-binding">2</span>
                                            </div>
                                        </span><!-- end ngIf: ckSl.qbck > 0 -->
                            </a>
                        </li>
                        <li>
                            <a style="width: 105px">销售出库
                                <!-- ngIf: ckSl.xsck > 0 --><span style="padding: 0px; background-color: #FF6400; color: #fff; font-weight: bold; position: absolute; top: 6px; z-index: 1;border-radius: 8px;">
                                            <div style="margin: 0px 5px; line-height: 1.4;">
                                                <span class="ng-binding">2</span>
                                            </div>
                                        </span><!-- end ngIf: ckSl.xsck > 0 -->
                            </a>
                        </li>
                        <li>
                            <a style="width: 105px">还货出库
                                <!-- ngIf: ckSl.hhck > 0 -->
                            </a>
                        </li>
                        <li>
                            <a style="width: 105px">处理出库
                                <!-- ngIf: ckSl.clck > 0 -->
                            </a>
                        </li>
                        <li>
                            <a style="width: 105px">其他出库
                                <!-- ngIf: ckSl.qtck > 0 -->
                            </a>
                        </li>
                    </ul>
                    <!--右侧帮助-->
                    <div style="position: absolute; padding: 0px; color: #088AE3; top: 8px; right: 10px; text-align: right; z-index: 999">
                                <span id="spanLoading" style="display: none;">
                                    <a class="btn btn-danger btn-xs" href="javascript:void(0);" style="display: none;">正在加载...</a>
                                </span>


                        <a id="helpTip" href="javascript:void(0);" style="font-size: 13px; text-decoration: none" data-hasqtip="0">帮助
                            <img style="padding-bottom: 3px;" src="images/shipintb.png">
                        </a>
                    </div>
                </div>

                <div id="middleDiv" class="panel panel-default" style="padding: 10px; border-top-width: 0px; height: 582px; overflow: auto;">
                    <!--第二列第一栏-->
                    <form:form id="searchForm" modelAttribute="cRkckddinfo" action="${ctx}/ck/cRkckddinfo/query" method="post" class="breadcrumb form-search">
                    <table style="margin-bottom: 10px;">
                        <tbody>
                        <tr>
                            <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
                            <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
                            <td>
                                <div class="input-group" style="width: 180px;">
                                    <div class="input-group-btn" >
                                        <div class="input-group-btn">
                                        <button type="button" class="btn btn-default dropdown-toggle lk-p5" data-toggle="dropdown" aria-expanded="false" style="height: 34px; background-color: #EEEEEE;">
                                            出库日期<span class="caret"></span>

                                        <input name="startDate" id="startDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
                                               value="<fmt:formatDate value="${cRkckddinfo.startDate}" pattern="yyyy-MM-dd"/>"
                                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd', maxDate:'#F{$dp.$D(\'endDate\')}',isShowClear:false});"/>
                                            </button>
                                        </div>
                            <td>
                                <div class="input-group" style="width: 130px; padding-left: 10px;">
                                    <button type="button" class="btn btn-default dropdown-toggle lk-p5" data-toggle="dropdown" aria-expanded="false" style="height: 34px; background-color: #EEEEEE;">
                                        到 <span class="caret"></span>

                                        <input name="endDate" id="endDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
                                               value="<fmt:formatDate value="${cRkckddinfo.startDate}" pattern="yyyy-MM-dd"/>"
                                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd', maxDate:'#F{$dp.$D(\'endDate\')}',isShowClear:false});"/>
                                    </button>
                                </div>
                            </td>
                            <!--出库仓库-->
                            <td>
                                <div class="input-group" style="padding-left: 10px;">
                                    <span class="input-group-addon lk-p5">仓库</span>
                                    <span id="Anthem_dropCK__">
                                          <form:select path="cHouse.id" class="form-control lk-p5" style="width:150px;">
                                              <form:option selected="selected" value="">请选择</form:option>
                                              <form:options items="${houseList}" itemLabel="name" itemValue="id" htmlEscape="false"></form:options>
                                          </form:select>
                                            </span>
                                </div>
                            </td>
                            <!--查询-->
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
                    <!--第二列第二栏-->
                    <table id="divMore" style="margin-bottom: 10px;">
                        <tbody>
                        <tr>
                            <td>
                                <div class="input-group">
                                    <span style="" class="input-group-addon lk-p5">审核状态</span>
                                    <span id="Anthem_dropSHZT__">
                                              <form:select path="issp" class="form-control lk-p5" style="width:100px;">
                                                  <form:option selected="selected" value="">请选择</form:option>
                                                  <form:options items="${fns:getDictList('storeState')}" itemLabel="label" itemValue="value" htmlEscape="false"></form:options>
                                              </form:select>
                                            </span>
                                </div>
                            </td>



                            <!--红冲状态-->
                            <%--<td style="padding-left: 10px;">--%>
                                <%--<div class="input-group">--%>
                                    <%--<span style="" class="input-group-addon lk-p5">红冲状态</span>--%>
                                    <%--<select id="dropHCZT" ng-model="jbxx.hczt" class="form-control lk-p5 ng-pristine ng-untouched ng-valid" ng-change="SearchDataList()">--%>
                                        <%--<option value="">全部</option>--%>
                                        <%--<option value="0" selected="selected">未红冲</option>--%>
                                        <%--<option value="1">已红冲</option>--%>
                                    <%--</select>--%>
                                <%--</div>--%>
                            <%--</td>--%>
                            <!--打印状态-->
                            <%--<td style="padding-left: 10px;">--%>
                                <%--<div class="input-group">--%>
                                    <%--<span class="input-group-addon lk-p5">打印状态</span>--%>
                                    <%--<select id="dropDYZT" ng-model="jbxx.dyzt" class="form-control lk-p5 ng-pristine ng-untouched ng-valid" ng-change="SearchDataList()">--%>
                                        <%--<option value="-1" selected="selected">全部</option>--%>
                                        <%--<option value="0">未打印</option>--%>
                                        <%--<option value="1">已打印</option>--%>
                                    <%--</select>--%>
                                <%--</div>--%>
                            <%--</td>--%>
                            <!--打印类型-->
                            <%--<td style="padding-left: 10px;">--%>
                                <%--<div id="divDDLX" class="input-group">--%>
                                    <%--<span class="input-group-addon lk-p5">打印类型</span>--%>
                                    <%--<select id="dropDYLX" ng-model="jbxx.dylx" class="form-control lk-p5 ng-pristine ng-untouched ng-valid" style="width: 140px;">--%>
                                        <%--<option value="0" selected="selected">标准版(210*93|原PSD)</option>--%>
                                        <%--<option value="1">A4版(210*297)</option>--%>
                                        <%--<option value="2">A5版(210*140)</option>--%>
                                        <%--<option value="3">定制报表</option>--%>
                                    <%--</select>--%>
                                <%--</div>--%>
                            <%--</td>--%>
                            <!--收款方式-->
                            <%--<td style="padding-left: 10px;">--%>
                                <%--<div id="div1" class="input-group">--%>
                                    <%--<span class="input-group-addon lk-p5">收款方式</span>--%>
                                    <%--<select id="dropSKFS" ng-model="jbxx.skfs" class="form-control lk-p5 ng-pristine ng-untouched ng-valid" ng-change="SearchDataList()">--%>
                                        <%--<option value="0" selected="selected">全部类型</option>--%>
                                        <%--<option value="401">混合</option>--%>
                                        <%--<option value="402">现款</option>--%>
                                        <%--<option value="403">预收</option>--%>
                                        <%--<option value="404">应收</option>--%>
                                    <%--</select>--%>
                                <%--</div>--%>
                            <%--</td>--%>
                            <!--作废状态-->
                            <%--<td style="padding-left: 10px;">--%>
                                <%--<div class="input-group">--%>
                                    <%--<span class="input-group-addon lk-p5">作废状态</span>--%>
                                    <%--<select id="dropZFZT" ng-model="jbxx.zfzt" class="form-control lk-p5 ng-pristine ng-untouched ng-valid" ng-change="SearchDataList()">--%>
                                        <%--<option value="0">未作废</option>--%>
                                        <%--<option value="1">已作废</option>--%>
                                    <%--</select>--%>
                                <%--</div>--%>
                            <%--</td>--%>
                            <!--商品-->
                            <%--<td style="padding-left: 10px;">--%>
                                <%--<div class="input-group">--%>
                                    <%--<span class="input-group-addon lk-p5">商品</span>--%>
                                            <%--<span>--%>
                                                 <%--<form:select path="goods.id" class="form-control lk-p5" style="width:150px;">--%>
                                                     <%--<form:option selected="selected" value="">请选择</form:option>--%>
                                                     <%--<form:options items="${goodsList}" itemLabel="name" itemValue="id" htmlEscape="false"></form:options>--%>
                                                 <%--</form:select>--%>
                                            <%--</span>--%>
                                <%--</div>--%>
                            <%--</td>--%>
                        </tr>
                        </tbody>
                    </table>
                    </form:form>
                    <!--全部单据菜单栏-->
                    <ul class="nav nav-tabs" role="tablist">
                        <li class="active"><a>全部单据</a></li>
                        <li><a>变价单据</a></li>
                        <li><a>未变价单据</a></li>
                    </ul>

                    <div style="padding: 10px 10px">
                        <div id="Anthem_grid__"><div>
                            <table class="lk-table003 table-hover" cellspacing="0" border="0" id="grid" style="width:100%;border-collapse:collapse;">
                                <tbody>
                                <tr class="table-header">
                                    <th scope="col">
                                        <input type="checkbox" id="chkCheckAll" name="chkAllSelect" title="全选/取消全选">
                                    </th>
                                    <th scope="col" style="width:65px;">出库单号</th>
                                    <th scope="col">客户</th>
                                    <th scope="col">业务员</th>
                                    <th scope="col">出库仓库</th>
                                    <th scope="col">操作人</th>
                                    <th scope="col">出库时间</th>
                                    <th scope="col">金额</th>
                                    <th scope="col">状态</th>
                                </tr>
                                        <c:forEach items="${page.list}" var="cRkckddinfo" varStatus="status">
                                            <tr <c:if test="${status.first}">class="lk-selected001"</c:if> onclick="showSubOrder('${cRkckddinfo.id}',$(this),'${cRkckddinfo.createBy.name}','${cRkckddinfo.store.name}','${cRkckddinfo.rkckdate}')">
                                            <%--<tr <c:if test="${status.first}">class="lk-selected001"</c:if> onclick="showSubOrder('${crk.id}',$(this),'${crk.createBy.name}','${crk.store.name}')">--%>
                                            <%--<tr <c:if test="${status.first}">class="lk-selected001"</c:if> onclick="showSubOrder('${cRkckddinfo.id}',$(this))">--%>
                                                <td align="center" style="width:35px;">
                                                    <input type="checkbox" name="chkSelect" value="${cRkckddinfo.id}" style="margin: 2px 0px 0px 1px"/>
                                                </td>
                                                <td align="center">
                                                    <div title="出库单号">
                                                        <a href="javascript:Show('${cRkckddinfo.id}');">${cRkckddinfo.ddbh}</a>
                                                        <%--<a href="javascript:void(0)" onclick="openForm('${cRkckddinfo.id}')">--%>
                                                                <%--${cRkckddinfo.ddbh}--%>
                                                        <%--<a href="javascript:Show('${cRkckddinfo.id}');">${cRkckddinfo.ddbh}</a>--%>
                                                        <%--<a href="javascript:void(0)" onclick="openForm('${cRkckddinfo.id}')">${cRkckddinfo.ddbh}</a>--%>
                                                    </div>
                                                </td>
                                                <td align="center">${cRkckddinfo.store.name}</td>
                                                <td align="center">${cRkckddinfo.createBy.name}</td>
                                                <td align="center">${cRkckddinfo.cHouse.name}</td>
                                                <td align="center">
                                                    <div title="操作人：${cRkckddinfo.createBy.name}">${cRkckddinfo.createBy.name}</div>
                                                </td>
                                                <td align="center" style="width:155px;">
                                                    <div title="出库时间：<fmt:formatDate value='${cRkckddinfo.rkckdate}' pattern='yyyy-MM-dd HH:mm'/>"><fmt:formatDate value='${cRkckddinfo.rkckdate}' pattern='yyyy-MM-dd HH:mm'/></div>
                                                </td>
                                                <td align="center">${cRkckddinfo.htje}</td>
                                                <td align="center">
                                                ${fns:getDictLabel(cRkckddinfo.issp, "storeState", "")}
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
</td>

            <!-- 已选择 商品  -->
            <td valign="bottom">
                <div class="panel panel-default" style="min-width: 400px; margin-left: 10px; margin-top: 10px;">
                    <div class="panel-heading" id="diandian">
                        <table style="width: 100%;" >
                            <tbody id="xiao">
                            <tr style="height: 28px;">
                                <td colspan="2" style="font-size: 13px;">
                                    <span style="font-weight: bold;" >客户：</span><span id="kehu"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <span style="font-weight: bold;">业务员：</span><span id="ywy"></span>&nbsp;&nbsp;&nbsp;
                                    <span style="font-weight: bold;">出库时间：</span><span id="sj"></span>&nbsp;&nbsp;&nbsp;
                                </td>
                                <td style="float: right;">
                                    <img style="padding-bottom: 4px;margin-left: 10px;" src="images/xxx.png">
                                </td>
                            </tr>
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





  <div tabindex="-1" aria-labelledby="title:rkdmx" aria-describedby="content:rkdmx" class="details ui-popup ui-popup-modal ui-popup-show ui-popup-focus" role="alertdialog" style="position: absolute; outline: 0px; left: 349px; top: 46px; z-index: 1051;display:none;">
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
                                                    <div i="content" class="ui-dialog-content" id="content:rkdmx" style="width: 900px; height: 550px;">
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
</body>

</html>