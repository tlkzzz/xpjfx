<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/6/7 0007
  Time: 下午 8:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>Excel文件导入</title>

    <meta name="decorator" content="default"/>
    <script type="text/javascript">

        $(document).ready(function() {
                    //首先验证文件格式
                    var fileName = $('#file_input').val();
                    if (fileName === '') {
                        alert('请选择文件');
                        return false;
                    }
                    var fileType = (fileName.substring(fileName
                            .lastIndexOf(".") + 1, fileName.length))
                        .toLowerCase();
                    if (fileType !== 'xls' && fileType !== 'xlsx') {
                        alert('文件格式不正确，excel文件！');
                        return false;
                    }
                });

    </script>
</head>
<body>
    <form id="file_form" action="${ctx}/ck/CDaoru/guigeDaoru" enctype="multipart/form-data"
      method="post">
    <input type="file" name="file" id="file_input" />
    <input type="submit" value="文件上传" id='upFile-btn'>
</form>
</body>
</html>
