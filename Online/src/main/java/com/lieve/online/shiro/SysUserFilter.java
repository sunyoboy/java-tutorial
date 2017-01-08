package com.lieve.online.shiro;

import com.sjdd.base.service.UserInfoService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Created by DD240 on 2016/2/26.
 */
public class SysUserFilter extends PathMatchingFilter {

    @Autowired
    private UserInfoService userInfoService;

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {

        String username = (String) SecurityUtils.getSubject().getPrincipal();
        request.setAttribute(Constants.CURRENT_USER, userInfoService.findByUsername(username));
        return true;
    }
}
