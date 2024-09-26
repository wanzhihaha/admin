package com.cellosquare.adminapp.admin.seoInnerChain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.cellosquare.adminapp.common.vo.GeneralVO;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class SeoInnerChainVO extends GeneralVO {
    /**
     * 主键id
     */
    @TableId("id")
    private Integer id;

    /**
     * 关键词
     */
    @TableField("key_word")
    private String keyWord;

    /**
     * 链接
     */
    @TableField("link")
    private String link;

    /**
     * 关联数量
     */
    @TableField("count")
    private String count;

    /**
     * 文章类型
     */
    @TableField("type")
    private String type;

    /**
     * 开始时间
     */
    @TableField("start_time")
    private String startTime;

    /**
     * 结束时间
     */
    @TableField("end_time")
    private String endTime;

    /**
     * 有效否
     */
    @TableField("use_yn")
    private String useYn;
}
