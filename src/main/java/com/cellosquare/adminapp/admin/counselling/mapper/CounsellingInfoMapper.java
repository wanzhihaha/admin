package com.cellosquare.adminapp.admin.counselling.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cellosquare.adminapp.admin.counselling.vo.CounsellingInfoVO;

import java.util.List;

public interface CounsellingInfoMapper extends BaseMapper<CounsellingInfoVO> {

    public List<CounsellingInfoVO> selectUserInfo(CounsellingInfoVO vo);
    public int selectUserInfoCount(CounsellingInfoVO vo);
    public int doWriteLog(CounsellingInfoVO vo);

}
