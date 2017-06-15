<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>仓库利润排行</title>
	<meta name="decorator" content="default"/>
	<script src="${ctxStatic}/echarts/echarts.js"></script>

</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ck/cDdinfo/cklist">仓库利润排行</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="cDdinfo" action="${ctx}/ck/cDdinfo/cklist" method="post" class="breadcrumb form-search">
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
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<div id="main" style="width: 1000px;height:400px;"></div>

	<script type="text/javascript">
		$(document).ready(function() {
			getData();
		});
		function getData(){
			var startDate = $("#startDate").val();
			var endDate  =$("#endDate").val();
			$.ajax({
				url:"${ctx}/ck/cDdinfo/ckAjax",
				type:"POST",
				dataType:"text",
				data:{startDate:startDate,endDate:endDate},
				success:function(data){
				    var list = eval(data);

                    for(var i=0;i<list.length;i++){
                      var xslr= list[i].zlr;
					  var ywy=list[i].name;
                    }

                    // 基于准备好的dom，初始化echarts实例
                    var myChart = echarts.init(document.getElementById('main'));

                    // 指定图表的配置项和数据
                    var option = {
                        title: {
                            text: '仓库利润排行'
                        },
                        tooltip: {},
                        legend: {
                            data:['利润']
                        },
                        xAxis: {
                            data: [ywy]
                        },
                        yAxis: {},
                        series: [{
                            name: '利润',
                            type: 'bar',
                            data: [xslr]
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
</body>
</html>