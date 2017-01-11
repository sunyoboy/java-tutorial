package com.lieve.online.base.tag;

import com.lieve.online.base.entity.SysFunc;
import com.lieve.online.base.entity.UserInfo;
import com.lieve.online.base.service.SysFuncService;
import com.lieve.online.base.service.SysRoleService;
import com.lieve.online.common.SpringUtils;
import com.lieve.online.common.TreeUtil;
import com.lieve.online.shiro.Constants;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;

/**
 * Created by DD240 on 2016/2/29.
 */
public class SysMenuTag extends TagSupport {

    @Override
    public int doStartTag() throws JspException {

        //得到当前用户
        UserInfo userInfo= (UserInfo)this.pageContext.getRequest().getAttribute(Constants.CURRENT_USER); // CheckLogin.getCurrentLoginUser(this.pageContext.getSession());
/*        try {
            this.pageContext.getOut().write(userInfo.getLoginName());
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        if(userInfo!=null)
        {
            SysRoleService sysRoleService;
            SysFuncService sysFuncService;
            sysRoleService = SpringUtils.getBean(SysRoleService.class);
            sysFuncService = SpringUtils.getBean(SysFuncService.class);
            StringBuilder navHTML=new StringBuilder();
            StringBuilder navScript=new StringBuilder("var arr ;arr=[];");
            userInfo.setSysRole(sysRoleService.findOne(userInfo.getRoleNo()));
            /*SysFuncAction sysFuncAction=(SysFuncAction)ServiceBean.getBean("sysFuncAction");*/
            TreeUtil treeUtil = new TreeUtil();
            int topNo=1000000;
            List<SysFunc> list = treeUtil.getTree(sysFuncService.getCurrentUserMenuList(userInfo.getRoleRight()),topNo);

            SysFunc sysFunc=null;
            int lastTopId=0;


            for(int i=0,j=list==null?0:list.size();i<j;i++)
            {
                sysFunc=list.get(i);

                if(sysFunc.getLayer()==1)
                {
                    if(lastTopId>0)
                    {
                        navScript.append("$('_Menu_").append(lastTopId).append("').ChildArray=arr;	arr = [];");
                    }

                    lastTopId=sysFunc.getFuncID()/10000;

                    navHTML.append("<li id='_Menu_").append(lastTopId).append("' onclick='Application.onMainMenuClick(this);' onMouseOver='Application.onMainMenuMouseOver(this);' onMouseOut='Application.onMainMenuMouseOut(this);'>").append(sysFunc.getFuncName()).append("</li>");

                }else{
                    String contextPath = this.pageContext.getRequest().getServletContext().getContextPath();
                    navScript.append("arr.push([").append(sysFunc.getFuncID()%100000).append(",'").append(sysFunc.getFuncName()).append("','").append(contextPath + sysFunc.getEntranceURL())
                            //.append("','../Icons/menu/m").append(sysFunc.getFuncId()).append(".gif']);")
                            .append("','']);")
                    ;


                }
            }

            if(lastTopId>0)
            {
                navScript.append("$('_Menu_").append(lastTopId).append("').ChildArray=arr;");
            }

            navHTML.append("<script>");
            navHTML.append(navScript);
            navHTML.append("</script>");
            //输出
            try {

                this.pageContext.getOut().write(navHTML.toString());

            } catch (IOException e) {
                // TODO Auto-generated catch block

                e.printStackTrace();

                throw new JspException(e);

            }
        }

        return this.EVAL_BODY_AGAIN;
    }
}
