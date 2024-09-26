<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>

<form id="writeForm" name="writeForm" method="post" action="./register.do" enctype="multipart/form-data">
    <input type="hidden" name="searchValue" value="<c:out value="${vo.searchValue }" />">
    <input type="hidden" name="searchType" value="<c:out value="${vo.searchType }" />">
    <input type="hidden" name="page" value="<c:out value="${vo.page }" />">
    <input type="hidden" name="rowPerPage" value="<c:out value="${vo.rowPerPage }" />">
    <input type="hidden" id="mvBannerSeqNo" name="mvBannerSeqNo" value="<c:out value="${detail.mvBannerSeqNo }" />">
    <input type="hidden" id="picType" name="picType" value="<c:out value="${vo.picType}" />">


    <section>
        <c:choose>
            <c:when test="${!empty detail.mvBannerSeqNo }">
                <div class="title"><h3>修改</h3></div>
            </c:when>
            <c:otherwise>
                <div class="title"><h3>新增</h3></div>
            </c:otherwise>
        </c:choose>
        <article>
            <div class="Cont_place">
                <table class="bd-form s-form" summary="등록, 수정하는 영역 입니다.">
                    <caption>열람,내용</caption>
                    <colgroup>
                        <col width="130px"/>
                        <col width=""/>
                    </colgroup>
                    <tr>
                        <th colspan="2"
                            style="text-align: center; font-weight: bold; font-size: large; border-top: 1px solid; border-bottom: 1px solid;">
                            基础信息
                        </th>
                    </tr>
                    <tr>
                        <th scope="row">类型</th>
                        <c:choose>
                            <c:when test="${vo.picType ==2}">
                                <td>
                                    <select name="carouselType" id="carouselType">
                                    </select>
                                </td>
                            </c:when>
                            <c:otherwise>
                                <td>
                                    横幅广告
                                    <input type="hidden" name="carouselType" value="0"/>
                                </td>
                            </c:otherwise>
                        </c:choose>
                    </tr>
                    <tr>
                        <th scope="row">标题</th>
                        <td>
                            <input id="titleNm" name="titleNm" type="text" placeholder="请输入" class="inp-field widL"
                                   value="<c:out value="${detail.titleNm }" escapeXml="false"/>" maxlength="160"
                                   data-vbyte="160" data-vmsg="title"/>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">内容</th>
                        <td>
                            <input id="content" name="content" type="text" placeholder="请输入" class="inp-field widL"
                                   value="<c:out value="${detail.content }" escapeXml="false"/>" maxlength="160"
                                   data-vbyte="160" data-vmsg="title"/>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">时间</th>
                        <td>
                            <c:choose>
                                <c:when test="${empty detail.mvBannerSeqNo}">
                                    <input class="inp-field wid100" type="text" id="bannerOpenStatDate"
                                           name="bannerOpenStatDate" value="" readonly="readonly"/>
                                    <span>~</span>
                                    <input class="inp-field wid100" type="text" id="bannerOpenEndDate"
                                           name="bannerOpenEndDate" value="" readonly="readonly"/>
                                </c:when>
                                <c:otherwise>
                                    <input class="inp-field wid100" type="text" id="bannerOpenStatDate"
                                           name="bannerOpenStatDate"
                                           value="<c:out value='${detail.bannerOpenStatDate}'/>" readonly="readonly"/>
                                    <span>~</span>
                                    <input class="inp-field wid100" type="text" id="bannerOpenEndDate"
                                           name="bannerOpenEndDate" value="<c:out value='${detail.bannerOpenEndDate}'/>"
                                           readonly="readonly"/>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">跳转链接</th>
                        <td>
                            <div class="textCont">
                                <input id="bannerUrl" name="bannerUrl" type="text" placeholder="请输入"
                                       class="inp-field widL"
                                       value="<c:out value="${detail.bannerUrl }" escapeXml="false"/>" maxlength="240"
                                       data-vbyte="240" data-vmsg="link"/>

                            </div>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">PC 图片<br/>(1440px - 50px)</th>
                        <td>
                            <div class="textCont">
                                <div style="margin-bottom: 7px;">
                                    <input id="pcListOrginFile" name="pcListOrginFile" type="file"
                                           class="inp-field widSM"/>
                                    <c:if test="${!empty detail.pcListImgOrgFileNm}">
                                        <span style="line-height: 28px;"><a
                                                href='<blabProperty:value key="system.admin.path"/>/mvBannerDownload.do?mvBannerSeqNo=<c:out value="${detail.mvBannerSeqNo }"/>&imgKinds=pcList'
                                                style="color: blue;"><c:out value="${detail.pcListImgOrgFileNm}"
                                                                            escapeXml="false"/></a></span>
                                        <span><input type="checkbox" name="pcListFileDel" id="pcListFileDel" value="Y"/> <label
                                                for="pcListFileDel">Delete</label></span>
                                    </c:if>
                                </div>
                              <%--  <div>
                                    <input type="text" id="pcListImgAlt" name="pcListImgAlt"
                                           value="<c:out value="${detail.pcListImgAlt }" escapeXml="false"/>"
                                           placeholder="Please enter the Alt value for the image."
                                           class="inp-field widSM" maxlength="210" data-vbyte="210"
                                           data-vmsg="alt value"/>
                                    <span style="line-height: 28px;"> ※Plase enter an 'alt' value for Image.</span>
                                </div>--%>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">Mobile 图片<br/>(720px - 160px)</th>
                        <td>
                            <div class="textCont">
                                <div style="margin-bottom: 7px;">
                                    <input id="mobileListOrginFile" name="mobileListOrginFile" type="file"
                                           class="inp-field widSM"/>
                                    <c:if test="${!empty detail.mobileListImgOrgFileNm}">
                                        <span style="line-height: 28px;"><a
                                                href='<blabProperty:value key="system.admin.path"/>/mvBannerDownload.do?mvBannerSeqNo=<c:out value="${detail.mvBannerSeqNo }"/>&imgKinds=mobileList'
                                                style="color: blue;"><c:out value="${detail.mobileListImgOrgFileNm}"
                                                                            escapeXml="false"/></a></span>
                                        <span><input type="checkbox" name="mobileListFileDel" id="mobileListFileDel"
                                                     value="Y"/> <label for="mobileListFileDel">Delete</label></span>
                                    </c:if>
                                </div>
                        <%--        <div>
                                    <input type="text" id="mobileListImgAlt" name="mobileListImgAlt"
                                           value="<c:out value="${detail.mobileListImgAlt }" escapeXml="false"/>"
                                           placeholder="Please enter the Alt value for the image."
                                           class="inp-field widSM" maxlength="210" data-vbyte="210"
                                           data-vmsg="alt value"/>
                                    <span style="line-height: 28px;"> ※Plase enter an 'alt' value for Image.</span>
                                </div>--%>
                            </div>
                        </td>
                    </tr>
                     <tr>
                           <th scope="row">background color</th>
                           <td>
                               # <input id="bkgrColor" name="bkgrColor" type="text" class="inp-field wid200"
                                        value="<c:out value="${detail.bkgrColor == null ? '000000' :  detail.bkgrColor}" escapeXml="false"/>"
                                        placeholder="Please enter a background color." maxlength="24" data-vbyte="24"
                                        data-vmsg="background color"/>
                           </td>
                       </tr>
                    <tr>
                        <th scope="row">状态*</th>
                        <td>
                            <div id="radUseCd"></div>
                        </td>
                    </tr>
                    <tbody>

                    </tbody>

                </table>

                <div class="btn-module mgtS">
                    <div class="rightGroup">
                        <a id="btnSave" href="#none" class="btnStyle01">保存</a>
                        <a href="javascript:;" id="btnList" class="btnStyle01">取消</a>
                    </div>
                </div>

            </div>
        </article>
    </section>
