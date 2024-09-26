<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>

<form id="writeForm" name="writeForm" method="post" action="./register.do" enctype="multipart/form-data">
    <input type="hidden" name="searchValue" value="<c:out value="${vo.searchValue }" />">
    <input type="hidden" name="searchType" value="<c:out value="${vo.searchType }" />">
    <input type="hidden" name="page" value="<c:out value="${vo.page }" />">
    <input type="hidden" name="rowPerPage" value="<c:out value="${vo.rowPerPage }" />">
    <input type="hidden" id="nationSeqNo" name="nationSeqNo" value="<c:out value="${detail.nationSeqNo }" />">
    <input type="hidden" id="searchKeyWord" name="searchKeyWord">

    <section>
        <div class="title"><h3>国家

            <c:choose>
                <c:when test="${contIU eq 'I' }">
                    新增
                </c:when>
                <c:otherwise>
                    修改
                </c:otherwise>
            </c:choose>

        </h3></div>
        <article>
            <div class="Cont_place">
                <table class="bd-form" summary="등록, 수정하는 영역 입니다.">
                    <caption>열람,내용</caption>
                    <colgroup>
                        <col width="200px"/>
                        <col width=""/>
                    </colgroup>


                    <tr>
                        <th colspan="10"
                            style="text-align: center; font-weight: bold; font-size: large; border-top: 1px solid; border-bottom: 1px solid;">
                            国家信息
                        </th>
                    </tr>
                    <tr>
                        <th scope="row">州/大陆</th>
                        <td>
                            <select id="continentCd" name="continentCd">
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">国家(英文)</th>
                        <td>
                            <div class="btn-module chk">
                                <input id="nationNm" name="nationNm" type="text" class="inp-field widSM"
                                       placeholder="请输入"
                                       value="<c:out value="${detail.nationNm }" escapeXml="false"/>" maxlength="200"
                                       data-vbyte="200" data-vmsg="Sort2"/>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">国家(中文)</th>
                        <td>
                            <div class="btn-module chk">
                                <input id="nationCnNm" name="nationCnNm" type="text" class="inp-field widSM"
                                       placeholder="请输入"
                                       value="<c:out value="${detail.nationCnNm }" escapeXml="false"/>" maxlength="200"
                                       data-vbyte="200" data-vmsg="Sort2"/>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">国家 code</th>
                        <td>
                            <div class="btn-module chk">
                                <input id="nationCd" name="nationCd" type="text" class="inp-field widSM"
                                       placeholder="请输入"
                                       value="<c:out value="${detail.nationCd }" escapeXml="false"/>" maxlength="5"
                                       data-vbyte="5" data-vmsg="Sort2"/>
                                <a href="javascript:;" id="chkId" class="btnStyle01">Double Check</a>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">图标</th>
                        <td>
                            <div style="margin-bottom: 7px;">
                                <input type="file" id="imgFileUpload" name="imgFileUpload" class="inp-field widSM"/>
                                <c:if test="${!empty detail.imgOrgFileNm}">
								<span style="line-height: 28px;">
									<a href="<blabProperty:value key="system.admin.path"/>/country/ImgDownload.do?nationSeqNo=<c:out value='${detail.nationSeqNo}'/>"><font
                                            color="blue"><c:out value="${detail.imgOrgFileNm}"/></font></a>
								</span>
                                </c:if>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">是否支持快递(跨境小包)</th>
                        <td>
                            <div id="deliveryId"></div>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">搜索选项</th>
                        <td>
                            <div class="btn-module chk" id="addRowDiv">
                                <ul class="country-row-ul">

                                    <c:choose>
                                        <c:when test="${!empty detail.searchKeyWord}">
                                            <c:forEach var="item" items="${detail.listSearchKeyWord}">
                                                <li class="row-input">
                                                    <input type="text" class="inp-field widSM searchKeyWord"
                                                           placeholder="请输入"
                                                           value="<c:out value="${item}" escapeXml="false"/>"
                                                           maxlength="200" data-vbyte="200" data-vmsg="Sort2"/>
                                                    <a href="javascript:;" class="btnStyle01"
                                                       style="text-indent: 0; height: 28px"
                                                       onclick="removeParent(this)">删除</a>
                                                </li>
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            <li class="row-input">
                                                <input type="text" class="inp-field widSM searchKeyWord"
                                                       placeholder="请输入" maxlength="200"
                                                       data-vbyte="200" data-vmsg="Sort2"/>
                                                <a href="javascript:;" class="btnStyle01"
                                                   style="text-indent: 0; height: 28px" onclick="removeParent(this)">删除</a>
                                            </li>
                                        </c:otherwise>
                                    </c:choose>


                                </ul>
                                <a href="javascript:;" id="addRow" class="btnStyle01">添加</a>
                            </div>

                        </td>
                    </tr>
                    <tr>
                        <th scope="row">状态<span style="color: red">（影响范围：该国家所有运输地址）</span></th>

                        <td>
                            <div id="radUseCd"></div>

                        </td>
                    </tr>
                    <tbody>

                    </tbody>

                </table>

                <div class="btn-module mgtS">
                    <div class="rightGroup">
                        <c:choose>
                            <c:when test="${contIU eq 'I' }">
                                <a href="javascript:;" id="btnSave" class="btnStyle01">保存</a>
                            </c:when>
                            <c:otherwise>
                                <a href="javascript:;" id="btnSave" class="btnStyle01">保存</a>
                            </c:otherwise>
                        </c:choose>
                        <a href="javascript:;" id="btnList" class="btnStyle01">取消</a>
                    </div>
                </div>
            </div>
            </div>
        </article>
    </section>
