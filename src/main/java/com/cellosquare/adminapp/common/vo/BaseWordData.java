package main.java.com.cellosquare.adminapp.common.vo;

import lombok.Data;

import java.io.Serializable;


@Data
public class BaseWordData implements Serializable {
    //标题
    private String title;
    //内容
    private String contents;
    //类型
    private String type;
    //摘要
    private String summaryInfo;

    public BaseWordData(String title, String contents, String type, String summaryInfo) {
        this.title = title;
        this.contents = contents;
        this.type = type;
        this.summaryInfo = summaryInfo;
    }
}
