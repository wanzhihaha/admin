package com.cellosquare.adminapp.admin.helpSupportMenu.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.cellosquare.adminapp.admin.helpSupportMenu.entity.HelpSupportMenu;
import com.cellosquare.adminapp.admin.helpSupportMenu.vo.HelpSupportMenuVo;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author hugo
 * @since 2023-03-65 09:48:42
 */
public interface IHelpSupportMenuService extends IService<HelpSupportMenu> {

    List<HelpSupportMenu> getSMenu(String pId);

    List<HelpSupportMenu> getAllMenu();

    List<HelpSupportMenuVo> getAllMenuTop();

    void getList(Model model, HelpSupportMenuVo vo);

    void detail(Model model, HelpSupportMenuVo vo);

    void register(HttpServletRequest request, HttpServletResponse response, HelpSupportMenuVo vo);

    void updateForm(Model model, HelpSupportMenuVo vo);

    void doUpdate(HttpServletRequest request, HttpServletResponse response, HelpSupportMenuVo vo);

    void doDelete(HelpSupportMenuVo vo);

    void doSortOrder(List<HelpSupportMenuVo> helpSupportMenuVos);
}
