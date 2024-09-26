package main.java.com.cellosquare.adminapp.common.util;

import com.bluewaves.lab.util.StringUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.netease.yidun.sdk.antispam.AntispamRequester;
import com.netease.yidun.sdk.antispam.text.TextCheckClient;
import com.netease.yidun.sdk.antispam.text.v5.check.sync.batch.TextBatchCheckRequest;
import com.netease.yidun.sdk.antispam.text.v5.check.sync.batch.TextBatchCheckResponse;
import com.netease.yidun.sdk.antispam.text.v5.check.sync.single.TextCheckResult;
import com.netease.yidun.sdk.antispam.text.v5.check.sync.single.TextCheckSceneRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;


public class TextCheckUtil {
    private final static Logger logger = LoggerFactory.getLogger(TextCheckUtil.class);


    public static Boolean check(String req){
        HashMap<String, String> reqMap = Maps.newHashMap();
        reqMap.put(req,req);
        List<String> batchCheck = batchCheck(reqMap);
        return CollectionUtils.isEmpty(batchCheck);
    }
    /**
     * 批量校验敏感词
     *
     * @param request
     * @return
     */
    public static List<String> batchCheck(Map<String, String> request) {
        if (Objects.isNull(request) || CollectionUtils.isEmpty(request.keySet())) {
            return Lists.newArrayList();
        }
        //请求器
        TextCheckClient textCheckClient = new TextCheckClient(AntispamRequester.createDefaultProfile(com.cellosquare.adminapp.common.constant.TextCheckConstants.secretId, com.cellosquare.adminapp.common.constant.TextCheckConstants.secretKey));
        //请求体
        TextBatchCheckRequest textBatchCheckRequest = new TextBatchCheckRequest();
        textBatchCheckRequest.setBusinessId(com.cellosquare.adminapp.common.constant.TextCheckConstants.businessId);
        //前端入参转换请求实体
        List<TextCheckSceneRequest> texts = request.entrySet().stream().map(r -> {
            if (!StringUtil.isEmpty(r.getValue())) {
                TextCheckSceneRequest textCheckSceneRequest = new TextCheckSceneRequest();
                textCheckSceneRequest.setDataId(r.getKey());
                textCheckSceneRequest.setContent(r.getValue());
                return textCheckSceneRequest;
            }
            return null;
        }).filter(Objects::nonNull).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(texts)) {
            return Lists.newArrayList();
        }
        textBatchCheckRequest.setTexts(texts);
        //执行校验敏感数据
        TextBatchCheckResponse textBatchCheckResponse = textCheckClient.syncBatchCheckText(textBatchCheckRequest);
        System.out.println(textBatchCheckResponse);
        List<TextCheckResult> result = textBatchCheckResponse.getResult();
        //返回结果不为空
        if (Objects.equals(com.cellosquare.adminapp.common.constant.TextCheckConstants.SUCCESS_CODE, textBatchCheckResponse.getCode()) && !CollectionUtils.isEmpty(result)) {
            return result.stream().filter(r -> Objects.nonNull(r.getAntispam())
                    && com.cellosquare.adminapp.common.constant.TextCheckConstants.TEXT_CHECK_FAIL.contains(r.getAntispam().getSuggestion()))
                    .map(TextCheckResult::getAntispam)
                    .map(TextCheckResult.Antispam::getDataId).collect(Collectors.toList());
        }
        return Lists.newArrayList();
    }
}
