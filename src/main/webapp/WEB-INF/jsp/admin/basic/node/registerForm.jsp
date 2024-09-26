<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>

<form id="writeForm" name="writeForm" method="post" action="./register.do" enctype="multipart/form-data">
    <input type="hidden" name="searchValue" value="<c:out value="${vo.searchValue }" />">
    <input type="hidden" name="searchType" value="<c:out value="${vo.searchType }" />">
    <input type="hidden" name="page" value="<c:out value="${vo.page }" />">
    <input type="hidden" name="rowPerPage" value="<c:out value="${vo.rowPerPage }" />">
    <input type="hidden" name="id" id="id" value="<c:out value="${detail.id }" />">
    <input type="hidden" name="continentCd" id="continentCd" value="<c:out value="${vo.continentCd }" />">
    <input type="hidden" id="searchKeyWord" name="searchKeyWord">
    <input type="hidden" id="searchType1" name="searchType1" value="<c:out value="${vo.searchType1}"/>">

    <section>
        <div class="title">
            <h3>节点
                <c:choose>
                    <c:when test="${contIU eq 'I' }">
                        新建
                    </c:when>
                    <c:otherwise>
                        修改
                    </c:otherwise>
                </c:choose>
            </h3>
        </div>
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
                            节点信息录入
                        </th>
                    </tr>
                    <tr>
                        <th scope="row">节点cd*</th>
                        <td>
                            <div class="btn-module chk">
                                <input id="nodeCd" name="nodeCd" type="text" class="inp-field widSM"
                                       <c:if test="${not empty detail.id  }">readonly="readonly" </c:if>
                                       placeholder="请输入节点值" value="<c:out value="${detail.nodeCd }" escapeXml="false"/>"
                                       maxlength="30" data-vbyte="30" data-vmsg="Sort2"/>
                            </div>
                            <span style="color: red">新增后，不可修改！，请谨慎填写（如新增错误，需删除节点重新操作）</span>
                        </td>

                    </tr>
                    <tr>
                        <th scope="row">类型*</th>
                        <td>
                            <select name="productMode" id="productMode">
                                <option value="">空</option>
                                <option <c:if test="${detail.productMode == 'AR' }"> selected="selected" </c:if>
                                        value="AR">国际空运
                                </option>
                                <option <c:if test="${detail.productMode == 'VS' }"> selected="selected" </c:if>
                                        value="VS">海运
                                </option>

                            </select>
                        </td>

                    </tr>
                    <tr>
                        <th scope="row">国家*</th>
                        <td>
                            <div class="btn-module chk">
                                <input readonly="readonly" style="background-color: #E6E6E6;" id="nationNm"
                                       name="nationNm" type="text" class="inp-field widSM" placeholder="请选择国家"
                                       value="<c:out value="${detail.nationNm }" escapeXml="false"/>" maxlength="200"
                                       data-vbyte="200" data-vmsg="Sort2"/>
                                <a href="javascript:;" id="countrySearch" class="btnStyle01">国家搜索</a>
                                <input type="hidden" id="nationSeqNo" name="nationSeqNo"
                                       value="<c:out value="${detail.nationSeqNo }" />">
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">节点英文code*</th>
                        <td>
                            <div class="btn-module chk">
                                <input id="nodeEngNm" name="nodeEngNm" type="text" class="inp-field widSM"
                                       placeholder="请输入节点英文"
                                       value="<c:out value="${detail.nodeEngNm }" escapeXml="false"/>" maxlength="100"
                                       data-vbyte="100" data-vmsg="Sort2"/>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">节点中文</th>
                        <td>
                            <div class="btn-module chk">
                                <input id="nodeCnNm" name="nodeCnNm" type="text" class="inp-field widSM"
                                       placeholder="请输入节点中文"
                                       value="<c:out value="${detail.nodeCnNm }" escapeXml="false"/>" maxlength="100"
                                       data-vbyte="100" data-vmsg="Sort2"/>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">所属城市（英文）*</th>
                        <td>
                            <div class="btn-module chk">
                                <input id="cityEngNm" name="cityEngNm" type="text" class="inp-field widSM"
                                       placeholder="可以自动填充"
                                       value="<c:out value="${detail.cityEngNm }" escapeXml="false"/>" maxlength="100"
                                       data-vbyte="100" data-vmsg="Sort2"/>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">所属城市（中文）*</th>
                        <td>
                            <div class="btn-module chk">
                                <input id="cityCnNm" name="cityCnNm"
                                       type="text" class="inp-field widSM"
                                       placeholder="请输入"
                                       value="<c:out value="${detail.cityCnNm }" escapeXml="false"/>" maxlength="100"
                                       data-vbyte="100" data-vmsg="Sort2"/>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">搜索选项值</th>
                        <td>
                            <div class="btn-module chk" id="addRowDiv">
                                <ul class="country-row-ul">

                                    <c:choose>
                                        <c:when test="${!empty detail.searchKeyWord}">
                                            <c:forEach var="item" items="${detail.listSearchKeyWord}">
                                                <li class="row-input">
                                                    <input type="text" class="inp-field widSM searchKeyWord"
                                                           placeholder="请输入选项值"
                                                           value="<c:out value="${item}" escapeXml="false"/>"
                                                           maxlength="200" data-vbyte="200" data-vmsg="Sort2"/>
                                                    <a href="javascript:;" class="btnStyle01"
                                                       style="text-indent: 0; height: 28px"
                                                       onclick="removeParent(this)">Delete</a>
                                                </li>
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            <li class="row-input">
                                                <input type="text" class="inp-field widSM searchKeyWord"
                                                       placeholder="Please enter the key word" maxlength="200"
                                                       data-vbyte="200" data-vmsg="Sort2"/>
                                                <a href="javascript:;" class="btnStyle01"
                                                   style="text-indent: 0; height: 28px"
                                                   onclick="removeParent(this)">删除</a>
                                            </li>
                                        </c:otherwise>
                                    </c:choose>


                                </ul>
                                <a href="javascript:;" id="addRow" class="btnStyle01">添加</a>
                            </div>

                        </td>
                    </tr>
                    <tr>
                        <th scope="row">状态</th>
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
        </article>
    </section>
