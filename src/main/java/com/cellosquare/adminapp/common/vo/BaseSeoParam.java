package main.java.com.cellosquare.adminapp.common.vo;

import lombok.Data;

import java.util.List;

/**
 *
 */
@Data
public class BaseSeoParam {
    //模块
    private String SeoModuleCode;
    //name
    private String name;
    //描述
    private String description;
    //内容
    private String contents;
    //术语集合
    private List<String> terminologyTags;
    //seo
    private com.cellosquare.adminapp.admin.seo.vo.AdminSeoVO adminSeoVO;

    public BaseSeoParam(String seoModuleCode, String name, String description,
                        String contents, List<String> terminologyTags, com.cellosquare.adminapp.admin.seo.vo.AdminSeoVO adminSeoVO) {
        SeoModuleCode = seoModuleCode;
        this.name = name;
        this.description = description;
        this.contents = contents;
        this.terminologyTags = terminologyTags;
        this.adminSeoVO = adminSeoVO;
    }
}
