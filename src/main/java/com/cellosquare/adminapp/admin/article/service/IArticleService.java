package com.cellosquare.adminapp.admin.article.service;

import com.cellosquare.adminapp.admin.article.vo.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cellosquare.adminapp.admin.article.vo.ArticleVO;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author walker
 * @since 2022-12-335 10:57:25
 */
public interface IArticleService extends IService<Article> {

    Boolean register(HttpServletRequest request, HttpServletResponse response, ArticleVO vo, MultipartHttpServletRequest muServletRequest) throws Exception;

    Boolean doUpdate(HttpServletRequest request, HttpServletResponse response, ArticleVO vo, MultipartHttpServletRequest muServletRequest);
    void getTags(ArticleVO vo);

    void importWord(MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception;
}
