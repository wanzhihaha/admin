package com.cellosquare.adminapp.admin.seoInnerChain.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.EscapeUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.cellosquare.adminapp.admin.activities.entity.CorporateActivities;
import com.cellosquare.adminapp.admin.activities.service.impl.CorporateActivitiesServiceImpl;
import com.cellosquare.adminapp.admin.article.service.impl.ArticleServiceImpl;
import com.cellosquare.adminapp.admin.article.vo.Article;
import com.cellosquare.adminapp.admin.helpSupport.entity.HelpSupport;
import com.cellosquare.adminapp.admin.helpSupport.service.impl.HelpSupportServiceImpl;
import com.cellosquare.adminapp.admin.logisticsQa.entity.LogisticsQa;
import com.cellosquare.adminapp.admin.logisticsQa.service.impl.LogisticsQaServiceImpl;
import com.cellosquare.adminapp.admin.seoInnerChain.conver.SeoInnerChainConver;
import com.cellosquare.adminapp.admin.seoInnerChain.entity.SeoInnerChain;
import com.cellosquare.adminapp.admin.seoInnerChain.mapper.SeoInnerChainMapper;
import com.cellosquare.adminapp.admin.seoInnerChain.service.ISeoInnerChainService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cellosquare.adminapp.admin.seoInnerChain.vo.SeoInnerChainVO;
import com.cellosquare.adminapp.common.enums.EnableFlagEnum;
import com.cellosquare.adminapp.common.enums.LinkYnEnum;
import com.cellosquare.adminapp.common.enums.TermTypeEnum;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.poi.ss.formula.functions.T;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author walker
 * @since 2023-03-66 09:03:29
 */
@Service
public class SeoInnerChainServiceImpl extends ServiceImpl<SeoInnerChainMapper, SeoInnerChain> implements ISeoInnerChainService {
    private final String linkFormat = "<a href=\"%s\">%s</a>";
    @Autowired
    private ArticleServiceImpl articleService;
    @Autowired
    private CorporateActivitiesServiceImpl corporateActivitiesService;
    @Autowired
    private LogisticsQaServiceImpl logisticsQaService;
    @Autowired
    private HelpSupportServiceImpl helpSupportService;

    public Boolean register(HttpServletRequest request, HttpServletResponse response, SeoInnerChainVO vo, MultipartHttpServletRequest muServletRequest) {
        SeoInnerChain seoInnerChain = SeoInnerChainConver.INSTANCT.getSeoInnerChain(vo);
        //行业咨询
        if (TermTypeEnum.COMPANY_NEWS.getCode().equals(vo.getType())) {
            LambdaQueryWrapper<Article> seoInnerChain1 = getSeoInnerChain(seoInnerChain, Article::getArticleContent, Article::getCreateDate, Article::getLinkYn);
            List<Article> articles = articleService.list(seoInnerChain1);
            if (CollectionUtil.isNotEmpty(articles)) {
                articles.forEach(r->buildLink(r.getArticleContent(),seoInnerChain,Article::setArticleContent,r,Article::setLinkYn));
                seoInnerChain.setCount(articles.size());
                articleService.updateBatchById(articles);
            }
        }else if (TermTypeEnum.CROSS_BORDER.getCode().equals(vo.getType())) {
            //公司活动
            LambdaQueryWrapper<CorporateActivities> seoInnerChain1 = getSeoInnerChain(seoInnerChain, CorporateActivities::getContents, CorporateActivities::getInsDtm, CorporateActivities::getIsSetLink);
            List<CorporateActivities> articles = corporateActivitiesService.list(seoInnerChain1);
            if (CollectionUtil.isNotEmpty(articles)) {
                articles.forEach(r->buildLink(r.getContents(),seoInnerChain,CorporateActivities::setContents,r,CorporateActivities::setIsSetLink));
                seoInnerChain.setCount(articles.size());
                corporateActivitiesService.updateBatchById(articles);
            }
        }else if (TermTypeEnum.DYNAMICS_LOGISTICS_LIST.getCode().equals(vo.getType())) {
            //物流问答
            LambdaQueryWrapper<LogisticsQa> seoInnerChain1 = getSeoInnerChain(seoInnerChain, LogisticsQa::getContents, LogisticsQa::getInsDtm, LogisticsQa::getIsSetLink);
            List<LogisticsQa> articles = logisticsQaService.list(seoInnerChain1);
            if (CollectionUtil.isNotEmpty(articles)) {
                articles.forEach(r->buildLink(r.getContents(),seoInnerChain,LogisticsQa::setContents,r,LogisticsQa::setIsSetLink));
                seoInnerChain.setCount(articles.size());
                logisticsQaService.updateBatchById(articles);
            }
        }else if (TermTypeEnum.DYNAMICS_LOGISTICS.getCode().equals(vo.getType())) {
            //帮助与支持
            LambdaQueryWrapper<HelpSupport> seoInnerChain1 = getSeoInnerChain(seoInnerChain, HelpSupport::getContents, HelpSupport::getInsDtm, HelpSupport::getIsSetLink);
            List<HelpSupport> articles = helpSupportService.list(seoInnerChain1);
            if (CollectionUtil.isNotEmpty(articles)) {
                articles.forEach(r->buildLink(r.getContents(),seoInnerChain,HelpSupport::setContents,r,HelpSupport::setIsSetLink));
                seoInnerChain.setCount(articles.size());
                helpSupportService.updateBatchById(articles);
            }
        }
        save(seoInnerChain);
        return true;
    }

    /**
     * 设置链接
     * @param <T>
     * @param articleContent
     * @param seoInnerChain
     * @param consumer
     * @param r
     */
    public <T>void buildLink(String articleContent, SeoInnerChain seoInnerChain, BiConsumer<T, String> consumer, T r,BiConsumer<T, Integer> linkYn) {
        String result = EscapeUtil.unescapeHtml4(articleContent);
        String replace = result.replaceFirst(seoInnerChain.getKeyWord(), String.format(linkFormat, seoInnerChain.getLink(), seoInnerChain.getKeyWord()));
        consumer.accept(r,EscapeUtil.escapeHtml4(replace));
        linkYn.accept(r,Integer.parseInt(LinkYnEnum.DYNAMICS_LOGISTICS.getCode()));
    }

    /**
     * 获取查询条件
     * @param seoInnerChain
     * @param like
     * @param between
     * @param ne
     * @param <T>
     * @param <R>
     * @return
     */
    private <T, R> LambdaQueryWrapper<T> getSeoInnerChain(SeoInnerChain seoInnerChain, SFunction<T, R> like
            , SFunction<T, R> between, SFunction<T, R> ne) {
        LambdaQueryWrapper<T> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .like(like, seoInnerChain.getKeyWord())
                .between(between, seoInnerChain.getStartTime(), seoInnerChain.getEndTime())
                .eq(ne, Integer.parseInt(LinkYnEnum.LOGISTICS_POLICY.getCode())).last("limit "+seoInnerChain.getCount());
        return queryWrapper;
    }
}
