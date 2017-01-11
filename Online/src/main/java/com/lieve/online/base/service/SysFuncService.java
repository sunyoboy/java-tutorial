package com.lieve.online.base.service;

import com.lieve.online.base.common.IBaseService;
import com.lieve.online.base.dao.SysFuncDao;
import com.lieve.online.base.entity.SysFunc;

import java.util.List;
import java.util.Set;

public interface SysFuncService extends IBaseService<SysFunc, SysFuncDao> {

    /**
     * 根据用户权限得到菜单
     * @param permissions
     * @return
     */
    List<SysFunc> findMenus(Set<String> permissions);

    /**
     *
     * 功能描述: <br>
     * 根据用户的权利得到菜单列表
     *
     * @param userRight
     *            由系统功能编号组成的字符串，以,号分割
     * @return result 由功能列表名称组成的List
     * @see com.lieve.online.base.service.gzimpl.SysFuncServiceImpl#getCurrentUserMenuList(String)
     * @since 1.0
     */
    public List getCurrentUserMenuList(String userRight);
}