package com.lieve.base.service;

import com.lieve.base.model.Customer;

import java.util.List;
import java.util.Map;

/**
 * 提供客户数据服务
 * Created by IntelliJ IDEA.
 * User: sunyoboy@gmail.com
 * Date: 3/17/17
 * Time: 8:59 AM
 */
public class CustomerService {

    /**
     * 获取客户列表
     * @param keyword
     * @return
     */
    public List<Customer> getCustomerList(String keyword) {
        // TODO
        return null;
    }

    /**
     * 获取客户
     * @param id
     * @return
     */
    public Customer getCustomer(long id) {
        // TODO
        return null;
    }

    /**
     * 创建客户
     * @param fieldMap
     * @return
     */
    public boolean createCustomer(Map<String, Object> fieldMap) {
        // TODO
        return false;
    }

    /**
     * 更新客户
     * @param id
     * @param fieldMap
     * @return
     */
    public boolean updateCustomer(long id, Map<String, Object> fieldMap) {
        // TODO
        return false;
    }

    /**
     * 删除客户
     * @param id
     * @return
     */
    public boolean deleteCustomer(long id) {
        // TODO
        return false;
    }

}
