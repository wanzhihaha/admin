package com.cellosquare.adminapp.admin.estimate.mapper;

import com.cellosquare.adminapp.admin.estimate.vo.AdminEstimateVO;

import java.util.List;

/**
 * 20220617 update
 * AdminEstimateMapper
 * @author juru.jia
 * @Date : 2022/06/17
 */
public interface AdminEstimateMapper {

    /**
     * get total count by search param
     * @param vo
     * @return
     */
    public int getTotalCount(AdminEstimateVO vo);

    /**
     * get quote api request history list
     * @param vo
     * @return
     */
    public List<AdminEstimateVO> getList(AdminEstimateVO vo);

    /**
     * get quote api request history list by limit page
     * @param vo
     * @return
     */
    public List<AdminEstimateVO> getListByPage(AdminEstimateVO vo);
}
