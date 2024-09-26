package com.cellosquare.adminapp.admin.quote.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bluewaves.lab.property.XmlPropertyManager;
import com.bluewaves.lab.util.StringUtil;
import com.cellosquare.adminapp.admin.antistop.entity.Antistop;
import com.cellosquare.adminapp.admin.manager.mapper.AdminManagerMapper;
import com.cellosquare.adminapp.admin.quote.cover.QuoteVoConver;
import com.cellosquare.adminapp.admin.quote.entity.QuoteNation;
import com.cellosquare.adminapp.admin.quote.entity.QuoteNode;
import com.cellosquare.adminapp.admin.quote.entity.vo.AdminNationVO;
import com.cellosquare.adminapp.admin.quote.entity.vo.AdminNodeVO;
import com.cellosquare.adminapp.admin.quote.entity.vo.QuoteNationExportVo;
import com.cellosquare.adminapp.admin.quote.mapper.QuoteNationMapper;
import com.cellosquare.adminapp.admin.quote.service.IQuoteNationService;
import com.cellosquare.adminapp.admin.quote.service.IQuoteNodeService;
import com.cellosquare.adminapp.common.constant.ContinentCdEnum;
import com.cellosquare.adminapp.common.constant.ExpressUseEnum;
import com.cellosquare.adminapp.common.constant.IsHotEnum;
import com.cellosquare.adminapp.common.session.AdminSessionForm;
import com.cellosquare.adminapp.common.session.SessionManager;
import com.cellosquare.adminapp.common.upload.FileUploadManager;
import com.cellosquare.adminapp.common.upload.FileUploadVO;
import com.cellosquare.adminapp.common.util.EasyExcelUtils;
import com.cellosquare.adminapp.common.util.NationUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
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
 * @since 2023-06-157 09:11:30
 */
@Service
public class QuoteNationServiceImpl extends ServiceImpl<QuoteNationMapper, QuoteNation> implements IQuoteNationService {
    @Autowired
    private AdminManagerMapper adminManagerMapper;

    @Autowired
    private IQuoteNodeService quoteNodeServiceImpl;

    /**
     * 处理数据
     *
     * @param vo
     */
    public void dealData(AdminNationVO vo) {
        if (Objects.isNull(vo)) {
            return;
        }
        //查询用户
        if (org.apache.commons.lang3.StringUtils.isNotBlank(vo.getInsPersonId())) {
            vo.setInsPersonNm(adminManagerMapper.getByUserId(vo.getInsPersonId()));
        }
        if (org.apache.commons.lang3.StringUtils.isNotBlank(vo.getUpdPersonId())) {
            vo.setUpdPersonNm(adminManagerMapper.getByUserId(vo.getUpdPersonId()));
        }
        vo.setContinentNm(ContinentCdEnum.getEnumByCode(vo.getContinentCd()).getDesc());
        vo.setExpressUseValue(ExpressUseEnum.getEnumByCode(vo.getExpressUseYn()).getDesc());
        vo.setIsHotNm(IsHotEnum.getEnumByCode(vo.getIsHot()).getDesc());
    }

    @Override
    public Page<QuoteNation> getList(AdminNationVO vo) {
        LambdaQueryChainWrapper<QuoteNation> quoteNationLambdaQueryChainWrapper = commonSql(vo);
        Page<QuoteNation> page = quoteNationLambdaQueryChainWrapper
                .page(new Page<>(Integer.parseInt(vo.getPage()), Integer.parseInt(vo.getRowPerPage())));
        return page;
    }

