package com.cellosquare.adminapp.admin.celloFront;

import com.cellosquare.adminapp.common.adminAnnotion.CleanCacheAnnotion;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 清除缓存
 */
@RestController
@RequestMapping("cleanCache")
public class CleanCacheController {

    @GetMapping("/prd.do")
    @CleanCacheAnnotion
    public String cleanCache() throws Exception {
        return "success";
    }
}
