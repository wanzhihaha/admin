package main.java.com.cellosquare.adminapp.common.util;

import org.apache.commons.lang.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName XssUtils
 * @Description TODO
 * @Author bu.zhaolei
 * @Date 2022-04-12 오후 1:59
 **/
public class XssUtils {

    /**
     * 富文本框操作
     *
     * @param content
     * @return
     */
    public static String operationContent(String content, String name) {
        Whitelist whitelist = Whitelist.relaxed();
        whitelist.addAttributes(":all", "style", "class", "id");
        whitelist.addAttributes("a", "target");
        String baseUri = "http://baseuri";
        content = Jsoup.clean(StringEscapeUtils.unescapeXml(StringEscapeUtils.unescapeXml(content)), baseUri, whitelist).replaceAll("http://baseuri", "");
        //图片加上标签
        return RichTextUtil.addImgTag(content, name);
    }

    public static String cleanXssNamoContent(String content) {
        Whitelist whitelist = Whitelist.relaxed();
        whitelist.addAttributes(":all", "style", "class", "id");
        whitelist.addAttributes("a", "target");
        String baseUri = "http://baseuri";
        content = Jsoup.clean(StringEscapeUtils.unescapeXml(StringEscapeUtils.unescapeXml(content)), baseUri, whitelist).replaceAll("http://baseuri", "");
        return content;
    }

