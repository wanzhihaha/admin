package com.cellosquare.adminapp.admin.counselling.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cellosquare.adminapp.admin.counselling.vo.CounsellingInfoVO;
import com.cellosquare.adminapp.admin.recommend.vo.HotRecommend;
import org.springframework.ui.Model;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface CounsellingInfoService extends IService<CounsellingInfoVO> {

    public List<CounsellingInfoVO> selectUserInfo(CounsellingInfoVO vo);
    public int selectUserInfoCount(CounsellingInfoVO vo);
    public int doWriteLog(CounsellingInfoVO vo);

    void excelDownLoad(HttpServletRequest request, HttpServletResponse response) throws Exception;

    void jumpList(Model model, CounsellingInfoVO vo);
}