</form>


<script type="text/javascript">
    var chk = false;
    var IU = "${contIU}";

    function removeParent(v) {
        $(v).parent().remove();
    }

    $(document).ready(function () {
        $.fnGetCodeSelectAjax("sGb=USE_YN&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "radUseCd", "${detail.useYn == null ? 'Y' : detail.useYn}", "radio", "useYn");
        $.fnGetCodeSelectAjax("sGb=CONTINENT_CD&langCd=A", "continentCd", "${detail.continentCd}", "selectbox", "");
        $.fnGetCodeSelectAjax("sGb=EXP_USE_YN&langCd=A", "deliveryId", "${detail.expressUseYn == null ? 'Y' : detail.expressUseYn}", "radio", "expressUseYn");
        $("#addRow").click(function () {
            var elHtml = `<li class="row-input">
								<input type="text" class="inp-field widSM searchKeyWord" placeholder="请输入" maxlength="200" data-vbyte="200" data-vmsg="Sort2"/>
								<a href="javascript:;" class="btnStyle01" style="text-indent: 0; height: 28px"  onclick="removeParent(this)">Delete</a>
							</li>`;
            $("#addRowDiv ul").append(elHtml)
        });

        $("#chkId").on("click", function () {
            if ($("#nationNm").val().trim() == "") {
                alert("请输入国家名.");
                $("#nationNm").focus();
                return false;
            }
            if ($("#nationNm").val().length > 50) {
                alert("国家名超出50字符");
                $("#nationNm").focus();
                return false;
            }

            if ($("#nationCd").val().trim() == "") {
                alert("请输入国家 code.");
                $("#nationCd").focus();
                return false;
            }
            if ($("#nationCd").val().length > 5) {
                alert("国家 code 超出5个字符");
                $("#nationNm").focus();
                return false;
            }
            console.log("nationCd", "${detail.nationCd }")
            console.log("nationCd", $("#nationCd").val())

            $.ajax({
                url: "<c:url value='/celloSquareAdmin/country/checkCountry.do' />",
                data: {
                    /*nationNm:$("#nationNm").val().trim(),*/
                    nationCd: $("#nationCd").val().trim(),
                    nationSeqNo: $("#nationSeqNo").val().trim()
                },
                type: "POST",
                dataType: "text",
                success: function (message) {

                    if (message == "success") {
                        alert("无重复记录");
                        chk = true;
                        $("#nationNm").on("propertychange change keyup paste input", function () {
                            chk = false;
                        });
                        $("#nationCd").on("propertychange change keyup paste input", function () {
                            chk = false;
                        });
                    } else {
                        chk = false;
                        alert("已有相同的国家code存在，请修改");
                        $("#nationCd").focus();
                    }
                }, error: function (request, status, error) {
                    alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
                }
            });
        });


        $("#btnSave").click(function () {
            if (IU === 'U' && $("#nationCd").val() === "${detail.nationCd }") {
                chk = true;
            }

            if (!chk) {
                alert("请检查国家code是否重复")
                return false;
            }


            var imgNm = "${detail.imgOrgFileNm}";
          /*  if ($.trim($("#imgFileUpload").val()) == "" && imgNm == "") {
                alert("Please select the Flag Image.");
                $("#imgFileUpload").focus();
                return false;
            }


            if ($("#imgFileUpload").val()) {
                var firstAttachFileName = document.getElementById("imgFileUpload").name;
                var fileName = firstAttachFileName.substring(0, firstAttachFileName.lastIndexOf("."));
                if (checkNotEnglishChar(fileName)) {
                    alert("The file name must be english.");
                    $("#imgFileUpload").focus();
                    return false;
                }
            }*/

            var searchKeyWord = "";
            console.log($(".searchKeyWord").length)
            $(".searchKeyWord").each(function (i, o) {
                var val = $(o).val().trim();
                if (i == $(".searchKeyWord").length - 1) {
                    searchKeyWord += val
                } else {
                    searchKeyWord += val + ","
                }

            })
            if (searchKeyWord.length > 300) {
                alert("搜索选项值最大为300字符");
                return false;
            }
            $("#searchKeyWord").val(searchKeyWord);
            <c:choose>
            <c:when test="${contIU eq 'I' }">
            $("#writeForm").prop("action", "register.do");
            $("#writeForm").submit();
            </c:when>
            <c:otherwise>
            $("#writeForm").prop("action", "update.do");
            $("#writeForm").submit();
            </c:otherwise>
            </c:choose>
        });

        $("#btnList").on("click", function () {
            cancel_backUp("nationSeqNo","writeForm");
        });
    });

    function checkNotEnglishChar(str) {
        for (i = 0; i < str.length; i++) {
            if (((str.charCodeAt(i) > 0x3130 && str.charCodeAt(i) < 0x318F) || (str.charCodeAt(i) >= 0xAC00 && str.charCodeAt(i) <= 0xD7A3) || (str.charCodeAt(i) >= 0x4e00 && str.charCodeAt(i) <= 0x9fa5) || (str.charCodeAt(i) >= 0x0800 && str.charCodeAt(i) <= 0x4e00))) {
                return true;
            }
        }
        return false;
    }
</script>		
							
