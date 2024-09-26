package main.java.com.cellosquare.adminapp.common.util;

import com.cellosquare.adminapp.admin.helpSupportMenu.entity.HelpSupportMenu;

import java.util.List;
import java.util.stream.Collectors;

public class HelpSpportMenuUtil {

    /**
     * 根据子节点获取最上层节点
     *
     * @param deptAll   所有菜单集合
     * @param deptChild 子节点
     * @return
     */
    public static com.cellosquare.adminapp.admin.helpSupportMenu.entity.HelpSupportMenu getMaximumParent(List<HelpSupportMenu> deptAll, com.cellosquare.adminapp.admin.helpSupportMenu.entity.HelpSupportMenu deptChild) {
        com.cellosquare.adminapp.admin.helpSupportMenu.entity.HelpSupportMenu dept = null;
        Long parentId = deptChild.getPId();
        //根节点
        if (0 == parentId) {
            dept = deptChild;
        } else {
            List<HelpSupportMenu> parent = deptAll.stream().filter(item -> item.getId() == parentId).collect(Collectors.toList());
            com.cellosquare.adminapp.admin.helpSupportMenu.entity.HelpSupportMenu maximumParent = getMaximumParent(deptAll, parent.get(0));
            dept = maximumParent;
        }
        return dept;
    }
}
