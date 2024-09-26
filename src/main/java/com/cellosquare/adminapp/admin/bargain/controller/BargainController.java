package com.cellosquare.adminapp.admin.bargain.controller;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.util.ListUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bluewaves.lab.message.XmlMessageManager;
import com.bluewaves.lab.message.util.ActionMessageUtil;
import com.bluewaves.lab.property.XmlPropertyManager;
import com.cellosquare.adminapp.admin.bargain.conver.BargainConver;
import com.cellosquare.adminapp.admin.bargain.entity.Bargain;
import com.cellosquare.adminapp.admin.bargain.service.impl.BargainServiceImpl;
import com.cellosquare.adminapp.admin.bargain.vo.BargainExportVO;
import com.cellosquare.adminapp.admin.bargain.vo.BargainUploadVO;
import com.cellosquare.adminapp.admin.bargain.vo.BargainVO;
import com.cellosquare.adminapp.admin.bargainProduct.entity.BargainProduct;
import com.cellosquare.adminapp.admin.bargainProduct.service.impl.BargainProductServiceImpl;
import com.cellosquare.adminapp.admin.bargainProduct.vo.BargainProductVO;
import com.cellosquare.adminapp.admin.code.service.impl.ApiCodeServiceImpl;
import com.cellosquare.adminapp.admin.code.vo.ApiCodeVO;
import com.cellosquare.adminapp.admin.registerStatistics.vo.FromSourceUploadVO;
import com.cellosquare.adminapp.admin.seo.service.impl.AdminSeoServiceImpl;
import com.cellosquare.adminapp.admin.terminology.conver.TerminologyConver;
import com.cellosquare.adminapp.admin.terminology.entity.Terminology;
import com.cellosquare.adminapp.admin.terminology.vo.TerminologyUploadVO;
import com.cellosquare.adminapp.common.adminAnnotion.CleanCacheAnnotion;
import com.cellosquare.adminapp.common.config.CommonSpinnerWriteHandler;
import com.cellosquare.adminapp.common.config.SpinnerWriteHandler;
import com.cellosquare.adminapp.common.download.FileDownLoadManager;
import com.cellosquare.adminapp.common.enums.FromSourceType;
import com.cellosquare.adminapp.common.ex.UploadException;
import com.cellosquare.adminapp.common.listener.BargainUploadListener;
import com.cellosquare.adminapp.common.listener.TermExcelUploadListener;
import com.cellosquare.adminapp.common.util.CommonMessageModel;
import com.cellosquare.adminapp.common.util.EasyExcelUtils;
import com.google.common.collect.Maps;
import org.apache.commons.io.FilenameUtils;
import org.owasp.esapi.SafeFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URLEncoder;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author walker
 * @since 2023-08-234 08:55:28
 */
@Controller
@RequestMapping("/celloSquareAdmin/bargain")
public class BargainController {
    @Autowired
    private BargainServiceImpl bargainService;
    @Autowired
    private ApiCodeServiceImpl apiCodeService;
    @Autowired
    private BargainProductServiceImpl bargainProductService;
    @Autowired
    private AdminSeoServiceImpl adminSeoService;
    @GetMapping("/list.do")
    public String list(Model model, @ModelAttribute("vo") BargainVO vo) {
        ApiCodeVO apiCodeVO = new ApiCodeVO();
        apiCodeVO.setGrpCd("BARGAIN_SWITCH");
        apiCodeVO.setLangCd("cn-zh");
        ApiCodeVO apiCodeFirst = apiCodeService.getApiCodeFirst(apiCodeVO);
        Page<Bargain> page = bargainService.lambdaQuery()
                .and(ObjectUtil.isNotNull(vo.getProductId())&&!Objects.equals(vo.getProductId(),-1L),r->r.eq(Bargain::getProductId,vo.getProductId()))
                .and(StrUtil.isNotEmpty(vo.getSearchValue())
                        ,r->r.like(Bargain::getOrigin,vo.getSearchValue()).or().like(Bargain::getDestination,vo.getSearchValue()))
                .orderByDesc(Bargain::getId)
                .page(new Page<Bargain>(Integer.parseInt(vo.getPage()), Integer.parseInt(vo.getRowPerPage())));
        if (CollectionUtil.isNotEmpty(page.getRecords())) {
            List<Long> productIds = page.getRecords().stream().map(Bargain::getProductId).collect(Collectors.toList());
            List<BargainProduct> productList = bargainProductService.lambdaQuery().in(BargainProduct::getId, productIds).list();
            Map<Long, BargainProduct> productMap = productList.stream().collect(Collectors.toMap(BargainProduct::getId, Function.identity()));
            List<BargainVO> collect = page.getRecords().stream().map(BargainConver.INSTANCT::getBargainVO).collect(Collectors.toList());
            for (BargainVO bargainVO : collect) {
                BargainProduct bargainProduct = productMap.get(bargainVO.getProductId());
                if (Objects.nonNull(bargainProduct)) {
                    bargainVO.setProductType(bargainProduct.getProductName());
                }
            }
            model.addAttribute("list", collect);
        }
        model.addAttribute("totalCount", page.getTotal());
        model.addAttribute("bargainSwitchData", apiCodeFirst.getCd());
        model.addAttribute("bargainTypeVal", Objects.isNull(vo.getProductId())?-1:vo.getProductId());
        return "admin/basic/bargain/list";
    }

