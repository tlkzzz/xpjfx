<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>仓库管理</title>
    <style>
        ul{
            margin: 0;
            padding: 0;
            float: left;
        }
        li{
            list-style: none;
            border-bottom: 1px solid #d3d3d3;
            border-right: 1px solid #d3d3d3;
            margin-top: -1px;
            height: 30px;
        }
        p{
            margin: 0;
        }
        img{
            display: block;
        }
        input{
            border: 0;
            padding: 0;
            margin: 0;
        }
        table,tr,td{
            border: 0;
            margin: 0;
            padding: 0;
        }
        .box1,.box2,.box3,.box4{
            height: 582px;
        }
        .clearfix{
            clear: both;
        }
        .box{
            width: 1290px;
        }
        .box1,.box2{
            width: 230px;
            border: 1px solid #d3d3d3;
            border-radius: 4px;
            margin-right: 10px;
            float: left;
        }
        .box2{
            width: 230px;
        }
        .bb{
            padding: 0px;
            height: 543px;
            overflow: auto;
        }
        ::-webkit-scrollbar{
            width: 3px;
        }
        ::-webkit-scrollbar-thumb {
            background-color: rgba(0, 0, 0, 0.2);
        }
        .fen1,.fen2{
            width: 115px;
            border-left: 1px solid #d3d3d3;
            box-sizing: border-box;
            margin-left: -1px;
        }
        .fen2{
            width: calc(50% + 2px);
            margin-left: 0;
        }
        .fen2 li{
            border-right: 0px;
            border-right: 0px;
        }
        .mingcheng{
            font-size: 14px;
            padding: 10px; 5px;
            border-bottom: 1px solid #d3d3d3;
            width: 210px;
            margin-top: -1px;
        }
        .bold{
            font-size: 12px;
            font-weight: bold;
            padding: 4px 8px;
        }
        .xi{
            font-size: 12px;
            padding: 4px 8px;

        }
        .xixi{
            line-height: 30px;
        }
        .bg{
            background-color: #678AF9;
            color: #fff;
        }
        .span{
            float: right;
            margin-right: 4px;
            font-size: 14px;
            color: #B3B3B3;
        }
        .n1,.n2{
            border-bottom: 1px solid #d3d3d3;
        }
        .n1{
            padding: 4px 4px;
        }
        .n1 p{
            float: left;
            font-size: 14px;
            padding: 6px 4px;
        }
        .shuzi1{
            float: left;
            padding-left: 4px;
        }
        .shuzi2{
            float: right;
            padding-right: 4px;
            padding-top: 4px;
        }
        .shuzi3{
            float: left;
            font-size: 12px;
            padding-left: 4px;
            margin-top: -4px;
            color: #B3B3B3;
        }
        .shuzi4{
            float: right;
            font-size: 12px;
            padding-right: 4px;
            margin-top: -4px;
            color: #B3B3B3;
        }
        .shuzi5{
            float: left;
            font-size: 12px;
            padding-left: 4px;
            color: #B3B3B3;
            padding-bottom: 4px;
        }
        .box3{
            width: 340px;
            float: left;
            border: 1px solid #d3d3d3;
            border-radius: 4px;
        }
        .panel-heading{
            width: 340px;
        }
        .biaoti{
            width: 340px;
            font-size: 14px;
            padding: 3.2px 4px;
            border-bottom: 1px solid #d3d3d3;
        }
        .biaoti td{
            width: 170px;
        }
        .panel-body{
            margin: 0 auto;
            width: 324px;
            font-size: 12px;
            padding: 2px 2px;
            border-bottom: 1px solid #d3d3d3;
        }
        .panel-body td{
            text-align: left;
            padding: 2px 2px;
        }
        .shangpin{
            margin: 8px auto;
            width: 324px;
        }
        .input-group{
            border: 1px solid #d3d3d3;
            border-radius: 4px;
            width: 324px;
            margin: 0 auto;
        }
        .input-group-addon{
            padding: 2px 2px;
            font-size: 14px;
            text-align: center;
            background-color: #eee;
            float: left;
            border-right: 1px solid #d3d3d3;
            width: 34px;
            border-top-left-radius: 4px;
            border-bottom-left-radius: 4px;
        }
        .input-group-addon2{
            width: 30px;
            padding: 6px 4px;
            font-size: 14px;
            text-align: center;
            background-color: #eee;
            float: right;
            border-left: 1px solid #d3d3d3;
            border-bottom-right-radius: 4px;
            border-top-right-radius: 4px;
        }
        .shuru{
            font-size: 14px;
            width: 236px;
        }
        .lk-tal{
            width: 324px;
            font-size: 12px;
            color: #B3B3B3;
        }
        .left{
            padding: 8px 4px;
            float: left;
        }
        .right{
            padding: 8px 0;
            float: right;
            text-align: right;
        }
        .input-group-1{
            width: 178px;
            border-radius: 4px;
            float: left;
        }
        .shuliang{
            padding: 2px 2px;
            font-size: 14px;
            text-align: center;
            background-color: #eee;
            float: left;
            border-right: 1px solid #d3d3d3;
            border-top-left-radius: 4px;
            border-bottom-left-radius: 4px;
        }
        .xiang{
            float: right;
            padding: 1.8px 2px;
            font-size: 14px;
            text-align: center;
            background-color: #eee;
            border-left: 1px solid #d3d3d3;
            border-bottom-right-radius: 4px;
            border-top-right-radius: 4px;
        }
        .jia{
            float: right;
            padding: 1px 4px;
            border-left: 1px solid #d3d3d3;
        }
        .jian{
            float: left;
            padding: 1px 4px;
            border-right: 1px solid #d3d3d3;
        }
        .shuru1{
            font-size: 14px;
            padding: 4px 4px;
            display: block;
            text-align: right;
            float: left;
        }
        .box4{
            margin-left: 10px;
            width: 450px;
            float: left;
            border: 1px solid #d3d3d3;
            border-radius: 4px;
            position: relative;
        }
        .bt{
            font-size: 14px;
            padding: 1.8% 2%;
            border-bottom: 1px solid #d3d3d3;
        }
        .ha{
            float: right;
            width: 47.25%;
            text-align: right;
        }
        .aa{
            background-color: #888;
            font-size: 10px;
            color: #fff;
            padding: 1% 5%;
            border-radius: 4px;
        }
        .list{
            font-size: 12px;
        }
        .list_bt{
            font-size: 12px;
            text-align: center;
            border: 1px solid;
            line-height: 200%;
        }
        .list_bt td{
            width: 20%;
        }
        .nongjia_bg{
            width: 6%;
        }
        .nongjia{
            background-color: #d9edf7;
            vertical-align: top;
        }
        .nongjia td{
            padding: 2% 0;

        }
        .nongjia_bg span{
            background-color: #f1ad4e;
            padding: 2% 12%;
            color: #fff;
            margin-left: 24%;
        }
        .xiaozi{
            color: #ff6500;
            font-size: 12px;
            padding: 1% 2%;
        }
        .input-group4{
            width: 95%;
            margin: 0 auto;
        }
        .box5{
            position: relative;
        }
        .box5_bt{
            width: 795px;
            background-color: #fff;
            position: absolute;
            top: 50%;
            left: 50%;
            z-index: 1027;
            margin-left: -15%;
            margin-top: 5%;
        }
        .jiben{
            padding: 4%;
            border-bottom: 1px solid #d3d3d3;
        }
        .input_g{
            border: 1px solid #d3d3d3;
            border-radius: 4px;
            width: 556px;
            margin: 8px auto;
        }
        .width{
            text-align: center;
            float: left;
            width: 84px;
            padding: 10px;
            background-color: #eee;
            border-top-left-radius: 4px;
            border-bottom-left-radius: 4px;
            border-right: 1px solid #d3d3d3;
        }
        .shu{
            float: left;
            width: 408px;
        }
        .shu input{
            padding: 11px 0;
            width: 415px;
        }
        .tu2{
            float: right;
            padding: 11px;
            background-color: #eee;
            border-top-right-radius: 4px;
            border-bottom-right-radius: 4px;
            border-left: 1px solid #d3d3d3;
        }
        .xx{
            z-index: 1026;
            position: fixed;
            left: 0px;
            top: 0px;
            width: 100%;
            height: 100%;
            overflow: hidden;
            opacity: 0.5;
            background: rgb(0, 0, 0);
        }
        .anniu{
            text-align: center;
            margin-bottom: 5%;
        }
        .anniu input{
            padding: 2% 4%;
            border-radius: 4px;
            background-color: #5cb85c;
            font-size: 14px;
            color: #fff;
        }
    </style>
    <script src="${ctxStatic}/jquery/jquery-1.8.3.min.js" type="text/javascript"></script>
    <link href="${ctxStatic}/jquery-jbox/2.3/Skins/Bootstrap/jbox.min.css" rel="stylesheet" />
    <script src="${ctxStatic}/jquery-jbox/2.3/jquery.jBox-2.3.min.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            getGclass('${gClass[0].id}',$(".box1 .fen1 .xixi:first"));
        });
        function getGoods(id,ele) {
            if(id=='')return;
            if(ele!=null){
                $(".box1 .fen2 .xixi").removeClass("bg");
                ele.addClass("bg");
            }
            $.ajax({
                url:"${ctx}/ck/cGoods/findGoodsList",
                dataType:"json",
                type:"post",
                data:{"gclass.id":id},
                success:function (data) {
                    $(".box2 .bb").empty();
                    if(data.length>0){
                        $("#goodsList").val(JSON.stringify(data));
                        $("#goodsNum").text("共 "+data.length+" 条");
                        for(var i=0;i<data.length;i++){
                            var specName = data[i].spec.name;//规格
                            var sumPrice = eval(specName)*data[i].sj;//单个最大规格总价格
                            var smallName = data[i].zong.name;//最小单位
                            if(specName.split("*").length>2)smallName = data[i].small.name;
                            var text = '<div class="n2" onclick="setGoods(\''+data[i].id+'\',$(this))"><p class="bold shuzi1">'+data[i].name+'</p>'+
                                '<p class="xi shuzi2">'+sumPrice+'/'+data[i].big.name+'</p><div class="clearfix"></div>'+
                                '<p class="shuzi3">'+data[i].spec.name + smallName+'</p><p class="shuzi4">'+data[i].sj+'/'+smallName+'</p>'+
                                '<div class="clearfix"></div><p class="shuzi5">0</p>'+
                                '<div class="clearfix"></div></div>';
                            $(".box2 .bb").append(text);
                        }
                    }else {

                        $("#goodsNum").text("共 0 条");
                        $(".box2 .bb").append('<div class="n2"><p class="bold shuzi1">商品为空</p><div class="clearfix"></div></div>');
                    }
                }
            });
        }
        function getGclass(id,ele) {
            if(id=='')return;
            if(ele!=null){
                $(".box1 .fen1 .xixi").removeClass("bg");
                ele.addClass("bg");
            }
            $.ajax({
                url:"${ctx}/ck/cGclass/listByParent",
                dataType:"json",
                type:"post",
                data:{"parent.id":id},
                success:function (data) {
                    $(".box1 .fen2").empty();
                    if(data.length>0){
                        $("#gClassNum").text(data.length);
                        for(var i=0;i<data.length;i++){
                            $(".box1 .fen2").append('<li class="xi xixi" onclick="getGoods(\''+data[i].id+'\',$(this))">'+
                                data[i].name+'<span class="span">5</span></li>');
                        }
                        getGoods(data[0].id,$(".box1 .fen2 .xixi:first"));
                    }else {
                        $(".box1 .fen2").append('<li class="xi xixi">分类为空</li>');
                    }
                }
            });
        }
        function setGoods(id,ele) {
            if(id==null||id=='')return;
            var goodsText = $("#goodsList").val();
            if(goodsText==null||goodsText=='')return;
            if(ele!=null){
                $(".box2 .bb .n2").removeClass("bg");
                ele.addClass("bg");
            }
            var goodsList = eval(goodsText);
            for(var i=0;i<goodsList.length;i++){
                if(goodsList[i].id==id){
                    $("#orderGoods").val(JSON.stringify(goodsList[i]));
                    var specName = goodsList[i].spec.name;//规格
                    var specList = specName.split("*");
                    var sj = goodsList[i].sj;//售价
                    $("#goodsId").val(goodsList[i].id);
                    $("#goodsName").val(goodsList[i].name);
                    $("#orderNum").val(eval(goodsList[i].spec.name));
                    $("#specName").text(specName);
                    $("#keYongKC").text(0);//可用库存
                    $("#anQuanKC").text(0);//安全库存
                    $("#bigNum").val(1);
                    $("#zongNum").val(0);
                    $("#smallNum").val(0);
                    $(".bigUnit").text(goodsList[i].big.name);
                    $(".zongUnit").text(goodsList[i].zong.name);
                    $(".smallUnit").text(goodsList[i].small.name);
                    $("#sumPrice").val(eval(specName)*sj);
                    if(specList.length>2){
                        $(".smallEle").css("display","block");
                        $("#bigPrice").val(eval(specName)*sj);
                        $("#zongPrice").val(specList[2]*sj);
                        $("#smallPrice").val(sj);
                    }else {
                        $(".smallEle").css("display","none");
                        $("#bigPrice").val(eval(specName)*sj);
                        $("#zongPrice").val(sj);
                    }
                }
            }
        }
        /*公共js*/
        function bigNumChange(num) {
            if(isNaN(num)||num<0){
                num = 1;
            }
            num = parseInt(num);
            /*不能大于可用库存*/
            $("#bigNum").val(num);
            setNum();
        }
        function zongNumChange(num) {
            if(isNaN(num)||num<0){
                num = 1;
            }
            num = parseInt(num);
            /*不能大于可用库存*/
            $("#zongNum").val(num);
            setNum();
        }
        function smallNumChange(num) {
            if(isNaN(num)||num<=0){
                num = 1;
            }
            num = parseInt(num);
            /*不能大于可用库存*/
            $("#smallNum").val(num);
            setNum();
        }
        function setNum() {
            var specName = $("#specName").text();
            var specList = specName.split("*");
            var sumNum = eval(specName)*parseInt($("#bigNum").val());
            if(specList.length>2){
                var sum = parseInt($("#bigNum").val())*parseFloat($("#bigPrice").val())+
                    parseInt($("#zongNum").val())*parseFloat($("#zongPrice").val())+
                    parseInt($("#smallNum").val())*parseFloat($("#smallPrice").val());
                $("#sumPrice").val(sum.toFixed(4))
                sumNum += specList[2]*parseInt($("#zongNum").val())+parseInt($("#smallNum").val());
            }else{
                var sum = parseInt($("#bigNum").val())*parseFloat($("#bigPrice").val())+
                    parseInt($("#zongNum").val())*parseFloat($("#zongPrice").val());
                $("#sumPrice").val(sum.toFixed(4));
                sumNum += parseInt($("#zongNum").val());
            }
            $("#orderNum").val(sumNum);
        }
        function setPrice(price,id) {
            if(isNaN(price)||price<0){
                price = 0;
            }
            price = parseFloat(price);
            var sj = $("#smallPrice").val();
            var specName = $("#specName").text();
            var specList = specName.split("*");
            if(id=='bigPrice'){
                sj = (price/eval(specName)).toFixed(4);
            }else if(id=='zongPrice'&&specList.length>2){
                sj = (price/specList[2]).toFixed(4);
            }else{
                sj = price;
            }
            $("#bigPrice").val(eval(specName)*sj);
            if(specList.length>2) {
                $("#zongPrice").val(specList[2] * sj);
                $("#smallPrice").val(sj);
            }else{
                $("#zongPrice").val(sj);
            }
            setNum();
        }
        function clearDate() {//初始化商品添加页面
            $(".box .box3 .shangpin input:hidden").val("");
            $(".box .box3 .shangpin input:text").val("");
            $("#specName").empty();
            $("#keYongKC").empty();
            $("#anQuanKC").empty();
            $(".bigUnit").empty();
            $(".zongUnit").empty();
            $(".smallUnit").empty();
            $(".smallEle").css("display","block");
        }
        function addGoods(id,num,price,remark) {//添加商品到订单列表
            var orderGoods = $("#orderGoods").val();
            if(id==''||num<=0||price<=0||orderGoods==null){
                alert("信息不完整!");
                return;
            }
            var goods = eval("("+orderGoods+")");
            setJsonGoods(goods);//将goods添加到JSON数据中
            setPostJson(id,num,price,remark);
            showGoodsList();
        }
        function showGoodsList() {//通过隐藏域订单信息展示订单列表
            $(".list .nongjia").remove();
            clearDate();//保存后清除填写信息
            var jsonData = $("#jsonData").val();
            jsonData = eval("("+jsonData+")");
            for(var i=0;i<jsonData.length;i++){
                var json = jsonData[i];
                showGoods(json.id,json.num,json.price,json.remark,getJsonGoods(json.id));
            }
        }
        function showGoods(id,num,price,remark,goods) {//显示单个订单商品
            if(id==''||num<=0||price<=0||goods==null){
                alert("信息不完整");
                return;
            }
            var specName = goods.spec.name;
            var specList = specName.split("*");
            var specNum = parseInt(eval(specName));
            var big = parseInt(num/specNum);
            var zong = (specList.length>2)?parseInt((num-(big*specNum))/parseInt(specList[2])):num-(big*specNum);
            var small = parseInt(num-(big*specNum)-(zong*parseInt(specList[2])));
            var smallUnit = (specList.length>2)?goods.small.name:goods.zong.name;
            var smallHtml = (specList.length>2)?'<div style="margin-bottom: 4%;"><input type="text" readonly="true" value="'+small+'" style="width: 30%;border: 1px solid #d3d3d3;margin-right: 8%;text-align: right;"><span>'+goods.small.name+'</span></div>':'';
            var text = '<tr class="nongjia"><td class="nongjia_bg"><span>'+($(".list .nongjia").length+1)+'</span></td><td style="width: 20%"><p style="font-weight: bold;">'+ goods.name+
                '</p><p style="color: #B3B3B3;">'+specName+'</p><p style="color: #B3B3B3;">'+goods.tm +'</p></td><td style="padding-left: 6%;">'+
                '<div style="margin-bottom: 4%;"><input type="text" readonly="true" value="'+big+'" style="width: 30%;border: 1px solid #d3d3d3;margin-right: 8%;text-align: right;"><span>'+goods.big.name+'</span></div>'+
                '<div style="margin-bottom: 4%;"><input type="text" readonly="true" value="'+zong+'" style="width: 30%;border: 1px solid #d3d3d3;margin-right: 8%;text-align: right;"><span>'+goods.zong.name+'</span></div>'+smallHtml+
                '</td><td style="padding-left: 5%;"><p style="margin-bottom: 4%;">'+specNum*price+'/'+goods.big.name+'</p><p>'+price+'/'+smallUnit+
                '</p></td><td style="padding-left: 6.5%;"><p>'+num*price+'</p></td><td><img src="${ctxStatic}/images/mxcpbianji.png" style="float: left;margin: 0 10px;"><img src="${ctxStatic}/images/shanchu.png"></td></tr>'
            $(".list").append(text);
        }
        function setJsonGoods(goods) {
            var goodsData = $("#goodsData").val();
            if(goods==''||goods==null)return;
            if(goodsData==''){
                goodsData = [];
            }else{
                goodsData = eval("("+goodsData+")");
            }
            var flag = true;
            for(var i=0;i<goodsData.length;i++){
                if(goods.id==goodsData[i].id){flag = false;}
            }
            if(flag){
                goodsData.push(goods);
                $("#goodsData").val(JSON.stringify(goodsData));
            }
        }
        function setPostJson(id,num,price,remark) {
            if(id==''||num<=0||price<=0){
                alert("信息不完整");
                return;
            }
            var jsonData = $("#jsonData").val();
            var json = {id:id,num:num,price:price,remark:remark};
            if(jsonData==''){
                jsonData = [];
                jsonData.push(json);
            }else{
                var flag = true;
                jsonData = eval("("+jsonData+")");
                for(var i=0;i<jsonData.length;i++){
                    if(jsonData[i].id==id){
                        jsonData[i].num = parseInt(jsonData[i].num)+parseInt(num);
                        jsonData[i].price = price;
                        jsonData[i].remark = remark;
                        flag = false;
                    }
                }
                if(flag)jsonData.push(json);
            }
            $("#jsonData").val(JSON.stringify(jsonData));
        }
        function getJsonGoods(id) {
            if(id=='')return null;
            var goodsData = $("#goodsData").val();
            if(goodsData=='')return null;
            goodsData = eval("("+goodsData+")");
            for(var i=0;i<goodsData.length;i++){
                if(goodsData[i].id==id){
                    return goodsData[i];
                }
            }
            return null;
        }
    </script>
