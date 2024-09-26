package com.cellosquare.adminapp.admin.logisticsQa.service.impl;


import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bluewaves.lab.property.XmlPropertyManager;
import com.bluewaves.lab.util.StringUtil;
import com.cellosquare.adminapp.admin.advertising.conver.AdvertisingConver;
import com.cellosquare.adminapp.admin.advertising.vo.AdvertisingVO;
import com.cellosquare.adminapp.admin.antistop.service.impl.AntistopServiceImpl;
import com.cellosquare.adminapp.admin.helpSupport.conver.HelpSupportVoConver;
import com.cellosquare.adminapp.admin.helpSupport.entity.HelpSupport;
import com.cellosquare.adminapp.admin.helpSupport.vo.HelpSupportVo;
import com.cellosquare.adminapp.admin.helpSupportMenu.service.IHelpSupportMenuService;
import com.cellosquare.adminapp.admin.logisticsQa.conver.LogisticsQaVoConver;
import com.cellosquare.adminapp.admin.logisticsQa.entity.LogisticsQa;
import com.cellosquare.adminapp.admin.logisticsQa.mapper.LogisticsQaMapper;
import com.cellosquare.adminapp.admin.logisticsQa.service.ILogisticsQaService;
import com.cellosquare.adminapp.admin.logisticsQa.vo.LogisticsQaVo;
import com.cellosquare.adminapp.admin.manager.mapper.AdminManagerMapper;
import com.cellosquare.adminapp.admin.seo.service.AdminSeoService;
import com.cellosquare.adminapp.admin.seo.vo.AdminSeoVO;
import com.cellosquare.adminapp.admin.terminology.service.impl.TerminologyServiceImpl;
import com.cellosquare.adminapp.common.constant.SeoModuleEnum;
import com.cellosquare.adminapp.common.constant.UseEnum;
import com.cellosquare.adminapp.common.constant.WordUploadTypeEnum;
import com.cellosquare.adminapp.common.enums.OperationEnum;
import com.cellosquare.adminapp.common.enums.TermTypeEnum;
import com.cellosquare.adminapp.common.session.AdminSessionForm;
import com.cellosquare.adminapp.common.session.SessionManager;
import com.cellosquare.adminapp.common.upload.FileUploadManager;
import com.cellosquare.adminapp.common.upload.FileUploadVO;
import com.cellosquare.adminapp.common.util.DateUtils;
import com.cellosquare.adminapp.common.util.SeoUtils;
import com.cellosquare.adminapp.common.util.WordOperationUtils;
import com.cellosquare.adminapp.common.util.XssUtils;
import com.cellosquare.adminapp.common.vo.BaseSeoParam;
import com.cellosquare.adminapp.common.vo.BaseWordData;
import com.nhncorp.lucy.security.xss.XssPreventer;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author hugo
 * @since 2023-03-67 09:03:09
 */
@Service
public class LogisticsQaServiceImpl extends ServiceImpl<LogisticsQaMapper, LogisticsQa> implements ILogisticsQaService {
    @Autowired
    private AdminManagerMapper adminManagerMapper;
    @Autowired
    private AdminSeoService adminSeoServiceImpl;
    @Autowired
    private TerminologyServiceImpl terminologyService;
    @Autowired
    private AntistopServiceImpl antistopServiceImpl;

    /**
     * 处理数据
     *
     * @param vo
     */
    public void dealData(LogisticsQaVo vo) {
        if (Objects.isNull(vo)) {
            return;
        }
        vo.setUseYnNm(UseEnum.getEnumByCode(vo.getUseYn()).getCnValue());
        //查询用户
        if (StringUtils.isNotBlank(vo.getInsPersonId())) {
            vo.setInsPersonNm(adminManagerMapper.getByUserId(vo.getInsPersonId()));
        }
        if (StringUtils.isNotBlank(vo.getUpdPersonId())) {
            vo.setUpdPersonNm(adminManagerMapper.getByUserId(vo.getUpdPersonId()));
        }
    }

    @Override
    public void getList(Model model, LogisticsQaVo vo) {
        LambdaQueryChainWrapper<LogisticsQa> helpSupportLambdaQueryChainWrapper = lambdaQuery()
                .like(StrUtil.isNotEmpty(vo.getName()), LogisticsQa::getName, vo.getName())
                .orderByDesc(LogisticsQa::getInsDtm);
        if (StrUtil.isNotEmpty(vo.getStartDate()) && StrUtil.isNotEmpty(vo.getEndDate())) {
            helpSupportLambdaQueryChainWrapper.ge(LogisticsQa::getInsDtm, DateUtils.getDateForMatt(0, vo.getStartDate()))
                    .le(LogisticsQa::getInsDtm, DateUtils.getDateForMatt(1, vo.getEndDate()));
        }
        Page<LogisticsQa> page = helpSupportLambdaQueryChainWrapper
                .page(new Page<>(Integer.parseInt(vo.getPage()), Integer.parseInt(vo.getRowPerPage())));
        List<LogisticsQaVo> logisticsQaVos = page.getRecords().stream().map(LogisticsQaVoConver.INSTANCT::getLogisticsQaVo).collect(Collectors.toList());
        List<LogisticsQaVo> collect = logisticsQaVos.stream().map(logisticsQaVo -> {
            dealData(logisticsQaVo);
            return logisticsQaVo;
        }).collect(Collectors.toList());
        model.addAttribute("totalCount", page.getTotal());
        model.addAttribute("list", collect);
    }

