package com.cellosquare.adminapp.admin.helpSupport.service.impl;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.cron.pattern.CronPatternUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cellosquare.adminapp.admin.activities.vo.ActivitiesRegistrationExportVo;
import com.cellosquare.adminapp.admin.antistop.service.impl.AntistopServiceImpl;
import com.cellosquare.adminapp.admin.helpSupport.conver.HelpSupportVoConver;
import com.cellosquare.adminapp.admin.helpSupport.entity.HelpSupport;
import com.cellosquare.adminapp.admin.helpSupport.mapper.HelpSupportMapper;
import com.cellosquare.adminapp.admin.helpSupport.service.IHelpSupportService;
import com.cellosquare.adminapp.admin.helpSupport.vo.HelpSupportExportVo;
import com.cellosquare.adminapp.admin.helpSupport.vo.HelpSupportVo;
import com.cellosquare.adminapp.admin.helpSupportMenu.entity.HelpSupportMenu;
import com.cellosquare.adminapp.admin.helpSupportMenu.service.IHelpSupportMenuService;
import com.cellosquare.adminapp.admin.manager.mapper.AdminManagerMapper;
import com.cellosquare.adminapp.admin.seo.service.AdminSeoService;
import com.cellosquare.adminapp.admin.seo.vo.AdminSeoVO;
import com.cellosquare.adminapp.admin.terminology.service.impl.TerminologyServiceImpl;
import com.cellosquare.adminapp.common.constant.Constants;
import com.cellosquare.adminapp.common.constant.SeoModuleEnum;
import com.cellosquare.adminapp.common.constant.UseEnum;
import com.cellosquare.adminapp.common.constant.WordUploadTypeEnum;
import com.cellosquare.adminapp.common.enums.HelpSupportSourceEnum;
import com.cellosquare.adminapp.common.enums.HelpSupprtoMenuEnum;
import com.cellosquare.adminapp.common.enums.OperationEnum;
import com.cellosquare.adminapp.common.enums.TermTypeEnum;
import com.cellosquare.adminapp.common.session.AdminSessionForm;
import com.cellosquare.adminapp.common.session.SessionManager;
import com.cellosquare.adminapp.common.util.*;
import com.cellosquare.adminapp.common.vo.BaseSeoParam;
import com.cellosquare.adminapp.common.vo.BaseWordData;
import com.nhncorp.lucy.security.xss.XssPreventer;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author hugo
 * @since 2023-03-65 09:27:10
 */
@Service
public class HelpSupportServiceImpl extends ServiceImpl<HelpSupportMapper, HelpSupport> implements IHelpSupportService {
    @Autowired
    private AdminManagerMapper adminManagerMapper;
    @Autowired
    private AdminSeoService adminSeoServiceImpl;
    @Autowired
    private IHelpSupportMenuService helpSupportMenuServiceImpl;
    @Autowired
    private TerminologyServiceImpl terminologyService;
    @Autowired
    private AntistopServiceImpl antistopServiceImpl;

    /**
     * 处理数据
     *
     * @param vo
     */
    public void dealData(HelpSupportVo vo, Map<Long, String> mapMenu) {
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
        vo.setChildMenuName(mapMenu.get(Long.parseLong(vo.getChildMenuId())));
        vo.setTopName(mapMenu.get(Long.parseLong(vo.getTopId())));
    }

