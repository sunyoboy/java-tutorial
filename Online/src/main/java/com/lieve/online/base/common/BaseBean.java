package com.lieve.online.base.common;


import com.lieve.online.base.entity.UserInfo;
import org.apache.commons.lang.builder.ToStringBuilder;

public class BaseBean {

    public static final int dbOperateFlag_Read = 1;

    public static final int dbOperateFlag_Write = 2;

    public static final String totalNumPropertyName = "totalNum";

    private int dbOperateFlag;

    // 查询时的辅助查询项
    private UserInfo curUser;

    private int totalNum;

    public BaseBean(int dbOperateFlag) {
        super();
        this.dbOperateFlag = dbOperateFlag;
    }

    public BaseBean() {
        super();
    }

    public int getDbOperateFlag() {
        return dbOperateFlag;
    }

    public void setDbOperateFlag(int dbOperateFlag) {
        this.dbOperateFlag = dbOperateFlag;
    }

    public String toString() {

        return ToStringBuilder.reflectionToString(this);

    }

    /**
     * 功能描述: <br>
     * 获取进行查询用户
     *
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public UserInfo getCurUser() {
        return curUser;
    }

    public void setCurUser(UserInfo curUser) {
        this.curUser = curUser;
    }

    /**
     * 功能描述: <br>
     * 获取总的记录数
     *
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

}
