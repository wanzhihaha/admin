package com.cellosquare.adminapp.admin.apihistory.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cellosquare.adminapp.admin.apihistory.entity.ApiHistory;
import com.cellosquare.adminapp.admin.apihistory.mapper.ApiHistoryMapper;
import com.cellosquare.adminapp.admin.apihistory.service.IApiHistoryService;
import com.cellosquare.adminapp.admin.counselling.vo.CounsellingInfoVO;
import com.cellosquare.adminapp.admin.quote.entity.QuoteNation;
import com.cellosquare.adminapp.common.enums.ApihistoryEnum;
import com.cellosquare.adminapp.common.enums.ApihistoryStatusEnum;
import com.cellosquare.adminapp.common.util.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author walker
 * @since 2023-08-230 11:03:00
 */
@Service
public class ApiHistoryServiceImpl extends ServiceImpl<ApiHistoryMapper, ApiHistory> implements IApiHistoryService {

    @Override
    public void getList(Model model, ApiHistory vo) {
        LambdaQueryChainWrapper<ApiHistory> apiHistoryLambdaQueryChainWrapper = lambdaQuery()
                .eq(StrUtil.isNotEmpty(vo.getType()), ApiHistory::getType, vo.getType())
                .eq(Objects.nonNull(vo.getStatus()) && vo.getStatus() != 2, ApiHistory::getStatus, vo.getStatus())
                .orderByDesc(ApiHistory::getCreateDate);
        if (StringUtils.isNotEmpty(vo.getEndDate()) && StringUtils.isNotEmpty(vo.getStartDate())) {
            apiHistoryLambdaQueryChainWrapper
                    .le(ApiHistory::getCreateDate, DateUtils.parse(vo.getEndDate() + " 23:59:59", "yyyy-MM-dd HH:mm:ss"))
                    .ge(ApiHistory::getCreateDate, DateUtils.parse(vo.getStartDate() + " 00:00:00", "yyyy-MM-dd HH:mm:ss"));
        }
        if (StringUtils.isNotEmpty(vo.getSearchValue())) {
            apiHistoryLambdaQueryChainWrapper.and(apiHistoryLambdaQueryWrapper -> {
                apiHistoryLambdaQueryWrapper.or().like(ApiHistory::getRequestBody, vo.getSearchValue());
                apiHistoryLambdaQueryWrapper.or().like(ApiHistory::getResponseBody, vo.getSearchValue());
                apiHistoryLambdaQueryWrapper.or().like(ApiHistory::getExceptionMsg, vo.getSearchValue());
                apiHistoryLambdaQueryWrapper.or().like(ApiHistory::getUrl, vo.getSearchValue());
            });
        }


        Page<ApiHistory> page = apiHistoryLambdaQueryChainWrapper.page(new Page<>(Integer.parseInt(vo.getPage()),
                Integer.parseInt(vo.getRowPerPage())));
        List<ApiHistory> apiHistories = page.getRecords().stream().map(apiHistory -> {
            apiHistory.setTypeName(getTypeName(apiHistory.getType()));
            apiHistory.setStatusName(getStatusName(apiHistory.getStatus()));
            return apiHistory;
        }).collect(Collectors.toList());
        model.addAttribute("totalCount", page.getTotal());
        model.addAttribute("list", apiHistories);
    }

    @Override
    public void getDetail(Model model, ApiHistory vo) {
        ApiHistory one = lambdaQuery().eq(ApiHistory::getId, vo.getId()).one();
        one.setTypeName(getTypeName(one.getType()));
        one.setStatusName(getStatusName(one.getStatus()));
        model.addAttribute("detail", one);
    }

    private String getTypeName(String type) {
        return ApihistoryEnum.getEnumByCode(type).getCnValue();
    }

    private String getStatusName(Integer status) {
        return ApihistoryStatusEnum.getEnumByCode(status).getCnValue();
    }
}
