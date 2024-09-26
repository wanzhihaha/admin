<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>

<form id="detailForm" name="detailForm" method="post" action="./updateForm.do">
    <input type="hidden" name="searchValue" value="<c:out value="${vo.searchValue }" />">
    <input type="hidden" name="searchType" value="<c:out value="${vo.searchType }" />">
    <input type="hidden" name="page" value="<c:out value="${vo.page }" />">
    <input type="hidden" name="rowPerPage" value="<c:out value="${vo.rowPerPage }" />">
    <input type="hidden" id="mvBannerSeqNo" name="mvBannerSeqNo" value="<c:out value="${detail.mvBannerSeqNo }" />">
    <input type="hidden" id="picType" name="picType" value="<c:out value="${vo.picType }" />">
    <input type="hidden" id="carouselType" name="carouselType" value="<c:out value="${vo.carouselType }"/>" />

    <section>
        <div class="title"><h3>详情</h3></div>
        <article>
            <div class="Cont_place">
                <table class="bd-form" summary="등록, 수정하는 영역 입니다.">
                    <caption>열람,내용</caption>
                    <colgroup>
                        <col width="200px"/>
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
                        <td>
                            <c:out value="${detail.carouselTypeName }" escapeXml="false"/>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">标题</th>
                        <td>
                            <c:out value="${detail.titleNm }" escapeXml="false"/>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">内容</th>
                        <td>
                            <c:out value="${detail.content }" escapeXml="false"/>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">时间</th>
                        <td>
                            <c:out value="${detail.bannerOpenStatDate}"/> ~ <c:out value="${detail.bannerOpenEndDate}"/>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">链接</th>
                        <td>
                            <c:out value="${detail.bannerUrl}" escapeXml="false"/>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">PC 图片</th>
                        <td>
                            <c:if test="${!empty detail.pcListImgPath }">
                                <img alt="<c:out value="${detail.pcListImgAlt }" escapeXml="false"/>"
                                     src="<blabProperty:value key="system.admin.path"/>/mvBannerImgView.do?mvBannerSeqNo=${detail.mvBannerSeqNo }&imgKinds=pcList"
                                     style="max-width: 100px; vertical-align: middle;">
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">Mobile 图片</th>
                        <td>
                            <c:if test="${!empty detail.mobileListImgPath }">
                                <img alt="<c:out value="${detail.mobileListImgAlt }" escapeXml="false"/>"
                                     src="<blabProperty:value key="system.admin.path"/>/mvBannerImgView.do?mvBannerSeqNo=${detail.mvBannerSeqNo }&imgKinds=mobileList"
                                     style="max-width: 100px; vertical-align: middle;">
                            </c:if>
                        </td>
                    </tr>

                  <tr>
                        <th scope="row">Color table</th>
                        <td>
                            #<c:out value="${detail.bkgrColor}" escapeXml="false"/>
                        </td>
                    </tr>

                    <tr>
                        <th scope="row">创建人</th>
                        <td>
                            <c:out value="${detail.insPersonNm}"/>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">创建时间</th>
                        <td>
                            <c:out value="${detail.insDtm}"/>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">状态</th>
                        <td>
                            <c:out value="${detail.useYnNm}"/>
                        </td>
                    </tr>

                </table>

                <div class="btn-module mgtS">
                    <div class="leftGroup">
                        <a href="javascript:;" id="btnList" class="btnStyle01">列表</a>
                    </div>
                    <div class="rightGroup">
                        <a href="javascript:;" id="btnUpdate" class="btnStyle01">修改</a>
                        <a href="javascript:;" id="btnDelete" class="btnStyle02">删除</a>
                    </div>
                </div>
            </div>
        </article>
    </section>
</form>


<script type="text/javascript">
    $(document).ready(function () {

        $("#btnList").click(function () {
            $("#detailForm").prop("method", "get");
            $("#detailForm").attr("action", "<c:url value ='./list.do'/>").submit();
        });

        $("#btnUpdate").click(function () {
            $("#detailForm").prop("method", "post");
            $("#detailForm").attr('action', '<c:url value="./updateForm.do"/>').submit();
        });

        $("#btnDelete").click(function () {
            if (confirm("Are you sure you want to delete it?")) {
                $("#detailForm").prop("method", "post");
                $("#detailForm").attr('action', '<c:url value="./doDelete.do"/>').submit();
            }
        });

    });

</script>		
							
							