    /**
     * 设置信息
     *
     * @param request
     * @param vo
     */
    private void setMsg(HttpServletRequest request, HelpSupport vo) {
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

    @Override
    public void register(HttpServletRequest request, HttpServletResponse response, HelpSupportVo vo) {
        adminSeoServiceImpl.doSeoWriteV2(request, response, null, vo);
        HelpSupport helpSupport = HelpSupportVoConver.INSTANCT.getHelpSupport(vo);
        setMsg(request, helpSupport);
        //处理摘要
        helpSupport.setSummaryInfo(SeoUtils.getSummaryInfo(helpSupport.getSummaryInfo(), helpSupport.getContents()));
        save(helpSupport);
        saveTags(helpSupport, vo, OperationEnum.INSERT);
        //查询tag
        HelpSupportVo helpSupportVo = HelpSupportVoConver.INSTANCT.getHelpSupportVo(helpSupport);
        getTags(helpSupportVo);
        //保存seo信息
        BaseSeoParam baseSeoParam = new BaseSeoParam(SeoModuleEnum.HELPSUPPORT.getCode(), helpSupportVo.getName(), helpSupportVo.getSummaryInfo(),
                helpSupportVo.getContents(), vo.getTerminologyTagList(), helpSupportVo);
        SeoUtils.setSeoMsg(baseSeoParam);
        adminSeoServiceImpl.doSeoUpdatev2(request, response, null, helpSupportVo);
    }

    @Override
    public void doUpdate(HttpServletRequest request, HttpServletResponse response, HelpSupportVo vo) {
        HelpSupport helpSupport = HelpSupportVoConver.INSTANCT.getHelpSupport(vo);
        setMsg(request, helpSupport);
        updateById(helpSupport);
        saveTags(helpSupport, vo, OperationEnum.UPDATE);
        //查询tag
        getTags(vo);
        BaseSeoParam baseSeoParam = new BaseSeoParam(SeoModuleEnum.HELPSUPPORT.getCode(), vo.getName(), vo.getSummaryInfo(),
                vo.getContents(), vo.getTerminologyTagList(), vo);
        SeoUtils.setSeoMsg(baseSeoParam);
        adminSeoServiceImpl.doSeoUpdatev2(request, response, null, vo);
    }

    @Override
    public void doDelete(HelpSupportVo vo) {
        HelpSupport result = lambdaQuery().eq(HelpSupport::getId, Long.valueOf(vo.getId())).one();
        HelpSupportVo helpSupportVo = HelpSupportVoConver.INSTANCT.getHelpSupportVo(result);
        removeById(Long.valueOf(vo.getId()));
        //删除adminSeo
        adminSeoServiceImpl.doSeoDelete(helpSupportVo);
    }

    @Override
    public void getList(Model model, HelpSupportVo vo) {
        LambdaQueryChainWrapper<HelpSupport> helpSupportLambdaQueryChainWrapper = lambdaQuery()
                .eq(HelpSupport::getTopId, Long.valueOf(vo.getTopId()))
                .like(StrUtil.isNotEmpty(vo.getSearchValue()), HelpSupport::getName, vo.getSearchValue())
                .orderByDesc(HelpSupport::getInsDtm);
        if (StrUtil.isNotEmpty(vo.getStartDate()) && StrUtil.isNotEmpty(vo.getEndDate())) {
            helpSupportLambdaQueryChainWrapper.ge(HelpSupport::getInsDtm, DateUtils.getDateForMatt(0, vo.getStartDate()))
                    .le(HelpSupport::getInsDtm, DateUtils.getDateForMatt(1, vo.getEndDate()));
        }
        if (StrUtil.isNotEmpty(vo.getChildMenuId())) {
            helpSupportLambdaQueryChainWrapper.eq(HelpSupport::getChildMenuId, Long.valueOf(vo.getChildMenuId()));
        }
        Page<HelpSupport> page = helpSupportLambdaQueryChainWrapper
                .page(new Page<>(Integer.parseInt(vo.getPage()), Integer.parseInt(vo.getRowPerPage())));
        List<HelpSupportVo> helpSupportVos = JSONArray.parseArray(JSONObject.toJSONString(page.getRecords()), HelpSupportVo.class);
        //所有菜单分类
        Map<Long, String> mapMenu = helpSupportMenuServiceImpl.getAllMenu().stream().collect(Collectors.toMap(HelpSupportMenu::getId, HelpSupportMenu::getMenuName));
        List<HelpSupportVo> collect = helpSupportVos.stream().map(helpSupportVo -> {
            dealData(helpSupportVo, mapMenu);
            return helpSupportVo;
        }).collect(Collectors.toList());
        model.addAttribute("totalCount", page.getTotal());
        model.addAttribute("list", collect);
        model.addAttribute("siblingNodes", helpSupportMenuServiceImpl.getSMenu(vo.getTopId()));
        model.addAttribute("topname", HelpSupprtoMenuEnum.getEnumByCode(vo.getTopId()).getDesc());
    }

    /**
     * 下载 lambda通用
     *
     * @param vo
     * @return
     */
    public LambdaQueryChainWrapper<HelpSupport> downloadCommom(HelpSupportVo vo) {
        LambdaQueryChainWrapper<HelpSupport> helpSupportLambdaQueryChainWrapper = lambdaQuery()
                .eq(HelpSupport::getTopId, Long.valueOf(vo.getTopId()))
                .like(StrUtil.isNotEmpty(vo.getSearchValue()), HelpSupport::getName, vo.getSearchValue());
        if (StrUtil.isNotEmpty(vo.getStartDate()) && StrUtil.isNotEmpty(vo.getEndDate())) {
            helpSupportLambdaQueryChainWrapper.ge(HelpSupport::getInsDtm, DateUtils.getDateForMatt(0, vo.getStartDate()))
                    .le(HelpSupport::getInsDtm, DateUtils.getDateForMatt(1, vo.getEndDate()));
        }
        if (StrUtil.isNotEmpty(vo.getChildMenuId()) && StringUtils.isNumeric(vo.getChildMenuId())) {
            helpSupportLambdaQueryChainWrapper.eq(HelpSupport::getChildMenuId, Long.valueOf(vo.getChildMenuId()));
        }
        return helpSupportLambdaQueryChainWrapper;
    }

    @Override
    public int downloadCount(HelpSupportVo vo) {
        return downloadCommom(vo).count().intValue();
    }

    @Override
    public void excelDownLoad(HttpServletRequest request, HttpServletResponse response, HelpSupportVo vo) throws Exception {
        List<HelpSupport> list = downloadCommom(vo).orderByDesc(HelpSupport::getInsDtm).list();
        //所有菜单分类
        List<HelpSupportMenu> sMenu = helpSupportMenuServiceImpl.getAllMenu();
        Map<Long, String> mapMenu = sMenu.stream().collect(Collectors.toMap(HelpSupportMenu::getId, HelpSupportMenu::getMenuName));
        //转换
        List<HelpSupportVo> helpSupportVos = list.stream().map(HelpSupportVoConver.INSTANCT::getHelpSupportVo).collect(Collectors.toList());

        List<HelpSupportExportVo> helpSupportExportVos = helpSupportVos.stream().map(helpSupportVo -> {
            dealData(helpSupportVo, mapMenu);
            return helpSupportVo;
        }).map(HelpSupportVoConver.INSTANCT::getHelpSupportExportVo).collect(Collectors.toList());
        String fileName = "帮助与支持_" + DateUtil.formatTime(new Date());
        EasyExcelUtils.writeExcel(response, helpSupportExportVos, fileName, "helpSupport", HelpSupportExportVo.class);
    }

    @Override
    public void detail(Model model, HelpSupportVo vo) {
        HelpSupport result = lambdaQuery().eq(HelpSupport::getId, Long.valueOf(vo.getId())).one();
        HelpSupportVo helpSupportVo = HelpSupportVoConver.INSTANCT.getHelpSupportVo(result);
        //所有菜单分类
        Map<Long, String> mapMenu = helpSupportMenuServiceImpl.getAllMenu().stream().collect(Collectors.toMap(HelpSupportMenu::getId, HelpSupportMenu::getMenuName));
        dealData(helpSupportVo, mapMenu);
        helpSupportVo.setContents(XssPreventer.unescape(helpSupportVo.getContents()));
        // seo 정보 가져오기
        AdminSeoVO adminSeoVO = adminSeoServiceImpl.getSeoSelect(helpSupportVo);
        getTags(helpSupportVo);
        model.addAttribute("adminSeoVO", adminSeoVO);
        model.addAttribute("detail", helpSupportVo);
        model.addAttribute("vo", vo);
    }

    @Override
    public void updateForm(Model model, HelpSupportVo vo) {
        HelpSupport detailVO = lambdaQuery().eq(HelpSupport::getId, Long.valueOf(vo.getId())).one();
        HelpSupportVo helpSupportVo = HelpSupportVoConver.INSTANCT.getHelpSupportVo(detailVO);
        AdminSeoVO adminSeoVO = adminSeoServiceImpl.getSeoSelect(helpSupportVo);
        getTags(helpSupportVo);
        model.addAttribute("siblingNodes", helpSupportMenuServiceImpl.getSMenu(vo.getTopId()));
        model.addAttribute("adminSeoVO", adminSeoVO);
        model.addAttribute("detail", helpSupportVo);
        model.addAttribute("contIU", "U");
    }

    @Override
    public void saveImportWord(MultipartFile file, HttpServletRequest request, HttpServletResponse response, String topId) throws Exception {
        List<BaseWordData> baseWordDataList = WordOperationUtils.importWord(file, WordUploadTypeEnum.HELP.getCode());
        List<HelpSupport> helpSupports = new ArrayList<>();
        //全部菜单
        List<HelpSupportMenu> allMenu = helpSupportMenuServiceImpl.getAllMenu();
        Map<String, HelpSupportMenu> helpSupportMenu_map = allMenu.stream().
                collect(Collectors.toMap(HelpSupportMenu::getMenuName, Function.identity(), (o1, o2) -> o1));
        for (BaseWordData baseWordData : baseWordDataList) {
            //类型 是中文
            String type = baseWordData.getType().trim();
            HelpSupportMenu helpSupportMenu = helpSupportMenu_map.get(type);
            //类型有误
            if (Objects.isNull(helpSupportMenu)) {
                continue;
            }
            Long childMenuId = helpSupportMenu.getId();

            //查询根结点
            HelpSupportMenu maximumParent = HelpSpportMenuUtil.getMaximumParent(allMenu, helpSupportMenu);

            HelpSupport helpSupport = new HelpSupport();

            helpSupport.setChildMenuId(childMenuId);
            helpSupport.setTopId(maximumParent.getId());
            helpSupport.setName(baseWordData.getTitle());
            helpSupport.setSummaryInfo(baseWordData.getSummaryInfo());
            helpSupport.setUseYn(UseEnum.USE.getCode());
            helpSupport.setContents(baseWordData.getContents());
            helpSupport.setSource(HelpSupportSourceEnum.IMPORT.getCode());

            setMsg(request, helpSupport);

            HelpSupportVo vo = HelpSupportVoConver.INSTANCT.getHelpSupportVo(helpSupport);
            //保存seo
            BaseSeoParam baseSeoParam = new BaseSeoParam(SeoModuleEnum.HELPSUPPORT.getCode(), vo.getName(), vo.getSummaryInfo(),
                    vo.getContents(), vo.getTerminologyTagList(), vo);
            SeoUtils.setSeoMsg(baseSeoParam);
            adminSeoServiceImpl.doSeoWriteV2(request, response, null, vo);
            helpSupport.setMetaSeqNo(Integer.valueOf(vo.getMetaSeqNo()));
            helpSupports.add(helpSupport);
        }
        if (CollectionUtils.isNotEmpty(helpSupports)) {
            saveBatch(helpSupports);
            //保存术语和关键词
            helpSupports.forEach(helpSupport -> {
                saveTags(helpSupport, null, OperationEnum.INSERT);
            });
        }
    }

    @Override
    public void downloadTemplete(HttpServletRequest request, HttpServletResponse response) {
        String dd = "<div style=\"width:595.3pt;margin-bottom:72.0pt;margin-top:72.0pt;margin-left:56.7pt;margin-right:56.7pt;\">" +
                "<p style=\"text-align:left;white-space:pre-wrap;\">" +
                "<span style=\"font-family:'Microsoft YaHei';font-size:16.0pt;color:#000000;white-space:pre-wrap;\">标题" +
                "</span><span style=\"font-family:'Microsoft YaHei';font-size:16.0pt;color:#000000;white-space:pre-wrap;\">1" +
                "</span></p><p style=\"white-space:pre-wrap;\"><span style=\"font-family:'DengXian';white-space:pre-wrap;\">" +
                Constants.SPLIT_EVERY_TYPE + "</span></p><p style=\"white-space:pre-wrap;\"><span style=\"font-family:'DengXian';color:#ff0000;white-space:pre-wrap;\">" +
                "类型1 如：注册与登录</span></p><p style=\"white-space:pre-wrap;\"><span style=\"font-family:'DengXian';white-space:pre-wrap;\">" +
                Constants.SPLIT_EVERY_TYPE + "</span></p><p class=\"a a3\" style=\"margin-top:0.0pt;margin-bottom:0.0pt;white-space:pre-wrap;\"><span class=\"a a3\" style=\"font-family:'DengXian';font-size:10.0pt;color:#000000;white-space:pre-wrap;\">" +
                "内容</span><span class=\"a a3\" style=\"font-family:'DengXian';font-size:10.0pt;color:#000000;white-space:pre-wrap;\">1</span></p><p style=\"white-space:pre-wrap;\"><br/></p><p class=\"a a3\" style=\"margin-top:0.0pt;margin-bottom:0.0pt;white-space:pre-wrap;\"><span class=\"a a3\" style=\"font-family:'DengXian';font-size:10.0pt;color:#000000;white-space:pre-wrap;\">" +
                Constants.SPLIT_EVERY_PIAN + "</span><span id=\"_GoBack\"/></p><p style=\"text-align:left;white-space:pre-wrap;\"><span style=\"font-family:'Microsoft YaHei';font-size:16.0pt;color:#000000;white-space:pre-wrap;\">标题</span><span style=\"font-family:'Microsoft YaHei';font-size:16.0pt;color:#000000;white-space:pre-wrap;\">2</span></p><p style=\"white-space:pre-wrap;\"><span style=\"font-family:'DengXian';white-space:pre-wrap;\">" +
                Constants.SPLIT_EVERY_TYPE + "</span></p><p style=\"white-space:pre-wrap;\"><span style=\"font-family:'DengXian';color:#ff0000;white-space:pre-wrap;\">类型</span><span style=\"font-family:'DengXian';color:#ff0000;white-space:pre-wrap;\">2</span><span style=\"font-family:'DengXian';color:#ff0000;white-space:pre-wrap;\"> 如：</span><span style=\"font-family:'DengXian';color:#ff0000;white-space:pre-wrap;\">报价</span></p><p style=\"white-space:pre-wrap;\"><span style=\"font-family:'DengXian';white-space:pre-wrap;\">" +
                Constants.SPLIT_EVERY_TYPE + "</span></p><p class=\"a a3\" style=\"margin-top:0.0pt;margin-bottom:0.0pt;white-space:pre-wrap;\"><span class=\"a a3\" style=\"font-family:'DengXian';font-size:10.0pt;color:#000000;white-space:pre-wrap;\">内容</span><span class=\"a a3\" style=\"font-family:'DengXian';font-size:10.0pt;color:#000000;white-space:pre-wrap;\">2</span></p><p style=\"white-space:pre-wrap;\"><br/></p><p class=\"a a3\" style=\"margin-top:0.0pt;margin-bottom:0.0pt;white-space:pre-wrap;\">" +
                "<span class=\"a a3\" style=\"font-family:'DengXian';font-size:10.0pt;color:#000000;white-space:pre-wrap;\">" +
                Constants.SPLIT_EVERY_PIAN + "</span></p><p/></div>";
        WordUtil.downloadWord(request, response, "helpSupport_template", dd);
    }

    @Override
    public List<HelpSupport> queryHelpSupportByMenu(Long id) {
        return lambdaQuery().eq(HelpSupport::getChildMenuId, id).list();
    }

    @Override
    public List<HelpSupportVo> queryQAndA() {
        return lambdaQuery().eq(HelpSupport::getTopId, Long.valueOf(HelpSupprtoMenuEnum.QANDA.getCode())).eq(HelpSupport::getUseYn, UseEnum.USE.getCode()).list()
                .stream().map(HelpSupportVoConver.INSTANCT::getHelpSupportVo).collect(Collectors.toList());
    }

    @Override
    public List<HelpSupportVo> queryQAndAByIds(List<Long> ids) {
        return lambdaQuery().eq(HelpSupport::getUseYn, UseEnum.USE.getCode()).in(HelpSupport::getId, ids).list()
                .stream().map(HelpSupportVoConver.INSTANCT::getHelpSupportVo).collect(Collectors.toList());
    }

    @Override
    public void doSortOrder(List<HelpSupportVo> helpSupportVos) {
        helpSupportVos.forEach(helpSupportVo -> {
            lambdaUpdate().set(HelpSupport::getOrdb, helpSupportVo.getOrdb()).
                    eq(HelpSupport::getId, Long.valueOf(helpSupportVo.getId())).update();
        });
    }

    @Override
    public void batchDelete(HelpSupportVo vo) {
        String[] listId = vo.getListId();
        if (!Objects.isNull(listId) && listId.length > 0) {
            List<Long> collect = Arrays.asList(listId).stream().map(Long::valueOf).collect(Collectors.toList());
            removeBatchByIds(collect);
        }
    }

    /**
     * 将标签赋值
     *
     * @param vo
     */
    public void getTags(HelpSupportVo vo) {
        //术语
        String term = terminologyService.getTerm(Long.valueOf(vo.getId()), TermTypeEnum.DYNAMICS_LOGISTICS);
        vo.setTerminologyTags(term);
        List<String> terms = terminologyService.getTerms(Long.valueOf(vo.getId()), TermTypeEnum.DYNAMICS_LOGISTICS);
        vo.setTerminologyTagList(terms);
        //关键词
        String topTags = antistopServiceImpl.getTerm(Long.valueOf(vo.getId()), TermTypeEnum.DYNAMICS_LOGISTICS);
        vo.setAntistopTags(topTags);
    }

    /**
     * 设置标签
     *
     * @param helpSupport
     * @param vo
     */
    public void saveTags(HelpSupport helpSupport, HelpSupportVo vo, OperationEnum operationEnum) {
        //术语
        if (ArrayUtil.isNotEmpty(vo.getTerminologyTags())){
            String[] split = Objects.isNull(vo) ? new String[0] : vo.getTerminologyTags().split(",");
            terminologyService.marchingTerm(helpSupport.getId(), TermTypeEnum.DYNAMICS_LOGISTICS, helpSupport.getName(), operationEnum, split);
        }
        //关键词
        if (ArrayUtil.isNotEmpty(vo.getAntistopTags())){
            String[] antistopTags_arr = Objects.isNull(vo) ? new String[0] : vo.getAntistopTags().split(",");
            antistopServiceImpl.marchingTerm(helpSupport.getId(), TermTypeEnum.DYNAMICS_LOGISTICS, helpSupport.getName(), operationEnum, antistopTags_arr);
        }

    }
}
