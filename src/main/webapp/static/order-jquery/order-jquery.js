var editGoodsFlag = false;
function getGoods(id,ele) {
    if(id=='')return;
    if(ele!=null){
        $(".box1 .fen2 .xixi").removeClass("bg");
        ele.addClass("bg");
    }
    var ctx = $("#ctx").val();
    $.ajax({
        url:ctx+"/ck/cGoods/findGoodsList",
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
                    var kykc = (data[i].kykc!=null&&data[i].kykc!="")?data[i].kykc:"0";
                    if(specName.split("*").length>2)smallName = data[i].small.name;
                    var text = '<div class="n2" onclick="setGoods(\''+data[i].id+'\',$(this))"><p class="bold shuzi1">'+data[i].name+'</p>'+
                        '<p class="xi shuzi2">'+sumPrice+'/'+data[i].big.name+'</p><div class="clearfix"></div>'+
                        '<p class="shuzi3">'+data[i].spec.name + smallName+'</p><p class="shuzi4">'+data[i].sj+'/'+smallName+'</p>'+
                        '<div class="clearfix"></div><p class="shuzi5">库存：'+kykc+'</p>'+
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
    var ctx = $("#ctx").val();
    $.ajax({
        url:ctx+"/ck/cGclass/listByParent",
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
    editGoodsFlag = false;
    setGoodsInfo("",id,0,0,"",goodsList);
}
function editGoods(cdId,id,num,price,remark) {
    if(id==null||id=='')return;
    var goodsText = $("#goodsData").val();
    if(goodsText==null||goodsText=='')return;
    var goodsList = eval(goodsText);
    editGoodsFlag = true;
    setGoodsInfo(cdId,id,num,price,remark,goodsList);
}
function setGoodsInfo(cdId,id,num,price,remark,goodsList) {
    if(id==null||id=='')return;
    if(goodsList.length<=0)return;
    for(var i=0;i<goodsList.length;i++){
        if(goodsList[i].id==id){
            $("#orderGoods").val(JSON.stringify(goodsList[i]));
            var specName = goodsList[i].spec.name;//规格
            var specList = specName.split("*");
            var sj = (price<=0)?goodsList[i].sj:price;//售价
            var big = (num>0)?parseInt(num/parseInt(eval(specName))):1;
            $("#cdId").val(cdId);
            $("#goodsId").val(goodsList[i].id);
            $("#goodsName").val(goodsList[i].name);
            $("#orderNum").val((num>0)?num:eval(goodsList[i].spec.name));
            $("#specName").text(specName);
            $("#keYongKC").text((goodsList[i].kykc!=null&&goodsList[i].kykc!="")?goodsList[i].kykc:"0");//可用库存
            $("#anQuanKC").text((goodsList[i].aqkc!=null&&goodsList[i].aqkc!="")?goodsList[i].aqkc:"0");//安全库存
            $(".bigUnit").text(goodsList[i].big.name);
            $(".zongUnit").text(goodsList[i].zong.name);
            $(".smallUnit").text(goodsList[i].small.name);
            $("#sumPrice").val(eval(specName)*sj);
            $("#bigPrice").val(eval(specName)*sj);
            $("#orderRemark").val(remark);
            $("#bigNum").val(big);
            if(specList.length>2){
                var zong = (num>0)?parseInt((num-big*parseInt(eval(specName)))/specList[2]):0;
                $(".smallEle").css("display","block");
                $("#zongPrice").val(specList[2]*sj);
                $("#smallPrice").val(sj);
                $("#zongNum").val(zong);
                $("#smallNum").val((num>0)?parseInt(num-big*parseInt(eval(specName))-zong*specList[2]):0);
            }else {
                $(".smallEle").css("display","none");
                $("#zongPrice").val(sj);
                $("#zongNum").val((num>0)?parseInt(num-big*parseInt(eval(specName))):0);
                $("#smallNum").val(0);
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
function addGoods(cdId,id,num,price,remark) {//添加商品到订单列表
    var orderGoods = $("#orderGoods").val();
    if(id==''||num<=0||price<=0||orderGoods==null){
        message("信息不完整!");
        return;
    }
    var goods = eval("("+orderGoods+")");
    setJsonGoods(goods);//将goods添加到JSON数据中
    setPostJson(cdId,id,num,price,remark);
    showGoodsList();
}
function showGoodsList() {//通过隐藏域订单信息展示订单列表
    $(".list .nongjia").remove();
    clearDate();//保存后清除填写信息
    var jsonData = $("#jsonData").val();
    jsonData = eval("("+jsonData+")");
    var sumMoney = 0;
    for(var i=0;i<jsonData.length;i++){
        var json = jsonData[i];
        sumMoney += parseInt(json.num)*parseFloat(json.price);
        showGoods(json.cdId,json.id,json.num,json.price,json.remark,getJsonGoods(json.id));
    }
    $("#totalInfo").text("共 "+jsonData.length+" 条，"+sumMoney.toFixed(4)+"元");
}
function showGoods(cdId,id,num,price,remark,goods) {//显示单个订单商品
    if(id==''||num<=0||price<=0||goods==null){
        message("信息不完整");
        return;
    }
    remark = (remark)?remark:"";
    var ctxStatic = $("#ctxStatic").val();
    var specName = goods.spec.name;
    var specList = specName.split("*");
    var specNum = parseInt(eval(specName));
    var big = parseInt(num/specNum);
    var zong = (specList.length>2)?parseInt((num-(big*specNum))/parseInt(specList[2])):num-(big*specNum);
    var small = parseInt(num-(big*specNum)-(zong*parseInt(specList[2])));
    var smallUnit = (specList.length>2)?goods.small.name:goods.zong.name;
    var smallPriceHtml = (specList.length>2)?'<p>'+price*parseInt(specList[2])+'/'+goods.zong.name+'</p><p>'+price+'/'+smallUnit+'</p>':'<p>'+price+'/'+smallUnit+'</p>';
    var smallNumHtml = (specList.length>2)?'<div style="margin-bottom: 4%;"><input type="text" readonly="true" value="'+small+'" style="width: 30%;border: 1px solid #d3d3d3;margin-right: 8%;text-align: right;"><span>'+goods.small.name+'</span></div>':'';
    var text = '<tr class="nongjia"><td class="nongjia_bg"><span>'+($(".list .nongjia").length+1)+'</span></td><td style="width: 20%"><p style="font-weight: bold;">'+ goods.name+
        '</p><p style="color: #B3B3B3;">'+specName+'</p><p style="color: #B3B3B3;">'+goods.tm +'</p></td><td style="padding-left: 6%;">'+
        '<div style="margin-bottom: 4%;"><input type="text" readonly="true" value="'+big+'" style="width: 30%;border: 1px solid #d3d3d3;margin-right: 8%;text-align: right;"><span>'+goods.big.name+'</span></div>'+
        '<div style="margin-bottom: 4%;"><input type="text" readonly="true" value="'+zong+'" style="width: 30%;border: 1px solid #d3d3d3;margin-right: 8%;text-align: right;"><span>'+goods.zong.name+'</span></div>'+smallNumHtml+
        '</td><td style="padding-left: 5%;"><p style="margin-bottom: 4%;">'+specNum*price+'/'+goods.big.name+'</p>'+smallPriceHtml+'</td><td style="padding-left: 6.5%;"><p>'+num*price+
        '</p></td><td><img onclick="editGoods(\''+cdId+'\',\''+goods.id+'\',\''+num+'\',\''+price+'\',\''+remark+'\')" src="'+ctxStatic+'/images/mxcpbianji.png"></td><td><img onclick="deleteGoods(\''+goods.id+'\')" src="'+ctxStatic+'/images/shanchu.png"></td></tr>'
    $(".list").append(text);
}
function deleteGoods(id) {
    if(id=='')return;
    var jsonData = $("#jsonData").val();
    if(jsonData!=''){
        jsonData = eval("("+jsonData+")");
        for(var i=0;i<jsonData.length;i++){
            if(jsonData[i].id==id){
                jsonData.splice(i,1);
            }
        }
        $("#jsonData").val(JSON.stringify(jsonData));
        showGoodsList();
    }
}
function deleteAllGoods() {
    $("#jsonData").val(JSON.stringify([]));
    showGoodsList();
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
function setPostJson(cdId,id,num,price,remark) {
    if(id==''||num<=0||price<=0){
        message("信息不完整");
        return;
    }
    var json = {cdId:cdId,id:id,num:num,price:price,remark:remark};
    var jsonData = $("#jsonData").val();
    if(jsonData==''){jsonData = [];}else{jsonData = eval("("+jsonData+")");}
    for(var i=0;i<jsonData.length;i++){
        if(jsonData[i].id==id){//id相同且非编辑模式下数量相加
            if(!editGoodsFlag)json.num = parseInt(jsonData[i].num)+parseInt(num);
            jsonData.splice(i,1);
        }
    }
    jsonData.push(json);
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
function message(text) {
    if(top!=null){
        top.$.jBox.tip(text,"warning","系统提示");
    }else {
        $.jBox.tip(text,"warning","系统提示");
    }
}