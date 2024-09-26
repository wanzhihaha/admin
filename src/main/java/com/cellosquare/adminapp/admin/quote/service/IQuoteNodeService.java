package com.cellosquare.adminapp.admin.quote.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cellosquare.adminapp.admin.quote.entity.QuoteNode;
import com.cellosquare.adminapp.admin.quote.entity.vo.AdminNodeVO;
import org.springframework.ui.Model;

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
public interface IQuoteNodeService extends IService<QuoteNode> {

    void getList(Model model, AdminNodeVO vo);

    AdminNodeVO getDetail(AdminNodeVO vo);

    void regist(AdminNodeVO vo);
    void registApi(AdminNodeVO vo);

    void doUpdate(AdminNodeVO vo);

    void delete(AdminNodeVO vo);

    Page<QuoteNode> getInnerPage(AdminNodeVO vo);

    void excelDownLoad(HttpServletRequest request, HttpServletResponse response, AdminNodeVO vo) throws Exception;

    int downloadCount(AdminNodeVO vo);

    void setHotOrNot(AdminNodeVO vo);

    void doSortOrder(List<AdminNodeVO> adminNationVOList);
}