    @Override
    public void detail(Model model, LogisticsQaVo vo) {
        LogisticsQa result = lambdaQuery().eq(LogisticsQa::getId, Long.valueOf(vo.getId())).one();
        LogisticsQaVo logisticsQaVo = LogisticsQaVoConver.INSTANCT.getLogisticsQaVo(result);
        dealData(logisticsQaVo);
        logisticsQaVo.setContents(XssPreventer.unescape(logisticsQaVo.getContents()));
        // seo 정보 가져오기
        AdminSeoVO adminSeoVO = adminSeoServiceImpl.getSeoSelect(logisticsQaVo);
        getTags(logisticsQaVo);
        model.addAttribute("adminSeoVO", adminSeoVO);
        model.addAttribute("detail", logisticsQaVo);
        model.addAttribute("vo", logisticsQaVo);
    }

    @Override
    public void register(HttpServletRequest request, HttpServletResponse response, LogisticsQaVo vo, MultipartHttpServletRequest muServletRequest) throws Exception {
        upLoadPic(response, vo, muServletRequest);
        adminSeoServiceImpl.doSeoWriteV2(request, response, null, vo);
        LogisticsQa logisticsQa = LogisticsQaVoConver.INSTANCT.getLogisticsQa(vo);
        setMsg(request, logisticsQa);
        //处理摘要
        logisticsQa.setSummaryInfo(SeoUtils.getSummaryInfo(logisticsQa.getSummaryInfo(), logisticsQa.getContents()));
        save(logisticsQa);
        saveTags(logisticsQa, vo,OperationEnum.INSERT);
        //查询tag
        LogisticsQaVo logisticsQaVo = LogisticsQaVoConver.INSTANCT.getLogisticsQaVo(logisticsQa);
        getTags(logisticsQaVo);
        //保存seo信息
        BaseSeoParam baseSeoParam = new BaseSeoParam(SeoModuleEnum.LOGISTICSQA.getCode(), logisticsQaVo.getName(), logisticsQaVo.getSummaryInfo(),
                logisticsQaVo.getContents(), logisticsQaVo.getTerminologyTagList(), logisticsQaVo);
        SeoUtils.setSeoMsg(baseSeoParam);
        adminSeoServiceImpl.doSeoUpdatev2(request, response, null, logisticsQaVo);
    }

    @Override
    public void updateForm(Model model, LogisticsQaVo vo) {
        LogisticsQa logisticsQa = getById(Long.valueOf(vo.getId()));
        LogisticsQaVo logisticsQaVo = LogisticsQaVoConver.INSTANCT.getLogisticsQaVo(logisticsQa);
        AdminSeoVO adminSeoVO = adminSeoServiceImpl.getSeoSelect(logisticsQaVo);
        getTags(logisticsQaVo);
        model.addAttribute("adminSeoVO", adminSeoVO);
        model.addAttribute("detail", logisticsQaVo);
        model.addAttribute("contIU", "U");
    }

    @Override
    public void doUpdate(HttpServletRequest request, HttpServletResponse response, LogisticsQaVo vo, MultipartHttpServletRequest muServletRequest) throws Exception {
        //上传图片
        upLoadPic(response, vo, muServletRequest);
        LogisticsQa logisticsQa = LogisticsQaVoConver.INSTANCT.getLogisticsQa(vo);
        setMsg(request, logisticsQa);
        updateById(logisticsQa);
        saveTags(logisticsQa, vo,OperationEnum.UPDATE);
        //查询tag
        getTags(vo);
        //设置seo信息
        BaseSeoParam baseSeoParam = new BaseSeoParam(SeoModuleEnum.LOGISTICSQA.getCode(), vo.getName(), vo.getSummaryInfo(),
                vo.getContents(), vo.getTerminologyTagList(), vo);
        SeoUtils.setSeoMsg(baseSeoParam);
        adminSeoServiceImpl.doSeoUpdatev2(request, response, null, vo);
    }

    @Override
    public void doDelete(LogisticsQaVo vo) {
        LogisticsQa logisticsQa = getById(Long.valueOf(vo.getId()));
        LogisticsQaVo logisticsQaVo = LogisticsQaVoConver.INSTANCT.getLogisticsQaVo(logisticsQa);
        removeById(Long.valueOf(vo.getId()));
        //删除adminSeo
        adminSeoServiceImpl.doSeoDelete(logisticsQaVo);
    }

