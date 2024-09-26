package main.java.com.cellosquare.adminapp.common.util;

import com.cellosquare.adminapp.common.vo.BaseWordData;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class WordUtil {

    static final String suffix = ".docx";


    /**
     * 解析读取富文本
     *
     * @param content
     * @param type
     * @return
     */
    public static List<BaseWordData> converData(String content, String type) {
        List<BaseWordData> baseWordDataList = new ArrayList<>();

        String[] split_pian = content.split(com.cellosquare.adminapp.common.constant.Constants.SPLIT_EVERY_PIAN);
        String split_type = com.cellosquare.adminapp.common.constant.Constants.SPLIT_EVERY_TYPE;
        //导入最大一百条
        int t_s = 100;
        if (split_pian.length > t_s) {
            throw new RuntimeException("超出最大上传条数" + t_s);
        }
        for (int i = 0; i < split_pian.length; i++) {
            //每个文本
            String[] split = split_pian[i].split(split_type);
            //标题
            String title = split[0];
            //内容
            String title_text = RichTextUtil.getText(title);
            if (StringUtils.isEmpty(title) || StringUtils.isEmpty(title_text)) {
                continue;
            }
            String ticx_content = StringUtils.EMPTY;
            String type_s = StringUtils.EMPTY;
            //物流问答没有类型
            if (com.cellosquare.adminapp.common.constant.WordUploadTypeEnum.QA.getCode().equals(type)) {
                ticx_content = split[1];
            } else {//文章和帮助支持 有类型
                String t = split[1];
                type_s = RichTextUtil.getText(t);

                ticx_content = split[2];
            }
            //转换为纯文本 拿前一百字
            String summary_ = RichTextUtil.getText(ticx_content);
            String summary_result = summary_.length() > 100 ? summary_.substring(0, 100) : summary_;

            baseWordDataList.add(new BaseWordData(title_text, ticx_content, type_s, summary_result));
        }
        return baseWordDataList;
    }


    /**
     * 文档返回数据
     *
     * @param file
     * @return
     * @throws Exception
     */
    public static List<Map<String, String>> readWord(MultipartFile file) {
        try {
            InputStream in = file.getInputStream();
            System.out.println("解析的文件名：" + file.getOriginalFilename());
            if (file.getOriginalFilename().toLowerCase().endsWith(suffix)) {
                // 2007+版
                return readWord2007(in);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 该读取方法针对导入案例
     *
     * @param in
     * @return
     * @throws IOException
     */
    public static List<Map<String, String>> readWord2007(InputStream in) throws IOException {
        XWPFDocument document = new XWPFDocument(in);
        List<Map<String, String>> list = new ArrayList<>();
        try {
            Iterator<XWPFTable> iterator = document.getTablesIterator();
            XWPFTable table = iterator.next();
            // 获取到表格的行数
            List<XWPFTableRow> rows = table.getRows();
            // 读取表格的每一行
            Map<String, String> map = new LinkedHashMap<>();
            for (int i = 0; i < rows.size(); i++) {
                XWPFTableRow row = rows.get(i);
                List<XWPFTableCell> cells = row.getTableCells();
                String title = cells.get(0).getText();
                if (StringUtils.isNotBlank(title)) {
                    title = title.trim();
                }
                String content = cells.get(1).getText();
                if (StringUtils.isNotBlank(content)) {
                    content = content.trim();
                }
                map.put(title, content);

            }
            list.add(map);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            document.close();
            in.close();
        }
        return list;
    }

    public static String[] getBracketContent(String content) {
        String[] arr = new String[0];
        Pattern p = Pattern.compile("(?<=\\《\\《\\《\\《)[^\\》\\》\\》\\》]+");
        Matcher m = p.matcher(content);
        while (m.find()) {
            arr = Arrays.copyOf(arr, arr.length + 1);
            arr[arr.length - 1] = m.group();
        }
        return arr;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.asList(getBracketContent("张三来了《《《《深圳跨境电商物流技术与政策的优势》》》》iii哦哦")));
    }

    /**
     * 下载word
     *
     * @param response
     * @param title
     * @param content
     */
    public static void downloadWord(HttpServletRequest request, HttpServletResponse response, String title, String content) {
        try {
            InputStream in = null;
            XWPFDocument doc = null;
            in = Thread.currentThread().getContextClassLoader().getResourceAsStream("templete/content.docx");
            OPCPackage srcPackage = OPCPackage.open(in);
            doc = new XWPFDocument(srcPackage);

            List<Map<String, Object>> mapList = new ArrayList<>();
            Map<String, Object> param = new HashMap<>(16);
            param.put("content", content);
            mapList.add(param);
            String s = "";
            wordInsertRitchText(doc, mapList);
            // 将docx输出
            exportWordList(doc, title + ".docx", request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 导出word
     *
     * @param doc      word 模版地址
     * @param fileName 文件名
     * @param request
     * @param response
     */
    public static void exportWordList(XWPFDocument doc, String fileName, HttpServletRequest request, HttpServletResponse response) {
        Assert.notNull(fileName, "导出文件名不能为空");
        Assert.isTrue(fileName.endsWith(".docx"), "word导出请使用docx格式");
        try {
            String userAgent = request.getHeader("user-agent").toLowerCase();
            if (userAgent.contains("msie") || userAgent.contains("like gecko")) {
                fileName = URLEncoder.encode(fileName, "UTF-8");
            } else {
                fileName = new String(fileName.getBytes("utf-8"), "ISO-8859-1");
            }
            // 设置强制下载不打开
            response.setContentType("application/force-download");
            // 设置文件名
            response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);
            OutputStream out = response.getOutputStream();
            doc.write(out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 往doc的标记位置插入富文本内容 注意：目前支持富文本里面带url的图片，不支持base64编码的图片
     *
     * @param doc          需要插入内容的Word
     * @param ritchtextMap 标记位置对应的富文本内容
     * @param
     */
    public static void wordInsertRitchText(XWPFDocument doc, List<Map<String, Object>> ritchtextMap) {
        try {
            int i = 0;
            long beginTime = System.currentTimeMillis();
            // 如果需要替换多份富文本，通过Map来操作，key:要替换的标记，value：要替换的富文本内容
            for (Map<String, Object> mapList : ritchtextMap) {
                for (Map.Entry<String, Object> entry : mapList.entrySet()) {
                    i++;
                    for (XWPFParagraph paragraph : doc.getParagraphs()) {
                        if (entry.getKey().equals(paragraph.getText().trim())) {
                            // 在标记处插入指定富文本内容
                            HtmlUtils.resolveHtml(entry.getValue().toString(), doc, paragraph);
                            if (i == ritchtextMap.size()) {
                                //当导出最后一个富文本时 删除需要替换的标记
                                doc.removeBodyElement(doc.getPosOfParagraph(paragraph));
                            }
                            break;
                        }
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