    public static String cleanScript(String rtn) {
        if (rtn == null) {
            return "";
        }
        String[] events = new String[]{"onabort", "on--abort", "onactivate", "on--activate", "onafterprint", "on--afterprint"
                , "onafterupdate", "on--afterupdate", "onbeforeactivate", "on--beforeactivate", "onbeforecopy", "on--beforecopy"
                , "onbeforecut", "on--beforecut", "onbeforedeactivate", "on--beforedeactivate", "onbeforeeditfocus", "on--beforeeditfocus"
                , "onbeforepaste", "on--beforepaste", "onbeforeprint", "on--beforeprint", "onbeforeunload", "on--beforeunload"
                , "onbeforeupdate", "on--beforeupdate", "onbegin", "on--begin", "onblur", "on--blur", "onbounce", "on--bounce"
                , "oncellchange", "on--cellchange", "onchange", "on--change", "onclick", "on--click", "oncontentready", "on--contentready"
                , "oncontentsave", "on--contentsave", "oncontextmenu", "on--contextmenu", "oncontrolselect", "on--controlselect"
                , "oncopy", "on--copy", "oncut", "on--cut", "ondataavailable", "on--dataavailable", "ondatasetchanged", "on--datasetchanged"
                , "ondatasetcomplete", "on--datasetcomplete", "ondatasetcomplete", "on--datasetcomplete", "ondblclick", "on--dblclick"
                , "ondeactivate", "on--deactivate", "ondetach", "on--detach", "ondocumentready", "on--documentready"
                , "ondragdrop", "on--dragdrop", "ondragend", "on--dragend", "ondragenter", "on--dragenter", "ondragleave", "on--dragleave"
                , "ondragover", "on--dragover", "ondragstart", "on--dragstart", "ondrag", "on--drag", "ondrop", "on--drop", "onend", "on--end"
                , "onerror", "on--error", "onerrorupdate", "on--errorupdate", "onfilterchange", "on--filterchange", "onfinish", "on--finish"
                , "onfocusin", "on--focusin", "onfocusout", "on--focusout", "onfocus", "on--focus", "onhelp", "on--help", "onhide", "on--hide"
                , "onkeydown", "on--keydown", "onkeypress", "on--keypress", "onkeyup", "on--keyup", "onlayoutcomplete", "on--layoutcomplete"
                , "onload", "on--load", "onlosecapture", "on--losecapture", "onmediacomplete", "on--mediacomplete"
                , "onmediaerror", "on--mediaerror", "onmedialoadfailed", "on--medialoadfailed", "onmousedown", "on--mousedown"
                , "onmouseenter", "on--mouseenter", "onmouseleave", "on--mouseleave", "onmousemove", "on--mousemove"
                , "onmouseout", "on--mouseout", "onmouseover", "on--mouseover", "onmouseup", "on--mouseup"
                , "onmousewheel", "on--mousewheel", "onmoveend", "on--moveend", "onmovestart", "on--movestart", "onmove", "on--move"
                , "onopenstatechange", "on--openstatechange", "onoutofsync", "on--outofsync", "onpaste", "on--paste", "onpause", "on--pause"
                , "onplaystatechange", "on--playstatechange", "onpropertychange", "on--propertychange"
                , "onreadystatechange", "on--readystatechange", "onrepeat", "on--repeat", "onreset", "on--reset", "onresizeend", "on--resizeend"
                , "onresizestart", "on--resizestart", "onresize", "on--resize", "onresume", "on--resume", "onreverse", "on--reverse"
                , "onrowclick", "on--rowclick", "onrowenter", "on--rowenter", "onrowexit", "on--rowexit", "onrowout", "on--rowout"
                , "onrowover", "on--rowover", "onrowsdelete", "on--rowsdelete", "onrowsinserted", "on--rowsinserted", "onsave", "on--save"
                , "onscroll", "on--scroll", "onseek", "on--seek", "onselectionchange", "on--selectionchange", "onselectstart", "on--selectstart"
                , "onselect", "on--select", "onshow", "on--show", "onstart", "on--start", "onstop", "on--stop", "onsubmit", "on--submit"
                , "onsyncrestored", "on--syncrestored", "ontimeerror", "on--timeerror", "ontrackchange", "on--trackchange"
                , "onunload", "on--unload", "onurlflip", "on--urlflip"
                , "onhashchange", "on--hashchange"
                , "onmessage", "on--message", "onoffline", "on--offline", "ononline", "on--online", "onpagehide", "on-pagehide", "onpageshow", "on-pageshow"
                , "onpopstate", "on--popstate", "onstorage", "on--storage", "oninput", "on--input", "oninvalid", "on--invalid", "onsearch", "on--search"
                , "onwheel", "on--wheel", "oncanplay", "on--canplay", "oncanplaythrough", "on--canplaythrough", "oncuechange", "on-cuechange", "ondurationchange", "on-durationchange"
                , "onemptied", "on--emptied", "onended", "on--ended", "onloadeddata", "on--loadeddata", "onloadedmetadata", "on--loadedmetadata"
                , "onloadstart", "on--loadstart", "onplay", "on--play", "onplaying", "on--playing", "onprogress", "on--progress", "onratechange", "on--ratechange"
                , "onseeked", "on--seeked", "onseeking", "on--seeking", "onstalled", "on--stalled", "onsuspend", "on-suspend", "ontimeupdate", "on--timeupdate"
                , "onvolumechange", "on--volumechange", "onwaiting", "on--waiting", "ontoggle", "on--toggle", "&#", "&#  "
                , "ontouchcancel", "on--touchcancel", "ontouchend", "on--touchend", "ontouchmove", "on--touchmove", "ontouchstart", "on--touchstart", "marquee", "ma--rquee"
                , "alert", "a--lert"
                , "meta", "me--ta", "script", "s--cript", "iframe", "i--frame", "vbscript", "v--bscript", "javascript", "j--avascript",
                "frame", "f--rame", "object", "ob--ject", "embed", "e--mbed", "applet", "a--pplet", "<form", "<--form", "<input", "<--input"
        };

        for (int i = 0; events != null && i < ((int) events.length / 2); i++) {
            rtn = rtn.replaceAll("(?i)" + events[i * 2], events[i * 2 + 1]);
        }
        return rtn;
    }

    /**
     * 是否包含特殊字符
     * @param str
     * @return
     */
    public static boolean hasSpecialChar(String str) {
        String regEx = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）—+|{}【】‘；：”“’。，、？]";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }

}
