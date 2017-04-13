<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>科目类别表管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treetable.jsp" %>
	<script type="text/javascript">
		$(document).ready(function() {
			var tpl = $("#treeTableTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
			var data = ${fns:toJson(list)}, ids = [], rootIds = [];
			for (var i=0; i<data.length; i++){
				ids.push(data[i].id);
			}
			ids = ',' + ids.join(',') + ',';
			for (var i=0; i<data.length; i++){
				if (ids.indexOf(','+data[i].parentId+',') == -1){
					if ((','+rootIds.join(',')+',').indexOf(','+data[i].parentId+',') == -1){
						rootIds.push(data[i].parentId);
					}
				}
			}
			for (var i=0; i<rootIds.length; i++){
				addRow("#treeTableList", tpl, data, rootIds[i], true);
			}
			$("#treeTable").treeTable({expandLevel : 5});
		});
		function addRow(list, tpl, data, pid, root){
			for (var i=0; i<data.length; i++){
				var row = data[i];
				if ((${fns:jsGetVal('row.parentId')}) == pid){
					$(list).append(Mustache.render(tpl, {
						dict: {
						blank123:0}, pid: (root?0:pid), row: row
					}));
					addRow(list, tpl, data, row.id);
				}
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ck/cKm/">科目类别表列表</a></li>
		<shiro:hasPermission name="ck:cKm:edit"><li><a href="${ctx}/ck/cKm/form">科目类别表添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="cKm" action="${ctx}/ck/cKm/" method="post" class="breadcrumb form-search">
		<ul class="ul-form">
			<li><label>科目名称：</label>
				<form:input path="kmname" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>所有父级Id：</label>
				<form:input path="parentIds" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>

			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="treeTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>科目名称</th>
				<th>科目编号</th>
				<th>修改时间</th>
				<th>备注</th>
				<shiro:hasPermission name="ck:cKm:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody id="treeTableList"></tbody>
	</table>
	<script type="text/template" id="treeTableTpl">
		<tr id="{{row.id}}" pId="{{pid}}">
			<td><a href="${ctx}/ck/cKm/form?id={{row.id}}">
				{{row.kmname}}
			</a></td>
			<td>
				{{row.kmnb}}
			</td>
			<td>
				{{row.updateDate}}
			</td>
			<td>
				{{row.remarks}}
			</td>
			<shiro:hasPermission name="ck:cKm:edit"><td>
   				<a href="${ctx}/ck/cKm/form?id={{row.id}}">修改</a>
				<a href="${ctx}/ck/cKm/delete?id={{row.id}}" onclick="return confirmx('确认要删除该科目类别表及所有子科目类别表吗？', this.href)">删除</a>
				<a href="${ctx}/ck/cKm/form?parent.id={{row.id}}">添加下级科目类别表</a> 
			</td></shiro:hasPermission>
		</tr>
	</script>
</body>
</html>