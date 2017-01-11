package com.lieve.online.base.controller;

import com.lieve.online.base.common.BaseAction;
import com.lieve.online.base.common.QueryResult;
import com.lieve.online.base.entity.SysRole;
import com.lieve.online.base.service.SysRoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by DD240 on 2016/2/26.
 */
@Controller
@RequestMapping("/role")
public class SysRoleController  extends BaseAction<SysRole, SysRoleService> {

    @Autowired
    public void setService(SysRoleService service) {
        super.setService(service);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        // model.addAttribute("userList", userService.findAll());
        return "role/list";
    }

    @SuppressWarnings("rawtypes")
    @Deprecated
    @RequestMapping(value = "/findall", method = RequestMethod.GET)
    @ResponseBody
    public QueryResult findAll() {
        SysRole sysRole = new SysRole();
        return this.service.query(0, 10, sysRole, 1, 0);
    }

/*
    @RequiresPermissions("role:view")
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("roleList", service.findAll());
        return "role/list";
    }
*/

    @RequiresPermissions("user:view")
    @RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
    public String showDetail(@PathVariable("id") Long id, Model model) {
        // setCommonData(model);
        model.addAttribute("role", service.getById(id));
        model.addAttribute("op", "修改");
        return "role/role_detail";
    }

    @RequiresPermissions("role:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String showCreateForm(Model model) {
        setCommonData(model);
        model.addAttribute("role", new SysRole());
        model.addAttribute("op", "新增");
        return "role/role_form";
    }

    @RequiresPermissions("role:create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(SysRole sysRole, RedirectAttributes redirectAttributes) {
        service.create(sysRole);
        redirectAttributes.addFlashAttribute("msg", "新增成功");
        return "redirect:/role";
    }

    @RequiresPermissions("role:update")
    @RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        setCommonData(model);
        model.addAttribute("role", service.getById(id));
        model.addAttribute("op", "修改");
        return "role/role_form";
    }

    @RequiresPermissions("role:update")
    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public String update(SysRole role, RedirectAttributes redirectAttributes) {
        service.update(role);
        redirectAttributes.addFlashAttribute("msg", "修改成功");
        return "redirect:/role";
    }

    @RequiresPermissions("role:delete")
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String showDeleteForm(@PathVariable("id") Long id, Model model) {
        setCommonData(model);
        model.addAttribute("role", service.getById(id));
        model.addAttribute("op", "删除");
        return "role/edit";
    }

    @RequiresPermissions("role:delete")
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
    public String delete(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        service.delete(id);
        redirectAttributes.addFlashAttribute("msg", "删除成功");
        return "redirect:/role";
    }

    private void setCommonData(Model model) {
        // model.addAttribute("resourceList", resourceService.findAll());
    }
}
