package com.cellosquare.adminapp.admin.article.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author walker
 * @since 2022-12-335 15:10:07
 */
@Getter
@Setter
@TableName("mk_article_access_record")
public class ArticleAccessRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId("id")
    private Long id;

    /**
     * 文章id
     */
    @TableField("article_id")
    private Long articleId;

    /**
     * 客户端ip地址
     */
    @TableField("access_ip")
    private String accessIp;

    /**
     * 访问时间
     */
    @TableField("access_date")
    private LocalDateTime accessDate;


}
