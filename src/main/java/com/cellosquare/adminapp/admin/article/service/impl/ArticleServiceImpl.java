package com.cellosquare.adminapp.admin.article.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HtmlUtil;
import com.bluewaves.lab.message.XmlMessageManager;
import com.bluewaves.lab.message.util.ActionMessageUtil;
import com.bluewaves.lab.property.XmlPropertyManager;
import com.bluewaves.lab.util.StringUtil;
import com.cellosquare.adminapp.admin.antistop.service.impl.AntistopServiceImpl;
import com.cellosquare.adminapp.admin.article.conver.ArticleConver;
import com.cellosquare.adminapp.admin.article.vo.Article;
import com.cellosquare.adminapp.admin.article.mapper.ArticleMapper;
import com.cellosquare.adminapp.admin.article.service.IArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cellosquare.adminapp.admin.article.vo.ArticleVO;
import com.cellosquare.adminapp.admin.seo.service.AdminSeoService;
import com.cellosquare.adminapp.admin.terminology.service.impl.TerminologyServiceImpl;
import com.cellosquare.adminapp.common.constant.SeoModuleEnum;
import com.cellosquare.adminapp.common.constant.UseEnum;
import com.cellosquare.adminapp.common.constant.WordUploadTypeEnum;
import com.cellosquare.adminapp.common.enums.ArticleTypeEnum;
import com.cellosquare.adminapp.common.enums.OperationEnum;
import com.cellosquare.adminapp.common.enums.StickTypeEnum;
import com.cellosquare.adminapp.common.enums.TermTypeEnum;
import com.cellosquare.adminapp.common.upload.FileUploadManager;
import com.cellosquare.adminapp.common.upload.FileUploadVO;
import com.cellosquare.adminapp.common.util.SeoUtils;
import com.cellosquare.adminapp.common.util.WordOperationUtils;
import com.cellosquare.adminapp.common.util.XssUtils;
import com.cellosquare.adminapp.common.vo.BaseSeoParam;
import com.cellosquare.adminapp.common.vo.BaseWordData;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author walker
 * @since 2022-12-335 10:57:25
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {
    @Autowired
    private AdminSeoService adminSeoServiceImpl;
    @Autowired
    private TerminologyServiceImpl terminologyService;
    @Autowired
    private AntistopServiceImpl antistopServiceImpl;


    @Override
    public Boolean register(HttpServletRequest request, HttpServletResponse response, ArticleVO vo, MultipartHttpServletRequest muServletRequest) throws Exception {
        int seoWrite = adminSeoServiceImpl.doSeoWriteV2(request, response, muServletRequest, vo);
        if (seoWrite <= 0) {
            return false;
        }
        if (!vo.getPcListOrginFile().isEmpty()) {

            if (FileUploadManager.isUploadImageValid(muServletRequest, response, true, "pcListOrginFile")) {
                String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathGoods")));

                FileUploadVO fileVO = FileUploadManager.uploadImageFile(muServletRequest, path, "pcListOrginFile");
                vo.setArticlePicBig(fileVO.getFilePath());
                vo.setArticlePicBigName(fileVO.getFileTempName());
            }
        }
        if (!vo.getPcDetailOrginFile().isEmpty()) {

            if (FileUploadManager.isUploadImageValid(muServletRequest, response, true, "pcDetailOrginFile")) {
                String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathGoods")));

                FileUploadVO fileVO = FileUploadManager.uploadImageFile(muServletRequest, path, "pcDetailOrginFile");
                vo.setArticlePicTb(fileVO.getFilePath());
                vo.setArticlePicTbName(fileVO.getFileTempName());
            }
        }

        if (!vo.getMobileListOrginFile().isEmpty()) {

            if (FileUploadManager.isUploadImageValid(muServletRequest, response, true, "mobileListOrginFile")) {
                String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathGoods")));

                FileUploadVO fileVO = FileUploadManager.uploadImageFile(muServletRequest, path, "mobileListOrginFile");
                vo.setArticlePicAs(fileVO.getFilePath());
                vo.setArticlePicAsName(fileVO.getFileTempName());
            }
        }
        Article article = ArticleConver.INSTANCT.getArticle(vo);
        //文章富文本图片加标签
        article.setArticleContent(XssUtils.operationContent(article.getArticleContent(), article.getArticleTitle()));
        //处理摘要
        article.setArticleDigest(SeoUtils.getSummaryInfo(article.getArticleDigest(), vo.getArticleContent()));
        save(article);
        this.updateStickType(article);
        saveTags(article, vo, OperationEnum.INSERT);
        ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.write.success"));
        ArticleVO articleVO = ArticleConver.INSTANCT.getArticleVO(article);
        getTags(articleVO);
        //保存seo信息
        BaseSeoParam baseSeoParam = new BaseSeoParam(SeoModuleEnum.ARTICLE.getCode(), articleVO.getArticleTitle(), articleVO.getArticleDigest(),
                articleVO.getArticleContent(), articleVO.getTerminologyTagList(), articleVO);
        SeoUtils.setSeoMsg(baseSeoParam);
        adminSeoServiceImpl.doSeoUpdatev2(request, response, null, articleVO);
        return true;
    }

    /**
     * 将标签赋值
     *
     * @param vo
     */
    @Override
    public void getTags(ArticleVO vo) {
        //术语
        String term = terminologyService.getTerm(Long.valueOf(vo.getId()), TermTypeEnum.COMPANY_NEWS);
        vo.setTerminologyTags(term);
        List<String> terms = terminologyService.getTerms(Long.valueOf(vo.getId()), TermTypeEnum.COMPANY_NEWS);
        vo.setTerminologyTagList(terms);
        //关键词
        String topTags = antistopServiceImpl.getTerm(Long.valueOf(vo.getId()), TermTypeEnum.COMPANY_NEWS);
        vo.setAntistopTags(topTags);
    }

    @Override
    public void importWord(MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<BaseWordData> baseWordDataList = WordOperationUtils.importWord(file, WordUploadTypeEnum.ARTICLE.getCode());
        List<Article> articles = new ArrayList<>();
        baseWordDataList.forEach(baseWordData -> {
            String type = baseWordData.getType().trim();
            Article article = new Article();
            article.setArticleTitle(baseWordData.getTitle());
            article.setArticleContent(XssUtils.operationContent(baseWordData.getContents(), baseWordData.getTitle()));
            article.setArticleType(ArticleTypeEnum.getEnumByDesc(type).getCode());
            article.setArticleDigest(baseWordData.getSummaryInfo());
            article.setUseYn(UseEnum.USE.getCode());
            article.setStickType(StickTypeEnum.LOGISTICS_POLICY_4.getCode());

            ArticleVO vo = ArticleConver.INSTANCT.getArticleVOSource(article);
            //保存seo信息
            BaseSeoParam baseSeoParam = new BaseSeoParam(SeoModuleEnum.ARTICLE.getCode(), vo.getArticleTitle(), vo.getArticleDigest(),
                    vo.getArticleContent(), vo.getTerminologyTagList(), vo);
            SeoUtils.setSeoMsg(baseSeoParam);
            adminSeoServiceImpl.doSeoWriteV2(request, response, null, vo);
            article.setMetaSeqNo(Long.valueOf(vo.getMetaSeqNo()));
            articles.add(article);

            //是否要关联术语
        });

        if (CollectionUtils.isNotEmpty(articles)) {
            //保存
            saveBatch(articles);
        }
    }

    /**
     * 设置标签
     *
     * @param
     * @param vo
     */
    public void saveTags(Article article, ArticleVO vo, OperationEnum operationEnum) {
        //术语
        if (ArrayUtil.isNotEmpty(vo.getTerminologyTags())){
            String[] split = vo.getTerminologyTags().split(",");
            terminologyService.marchingTerm(article.getId(), TermTypeEnum.COMPANY_NEWS, article.getArticleTitle(), operationEnum, split);
        }
        if (ArrayUtil.isNotEmpty(vo.getAntistopTags())){
            //关键词
            String[] antistopTags_arr = vo.getAntistopTags().split(",");
            antistopServiceImpl.marchingTerm(article.getId(), TermTypeEnum.COMPANY_NEWS, article.getArticleTitle(), operationEnum, antistopTags_arr);
        }

    }

    @Override
    public Boolean doUpdate(HttpServletRequest request, HttpServletResponse response, ArticleVO vo, MultipartHttpServletRequest muServletRequest) {
//        int seoWrite = adminSeoServiceImpl.doSeoUpdatev2(request, response, muServletRequest, vo);
//        if (seoWrite<=0) {
//            return false;
//        }
        String pcDetailfileDel = StringUtil.nvl(vo.getPcDetailFileDel(), "N");
        if (!vo.getPcListOrginFile().isEmpty()) {

            if (FileUploadManager.isUploadImageValid(muServletRequest, response, true, "pcListOrginFile")) {
                String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathGoods")));

                FileUploadVO fileVO = FileUploadManager.uploadImageFile(muServletRequest, path, "pcListOrginFile");
                vo.setArticlePicBig(fileVO.getFilePath());
                vo.setArticlePicBigName(fileVO.getFileTempName());
            }
        }
        if (!vo.getPcDetailOrginFile().isEmpty()) {

            if (FileUploadManager.isUploadImageValid(muServletRequest, response, true, "pcDetailOrginFile")) {
                String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathGoods")));

                FileUploadVO fileVO = FileUploadManager.uploadImageFile(muServletRequest, path, "pcDetailOrginFile");
                vo.setArticlePicTb(fileVO.getFilePath());
                vo.setArticlePicTbName(fileVO.getFileTempName());
            }
        }else{
            if(pcDetailfileDel.equals("Y")) {// 삭제를 체크했을시
                vo.setArticlePicTb("");
                vo.setArticlePicTbName("");
            }
        }
        if (!vo.getMobileListOrginFile().isEmpty()) {

            if (FileUploadManager.isUploadImageValid(muServletRequest, response, true, "mobileListOrginFile")) {
                String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathGoods")));

                FileUploadVO fileVO = FileUploadManager.uploadImageFile(muServletRequest, path, "mobileListOrginFile");
                vo.setArticlePicAs(fileVO.getFilePath());
                vo.setArticlePicAsName(fileVO.getFileTempName());
            }
        }
        Article article = ArticleConver.INSTANCT.getArticle(vo);
        //文章富文本图片加标签
        article.setArticleContent(XssUtils.operationContent(article.getArticleContent(), article.getArticleTitle()));
        updateById(article);
        this.updateStickType(article);
        saveTags(article, vo, OperationEnum.UPDATE);
        //查询tag
        getTags(vo);
        //设置seo信息
        BaseSeoParam baseSeoParam = new BaseSeoParam(SeoModuleEnum.ARTICLE.getCode(), vo.getArticleTitle(), vo.getArticleDigest(),
                vo.getArticleContent(), vo.getTerminologyTagList(), vo);
        SeoUtils.setSeoMsg(baseSeoParam);
        adminSeoServiceImpl.doSeoUpdatev2(request, response, null, vo);
        // ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.write.success"));
        return true;
    }

    /**
     * 保持StickType 一致
     *
     * @param article
     */
    private void updateStickType(Article article) {
        if (StrUtil.isNotEmpty(article.getStickType()) && !Objects.equals(article.getStickType(), StickTypeEnum.LOGISTICS_POLICY_4.getCode())) {
            List<Article> list = lambdaQuery().eq(Article::getStickType, article.getStickType()).ne(Article::getId, article.getId()).list();
            if (CollectionUtil.isNotEmpty(list)) {
                List<Long> ids = list.stream().filter(article1 -> article1.getId() != null).map(Article::getId).collect(Collectors.toList());
                lambdaUpdate().in(Article::getId, ids).set(Article::getStickType, StickTypeEnum.LOGISTICS_POLICY_4.getCode()).update();
            }
        }
    }

}
