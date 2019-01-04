package com.krista.eagle.admin.controller.api;

import com.krista.extend.base.model.MenuVo;
import com.krista.extend.base.response.JsonResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * MenuApiController
 *
 * @author dw_wangdonghong
 * @version V1.0
 * @since 2019/1/4 15:29
 */
@RestController
@RequestMapping("/menu")
public class MenuApiController {

    @RequestMapping("/listMenu")
    public JsonResponse<List<MenuVo>> listMenu() {
        List<MenuVo> menus = new ArrayList<>();
        MenuVo homeMenu = new MenuVo(1L, "首页", "/", "fa fa-home");
        MenuVo sysMenu = new MenuVo(2L, "系统管理", "/", "fa fa-server");
        MenuVo sysMenu01 = new MenuVo(3L, "菜单路由1", "/test", "");
        MenuVo sysMenu02 = new MenuVo(4L, "菜单路由2", "/test2", "");
        sysMenu.setChildren(new ArrayList<>());
        sysMenu.getChildren().add(sysMenu01);
        sysMenu.getChildren().add(sysMenu02);
        MenuVo testMenu = new MenuVo(5L, "系统管理", "/", "fa fa-bicycle");
        menus.add(homeMenu);
        menus.add(sysMenu);
        menus.add(testMenu);

        return new JsonResponse<>(menus);
    }
}
