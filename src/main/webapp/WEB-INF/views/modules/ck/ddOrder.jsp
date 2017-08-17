<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>订单申报</title>
    <script src="${ctxStatic}/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
    <link href="${ctxStatic}/My97DatePicker/skin/WdatePicker.css" type="text/css" rel="stylesheet"/>
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
            if($("#cStore").val()==""){

                message("请选择客户")
                return;
            }
            if($("#createBy").val()==""){
                message("请选择业务员")
                return;
            }
            if($("#house").val()==""){
                message("请选择仓库")
                return;
            }
            if($("#createDate").val()==""){
                message("时间不能为空")
                return;
            }
//            if($("#cCar").val()==""){
//                message("请选择仓库车辆")
//                return;
//            }
            var cStore = $("#cStore option:selected").text();
            var createBy = $("#createBy option:selected").text();
            var house = $("#house option:selected").text();
            var createDate = $("#createDate").val();
//            var cCar = $("#cCar option:selected").text();
            var bz = $("#bz").val();
            if(cStore!="" || house!="" ||createDate!=""  || name!="") {
                $("#kh").text(cStore);
                $("#ywy").text(createBy);
                $("#fhck").text(house);
                $("#date").text(createDate);
//                $("#pscl").text(cCar);
                $("#rom").text(bz);

                $("#cStorelist").val($("#cStore").val());
                $("#houselist").val($("#house").val());
                $("#createDatelist").val($("#createDate").val());
                $("#ywynamelist").val($("#createBy").val());
//                $("#cCarlist").val($("#cCar").val());
                $("#bzlist").val($("#bz").val());
                $(".box5").css("display","none");
            }else {
                message("信息填写不完整");
                return;
            }
        }
        function checkFormInfo() {
            if($("#cStore").val()==""){message("客户未选择");return false;}
            if($("#createBy").val()==""){message("业务员未选择");return false;}
            if($("#house").val()==""){message("仓库未选择");return false;}
            if($("#createDate").val()==""){message("时间不能为空");return false;}
//            if($("#cCar").val()==""){message("仓库车辆未选择");return false;}
            var jsonData = $("#jsonData").val();
            if(jsonData==""||eval("("+jsonData+")").length<=0){message("请填写单据");return false;}
            return true;
        }

        function onCspmx() {
            document.getElementById("spmx").style.display="block";
            document.getElementById("skxx").style.display="none";
        }
        function onCfkxx() {
            document.getElementById("spmx").style.display="none";
            document.getElementById("skxx").style.display="block";

        }

        function zhanghu() {
            $.ajax({
                type: "POST",
                url: "${adminPath}/a/ck/cRkckddinfo/zhanghuAdd",
                success: function(data){
                    for(var i=0;i<data.length;i++){
//                   alert(data[0].name);
                        var s=document.getElementById("level3");
                        s.add(new Option(data[i].name,data[i].id));
                    }
                }
            });
        }
        window.onload=zhanghu;
        function fktijiao() {
            var lwzh=document.getElementById("lwzh").value;
            alert(lwzh);
            var level3=document.getElementById("level3").value;
            alert(level3);
            var skfs=document.getElementById("skfs").value;
            alert(skfs);
            window.location="${adminPath}/a/ck/cRkckddinfo/xsckSh?zddId=1f418a8910a14b8ab1d0d86635981e7a"+"&lwzh="+lwzh+"&skzh="+level3+"&skfs="+skfs;
        }
    </script>
