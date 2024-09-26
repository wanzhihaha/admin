package com.cellosquare.adminapp.admin.videoUploadHistory.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author walker
 * @since 2024-09-254 18:41:21
 */
@Getter
@Setter
@TableName("mk_mv_video_upload_history")
public class MvVideoUploadHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @TableField("upload_status")
    private String uploadStatus;

    @TableField("video_name")
    private String videoName;

    @TableField("video_tittle")
    private String videoTittle;

    @TableField("upload_man")
    private String uploadMan;

    @TableField("upload_date")
    private String uploadDate;

    @TableField("video_link")
    private String videoLink;

    @TableField("fail_info")
    private String failInfo;


}
