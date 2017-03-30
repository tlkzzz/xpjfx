<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>个人信息</title>
    <meta name="decorator" content="default"/>
    <%--<script src="${ctxStatic}/ss.js" type="text/javascript"></script>--%>
    <script type="text/javascript">
        $(document).ready(function () {
            var specList = $("#spec").val().split("*");
            if(specList.length<3){
                $("#numZong").css("display","none");
                $("#priceZong").css("display","none");
            }
        })
        function getNum(val) {
            if(val==""||val==null){
                return 0;
            }else {
                return parseInt(val);
            }
        }
        function checkNum(inp) {
            var spec = $("#spec").val();
            var specList = spec.split("*");
            var thisNum = parseInt(getNum(inp.val()));
            if(specList.length>2&&thisNum>0){
                if(inp.attr("id")=="sx"){
                    if(thisNum>parseInt(specList[2])){
                        var szNum = getNum($("#sz").val());
                        var szAdd = parseInt(Math.floor(thisNum/parseInt(specList[2])));
                        $("#sz").val(szNum+szAdd);
                        checkNum($("#sz"));
                        inp.val(parseInt(thisNum-(szAdd*parseInt(specList[2]))));
                    }
                }else if(inp.attr("id")=="sz"){
                    if(thisNum>parseInt(specList[1])){
                        var sjNum = getNum($("#sj").val());
                        var sjAdd = parseInt(Math.floor(thisNum/parseInt(specList[1])));
                        $("#sj").val(sjNum+sjAdd);
                        checkNum($("#sj"));
                        inp.val(parseInt(thisNum-(sjAdd*parseInt(specList[1]))));
                    }
                }
            }else{
                if(inp.attr("id")=="sx") {
                    if (thisNum > parseInt(specList[1])) {
                        var sjNum = getNum($("#sj").val());
                        var sjAdd = parseInt(Math.floor(thisNum / parseInt(specList[1])));
                        $("#sj").val(sjNum + sjAdd);
                        checkNum($("#sj"));
                        inp.val(parseInt(thisNum - (sjAdd * parseInt(specList[1]))));
                    }
                }
            }
        }
        function changeNum(inp) {
            checkNum(inp);
            var spec = $("#spec").val();
            var specList = spec.split("*");
            var numList = [getNum($("#sj").val()),getNum($("#sz").val()),getNum($("#sx").val())];
            var nub = 0;
            for(var i=0;i<specList.length;i++){
                var subNum = specList[i];
                for(var j=i+1;j<specList.length;j++){subNum *= specList[j];}
                nub += subNum*numList[i];
            }
            var dj = $("#dj").val();
            var sumMoney = parseFloat(dj*nub).toFixed(4);
            $("#nub").val(nub);
            $("#zj").val(sumMoney);
            changeSumPrice();
        }
        function changePrice(inp) {
            var spec = $("#spec").val();
            var specList = spec.split("*");
            var nub = $("#nub").val();
            var dj = inp.val();
            if(specList.length>2){
                if(inp.attr("id")=="jj"){
                    dj = (inp.val()/(specList[1]*specList[2])).toFixed(4);
                    $("#jz").val((specList[2]*dj).toFixed(4))
                    $("#jx").val(dj);
                }else if(inp.attr("id")=="jz"){
                    dj = (inp.val()/specList[2]).toFixed(4);
                    $("#jj").val(((specList[1]*specList[2])*dj).toFixed(4));
                    $("#jx").val(dj);
                }else {
                    $("#jj").val(((specList[1]*specList[2])*dj).toFixed(4));
                    $("#jz").val((specList[2]*dj).toFixed(4))
                }
            }else {
                if(inp.attr("id")=="jj"){
                    dj = (inp.val()/specList[1]).toFixed(4);
                    $("#jx").val(dj);
                }else {
                    $("#jj").val((specList[1]*dj).toFixed(4));
                }
            }
            $("#dj").val(dj);
            $("#zj").val((dj*nub).toFixed(4));
        }
        function changeSumPrice() {
            var sumMoney = $("#zj").val();
            var nub = $("#nub").val();
            var dj = (sumMoney/nub).toFixed(4);
            $("#dj").val(dj);
            $("#jx").val(dj);
            changePrice($("#jx"));
        }
	</script>
</head>
<body>
<ul class="nav nav-tabs">
    <li class="active"><a href="${ctx}/sys/user/info">个人信息</a></li>

</ul><br/>
<form:form id="inputForm" modelAttribute="user" action="" method="post" class="form-horizontal">
    <form:hidden path="" id="spec" value="1*5*10"/>
    <form:hidden path="" id="nub" value="120"/>
    <form:hidden path="" id="dj" value="2.56"/>

    <div class="control-group">
        <label class="control-label">数量:</label>
        <div class="controls">
            <span class="input-group-btn">
            <form:input path="" id="sj" type="digits" onchange="changeNum($(this))" htmlEscape="false" class="input-mini " maxlength="50"/>
      		 <a class="btn btn-danger"  >件</a>
    		 </span>
            <span class="input-group-btn" id="numZong">
            <form:input path="" id="sz" type="digits" onchange="changeNum($(this))" htmlEscape="false" class="input-mini " maxlength="50"/>
      		 <a class="btn btn-danger"   >中</a>
    		 </span>
            <span class="input-group-btn">
            <form:input path="" id="sx" type="digits" onchange="changeNum($(this))" htmlEscape="false" class="input-mini " maxlength="50"/>
     		  <a class="btn btn-danger"  >小</a>
    		 </span>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">单价:</label>
        <div class="controls">
            <span class="input-group-btn">
            <form:input path="" id="jj" type="number" onchange="changePrice($(this))" htmlEscape="false" class="input-mini " maxlength="50"/>
      		 <a class="btn btn-danger"  >／件</a>
    		 </span>
            <span class="input-group-btn" id="priceZong">
            <form:input path="" id="jz" type="number" onchange="changePrice($(this))" htmlEscape="false" class="input-mini " maxlength="50"/>
      		 <a class="btn btn-danger"   >／中</a>
    		 </span>
            <span class="input-group-btn">
            <form:input path="" id="jx" type="number" onchange="changePrice($(this))" htmlEscape="false" class="input-mini " maxlength="50"/>
     		  <a class="btn btn-danger"  >／小</a>
    		 </span>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">金额:</label>
        <div class="controls">
            <form:input path="" id="zj" type="number" onchange="changeSumPrice()" htmlEscape="false" maxlength="11"/>
            <span class="input-group-btn">
     		  <a class="btn btn-danger"  >元</a>
    		 </span>
        </div>
    </div>

    <div class="form-actions">
        <input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>
    </div>
</form:form>
</body>
</html>