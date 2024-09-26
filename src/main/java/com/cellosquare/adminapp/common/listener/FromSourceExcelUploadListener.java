package main.java.com.cellosquare.adminapp.common.listener;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.excel.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.bluewaves.lab.property.XmlPropertyManager;
import com.cellosquare.adminapp.admin.registerStatistics.entity.FromSource;
import com.cellosquare.adminapp.admin.registerStatistics.vo.FromSourceUploadVO;
import com.cellosquare.adminapp.common.ex.UploadException;
import com.cellosquare.adminapp.common.session.AdminSessionForm;
import com.cellosquare.adminapp.common.session.SessionManager;
import com.cellosquare.adminapp.common.util.UrlUtil;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.ObjectUtils;

import java.sql.Timestamp;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
public class FromSourceExcelUploadListener implements ReadListener<com.cellosquare.adminapp.admin.registerStatistics.vo.FromSourceUploadVO> {
    /**
     * 每隔5条存储数据库，实际使用中可以100条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 100;
    private List<FromSourceUploadVO> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
    /**
     * 假设这个是一个DAO，当然有业务逻辑这个也可以是一个service。当然如果不用存储这个对象没用。
     */
    private com.cellosquare.adminapp.admin.registerStatistics.service.IFromSourceService fromSourceServiceImpl;
    private String type;
    private UploadException uploadException;

