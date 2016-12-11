/*
 * Copyright (C), 2002-2015, 北京世纪鼎点软件有限公司
 * FileName: InetAddressTest.java
 * Author:   DD240
 * Date:     2015-12-9 下午7:30:47
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author> sunlj     <time>      <version>    <desc>
 * 修改人姓名             修改日期            版本号                  描述
 */
package com.usual.http;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.junit.Test;

/**
 * 〈一句话功能简述〉<br> 
 * 〈功能详细描述〉
 *
 * @author sunlj
 * @see 
 * @since 1.0
 */
public class InetAddressTest {
    
    @Test
    public void testInetAddress() {
        try {
            System.out.println(" address " + InetAddress.getLocalHost());
            System.out.println(InetAddress.getLoopbackAddress());
            
            InetAddress[] addresses = InetAddress.getAllByName("www.baidu.com");
            for(int i=0; i<addresses.length; i++) {
                System.out.println("address\t" + i + "\t" + addresses[i]);
            }
            
            System.out.println(InetAddress.getByAddress(new byte[] {127,0,0,1}));
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