    private LambdaQueryChainWrapper<QuoteNation> commonBaseSql(AdminNationVO vo) {
        LambdaQueryChainWrapper<QuoteNation> quoteNationLambdaQueryChainWrapper = lambdaQuery()
                .eq(StringUtils.isNotEmpty(vo.getContinentCd()), QuoteNation::getContinentCd, vo.getContinentCd());
        if (StringUtils.isNotEmpty(vo.getSearchType())) {
            quoteNationLambdaQueryChainWrapper
                    .eq(StringUtils.isNotEmpty(vo.getSearchType()), QuoteNation::getIsHot, Integer.valueOf(vo.getSearchType()));
        }
        if (StringUtils.isNotEmpty(vo.getSearchValue())) {
            quoteNationLambdaQueryChainWrapper.and(quoteNodeLambdaQueryWrapper -> {
                quoteNodeLambdaQueryWrapper.or().like(QuoteNation::getNationCd, vo.getSearchValue());
                quoteNodeLambdaQueryWrapper.or().like(QuoteNation::getNationNm, vo.getSearchValue());
                quoteNodeLambdaQueryWrapper.or().like(QuoteNation::getNationCnNm, vo.getSearchValue());
                quoteNodeLambdaQueryWrapper.or().like(QuoteNation::getContinentCd, vo.getSearchValue());
                quoteNodeLambdaQueryWrapper.or().like(QuoteNation::getSearchKeyWord, vo.getSearchValue());
            });
        }
        return quoteNationLambdaQueryChainWrapper;
    }

    private LambdaQueryChainWrapper<QuoteNation> commonSql(AdminNationVO vo) {
        return commonBaseSql(vo).last("ORDER BY ordb ::integer ASC,nation_seq_no desc");
    }

    @Override
    public List<QuoteNation> getAll() {
        List<QuoteNation> list = lambdaQuery().list();
        return list;
    }

    @Override
    public List<Long> getSearchNation(AdminNodeVO vo) {
        String val = vo.getSearchValue();
        String continentCd = vo.getContinentCd();

        LambdaQueryChainWrapper<QuoteNation> quoteNationLambdaQueryChainWrapper = lambdaQuery();
        if (StringUtils.isNotEmpty(continentCd)) {
            quoteNationLambdaQueryChainWrapper.eq(QuoteNation::getContinentCd, continentCd);
        }
        if (StringUtils.isNotEmpty(val)) {
            quoteNationLambdaQueryChainWrapper.and(quoteNationLambdaQueryWrapper -> {
                quoteNationLambdaQueryWrapper.like(QuoteNation::getNationCd, val).or().
                        like(QuoteNation::getNationNm, val).or().
                        like(QuoteNation::getNationCnNm, val).or().
                        like(QuoteNation::getSearchKeyWord, val).or();
            });
        }
        return quoteNationLambdaQueryChainWrapper.list().stream().map(QuoteNation::getNationSeqNo).collect(Collectors.toList());
    }

    /**
     * 设置信息
     *
     * @param vo
     */
    private void setMsg(QuoteNation vo) {
        AdminSessionForm sessionForm = SessionManager.getAdminSessionForm();
        if (vo.getNationSeqNo() == null) {//新增
            vo.setInsPersonId(sessionForm.getAdminId());
            vo.setUpdPersonId(sessionForm.getAdminId());
            vo.setInsDtm(new Timestamp(new Date().getTime()));
            vo.setUpdDtm(new Timestamp(new Date().getTime()));
        } else {//修改
            vo.setUpdPersonId(sessionForm.getAdminId());
            vo.setUpdDtm(new Timestamp(new Date().getTime()));
        }

    }

    @Override
    public void getList(Model model, AdminNationVO vo) {
        Page<QuoteNation> list = getList(vo);
        List<AdminNationVO> collect = list.getRecords().stream().map(QuoteVoConver.INSTANCT::getAdminNationVO).collect(Collectors.toList());
        List<AdminNationVO> nationVOS = collect.stream().map(adminNationVO -> {
            dealData(adminNationVO);
            return adminNationVO;
        }).collect(Collectors.toList());
        model.addAttribute("totalCount", list.getTotal());
        model.addAttribute("list", nationVOS);
    }

