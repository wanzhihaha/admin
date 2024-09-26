package com.cellosquare.adminapp.admin.terminology.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bluewaves.lab.message.util.ActionMessageUtil;
import com.cellosquare.adminapp.admin.dic.service.impl.DicServiceImpl;
import com.cellosquare.adminapp.admin.seo.mapper.AdminSeoMapper;
import com.cellosquare.adminapp.admin.seo.service.impl.AdminSeoServiceImpl;
import com.cellosquare.adminapp.admin.termRelation.entity.TermRelation;
import com.cellosquare.adminapp.admin.termRelation.service.impl.TermRelationServiceImpl;
import com.cellosquare.adminapp.admin.terminology.conver.TerminologyConver;
import com.cellosquare.adminapp.admin.terminology.entity.Terminology;
import com.cellosquare.adminapp.admin.terminology.mapper.TerminologyMapper;
import com.cellosquare.adminapp.admin.terminology.service.ITerminologyService;
import com.cellosquare.adminapp.admin.terminology.vo.TerminologyVO;
import com.cellosquare.adminapp.common.constant.SeoModuleEnum;
import com.cellosquare.adminapp.common.constant.UseEnum;
import com.cellosquare.adminapp.common.enums.OperationEnum;
import com.cellosquare.adminapp.common.enums.TermTypeEnum;
import com.cellosquare.adminapp.common.util.SeoUtils;
import com.cellosquare.adminapp.common.util.TextCheckUtil;
import com.cellosquare.adminapp.common.vo.BaseSeoParam;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
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
 * @since 2023-03-61 14:43:00
 */
@Service
public class TerminologyServiceImpl extends ServiceImpl<TerminologyMapper, Terminology> implements ITerminologyService {
    @Autowired
    private AdminSeoServiceImpl adminSeoService;
    @Autowired
    private AdminSeoMapper adminSeoMapper;
    @Autowired
    private TermRelationServiceImpl termRelationService;
    @Autowired
    private DicServiceImpl dicService;

    public Boolean register(HttpServletRequest request, HttpServletResponse response, TerminologyVO vo, MultipartHttpServletRequest muServletRequest) {
        getTags(vo);
        //保存seo信息
        BaseSeoParam baseSeoParam = new BaseSeoParam(SeoModuleEnum.TERMINOLOGY.getCode(), vo.getTerminologyName(), vo.getDescription(),
                null, vo.getTerminologyTagList(), vo);
        SeoUtils.setSeoMsg(baseSeoParam);
        int seoWrite = adminSeoService.doSeoWriteV2(request, response, muServletRequest, vo);
        if (seoWrite <= 0) {
            return false;
        }
        Terminology one = lambdaQuery().eq(Terminology::getTerminologyName, vo.getTerminologyName().trim()).one();
        if (Objects.nonNull(one)) {
            ActionMessageUtil.setActionMessage(request, "术语名重复");
            return false;
        }
        //敏感词校验
        if (!TextCheckUtil.check(vo.getTerminologyName())) {
            ActionMessageUtil.setActionMessage(request, "术语存在敏感词");
            return false;
        }
        Terminology advertising = TerminologyConver.INSTANCT.getAdvertising(vo);
        save(advertising);
        return true;
    }

    /**
     * 将标签赋值
     *
     * @param vo
     */
    public void getTags(TerminologyVO vo) {
        List<String> terms = this.relateTerminologys(vo.getTerminologyName());
        vo.setTerminologyTagList(terms);
    }

