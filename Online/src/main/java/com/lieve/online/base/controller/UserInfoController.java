package com.lieve.online.base.controller;

import com.lieve.online.base.common.BaseAction;
import com.lieve.online.base.common.QueryResult;
import com.lieve.online.base.entity.UserInfo;
import com.lieve.online.base.service.UserInfoService;
import com.lieve.online.shiro.service.PasswordHelper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by DD240 on 2016/2/25.
 */
@Controller
@RequestMapping("/user")
public class UserInfoController extends BaseAction<UserInfo, UserInfoService> {

    private PasswordHelper passwordHelper = new PasswordHelper();

    @SuppressWarnings("rawtypes")
    @Deprecated
    @RequestMapping(value = "/findall", method = RequestMethod.GET)
    @ResponseBody
    public QueryResult findAll() {
        UserInfo user = new UserInfo();
        user.setUserStatus(1);
        return this.service.query(0, 10, user, 1, 0);
    }

    @Autowired
    public void setService(UserInfoService service) {
        super.setService(service);
    }

    @RequestMapping(value = "/createUserInfo")
    public String createUser(@RequestParam(value = "username") String username,
                             @RequestParam(value = "password") String password) {
        UserInfo user = new UserInfo();
        user.setLoginName(username);
        user.setPassword(password);

        //加密密码
        passwordHelper.encryptPassword(user);
        user.setRealName(username);
        user.setUserStatus(UserInfo.STATUS_UNACTIVE);
        user.setType(UserInfo.TYPE_AUDIENCE);
        this.service.create(user);
        return "success";
    }

    @RequestMapping(value = "/findById")
    public ModelAndView findById(int id) {
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo = this.service.getById(id);
        mv.addObject("userInfo", userInfo);
        mv.setViewName("success");
        return mv;
    }

    @RequestMapping(value = "/updateUserInfo")
    public void updateUserInfo(@ModelAttribute UserInfo userInfo) {
        this.service.update(userInfo);
    }

    @RequestMapping(value = "/deleteUserById")
    public void deleteUserInfo(int id) {
        this.service.delete(id);
    }

    @RequiresPermissions("user:view")
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        return "user/list";
    }

    @RequiresPermissions("user:view")
    @RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
    public String showDetail(@PathVariable("id") Long id, Model model) {
        // setCommonData(model);
        model.addAttribute("user", service.getById(id));
        model.addAttribute("op", "修改");
        return "user/user_detail";
    }

    @RequiresPermissions("user:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String showCreateForm(Model model) {
        // setCommonData(model);
        model.addAttribute("user", new UserInfo());
        model.addAttribute("op", "新增");
        return "user/user_form";
    }

    @RequiresPermissions("user:create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public String create(@Valid UserInfo user, RedirectAttributes redirectAttributes) {
        service.create(user);
        redirectAttributes.addFlashAttribute("msg", "新增成功");
        return "user/success";
    }

    @RequiresPermissions("user:update")
    @RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        // setCommonData(model);
        model.addAttribute("user", service.getById(id));
        model.addAttribute("op", "修改");
        return "user/user_form";
    }

    @RequiresPermissions("user:update")
    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public String update(UserInfo user, RedirectAttributes redirectAttributes) {
        service.update(user);
        redirectAttributes.addFlashAttribute("msg", "修改成功");
        return "user/success";
    }

/*    @RequiresPermissions("user:delete")
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String showDeleteForm(@PathVariable("id") Long id, Model model) {
        // setCommonData(model);
        model.addAttribute("user", service.getById(id));
        model.addAttribute("op", "删除");
        return "user/edit";
    }*/

    @RequiresPermissions("user:delete")
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        service.delete(id);
        redirectAttributes.addFlashAttribute("msg", "删除成功");
        return "redirect:/user";
    }


    @RequiresPermissions("user:update")
    @RequestMapping(value = "/{id}/changePassword", method = RequestMethod.GET)
    public String showChangePasswordForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", service.getById(id));
        model.addAttribute("op", "修改密码");
        return "user/changePassword";
    }

    @RequiresPermissions("user:update")
    @RequestMapping(value = "/{id}/changePassword", method = RequestMethod.POST)
    public String changePassword(@PathVariable("id") Long id, String newPassword, RedirectAttributes redirectAttributes) {
        // service.changePassword(id, newPassword);
        redirectAttributes.addFlashAttribute("msg", "修改密码成功");
        return "redirect:/user";
    }

    @RequestMapping(value = "/success", method = RequestMethod.POST)
    @ResponseBody
    public String result() {
        return "success";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public String registerUser(@RequestParam String uid, @RequestParam String pid, @RequestParam String email, @RequestParam String phone) {
        UserInfo user = new UserInfo();
        user.setLoginName(uid);
        user.setPassword(pid);
        user.setEmail(email);
        user.setPhoto(phone);
        user.setType(UserInfo.TYPE_AUDIENCE);
        user.setRoleNo(1);
        user.setGroupNo(1);
        user.setUserStatus(UserInfo.STAUS_NORMAL);
        user.setRealName("");
        user.setSex(0);
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        user.setCreateTime(timestamp);
        user.setSumOnline(0);
        service.create(user);
        return "{\"flag\":1}";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public UserInfo loginUser(@RequestParam String uid, @RequestParam String pid) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(uid, pid);
        subject.login(token);
        UserInfo userInfo = null;
        if(subject.isAuthenticated()) {
            userInfo = service.getByLoginName(uid);
        }
        return userInfo;
    }
}