    /**
     * 详情
     *
     * @param model
     * @param vo
     * @return
     */
    @PostMapping("/detail.do")
    public String detail(Model model, @ModelAttribute("vo") BargainVO vo) {
        bargainService.detail(model, vo);
        return "admin/basic/bargain/detail";
    }


    /**
     * 跳转到新增页
     *
     * @param model
     * @param vo
     * @return
     */
    @PostMapping("/registerForm.do")
    public String registerForm(Model model, @ModelAttribute("vo") BargainVO vo) {
        model.addAttribute("detail", vo);
        return "admin/basic/bargain/registerForm";
    }

    /**
     * 新增数据接口
     *
     * @param request
     * @param vo
     * @return
     */
    @PostMapping("/register.do")
    @CleanCacheAnnotion
    public String register(HttpServletRequest request, HttpServletResponse response, Model model, @ModelAttribute("vo") BargainVO vo, MultipartHttpServletRequest muServletRequest) {
        try {
            bargainService.register(request, response, vo, muServletRequest);
            ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.write.success"));
            return "redirect:" + XmlPropertyManager.getPropertyValue("system.admin.path") + "/bargain/list.do";
        } catch (Exception e) {
            model.addAttribute("msg_type", ":submit");
            model.addAttribute("method", "post");
            model.addAttribute("msg", e.getMessage());
            model.addAttribute("url", "./registerForm.do");
            return "admin/common/message";
        }
    }


    /**
     * 跳转到修改页
     *
     * @param model
     * @param vo
     * @return
     */
    @PostMapping("/updateForm.do")
    public String updateForm(Model model, @ModelAttribute("vo") BargainVO vo) {
        bargainService.updateForm(model, vo);
        return "admin/basic/bargain/registerForm";
    }

    /**
     * 修改
     *
     * @param model
     * @param vo
     * @return
     */
    @PostMapping("/update.do")
    @CleanCacheAnnotion
    public String doUpdate(HttpServletRequest request, HttpServletResponse response, Model model, @ModelAttribute("vo") BargainVO vo, MultipartHttpServletRequest muServletRequest) {


        Map<String, Object> hmParam = new HashMap<>();
        hmParam.put("id", vo.getId());
        model.addAttribute("msg_type", ":submit");
        model.addAttribute("method", "post");
        model.addAttribute("msg", XmlMessageManager.getMessageValue("message.common.modify.success"));
        model.addAttribute("url", "./detail.do");
        model.addAttribute("hmParam", hmParam);
        try {
            bargainService.doUpdate(request, response, vo,muServletRequest);
            return "admin/common/message";
        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            model.addAttribute("url", "./updateForm.do");
            return "admin/common/message";
        }
    }

    /**
     * 删除
     *
     * @param request
     * @param vo
     * @return
     */
    @PostMapping("/doDelete.do")
    @CleanCacheAnnotion
    public String delete(HttpServletRequest request, Model model, @ModelAttribute("vo") Bargain vo) {
        try {
            bargainService.doDelete(vo);
            Map<String, String> hmParam = new HashMap<String, String>();
            model.addAttribute("msg_type", ":submit");
            model.addAttribute("msg", XmlMessageManager.getMessageValue("message.common.delete.success"));
            model.addAttribute("url", "./list.do");
            model.addAttribute("hmParam", hmParam);
            return "admin/common/message";
        } catch (Exception e) {
            e.printStackTrace();
            ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.delete.fail"), null, null, null, true);
            return "redirect:" + XmlPropertyManager.getPropertyValue("system.admin.path") + "/bargain/detail.do";
        }
    }

