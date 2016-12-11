/*
 * Copyright (C), 2002-2015, 北京世纪鼎点软件有限公司
 * FileName: EncodeTest.java
 * Author:   DD240
 * Date:     2015-11-10 下午2:26:05
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author> sunlj     <time>      <version>    <desc>
 * 修改人姓名             修改日期            版本号                  描述
 */
package com.javase.junit;

import java.io.File;
import java.io.UnsupportedEncodingException;

import org.junit.Assert;
import org.junit.Test;

/**
 * 〈一句话功能简述〉<br> 
 * 〈功能详细描述〉
 *
 * @author sunlj
 * @see 
 * @since 1.0
 */
public class EncodeTest {

    @Test
    public void testName() {
        File f = new File("E:/tmp/清华附小选拔赛集锦-2M.mp4");
        Assert.assertEquals("清华附小选拔赛集锦-2M.mp4", f.getName());
        try {
            System.out.println(new String(f.getName().getBytes("GBK"), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
}
