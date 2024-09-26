package main.java.com.cellosquare.adminapp.common.util;

import cn.hutool.core.util.StrUtil;
import com.cellosquare.adminapp.common.vo.BaseSeoParam;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class SeoUtils {

    //后缀
    private static final String H_Z = "-琴路捷Cello Square";
    private static final String S_L_H = "...";

    /**
     * 获取摘要
     *
     * @return
     */
    public static String getSummaryInfo(String SummaryInfo, String contents) {
        String contents_new = delSpliChar(contents);
        return StringUtils.isNotEmpty(SummaryInfo) ? SummaryInfo : getText(contents_new);
    }

    /**
     * 去除特殊字符
     *
     * @param contents
     * @return
     */
    private static String delSpliChar(String contents) {
        if (StrUtil.isEmpty(contents)) {
            return contents;
        }
        return contents.replaceAll(com.cellosquare.adminapp.common.constant.TextConstant.START_MARK_CHART, StringUtils.EMPTY).replaceAll(com.cellosquare.adminapp.common.constant.TextConstant.END_MARK_CHART, StringUtils.EMPTY);
    }

    /**
     * 获取摘要
     *
     * @param contents
     * @param addOrUpdate 1是新增  2是修改
     * @return
     */
//    public static String getSummaryInfo(String contents, Integer addOrUpdate) {
//        return getText(contents);
//    }

    /**
     * 文本内容截取
     *
     * @param contents
     * @return
     */
    private static String getText(String contents) {
        if (StringUtils.isEmpty(contents)) {
            return StringUtils.EMPTY;
        }
        String result = StringEscapeUtils.unescapeXml(contents);
        Document document = Jsoup.parse(result);
        String text = document.text();
        if (text.length() > 100) {
            return text.substring(0, 100) + S_L_H;
        }
        return text;
    }

    /**
     * 拼接术语
     *
     * @param list
     * @param count
     * @param split
     * @return
     */
    private static String getTer(List<String> list, Integer count, String split) {
        if (CollectionUtils.isEmpty(list)) {
            return StringUtils.EMPTY;
        }
        return list.stream().limit(count).collect(Collectors.joining(split));
    }

    private static String getTer(String name, List<String> list, Integer count, String split) {
        if (CollectionUtils.isEmpty(list)) {
            return name;
        }
        List<String> new_list = new ArrayList<>(list);
        new_list.add(0, name);
        List<String> result = new_list.stream().distinct().collect(Collectors.toList());
        return result.stream().limit(count).collect(Collectors.joining(split));
    }

    /**
     * 设置seo信息
     *
     * @param baseSeoParam
     */
    public static void setSeoMsg(BaseSeoParam baseSeoParam) {
        String contents = baseSeoParam.getContents();
        //去除特殊字符
        String contents_new = delSpliChar(contents);
        com.cellosquare.adminapp.admin.seo.vo.AdminSeoVO adminSeoVO = baseSeoParam.getAdminSeoVO();
        String name = baseSeoParam.getName();
        String description = baseSeoParam.getDescription();
        List<String> terminologyTags = baseSeoParam.getTerminologyTags();

        String description_result = getText(description);
        String contents_result = getText(contents_new);
        String seoModuleCode = baseSeoParam.getSeoModuleCode();

        switch (com.cellosquare.adminapp.common.constant.SeoModuleEnum.getEnumByCode(seoModuleCode)) {
            case LOGISTICSQA://物流问答
            case ARTICLE: {//文章
                if (StringUtils.isEmpty(adminSeoVO.getMetaTitleNm())) {
                    adminSeoVO.setMetaTitleNm(name + H_Z);
                }
                if (StringUtils.isEmpty(adminSeoVO.getKeyWords())) {
                    adminSeoVO.setKeyWords(getTer(terminologyTags, 5, ","));
                }
                if (StringUtils.isEmpty(adminSeoVO.getMetaDesc())) {
                    if (com.cellosquare.adminapp.common.constant.SeoModuleEnum.LOGISTICSQA.getCode().equals(seoModuleCode)) {
                        adminSeoVO.setMetaDesc(description_result);
                    } else {
                        adminSeoVO.setMetaDesc(contents_result);
                    }
                }
                break;
            }
            case HELPSUPPORT://帮助与支持
            case CORPORATEACTIVITIES: {//活动
                if (StringUtils.isEmpty(adminSeoVO.getMetaTitleNm())) {
                    adminSeoVO.setMetaTitleNm(name + H_Z);
                }
                adminSeoVO.setKeyWords(adminSeoVO.getKeyWords());
                if (StringUtils.isEmpty(adminSeoVO.getMetaDesc())) {
                    adminSeoVO.setMetaDesc(contents_result);
                }
                break;
            }
            case TERMINOLOGY: {//术语
                if (StringUtils.isEmpty(adminSeoVO.getMetaTitleNm())) {
                    adminSeoVO.setMetaTitleNm(getTer(name, terminologyTags, 3, "_") + H_Z);
                }
                if (StringUtils.isEmpty(adminSeoVO.getKeyWords())) {
                    adminSeoVO.setKeyWords(getTer(name, terminologyTags, 5, ","));
                }
                if (StringUtils.isEmpty(adminSeoVO.getMetaDesc())) {
                    adminSeoVO.setMetaDesc(description_result);
                }
                break;
            }
            case ANTISTOP: {//关键词
                if (StringUtils.isEmpty(adminSeoVO.getMetaTitleNm())) {
                    adminSeoVO.setMetaTitleNm(getTer(name, terminologyTags, 3, "_") + H_Z);
                }
                if (StringUtils.isEmpty(adminSeoVO.getKeyWords())) {
                    adminSeoVO.setKeyWords(getTer(name, terminologyTags, 5, ","));
                }
                //规则一：调用关键词描述前100字（含标点符号）
                //规则二：琴路捷Cello Square国际物流为您分享XXXX关键词（调keywords里面的关键词）
                // 的相关物流资讯，物流活动，帮助与支持，常见物流问题等文档，快速解决物流相关问题，并提供相关资料和解决方案。
                if (StringUtils.isEmpty(adminSeoVO.getMetaDesc())) {
                    if (StringUtils.isEmpty(description_result)) {
                        adminSeoVO.setMetaDesc("琴路捷Cello Square国际物流为您分享" + name + "的相关物流资讯，物流活动，帮助与支持，常见物流问题等文档，快速解决物流相关问题，并提供相关资料和解决方案");
                    } else {
                        adminSeoVO.setMetaDesc(description_result);
                    }
                }
                break;
            }
            case PRODUCT: {//产品
                if (StringUtils.isEmpty(adminSeoVO.getMetaTitleNm())) {
                    adminSeoVO.setMetaTitleNm(name);
                }
                if (StringUtils.isEmpty(adminSeoVO.getKeyWords())) {
                    adminSeoVO.setKeyWords(name);
                }
                if (StringUtils.isEmpty(adminSeoVO.getMetaDesc())) {
                    adminSeoVO.setMetaDesc(description_result);
                }
                break;
            }
            case VIDEO: {//视频
                if (StringUtils.isEmpty(adminSeoVO.getMetaTitleNm())) {
                    adminSeoVO.setMetaTitleNm(name+H_Z);
                }
                if (StringUtils.isEmpty(adminSeoVO.getMetaDesc())) {
                    adminSeoVO.setMetaDesc(contents_result);
                }
                if (StringUtils.isEmpty(adminSeoVO.getKeyWords())) {
                    adminSeoVO.setKeyWords(name);
                }
                break;
            }
        }
    }
}
