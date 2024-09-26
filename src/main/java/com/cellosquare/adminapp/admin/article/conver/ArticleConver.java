package com.cellosquare.adminapp.admin.article.conver;

import com.cellosquare.adminapp.admin.article.vo.Article;
import com.cellosquare.adminapp.admin.article.vo.ArticleVO;
import com.cellosquare.adminapp.common.util.ConverUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = ConverUtil.class)
public interface ArticleConver {
    ArticleConver INSTANCT = Mappers.getMapper(ArticleConver.class);

    @Mapping(target = "articleType",source = "articleType",qualifiedByName = "converArticleType")
    @Mapping(target = "stickType",source = "stickType",qualifiedByName = "converStickType")
    ArticleVO getArticleVO(Article articles);

    Article getArticle(ArticleVO articles);


    /**
     * 原样转换
     * @param articles
     * @return
     */
    ArticleVO getArticleVOSource(Article articles);
}
