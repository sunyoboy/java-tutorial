package com.lieve.online.shiro.service;


import com.lieve.online.shiro.dao.PermissionDao;
import com.lieve.online.shiro.dao.PermissionDaoImpl;
import com.lieve.online.shiro.entity.Permission;
import org.springframework.stereotype.Service;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {

    private PermissionDao permissionDao = new PermissionDaoImpl();

    public Permission createPermission(Permission permission) {
        return permissionDao.createPermission(permission);
    }

    public void deletePermission(Long permissionId) {
        permissionDao.deletePermission(permissionId);
    }
}
