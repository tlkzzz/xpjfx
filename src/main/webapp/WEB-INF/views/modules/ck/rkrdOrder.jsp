<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>入库录单</title>
    <link href="${ctxStatic}/css/ckgl.css" type="text/css" rel="stylesheet"/>
    <script src="${ctxStatic}/jquery/jquery-1.8.3.min.js" type="text/javascript"></script>
    <link href="${ctxStatic}/jquery-jbox/2.3/Skins/Bootstrap/jbox.min.css" rel="stylesheet" />
    <script src="${ctxStatic}/jquery-jbox/2.3/jquery.jBox-2.3.min.js" type="text/javascript"></script>
    <script src="${ctxStatic}/order-jquery/order-jquery.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            getGclass('${gClass[0].id}',$(".box1 .fen1 .xixi:first"));
            <c:if test="${not empty cRkckddinfo}">jblist();</c:if>
            <c:if test="${not empty json}">showGoodsList();</c:if>
        });
        function  jblist() {
            if($("#supplier").val()==""){
                message("请选择供应商")
                return;
            }
            if($("#house").val()==""){
                message("请选择仓库")
                return;
            }
            var supplier = $("#supplier option:selected").text();
            var house = $("#house option:selected").text();
            var bz = $("#bz").val();
            if(supplier!="" || house!="" ) {
                $("#bb").text(supplier);
                $("#aa").text(house);
                $("#zz").text(bz);
                $("#supplierlist").val($("#supplier").val());
                $("#houselist").val($("#house").val());
                $("#bzlist").val(bz);
                $(".box5").css("display","none");
            }else{
                message("信息填写不完整");
                return;
            }
        }
        function checkFormInfo() {
            if($("#supplierlist").val()==""){message("供应商未选择");return false;}
            if($("#houselist").val()==""){message("仓库未选择");return false;}
            var jsonData = $("#jsonData").val();
            if(jsonData==""||eval("("+jsonData+")").length<=0){message("请填写单据");return false;}
            return true;
        }
        function changePayTable(restore,change) {
            $("#"+restore).css("display","block");
            $("#"+change).css("display","none");
        }
        function Auditing(id) {
            var account = $("#dropFKZH").val();
            alert(account);
            var travelAccount = $("#fkAccount").val();
            alert(travelAccount)
            var total = $("#sfje").val();
            alert(total);
            if(id==""){message("请先提交订单后在进行审核!");return false;}
            if(total==""){message("付款金额必须大于零!");return false;}
            if(account==""){message("请选择付款账户!");return false;}
            if(travelAccount==""){message("请填写收款账户!");return false;}
            $("#aSubmitAndAudit").css("display","none");
            $("#aAfterSubmitAndAudit").css("display","block");
            $.post("../rkReview",{id:id,account:account,travelAccount:travelAccount,total:total},function (data) {
                if(data=="true"){
                    window.parent.dialogClose();
                    window.parent.location.reload();
                    message("审核成功!");
                }else {
                    $("#aSubmitAndAudit").css("display","block");
                    $("#aAfterSubmitAndAudit").css("display","none");
                    message("审核失败,请刷新后重新审核!");
                }
            });
        }
    </script>
