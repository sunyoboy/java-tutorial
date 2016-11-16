/*
 * Copyright (C), 2002-2015, 北京世纪鼎点软件有限公司
 * FileName: ServerSocketTest.java
 * Author:   DD240
 * Date:     2015-12-9 下午7:48:19
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author> sunlj     <time>      <version>    <desc>
 * 修改人姓名             修改日期            版本号                  描述
 */
package com.usual.http;

import java.io.IOException;
import java.net.ServerSocket;

import org.junit.Test;

/**
 * 〈一句话功能简述〉<br> 
 * 〈功能详细描述〉
 *
 * @author sunlj
 * @see 
 * @since 1.0
 */
public class ServerSocketTest {
    
    @Test
    public void testServerSocket() {
        try {
            ServerSocket serverSocket = new ServerSocket(8089);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
