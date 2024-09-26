package main.java.com.cellosquare.adminapp.common.util;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.text.html.HTMLEditorKit;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RichTextUtil extends HTMLEditorKit.ParserCallback {


    /**
     * 纯文本
     *
     * @param content
     * @return
     */
    public static String getText(String content) {
        Document document = Jsoup.parse(content);
        return document.text();
    }

    /**
     * 获取图片地址
     *
     * @param htmlStr
     * @return
     */
    public static Set<String> getImgStr(String htmlStr) {
        Set<String> pics = new HashSet<>();
        String img = "";
        Pattern p_image;
        Matcher m_image;
        //     String regEx_img = "<img.*src=(.*?)[^>]*?>"; //图片链接地址
        String regEx_img = "<img.*src\\s*=\\s*(.*?)[^>]*?>";
        p_image = Pattern.compile
                (regEx_img, Pattern.CASE_INSENSITIVE);
        m_image = p_image.matcher(htmlStr);
        while (m_image.find()) {
            // 得到<img />数据
            img = m_image.group();
            // 匹配<img>中的src数据
            Matcher m = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)").matcher(img);
            while (m.find()) {
                pics.add(m.group(1));
            }
        }
        return pics;
    }

    /**
     * 获取第一张图片
     *
     * @param htmlStr
     * @return
     */
    public static String getImgFirst(String htmlStr) {
        Set<String> imgSet = getImgStr(htmlStr);
        if (!imgSet.isEmpty()) {
            return imgSet.iterator().next();//获取第一张
        }
        return StringUtils.EMPTY;
    }


    /**
     * 图片打标签
     *
     * @param text
     * @param title
     * @return
     */
    public static String addImgTag(String text, String title) {
        Element doc = Jsoup.parseBodyFragment(text).body();
        Elements pngs = doc.select("img");
        for (Element element : pngs) {
            String imgUrlAlt = element.attr("alt");
            // if (StringUtils.isEmpty(imgUrlAlt)) { //没有的就添加alt属性
            element.attr("alt", title);
            //}
            String imgUrlTitle = element.attr("title");
            //if (StringUtils.isEmpty(imgUrlTitle)) { //没有的就添加title属性
            element.attr("title", title);
            // }
        }
        return doc.toString();
    }

    private static boolean matchKeywords(String words, String keywords) {

        Pattern pattern = Pattern.compile(keywords);
        Matcher matcher = pattern.matcher(words);
        if (matcher.find()) {
            //log.info("匹配到了！用户输入的关键字={}",words);
            return true;
        }
        //log.info("未匹配到！用户输入的关键字={}",words);
        return false;
    }

}


