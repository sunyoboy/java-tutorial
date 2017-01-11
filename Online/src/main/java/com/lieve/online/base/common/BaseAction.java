package com.lieve.online.base.common;

import com.lieve.online.base.entity.UserInfo;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * 〈Action类的基类，Web调用的接口〉<br>
 * 〈实现Web操作的基础、公共的功能〉
 *
 * @author sunlj
 */
public abstract class BaseAction<T extends BaseBean, S extends IBaseService>
        extends ServiceAction<S> {

    protected Logger logger = Logger.getLogger(this.getClass());

    protected UserInfo checkLogin() {
        return new UserInfo();
        // return CheckLogin.checkLoginUser();
    }

    protected void formate(T entity, UserInfo userinfo) {

    }

    /**
     * 功能描述: 新增对象<br>
     * 〈功能详细描述〉
     *
     * @param entity
     * @see com.lieve.online.base.common.BaseServiceImpl#create(BaseBean)
     * @since 1.0
     */
    public void create(T entity) {
        // 判断用户是否处于登录状态
        UserInfo userinfo = this.checkLogin();
        try {
            this.formate(entity, userinfo);
            this.getService().create(entity);

        } catch (Exception e) {
            Error.write(logger, "baseaction.create.error", e);
        }
    }

    /**
     * 功能：修改
     *

     * @param entity
     * @author zhenjw
     */
    public void update(T entity) {
        // 判断用户是否处于登录状态
        UserInfo userinfo = this.checkLogin();

        try {

            this.formate(entity, userinfo);

            this.getService().update(entity);

        } catch (Exception e) {
            Error.write(logger, "baseaction.update.error", e);
        }
    }

    /**
     * 功能：批量删除
     *
     * @param no
     * @author zhenjw
     */
    public void batchDelete(int[] no) {
        // 判断用户是否处于登录状态
        this.checkLogin();

        try {

            this.getService().batchDelete(no);
        } catch (Exception e) {
            Error.write(logger, "baseaction.batchdelete.error", e);
        }

    }

    public boolean checkName(String name, int no) {
        // 判断用户是否处于登录状态
        this.checkLogin();

        boolean result = false;

        try {

            result = this.getService().checkName(name, no);

        } catch (Exception e) {
            Error.write(logger, "baseaction.checkName.error", e);
        }

        return result;
    }

    /**
     * 功能描述: 根据对象id查询对象<br>
     *
     * @param id 对象的主键
     * @return T 对象
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public T getById(int id) {
        this.checkLogin();

        T result = null;

        try {

            result = (T) this.getService().getById(id);

        } catch (Exception e) {
            Error.write(logger, "baseaction.getById.error", e);
        }

        return result;
    }

    public QueryResult query(int pageNo, int pageSize, T entity) {

        return this.query(pageNo, pageSize, entity, 0, 0);
    }

    public QueryResult query(int pageNo, int pageSize, T entity,
                             int orderFieldId, int orderMthodFlag) {
        QueryResult result = new QueryResult();

        // 判断用户是否处于登录状态
        this.checkLogin();

        try {

            result = this.getService().query(pageNo, pageSize, entity,
                    orderFieldId, orderMthodFlag);

        } catch (Exception e) {
            Error.write(logger, "baseaction.query.error", e);
        }

        return result;
    }

    /**
     * 功能描述: <br>
     * 门户查询不判断用户是否处于登录状态
     *
     * @param pageNo
     * @param pageSize
     * @param entity
     * @param orderFieldId
     * @param orderMthodFlag
     */
    public QueryResult portalQuery(int pageNo, int pageSize, T entity,
                                   int orderFieldId, int orderMthodFlag) {
        QueryResult result = new QueryResult();
        try {
            result = this.getService().query(pageNo, pageSize, entity,
                    orderFieldId, orderMthodFlag);
        } catch (Exception e) {
            Error.write(logger, "baseaction.portalQuery.error", e);
        }

        return result;
    }

    public List<T> getAll() {
        return this.queryAll(0, 1);
    }

    @SuppressWarnings("unchecked")
    public List<T> queryAll(int orderFieldFlag, int directFlag) {

        // 判断用户是否处于登录状态
        this.checkLogin();

        List<T> result = null;

        try {

            result = this.query(0, 0, null, orderFieldFlag, directFlag)
                    .getList();

        } catch (Exception e) {
            Error.write(logger, "baseaction.getAll.error", e);
        }

        return result;

    }

    /**
     * 功能描述:根据对象的属性查询 <br>
     * 根据对象的其他属性查询对象
     *
     * @param clazz         对象类型
     * @param property      属性名
     * @param propertyValue 属性值
     * @param orderProperty 排序字段
     * @return 返回List
     * @see com.lieve.online.base.common.BaseServiceImpl#queryByProperty(BaseBean, String,
     * long, String)
     * @since 1.0
     */
    @SuppressWarnings("unchecked")
    public List<T> queryByProperty(T clazz, String property,
                                   long propertyValue, String orderProperty) {
        return this.service.queryByProperty(clazz, property, propertyValue,
                orderProperty);
    }

    /**
     * 功能描述:根据对象的属性查询 <br>
     * 根据对象的其他属性查询对象
     *
     * @param clazz         对象类型
     * @param property      属性名
     * @param propertyValue 属性值
     * @param orderProperty 排序字段
     * @return 返回List
     * @see com.lieve.online.base.common.BaseServiceImpl#queryByProperty(BaseBean, String,
     * String, String)
     * @since 1.0
     */
    @SuppressWarnings("unchecked")
    public List<T> queryByProperty(T clazz, String property,
                                   String propertyValue, String orderProperty) {
        return this.service.queryByProperty(clazz, property, propertyValue,
                orderProperty);
    }

}