    @Override
    public void regist(AdminNationVO vo, HttpServletResponse response, MultipartHttpServletRequest muServletRequest) {
        if (StringUtils.isNotEmpty(vo.getNationCd())) {
            vo.setNationCd(vo.getNationCd().toUpperCase());
        }
        if (vo.getImgFileUpload() != null && !vo.getImgFileUpload().isEmpty()) {
            if (FileUploadManager.isUploadImageValid(muServletRequest, response, true, "imgFileUpload")) {
                String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathCountry")));
                FileUploadVO fileVO = FileUploadManager.uploadImageFile(muServletRequest, path, "imgFileUpload");
                vo.setImgFilePath(fileVO.getFilePath());
                vo.setImgFileNm(fileVO.getFileTempName());
                vo.setImgSize(String.valueOf(fileVO.getFileSize()));
                vo.setImgOrgFileNm(fileVO.getFileOriginName());
            }
        }
        QuoteNation quoteNation = QuoteVoConver.INSTANCT.getQuoteNation(vo);
        setMsg(quoteNation);
        save(quoteNation);
    }

    @Override
    public void updateForm(Model model, AdminNationVO vo) {
        QuoteNation one = lambdaQuery().eq(QuoteNation::getNationSeqNo, Long.valueOf(vo.getNationSeqNo())).one();
        AdminNationVO detailVO = QuoteVoConver.INSTANCT.getAdminNationVO(one);
        if (StringUtils.isNotEmpty(detailVO.getSearchKeyWord())) {
            String[] split = detailVO.getSearchKeyWord().split(",");
            detailVO.setListSearchKeyWord(split);
        }
        AdminSessionForm adminSessionForm = SessionManager.getAdminSessionForm();
        vo.setUpdPersonId(adminSessionForm.getAdminId());
        model.addAttribute("detail", detailVO);
        model.addAttribute("contIU", "U");
    }

    @Override
    public void doUpdate(HttpServletRequest request, HttpServletResponse response, MultipartHttpServletRequest muServletRequest, Model model, AdminNationVO vo) {
        if (StringUtils.isNotEmpty(vo.getNationCd())) {
            vo.setNationCd(vo.getNationCd().toUpperCase());
        }
        if (vo.getImgFileUpload() != null && !vo.getImgFileUpload().isEmpty()) {
            if (FileUploadManager.isUploadImageValid(muServletRequest, response, true, "imgFileUpload")) {
                String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathCountry")));

                FileUploadVO fileVO = FileUploadManager.uploadImageFile(muServletRequest, path, "imgFileUpload");

                vo.setImgFilePath(fileVO.getFilePath());
                vo.setImgFileNm(fileVO.getFileTempName());
                vo.setImgSize(String.valueOf(fileVO.getFileSize()));
                vo.setImgOrgFileNm(fileVO.getFileOriginName());
            }
        }
        QuoteNation quoteNation = QuoteVoConver.INSTANCT.getQuoteNation(vo);
        setMsg(quoteNation);
        updateById(quoteNation);
    }

    @Override
    public void detail(Model model, AdminNationVO vo) {
        QuoteNation one = lambdaQuery().eq(QuoteNation::getNationSeqNo, Long.valueOf(vo.getNationSeqNo())).one();
        AdminNationVO detailVO = QuoteVoConver.INSTANCT.getAdminNationVO(one);
        dealData(detailVO);
        model.addAttribute("detail", detailVO);
    }

    @Override
    public void delete(AdminNationVO vo) {
        removeById(Long.valueOf(vo.getNationSeqNo()));
    }

    @Override
    public void doSortOrder(List<AdminNationVO> adminNationVOList) {
        adminNationVOList.forEach(adminNationVO -> {
            lambdaUpdate().set(QuoteNation::getOrdb, StringUtils.isNotEmpty(adminNationVO.getOrdb()) ? adminNationVO.getOrdb() : null).
                    eq(QuoteNation::getNationSeqNo, Long.valueOf(adminNationVO.getNationSeqNo())).update();
        });
    }

    @Override
    public List<AdminNationVO> checkCountry(AdminNationVO vo) {
        return lambdaQuery().eq(QuoteNation::getNationCd, vo.getNationCd()).list().
                stream().map(QuoteVoConver.INSTANCT::getAdminNationVO).collect(Collectors.toList());
    }

