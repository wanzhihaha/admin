package com.cellosquare.adminapp.admin.quote.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cellosquare.adminapp.admin.helpSupport.vo.HelpSupportVo;
import com.cellosquare.adminapp.admin.quote.entity.QuoteNation;
import com.cellosquare.adminapp.admin.quote.entity.vo.AdminNationVO;
import com.cellosquare.adminapp.admin.quote.entity.vo.AdminNodeVO;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hugo
 * @since 2023-06-157 09:11:30
 */
public interface IQuoteNationService extends IService<QuoteNation> {


    Page<QuoteNation>  getList(AdminNationVO vo);

    List<QuoteNation> getAll();

    List<Long> getSearchNation(AdminNodeVO vo);

    void getList(Model model, AdminNationVO vo);

    void regist(AdminNationVO vo, HttpServletResponse response, MultipartHttpServletRequest muServletRequest);

    void updateForm(Model model, AdminNationVO vo);

    void doUpdate(HttpServletRequest request, HttpServletResponse response, MultipartHttpServletRequest muServletRequest, Model model, AdminNationVO vo);

    void detail(Model model, AdminNationVO vo);

    void delete(AdminNationVO vo);

    void doSortOrder(List<AdminNationVO> adminNationVOList);

    List<AdminNationVO> checkCountry(AdminNationVO vo);

    void excelDownLoad(HttpServletRequest request, HttpServletResponse response, AdminNationVO vo) throws Exception;

    int downloadCount(AdminNationVO vo);

    List<AdminNationVO> getNationByLangCd();

    AdminNationVO getDetailByNationCd(String nationCd);

    void bianhuan();

    void updateImg(AdminNationVO vo);

    void setHotOrNot(AdminNationVO vo);
}