    public Boolean doUpdate(HttpServletRequest request, HttpServletResponse response, TerminologyVO advertisingVO, MultipartHttpServletRequest muServletRequest) {
        Terminology one = lambdaQuery().eq(Terminology::getTerminologyName, advertisingVO.getTerminologyName()).one();
        if (Objects.nonNull(one) && !Objects.equals(advertisingVO.getId(), one.getId())) {
            ActionMessageUtil.setActionMessage(request, "术语名重复");
            return false;
        }
        //敏感词校验
//        if (!TextCheckUtil.check(advertisingVO.getTerminologyName())) {
//            ActionMessageUtil.setActionMessage(request, "术语存在敏感词");
//            return false;
//        }
        Terminology advertising = TerminologyConver.INSTANCT.getAdvertising(advertisingVO);
        updateById(advertising);
        getTags(advertisingVO);
        //保存seo信息
        BaseSeoParam baseSeoParam = new BaseSeoParam(SeoModuleEnum.TERMINOLOGY.getCode(), advertisingVO.getTerminologyName(), advertisingVO.getDescription(),
                null, advertisingVO.getTerminologyTagList(), advertisingVO);
        SeoUtils.setSeoMsg(baseSeoParam);
        adminSeoService.doSeoUpdatev2(request, response, null, advertisingVO);
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
//        List<String> parse  = DicServiceImpl.parse(title);
        //匹配出所有术语
        List<Terminology> list = lambdaQuery().in(Terminology::getTerminologyName, parse).list();
        //查出所有已存在关联数据 并删除
        List<TermRelation> relationDbs = termRelationService.lambdaQuery()
                .eq(TermRelation::getArticleId, id)
                .eq(TermRelation::getArticleType, termTypeEnum.getCode()).list();
        if (CollectionUtil.isNotEmpty(relationDbs)) {
            termRelationService.removeByIds(relationDbs.stream().map(TermRelation::getId).collect(Collectors.toList()));
        }

        //组装关系入库对象
        List<TermRelation> saves = list.stream().limit(5).map(r -> {
            TermRelation termRelation = new TermRelation();
            termRelation.setTermId(r.getId());
            termRelation.setArticleId(id);
            termRelation.setArticleType(termTypeEnum.getCode());
            return termRelation;
        }).collect(Collectors.toList());

        //关系表入库
        termRelationService.saveBatch(saves);
    }

    /**
     * 手动关联术语
     *
     * @param id
     * @param termTypeEnum
     * @param input
     */
    private void inputTerm(Long id, TermTypeEnum termTypeEnum, String... input) {
        List<String> inputs = Arrays.stream(input).filter(StrUtil::isNotBlank).map(String::trim).collect(Collectors.toList());
        if (ArrayUtil.isEmpty(input)) {
            return;
        }
        //查出所有已存在关联数据 并删除
        List<TermRelation> relationDbs = termRelationService.lambdaQuery()
                .eq(TermRelation::getArticleId, id)
                .eq(TermRelation::getArticleType, termTypeEnum.getCode()).list();
        if (CollectionUtil.isNotEmpty(relationDbs)) {
            termRelationService.removeByIds(relationDbs.stream().map(TermRelation::getId).collect(Collectors.toList()));
        }


        List<Terminology> list = lambdaQuery().in(Terminology::getTerminologyName, input).list();
        if (CollectionUtil.isNotEmpty(list)) {
            List<String> dbName = list.stream().map(Terminology::getTerminologyName).collect(Collectors.toList());
            inputs.removeAll(dbName);
        }

        if (CollectionUtil.isNotEmpty(inputs)) {
            List<Terminology> insert = inputs.stream().map(r -> {
                //需要处理相关术语 拼接
                List<String> t_names = this.relateTerminologys(r);
                t_names.add(r);
                BaseSeoParam baseSeoParam = new BaseSeoParam(SeoModuleEnum.TERMINOLOGY.getCode(), r, r,
                        null, t_names, new TerminologyVO());
                SeoUtils.setSeoMsg(baseSeoParam);
                adminSeoMapper.doSeoWrite(baseSeoParam.getAdminSeoVO());
                Terminology terminology = new Terminology();
                terminology.setTerminologyName(r);
                terminology.setUseYn("Y");
                terminology.setMetaSeqNo(Long.parseLong(baseSeoParam.getAdminSeoVO().getMetaSeqNo()));
                return terminology;
            }).collect(Collectors.toList());
            saveBatch(insert);
            list.addAll(insert);
        }

        List<TermRelation> relations = list.stream().map(r -> {
            TermRelation termRelation = new TermRelation();
            termRelation.setArticleId(id);
            termRelation.setArticleType(termTypeEnum.getCode());
            termRelation.setTermId(r.getId());
            return termRelation;
        }).collect(Collectors.toList());

        termRelationService.saveBatch(relations);
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
        List<Long> termIds = termRelationService.lambdaQuery()
                .eq(TermRelation::getArticleType, termTypeEnum.getCode())
                .eq(TermRelation::getArticleId, id)
                .list()
                .stream()
                .map(TermRelation::getTermId)
                .collect(Collectors.toList());

        if (CollectionUtil.isEmpty(termIds)) {
            return "";
        }
        return lambdaQuery().in(Terminology::getId, termIds)
                .list()
                .stream()
                .map(Terminology::getTerminologyName)
                .filter(StrUtil::isNotEmpty)
                .collect(Collectors.joining(","));
    }