</head>
<body>

<div class="box">
    <div style="height: 40px;">
        <ul class="nav nav-tabs">
            <li class="active" style="border: 0;border-top: 1px solid #d3d3d3;border-left: 1px solid #d3d3d3;border-right: 1px solid #d3d3d3;padding: 4px 4px 0;border-top-left-radius: 4px;border-top-right-radius: 4px;"><a href="" style="text-decoration: none;out-line: none;color: #000;">出库录单</a></li>
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
                    <td style="text-align: right;font-size: 12px;color:#678AF9;">编辑</td>
                </tr>
            </table>
            <div style="padding: 0px;
		height: 60px;
		overflow: auto;">
                <table class="panel-body">
                    <tbody>
                    <tr>
                        <td>进货仓库：</td>
                        <td>北区(李海生)仓库</td>
                        <td>供 应 商：</td>
                        <td>abd榴莲饼厂</td>
                    </tr>
                    <tr>
                        <td>备  注：</td>
                        <td></td>
                    </tr>
                    <tr>
                        <td>备  注：</td>
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
                <input id="goodsId" type="hidden"><input id="orderNum" type="hidden"><input id="orderGoods" type="hidden"><%--商品ID/订单数量/商品信息--%>
                <span class="input-group-addon2"><img src="${ctxStatic}/images/shanchu.png" style="margin: 0 auto;"></span>
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
                <input type="button" style="background-color: #499B5A;color: #fff;border-radius: 4px;font-size: 16px;padding: 2% 8%;" onclick="addGoods($('#goodsId').val(),$('#orderNum').val(),
                ($('#specName').val().split('*').length>2)?$('#smallPrice').val():$('#zongPrice').val(),$('#orderRemark').val())" value="添  加">
            </div>
        </div>
    </div>
    <div class="box4">
        <div class="bt">
            <span>商品明细</span>
            <div class="ha">
                <span style="color: blue;">帮助</span>
            </div>
        </div>
        <div class="bb" style="height: 430px;">
            <table class="list" cellspacing="0" cellpadding="0" style="border-bottom: 1px solid #d3d3d3;">
                <thead class="list_bt" style="border-bottom: 1px solid;">
                    <td colspan="2" style="width: 90px;">商品</td>
                    <td>数量</td>
                    <td>单价(元)</td>
                    <td>金额(元)</td>
                    <td style="color: #678AF9">清空</td>
                </thead>
                <%--添加商品列表--%>
            </table>
            <div style="width: 100%;">
                <div class="xiaozi" style="float: left;"><%--大： 1  中： 1  小： 1--%></div>
                <div class="xiaozi" style="text-align: right;float: right;">共 1 条，118.30元</div>
                <div class="clearfix"></div>
            </div>
        </div>
        <div style="width: 100%;margin: 0 auto;text-align: center;padding:4% 0;position: absolute;bottom: 0;">
            <input type="hidden" id="goodsData">
            <form id="saveForm" action="">
                <input type="hidden" id="jsonData" name="jsonData">
                <input type="button" style="background-color: #f1ad4e;color: #fff;border-radius: 4px;font-size: 16px;padding: 2% 8%;" value="提  交">
            </form>
        </div>
    </div>
    <%--<div class="box5">
        <div class="box5_bt">
            <div class="jiben" style="text-align: center;">
                <span style="font-size: 22px; font-weight: bold; letter-spacing: 4px;">基本信息</span>
            </div>
            <div class="input_g">
                <div class="width"><span style="color: red;">*</span> 进货仓库</div>
                <div class="shu"><input type="text"  style="width: 412px"></div>
                <div class="tu2"><img src="${ctxStatic}/images/shanchu.png"></div>
                <p class="clearfix"></p>
            </div>
            <div class="input_g">
                <div class="width"><span style="color: red;">*</span> 供  应  商</div>
                <div class="shu"><input type="text" style="width: 412px"></div>
                <div class="tu2"><img src="${ctxStatic}/images/shanchu.png"></div>
                <p class="clearfix"></p>
            </div>
            <div class="input_g">
                <div class="width">备注</div>
                <div class="shu"><input type="text" style="width: 450px"></div>
                <p class="clearfix"></p>
            </div>
            <div class="anniu">
                <input type="button" value="确  定">
            </div>
        </div>
        <div tabindex="0" class="xx"></div>
    </div>--%>
</div>
</body>
</html>
