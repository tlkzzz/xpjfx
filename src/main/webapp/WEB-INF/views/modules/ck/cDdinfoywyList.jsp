<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>业务员利润</title>
	<meta name="decorator" content="default"/>
	<script src="${ctxStatic}/echarts/echarts.js"></script>

</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ck/cDdinfo/ywylr">业务员利润排行</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="cDdinfo" action="${ctx}/ck/cDdinfo/ywylr" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>开始时间：</label>
				<input name="startDate" id="startDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					   value="${startDate}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li><label>结束时间：</label>
				<input name="endDate" id="endDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					   value="${endDate}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
		</ul>
	</form:form>
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
                    // 基于准备好的dom，初始化echarts实例
                    var myChart = echarts.init(document.getElementById('main'));

                    // 指定图表的配置项和数据
                    var option = {
                        title: {
                            text: 'ECharts 入门示例'
                        },
                        tooltip: {},
                        legend: {
                            data:['销量']
                        },
                        xAxis: {
                            data: ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
                        },
                        yAxis: {},
                        series: [{
                            name: '销量',
                            type: 'bar',
                            data: [5, 20, 36, 10, 10, 20]
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
			<td align="center" valign="middle">$${newD}</td>
			<td align="center" valign="middle">$${newD2}</td>
			<td align="center" valign="middle">$${newD3}</td>
			<td align="center" valign="middle">$${newD4}</td>
			<td align="center" valign="middle">$${newD5}</td>
			<td align="center" valign="middle">$${newD6}</td>
			<td align="center" valign="middle">$${newD7}</td>
			<td align="center" valign="middle">$${newD8}</td>
			<td align="center" valign="middle">$${newD9}</td>
			<td align="center" valign="middle">$${newD10}</td>
			<td align="center" valign="middle">$${newD11}</td>
			<td align="center" valign="middle">$${newD12}</td>
		</tr>
	</table>
</body>
</html>