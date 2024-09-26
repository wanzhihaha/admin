package com.cellosquare.adminapp.admin.quote.controller;


import com.bluewaves.lab.message.XmlMessageManager;
import com.bluewaves.lab.message.util.ActionMessageUtil;
import com.bluewaves.lab.property.XmlPropertyManager;
import com.bluewaves.lab.util.StringUtil;
import com.cellosquare.adminapp.admin.quote.entity.QuoteNation;
import com.cellosquare.adminapp.admin.quote.entity.vo.AdminNationVO;
import com.cellosquare.adminapp.admin.quote.service.IQuoteNationService;
import com.cellosquare.adminapp.admin.seo.service.impl.AdminSeoServiceImpl;
import com.cellosquare.adminapp.common.constant.IsHotEnum;
import com.cellosquare.adminapp.common.download.FileDownLoadManager;
import com.cellosquare.adminapp.common.upload.FileUploadManager;
import com.cellosquare.adminapp.common.upload.FileUploadVO;
import com.cellosquare.adminapp.common.util.CommonMessageModel;
import com.cellosquare.adminapp.common.util.FileDownUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.owasp.esapi.SafeFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author hugo
 * @since 2023-06-157 09:11:30
 */
@Controller
@RequestMapping("/celloSquareAdmin/country")
@Slf4j
public class QuoteNationController {

    @Autowired
    private IQuoteNationService quoteNationServiceImpl;
    @Autowired
    private AdminSeoServiceImpl adminSeoServiceImpl;

    @GetMapping("/list.do")
    public String list(Model model, @ModelAttribute("vo") AdminNationVO vo) {
        quoteNationServiceImpl.getList(model, vo);
        return "admin/basic/country/list";
    }

    @GetMapping("/testss.do")
    public void testss(Model model, @ModelAttribute("vo") AdminNationVO vo) {
        adminSeoServiceImpl.updateAllAddMesg();
    }

//    @GetMapping("/bianhuan.do")
//    public void bianhuan(Model model, @ModelAttribute("vo") AdminNationVO vo) {
//        quoteNationServiceImpl.bianhuan();
//    }

    @PostMapping("/registerForm.do")
    public String registForm(Model model, @ModelAttribute("vo") AdminNationVO vo) {
        model.addAttribute("contIU", "I");
        return "admin/basic/country/registerForm";
    }


    @PostMapping("/register.do")
    public String register(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("vo") AdminNationVO vo,
                           MultipartHttpServletRequest muServletRequest) {
        quoteNationServiceImpl.regist(vo, response, muServletRequest);
        ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.write.success"));
        return "redirect:" + XmlPropertyManager.getPropertyValue("system.admin.path") + "/country/list.do";
    }


    @PostMapping("/updateForm.do")
    public String updateForm(Model model, @ModelAttribute("vo") AdminNationVO vo) {
        quoteNationServiceImpl.updateForm(model, vo);
        return "admin/basic/country/registerForm";
    }


    @PostMapping("/update.do")
    public String doUpdate(HttpServletRequest request, HttpServletResponse response, MultipartHttpServletRequest muServletRequest, Model model, @ModelAttribute("vo") AdminNationVO vo) {
        try {
            quoteNationServiceImpl.doUpdate(request, response, muServletRequest, model, vo);
            Map<String, String> hmParam = new HashMap<String, String>();
            hmParam.put("nationSeqNo", vo.getNationSeqNo());
            model.addAttribute("msg_type", ":submit");
            model.addAttribute("method", "post");
            model.addAttribute("msg", XmlMessageManager.getMessageValue("message.common.modify.success"));
            model.addAttribute("url", "./detail.do");
            model.addAttribute("hmParam", hmParam);
            return "admin/common/message";
        } catch (Exception e) {
            e.printStackTrace();
            ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.modify.fail"), null, null, null, true);
            return "admin/basic/country/registerForm";
        }
    }

    @PostMapping("/detail.do")
    public String detail(Model model, @ModelAttribute("vo") AdminNationVO vo) {
        quoteNationServiceImpl.detail(model, vo);
        return "admin/basic/country/detail";
    }


    @PostMapping("/doDelete.do")
    public String delete(HttpServletRequest request, Model model, @ModelAttribute("vo") AdminNationVO vo) {
        try {
            quoteNationServiceImpl.delete(vo);
            Map<String, String> hmParam = new HashMap<String, String>();
            hmParam.put("nationSeqNo", vo.getNationSeqNo());
            model.addAttribute("msg_type", ":submit");
            model.addAttribute("msg", XmlMessageManager.getMessageValue("message.common.delete.success"));
            model.addAttribute("url", "./list.do");
            model.addAttribute("hmParam", hmParam);
            return "admin/common/message";
        } catch (Exception e) {
            ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.delete.fail"), null, null, null, true);
            return "admin/basic/country/detail.do";
        }

    }

    @PostMapping("/doSortOrder.do")
    public String doSortOrder(Model model, @ModelAttribute("vo") AdminNationVO vo) {
        List<AdminNationVO> adminNationVOList = new ArrayList<>();
        for (int i = 0; i < vo.getListNationSeqNo().length; i++) {
            AdminNationVO adminNationVO = new AdminNationVO();
            adminNationVO.setNationSeqNo(vo.getListNationSeqNo()[i]);
            adminNationVO.setOrdb(vo.getListSortOrder()[i]);
            adminNationVOList.add(adminNationVO);
        }
        quoteNationServiceImpl.doSortOrder(adminNationVOList);
        model.addAttribute("param", new HashMap() {
            {
                put("searchValue", vo.getSearchValue());
            }
        });
        return CommonMessageModel.setModel(model, new HashMap() {
            {
                put("continentCd", vo.getContinentCd());
                put("expressUseYn", vo.getExpressUseYn());
            }
        });
    }

