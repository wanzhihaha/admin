<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>

<form id="writeForm" name="writeForm" method="post" action="./register.do" enctype="multipart/form-data">
    <input type="hidden" name="page" value="<c:out value="${vo.page }" />">
    <input type="hidden" name="rowPerPage" value="<c:out value="${vo.rowPerPage }" />">
    <input type="hidden" id="id" name="id" value="<c:out value="${detail.id }" />">


    <section>
        <c:choose>
            <c:when test="${!empty detail.id }">
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
                        <th scope="row">推送类型*</th>
                        <td>
                            <select id="type" name="type" onchange="autogenterLiuShui(this.value)">
                                <%--                                <option value="">请选择</option>--%>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">fromource值<br>(自动生成)</th>
                        <td>
                            <input id="sourceVal" name="sourceVal" type="text" class="inp-field widL"
                                   onkeyup="this.value=this.value.replace(/[^\w_]/g,'');" onchange="getgenertlink()"
                                   value="<c:out value="${detail.sourceVal }" escapeXml="false"/>"
                                   maxlength="128" data-vbyte="128" data-vmsg="sourceVal"/>
                        </td>
                    </tr>
                    <tr style="display:none" id="identifier_z_a">
                        <th scope="row">百度<br>注册按钮标识符*</th>
                        <td>
                            <div class="textCont">
                                <input id="identifier" name="identifier" type="text" placeholder="请输入标识符."
                                       onkeyup="this.value=this.value.replace(/[^\w_]/g,'');"
                                       class="inp-field widL"
                                       value="<c:out value="${detail.identifier }" escapeXml="false"/>" maxlength="64"
                                       data-vbyte="64" data-vmsg="identifier"/>
                            </div>
                        </td>
                    </tr>
                    <tr style="display:none" id="identifier_z_c">
                        <th scope="row">百度<br>app注册成功标识符*</th>
                        <td>
                            <input id="appSuccessIdentifier" name="appSuccessIdentifier" type="text"
                                   placeholder="请输入标识符." onkeyup="this.value=this.value.replace(/[^\w_]/g,'');"
                                   class="inp-field widL"
                                   value="<c:out value="${detail.appSuccessIdentifier }" escapeXml="false"/>"
                                   maxlength="64" data-vbyte="64" data-vmsg="appSuccessIdentifier"/>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">名称</th>
                        <td>
                            <input id="name" name="name" type="text" placeholder="请输入"
                                   class="inp-field widL" value="<c:out value="${detail.name }" escapeXml="false"/>"
                                   maxlength="64" data-vbyte="128" data-vmsg="name"/>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">渠道 *</th>
                        <td>
                            <input id="remark" name="remark" type="text" placeholder="请输入"
                                   class="inp-field widL" value="<c:out value="${detail.remark }" escapeXml="false"/>"
                                   maxlength="256" data-vbyte="256" data-vmsg="remark"/>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">官网URL</th>
                        <td>
                            <input id="shareLink" name="shareLink" type="text"
                                   placeholder="不填写则按照官网主页链接生成"
                                   class="inp-field widL" onchange="getgenertlink()"
                                   value="<c:out value="${detail.shareLink }" escapeXml="false"/>"
                                   maxlength="256" data-vbyte="256" data-vmsg="shareLink"/>
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
        autogenterLiuShui(3)
        $.fnGetCodeSelectAjax("sGb=REGISTER_STATISTICS_CODE&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "type", "${detail.type}", "selectbox", "");

        $("#btnSave").click(function () {

            if ($.trim($("#type").val()) == "") {
                alert("请选择类型");
                $("#type").focus();
                return false;
            }
            if ($.trim($("#remark").val()) == "") {
                alert("请输入渠道");
                $("#remark").focus();
                return false;
            }
            if ($("#type").val() == 1) {
                if ($.trim($("#identifier").val()) == "") {
                    alert("请输入标识符.");
                    $("#identifier").focus();
                    return false;
                }

                if ($.trim($("#appSuccessIdentifier").val()) == "") {
                    alert("请输入注册成功标识符.");
                    $("#appSuccessIdentifier").focus();
                    return false;
                }

                if ($.trim($("#identifier").val()) == $.trim($("#appSuccessIdentifier").val())) {
                    alert("按钮点击标识符与注册成功标识符重复.");

                    return false;
                }
            }
            // form byte check
            if (formByteCheck() == false) {
                return false;
            }
            <c:choose>
            <c:when test="${detail.id == null || detail.id == ''}">
            $("#writeForm").attr('action', '<c:url value="./register.do"/>').submit();
            </c:when>
            <c:otherwise>
            $("#writeForm").attr('action', '<c:url value="./update.do"/>').submit();
            </c:otherwise>
            </c:choose>
        });
    });

    function autogenterLiuShui(val) {
        if ($("#sourceVal").val() && $("#id").val()) {
            return;
        }
        if ("" == val) {
            $("#sourceVal").val("")
            return
        }
        var temp = "";
        if (1 == val) {//百度
            temp = 'baidu_';
            $("#identifier_z_a").show()
            $("#identifier_z_c").show()
        } else if (2 == val) {
            temp = 'growing_io_';
        } else if (3 == val) {
            temp = 'guanwang_';
            $("#identifier_z_a").hide()
            $("#identifier_z_c").hide()
        }
        //生成随机数
        var code = '';
        for (var i = 0; i < 6; i++) {
            code += parseInt(Math.random() * 10);
        }
        temp += code;
        $("#sourceVal").val(temp)
    }

    $("#btnList").on("click", function () {
        cancel_backUp("id", "writeForm");
    });

    function getgenertlink() {
        const input = $('#shareLink'); // 获取input表单的值
        if (!input.val()) {
            return;
        }
        var sourceVal = $("#sourceVal").val();
        const url = new URL(input.val());
        if (url.searchParams.has('from_source')) {
            url.searchParams.delete('from_source');
        }
        url.searchParams.set('from_source', sourceVal);
        input.val(url.href);
    }
</script>		
							
							