    /**
     * 返回集合
     *
     * @param id
     * @param termTypeEnum
     * @return
     */
    public List<String> getTerms(Long id, TermTypeEnum termTypeEnum) {
        if (Objects.isNull(id) || Objects.isNull(termTypeEnum)) {
            return Lists.newArrayList();
        }
        List<Long> termIds = termRelationService.lambdaQuery()
                .eq(TermRelation::getArticleType, termTypeEnum.getCode())
                .eq(TermRelation::getArticleId, id)
                .list()
                .stream()
                .map(TermRelation::getTermId)
                .collect(Collectors.toList());

        if (CollectionUtil.isEmpty(termIds)) {
            return Lists.newArrayList();
        }
        return lambdaQuery().in(Terminology::getId, termIds)
                .list()
                .stream()
                .map(Terminology::getTerminologyName)
                .filter(StrUtil::isNotEmpty)
                .collect(Collectors.toList());
    }

    /**
     * 相关的术语
     *
     * @param title 模糊搜索
     * @return
     */
    public List<String> relateTerminologys(String title) {
        LambdaQueryChainWrapper<Terminology> terminologyLambdaQueryChainWrapper = lambdaQuery().eq(Terminology::getUseYn, UseEnum.USE.getCode()).orderByDesc(Terminology::getInsDtm);
        //分词
        List<String> f_c = this.analyCY(title);
        if (CollectionUtils.isNotEmpty(f_c)) {
            terminologyLambdaQueryChainWrapper.and(terminologyLambdaQueryWrapper -> {
                //拼接or like
                f_c.forEach(temp_string -> {
                    terminologyLambdaQueryWrapper.or(lambdaQueryWrapper -> {
                        lambdaQueryWrapper.like(Terminology::getTerminologyName, temp_string);
                    });
                });
            });
        }
        return terminologyLambdaQueryChainWrapper.list().stream().map(Terminology::getTerminologyName).collect(Collectors.toList());
    }


    /**
     * 分词
     *
     * @param title
     * @return
     */
    private List<String> analyCY(String title) {
        List<String> parse = dicService.parse(title);
        return parse.stream().filter(s -> s.length() >= 2).collect(Collectors.toList());
    }

    /**
     * 术语分词匹配集合
     *
     * @param title
     * @param names
     * @return
     */

    public List<String> getListCY(String title, List<String> names) {
        List<String> result = Lists.newArrayList();
        List<String> analyCY = analyCY(title);
        names.forEach(s -> {
            analyCY.forEach(a_c -> {
                if (s.contains(a_c)) {
                    result.add(s);
                }
            });
        });
        return result;
    }

    @Override
    public void seoMatch(HttpServletRequest request, HttpServletResponse response, List<String> listId) {
        LambdaQueryChainWrapper<Terminology> terminologyLambdaQueryChainWrapper = lambdaQuery().eq(Terminology::getUseYn, UseEnum.USE.getCode());
        List<String> ter_nams = terminologyLambdaQueryChainWrapper.list().stream().map(Terminology::getTerminologyName).collect(Collectors.toList());
        if (CollectionUtil.isNotEmpty(listId)) {
            terminologyLambdaQueryChainWrapper.in(Terminology::getId, listId.stream().map(Long::valueOf).collect(Collectors.toList()));
        }
        List<Terminology> list = terminologyLambdaQueryChainWrapper.list();
        list.forEach(terminology -> {
            // List<String> terminologys_name = relateTerminologys(terminology.getTerminologyName());
            List<String> terminologys_name = getListCY(terminology.getTerminologyName(), ter_nams);
            TerminologyVO advertisingVO = TerminologyConver.INSTANCT.getAdvertisingVO(terminology);
            //保存seo信息
            //保存seo信息
            BaseSeoParam baseSeoParam = new BaseSeoParam(SeoModuleEnum.TERMINOLOGY.getCode(), terminology.getTerminologyName(), terminology.getDescription(),
                    null, terminologys_name, advertisingVO);
            SeoUtils.setSeoMsg(baseSeoParam);
            adminSeoService.doSeoUpdatev2(request, response, null, advertisingVO);
        });
    }
}