    @ResponseBody
    @GetMapping("/imgView.do")
    public String imgView(HttpServletRequest request, HttpServletResponse response, Model model,
                          @ModelAttribute("vo") Bargain vo) {
        Bargain product = bargainService.getById(vo.getId());
        try {
            if (ObjectUtil.isNotEmpty(product)) {
                FileDownLoadManager fileDownLoadManager = new FileDownLoadManager(request, response);
                SafeFile pcListFile = new SafeFile(product.getListImgPath(), FilenameUtils.getName(product.getListImgFileNm()));
                if (pcListFile.isFile()) {
                    fileDownLoadManager.fileFlush(pcListFile, product.getListImgOrgFileNm());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 跳转到修改页
     *
     * @param model
     * @param vo
     * @return
     */
    @PostMapping("/bargainSwitch.do")
    @CleanCacheAnnotion
    public String bargainSwitch(Model model, @ModelAttribute("vo") BargainVO vo) {
        ApiCodeVO apiCodeVO = new ApiCodeVO();
        apiCodeVO.setGrpCd("BARGAIN_SWITCH");
        apiCodeVO.setLangCd("cn-zh");
        ApiCodeVO apiCodeFirst = apiCodeService.getApiCodeFirst(apiCodeVO);
        if (Objects.equals(apiCodeFirst.getCd(),"Y")) {
            apiCodeFirst.setCd("N");
        }else {
            apiCodeFirst.setCd("Y");
        }
        apiCodeService.setCodeByGrpCd(apiCodeFirst);
        return "redirect:" + XmlPropertyManager.getPropertyValue("system.admin.path") + "/bargain/list.do";
    }

    @GetMapping("/download.do")
    public void download(HttpServletResponse response) throws Exception {
        Map<Integer, String[]> map = Maps.newHashMap();
        String[] strings = bargainProductService.lambdaQuery().list().stream().map(BargainProduct::getProductName).toArray(String[]::new);
        map.put(0,strings);
        map.put(1,new String[]{"Y","N"});
        EasyExcelUtils.writeExcelImp(response, data(), "特价舱模板", "特价舱模板",
                BargainUploadVO.class,new CommonSpinnerWriteHandler(map));
    }

    private List<BargainUploadVO> data() {
        List<BargainUploadVO> list = ListUtils.newArrayList();
        for (int i = 0; i < 10; i++) {
            BargainUploadVO data = new BargainUploadVO();
            list.add(data);
        }
        return list;
    }

    /**
     * 文件上传
     * <p>
     * <p>
     * <p>
     * 3. 直接读即可
     */
    @PostMapping("/upload.do")
    @ResponseBody
    public String upload(Model model,MultipartFile file) throws IOException {
        UploadException uploadException = new UploadException();
        EasyExcel.read(file.getInputStream(), BargainUploadVO.class, new BargainUploadListener(bargainService,adminSeoService,uploadException)).sheet().doRead();
        return uploadException.getMessage();
    }

    @GetMapping("/exportTerm.do")
    public void exportTerm(HttpServletResponse response,String searchValue,Long productId) throws IOException {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("特价舱", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=" + fileName + ".xlsx");

        EasyExcel.write(response.getOutputStream(), BargainExportVO.class).sheet("特价舱").doWrite(bargainService.exportTerm(searchValue,productId));
    }



    /**
     * 显示热卖
     */
    @PostMapping("/showHot.do")
    @ResponseBody
    public String showHot(@RequestBody Map<String,Object> req) throws IOException {
        List<String> ids = (List<String>) req.get("ids");
        List<Long> collect = ids.stream().map(Long::parseLong).collect(Collectors.toList());
        bargainService.lambdaUpdate().set(Bargain::getHotFlag,"Y").in(Bargain::getId,collect).update();
        return "success";
    }

    /**
     * 不显示热卖
     */
    @PostMapping("/blankHot.do")
    @ResponseBody
    public String blankHot(@RequestBody Map<String,Object> req) throws IOException {
        List<String> ids = (List<String>) req.get("ids");
        List<Long> collect = ids.stream().map(Long::parseLong).collect(Collectors.toList());
        bargainService.lambdaUpdate().set(Bargain::getHotFlag,"N").in(Bargain::getId,collect).update();
        return "success";
    }

    /**
     * 不显示热卖
     */
    @GetMapping("/checkLinkName.do")
    @ResponseBody
    public String checkLinkName(@RequestParam String start
            ,@RequestParam String end
            ,@RequestParam Long productId
            ,@RequestParam Long id) {
        Long count = bargainService.lambdaQuery()
                .eq(Bargain::getOrigin, start)
                .eq(Bargain::getDestination, end)
                .ne(Objects.nonNull(id), Bargain::getId, id)
                .eq(Bargain::getProductId, productId).count();
        if (count>0) {
            return "N";
        }
        return "Y";
    }


    /**
     * 显示热卖
     */
    @PostMapping("/batchDel.do")
    @ResponseBody
    public String batchDel(@RequestBody Map<String,Object> req) throws IOException {
        List<String> ids = (List<String>) req.get("ids");
        List<Long> collect = ids.stream().map(Long::parseLong).collect(Collectors.toList());
        bargainService.removeBatchByIds(collect);
        return "success";
    }
}