    public FromSourceExcelUploadListener(com.cellosquare.adminapp.admin.registerStatistics.service.IFromSourceService fromSourceServiceImpl, String type, UploadException uploadException) {
        this.fromSourceServiceImpl = fromSourceServiceImpl;
        this.type = type;
        this.uploadException = uploadException;
    }

    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data    one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @Override
    public void invoke(com.cellosquare.adminapp.admin.registerStatistics.vo.FromSourceUploadVO data, AnalysisContext context) {
        log.info("解析到一条数据:{}", JSON.toJSONString(data));
        cachedDataList.add(data);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (cachedDataList.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
        log.info("所有数据解析完成！");
    }

    /**
     * 加上存储数据库
     */
//    private void saveData() {
//        StringBuilder stringBuilder = new StringBuilder();
//        log.info("{}条数据，开始存储数据库！", cachedDataList.size());
//        if (CollectionUtil.isEmpty(cachedDataList)) {
//            return;
//        }
//        List<FromSource> fromSources = Lists.newArrayList();
//        cachedDataList.forEach(fromSourceUploadVO -> {
//            if (StringUtils.isNotBlank(fromSourceUploadVO.getIdentifier())
//                    && StringUtils.isNotBlank(fromSourceUploadVO.getRemark())
//                    && StringUtils.isNotBlank(fromSourceUploadVO.getType())
//                    && StringUtils.isNotBlank(fromSourceUploadVO.getAppSuccessIdentifier())
//                   ) {
//                if (XssUtils.hasSpecialChar(fromSourceUploadVO.getIdentifier()) || XssUtils.hasSpecialChar(fromSourceUploadVO.getAppSuccessIdentifier())) {
//                    stringBuilder.append(String.format("名称为%s，存在特殊字符.\\n",fromSourceUploadVO.getName()));
//                    uploadException.setMessage(stringBuilder.toString());
//                }else {
//                    String code = Objects.requireNonNull(FromSourceType.getEnumByVal(fromSourceUploadVO.getType())).getCode();
//                    FromSource fromSource = new FromSource(code, autogenterLiuShui(code),
//                            fromSourceUploadVO.getIdentifier(), fromSourceUploadVO.getRemark(),
//                            fromSourceUploadVO.getAppSuccessIdentifier(), fromSourceUploadVO.getName());
//                    setMsg(fromSource);
//                    fromSource.setAppSuccessIdentifier(org.apache.commons.lang3.StringUtils.deleteWhitespace(fromSource.getAppSuccessIdentifier()));
//                    fromSource.setIdentifier(org.apache.commons.lang3.StringUtils.deleteWhitespace(fromSource.getIdentifier()));
//                    fromSources.add(fromSource);
//                }
//            }
//        });
//        if (CollectionUtil.isNotEmpty(fromSources)) {
//            ArrayList<FromSource> collect = fromSources.stream()
//                    .collect(collectingAndThen(toCollection(() -> new TreeSet<FromSource>(Comparator.comparing(FromSource::getAppSuccessIdentifier)))
//                            , ArrayList::new));
//            ArrayList<FromSource> collect2 = collect.stream()
//                    .collect(collectingAndThen(toCollection(() -> new TreeSet<FromSource>(Comparator.comparing(FromSource::getIdentifier)))
//                            , ArrayList::new));
//            if (CollectionUtil.isEmpty(collect2)) {
//                return;
//            }
//            Function<List<FromSource> ,List<String> >getMapList = s->{
//                List<String> queryList = s.stream().map(r->{
//                    ArrayList<String> data = Lists.newArrayList();
//                    data.add(r.getAppSuccessIdentifier());
//                    data.add(r.getIdentifier());
//                    return data;
//                }).flatMap(Collection::stream).collect(Collectors.toList());
//                return queryList;
//            };
//
//            List<String> queryList = getMapList.apply(collect2);
//            List<FromSource> db = fromSourceServiceImpl.lambdaQuery()
//                    .in(FromSource::getAppSuccessIdentifier, queryList)
//                    .or()
//                    .in(FromSource::getIdentifier, queryList)
//                    .list();
//
//            List<String> dbSiList = getMapList.apply(db);
//
//            List<FromSource> insertList = collect2.stream()
//                    .filter(r->!Objects.equals(r.getAppSuccessIdentifier(),r.getIdentifier()))
//                    .filter(r -> !dbSiList.contains(r.getAppSuccessIdentifier()))
//                    .filter(r -> !dbSiList.contains(r.getIdentifier())).collect(Collectors.toList());
//
//            collect2.removeAll(insertList);
//            if (CollectionUtil.isNotEmpty(collect2)) {
//                collect2.forEach(r-> stringBuilder.append(String.format("名称为%s，存在重复数据.\\n",r.getName())));
//                uploadException.setMessage(stringBuilder.toString());
//            }
//            if (CollectionUtil.isEmpty(insertList)) {
//                return;
//            }
//            Collections.reverse(insertList);
//            fromSourceServiceImpl.saveBatch(insertList);
//            stringBuilder.append("成功条数:"+insertList.size());
//            uploadException.setMessage(stringBuilder.toString());
//        }
//        log.info("存储数据库成功！");
//    }
    private void saveData() {
        StringBuilder stringBuilder = new StringBuilder();
        log.info("{}条数据，开始存储数据库！", cachedDataList.size());
        if (CollectionUtil.isEmpty(cachedDataList)) {
            return;
        }
        //去重
        Map<String, FromSourceUploadVO> quchong_yuan =
                cachedDataList.stream().collect(Collectors.toMap(fromSourceUploadVO -> getXTName(fromSourceUploadVO.getName(),
                        fromSourceUploadVO.getRemark()), Function.identity(), (o1, o2) -> o1));
        List<FromSourceUploadVO> fromSourceUploadVOS = new ArrayList<>(quchong_yuan.values());

        List<FromSource> fromSources = Lists.newArrayList();
        fromSourceUploadVOS.forEach(fromSourceUploadVO -> {
            if (StringUtils.isNotBlank(fromSourceUploadVO.getRemark())
                    && StringUtils.isNotBlank(fromSourceUploadVO.getType())
            ) {

                String code = Objects.requireNonNull(com.cellosquare.adminapp.common.enums.FromSourceType.getEnumByVal(fromSourceUploadVO.getType())).getCode();
                com.cellosquare.adminapp.admin.registerStatistics.entity.FromSource fromSource = new com.cellosquare.adminapp.admin.registerStatistics.entity.FromSource(code, autogenterLiuShui(code),
                        null, StringUtils.isNotBlank(fromSourceUploadVO.getRemark()) ? fromSourceUploadVO.getRemark() : StringUtils.EMPTY,
                        null, StringUtils.isNotBlank(fromSourceUploadVO.getName()) ? fromSourceUploadVO.getName() : StringUtils.EMPTY,
                        fromSourceUploadVO.getShareLink());
                setMsg(fromSource);
                fromSource.setShareLink(UrlUtil.overrideUrlParams(fromSource.getShareLink(), fromSource.getSourceVal()));
                fromSources.add(fromSource);
            }
        });
        Boolean falg = false;

        if (CollectionUtil.isNotEmpty(fromSources)) {
            List<FromSource> db = fromSourceServiceImpl.lambdaQuery().list();
            List<String> collect = fromSources.stream().map(fromSource -> getXTName(fromSource.getName(), fromSource.getRemark())).collect(Collectors.toList());
            List<String> collect_db = db.stream().map(fromSource -> getXTName(fromSource.getName(), fromSource.getRemark())).collect(Collectors.toList());
            Set<String> set = collect_db.stream()
                    .collect(Collectors.toSet());
            for (String element : collect) {
                if (!set.add(element)) {
                    stringBuilder.append("存在重复（名称+渠道 唯一）：" + element + ",请修改后重试");
                    uploadException.setMessage(stringBuilder.toString());
                    falg = true;
                    return;
                }
            }
        }
        if (!falg) {
            fromSourceServiceImpl.saveBatch(fromSources);
            stringBuilder.append("成功条数:" + fromSources.size());
            uploadException.setMessage(stringBuilder.toString());
        }
        log.info("存储数据库成功！");
    }


    private String getXTName(String name, String remark) {
        String n1 = StringUtils.isNotBlank(name) ? name : "";
        String n2 = StringUtils.isNotBlank(remark) ? remark : "";
        return n1 + " " + n2;
    }

    private void setMsg(com.cellosquare.adminapp.admin.registerStatistics.entity.FromSource vo) {
        AdminSessionForm sessionForm = SessionManager.getAdminSessionForm();
        vo.setInsPersonId(sessionForm.getAdminId());
        vo.setUpdPersonId(sessionForm.getAdminId());
        vo.setInsDtm(new Timestamp(new Date().getTime()));
        vo.setUpdDtm(new Timestamp(new Date().getTime()));

        if (StringUtils.isEmpty(vo.getShareLink())) {
            String celloSquareUrl = XmlPropertyManager.getPropertyValue("celloSquare.website.prefix");
            celloSquareUrl += "?" + com.cellosquare.adminapp.common.constant.Constants.FROM_SOURCE + "=" + vo.getSourceVal();
            vo.setShareLink(celloSquareUrl);
        }
    }


    /**
     * 生成随机数  六位
     *
     * @param type
     * @return
     */
    private static String autogenterLiuShui(String type) {
        String temp = StringUtils.EMPTY;
        if (ObjectUtils.equals("1", type)) {//百度
            temp = "baidu_";
        } else if (ObjectUtils.equals("2", type)) {
            temp = "growing_io_";
        } else if (ObjectUtils.equals("3", type)) {
            temp = "guanwang_i_";
        }
        //生成随机数
        Random random = new Random();
        String code = StringUtils.EMPTY;
        for (int i = 0; i < 6; i++) {
            code += random.nextInt(9);
        }
        temp += code;
        return temp;
    }
}
