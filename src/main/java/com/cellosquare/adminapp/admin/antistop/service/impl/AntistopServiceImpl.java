package com.cellosquare.adminapp.admin.antistop.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.bluewaves.lab.message.util.ActionMessageUtil;
import com.cellosquare.adminapp.admin.antistop.conver.AntistopConver;
import com.cellosquare.adminapp.admin.antistop.entity.Antistop;
import com.cellosquare.adminapp.admin.antistop.mapper.AntistopMapper;
import com.cellosquare.adminapp.admin.antistop.service.IAntistopService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cellosquare.adminapp.admin.antistop.vo.AntistopVO;
import com.cellosquare.adminapp.admin.antistopRelation.entity.AntistopRelation;
import com.cellosquare.adminapp.admin.antistopRelation.service.impl.AntistopRelationServiceImpl;
import com.cellosquare.adminapp.admin.dic.service.impl.DicServiceImpl;
import com.cellosquare.adminapp.admin.seo.mapper.AdminSeoMapper;
import com.cellosquare.adminapp.admin.seo.service.impl.AdminSeoServiceImpl;
import com.cellosquare.adminapp.admin.terminology.service.impl.TerminologyServiceImpl;
import com.cellosquare.adminapp.admin.terminology.vo.TerminologyVO;
import com.cellosquare.adminapp.common.constant.SeoModuleEnum;
import com.cellosquare.adminapp.common.constant.UseEnum;
import com.cellosquare.adminapp.common.enums.OperationEnum;
import com.cellosquare.adminapp.common.enums.TermTypeEnum;
import com.cellosquare.adminapp.common.util.SeoUtils;
import com.cellosquare.adminapp.common.util.TextCheckUtil;
import com.cellosquare.adminapp.common.vo.BaseSeoParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author walker
 * @since 2023-03-68 09:15:21
 */
@Service
public class AntistopServiceImpl extends ServiceImpl<AntistopMapper, Antistop> implements IAntistopService {

    @Autowired
    private AdminSeoServiceImpl adminSeoService;
    @Autowired
    private AdminSeoMapper adminSeoMapper;
    @Autowired
    private AntistopRelationServiceImpl antistopRelationService;
    @Autowired
    private TerminologyServiceImpl terminologyService;
    @Autowired
    private DicServiceImpl dicService;

    public Boolean register(HttpServletRequest request, HttpServletResponse response, AntistopVO vo, MultipartHttpServletRequest muServletRequest) {
        getTags(vo);
        //保存seo信息
        BaseSeoParam baseSeoParam = new BaseSeoParam(SeoModuleEnum.ANTISTOP.getCode(), vo.getTerminologyName(), vo.getDescription(),
                null, vo.getTerminologyTagList(), vo);
        SeoUtils.setSeoMsg(baseSeoParam);
        int seoWrite = adminSeoService.doSeoWriteV2(request, response, muServletRequest, vo);
        if (seoWrite <= 0) {
            return false;
        }
        Antistop one = lambdaQuery().eq(Antistop::getTerminologyName, vo.getTerminologyName()).one();
        if (Objects.nonNull(one)) {
            ActionMessageUtil.setActionMessage(request, "术语名重复");
            return false;
        }
        //敏感词校验
        if (!TextCheckUtil.check(vo.getTerminologyName())) {
            ActionMessageUtil.setActionMessage(request, "关键字存在敏感词");
            return false;
        }
        Antistop antistop = AntistopConver.INSTANCT.getAntistop(vo);
        save(antistop);
        return true;
    }

    /**
     * 将标签赋值
     *
     * @param vo
     */
    public void getTags(AntistopVO vo) {
        List<String> terms = terminologyService.relateTerminologys(vo.getTerminologyName());
        vo.setTerminologyTagList(terms);
    }

