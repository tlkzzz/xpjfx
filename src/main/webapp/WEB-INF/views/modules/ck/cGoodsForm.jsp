<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商品管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
			rules: {
                sort: {
                       remote: "${ctx}/ck/cGoods/checksort?id="+$("#id").val(),
                                              },
                                    },
                 messages: {
                        sort: {remote: "商品编码已存在"},
                                  },
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
		function checkSpec() {
		    var str = $("#specId option:selected").text();
            $("#zongSpan").text("中");
		    $("#bigId").val('');
            $("#zongId").val('');
            $("#smallSeId").val('');//将三个单位下拉按钮置为空
            if(str!=""){
                $("#unitsId").show();
                if(checkMi(str)>1){
                    $("#zongSpan").text("中");
                    $("#smallId").show();
                }else{
                    $("#zongSpan").text("小");
                    $("#smallId").hide();
                }
			}else{
                $("#unitsId").hide();
			}
        }
		function checkMi(str) {
			return str.split('*').length-1;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/ck/cGoods/list">商品列表</a></li>
		<li class="active"><a href="${ctx}/ck/cGoods/form?id=${cGoods.id}">商品<shiro:hasPermission name="ck:cGoods:edit">${not empty cGoods.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="ck:cGoods:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="cGoods" action="${ctx}/ck/cGoods/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
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
			<label class="control-label">商品分类：</label>
			<div class="controls">
				<sys:treeselect id="gclass" name="gclass.id" value="${cGoods.gclass.id}" labelName="gclass.name" labelValue="${cGoods.gclass.name}"
								title="商品分类" url="/ck/cGclass/treeData" cssClass="required"/>
			</div>
		</div>
		 <div class="control-group">
            <label class="control-label">品牌：</label>
                <div class="controls">
               <form:select path="bands.id">
               <form:options items="${bandsList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
                </form:select>
                <span class="help-inline"><font color="red">*</font> </span>
                	</div>
                </div>
               <div class="control-group">
                               <label class="control-label">供应商：</label>
                                       <div class="controls">
                                       <form:select path="supplier.id">
                                        <form:options items="${supplierList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
                                       </form:select>
                                       </div>
                                    </div>
		<div class="control-group">
			<label class="control-label">商品名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
			<div class="control-group">
        			<label class="control-label">商品别名：</label>
        			<div class="controls">
        				<form:input path="spbm" htmlEscape="false" maxlength="64" class="input-xlarge "/>
        			</div>
        		</div>
        <div class="control-group">
        			<label class="control-label">规格：</label>
        			<div class="controls">
        				<form:select path="spec.id" onchange="checkSpec()" id="specId" cssClass="required">
        					<form:option value="" label="请选择"></form:option>
        					<form:options items="${specList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
        				</form:select>
        			</div>
        		</div>
        		<div class="control-group" <c:if test="#{not empty cGoods.spec}">style="display: none"</c:if> id="unitsId">
                			<label class="control-label">单位：</label>
                			<div class="controls">
                				<label class="control-label">大
                				<form:select path="big.id" cssClass="required" id="bigId">
                					<form:option value="" label="请选择"></form:option>
                					<form:options items="${unitList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
                				</form:select></label>
                				<label class="control-label"><span id="zongSpan">中</span>
                				<form:select path="zong.id" cssClass="required" id="zongId">
                					<form:option value="" label="请选择"></form:option>
                					<form:options items="${unitList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
                				</form:select></label>
                				<label class="control-label" id="smallId">小
                				<form:select path="small.id" cssClass="required" id="smallSeId">
                					<form:option value="" label="请选择"></form:option>
                					<form:options items="${unitList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
                				</form:select></label>
                				<span class="help-inline"><font color="red">*</font> </span>
                			</div>
                		</div>
               <div class="control-group">
               			<label class="control-label">商品编码：</label>
               			<div class="controls">
               				<form:input path="sort" htmlEscape="false" maxlength="64" class="input-xlarge "/>
               			</div>
               		</div>
               <div class="control-group">
               			<label class="control-label">条码：</label>
               			<div class="controls">
               				<form:input path="tm" htmlEscape="false" maxlength="64" class="input-xlarge "/>
               			</div>
               		</div>
        				<div class="control-group">
                			<label class="control-label">成本价：</label>
                			<div class="controls">
                				<form:input path="cbj" htmlEscape="false" class="input-xlarge required"/>
                				<span class="help-inline"><font color="red">*</font> </span>
                			</div>
                		</div>
                		<div class="control-group">
                			<label class="control-label">售价：</label>
                			<div class="controls">
                				<form:input path="sj" htmlEscape="false" class="input-xlarge required"/>
                				<span class="help-inline"><font color="red">*</font> </span>
                			</div>
                		</div>
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

			  <div class="control-group">
                            			<label class="control-label">预警售价：</label>
                            			<div class="controls">
                            				<form:input path="yjsj" htmlEscape="false" class="input-xlarge required"/>
                            			</div>
                            		</div>
                            		 <div class="control-group">
                                        <label class="control-label">参考成本价：</label>
                                           <div class="controls">
                                                <form:input path="ckcbj" htmlEscape="false" class="input-xlarge required"/>
                                            </div>
                                       </div>
				<div class="accordion-inner">
					<div class="control-group">
			<label class="control-label">商品图片：</label>
			<div class="controls">
				<form:hidden id="nameImage" path="sptp" htmlEscape="false" maxlength="255" class="input-xlarge"/>
				<sys:ckfinder input="nameImage" type="images" uploadPath="/photo" selectMultiple="true" maxWidth="100" maxHeight="100"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">保质期：</label>
			<div class="controls">
				<form:input path="bzq" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">产地：</label>
			<div class="controls">
				<form:input path="cd" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">生产厂商：</label>
			<div class="controls">
				<form:input path="sccs" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">批准文号：</label>
			<div class="controls">
				<form:input path="pzwh" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">质检报告号：</label>
			<div class="controls">
				<form:input path="zjbgh" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">食品卫生许可证：</label>
			<div class="controls">
				<form:input path="spxkz" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">全国工业生产产品许可证：</label>
			<div class="controls">
				<form:input path="gycpxkz" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">生产许可证有效期：</label>
			<div class="controls">
				<input name="scxkz" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${cGoods.scxkz}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<%--<div class="control-group">
			<label class="control-label">仓库ID：</label>
			<div class="controls">
				<form:input path="houid" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">单位：</label>
			<div class="controls">
				<form:input path="unit.id" htmlEscape="false" ></form:input>
			</div>
		</div>--%>
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
			<shiro:hasPermission name="ck:cGoods:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>