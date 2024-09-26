package com.cellosquare.adminapp.admin.estimate.service;

import com.cellosquare.adminapp.admin.estimate.vo.AdminEstimateVO;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 20220617 update
 * AdminEstimateService
 * @author juru.jia
 * @Date : 2022/06/17
 */
public interface AdminEstimateService {

    /**
     * quote history excel download
     * @param vo
     * @param response
     * @return
     */
    public void quoteExcelDownload(AdminEstimateVO vo, HttpServletResponse response) throws Exception;

    /**
     * get quote history list count
     * @param vo
     * @return
     */
    public int getTotalCount(AdminEstimateVO vo);

    /**
     * get quote history list by limit page
     * @param vo
     * @return
     */
    public List<AdminEstimateVO> getListByPage(AdminEstimateVO vo);
}