    public Boolean doUpdate(HttpServletRequest request, HttpServletResponse response, AntistopVO antistopVO, MultipartHttpServletRequest muServletRequest) {
        Antistop one = lambdaQuery().eq(Antistop::getTerminologyName, antistopVO.getTerminologyName()).one();
        if (Objects.nonNull(one) && !Objects.equals(antistopVO.getId(), one.getId())) {
            ActionMessageUtil.setActionMessage(request, "术语名重复");
            return false;
        }
        //敏感词校验
        if (!TextCheckUtil.check(antistopVO.getTerminologyName())) {
            ActionMessageUtil.setActionMessage(request, "关键字存在敏感词");
            return false;
        }
        Antistop antistop = AntistopConver.INSTANCT.getAntistop(antistopVO);
        updateById(antistop);
        getTags(antistopVO);
        BaseSeoParam baseSeoParam = new BaseSeoParam(SeoModuleEnum.ANTISTOP.getCode(), antistopVO.getTerminologyName(), antistopVO.getDescription(),
                null, antistopVO.getTerminologyTagList(), antistopVO);
        SeoUtils.setSeoMsg(baseSeoParam);
        adminSeoService.doSeoUpdatev2(request, response, null, antistopVO);
        return true;
    }

    /**
     * 匹配术语
     */
    public void marchingTerm(Long id, TermTypeEnum termTypeEnum, String title, OperationEnum operationEnum, String... input) {

        if (Objects.isNull(id) || Objects.isNull(termTypeEnum) || StrUtil.isEmpty(title)) {
            return;
        }
        if (StrUtil.isEmpty(title)) {
            return;
        }
        if (OperationEnum.INSERT.equals(operationEnum)) {
            //新增自动匹配术语
            insertTerm(id, termTypeEnum, title);
        } else {
            //修改手动关联术语
            inputTerm(id, termTypeEnum, input);
        }

    }

    private void insertTerm(Long id, TermTypeEnum termTypeEnum, String title) {
        List<String> parse = dicService.parse(title);
        LambdaQueryChainWrapper<Antistop> antistopLambdaQueryChainWrapper = lambdaQuery();
        for (String s : parse) {
            antistopLambdaQueryChainWrapper.likeRight(Antistop::getTerminologyName, s).or();
        }
        List<Antistop> list = antistopLambdaQueryChainWrapper.list();
        //查出所有已存在关联数据 并删除
        List<AntistopRelation> relationDbs = antistopRelationService.lambdaQuery()
                .eq(AntistopRelation::getArticleId, id)
                .eq(AntistopRelation::getArticleType, termTypeEnum.getCode()).list();
        if (CollectionUtil.isNotEmpty(relationDbs)) {
            antistopRelationService.removeByIds(relationDbs.stream().map(AntistopRelation::getId).collect(Collectors.toList()));
        }

        //组装关系入库对象
        List<AntistopRelation> saves = list.stream().limit(5).map(r -> {
            AntistopRelation AntistopRelation = new AntistopRelation();
            AntistopRelation.setTermId(r.getId());
            AntistopRelation.setArticleId(id);
            AntistopRelation.setArticleType(termTypeEnum.getCode());
            return AntistopRelation;
        }).collect(Collectors.toList());

        //关系表入库
        antistopRelationService.saveBatch(saves);
    }