</head>
<body>
<input id="ctx" type="hidden" value="${ctx}">
<input id="ctxStatic" type="hidden" value="${ctxStatic}">
<div class="box">
    <div style="height: 40px;">
        <ul class="nav nav-tabs">
            <li class="active" style="border: 0;border-top: 1px solid #d3d3d3;border-left: 1px solid #d3d3d3;border-right: 1px solid #d3d3d3;padding: 4px 4px 0;border-top-left-radius: 4px;border-top-right-radius: 4px;"><a href="" style="text-decoration: none;out-line: none;color: #000;">订单申报</a></li>
        </ul>
    </div>
    <div class="box1">
        <div class="mingcheng">商品类别</div>
        <div class="bb" style="float: left;">
            <ul class="fen1">
                <c:forEach items="${gClass}" var="gc">
                    <li class="xi xixi" onclick="getGclass('${gc.id}',$(this));">${gc.name}<span class="span">4</span></li>
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
                        <td>客户：</td>
                        <td id="kh">${cRkckddinfo.store.name}</td>
                        <td>业务员：${cRkckddinfo.createBy}</td>
                        <td id="ywy"></td>
                    </tr>
                    <tr>
                        <td>发货仓库：</td>
                        <td id="fhck">${cRkckddinfo.cHouse.name}</td>
                        <td>发货日期:</td>
                        <td id="date">	<fmt:formatDate value="${cRkckddinfo.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    </tr>
                    <tr>
                        <%--<td>配送车辆：</td>--%>
                        <%--<td id="pscl">${cRkckddinfo.cHouse.name}</td>--%>
                        <td>备  注：</td>
                        <td id="rom">${cRkckddinfo.remarks}</td>
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
        <div class="bt">
            <a onclick="onCspmx();" style="border: 2px"><span>商品明细</span></a>
            <a onclick="onCfkxx();" style="border: 2px"><span>付款信息</span></a>
            <div class="ha">
                <span style="color: blue;">帮助</span>
            </div>
        </div>
        <div id="spmx" class="bb" style="height: 430px;">
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
        </div>
        <%--收款信息开始--%>

        <div id="skxx" class="bb" style="height: 430px;" hidden>
            <table style="width: 100%">
                <tbody><!-- ngIf: jbxx.cklx !=309 --><tr ng-if="jbxx.cklx !=309" class="ng-scope">
                    <td style="">
                        <div class="input_g">
                            <div class="width">来往账户</div>
                            <div class="shu"><input id="lwzh" type="text"  style="width: 426px;padding: 11px;" value="${cRkckddinfo.remarks}"></div>
                            <div class="tu2"><img src="${ctxStatic}/images/shanchu.png" onclick="$('#bz').val('')"> </div>
                            <p class="clearfix"></p>
                        </div>
                    </td>
                </tbody>
                <tbody>
                <td style="">
                    <div class="input-group" style="width: 175px;">
                        <span class="input-group-addon lk-p5"><span style="">收款账户</span></span>
                        <select name="level3" id="level3" style="border: 1px solid #ccc; height: 30px; width: 100px;">
                            <option value="">请选择</option>
                        </select>
                    </div>
                </td>
                </tbody>
                <tbody>
                <td style="margin-left: auto">
                    <div class="input-group" style="width: 175px;">
                                <span class="input-group-addon lk-p5" style="padding-left: 5px; padding-right: 5px;">
                                    <span style="">收款方式</span>
                                </span>
                        <select  id="skfs" ng-model="configInfo.xsskfs" ng-change="changeKHSKFS()" ng-options="item.id as item.mc for item in khskfsList" style="border: 1px solid #ccc; height: 30px; width: 100px;" class="ng-pristine ng-valid ng-touched">
                            <option value="" selected="selected">请选择</option>
                            <option value="1" >银行卡</option>
                            <option value="2" >现金</option>
                            <option value="3" >其他</option>
                        </select>
                    </div>
                </td>
                </tbody>
            </table>
        </div>
        <%--收款信息结束--%>
        <div style="width: 100%;margin: 0 auto;text-align: center;padding:4% 0;position: absolute;bottom: 0;">
            <input type="hidden" id="goodsData" value='${goodsJSON}'>
            <form id="saveForm" action="../rkOrderSave" method="post" onsubmit="return checkFormInfo();">
                <input type="hidden" name="pageName" value="ddOrder">
                <input type="hidden" id="id" name="id" value="${cRkckddinfo.id}">
                <input type="hidden" id="jsonData" name="jsonData" value='${json}'>
                <input type="hidden" id="cStorelist" name="store.id">
                <input type="hidden" id="houselist" name="cHouse.id">
                <input type="hidden" id="createDatelist" name="createDate">
                <input type="hidden" id="ywynamelist" name="createBy">
                <%--<input type="hidden" id="cCarlist" name="cCar.id">--%>
                <input type="hidden" id="bzlist" name="remarks">
                <input type="hidden" name="lx" value="1">
                <input type="hidden" name="state" value="9">
                <input type="button" onclick="fktijiao();" style="background-color: #f1ad4e;color: #fff;border-radius: 4px;font-size: 16px;padding: 2% 8%;" value="提  交">
            </form>
        </div>
    </div>
    <div class="box5">
        <div class="box5_bt">
            <div class="jiben" style="text-align: center;">
                <span style="font-size: 22px; font-weight: bold; letter-spacing: 4px;">基本信息</span>
            </div>
            <div class="input_g">
                <div class="width"><span style="color: red;">*</span> 客户</div>
                <div class="shu">
                    <select class="shu" id="cStore">
                        <option value="">请选择</option>
                        <c:forEach items="${cStorelist}" var="cStore">
                            <option value="${cStore.id}" <c:if test="${cRkckddinfo.cStore.id eq cStore.id}">selected="selected"</c:if>>${cStore.name}</option>
                            <%--<option value="${cStore.id}">${cStore.name}</option>--%>
                        </c:forEach>
                    </select>
                </div>
                <div class="tu2"><img src="${ctxStatic}/images/shanchu.png" onclick="$('#cStore').val('')"> </div>
                <p class="clearfix"></p>
            </div>
            <div class="input_g">
                <div class="width"><span style="color: red;">*</span> 业务员</div>
                <div class="shu">
                    <select class="shu" id="createBy">
                        <option value="">请选择</option>
                        <c:forEach items="${name}" var="createBy">
                            <option value="${createBy}">${name}</option>
                            <%--<option value="${createBy}" <c:if test="${createBy.id eq createBy.id}">selected="selected"</c:if>>${name}</option>--%>
                        </c:forEach>
                    </select>
                </div>
                <div class="tu2"><img src="${ctxStatic}/images/shanchu.png" onclick="$('#createBy').val('')"> </div>
                <p class="clearfix"></p>
            </div>
            <div class="input_g">
                <div class="width">发货仓库</div>
                <div class="shu">
                    <select class="shu"  id="house">
                        <option value="">请选择</option>
                        <c:forEach items="${houseList}" var="house">
                            <option value="${house.id}" <c:if test="${cRkckddinfo.cHouse.id eq house.id}">selected="selected"</c:if>>${house.name} </option>
                        </c:forEach>
                    </select>
                </div>
                <div class="tu2"><img src="${ctxStatic}/images/shanchu.png" onclick="$('#house').val('')"> </div>
                <p class="clearfix"></p>
            </div>
            <div class="input_g">
                <div class="width">发货日期</div>
                <div class="shu">
                    <input name="createDate" id="createDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
                           value="${createDate}"
                           onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
                </div>
                <div class="tu2"><img src="${ctxStatic}/images/shanchu.png" onclick="$('#createDate').val('')"> </div>
                <p class="clearfix"></p>
            </div>
            <%--<div class="input_g">--%>
                <%--<div class="width">配送车辆</div>--%>
                <%--<div class="shu">--%>
                    <%--<select class="shu" id="cCar">--%>
                        <%--<option value="">请选择</option>--%>
                        <%--<c:forEach items="${cCar}" var="cCar">--%>
                            <%--<option value="${cCar.id}">${cCar.name}</option>--%>
                        <%--</c:forEach>--%>
                    <%--</select>--%>
                <%--</div>--%>
                <%--<div class="tu2"><img src="${ctxStatic}/images/shanchu.png" onclick="$('#cCar').val('')"> </div>--%>
                <%--<p class="clearfix"></p>--%>
            <%--</div>--%>
            <div class="input_g">
                <div class="width">备注</div>
                <div class="shu"><input id="bz" type="text"  style="width: 426px;padding: 11px;"></div>
                <div class="tu2"><img src="${ctxStatic}/images/shanchu.png" onclick="$('#bz').val('')"> </div>
                <p class="clearfix"></p>
            </div>
            <div class="anniu">
                <input type="button"  onclick="jblist()"value="确  定">
            </div>
            <div class="clearfix"></div>
        </div>
        <div tabindex="0" class="xx"></div>
    </div>
</div>
</body>
</html>
