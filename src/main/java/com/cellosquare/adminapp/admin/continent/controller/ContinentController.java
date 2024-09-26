package com.cellosquare.adminapp.admin.continent.controller;


import com.cellosquare.adminapp.admin.continent.service.IContinentService;
import com.cellosquare.adminapp.admin.continent.vo.ContinentVo;
import com.cellosquare.adminapp.admin.productsMenu.vo.ProductsMenuVo;
import com.cellosquare.adminapp.common.adminAnnotion.CleanCacheAnnotion;
import com.cellosquare.adminapp.common.util.CommonMessageModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author walker
 * @since 2023-11-314 14:07:21
 */
@RequestMapping("/celloSquareAdmin/continent")
@Controller
@Slf4j
public class ContinentController {

    @Autowired
    private IContinentService continentServiceImpl;

    @GetMapping("/list.do")
    public String list(Model model, @ModelAttribute("vo") ContinentVo vo) {
        continentServiceImpl.getList(model, vo);
        return "admin/basic/continent/list";
    }

    @PostMapping("/doSortOrder.do")
    @CleanCacheAnnotion
    public String doSortOrder(Model model, @ModelAttribute("vo") ContinentVo vo) {
        List<ContinentVo> continentVos = new ArrayList<>();
        for (int i = 0; i < vo.getListId().length; i++) {
            ContinentVo continentVo = new ContinentVo();
            continentVo.setId(vo.getListId()[i]);
            continentVo.setFclOrdb(vo.getListFclOrdb()[i]);
//            continentVo.setLclOrdb(vo.getListLclOrdb()[i]);
//            continentVo.setAirOrdb(vo.getListAirOrdb()[i]);
//            continentVo.setExpressOrdb(vo.getListExpressOrdb()[i]);
            continentVos.add(continentVo);
        }
        if (Arrays.asList(vo.getListFclOrdb()).contains(StringUtils.EMPTY) || Arrays.asList(vo.getListFclOrdb()).contains(null)) {
            return CommonMessageModel.setModel("序号不能为空", "./list.do", "get", model
                    , new HashMap());
        }
        continentServiceImpl.doSortOrder(continentVos);
        return CommonMessageModel.setModel(model, new HashMap() {
        });
    }

}
