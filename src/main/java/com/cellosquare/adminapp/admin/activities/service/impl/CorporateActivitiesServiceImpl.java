package com.cellosquare.adminapp.admin.activities.service.impl;


import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bluewaves.lab.property.XmlPropertyManager;
import com.bluewaves.lab.util.StringUtil;
import com.cellosquare.adminapp.admin.activities.conver.CorporateActivitiesVoConver;
import com.cellosquare.adminapp.admin.activities.entity.CorporateActivities;
import com.cellosquare.adminapp.admin.activities.mapper.CorporateActivitiesMapper;
import com.cellosquare.adminapp.admin.activities.service.ICorporateActivitiesService;
import com.cellosquare.adminapp.admin.activities.vo.CorporateActivitiesVo;
import com.cellosquare.adminapp.admin.advertising.conver.AdvertisingConver;
import com.cellosquare.adminapp.admin.advertising.vo.AdvertisingVO;
import com.cellosquare.adminapp.admin.antistop.service.impl.AntistopServiceImpl;
import com.cellosquare.adminapp.admin.logisticsQa.conver.LogisticsQaVoConver;
import com.cellosquare.adminapp.admin.logisticsQa.entity.LogisticsQa;
import com.cellosquare.adminapp.admin.logisticsQa.vo.LogisticsQaVo;
import com.cellosquare.adminapp.admin.manager.mapper.AdminManagerMapper;
import com.cellosquare.adminapp.admin.seo.service.AdminSeoService;
import com.cellosquare.adminapp.admin.seo.vo.AdminSeoVO;
import com.cellosquare.adminapp.admin.terminology.service.impl.TerminologyServiceImpl;
import com.cellosquare.adminapp.common.constant.ActivitiesEnum;
import com.cellosquare.adminapp.common.constant.SeoModuleEnum;
import com.cellosquare.adminapp.common.constant.UseEnum;
import com.cellosquare.adminapp.common.enums.OperationEnum;
import com.cellosquare.adminapp.common.enums.TermTypeEnum;
import com.cellosquare.adminapp.common.session.AdminSessionForm;
import com.cellosquare.adminapp.common.session.SessionManager;
import com.cellosquare.adminapp.common.upload.FileUploadManager;
import com.cellosquare.adminapp.common.upload.FileUploadVO;
import com.cellosquare.adminapp.common.util.DateUtils;
import com.cellosquare.adminapp.common.util.SeoUtils;
import com.cellosquare.adminapp.common.util.XssUtils;
import com.cellosquare.adminapp.common.vo.BaseSeoParam;
import com.nhncorp.lucy.security.xss.XssPreventer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
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
 * @since 2023-03-67 14:44:39
 */
@Service
public class CorporateActivitiesServiceImpl extends ServiceImpl<CorporateActivitiesMapper, CorporateActivities> implements ICorporateActivitiesService {
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
    public void dealData(CorporateActivitiesVo vo) {
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
        //转换类型
        vo.setTypeName(ActivitiesEnum.getEnumByCode(vo.getType()).getCnValue());
    }

    @Override
    public void getList(Model model, CorporateActivitiesVo vo) {
        LambdaQueryChainWrapper<CorporateActivities> lambdaQueryChainWrapper = lambdaQuery()
                .eq(StrUtil.isNotEmpty(vo.getType()), CorporateActivities::getType, vo.getType())
                .like(StrUtil.isNotEmpty(vo.getName()), CorporateActivities::getName, vo.getName())
                .orderByDesc(CorporateActivities::getId);
        if (StrUtil.isNotEmpty(vo.getStartDate()) && StrUtil.isNotEmpty(vo.getEndDate())) {
            lambdaQueryChainWrapper.ge(CorporateActivities::getInsDtm, DateUtils.getDateForMatt(0, vo.getStartDate()))
                    .le(CorporateActivities::getInsDtm, DateUtils.getDateForMatt(1, vo.getEndDate()));
        }
        Page<CorporateActivities> page = lambdaQueryChainWrapper
                .page(new Page<>(Integer.parseInt(vo.getPage()), Integer.parseInt(vo.getRowPerPage())));
        List<CorporateActivitiesVo> CorporateActivitiesVos = page.getRecords().stream().map(CorporateActivitiesVoConver.INSTANCT::getCorporateActivitiesVo).collect(Collectors.toList());
        List<CorporateActivitiesVo> collect = CorporateActivitiesVos.stream().map(CorporateActivitiesVo -> {
            dealData(CorporateActivitiesVo);
            return CorporateActivitiesVo;
        }).collect(Collectors.toList());
        model.addAttribute("totalCount", page.getTotal());
        model.addAttribute("list", collect);
    }

    @Override
    public void detail(Model model, CorporateActivitiesVo vo) {
        CorporateActivities result = lambdaQuery().eq(CorporateActivities::getId, Long.valueOf(vo.getId())).one();
        CorporateActivitiesVo corporateActivitiesVo = CorporateActivitiesVoConver.INSTANCT.getCorporateActivitiesVo(result);
        dealData(corporateActivitiesVo);
        corporateActivitiesVo.setContents(XssPreventer.unescape(corporateActivitiesVo.getContents()));
        // seo 정보 가져오기
        AdminSeoVO adminSeoVO = adminSeoServiceImpl.getSeoSelect(corporateActivitiesVo);
        getTags(corporateActivitiesVo);
        model.addAttribute("adminSeoVO", adminSeoVO);
        model.addAttribute("detail", corporateActivitiesVo);
        model.addAttribute("vo", corporateActivitiesVo);
    }

