package com.tag.jstl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 演示JSTL核心库
 * @author 巨亚红
 * @date 2014-1-7 下午9:19:15
 * @版本 V1.0   作者： 时间：  修改：
 */
@WebServlet(name="JstlCoreServlet", urlPatterns={"/JstlCoreServlet"})
public class JstlCoreServlet extends HttpServlet {

    /**
     * @author 巨亚红
     * @date 2014-1-8 下午5:36:05
     * @版本 V1.0   作者： 时间：  修改：
     */
    private static final long serialVersionUID = 1L;
    /**
     * Constructor of the object.
     */
    public JstlCoreServlet() {
        super();
    }

    

    /**
     * The doGet method of the servlet. <br>
     *
     * This method is called when a form has its tag value method equals to get.
     * 
     * @param request the request send by the client to the server
     * @param response the response send by the server to the client
     * @throws ServletException if an error occurred
     * @throws IOException if an error occurred
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 普通字符串
        request.setAttribute("hello", "hello world");
        
        //HTML字符串
        request.setAttribute("welcome", "<font color='red'>欢迎你来到这个世界</font>");
    
        //条件控制标签
        request.setAttribute("v1", 10);
        request.setAttribute("v2", 20);
        
        request.setAttribute("userList", new ArrayList());
    
        //结构
            
        
        Group group = new Group();
        group.setName("提高班第八期");
                
        List users = new ArrayList();
        for (int i=0; i<10; i++) {
                User user = new User();
                user.setName("juyahong(" + i+")");
                user.setAge(23 + i);
                user.setGroup(group);
                users.add(user);
        }
        request.setAttribute("users", users);
        
        
        //map
        Map map=new HashMap();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        map.put("key4", "value4");
        request.setAttribute("map", map);
        
        
        //forTokens
        request.setAttribute("strTokens", "1#2#3#4#5");
        
        request.getRequestDispatcher("jstl/jstl_core_02.jsp").forward(request, response);
        
    }

    
    
}
