package main.java.com.cellosquare.adminapp.common.interceptor.menu;

import com.cellosquare.adminapp.admin.code.vo.ApiCodeVO;
import com.cellosquare.adminapp.admin.productsMenu.entity.ProductsMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AdminMenuInterceptor extends HandlerInterceptorAdapter {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private com.cellosquare.adminapp.admin.code.service.ApiCodeService apiCodeServiceImpl;
	@Autowired
	private com.cellosquare.adminapp.admin.productsMenu.service.IProductsMenuService productsMenuServiceImpl;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		com.cellosquare.adminapp.admin.code.vo.ApiCodeVO vo = new com.cellosquare.adminapp.admin.code.vo.ApiCodeVO();
		vo.setGrpCd("LANG_CD");
		vo.setLangCd("A");
		List<ApiCodeVO> langList = apiCodeServiceImpl.getApiCodeList(vo);
		request.setAttribute("langList", langList);
		//查询产品菜单
//		ApiCodeVO vo_product = new ApiCodeVO();
//		vo_product.setGrpCd(ProductEnum.GOODS_LIST.getCode());
//		vo_product.setLangCd("cn-zh");
		//List<ApiCodeVO> langList_product = apiCodeServiceImpl.getApiCodeList(vo_product);
		List<ProductsMenu> list = productsMenuServiceImpl.lambdaQuery().eq(com.cellosquare.adminapp.admin.productsMenu.entity.ProductsMenu::getUseYn, com.cellosquare.adminapp.common.constant.UseEnum.USE.getCode()).orderByAsc(com.cellosquare.adminapp.admin.productsMenu.entity.ProductsMenu::getOrdb)
				.orderByDesc(com.cellosquare.adminapp.admin.productsMenu.entity.ProductsMenu::getInsDtm).list();
		request.setAttribute("productMenus", list);
		return true;
	}
}