</form>
<div class="modal" id="countryDataDiv" style="display:none;">
    <form id="nodeListAjaxForm">
        <input type="hidden" id="pageAjax" name="page" value="${vo.page}"/>
        <input type="hidden" name="rowPerPage" value="10"/>
        <div class="Cont_place modal-content">
            <i class="modal-close" id="closeCountry"></i>
            <div class="inputUI_simple">
                <table class="bd-form s-form" summary="상태, 답변 상태, 기간설정, 직접검색 영역 입니다.">
                    <caption>상태, 답변 상태, 기간설정, 직접검색 영역</caption>
                    <colgroup>
                        <col width="120px"/>
                        <col width="300px"/>
                        <col width=""/>
                    </colgroup>

                    <tr>
                        <th scope="row">查询</th>
                        <td>
                            <!-- 只有一个input，所以按enter自动提交表格； 添加一个样式隐藏的input 防止按enter键表单自动提交-->
                            <input type="text" style="display:none">
                            <input id="countrySelVal" onkeydown='if (event.keyCode==13) { fncPage(1) }' type="text"
                                   style="width: 100%" placeholder="请输入查询值."
                                   class="inp-field mglS" name="searchValue"/>
                        </td>
                        <td>
                            <div class="btn-module floatR">
                                <div class="rightGroup">
                                    <a href="javascript:;" class="search" onclick="fncPage(1);"
                                       id="btnSearch">查询</a>
                                </div>
                            </div>
                        </td>
                    </tr>
                </table>
            </div>

            <table class="bd-list inputUI_simple" summary="리스트 영역 입니다.">
                <caption>NO</caption>
                <colgroup>
                    <col width="3%"/>
                    <col width="8%"/>
                    <col width="10%"/>
                </colgroup>
                <thead>
                <tr>
                    <th scope="col">序号</th>
                    <th scope="col">国家</th>
                    <th scope="col">添加</th>
                </tr>
                </thead>
                <!-- js动态添加在这里 -->
                <tbody id="nationDataTb">

                </tbody>
            </table>

            <div class="page-module">
                <p class="paging" id="pageNationAjax">

                </p>
            </div>
        </div>
    </form>
