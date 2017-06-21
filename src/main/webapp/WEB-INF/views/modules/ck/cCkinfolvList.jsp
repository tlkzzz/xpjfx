<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>利润图表</title>
	<meta name="decorator" content="default"/>
	<script src="${ctxStatic}/echarts/echarts.js"></script>

</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ck/cDdinfo/lrtblist">利润图表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="cDdinfo" action="${ctx}/ck/cDdinfo/lrtblist" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>日期：</label>
				<input name="startDate" id="startDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="${startDate}"
					   onclick="WdatePicker({dateFmt:'yyyy',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<ul >
		<li> ${startDate}年度月销利润</li>
	</ul>
	<div id="main" style="width: 1000px;height:400px;"></div>

	<script type="text/javascript">
		$(document).ready(function() {
			getData();
		});
		function getData(){
			var startDate = $("#startDate").val();
			$.ajax({
				url:"${ctx}/ck/cDdinfo/lrajax",
				type:"POST",
				dataType:"json",
				data:{startDate:startDate},
				success:function(data){

                    var myChart = echarts.init(document.getElementById('main'));
                    // 指定图表的配置项和数据
                    var option = {
                        title: {
                            text: ''
                        },
                        tooltip: {},
                        legend: {
                            data:['销量']
                        },
                        xAxis: {
                            splitNumber: 1,
                            data: ["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"]
                        },
                        yAxis: {},
                        series: [{
                            name: '销量',
                            type: 'line',
                            data: [data[0],data[1],data[2],data[3],data[4],data[5],data[6],data[7],data[8],data[9],data[10],data[11]]
                        }]
                    };

                    // 使用刚指定的配置项和数据显示图表。
                    myChart.setOption(option);
				}
			})
		}
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
	<table border="1" width="1000">
		<tr>
			<th>一月</th>
			<th>二月</th>
			<th>三月</th>
			<th>四月</th>
			<th>五月</th>
			<th>六月</th>
			<th>七月</th>
			<th>八月</th>
			<th>九月</th>
			<th>十月</th>
			<th>十一月</th>
			<th>十二月</th>
		</tr>
		<tr>
			<td align="center" valign="middle">¥${newD}</td>
			<td align="center" valign="middle">¥${newD2}</td>
			<td align="center" valign="middle">¥${newD3}</td>
			<td align="center" valign="middle">¥${newD4}</td>
			<td align="center" valign="middle">¥${newD5}</td>
			<td align="center" valign="middle">¥${newD6}</td>
			<td align="center" valign="middle">¥${newD7}</td>
			<td align="center" valign="middle">¥${newD8}</td>
			<td align="center" valign="middle">¥${newD9}</td>
			<td align="center" valign="middle">¥${newD10}</td>
			<td align="center" valign="middle">¥${newD11}</td>
			<td align="center" valign="middle">¥${newD12}</td>
		</tr>
	</table>
</body>
</html>