</form>


<script type="text/javascript">
    $(document).ready(function () {
        $("#bannerOpenStatDate, #bannerOpenEndDate").datepicker({
            dateFormat: "yy-mm-dd"
            , showOn: 'button'
            , buttonImage: '/static/images/cal.png'
            , buttonImageOnly: true
            , buttonText: "달력"

        });

        $.fnGetCodeSelectAjax("sGb=USE_YN&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "radUseCd", "${detail.useYnNm == null ? 'Y' : detail.useYn}", "radio", "useYn");
        $.fnGetCodeSelectAjax("sGb=CAROUSEL_TYPE&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "carouselType", "${detail.carouselType}", "selectbox", "");


        $("#btnSave").click(function () {

            var pcListOrginFileBool = $("#pcListOrginFile").get(0).files[0]
            var mobileListOrginFileBool = $("#mobileListOrginFile").get(0).files[0]
            if (pcListOrginFileBool == null && mobileListOrginFileBool == null && "${detail.pcListImgOrgFileNm}" === "" && "${detail.mobileListImgOrgFileNm}" === "") {
                alert("图片不能全部为空");
                return false;
            }
            if ($("#pcListOrginFile").val() != "") {
                if (!/\.(gif|jpg|peg|png|jpeg)$/.test($("#pcListOrginFile").val().toLowerCase())) {
                    alert("Please select the image file (gif, jpg, bmp, png, jpeg)");
                    $("#pcListOrginFile").focus();
                    return false;
                }
            }

            if ($("#mobileListOrginFile").val() != "") {
                if (!/\.(gif|jpg|peg|png|jpeg)$/.test($("#mobileListOrginFile").val().toLowerCase())) {
                    alert("Please select the image file (gif, jpg, bmp, png, jpeg)");
                    $("#mobileListOrginFile").focus();
                    return false;
                }
            }

            // form byte check
            if (formByteCheck() == false) {
                return false;
            }
            <c:choose>
            <c:when test="${detail.mvBannerSeqNo == null}">
            $("#writeForm").attr('action', '<c:url value="./register.do"/>').submit();
            </c:when>
            <c:otherwise>
            $("#writeForm").attr('action', '<c:url value="./update.do"/>').submit();
            </c:otherwise>
            </c:choose>
        });
    });

    //날짜 밸리데이션
    $("#bannerOpenEndDate").on("change keyup paste", function () {
        var stDt = $("#bannerOpenStatDate").val();
        var stDtArr = stDt.split("-");
        var stDtObj = new Date(stDtArr[0], Number(stDtArr[1]) - 1, stDtArr[2]);
        var enDt = $("#bannerOpenEndDate").val();
        var enDtArr = enDt.split("-");
        var enDtObj = new Date(enDtArr[0], Number(enDtArr[1]) - 1, enDtArr[2]);
        var betweenDay = (enDtObj.getTime() - stDtObj.getTime()) / 1000 / 60 / 60 / 24;

        if (stDt == "" && enDt != "") {
            alert("이벤트 시작날짜를 입력해 주세요.");
            $("#bannerOpenStatDate").focus();
            $("#bannerOpenEndDate").val("");
            return false;
        }
        if (betweenDay < 0) {
            alert("이벤트 종료날짜가 시작날짜보다 이릅니다. 다시 입력해 주세요.");
            $("#bannerOpenEndDate").focus();
            $("#bannerOpenEndDate").val("");
            return false;
        }
    });

    $("#bannerOpenStatDate").on("change keyup paste", function () {
        var stDt = $("#bannerOpenStatDate").val();
        var stDtArr = stDt.split("-");
        var stDtObj = new Date(stDtArr[0], Number(stDtArr[1]) - 1, stDtArr[2]);
        var enDt = $("#bannerOpenEndDate").val();
        var enDtArr = enDt.split("-");
        var enDtObj = new Date(enDtArr[0], Number(enDtArr[1]) - 1, enDtArr[2]);
        var betweenDay = (enDtObj.getTime() - stDtObj.getTime()) / 1000 / 60 / 60 / 24;

        if (betweenDay < 0) {
            alert("이벤트 시작날짜가 종료날짜보다 후일입니다. 다시 입력해 주세요.");
            $("#bannerOpenStatDate").focus();
            $("#bannerOpenStatDate").val("");
            return false;
        }
    });

    $("#btnList").on("click", function () {
        cancel_backUp("mvBannerSeqNo", "writeForm");
    });


</script>		
							
							