<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>

<script type="text/javascript">
    $(document).ready(function () {
        $.fn_Left_Active = function () {
            var currentPathname = location.pathname;
            var pathArr = currentPathname.split('/');
            /*console.log(pathArr[2]);*/
            var path = currentPathname.substring(0, currentPathname.lastIndexOf("/"));
            /* console.log(path); */

            var leftMenu = $("#snbMenu");
            leftMenu.find("> li").each(function () {
                var this_ = $(this);
                this_.find(">a").removeClass("active");
            });

            leftMenu.find("> li").each(function () {
                var this_ = $(this);
                if (this_.find(">a").attr("href").lastIndexOf(path) > -1) {
                    this_.find(">a").addClass("active");
                }

                this_.find("> ul > li").each(function () {
                    var thisSub_ = $(this);
                    var subUrl = thisSub_.find(">a").attr("href");
                    var subPathArr = subUrl.split('/');

                    thisSub_.find("> ul > li").each(function (i, v) {
                        var thisSub_sub = $(this);
                        var subUrl_sub = thisSub_sub.find(">a").attr("href");
                        var subPathArr_sub = subUrl_sub.split('/');
                        if (subPathArr_sub[2] == pathArr[2]) {
                            thisSub_.find("> ul").show();
                            this_.find("> ul").show();
                            thisSub_.find(">a").addClass("active");
                        }
                        console.log("path = " + path)
                        if (subUrl_sub.indexOf("/celloSquareAdmin/helpSupport") > -1) {
                            if ((getQueryString("topId")) == i) {
                                thisSub_.find(">a").addClass("active");
                                thisSub_sub.find(">a").addClass("active");
                                thisSub_.find("> ul").show();
                                this_.find("> ul").show();
                            }
                        } else if (path.indexOf("/celloSquareAdmin/terminology") > -1) {
                            if (4 == i) {
                                thisSub_.find(">a").addClass("active");
                                thisSub_sub.find(">a").addClass("active");
                                thisSub_.find("> ul").show();
                                this_.find("> ul").show();
                            }

                        }
                        if (path.indexOf("/celloSquareAdmin/helpSupportMenu") > -1) {
                            console.log("i = " + i)
                            if (0 == i) {
                                thisSub_.find(">a").addClass("active");
                                thisSub_sub.find(">a").addClass("active");
                                thisSub_.find("> ul").show();
                                this_.find("> ul").show();
                            }
                        }

                    })

                    if (subPathArr[2] == pathArr[2]) {
                        console.log("subPathArr[2]", subPathArr[2])
                        console.log("pathArr[2]", pathArr[2])
                        console.log("picType", $("#picType").val())
                        console.log("productCtgry", $("#productCtgry").val())

                        if ("/celloSquareAdmin/products".lastIndexOf(path) > -1 || "/celloSquareAdmin/middle".lastIndexOf(path) > -1) {	// 상품일 경우

                            this_.find(">a").addClass("active");
                            this_.find("> ul").show();
                            if (thisSub_.find(">a").attr("href").lastIndexOf($("#productCtgry").val()) > -1
                                || thisSub_.find(">a").attr("href").lastIndexOf($("#picType").val()) > -1) {
                                thisSub_.find(">a").addClass("active");
                            }
                        } else {
                            this_.find(">a").addClass("active");
                            this_.find("> ul").show();
                            thisSub_.find(">a").addClass("active");
                        }
                        return;
                    }
                });
            });
        };

        $.fn_Left_Active();
    });

    function getQueryString(name) {
        var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
        var r = window.location.search.substr(1).match(reg);
        if (r != null) {
            return unescape(r[2]);
        }
        return null;
    }

</script>


<ul id="snbMenu">
    <li><a href="#">车队管理系统</a>
        <ul class="smenu" id="smenu">
            <li><a href="<c:url value='/celloSquareAdmin/article/list.do' />">车队信息管理</a></li>
            <li><a href="<c:url value='/celloSquareAdmin/corporateActivities/list.do' />">驾驶员信息管理</a></li>
            <li><a href="<c:url value='/celloSquareAdmin/logisticsQa/list.do' />">SIM卡管理</a></li>