    @Override
    public void excelDownLoad(HttpServletRequest request, HttpServletResponse response, AdminNationVO vo) throws Exception {
        List<AdminNationVO> collect = commonSql(vo).list().stream().map(QuoteVoConver.INSTANCT::getAdminNationVO).collect(Collectors.toList());
        List<AdminNationVO> nationVOS = collect.stream().map(adminNationVO -> {
            dealData(adminNationVO);
            return adminNationVO;
        }).collect(Collectors.toList());
        List<QuoteNationExportVo> nationExportVos = nationVOS.stream().map(QuoteVoConver.INSTANCT::getQuoteNationExportVo).collect(Collectors.toList());
        String fileName = "国家导出_" + DateUtil.formatTime(new Date());
        EasyExcelUtils.writeExcel(response, nationExportVos, fileName, "nation", QuoteNationExportVo.class);
    }

    @Override
    public int downloadCount(AdminNationVO vo) {
        return commonBaseSql(vo).count().intValue();
    }

    @Override
    public List<AdminNationVO> getNationByLangCd() {
        //查询全部节点的国家
        List<QuoteNode> list = quoteNodeServiceImpl.lambdaQuery().list();
        return lambdaQuery().in(QuoteNation::getNationSeqNo, list.stream().map(QuoteNode::getNationSeqNo).collect(Collectors.toList())).list()
                .stream().map(QuoteVoConver.INSTANCT::getAdminNationVO).collect(Collectors.toList());
    }

    @Override
    public AdminNationVO getDetailByNationCd(String nationCd) {
        List<AdminNationVO> collect = lambdaQuery().eq(QuoteNation::getNationCd, nationCd).list().stream().map(QuoteVoConver.INSTANCT::getAdminNationVO).collect(Collectors.toList());
        return collect.stream().findFirst().orElse(null);
    }

    @Override
    public void bianhuan() {
        List<QuoteNation> list = lambdaQuery().list();
        list.forEach(quoteNation -> {
            String nationCd = quoteNation.getNationCd();
            String countryNameCnByCountryCode = NationUtil.getCountryNameCnByCountryCode(nationCd);
            lambdaUpdate().set(QuoteNation::getNationCnNm, countryNameCnByCountryCode).
                    set(QuoteNation::getSearchKeyWord, null).
                    set(QuoteNation::getInsPersonId, "master").
                    set(QuoteNation::getUpdPersonId, "master").
                    set(QuoteNation::getImgFileNm, null).
                    set(QuoteNation::getImgFilePath, null).
                    set(QuoteNation::getImgSize, 0).
                    set(QuoteNation::getImgOrgFileNm, null).
                    eq(QuoteNation::getNationSeqNo, quoteNation.getNationSeqNo()).update();
        });
    }

    @Override
    public void updateImg(AdminNationVO vo) {
        update(Wrappers.<QuoteNation>lambdaUpdate().set(
                QuoteNation::getImgFileNm, vo.getImgFileNm())
                .set(QuoteNation::getImgOrgFileNm, vo.getImgOrgFileNm())
                .set(QuoteNation::getImgFilePath, vo.getImgFilePath())
                .set(QuoteNation::getImgSize, Integer.valueOf(vo.getImgSize())).eq(QuoteNation::getNationCd, vo.getNationCd())
        );

    }

    @Override
    public void setHotOrNot(AdminNationVO vo) {
        AdminSessionForm sessionForm = SessionManager.getAdminSessionForm();
        update(Wrappers.<QuoteNation>lambdaUpdate()
                .set(QuoteNation::getIsHot, vo.getIsHot())
                .set(QuoteNation::getUpdPersonId, sessionForm.getAdminId())
                .set(QuoteNation::getUpdDtm, new Timestamp(new Date().getTime()))
                .eq(QuoteNation::getNationSeqNo, Long.valueOf(vo.getNationSeqNo()))
        );
    }
}
