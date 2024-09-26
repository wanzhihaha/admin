package com.cellosquare.adminapp.admin.recommend;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bluewaves.lab.message.XmlMessageManager;
import com.bluewaves.lab.message.util.ActionMessageUtil;
import com.bluewaves.lab.property.XmlPropertyManager;
import com.bluewaves.lab.util.StringUtil;
import com.cellosquare.adminapp.admin.recommend.service.HotRecommendService;
import com.cellosquare.adminapp.admin.recommend.vo.HotRecommend;
import com.cellosquare.adminapp.common.session.AdminSessionForm;
import com.cellosquare.adminapp.common.session.SessionManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequestMapping("/celloSquareAdmin/hotRecommend")
@Controller
@Slf4j
public class HotRecommendController {

    @Autowired
    private HotRecommendService hotRecommendServiceImpl;

    /**
     * 查询list
     *
     * @param model
     * @param vo
     * @return
     */
    @GetMapping("/list.do")
    public String list(Model model, @ModelAttribute("vo") HotRecommend vo) {
        AdminSessionForm sessionForm = SessionManager.getAdminSessionForm();
        vo.setLangCd(sessionForm.getLangCd());
        Page<HotRecommend> page = hotRecommendServiceImpl.lambdaQuery()
                .eq(StrUtil.isNotEmpty(vo.getSearchType()), HotRecommend::getType, vo.getSearchType())
                .eq(StrUtil.isNotEmpty(vo.getUseYn()), HotRecommend::getUseYn, vo.getUseYn())
                .like(StrUtil.isNotEmpty(vo.getSearchValue()), HotRecommend::getTitle, vo.getSearchValue())
                .orderByDesc(HotRecommend::getInsDtm)
                .page(new Page<>(Integer.parseInt(vo.getPage()), Integer.parseInt(vo.getRowPerPage())));
        model.addAttribute("totalCount", page.getTotal());
        List<HotRecommend> list = page.getRecords().stream().map(hotRecommend -> {
            hotRecommendServiceImpl.dealData(hotRecommend);
            return hotRecommend;
        }).collect(Collectors.toList());
        model.addAttribute("list", list);
        return "admin/basic/hotRecommend/list";
    }

    @PostMapping("/detail.do")
    public String detail(Model model, @ModelAttribute("vo") HotRecommend vo) {
        HotRecommend result = hotRecommendServiceImpl.lambdaQuery().eq(HotRecommend::getId, vo.getId()).one();
        hotRecommendServiceImpl.dealData(result);
        model.addAttribute("detail", result);
        model.addAttribute("vo", vo);
        return "admin/basic/hotRecommend/detail";
    }


    /**
     * 跳转到新增页
     *
     * @param model
     * @param vo
     * @return
     */
    @PostMapping("/registerForm.do")
    public String registerForm(Model model, @ModelAttribute("vo") HotRecommend vo) {
        return "admin/basic/hotRecommend/registerForm";
    }