<%--            <li><a href="<c:url value='/celloSquareAdmin/antistop/list.do' />">关键词</a></li>--%>
            <li><a href="<c:url value='/celloSquareAdmin/seoInnerChain/list.do' />">终端信息管理</a></li>
            <li><a href="<c:url value='/celloSquareAdmin/hotRecommend/list.do' />">车辆信息管理</a></li>
        </ul>
    </li>

    <li><a href="#">运营管理功能</a>
        <ul class="smenu">
            <li><a href="<c:url value='/celloSquareAdmin/middle/list.do?picType=1' />">运输计划管理</a></li>
            <li><a href="<c:url value='/celloSquareAdmin/middle/list.do?picType=2' />">报价管理</a></li>
        </ul>
    </li>
    <li><a href="#">运输管理功能</a>
        <ul class="smenu">
            <%--            <li><a href="<c:url value='/celloSquareAdmin/route/list.do' />">线路管理</a></li>--%>
            <li><a href="<c:url value='/celloSquareAdmin/continent/list.do' />">需求管理</a></li>
            <li><a href="<c:url value='/celloSquareAdmin/country/list.do' />">交易管理</a></li>
            <li><a href="<c:url value='/celloSquareAdmin/node/list.do' />">货物监控</a></li>
<%--            <li><a href="<c:url value='/celloSquareAdmin/returnReason/list.do' />">退出原因</a></li>--%>
            <li><a href="<c:url value='/celloSquareAdmin/estimate/list.do' />">运输单管理</a></li>
        </ul>
    </li>
    <!--  <li><a href="<c:url value='/celloSquareAdmin/manager/list.do' />">Site<br />Management</a> -->
    <li><a href="#">统计管理</a>
        <ul class="smenu">
            <%--            <li><a href="<c:url value='/celloSquareAdmin/counselling/list.do' />">用户咨询信息</a></li>--%>
            <li><a href="<c:url value='/celloSquareAdmin/activitiesRegistration/list.do' />">活动报名统计</a></li>
            <li><a href="<c:url value='/celloSquareAdmin/registerButton/list.do' />">注册按钮点击</a></li>
            <li><a href="<c:url value='/celloSquareAdmin/registerAppSuccess/list.do' />">注册成功统计</a></li>
                <li><a href="<c:url value='/celloSquareAdmin/clickStatistics/list.do' />">按钮点击统计</a></li>
        </ul>
    </li>
    <li><a href="#">特价舱管理</a>
        <ul class="smenu">
            <li><a href="<c:url value='/celloSquareAdmin/bargainProduct/list.do' />">特价舱产品</a></li>
            <li><a href="<c:url value='/celloSquareAdmin/bargain/list.do' />">特价舱线路</a></li>
        </ul>
    </li>
    <li><a href="#">网站管理</a>
        <ul class="smenu">
            <c:if test="${SESSION_FORM_ADMIN.adminSeCode eq 'TA'}">
                <li><a href="<c:url value='/celloSquareAdmin/manager/list.do' />">管理员账号管理</a></li>
            </c:if>
            <%--			<li><a href="<c:url value='/celloSquareAdmin/popup/list.do' />">弹窗管理</a></li>--%>
            <li><a href="<c:url value='/celloSquareAdmin/terms/list.do' />">服务与条款</a></li>
            <li><a href="<c:url value='/celloSquareAdmin/fromSource/list.do' />">来源维护</a></li>
            <c:if test="${SESSION_FORM_ADMIN.adminSeCode eq 'TA'}">
                <li><a href="<c:url value='/celloSquareAdmin/apiHistory/list.do' />">api日志</a></li>
            </c:if>
        </ul>
    </li>
</ul>
