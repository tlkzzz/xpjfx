<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>下载中心</title>
    <meta name="decorator" content="default"/>
    <style type="text/css">
        body { margin: auto; /*background-color:#F0F2F4;*/ font-family: "微软雅黑"; }
        h1 { font: 700 20px "微软雅黑"; color: #333; margin-top: 10px; }
        .divLine { border-bottom: solid 1px #E8E8E8; margin-bottom: 6px; margin-top: 1px; }
        .item { line-height: 22px; font-size: 12px; color: #C1C1C1; }
        /*-----------------*/
        .load-warpDiv { border: 1px solid #DDDDDD; padding: 6px; width: 164px; height: 260px; color: #333; float: left; margin: 8px 8px; }
        .load-imgDiv { width: 150px; height: 150px; border: 0px solid #ddd; background-color: #EEEEEE; }
        .load-titleDiv { font-weight: bold; padding: 4px 0px; height: 24px; overflow: hidden; }
        .load-remarkDiv { font-size: 13px; overflow: hidden; padding: 0px 0px; height: 40px; margin: 4px 0px; color: #aaa; }
        .load-botomDiv { border-top: 0px solid #ccc; font-size: 12px; height: 30px; line-height: 30px; }
        .load-botomDiv-left { float: left; width: 68px; }
        .load-botomDiv-right { float: right; text-align: right; }
    </style>
    <script type="text/javascript">
        $(document).ready(function () {

        });
    </script>
    <link href="${ctxStatic}/bootstrap/2.3.1/css_default/bootstrap.css" type="text/css" rel="stylesheet"/>
    <link href="${ctxStatic}/css/bootstrap-switch.css" type="text/css" rel="stylesheet"/>
</head>
<body>
        <table style="width: 100%">
            <tbody>
            <tr>
                <td valign="top" style="padding-top: 10px; padding-bottom: 10px; padding-right: 10px;">
                    <div id="rightBodyDiv" class="panel panel-default" style="height: 537px; overflow: auto;">
                        <div class="panel-heading">
                            <span>下载中心</span>
                            <%--<span style="float:right;color:#aaaaaa">暂不支持微信二维码扫描下载,请使用其他浏览器进行扫描下载</span>--%>
                        </div>
                        <div class="panel-body">
                                    <div class="load-warpDiv computer" style="display: block;">
                                        <div class="load-imgDiv">
                                            <a id="rptDN_ctl00_linkImgIco" target="_blank" href="https://itunes.apple.com/cn/app/%E4%BC%98%E5%BD%BC%E5%88%86%E9%94%80/id1256319312?mt=8">
                                                <img id="rptDN_ctl00_imgIco" src="${ctxStatic}/images/1503476697.png" style="width:150px;border-width:0px;">
                                            </a>

                                            <span id="rptDN_ctl00_lblUrl" style="display: none"></span>
                                            <span id="rptDN_ctl00_lblPicPath" style="display: none"></span>
                                        </div>
                                        <div class="load-titleDiv">
                                            <span id="rptDN_ctl00_lblName">优彼分销(苹果)</span>
                                        </div>
                                        <div class="load-remarkDiv" title="仓库管理,销售出入库,店铺打卡,定期配送,收付款等于一体的分销系统.">仓库管理,销售出入库,店铺打卡,定期配送,收付款等于一体的分销系统.</div>
                                        <div class="load-botomDiv">
                                            <div class="load-botomDiv-left">
                                                <img src="${ctxStatic}/images/xiazaizhongxin-pingguo.png">
                                                &nbsp;
                                            </div>
                                            <div class="load-botomDiv-right" style="color: #AAAAAA; text-align: right;">
                                                <table>
                                                    <tbody><tr>
                                                        <td>
                                                            <a id="rptDN_ctl00_ImgDownload" target="_blank" href="https://itunes.apple.com/cn/app/%E4%BC%98%E5%BD%BC%E5%88%86%E9%94%80/id1256319312?mt=8">
                                                                <img src="${ctxStatic}/images/homepage-01_1.png" alt="" style="border-width:0px;">
                                                            </a>
                                                        </td>
                                                        <td>
                                                            <a id="rptDN_ctl00_linkDowLoad" target="_blank" href="https://itunes.apple.com/cn/app/%E4%BC%98%E5%BD%BC%E5%88%86%E9%94%80/id1256319312?mt=8" style="color:#AAAAAA;">下载</a>
                                                        </td>
                                                    </tr>
                                                </tbody></table>
                                            </div>
                                        </div>
                                    </div>
                                
                                    <div class="load-warpDiv computer" style="display: block;">
                                        <div class="load-imgDiv">
                                            <a id="rptDN_ctl01_linkImgIco" href="${pageContext.request.contextPath}/userfiles/1/files/%E4%BC%98%E5%BD%BC%E5%88%86%E9%94%80%E5%B9%B3%E5%8F%B0.apk">
                                                <img id="rptDN_ctl01_imgIco" src="${ctxStatic}/images/1503477491.png" style="width:150px;border-width:0px;">
                                            </a>
                                            <span id="rptDN_ctl01_lblUrl" style="display: none"></span>
                                            <span id="rptDN_ctl01_lblPicPath" style="display: none"></span>
                                        </div>
                                        <div class="load-titleDiv">
                                            <span id="rptDN_ctl01_lblName">优彼分销(安卓)</span>
                                        </div>
                                        <div class="load-remarkDiv" title="仓库管理,销售出入库,店铺打卡,定期配送,收付款等于一体的分销系统.">仓库管理,销售出入库,店铺打卡,定期配送,收付款等于一体的分销系统.</div>
                                        <div class="load-botomDiv">
                                            <div class="load-botomDiv-left">
                                                <img src="${ctxStatic}/images/xiazaizhongxin-anzhuo.png">
                                                &nbsp;
                                            </div>
                                            <div class="load-botomDiv-right" style="color: #AAAAAA; text-align: right;">
                                                <table>
                                                    <tbody><tr>
                                                        <td>
                                                            <a id="rptDN_ctl01_ImgDownload" href="${pageContext.request.contextPath}/userfiles/1/files/%E4%BC%98%E5%BD%BC%E5%88%86%E9%94%80%E5%B9%B3%E5%8F%B0.apk">
                                                                <img src="${ctxStatic}/images/homepage-01_1.png" alt="" style="border-width:0px;">
                                                            </a>
                                                        </td>
                                                        <td>
                                                            <a id="rptDN_ctl01_linkDowLoad" href="${pageContext.request.contextPath}/userfiles/1/files/%E4%BC%98%E5%BD%BC%E5%88%86%E9%94%80%E5%B9%B3%E5%8F%B0.apk" style="color:#AAAAAA;">下载</a>
                                                        </td>
                                                    </tr>
                                                </tbody></table>
                                            </div>
                                        </div>
                                    </div>
                                
                                    <div class="load-warpDiv computer" style="display: none;">
                                        <div class="load-imgDiv">
                                            <a id="rptDN_ctl02_linkImgIco" href="http://fenxiao.liankai.com/Files/FenXiaoPlugin/IE8.rar"><img id="rptDN_ctl02_imgIco" src="xiazaizhongxin-03.png" style="width:150px;border-width:0px;"></a>

                                            <span id="rptDN_ctl02_lblUrl" style="display: none">http://fenxiao.liankai.com/Files/FenXiaoPlugin/IE8.rar</span>
                                            <span id="rptDN_ctl02_lblPicPath" style="display: none">xiazaizhongxin-03.png</span>
                                        </div>
                                        <div class="load-titleDiv">
                                            <span id="rptDN_ctl02_lblName">IE8 For XP</span>
                                        </div>
                                        <div class="load-remarkDiv">
                                            XP系统IE8浏览器
                                        </div>
                                        <div class="load-botomDiv">
                                            <div class="load-botomDiv-left">
                                                <img src="xiazaizhongxin-diannao.png">
                                                &nbsp;
                                            </div>
                                            <div class="load-botomDiv-right" style="color: #AAAAAA; text-align: right;">
                                                <table>
                                                    <tbody><tr>
                                                        <td>
                                                            <a id="rptDN_ctl02_ImgDownload" href="http://fenxiao.liankai.com/Files/FenXiaoPlugin/IE8.rar"><img src="xiazaizhongxin-xiazai.png" alt="" style="border-width:0px;"></a>
                                                        </td>
                                                        <td>
                                                            <a id="rptDN_ctl02_linkDowLoad" href="http://fenxiao.liankai.com/Files/FenXiaoPlugin/IE8.rar" style="color:#AAAAAA;">下载</a>
                                                        </td>
                                                    </tr>
                                                </tbody></table>
                                            </div>
                                        </div>
                                    </div>
                        </div>
                    </div>
                </td>
            </tr>
        </tbody>
        </table>
    </form>
</body>
</html>