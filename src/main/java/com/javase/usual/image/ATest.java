/*
 * Copyright (C), 2002-2015, 北京世纪鼎点软件有限公司
 * FileName: ATest.java
 * Author:   DD240
 * Date:     2015-11-9 下午5:25:43
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author> sunlj     <time>      <version>    <desc>
 * 修改人姓名             修改日期            版本号                  描述
 */
package com.usual.image;

import java.util.ArrayList;
import java.util.Collection;

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
public class ATest {
    
    @Test
    public void TestCollection() {
        Collection c = new ArrayList();
        Assert.assertNotNull(c);
        Assert.assertNull(c);
        
    }
}
