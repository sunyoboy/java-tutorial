package com.lieve.online.base.service;

import com.lieve.online.base.common.IBaseService;
import com.lieve.online.base.dao.SysRoleDao;
import com.lieve.online.base.entity.SysRole;

import java.util.Set;

public interface SysRoleService extends IBaseService<SysRole, SysRoleDao> {


    /**
     * 根据用户角色编号找到标识符
     * @param roleId
     * @return
     */
    public SysRole findOne(int roleId);

    /**
     * 根据角色编号得到角色标识符列表
     * @param roleId
     * @return
     */
    Set<String> findRoles(int roleId);


    /**
     * 根据角色编号得到权限字符串列表
     * @param roleIds
     * @return
     */
    Set<String> findPermissions(Long[] roleIds);
}