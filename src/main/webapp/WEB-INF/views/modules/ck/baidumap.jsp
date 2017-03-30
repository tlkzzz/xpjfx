<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
    <style type="text/css">
        body, html,#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}
    </style>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=EVunsrA5meplA36r4oED7mwXdpYqzXiR"></script>
    <title>地图展示</title>
</head>
<body>
<div id="allmap"></div>
<input type="hidden" id="jd">
<input type="hidden" id="wd">
</body>
<script type="text/javascript">
    function aa(){
    return jd+'-'+wd
    }
    var jd ;
    var wd ;
	// 百度地图API功能
	var map = new BMap.Map("allmap");    // 创建Map实例




	<%--var point1 = new BMap.Point(113.056834,28.176775);--%>
	<%--var marker1 = new BMap.Marker(point1); // 创建点--%>
	<%--map.centerAndZoom(point1, 15);   //初始化位置--%>
	<%--map.addOverlay(marker1);   //将点放到地图中--%>
	<%--map.setCurrentCity("长沙");          // 设置地图显示的城市 此项是必须设置的--%>
	<%--map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放--%>
	<%--marker1.enableDragging();   //设置不可以拖放--%>




    var point = new BMap.Point(113.036834,28.176775);
	var marker = new BMap.Marker(point); // 创建点
	map.centerAndZoom(point, 15);   //初始化位置
	map.setCurrentCity("长沙");          // 设置地图显示的城市 此项是必须设置的
	map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
	map.addOverlay(marker);   //将点放到地图中
	map.enableDragging();  //设置地图拖放
	marker.enableDragging();   //设置点可以拖放
    var label = new BMap.Label("优彼",{offset:new BMap.Size(20,-10)});
	marker.setLabel(label);   //设置文字
	marker.addEventListener("dragend", function(){
	  var center = map.getCenter();
   jd=center.lng;
   wd=center.lat;
    // alert("地图中心点变更为："  + center.lng + ", " + center.lat);
    });

</script>
</html>