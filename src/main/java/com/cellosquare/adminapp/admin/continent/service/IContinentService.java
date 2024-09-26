package com.cellosquare.adminapp.admin.continent.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.cellosquare.adminapp.admin.continent.entity.Continent;
import com.cellosquare.adminapp.admin.continent.vo.ContinentVo;
import org.springframework.ui.Model;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author walker
 * @since 2023-11-314 14:07:21
 */
public interface IContinentService extends IService<Continent> {

    void getList(Model model, ContinentVo vo);

    void doSortOrder(List<ContinentVo> continentVos);
}