    @Override
    public void importWord(MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<BaseWordData> baseWordDataList = WordOperationUtils.importWord(file, WordUploadTypeEnum.QA.getCode());
        List<LogisticsQa> logisticsQas = new ArrayList<>();
        baseWordDataList.forEach(baseWordData -> {
            LogisticsQa logisticsQa = new LogisticsQa();
            logisticsQa.setName(baseWordData.getTitle());
            logisticsQa.setSummaryInfo(baseWordData.getSummaryInfo());
            logisticsQa.setUseYn(UseEnum.USE.getCode());
            logisticsQa.setContents(baseWordData.getContents());
            setMsg(request, logisticsQa);

            LogisticsQaVo vo = LogisticsQaVoConver.INSTANCT.getLogisticsQaVo(logisticsQa);
            BaseSeoParam baseSeoParam = new BaseSeoParam(SeoModuleEnum.LOGISTICSQA.getCode(), vo.getName(), vo.getSummaryInfo(),
                    vo.getContents(), vo.getTerminologyTagList(), vo);
            SeoUtils.setSeoMsg(baseSeoParam);
            adminSeoServiceImpl.doSeoWriteV2(request, response, null, vo);
            logisticsQa.setMetaSeqNo(Integer.valueOf(vo.getMetaSeqNo()));
            logisticsQas.add(logisticsQa);

            //是否要关联术语关键词
        });
        if (CollectionUtils.isNotEmpty(logisticsQas)) {
            //保存
            saveBatch(logisticsQas);
        }
    }

    /**
     * 上传图片
     *
     * @param response
     * @param vo
     * @param muServletRequest
     * @throws Exception
     */
    public void upLoadPic(HttpServletResponse response, LogisticsQaVo vo, MultipartHttpServletRequest muServletRequest) throws Exception {
        //图片部分
        if (!vo.getListOrginFile().isEmpty()) {
            if (FileUploadManager.isUploadImageValid(muServletRequest, response, true, "listOrginFile")) {
                String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathGoods")));
                FileUploadVO fileVO = FileUploadManager.uploadImageFile(muServletRequest, path, "listOrginFile");
                vo.setListImgPath(fileVO.getFilePath());
                vo.setListImgFileNm(fileVO.getFileTempName());
                vo.setListImgOrgFileNm(fileVO.getFileOriginName());
            } else {
                throw new Exception();
            }
        }
    }

    /**
     * 设置信息
     *
     * @param request
     * @param vo
     */
    private void setMsg(HttpServletRequest request, LogisticsQa vo) {
        AdminSessionForm sessionForm = SessionManager.getAdminSessionForm();
        if (vo.getId() == null) {//新增
            vo.setInsPersonId(sessionForm.getAdminId());
            vo.setUpdPersonId(sessionForm.getAdminId());
            vo.setInsDtm(new Timestamp(new Date().getTime()));
            vo.setUpdDtm(new Timestamp(new Date().getTime()));
        } else {//修改
            vo.setUpdPersonId(sessionForm.getAdminId());
            vo.setUpdDtm(new Timestamp(new Date().getTime()));
        }
        //处理富文本信息
        vo.setContents(XssUtils.operationContent(vo.getContents(), vo.getName()));
    }

    /**
     * 将标签赋值
     *
     * @param vo
     */
    public void getTags(LogisticsQaVo vo) {
        //术语
        String term = terminologyService.getTerm(Long.valueOf(vo.getId()), TermTypeEnum.DYNAMICS_LOGISTICS_LIST);
        vo.setTerminologyTags(term);
        List<String> terms = terminologyService.getTerms(Long.valueOf(vo.getId()), TermTypeEnum.DYNAMICS_LOGISTICS_LIST);
        vo.setTerminologyTagList(terms);
        //关键词
        String topTags = antistopServiceImpl.getTerm(Long.valueOf(vo.getId()), TermTypeEnum.DYNAMICS_LOGISTICS_LIST);
        vo.setAntistopTags(topTags);
    }

    /**
     * 设置标签
     *
     * @param logisticsQa
     * @param vo
     */
    public void saveTags(LogisticsQa logisticsQa, LogisticsQaVo vo, OperationEnum operationEnum) {
        //术语
        if (ArrayUtil.isNotEmpty(vo.getTerminologyTags())){
            String[] split = vo.getTerminologyTags().split(",");
            terminologyService.marchingTerm(logisticsQa.getId(), TermTypeEnum.DYNAMICS_LOGISTICS_LIST, logisticsQa.getName(),operationEnum, split);
        }
        //关键词
        if (ArrayUtil.isNotEmpty(vo.getAntistopTags())){
            String[] antistopTags_arr = vo.getAntistopTags().split(",");
            antistopServiceImpl.marchingTerm(logisticsQa.getId(), TermTypeEnum.DYNAMICS_LOGISTICS_LIST, logisticsQa.getName(),operationEnum, antistopTags_arr);
        }
    }
}
