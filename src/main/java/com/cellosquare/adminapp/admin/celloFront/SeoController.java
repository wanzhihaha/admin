package com.cellosquare.adminapp.admin.celloFront;

import com.cellosquare.adminapp.admin.antistop.service.IAntistopService;
import com.cellosquare.adminapp.admin.terminology.service.ITerminologyService;
import com.cellosquare.adminapp.common.adminAnnotion.CleanCacheAnnotion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("seoMatch")
public class SeoController {

    @Autowired
    private ITerminologyService terminologyServiceImpl;
    @Autowired
    private IAntistopService antistopServiceImpl;

    /**
     * 重新匹配一遍关键词或术语
     *
     * @param request
     * @param response
     * @param type
     * @param listId   需要匹配的id
     * @return
     */
    @GetMapping("/match.do")
    @CleanCacheAnnotion
    public String match(HttpServletRequest request, HttpServletResponse response, @RequestParam("type") String type
            , @RequestParam("listId") List<String> listId) {
        if (Objects.equals("1", type)) {
            terminologyServiceImpl.seoMatch(request, response, listId);
        } else if (Objects.equals("2", type)) {
            antistopServiceImpl.seoMatch(request, response, listId);
        }
        return "success";
    }
}
