package main.java.com.cellosquare.adminapp.common.util;

import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Named;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class ConverUtil {
    /**
     * 文章类型转换
     *
     * @return
     */
    @Named("converArticleType")
    public static String converArticleType(String req) {
        com.cellosquare.adminapp.common.enums.ArticleTypeEnum enumByCode = com.cellosquare.adminapp.common.enums.ArticleTypeEnum.getEnumByCode(req);
        return Objects.isNull(enumByCode) ? null : enumByCode.getDesc();
    }

    /**
     * 文章类型转换
     *
     * @return
     */
    @Named("converEnableFlag")
    public static String converEnableFlag(String req) {
        com.cellosquare.adminapp.common.enums.EnableFlagEnum enumByCode = com.cellosquare.adminapp.common.enums.EnableFlagEnum.getEnumByCode(req);
        return Objects.isNull(enumByCode) ? null : enumByCode.getDesc();
    }

    /**
     * 转换id
     *
     * @param id
     * @return
     */
    @Named("converIdToId")
    public static Long converIdToId(String id) {
        return StringUtils.isEmpty(id) ? null : Long.parseLong(id);
    }

    /**
     * 文章类型转换
     *
     * @return
     */
    @Named("converStickType")
    public static String converStickType(String req) {
        com.cellosquare.adminapp.common.enums.StickTypeEnum enumByCode = com.cellosquare.adminapp.common.enums.StickTypeEnum.getEnumByCode(req);
        return Objects.isNull(enumByCode) ? null : enumByCode.getDesc();
    }

    /**
     * 文章类型转换
     *
     * @return
     */
    @Named("converEffectiveTime")
    public static Timestamp converEffectiveTime(String req) throws ParseException {
        Date parse = new SimpleDateFormat("yyyy-MM-dd").parse(req);
        return new Timestamp(parse.getTime());
    }
    /**
     * 文章类型转换
     *
     * @return
     */
    @Named("converEffectiveTime2")
    public static Timestamp converEffectiveTime2(String req) throws ParseException {
        Date parse = new SimpleDateFormat("yyyy/MM/dd").parse(req);
        return new Timestamp(parse.getTime());
    }

    /**
     * 文章类型转换
     *
     * @return
     */
    @Named("converAdLocation")
    public static String converAdLocation(String req) {
        com.cellosquare.adminapp.common.enums.AdLocationEnum enumByCode = com.cellosquare.adminapp.common.enums.AdLocationEnum.getEnumByCode(req);
        return Objects.isNull(enumByCode) ? null : enumByCode.getDesc();
    }


    /**
     * 去除空格
     *
     * @return
     */
    @Named("term")
    public static String term(String req) {
        return req.trim();
    }

    /**
     * 去除空格
     *
     * @return
     */
    @Named("booleanEnum")
    public static String booleanEnum(String val) {
        return com.cellosquare.adminapp.common.constant.BooleanEnum.getEnumByCode(val).getCnValue();
    }

    @Named("toTimestamp")
    public static Timestamp toTimestamp(String req) throws ParseException {
        if (StringUtils.isEmpty(req))
            return null;
        Date parse = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(req);
        return new Timestamp(parse.getTime());
    }
}