</div>
<script type="text/javascript">


    var IU = "${contIU}";

    function removeParent(v) {
        $(v).parent().remove();
    }

    function countrySelect(name, seqNo) {
        $("#nationNm").val(name);
        $("#nationSeqNo").val(seqNo);
        $('#countryDataDiv').hide();

    }

    function initFront() {
        if (IU === 'U') {
            var source = "${detail.nodeSource}";
            console.log("source", source);
            if (source === "A") {
                var nationSeqNo = "${detail.nationSeqNo}";
                console.log("nationSeqNo", nationSeqNo)
                $('#nodeCd').attr("readonly", "readonly");
                $("#nodeCd").css("backgroundColor", "#E6E6E6");
                if (null != nationSeqNo && '' != nationSeqNo) {
                    $('#countrySearch').hide();
                    $('#nodeEngNm').attr("readonly", "readonly");
                    $("#nodeEngNm").css("backgroundColor", "#E6E6E6");
                    $('#nodeCd').attr("readonly", "readonly");
                    $("#nodeCd").css("backgroundColor", "#E6E6E6");

                } else {
                    $('#airPortNm').removeAttr("readonly");
                    $("#airPortNm").css("backgroundColor", "#FFFFFF");
                }

            }
        }
    }

    $(document).ready(function () {
        $.fnGetCodeSelectAjax("sGb=USE_YN&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "radUseCd", "${detail.useYn == null ? 'Y' : detail.useYn}", "radio", "useYn");


        initFront();
        $("#addRow").click(function () {
            var elHtml = `<li class="row-input">
								<input type="text" class="inp-field widSM searchKeyWord" placeholder="Please enter the country name" maxlength="200" data-vbyte="200" data-vmsg="Sort2"/>
								<a href="javascript:;" class="btnStyle01" style="text-indent: 0; height: 28px"  onclick="removeParent(this)">Delete</a>
							</li>`;
            $("#addRowDiv ul").append(elHtml)
        });

        $("#countrySearch").on("click", function () {
            ajaxNodeList();
            $('#countryDataDiv').show();
        });


        $("#btnSave").click(function () {

            if ($("#nodeCd").val().trim() == "") {
                alert("请输入节点cd.");
                $("#nationCd").focus();
                return false;
            }
            if ($("#productMode").val().trim() == "") {
                alert("请选择类型.");
                $("#productMode").focus();
                return false;
            }

            if ($('#nationSeqNo').val().trim() == '') {
                alert("请选择国家.");
                $("#nationNm").focus();
                return false;
            }

            if ($('#nodeEngNm').val().trim() == '') {
                alert("请输入节点英文名.");
                $("#nodeEngNm").focus();
                return false;
            }

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
                alert("The sereach option Max length 300 characters.");
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
            cancel_backUp("id","writeForm");
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

    function fncPage(page) {
        $("#pageAjax").val(page);
        ajaxNodeList();
    }

    $('#closeCountry').click(function () {
        $("#nationDataTb").children().remove();
        $("#pageNationAjax").children().remove();
        $('#countryDataDiv').hide();
    })

    function ajaxNodeList() {
        var formData = $('#nodeListAjaxForm').serialize();

        $.ajax({
            url: "./list-nation-ajaxList.do"
            , dataType: 'json'
            , data: formData
            , type: "GET"
            , contentType: "application/x-www-form-urlencoded; charset=UTF-8"
            , success: function (json) {
                $("#nationDataTb").children().remove();
                $("#pageNationAjax").children().remove();
                console.log(json);
                var resultText = ""; //html 삽입값

                $.each(json.list, function (index, list) {
                    resultText += "<tr>";
                    resultText += "<td>" + index + "</td>";
                    resultText += "<td>" + list.nationNm + "-" + list.nationCnNm + "</td>";
                    resultText += "<td>"
                    resultText += "<div class='btn-module chk'>";
                    resultText += '  <a class="btnStyle01" href="javascript:countrySelect(\'' + list.nationNm + '\', \'' + list.nationSeqNo + '\');" role="button"> Select </a>';
                    resultText += "</div>"
                    resultText += "</td>"
                    resultText += "/<tr>";
                });
                var pageText = "";
                var pageBean = json.pageBean;
                var pageText = "";
                if (pageBean.prePageListIndex > 0) {
                    pageText += '  <a class="prev10" href="javascript:fncPage(\'' + pageBean.prePageListIndex + '\');"><<</a>';
                } else {
                    pageText += '<a clas="prev10"><<</a>'
                }
                if (pageBean.prePage > 0) {
                    pageText += '  <a clas="prev" href="javascript:fncPage(\'' + pageBean.prePage + '\');"><</a>';
                } else {
                    pageText += '<a clas="prev"><</a>'
                }

                for (let i = pageBean.beginPageIndex; i <= pageBean.endPageIndex; i++) {
                    if (i === pageBean.currentPage) {
                        pageText += '  <a class = "current" href="javascript:fncPage(\'' + i + '\');">' + i + '</a>';
                    } else {
                        pageText += '  <a href="javascript:fncPage(\'' + i + '\');">' + i + '</a>';
                    }
                }
                if (pageBean.nextPage > 0) {
                    pageText += '  <a clas="prev" href="javascript:fncPage(\'' + pageBean.nextPage + '\');">></a>';
                } else {
                    pageText += '<a clas="prev">></a>'
                }
                if (pageBean.nextPageListIndex > 0) {
                    pageText += '  <a class="prev10" href="javascript:fncPage(\'' + pageBean.nextPageListIndex + '\');">>></a>';
                } else {
                    pageText += '<a clas="prev10">>></a>'
                }

                $("#nationDataTb").append(resultText);
                $("#pageNationAjax").append(pageText);
            }, error: function (xhr) {
                alert("Network Error");
            }
        });

        /*$('#countrySelVal').on("keyup", function (event){
            console.log(event.keyCode, "xxxxx");
            if(event.keyCode === 13){
                console.log("触发");
                fncPage(1);
            }
        })*/


    }
</script>		
							