    @Override
    public void register(HttpServletRequest request, HttpServletResponse response, CorporateActivitiesVo vo, MultipartHttpServletRequest muServletRequest) throws Exception {

        adminSeoServiceImpl.doSeoWriteV2(request, response, null, vo);
        upLoadPic(response, vo, muServletRequest);
        CorporateActivities corporateActivities = CorporateActivitiesVoConver.INSTANCT.getCorporateActivities(vo);
        setMsg(request, corporateActivities);
        //处理摘要
        corporateActivities.setSummaryInfo(SeoUtils.getSummaryInfo(corporateActivities.getSummaryInfo(), corporateActivities.getContents()));
        save(corporateActivities);
        saveTags(corporateActivities, vo,OperationEnum.INSERT);
        //查询tag
        CorporateActivitiesVo corporateActivitiesVo = CorporateActivitiesVoConver.INSTANCT.getCorporateActivitiesVo(corporateActivities);
        getTags(corporateActivitiesVo);
        //保存seo信息
        BaseSeoParam baseSeoParam = new BaseSeoParam(SeoModuleEnum.CORPORATEACTIVITIES.getCode(), corporateActivitiesVo.getName(), corporateActivitiesVo.getSummaryInfo(),
                corporateActivitiesVo.getContents(), corporateActivitiesVo.getTerminologyTagList(), corporateActivitiesVo);
        SeoUtils.setSeoMsg(baseSeoParam);
        adminSeoServiceImpl.doSeoUpdatev2(request, response, null, corporateActivitiesVo);
    }

    @Override
    public void doUpdate(HttpServletRequest request, HttpServletResponse response, CorporateActivitiesVo vo, MultipartHttpServletRequest muServletRequest) throws Exception {
        //上传图片
        upLoadPic(response, vo, muServletRequest);
        CorporateActivities corporateActivities = CorporateActivitiesVoConver.INSTANCT.getCorporateActivities(vo);
        setMsg(request, corporateActivities);
        updateById(corporateActivities);
        saveTags(corporateActivities, vo,OperationEnum.UPDATE);
        //查询tag
        getTags(vo);
        //设置seo信息
        BaseSeoParam baseSeoParam = new BaseSeoParam(SeoModuleEnum.CORPORATEACTIVITIES.getCode(), vo.getName(), vo.getSummaryInfo(),
                vo.getContents(), vo.getTerminologyTagList(), vo);
        SeoUtils.setSeoMsg(baseSeoParam);
        adminSeoServiceImpl.doSeoUpdatev2(request, response, null, vo);
    }

    @Override
    public void updateForm(Model model, CorporateActivitiesVo vo) {
        CorporateActivities corporateActivities = getById(Long.valueOf(vo.getId()));
        CorporateActivitiesVo corporateActivitiesVo = CorporateActivitiesVoConver.INSTANCT.getCorporateActivitiesVo(corporateActivities);
        AdminSeoVO adminSeoVO = adminSeoServiceImpl.getSeoSelect(corporateActivitiesVo);
        getTags(corporateActivitiesVo);
        model.addAttribute("adminSeoVO", adminSeoVO);
        model.addAttribute("detail", corporateActivitiesVo);
        model.addAttribute("contIU", "U");
    }

    @Override
    public void doDelete(CorporateActivitiesVo vo) {
        CorporateActivities corporateActivities = getById(Long.valueOf(vo.getId()));
        CorporateActivitiesVo corporateActivitiesVo = CorporateActivitiesVoConver.INSTANCT.getCorporateActivitiesVo(corporateActivities);
        removeById(corporateActivities.getId());
        //删除adminSeo
        adminSeoServiceImpl.doSeoDelete(corporateActivitiesVo);
    }

    @Override
    public List<CorporateActivitiesVo> allList() {
        return lambdaQuery().orderByDesc(CorporateActivities::getInsDtm).list().stream().map(CorporateActivitiesVoConver.INSTANCT::getCorporateActivitiesVo).collect(Collectors.toList());
    }

    /**
     * 上传图片
     *
     * @param response
     * @param vo
     * @param muServletRequest
     * @throws Exception
     */
    public void upLoadPic(HttpServletResponse response, CorporateActivitiesVo vo, MultipartHttpServletRequest muServletRequest) throws Exception {
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
    private void setMsg(HttpServletRequest request, CorporateActivities vo) {
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
    public void getTags(CorporateActivitiesVo vo) {
        //术语
        String term = terminologyService.getTerm(Long.valueOf(vo.getId()), TermTypeEnum.CROSS_BORDER);
        List<String> terms = terminologyService.getTerms(Long.valueOf(vo.getId()), TermTypeEnum.CROSS_BORDER);
        vo.setTerminologyTagList(terms);
        vo.setTerminologyTags(term);
        //关键词
        String topTags = antistopServiceImpl.getTerm(Long.valueOf(vo.getId()), TermTypeEnum.CROSS_BORDER);
        vo.setAntistopTags(topTags);
    }

    /**
     * 设置标签
     *
     * @param corporateActivities
     * @param vo
     */
    public void saveTags(CorporateActivities corporateActivities, CorporateActivitiesVo vo, OperationEnum operationEnum) {
        //术语
        if (ArrayUtil.isNotEmpty(vo.getTerminologyTags())){
            String[] split = vo.getTerminologyTags().split(",");
            terminologyService.marchingTerm(corporateActivities.getId(), TermTypeEnum.CROSS_BORDER, corporateActivities.getName(),operationEnum, split);
        }
        //关键词
        if (ArrayUtil.isNotEmpty(vo.getAntistopTags())){
            String[] antistopTags_arr = vo.getAntistopTags().split(",");
            antistopServiceImpl.marchingTerm(corporateActivities.getId(), TermTypeEnum.CROSS_BORDER, corporateActivities.getName(),operationEnum, antistopTags_arr);
        }
    }
}
