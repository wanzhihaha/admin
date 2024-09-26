package com.cellosquare.adminapp.admin.goods;

import com.bluewaves.lab.message.XmlMessageManager;
import com.bluewaves.lab.message.util.ActionMessageUtil;
import com.bluewaves.lab.property.XmlPropertyManager;
import com.bluewaves.lab.util.StringUtil;
import com.cellosquare.adminapp.admin.goods.service.AdminProductsService;
import com.cellosquare.adminapp.admin.goods.vo.AdminProductsVO;
import com.cellosquare.adminapp.admin.helpSupport.service.IHelpSupportService;
import com.cellosquare.adminapp.admin.helpSupport.vo.HelpSupportVo;
import com.cellosquare.adminapp.admin.seo.service.AdminSeoService;
import com.cellosquare.adminapp.admin.seo.vo.AdminSeoVO;
import com.cellosquare.adminapp.common.adminAnnotion.CleanCacheAnnotion;
import com.cellosquare.adminapp.common.download.FileDownLoadManager;
import com.cellosquare.adminapp.common.util.FileDownUtil;
import com.nhncorp.lucy.security.xss.XssPreventer;
import org.apache.commons.io.FilenameUtils;
import org.owasp.esapi.SafeFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class AdminProductsController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AdminProductsService adminProductsServiceImpl;
    @Autowired
    private AdminSeoService adminSeoServiceImpl;
    @Autowired
    private IHelpSupportService helpSupportServiceImpl;

    @GetMapping("/celloSquareAdmin/products/list.do")
    public String productList(Model model, @ModelAttribute("vo") AdminProductsVO vo) {
        adminProductsServiceImpl.productList(model, vo);
        return "admin/basic/products/list";
    }


    @PostMapping("/celloSquareAdmin/products/registerForm.do")
    public String registForm(HttpServletRequest request, HttpServletResponse response, Model model,
                             @ModelAttribute("vo") AdminProductsVO vo) {
        HttpSession session = request.getSession();
        session.removeAttribute("detail");
        session.removeAttribute("adminSeoVO");
        session.removeAttribute("attachFileList");
        session.removeAttribute("vo");

        List<HelpSupportVo> helpSupportVos = helpSupportServiceImpl.queryQAndA();
        model.addAttribute("ackQuestions", helpSupportVos);
        model.addAttribute("contIU", "I");
        return "admin/basic/products/registerForm";
    }

    /**
     * 名称校验
     *
     * @param request
     * @param response
     * @param model
     * @param nm
     * @return
     */
    @GetMapping("/celloSquareAdmin/products/getProductCountByNm.do")
    @ResponseBody
    public Map getProductCountByNm(HttpServletRequest request, HttpServletResponse response, Model model,
                                   @ModelAttribute("nm") String nm, @ModelAttribute("ignoreSeqNo") String ignoreSeqNo) {
        Map<String, String> map = new HashMap<>();
        int productCountByNm = adminProductsServiceImpl.getProductCountByNm(nm, ignoreSeqNo);
        map.put("productCountByNm", productCountByNm + "");
        map.put("productCountByNmMsg", productCountByNm > 0 ? "产品名称重复" : "");
        return map;
    }

    @PostMapping("/celloSquareAdmin/products/register.do")
    @CleanCacheAnnotion
    public String register(HttpServletRequest request, HttpServletResponse response, Model model,
                           @ModelAttribute("vo") AdminProductsVO vo, MultipartHttpServletRequest muServletRequest) {
        try {
            return adminProductsServiceImpl.register(request, response, muServletRequest, vo);
        } catch (Exception e) {
            e.printStackTrace();
            ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.write.fail"), null, null, null, true);
            return "admin/basic/products/registerForm";
        }

    }


    @PostMapping("/celloSquareAdmin/products/updateForm.do")
    public String updateForm(HttpServletRequest request, HttpServletResponse response, Model model,
                             @ModelAttribute("vo") AdminProductsVO vo) {
        adminProductsServiceImpl.updateForm(model, vo);
        return "admin/basic/products/registerForm";
    }


    @PostMapping("/celloSquareAdmin/products/update.do")
    @CleanCacheAnnotion
    public String doUpdate(HttpServletRequest request, HttpServletResponse response,
                           MultipartHttpServletRequest muServletRequest, Model model,
                           @ModelAttribute("vo") AdminProductsVO vo) {
        try {
            return adminProductsServiceImpl.doUpdate(request, response, muServletRequest, model, vo);
        } catch (Exception e) {
            e.printStackTrace();
            ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.modify.fail"), null, null, null, true);
            return "admin/basic/products/registerForm";
        }
    }

    @PostMapping("/celloSquareAdmin/products/detail.do")
    public String goDetail(@ModelAttribute("vo") AdminProductsVO vo, Model model, HttpServletRequest request) {
        adminProductsServiceImpl.goDetail(model,vo);
        return "admin/basic/products/detail";
    }

    @PostMapping("/celloSquareAdmin/products/doDelete.do")
    @CleanCacheAnnotion
    public String delete(HttpServletRequest request, HttpServletResponse response, Model model,
                         @ModelAttribute("vo") AdminProductsVO vo) {
        logger.debug("AdminGoodsController :: delete");
        try {
            adminProductsServiceImpl.delete(vo);

            // seo정보 삭제
            adminSeoServiceImpl.doSeoDelete(vo);

            Map<String, String> hmParam = new HashMap<String, String>();
            hmParam.put("productSeqNo", vo.getProductSeqNo());
            hmParam.put("productCtgry", vo.getProductCtgry());
            model.addAttribute("msg_type", ":submit");
            model.addAttribute("msg", XmlMessageManager.getMessageValue("message.common.delete.success"));
            model.addAttribute("url", "./list.do");
            model.addAttribute("hmParam", hmParam);

            return "admin/common/message";

        } catch (Exception e) {
            ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.delete.fail"), null, null, null, true);
            return "admin/basic/products/detail.do";
        }

    }


    @ResponseBody
    @GetMapping("/celloSquareAdmin/products/goodsImgView.do")
    public String eventImgView(HttpServletRequest request, HttpServletResponse response, Model model,
                               @ModelAttribute("vo") AdminProductsVO vo) {

        logger.debug("AdminEventController :: imageDownload");
        AdminProductsVO AdminProductsVO = adminProductsServiceImpl.getDetail(vo);

        try {
            if (AdminProductsVO != null) {
                FileDownLoadManager fileDownLoadManager = new FileDownLoadManager(request, response);
                if (vo.getImgKinds().equals("pcList")) {
                    SafeFile pcListFile = new SafeFile(AdminProductsVO.getPcListImgPath(), FilenameUtils.getName(AdminProductsVO.getPcListImgFileNm()));
                    if (pcListFile.isFile()) {
                        fileDownLoadManager.fileFlush(pcListFile, AdminProductsVO.getPcListImgOrgFileNm());
                    }
                } else if (vo.getImgKinds().equals("pcDetail")) {
                    SafeFile pcDetailFile = new SafeFile(AdminProductsVO.getPcDetlImgPath(), FilenameUtils.getName(AdminProductsVO.getPcDetlImgFileNm()));
                    if (pcDetailFile.isFile()) {
                        fileDownLoadManager.fileFlush(pcDetailFile, AdminProductsVO.getPcDetlImgOrgFileNm());
                    }
                } else if (vo.getImgKinds().equals("mobileList")) {
                    SafeFile mobileListfile = new SafeFile(AdminProductsVO.getMobileListImgPath(), FilenameUtils.getName(AdminProductsVO.getMobileListImgFileNm()));
                    if (mobileListfile.isFile()) {
                        fileDownLoadManager.fileFlush(mobileListfile, AdminProductsVO.getMobileListImgOrgFileNm());
                    }
                } else if (vo.getImgKinds().equals("mobileDetail")) {
                    SafeFile mobileDetailfile = new SafeFile(AdminProductsVO.getMobileDetlImgPath(), FilenameUtils.getName(AdminProductsVO.getMobileDetlImgFileNm()));
                    if (mobileDetailfile.isFile()) {
                        fileDownLoadManager.fileFlush(mobileDetailfile, AdminProductsVO.getMobileDetlImgOrgFileNm());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    @GetMapping("/celloSquareAdmin/products/goodsDownload.do")
    public void fileDown(HttpServletRequest request, HttpServletResponse response, Model model,
                         @ModelAttribute("vo") AdminProductsVO vo) throws IOException {

        AdminProductsVO AdminProductsVO = adminProductsServiceImpl.getDetail(vo);

        String fNm = null;
        String filePath = null;
        if (vo.getImgKinds().equals("pcList")) {
            fNm = AdminProductsVO.getPcListImgOrgFileNm();
            filePath = AdminProductsVO.getPcListImgPath() + "/" + FilenameUtils.getName(AdminProductsVO.getPcListImgFileNm());
        } else if (vo.getImgKinds().equals("pcDetail")) {
            fNm = AdminProductsVO.getPcDetlImgOrgFileNm();
            filePath = AdminProductsVO.getPcDetlImgPath() + "/" + FilenameUtils.getName(AdminProductsVO.getPcDetlImgFileNm());
        } else if (vo.getImgKinds().equals("mobileList")) {
            fNm = AdminProductsVO.getMobileListImgOrgFileNm();
            filePath = AdminProductsVO.getMobileListImgPath() + "/" + FilenameUtils.getName(AdminProductsVO.getMobileListImgFileNm());
        } else if (vo.getImgKinds().equals("mobileDetail")) {
            fNm = AdminProductsVO.getMobileDetlImgOrgFileNm();
            filePath = AdminProductsVO.getMobileDetlImgPath() + "/" + FilenameUtils.getName(AdminProductsVO.getMobileDetlImgFileNm());
        }


        OutputStream os = null;
        FileInputStream fis = null;

        try {
            if (AdminProductsVO != null) {

                SafeFile file = new SafeFile(filePath);

                response.setContentType("application/octet-stream; charset=utf-8");
                response.setContentLength((int) file.length());

                String browser = FileDownUtil.getBrowser(request);
                String disposition = FileDownUtil.getDisposition(fNm, browser);

                //fileName으로 다운받아라 라는뜻  attachment로써 다운로드 되거나 로컬에 저장될 용도록 쓰이는 것인지
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

    /**
     * 정렬순서 저장
     *
     * @param request
     * @param response
     * @param model
     * @param vo
     * @return
     */
    @PostMapping("/celloSquareAdmin/products/doSortOrder.do")
    public String doSortOrder(HttpServletRequest request, HttpServletResponse response, Model model,
                              @ModelAttribute("vo") AdminProductsVO vo) {
        try {
            AdminProductsVO AdminProductsVO = null;
            for (int i = 0; i < vo.getListproductSeqNo().length; i++) {
                AdminProductsVO = new AdminProductsVO();
                AdminProductsVO.setProductSeqNo(vo.getListproductSeqNo()[i]);
                AdminProductsVO.setProductCtgry(vo.getProductCtgry());
                // 문자열이 숫자인지 확인
                String str = "";
                if (vo.getListOrdb().length > 0) {
                    str = vo.getListOrdb()[i];
                }
                if (!StringUtil.nvl(str).equals("")) {
                    boolean isNumeric = str.matches("[+-]?\\d*(\\.\\d+)?");
                    // 숫자라면 true
                    if (isNumeric) {
                        int num = Integer.parseInt(vo.getListOrdb()[i]);
                        if (0 <= num && 1000 > num) {
                            AdminProductsVO.setOrdb(vo.getListOrdb()[i]);
                        } else { //1부터 999까지만 값을 체크 그값이 아니라면 오류메세지
                            ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.modify.fail"), null, null, null, true);
                            return "admin/basic/products/list";
                        }
                    } else { //숫자가 아니면 오류메세지
                        ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.modify.fail"), null, null, null, true);
                        return "admin/basic/products/list";
                    }
                }
                adminProductsServiceImpl.doSortOrder(AdminProductsVO);
            }


            Map<String, String> hmParam = new HashMap<String, String>();
            hmParam.put("productCtgry", vo.getProductCtgry());
            model.addAttribute("msg_type", ":submit");
            model.addAttribute("msg", XmlMessageManager.getMessageValue("message.common.modify.success"));
            model.addAttribute("url", "./list.do");
            model.addAttribute("hmParam", hmParam);

            return "admin/common/message";

        } catch (Exception e) {
            ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.modify.fail"), null, null, null, true);
            return "redirect:" + XmlPropertyManager.getPropertyValue("system.admin.path") + "/products/list.do" + "?" + "productCtgry=" + vo.getProductCtgry();
        }
    }


}