    @PostMapping("/checkCountry.do")
    @ResponseBody
    public String checkCountry(@ModelAttribute("vo") AdminNationVO vo) {
        if (StringUtils.isNotEmpty(vo.getNationCd())) {
            vo.setNationCd(vo.getNationCd().toUpperCase());
        }
        List<AdminNationVO> adminNationVOList = quoteNationServiceImpl.checkCountry(vo);
        for (AdminNationVO adminNationVO : adminNationVOList) {
            if (adminNationVO.getNationSeqNo().equals(vo.getNationSeqNo()))
                continue;
            return "nationCd";
        }
        return "success";
    }

    /**
     * 导出
     *
     * @param request
     * @param response
     * @param vo
     * @throws Exception
     */
    @GetMapping("/download.do")
    public void download(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("vo") AdminNationVO vo) throws Exception {
        quoteNationServiceImpl.excelDownLoad(request, response, vo);
    }

    @ResponseBody
    @GetMapping("/downloadCount.do")
    public int downloadCount(@ModelAttribute("vo") AdminNationVO vo) {
        return quoteNationServiceImpl.downloadCount(vo);
    }

    @ResponseBody
    @GetMapping("ImgView.do")
    public String eventImgView(HttpServletRequest request,
                               HttpServletResponse response, Model model, @ModelAttribute("vo") AdminNationVO vo) throws Exception {
        QuoteNation quoteNation = quoteNationServiceImpl.lambdaQuery().eq(QuoteNation::getNationSeqNo, Long.valueOf(vo.getNationSeqNo())).one();
        if (Objects.nonNull(quoteNation)) {
            FileDownLoadManager fileDownLoadManager = new FileDownLoadManager(request, response);
            SafeFile safeFile = new SafeFile(quoteNation.getImgFilePath(), FilenameUtils.getName(quoteNation.getImgFileNm()));
            if (safeFile.isFile()) {
                fileDownLoadManager.fileFlush(safeFile, quoteNation.getImgOrgFileNm());
            }
        }
        return null;
    }

    @PostMapping("/setHotOrNot.do")
    public String setHotOrNot(HttpServletRequest request, Model model, @ModelAttribute("vo") AdminNationVO vo) {
        model.addAttribute("param", new HashMap() {
            {
                put("searchValue", vo.getSearchValue());
                put("searchType", vo.getSearchType());
            }
        });
        HashMap hashMap = new HashMap() {
            {
                put("continentCd", vo.getContinentCd());
            }
        };
        //查询是否超过十条
        Long count = quoteNationServiceImpl.lambdaQuery().eq(QuoteNation::getIsHot, IsHotEnum.IS.getCd()).count();
        if (count >= 10 && 1 == vo.getIsHot()) {
            return CommonMessageModel.setModel("已存在十条或以上的热门，请先去除之前热门再设置", "./list.do", "get", model
                    , hashMap);
        }
        quoteNationServiceImpl.setHotOrNot(vo);
        return CommonMessageModel.setModel(model, hashMap);
    }

    @GetMapping("ImgDownload.do")
    public void fileDown(HttpServletRequest request, HttpServletResponse response, Model model,
                         @ModelAttribute("vo") AdminNationVO vo) throws IOException {
        QuoteNation quoteNation = quoteNationServiceImpl.lambdaQuery().eq(QuoteNation::getNationSeqNo, Long.valueOf(vo.getNationSeqNo())).one();
        String fNm = null;
        String filePath = null;
        if (null != quoteNation) {
            fNm = quoteNation.getImgOrgFileNm();
            filePath = quoteNation.getImgFilePath() + "/" + FilenameUtils.getName(quoteNation.getImgFileNm());
        }
        OutputStream os = null;
        FileInputStream fis = null;

        try {
            if (quoteNation != null) {
                SafeFile file = new SafeFile(filePath);
                response.setContentType("application/octet-stream; charset=utf-8");
                response.setContentLength((int) file.length());
                String browser = FileDownUtil.getBrowser(request);
                String disposition = FileDownUtil.getDisposition(fNm, browser);
                response.setHeader("Content-Disposition", disposition);
                response.setHeader("Content-Transfer-Encoding", "binary");
                os = response.getOutputStream();
                fis = new FileInputStream(file);
                FileCopyUtils.copy(fis, os);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                fis.close();
            }
        }
        if (os != null) {
            os.flush();
        }
    }

    @GetMapping("/temp.do")
    public String temp(Model model, @ModelAttribute("vo") AdminNationVO vo) {
        return "admin/basic/country/temp";
    }

    @PostMapping("/nationImgUpload.do")
    @ResponseBody
    public List<FileUploadVO> nationImgUpload(@RequestParam("imgFileUploads") MultipartFile[] multipartFiles,
                                              MultipartHttpServletRequest muServletRequest) {

        String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathCountry")));
        List<FileUploadVO> imgFileUploads = FileUploadManager.uploadFiles(muServletRequest, path, "imgFileUploads");
        //FileUploadVO fileVO = FileUploadManager.uploadImageFile(muServletRequest, path, "imgFileUploads");
        for (FileUploadVO fileVO : imgFileUploads) {
            AdminNationVO vo = new AdminNationVO();
            vo.setImgFilePath(fileVO.getFilePath());
            vo.setImgFileNm(fileVO.getFileTempName());
            vo.setImgSize(String.valueOf(fileVO.getFileSize()));
            vo.setImgOrgFileNm(fileVO.getFileOriginName());
            vo.setNationCd(vo.getImgOrgFileNm().split("-")[1].toUpperCase());
            quoteNationServiceImpl.updateImg(vo);
        }
        return imgFileUploads;
    }

}
