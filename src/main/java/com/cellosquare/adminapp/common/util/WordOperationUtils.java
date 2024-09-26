package main.java.com.cellosquare.adminapp.common.util;

import com.bluewaves.lab.property.XmlPropertyManager;
import com.bluewaves.lab.util.DateUtil;
import com.cellosquare.adminapp.common.vo.BaseWordData;
import fr.opensagres.poi.xwpf.converter.core.FileImageExtractor;
import fr.opensagres.poi.xwpf.converter.core.FileURIResolver;
import fr.opensagres.poi.xwpf.converter.xhtml.XHTMLConverter;
import fr.opensagres.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.owasp.esapi.SafeFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.UUID;

public class WordOperationUtils {


    public static List<BaseWordData> importWord(MultipartFile fileItem, String type) throws Exception {
        //下载文件到临时路径下
        File destFile = FileUtil.fileDownloadToLocalPath(fileItem);
        //转换为html
        String path = docxToHtml(destFile);
        //读取文件内容
        String content = readFileByLines(path);
        //替换案例富文本信息中的图片(如果有)路径并删除临时文件和临时图片
        content = dealWithTemplatesRichTextToPictureChild(content);
        //转换为实体类
        List<BaseWordData> baseWordDataList = WordUtil.converData(content, type);
        //删除临时文件
        destFile.delete();
        File html_file = new File(path);
        html_file.delete();
        return baseWordDataList;
    }


    /**
     * 逐行读取文件
     *
     * @param fileName
     * @return
     */
    public static String readFileByLines(String fileName) {
        FileInputStream file = null;
        BufferedReader reader = null;
        InputStreamReader inputFileReader = null;
        String content = "";
        String tempString = null;
        try {
            file = new FileInputStream(fileName);
            inputFileReader = new InputStreamReader(file, "utf-8");
            reader = new BufferedReader(inputFileReader);
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                content += tempString;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return content;
    }

    /**
     * 处理图片
     *
     * @return
     */
    public static String dealWithTemplatesRichTextToPictureChild(String content) throws Exception {
        //上传的目录
        String uploadFilePathNamo = XmlPropertyManager.getPropertyValue("uploadFilePathNamo");
        //1、正则匹配src
        List<String> imagesFiles = HtmlUtil.regexMatchPicture(content);
        //2、判空
        if (imagesFiles.size() > 0) {
            for (String imagesFile : imagesFiles) {
                File file = new File(imagesFile);
                MultipartFile fileItem = FileUtil.createFileItem(file, file.getName());
                //文件名
                String fileNm = fileItem.getOriginalFilename();
                //后缀
                String fileExt = fileNm.toLowerCase().substring(fileNm.lastIndexOf(".") + 1);
                //新的文件名
                String fileTmprNm = DateUtil.getDateFormat("yyyyMMddHHmmss") + "_" + UUID.randomUUID() + "." + fileExt;

                SafeFile safeFile = new SafeFile(uploadFilePathNamo, fileTmprNm);
                //上传文件
                fileItem.transferTo(safeFile);
                //拼接
                StringBuffer image_load = new StringBuffer();
                image_load.append("/namoImgView.do?");
                image_load.append("nfile=" + fileTmprNm);

                content = content.replace(imagesFile, image_load.toString());
            }
            //删除临时图片
            try {
                imagesFiles.forEach(imagesFile -> {
                    File file = new File(imagesFile);
                    file.delete();
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return content;
    }

    /**
     * 转换文档为html
     *
     * @param destFile
     * @return
     * @throws IOException
     */

    public static String docxToHtml(File destFile) throws IOException {
        OutputStreamWriter outputStreamWriter = null;
        FileInputStream in = new FileInputStream(destFile);
        XWPFDocument document = new XWPFDocument(in);
        // 存放图片的文件夹
        File imageFolderFile = new File(String.valueOf(destFile.getParentFile()));
        XHTMLOptions options = XHTMLOptions.create().URIResolver(new FileURIResolver(imageFolderFile));
        options.setExtractor(new FileImageExtractor(imageFolderFile));
        options.setIgnoreStylesIfUnused(false);
        options.setFragment(true);
        // html中图片的路径
        //options.URIResolver(new BasicURIResolver(System.currentTimeMillis() + "/"));
        String path_ = FileUtil.getProjectPath() + "tempHtml.html";
        outputStreamWriter = new OutputStreamWriter(new FileOutputStream(path_), "utf-8");
        XHTMLConverter xhtmlConverter = (XHTMLConverter) XHTMLConverter.getInstance();
        xhtmlConverter.convert(document, outputStreamWriter, options);
        outputStreamWriter.close();
        return path_;
    }

}
