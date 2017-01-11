package com.lieve.online.base.controller;

import com.lieve.online.base.common.BaseAction;
import com.lieve.online.base.entity.SysFunc;
import com.lieve.online.base.service.SysFuncService;
import com.lieve.online.common.TreeUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Properties;

/**
 * Created by DD240 on 2016/2/26.
 */
public class SysFuncController extends BaseAction<SysFunc,SysFuncService> {

    @Autowired
    public void setService( SysFuncService service) {
        super.setService(service);
    }

    public SysFuncController() {
        Properties funcIdAttributes=new Properties();
        this.setFuncIdAttributes(funcIdAttributes);
    }


    private TreeUtil treeUtil=new TreeUtil();

    //根据用户的权利得到菜单列表
/*    public List getDwrCurrentUserMenuList()
    {
        UserInfo userInfo= null ; // CheckLogin.checkLoginUser();

        return this.getUserMenuList(userInfo);

    }*/

/*    public List getUserMenuList(UserInfo userInfo)
    {
        List result=null;


        if(userInfo!=null)
        {
            //生成树
            int topNo=1000000;
            result=treeUtil.getTree(this.service.getCurrentUserMenuList(userInfo.getRoleRight()),topNo);
        }

        return result;

    }*/

    public List getFuncTree()
    {

        return treeUtil.getTree((List)this.getAll());
    }

    public List getAll()
    {
        String orderBy=" funcId asc ";

        return this.service.getAll(orderBy);

    }
    public List getManagerFuncTree()
    {
        return treeUtil.getTree((List)this.getAll(),1000000);
    }

    public List getSchoolManagerFuncTree()
    {
        return treeUtil.getTree((List)this.getAll(),2000000);
    }
}
