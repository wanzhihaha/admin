package com.cellosquare.adminapp.admin.helpSupport.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.cellosquare.adminapp.admin.helpSupport.entity.HelpSupport;
import com.cellosquare.adminapp.admin.helpSupport.vo.HelpSupportVo;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author hugo
 * @since 2023-03-65 09:27:10
 */
public interface IHelpSupportService extends IService<HelpSupport> {


    /**
     * 新增
     *
     * @param request
     * @param vo
     */
    void register(HttpServletRequest request, HttpServletResponse response, HelpSupportVo vo);

    /**
     * 修改
     *
     * @param request
     * @param response
     * @param vo
     */
    void doUpdate(HttpServletRequest request, HttpServletResponse response, HelpSupportVo vo);

    /**
     * 删除
     *
     * @param vo
     */
    void doDelete(HelpSupportVo vo);

    /**
     * 集合
     *
     * @param model
     * @param vo
     */
    void getList(Model model, HelpSupportVo vo);

    /**
     * 导出 数量
     *
     * @param vo
     * @return
     */
    int downloadCount(HelpSupportVo vo);

    /**
     * 导出
     *
     * @param request
     * @param response
     * @param vo
     */
    void excelDownLoad(HttpServletRequest request, HttpServletResponse response, HelpSupportVo vo) throws Exception;

    /**
     * 详情
     *
     * @param model
     * @param vo
     */
    void detail(Model model, HelpSupportVo vo);

    /**
     * 修改页面
     *
     * @param model
     * @param vo
     */
    void updateForm(Model model, HelpSupportVo vo);

    /**
     * 批量导入
     *
     * @param file
     * @param request
     * @param response
     * @param topId
     */
    void saveImportWord(MultipartFile file, HttpServletRequest request, HttpServletResponse response, String topId) throws Exception;

    /**
     * 导出模板
     *
     * @param request
     * @param response
     */
    void downloadTemplete(HttpServletRequest request, HttpServletResponse response);

    List<HelpSupport> queryHelpSupportByMenu(Long id);

    List<HelpSupportVo> queryQAndA();

    List<HelpSupportVo> queryQAndAByIds(List<Long> ids);

    void doSortOrder(List<HelpSupportVo> helpSupportVos);

    /**
     * 批量删除
     *
     * @param vo
     */
    void batchDelete(HelpSupportVo vo);
}
