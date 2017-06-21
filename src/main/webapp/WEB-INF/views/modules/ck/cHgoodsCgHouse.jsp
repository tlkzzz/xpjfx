<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>采购入库</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
        var flag = true;
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
                    formSubmit();
                    closeLoading();
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
		function formSubmit() {
		    var form = $("#inputForm");
		    if(flag) {
                $.ajax({
                    url: form.attr("action"),
                    type: "POST",
                    dataType: "json",
                    data: form.serialize(),
                    success: function (data) {
                        if (data) {
                            top.$.jBox.tip("入库成功", "系统提示", "warning");
                            flag = false;
                        }
                    }
                });
            }else {
                top.$.jBox.tip("商品已入库！", "系统提示", "warning");
			}
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
	</ul><br/>
	<form:form id="inputForm" modelAttribute="cHgoods" action="${ctx}/ck/cHgoods/saveCG" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="goods.id"/>
		<form:hidden path="fPayment.travelUnit.id" id="travelUnit"/>
		<form:hidden path="rkState" value="0"/>
		<form:hidden path="ckState"/><%--这里保存采购订单ID--%>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">商品：</label>
			<div class="controls">
				<form:input path="goods.name" class="required" readOnly="true" htmlEscape="true"></form:input>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">仓库：</label>
			<div class="controls">
				<form:select path="house.id" class="required">
					<form:option value="" label="请选择" ></form:option>
					<form:options items="${houseList}" itemLabel="name" itemValue="id" htmlEscape="false"></form:options>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">供应商：</label>
			<div class="controls">
				<form:select path="supplierid" class="required" onchange="$('#travelUnit').val($(this).val())">
					<form:option value="" label="请选择" ></form:option>
					<form:options items="${supplierList}" itemLabel="name" itemValue="id" htmlEscape="false"></form:options>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">成本价：</label>
			<div class="controls">
				<form:input path="cbj" class="required" number="true" maxlength="12" htmlEscape="true"></form:input>
				<span class="help-inline"><font color="red">* 保留两位小数</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">实际入库数：</label>
			<div class="controls">
				<form:input path="nub" class="required" digits="true" maxlength="11" htmlEscape="true" value="${cCgzbinfo.nub}" />
				<span class="help-inline"><font color="red">* 最小单位计量</font> </span>
			</div>
		</div>
	<div class="control-group">
		<label class="control-label">付款日期：</label>
		<div class="controls">
		<input id="paymentDate"  name="fPayment.paymentDate"  type="text" readonly="readonly" maxlength="64" class="input-medium Wdate" style="width:163px;"
			   value="<fmt:formatDate value="${fPayment.paymentDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
			   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});"/>
			<span class="help-inline"><font color="red">* </font> </span>
		</div>
	</div>
		<div class="control-group">
			<label class="control-label">实际支出：</label>
			<div class="controls">
				<form:input path="fPayment.je" htmlEscape="false" maxlength="64" class="input-xlarge required "/>
				<span class="help-inline"><font color="red">* </font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">付款账号：</label>
			<div class="controls">
				<form:input path="fPayment.paymentAccount" htmlEscape="false" maxlength="64" class="input-xlarge required "/>
				<span class="help-inline"><font color="red">* </font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">来往账号：</label>
			<div class="controls">
				<form:input path="fPayment.travelAccount" htmlEscape="false" maxlength="64" class="input-xlarge required "/>
				<span class="help-inline"><font color="red">* </font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">经手人：</label>
			<div class="controls">
				<sys:treeselect id="user" name="user.id" value="${testData.user.id}" labelName="fPayment.jsr" labelValue="${testData.user.name}"
								title="用户" url="/sys/office/treeData?type=3" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">付款方式：</label>
			<div class="controls">
				<form:select path="fPayment.paymentMode" class="input-xlarge required" id="expenMode">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('expenMode')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
	<div class="control-group">
		<label class="control-label">科目编码：</label>
		<div class="controls">
			<sys:treeselect id="parent" name="parent.id" value="${cKm.parent.id}" labelName="fPayment.subjectCode" labelValue="${cKm.parent.name}"
							title="科目编码" url="/ck/cKm/treeData" extId="${cKm.id}" cssClass="" allowClear="true"/>
		</div>
	</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否欠款：</label>
			<span class="help-inline" style="margin-left: 20px">
			<form:checkbox path="qkzt" value="1" class="input-xxlarge"/> </span>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="ck:cHgoods:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>