    /**
     * 新增数据接口
     *
     * @param request
     * @param model
     * @param vo
     * @return
     */
    @PostMapping("/register.do")
    public String doWrite(HttpServletRequest request, Model model, @ModelAttribute("vo") HotRecommend vo) {
        try {
            hotRecommendServiceImpl.doWrite(request, vo);
            ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.write.success"));
            return "redirect:" + XmlPropertyManager.getPropertyValue("system.admin.path") + "/hotRecommend/list.do";
        } catch (Exception e) {
            ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.write.fail"),
                    null, null, null, true);
            return "redirect:" + XmlPropertyManager.getPropertyValue("system.admin.path") + "/hotRecommend/registerForm.do";
        }
    }


    /**
     * 跳转到修改页
     *
     * @param model
     * @param vo
     * @return
     */
    @PostMapping("/updateForm.do")
    public String updateForm(Model model, @ModelAttribute("vo") HotRecommend vo) {
        HotRecommend detailVO = hotRecommendServiceImpl.lambdaQuery().eq(HotRecommend::getId, vo.getId()).one();
        model.addAttribute("detail", detailVO);
        model.addAttribute("contIU", "U");
        return "admin/basic/hotRecommend/registerForm";
    }

    /**
     * 修改
     *
     * @param model
     * @param vo
     * @return
     */
    @PostMapping("/update.do")
    public String doUpdate(HttpServletRequest request, Model model, @ModelAttribute("vo") HotRecommend vo) {
        try {
            hotRecommendServiceImpl.doUpdate(request, vo);
            Map<String, Object> hmParam = new HashMap<>();
            hmParam.put("id", vo.getId());
            model.addAttribute("msg_type", ":submit");
            model.addAttribute("method", "post");
            model.addAttribute("msg", XmlMessageManager.getMessageValue("message.common.modify.success"));
            model.addAttribute("url", "./detail.do");
            model.addAttribute("hmParam", hmParam);
            return "admin/common/message";
        } catch (Exception e) {
            ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.modify.fail"),
                    null, null, null, true);
            return "admin/basic/hotRecommend/updateForm";
        }
    }

    /**
     * 删除
     *
     * @param request
     * @param vo
     * @return
     */
    @PostMapping("/doDelete.do")
    public String delete(HttpServletRequest request, Model model, @ModelAttribute("vo") HotRecommend vo) {
        try {
            hotRecommendServiceImpl.doDelete(vo);
            Map<String, String> hmParam = new HashMap<String, String>();
            model.addAttribute("msg_type", ":submit");
            model.addAttribute("msg", XmlMessageManager.getMessageValue("message.common.delete.success"));
            model.addAttribute("url", "./list.do");
            model.addAttribute("hmParam", hmParam);
            return "admin/common/message";
        } catch (Exception e) {
            ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.delete.fail"), null, null, null, true);
            return "redirect:" + XmlPropertyManager.getPropertyValue("system.admin.path") + "/hotRecommend/detail.do";
        }
    }

    /**
     * 自定义排序
     *
     * @param request
     * @param model
     * @param vo
     * @return
     */
    @PostMapping("/doSortOrder.do")
    public String doSortOrder(HttpServletRequest request, Model model, @ModelAttribute("vo") HotRecommend vo) {
        try {
            HotRecommend adminBannerVO = null;
            for (int i = 0; i < vo.getListRecommendSeqNo().length; i++) {
                adminBannerVO = new HotRecommend();
                adminBannerVO.setId(vo.getListRecommendSeqNo()[i]);
                // 문자열이 숫자인지 확인
                String str = "";
                if (vo.getListOrdb().length > 0) {
                    str = vo.getListOrdb()[i] == null ? null : vo.getListOrdb()[i].toString();
                }
                if (!StringUtil.nvl(str).equals("")) {
                    boolean isNumeric = str.matches("[+-]?\\d*(\\.\\d+)?");
                    // 숫자라면 true
                    if (isNumeric) {
                        int num = vo.getListOrdb()[i];
                        if (0 <= num && 1000 > num) {
                            adminBannerVO.setOrdb(vo.getListOrdb()[i]);
                        } else { //1부터 999까지만 값을 체크 그값이 아니라면 오류메세지 
                            ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.modify.fail"), null, null, null, true);
                            return "admin/basic/hotRecommend/list";
                        }
                    } else { //숫자가 아니면 오류메세지
                        ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.modify.fail"), null, null, null, true);
                        return "admin/basic/hotRecommend/list";
                    }
                }
                hotRecommendServiceImpl.update(Wrappers.<HotRecommend>update().set("ordb", adminBannerVO.getOrdb()).eq("id", adminBannerVO.getId()));
            }
            Map<String, String> hmParam = new HashMap<String, String>();
            model.addAttribute("msg_type", ":submit");
            model.addAttribute("msg", XmlMessageManager.getMessageValue("message.common.modify.success"));
            model.addAttribute("url", "./list.do");
            model.addAttribute("hmParam", hmParam);
            return "admin/common/message";
        } catch (Exception e) {
            ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.modify.fail"), null, null, null, true);
            return "redirect:" + XmlPropertyManager.getPropertyValue("system.admin.path") + "/hotRecommend/list.do";
        }
    }

}
