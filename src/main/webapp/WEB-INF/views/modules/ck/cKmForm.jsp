<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>科目类别表管理</title>
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
        function hide(){
            var kmlbid=document.getElementById("kmlbid").value;
            if(kmlbid!="1"){
                document.getElementById("zhanghu").style.visibility = "hidden";
                document.getElementById("zhanghu").style.height = "1px";
                document.getElementById("yue").style.visibility = "hidden";
                document.getElementById("yue").style.height = "1px";
            }else if(kmlbid="1"){
                document.getElementById("zhanghu").style.visibility = "visible";
                document.getElementById("zhanghu").style.height = "30px";
                document.getElementById("yue").style.visibility = "visible";
                document.getElementById("yue").style.height = "30px";
                document.getElementById("baocun").style.height = "50px";
			}else if(kmlbid=""){
                document.getElementById("zhanghu").style.visibility = "hidden";
                document.getElementById("zhanghu").style.height = "1px";
                document.getElementById("yue").style.visibility = "hidden";
                document.getElementById("yue").style.height = "1px";
			}

		}
		function check(){
            $("#b").show
            var reg = /^\d{1,9}(\.\d{1,4})?$/
            if(!reg.test($("#je").val())){
                $("#b").text("× 请输入最多4位小数的14位数值！")
            }else{
                $("#b").text("√")
            }
        }
        $(function(){
            $("#je").blur(function(){
                check();
            })
        })
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/ck/cKm/">科目类别列表</a></li>
		<li class="active"><a href="${ctx}/ck/cKm/form?id=${cKm.id}">科目类别表<shiro:hasPermission name="ck:cKm:edit">${not empty cKm.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="ck:cKm:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="cKm" action="${ctx}/ck/cKm/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">科目类别ID：</label>
			<div class="controls">
				<form:select path="kmlbid" class="input-xlarge required" onclick="hide()" id="kmlbid">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('kmlbid')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">科目编号：</label>
			<div class="controls">
				<form:input path="kmnb" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">科目名称：</label>
			<div class="controls">
				<form:input path="kmname" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group" id="beizhu">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group" id="zhanghu">
			<label class="control-label">账户：</label>
			<div class="controls">
				<form:input path="zh" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group" id="yue">
			<label class="control-label">余额：</label>
			<div class="controls">
				<form:input path="je" id="je" htmlEscape="false" class="input-xlarge " pattern="^\d{1,9}(\.\d{1,4})?$"/>
                <span class="help-inline" id="b"><font color="red"></font> </span>
			</div>
		</div>

		<div class="form-actions" id="baocun">
			<shiro:hasPermission name="ck:cKm:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>