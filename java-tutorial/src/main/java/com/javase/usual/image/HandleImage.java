/*
 * Copyright (C), 2002-2015, 北京世纪鼎点软件有限公司
 * FileName: HandleImage.java
 * Author:   DD240
 * Date:     2015-11-9 上午10:59:36
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author> sunlj     <time>      <version>    <desc>
 * 修改人姓名             修改日期            版本号                  描述
 */
package com.usual.image;

/**
 * 〈一句话功能简述〉<br> 
 * 〈功能详细描述〉
 *
 * @author sunlj
 * @see 
 * @since 1.0
 */
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.im4java.core.ConvertCmd;
import org.im4java.core.IMOperation;
  
public class HandleImage {  
  
    public static void main(String[] args) {
        File srcFolder = new File("D:/tmp/thumbnail");
        File destFolder = new File("D:/tmp/destImage/");
        
        if(!destFolder.exists()) {
            destFolder.mkdirs();
        }
        File[] images = null;
        if(srcFolder.isDirectory()) {
            images = srcFolder.listFiles();
        }
        
        System.out.println(images[0].getName());
        System.out.println(images[0].getAbsolutePath());
        System.out.println(destFolder.getAbsolutePath());
        try {  
            BufferedImage image = null;
            File f = null;
            for(int i=0; i<images.length; i++) {
                
                f = new File(images[i].getAbsolutePath());
                image = ImageIO.read(f);
                System.out.println("size : " + f.length()/1024.0);
                // zoomImage(293,205, images[i].getAbsolutePath(), destFolder.getAbsolutePath() + "\\img" + (i+1) + ".jpg");
                // zoomImage(image.getWidth()/8,image.getHeight()/8, images[i].getAbsolutePath(),destFolder.getAbsolutePath() + "\\" + images[i].getName());
            }
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
  
    public static void zoomImage(Integer width, Integer height, String srcPath, String newPath) throws Exception {      
        IMOperation op = new IMOperation();      
        op.addImage(srcPath);  
        if(width == null){//根据高度缩放图片    
            op.resize(null, height);        
        }else if(height == null){//根据宽度缩放图片    
            op.resize(width, null);    
        }else {    
            op.resize(width, height);    
        }  
        op.addImage(newPath);  
        //这里不加参数或者参数为false是使用ImageMagick，true是使用GraphicsMagick  
        ConvertCmd convert = new ConvertCmd(true);  
//        convert.setSearchPath("g:/tool/graphicsmagick-1.3.20-q16");  
        convert.setSearchPath("D:/Program Files/GraphicsMagick-1.3.22-Q8");  
        convert.run(op);      
    }  
}  