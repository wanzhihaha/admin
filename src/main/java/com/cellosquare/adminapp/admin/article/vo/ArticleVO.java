package com.cellosquare.adminapp.admin.article.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.cellosquare.adminapp.common.vo.GeneralVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
public class ArticleVO extends GeneralVO {
    private Long id;
    /**
     * 文章类型
     */
    private String articleType;

    /**
     * 文章标题
     */
    private String articleTitle;

    /**
     * 文章摘要
     */
    private String articleDigest;

    /**
     * 文章大图url
     */
    private String articlePicBig;

    /**
     * 文章辅图url
     */
    private String articlePicAs;

    /**
     * 文章缩略图url
     */
    private String articlePicTb;

    /**
     * 文章标签
     */
    private String articleTag;

    /**
     * 是否有效1是0否
     */
    private String enableFlag;
    //关键词标签
    private String AntistopTags;
    //术语标签
    private String terminologyTags;
    //术语集合
    private List<String> terminologyTagList;

    /**
     * 创建时间
     */
    private Timestamp createDate;

    /**
     * 创建人id
     */
    private String createPersionId;

    /**
     * 修改时间
     */
    private Timestamp updateDate;

    /**
     * 修改人id
     */
    private String updatePersionId;

    /**
     * 置顶类型
     */
    private String stickType;

    /**
     * 文章内容
     */
    private String articleContent;

    /**
     * 文章大图名称
     */
    private String articlePicBigName;

    /**
     * 文章辅图名称
     */
    private String articlePicAsName;

    /**
     * 文章缩略图名称
     */
    private String articlePicTbName;

    /**
     * 是否使用
     */
    private String useYn;

    /**
     * 排序
     */
    private String ordb;

    /**
     * 浏览量
     */
    private Long pageView;

    private String[] listSortOrder;
    private String[] listblogSeq;
    private String metaSeqNo;
}