    /**
     * 手动关联术语
     *
     * @param id
     * @param termTypeEnum
     * @param input
     */
    private void inputTerm(Long id, TermTypeEnum termTypeEnum, String... input) {
        List<String> inputs = Arrays.stream(input).filter(StrUtil::isNotBlank).collect(Collectors.toList());
        if (ArrayUtil.isEmpty(inputs)) {
            return;
        }
        //查出所有已存在关联数据 并删除
        List<AntistopRelation> relationDbs = antistopRelationService.lambdaQuery()
                .eq(AntistopRelation::getArticleId, id)
                .eq(AntistopRelation::getArticleType, termTypeEnum.getCode()).list();
        if (CollectionUtil.isNotEmpty(relationDbs)) {
            antistopRelationService.removeByIds(relationDbs.stream().map(AntistopRelation::getId).collect(Collectors.toList()));
        }


        List<Antistop> list = lambdaQuery().in(Antistop::getTerminologyName, input).list();
        if (CollectionUtil.isNotEmpty(list)) {
            List<String> dbName = list.stream().map(Antistop::getTerminologyName).collect(Collectors.toList());
            inputs.removeAll(dbName);
        }
        if (CollectionUtil.isNotEmpty(inputs)) {
            List<Antistop> insert = inputs.stream().map(r -> {
                //需要处理相关术语 拼接
                List<String> t_names = terminologyService.relateTerminologys(r);
                t_names.add(r);
                BaseSeoParam baseSeoParam = new BaseSeoParam(SeoModuleEnum.ANTISTOP.getCode(), r, r,
                        null, t_names, new TerminologyVO());
                SeoUtils.setSeoMsg(baseSeoParam);
                adminSeoMapper.doSeoWrite(baseSeoParam.getAdminSeoVO());
                Antistop Antistop = new Antistop();
                Antistop.setTerminologyName(r);
                Antistop.setUseYn("Y");
                Antistop.setMetaSeqNo(Long.parseLong(baseSeoParam.getAdminSeoVO().getMetaSeqNo()));
                return Antistop;
            }).collect(Collectors.toList());
            saveBatch(insert);
            list.addAll(insert);
        }

        List<AntistopRelation> relations = list.stream().map(r -> {
            AntistopRelation AntistopRelation = new AntistopRelation();
            AntistopRelation.setArticleId(id);
            AntistopRelation.setArticleType(termTypeEnum.getCode());
            AntistopRelation.setTermId(r.getId());
            return AntistopRelation;
        }).collect(Collectors.toList());

        antistopRelationService.saveBatch(relations);
    }

    /**
     * 获取标签
     *
     * @return
     */
    public String getTerm(Long id, TermTypeEnum termTypeEnum) {
        if (Objects.isNull(id) || Objects.isNull(termTypeEnum)) {
            return "";
        }
        List<Long> termIds = antistopRelationService.lambdaQuery()
                .eq(AntistopRelation::getArticleType, termTypeEnum.getCode())
                .eq(AntistopRelation::getArticleId, id)
                .list()
                .stream()
                .map(AntistopRelation::getTermId)
                .collect(Collectors.toList());

        if (CollectionUtil.isEmpty(termIds)) {
            return "";
        }
        return lambdaQuery().in(Antistop::getId, termIds)
                .list()
                .stream()
                .map(Antistop::getTerminologyName)
                .filter(StrUtil::isNotEmpty)
                .collect(Collectors.joining(","));
    }

    @Override
    public void seoMatch(HttpServletRequest request, HttpServletResponse response, List<String> listId) {
        LambdaQueryChainWrapper<Antistop> antistopLambdaQueryChainWrapper = lambdaQuery().eq(Antistop::getUseYn, UseEnum.USE.getCode());
        List<String> ter_nams = antistopLambdaQueryChainWrapper.list().stream().map(Antistop::getTerminologyName).collect(Collectors.toList());
        if (CollectionUtil.isNotEmpty(listId)) {
            antistopLambdaQueryChainWrapper.in(Antistop::getId, listId.stream().map(Long::valueOf).collect(Collectors.toList()));
        }
        List<Antistop> list = antistopLambdaQueryChainWrapper.list();
        list.forEach(antistop -> {
            // List<String> terminologys_name = terminologyService.relateTerminologys(antistop.getTerminologyName());
            List<String> terminologys_name = terminologyService.getListCY(antistop.getTerminologyName(), ter_nams);
            AntistopVO antistopVO = AntistopConver.INSTANCT.getAntistopVO(antistop);
            //保存seo信息
            BaseSeoParam baseSeoParam = new BaseSeoParam(SeoModuleEnum.ANTISTOP.getCode(), antistopVO.getTerminologyName(), antistopVO.getDescription(),
                    null, terminologys_name, antistopVO);
            SeoUtils.setSeoMsg(baseSeoParam);
            adminSeoService.doSeoUpdatev2(request, response, null, antistopVO);
        });
    }
}
