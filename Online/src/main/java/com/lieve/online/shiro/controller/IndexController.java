package com.lieve.online.shiro.controller;

import com.lieve.online.base.entity.SysFunc;
import com.lieve.online.base.entity.UserInfo;
import com.lieve.online.base.service.SysFuncService;
import com.lieve.online.base.service.UserInfoService;
import com.lieve.online.shiro.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Set;

/**
 * Created by DD240 on 2016/2/29.
 */
@Controller
public class IndexController {

    @Autowired
    private SysFuncService sysFuncService;

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("/")
    public String index(@CurrentUser UserInfo loginUser, Model model) {
        Set<String> permissions = userInfoService.findPermissions(loginUser.getLoginName());
        List<SysFunc> menus = sysFuncService.findMenus(permissions);
        model.addAttribute("menus", menus);
        return "index";
    }

    @RequestMapping("/welcome")
    public String welcome() {
        return "welcome";
    }
}
