package com.lieve.online.base.common;

import com.lieve.online.base.entity.UserInfo;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;

import java.util.Properties;

/**
 * @author zhenjw
 * @createDate 2009-12-7 下午12:57:44
 * @since 1.0
 * @version 1.0
 */
public class ActionSaveLogAdvice implements MethodInterceptor {

    Logger logger = Logger.getLogger(this.getClass());

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.aopalliance.intercept.MethodInterceptor#invoke(org.aopalliance.intercept
     * .MethodInvocation)
     */
    public Object invoke(MethodInvocation invocation) throws Throwable {
        // TODO Auto-generated method stub

        Object obj = null;

        boolean successFlag = true;
        Object[] arg = null;
        // 得到方法名
        String methodName = null;
        UserInfo userInfo = null;
        try {
            // 得到方法名
            methodName = (String) invocation.getMethod().getName();

            logger.debug("methodName=" + methodName);

            arg = invocation.getArguments();

            // 得到当前用户
            // userInfo = CheckLogin.getCurrentLoginUser();

        } catch (Exception e) {
            this.logger.debug(" preHandle error ", e);
        }

        // 执行目标方法
        try {

            obj = invocation.proceed(); // 调用目标方法，如不调用，目标方法将不被执行

        } catch (Exception e) {

            successFlag = false;

            logger.debug(e.getMessage(), e);

            throw e;

        } finally {

            this.writeLog(invocation, successFlag, methodName, arg, userInfo);

        }

        return obj;

    }

    private RuntimeException userisNull = new RuntimeException("没有得到用户信息");

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     * 
     * @param invocation
     *            触发的方法
     * @param successFlag
     *            成功失败标志
     * @param methodName
     *            方法名
     * @param arg
     * @param userInfo
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    private void writeLog(MethodInvocation invocation, boolean successFlag,
            String methodName, Object[] arg, UserInfo userInfo) {

        try {
            // 得到类名
            String className = invocation.getThis().getClass().getName();

            logger.debug("className=" + className);

            // 保存参数
            StringBuilder args = new StringBuilder(successFlag ? " 执行成功"
                    : " 执行失败").append(",参数：{");

            // 方法的参数
            for (int i = 0, j = arg == null ? 0 : arg.length; i < j; i++) {

                logger.debug("  arg[" + i + "] type = "
                        + arg[i].getClass().getName());

                args.append(i > 0 ? "],[" : "[").append(arg[i].toString());

            }

            args.append(args.length() > 1 ? "]" : "").append("}");

            logger.debug("args is :" + args.toString());

            if (userInfo == null) {

                logger.debug("userInfo is null 没有得到用户信息");

                // throw userisNull;

            } else {
                // 得到类，所有的action类都是继承 CommonAction

                ILogAction action = (ILogAction) invocation.getThis();

                Properties FunProperties = action.getFuncIdAttributes();

                if (FunProperties == null) {
                    logger.debug("没有配置日志参数，不用写入日志记录中");
                } else {
                    // 写入日志
                    if (userInfo != null
                            && FunProperties.containsKey(methodName)) {
                        // 需要写入日志
                        int funcid = Util.StringToInt(
                                FunProperties.getProperty(methodName), -1);// ()

                        logger.debug("funcid=" + funcid);

                        // 得到对象ID
                        int objectId = Util.StringToInt(
                                FunProperties.getProperty("objectid"), -1);

                        logger.debug("funcid=" + objectId);

                        int operateid = Util.StringToInt(
                                FunProperties.getProperty(methodName
                                        + "_operateid"), -1);//

                        logger.debug("operateid=" + operateid);

                        if (funcid > 0 && objectId > 0 && operateid > 0) {
/*                            AdminLog adminlog = new AdminLog(
                                    userInfo.getUserNo(),
                                    userInfo.getLoginName(),
                                    userInfo.getRealName(),
                                    userInfo.getUnitNo(), userInfo.getRoleNo(),
                                    funcid, operateid, objectId, 1, 1,
                                    new Date(), className + "." + methodName
                                            + args.toString());

                            AdminLogDao adminLogDao = (AdminLogDao) ServiceBean
                                    .getBean("adminLogDao");

                            adminLogDao.save(adminlog);*/

                        } else {
                            // 系统配置参数有误
                            logger.debug("系统配置参数有误");
                        }

                    }
                }

            }
        } catch (Exception e) {
            logger.debug("写入管理日志时出错", e);
            e.printStackTrace();
        }

    }

}
