package com.lieve.online.base.service;

import com.lieve.online.base.common.IBaseService;
import com.lieve.online.base.dao.UserInfoDao;
import com.lieve.online.base.entity.UserInfo;

import java.util.Set;

public interface UserInfoService extends IBaseService<UserInfo, UserInfoDao> {

    public UserInfo findByUsername(String username);

    /**
     * 根据用户名查找其角色
     * @param username
     * @return
     */
    public Set<String> findRoles(String username);

    /**
     * 根据用户名查找其权限
     * @param username
     * @return
     */
    public Set<String> findPermissions(String username);

    public void create(UserInfo userInfo);

    public UserInfo getByLoginName(String loginName);
}