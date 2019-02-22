package com.lieve.base.service;

import com.lieve.base.model.Customer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CustomerService Tester.
 * CustomerService 单元测试
 *
 * @author sunlj
 * @version 1.0
 * @since <pre>Mar 17, 2017</pre>
 */
public class CustomerServiceTest {

    private CustomerService customerService;

    public CustomerServiceTest() {
        customerService = new CustomerService();
    }

    @Before
    public void before() throws Exception {
        // TODO 初始化数据库
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getCustomerList(String keyword)
     */
    @Test
    public void testGetCustomerList() throws Exception {
        String keyword = "customer";
        List<Customer> customerList = customerService.getCustomerList(keyword);
        Assert.assertEquals(2, customerList.size());
    }

    /**
     * Method: getCustomer(long id)
     */
    @Test
    public void testGetCustomer() throws Exception {
        long id = 1;
        Customer customer = customerService.getCustomer(id);
        Assert.assertNotNull(customer);
    }

    /**
     * Method: createCustomer(Map<String, Object> fieldMap)
     */
    @Test
    public void testCreateCustomer() throws Exception {
        Map<String, Object> fieldMap = new HashMap<>();
        fieldMap.put("name", "customer3");
        fieldMap.put("telephone", "12342342353");
        fieldMap.put("contact", "Sunlj");
        boolean result = customerService.createCustomer(fieldMap);
        Assert.assertTrue(result);
    }

    /**
     * Method: updateCustomer(long id, Map<String, Object> fieldMap)
     */
    @Test
    public void testUpdateCustomer() throws Exception {
        long id = 1;
        Map<String, Object> fieldMap = new HashMap<>();
        fieldMap.put("contact", "Eric");
        boolean result = customerService.updateCustomer(id, fieldMap);
        Assert.assertTrue(result);
    }

    /**
     * Method: deleteCustomer(long id)
     */
    @Test
    public void testDeleteCustomer() throws Exception {
        long id = 1;
        boolean result = customerService.deleteCustomer(id);
        Assert.assertTrue(result);
    }


} 
