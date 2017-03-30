<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>客户管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
					$("#baidumap").click(function(){
        		top.$.jBox.open("iframe:${ctx}/ck/cStore/baidumap", "选择图标", 700, $(top.document).height()-180, {
                    buttons:{"确定":"ok", "关闭":true}, submit:function(v, h, f){
                        if (v=="ok"){
                        var k=	h.find("iframe")[0].contentWindow.aa();
                        var ka = k.split("-");
             				console.log(k)
             				var h=ka[0];
             				var h1=ka[1];
             			    $("#jd").val(h);
                            $("#wd").val(h1);
                        }
                    }, loaded:function(h){
                        $(".jbox-content", top.document).css("overflow-y","hidden");
                    }
                });
        	});
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
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/ck/cStore/list">客户列表</a></li>
		<li class="active"><a href="${ctx}/ck/cStore/form?id=${cStore.id}">客户<shiro:hasPermission name="ck:cStore:edit">${not empty cStore.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="ck:cStore:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="cStore" action="${ctx}/ck/cStore/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<shiro:hasPermission name="ck:cStore:auditing"><form:hidden path="state" var="1"/></shiro:hasPermission>
		<sys:message content="${message}"/>

	<div class="accordion" id="accordion2">
		<div class="accordion-group">
			<div class="accordion-heading">
				<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseOne">
					基本信息
				</a>
			</div>
			<div id="collapseOne" class="accordion-body collapse in">
				<div class="accordion-inner">
					<div class="control-group">
						<label class="control-label">门店图片：</label>
						<div class="controls">
							<form:hidden id="nameImage" path="mdtp" htmlEscape="false" maxlength="640" class="input-xlarge"/>
							<sys:ckfinder input="nameImage" type="images" uploadPath="/mdPhoto" selectMultiple="true" maxWidth="100" maxHeight="100"/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">客户分类：</label>
						<div class="controls">
							<form:select path="sclass.id"  cssClass="required">
								<form:option value="" label="请选择"></form:option>
								<form:options items="${sClassList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
							</form:select>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">区域：</label>
						<div class="controls">
							<sys:treeselect id="areaId" name="area.id" value="${cStore.area.id}" labelName="area.name" labelValue="${cStore.area.name}"
								title="区域选择" url="/sys/area/treeData" cssClass="required"/>
						<span class="help-inline"><font color="red">*</font> </span>
							</div>
					</div>
					<div class="control-group">
						<label class="control-label">详细地址：</label>
						<div class="controls">
							<form:input path="dz" htmlEscape="false" maxlength="64" class="input-xlarge "/>
								<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
			       <div class="control-group">
                    						<label class="control-label">采购员：</label>
                    						<div class="controls">
                    							<form:input path="cgy" htmlEscape="false" maxlength="64" class="input-xlarge "/>
                    								<span class="help-inline"><font color="red">*</font> </span>
                    						</div>
                    </div>
					<div class="control-group">
						<label class="control-label">店铺名称：</label>
						<div class="controls">
							<form:input path="dpmc" htmlEscape="false" maxlength="64" class="input-xlarge "/>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">客户名称：</label>
						<div class="controls">
							<form:input path="name" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">手机号码：</label>
						<div class="controls">
							<form:input path="phone" phone="true" htmlEscape="false" maxlength="11" class="input-xlarge "/>
								<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
					<!--
	<div class="control-group">
			<label class="control-label">客户简称：</label>
			<div class="controls">
				<form:input path="khjc" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		-->
				</div>
			</div>
		</div>

		<div class="accordion-group">
			<div class="accordion-heading">
				<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseTwo">
					其他信息
				</a>
			</div>
			<div id="collapseTwo" class="accordion-body collapse">
				<div class="accordion-inner">

				  <div class="control-group">
                          <label class="control-label">采购员联系方式：</label>
                                                      <div class="controls">
                                                      <form:input path="cgydh" htmlEscape="false"  phone="true" maxlength="64" class="input-xlarge "/>
                                                        </div>
                                                  </div>
					<div class="control-group">
						<label class="control-label">邮编：</label>
						<div class="controls">
							<form:input path="yb" ZipCode="true" htmlEscape="false" maxlength="64" class="input-xlarge "/>
						</div>
					</div>
					<div class="control-group">
                    						<label class="control-label">回执周期：</label>
                    						<div class="controls">
                    							<form:input path="hzzq" htmlEscape="false" maxlength="9" class="input-xlarge "/>
                    						</div>
                    					</div>
                    <div class="control-group">
                                        <label class="control-label">微信：</label>
                                        	<div class="controls">
                                        		<form:input path="weixin" htmlEscape="false" maxlength="9" class="input-xlarge "/>
                                        		</div>
                                        </div>
                      <div class="control-group">
                                         <label class="control-label">QQ：</label>
                                         <div class="controls">
                                          <form:input path="qq" htmlEscape="false" maxlength="9" class="input-xlarge "/>
                                         </div>
                                    </div>
                      <div class="control-group">
                                          <label class="control-label">回执周期：</label>
                                        <div class="controls">
                                   <form:input path="hzzq" htmlEscape="false" maxlength="9" class="input-xlarge "/>
                                </div>
                            </div>
                     <div class="control-group">
                                     <label class="control-label">额度：</label>
                                      <div class="controls">
                                      <form:input path="edu" htmlEscape="false" maxlength="9" class="input-xlarge "/>
                                 </div>
                            </div>
                     <div class="control-group">
                                    <label class="control-label">销售渠道：</label>
                                     <div class="controls">
                                       <form:input path="xsqd" htmlEscape="false" maxlength="9" class="input-xlarge "/>
                                 </div>
                           </div>
                    <div class="control-group">
                                 <label class="control-label">进货渠道：</label>
                                 <div class="controls">
                                 <form:input path="hzzq" htmlEscape="jhqd" maxlength="9" class="input-xlarge "/>
                                 </div>
                             </div>
					<div class="control-group">
						<label class="control-label">电话：</label>
						<div class="controls">
							<form:input path="dh" phone="true" htmlEscape="false" maxlength="64" class="input-xlarge "/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">电子邮件：</label>
						<div class="controls">
							<form:input path="email" email="true" htmlEscape="false" maxlength="64" class="input-xlarge "/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">积分系数：</label>
						<div class="controls">
							<form:input path="jfxs" htmlEscape="false" maxlength="9" class="input-xlarge "/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">开户行名称：</label>
						<div class="controls">
							<form:input path="khh" htmlEscape="false" maxlength="64" class="input-xlarge "/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">开户行账号：</label>
						<div class="controls">
							<form:input path="khhzh" creditcard="true" htmlEscape="false" maxlength="64" class="input-xlarge "/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">开户人：</label>
						<div class="controls">
							<form:input path="khr" htmlEscape="false" maxlength="64" class="input-xlarge "/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">营业执照：</label>
						<div class="controls">
							<form:hidden id="nameImage1" path="yyzz" htmlEscape="false" maxlength="640" class="input-xlarge"/>
							<sys:ckfinder input="nameImage1" type="images" uploadPath="/mdPhoto" selectMultiple="false" maxWidth="100" maxHeight="100"/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">身份证照：</label>
						<div class="controls">
							<form:hidden id="nameImage2" path="sfzz" htmlEscape="false" maxlength="640" class="input-xlarge"/>
							<sys:ckfinder input="nameImage2" type="images" uploadPath="/mdPhoto" selectMultiple="true" maxWidth="100" maxHeight="100"/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">经度：</label>
						<div class="controls">
							<form:input path="jd" readonly='true' htmlEscape="false" maxlength="64" class="input-xlarge "/>

						</div>
					</div>
					<div class="control-group">
						<label class="control-label">纬度：</label>
						<div class="controls">
							<form:input path="wd" readonly='true' htmlEscape="false" maxlength="64" class="input-xlarge "/>
								<a id="baidumap"  class="btn btn-primary">地图</a>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">备注：</label>
						<div class="controls">
							<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
						</div>
					</div>

				</div>
			</div>
		</div>
	</div>


		<div class="form-actions">
			<shiro:hasPermission name="ck:cStore:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>