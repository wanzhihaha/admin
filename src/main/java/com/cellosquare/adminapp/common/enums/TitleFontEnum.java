package com.cellosquare.adminapp.common.enums;

/**
 * word 设置标题字体大小
 *
 * @author hexuan.wang
 */
public enum TitleFontEnum {
    H1("h1", 24),
    H2("h2", 22),
    H3("h3", 12),
    H7("h7", 12);
    private String title;
    private Integer font;

    public String getTitle() {
        return title;
    }

    public Integer getFont() {
        return font;
    }

    TitleFontEnum(String title, Integer font) {
        this.title = title;
        this.font = font;
    }

    public static Integer getFontByTitle(String title) {
        for (TitleFontEnum e : TitleFontEnum.values()) {
            if (title.equals(e.getTitle())) {
                return e.getFont();
            }
        }
        return null;
    }
}
