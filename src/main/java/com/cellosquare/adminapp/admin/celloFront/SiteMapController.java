package com.cellosquare.adminapp.admin.celloFront;

import cn.hutool.http.HttpUtil;
import com.bluewaves.lab.property.XmlPropertyManager;
import com.cellosquare.adminapp.common.adminAnnotion.CleanCacheAnnotion;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("siteMap")
public class SiteMapController {
    /**
     * 调cellosquare接口生成网站地图
     *
     * @param type
     * @return
     * @throws IOException
     */
    @GetMapping("/{type}.do")
    @CleanCacheAnnotion
    public String siteMap(@PathVariable String type, HttpServletResponse response) throws IOException {
        String celloSquareUrl = XmlPropertyManager.getPropertyValue("celloSquare.website.prefix");
        String url = celloSquareUrl + "/api/sitemap/" + type;
        return HttpUtil.get(url);
    }
}