</head>
<body>
<input id="ctx" type="hidden" value="${ctx}">
<input id="ctxStatic" type="hidden" value="${ctxStatic}">
<div class="box">
    <div style="height: 40px;">
        <ul class="nav nav-tabs">
            <li class="active" style="border: 0;border-top: 1px solid #d3d3d3;border-left: 1px solid #d3d3d3;border-right: 1px solid #d3d3d3;padding: 4px 4px 0;border-top-left-radius: 4px;border-top-right-radius: 4px;"><a href="" style="text-decoration: none;out-line: none;color: #000;">入库录单</a></li>
        </ul>
    </div>
    <div class="box1">
        <div class="mingcheng">商品类别</div>
        <div class="bb" style="float: left;">
            <ul class="fen1">
                <c:forEach items="${gClass}" var="gc">
                    <li class="xi xixi" onclick="getGclass('${gc.id}',$(this));">${gc.name}<span class="span">${gc.sort}</span></li>
                </c:forEach>
            </ul>
        </div>
        <div class="bold xixi" style="padding-left: 10px;">全部<span style="font-weight: normal;float: right;color: #b3b3b3;" id="gClassNum">0</span></div>
        <div class="bb" style="height: 506px;overflow-x: hidden;">
            <ul class="fen2" style="width: 117px;"><%--商品分类二级列表--%></ul>
        </div>
        <div class="clearfix"></div>
    </div>
    <div class="box2">
        <div class="n1">
            <p>商品</p>
            <p style="color: #678AF9;font-size: 12px;margin-top: 1%;margin-left: 4%;">全部添加</p>
            <p style="color: red;font-size: 12px;margin-top: 1%;float: right;" id="goodsNum">共 0 条</p>
            <input type="hidden" id="goodsList"/>
            <div class="clearfix"></div>
        </div>
        <div class="bb"><%--商品内容列表--%></div>
    </div>
    <div class="box3">
        <div class="panel-heading">
            <table class="biaoti">
                <tr>
                    <td>基本信息</td>
                    <td style="text-align: right;font-size: 12px;color:#678AF9;" onclick="$('.box5').css('display','block')">编辑</td>
                </tr>
            </table>
            <div style="padding: 0px;height: 60px;overflow: auto;">
                <table class="panel-body">
                    <tbody>
                    <tr>
                        <td>入库仓库:</td>
                        <td id="aa">${cRkckddinfo.cHouse.name}</td>
                        <td>供 应 商：</td>
                        <td id="bb">${cRkckddinfo.supplier.name}</td>
                    </tr>
                    <tr>
                        <td>备  注：</td>
                        <td id="zz">${cRkckddinfo.remarks}</td>
                        <td></td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="shangpin">
            <!--商品-->
            <div class="input-group">
                <div class="input-group-addon" style="padding: 6px 0;"><span>商品</span></div>
                <input id="goodsName" type="text" readonly="true" class="shuru" style="padding: 6px 0;width: 250px;">
                <input id="goodsId" type="hidden"><input id="cdId" type="hidden"><input id="orderNum" type="hidden"><input id="orderGoods" type="hidden"><%--商品ID/订单数量/商品信息--%>
                <span class="input-group-addon2"><img src="${ctxStatic}/images/shanchu.png" onclick="clearDate()" style="margin: 0 auto;"></span>
                <div class="clearfix"></div>
            </div>
            <!--规格型号-->
            <div class="lk-tal">
                <div class="left">规格型号&nbsp;:&nbsp;<span id="specName"></span></div>
                <div class="right">安全库存&nbsp;:&nbsp;&nbsp;<span id="anQuanKC"></span></div>
                <div class="right" style="margin-right: 60px;">可用库存&nbsp;:&nbsp;&nbsp;<span id="keYongKC"></span></div>
                <div class="clearfix"></div>
            </div>
            <!--数量-->
            <div class="input-group" style="border: 0">
                <div class="input-group-1" style="border: 1px solid #d3d3d3;">
                    <div class="shuliang"><span>数量</span></div>
                    <div class="jian" onclick="bigNumChange(parseInt($('#bigNum').val())-1)">-</div>
                    <input id="bigNum" onchange="bigNumChange($(this).val())" type="text" style="width: 89px;padding: 2px 0;">
                    <div class="xiang bigUnit"></div>
                    <div class="jia" onclick="bigNumChange(parseInt($('#bigNum').val())+1)">+</div>
                    <div class="clearfix"></div>
                </div>
                <div class="input-group-1" style="float: right;width: 40%;padding: 0.2% 0; border: 1px solid #d3d3d3;">
                    <div class="jian" onclick="zongNumChange(parseInt($('#zongNum').val())-1)" style="margin-top: -1px;">-</div>
                    <div style="margin-top: -2px;float: left;">
                        <input id="zongNum" onchange="zongNumChange($(this).val())" type="text" style="width: 54px;padding: 1px 0;margin-top: 1px;"></div>
                    <div class="xiang zongUnit" style="width: 25px;height: 20px;margin-top: -1px;"></div>
                    <div class="jia" onclick="zongNumChange(parseInt($('#zongNum').val())+1)" style="margin-top: -1px;">+</div>
                    <div class="clearfix"></div>
                </div>
                <div class="clearfix"></div>
                <div class="input-group-1 smallEle" style="float: right;width: 40%;border: 1px solid #d3d3d3;margin-top: 8px;">
                    <div class="jian" onclick="smallNumChange(parseInt($('#smallNum').val())-1)" style="margin-top: -1px;">-</div>
                    <input id="smallNum" onchange="smallNumChange($(this).val())" type="text" style="width: 55px;">
                    <div class="xiang smallUnit" style="width: 25px;height: 20px;margin-top: -1px;"></div>
                    <div class="jia" onclick="smallNumChange(parseInt($('#smallNum').val())+1)" style="margin-top: -1px;">+</div>
                    <div class="clearfix"></div>
                </div>
                <div class="clearfix"></div>
            </div>
            <div class="clearfix"></div>
            <!--单价-->
            <div class="input-group" style="border: 0;margin-top: 8px;">
                <div class="input-group-1" style="border: 1px solid #d3d3d3;">
                    <div class="shuliang"><span>单价</span></div>
                    <input id="bigPrice" onchange="setPrice($(this).val(),'bigPrice')" type="text" style="width: 118px;padding: 2px 0;">
                    <div class="xiang">/<span class="bigUnit"></span></div>
                    <div class="clearfix"></div>
                </div>
                <div class="input-group-1" style="float: right;width: 40%;padding: 0.2% 0; border: 1px solid #d3d3d3;">
                    <input id="zongPrice" onchange="setPrice($(this).val(),'zongPrice')" type="text" style="width: 102px;padding: 2px 0;">
                    <div class="xiang" style="padding:2.9px 0;margin-top: -1px;">/<span class="zongUnit"></span></div>
                </div>
                <div class="clearfix"></div>
                <div class="input-group-1 smallEle" style="float: right;width: 40%;padding: 0.2% 0; border: 1px solid #d3d3d3;margin-top: 8px;">
                    <input id="smallPrice" onchange="setPrice($(this).val(),'smallPrice')" type="text" style="width: 102px;padding: 2px 0;">
                    <div class="xiang" style="padding:2.9px 0;margin-top: -1px;">/<span class="smallUnit"></span></div>
                </div>
                <div class="clearfix"></div>
            </div>
            <!--金额-->
            <div class="input-group" style="margin-top: 2%;">
                <div class="input-group-addon"><span>金额</span></div>
                <input id="sumPrice" type="text" class="shuru" style="padding: 2px 0;width: 285px;">
                <div class="clearfix"></div>
            </div>
            <!--备注-->
            <div class="input-group" style="margin-top: 2%;">
                <div class="input-group-addon"><span>备注</span></div>
                <input id="orderRemark" type="text" class="shuru" style="padding: 2px 0;width: 285px;">
                <div class="clearfix"></div>
            </div>
            <!--添加按钮-->
            <div style="width: 100%;margin: 0 auto;text-align: center;padding:4% 0">
                <input type="button" style="background-color: #499B5A;color: #fff;border-radius: 4px;font-size: 16px;padding: 2% 8%;" onclick="addGoods($('#cdId').val(),$('#goodsId').val(),$('#orderNum').val(),
                ($('#specName').text().split('*').length>2)?$('#smallPrice').val():$('#zongPrice').val(),$('#orderRemark').val())" value="添  加">
            </div>
        </div>
    </div>
    <div class="box4">
        <div class="bt" style="height: 20px">
            <ul class="nav nav-tabs" style="height: 20px">
                <li style="width: 80px;float: left;padding-left: 5px;border: 0px" class="active">
                    <span class="badge" onclick="changePayTable('spxxDiv','skxxDiv')">商品明细</span>
                </li>
                <c:if test="${not empty review && cRkckddinfo.issp eq '0'}">
                <li style="width: 80px;float: left;padding-left: 5px;border: 0px">
                    <span class="badge" onclick="changePayTable('skxxDiv','spxxDiv')">付款信息</span>
                </li>
                </c:if>
            </ul>
            <div class="ha">
                <span style="color: blue;">帮助</span>
            </div>
        </div>
        <div id="spxxDiv" class="bb" style="height: 430px;<c:if test="${not empty review}">display: none</c:if>">
            <table class="list" cellspacing="0" cellpadding="0" style="border-bottom: 1px solid #d3d3d3;">
                <thead class="list_bt" style="border-bottom: 1px solid;">
                <td colspan="2" style="width: 90px;">商品</td>
                <td>数量</td>
                <td>单价(元)</td>
                <td>金额(元)</td>
                <td style="color: #678AF9" onclick="deleteAllGoods()">清空</td>
                </thead>
                <%--添加商品列表--%>
            </table>
            <div style="width: 100%;">
                <div class="xiaozi" style="float: left;"><%--大： 1  中： 1  小： 1--%></div>
                <div class="xiaozi" style="text-align: right;float: right;" id="totalInfo">共 0 条，0元</div>
                <div class="clearfix"></div>
            </div>
            <div style="width: 100%;margin: 0 auto;text-align: center;padding:4% 0;position: absolute;bottom: 0;">
                <input type="hidden" id="goodsData" value='${goodsJSON}'>
                <form id="saveForm" action="../rkOrderSave" method="post" onsubmit="return checkFormInfo();">
                    <input type="hidden" name="review" value="${review}">
                    <input type="hidden" name="pageName" value="rkrdOrder">
                    <input type="hidden" id="id" name="id" value="${cRkckddinfo.id}">
                    <input type="hidden" id="supplierlist" name="supplier.id">
                    <input type="hidden" id="houselist" name="cHouse.id">
                    <input type="hidden" id="bzlist" name="remarks">
                    <input type="hidden" id="jsonData" name="jsonData" value='${json}'>
                    <input type="hidden" name="lx" value="0">
                    <input type="hidden" name="state" value="1">
                    <input type="submit" style="background-color: #f1ad4e;color: #fff;border-radius: 4px;font-size: 16px;padding: 2% 8%;" value="提  交">
                </form>
            </div>
        </div>
        <c:if test="${not empty review && cRkckddinfo.issp eq '0'}">
        <div id="skxxDiv" class="" style="width: 100%;display: block">
            <div class="panel panel-default">
                <div id="skxxTabContent" style="padding: 10px; height: 517px; overflow: auto;">
                    <table style="width: 100%">
                        <tbody>
                        <tr>
                            <td style="width: 50%;">
                                <div style="padding: 2px 0px">
                                    <span style="width: 80px;">付款账户 :</span>
                                    <select id="dropFKZH" style="border: 1px solid #ccc; width: 120px; height: 24px;" class="ng-pristine ng-untouched ng-valid">
                                        <c:forEach items="${accountList}" var="ac">
                                            <option value="${ac.id}" label="${ac.name}">${ac.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </td>
                            <td class="lk-pt10">总&nbsp;&nbsp;金&nbsp;&nbsp;额 :
                                <span class="ng-scope">
                                    <span style="color: #E55548" class="ng-binding">${total}</span>
                                </span>&nbsp;元
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div style="padding: 2px 0px">
                                    <span style="width: 80px;">来往账户 :</span>
                                    <input id="fkAccount" style="height: 24px; width: 60px;border: 1px" value="${payment.travelAccount}">
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>供应商余额:
                                <span style="color: #E55548" class="ng-binding">0</span>&nbsp;元
                            </td>
                            <td>
                                <div style="padding: 2px 0px">
                                    预&nbsp; 付&nbsp; 款:
                                    <input class="lk-bc lk-tar ng-pristine ng-untouched ng-valid" style="height: 24px; width: 60px;" disabled="disabled" value="${(not empty payment)?payment.je:0}">
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td style="padding: 2px 0px;">应付款金额：
                                <span class="ng-binding ng-scope">${total-((not empty payment)?payment.je:0)}</span>
                            </td>
                            <td>
                                <div style="padding: 2px 0px">
                                    实付金额 :
                                    <span class="ng-scope">
                                            <input id="sfje" class="lk-bc lk-tar ng-pristine ng-untouched ng-valid" style="height: 24px; width: 60px;" value="${total-((not empty payment)?payment.je:0)}">
                                    </span>
                                </div>
                                <div style="padding: 2px 0px">
                                    优惠金额 :
                                    <span class="ng-scope">
                                            <input class="lk-bc lk-tar ng-pristine ng-untouched ng-valid" style="height: 24px; width: 60px;" value="${cRkckddinfo.htje-cRkckddinfo.sjje}">
                                    </span>
                                </div>
                            </td>
                        </tr>
                        </tbody></table>
                    <div id="submitAndAudit" class="input-group " style="width: 320px; margin: 10px auto; text-align: center; padding-bottom: 4px">
                        <div class="input-group " style="width: 310px;">
                            <div class="input-group-btn">
                                <a id="aSubmitAndAudit" class="btn btn-warning lk-w100" href="javascript:void(0)" onclick="Auditing('${cRkckddinfo.id}')">提交并审核</a>
                                <a id="aAfterSubmitAndAudit" class="btn btn-warning lk-w100" href="javascript:void(0)" style="display: none;">提交中...</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        </c:if>
    </div>
    <div class="box5">
        <div class="box5_bt">
            <div class="jiben" style="text-align: center;">
                <span style="font-size: 22px; font-weight: bold; letter-spacing: 4px;">基本信息</span>
            </div>
            <div class="input_g">
                <div class="width"><span style="color: red;">*</span> 入库出库</div>
                <div class="shu">
                    <select class="shu"  id="house">
                        <option value="" />请选择</option>
                        <c:forEach items="${houseList}" var="house">
                            <option value="${house.id}" <c:if test="${cRkckddinfo.cHouse.id eq house.id}">selected="selected"</c:if>>${house.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="tu2"><img src="${ctxStatic}/images/shanchu.png" onclick="$('#house').val('')"> </div>
                <p class="clearfix"></p>
            </div>
            <div class="input_g">
                <div class="width"><span style="color: red;">*</span> 供  应  商</div>
                <div class="shu">
                    <select class="shu" id="supplier" >
                        <option value=""/>请选择</option>
                        <c:forEach items="${supplierList}" var="supplier">
                            <option value="${supplier.id}" <c:if test="${cRkckddinfo.supplier.id eq supplier.id}">selected="selected"</c:if>>${supplier.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="tu2"><img src="${ctxStatic}/images/shanchu.png" onclick="$('#supplier').val('')"> </div>
                <p class="clearfix"></p>
            </div>
            <div class="input_g">
                <div class="width">备注</div>
                <div class="shu"><input id="bz" type="text"  style="width: 426px;padding: 11px;"></div>
                <div class="tu2"><img src="${ctxStatic}/images/shanchu.png" onclick="$('#bz').val('')"> </div>
                <p class="clearfix"></p>
            </div>
            <div class="anniu">
                <input type="button" onclick="jblist()"  value="确  定">
            </div>
            <div class="clearfix"></div>
        </div>
        <div tabindex="0" class="xx"></div>
    </div>
</div>
</body>
</html>
