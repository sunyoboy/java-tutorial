/*
 * Copyright (C), 2002-2015, 北京世纪鼎点软件有限公司
 * FileName: ConnectDB.java
 * Author: DD240
 * Date: 2015-11-5 下午12:47:35
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author> sunlj <time> <version> <desc>
 * 修改人姓名 修改日期 版本号 描述
 */
package com.usual.jdbc;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 * 
 * @author sunlj
 * @see
 * @since 1.0
 */

/**
 * @author ：陶伟基 ，微博：http://weibo.com/taoandtao
 * @date ：2012/12/11
 * @place：广州大学华软软件学院
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectDB {
    public static void main(String[] args) throws Exception {
        Connection conn = null;
        String sql = "";
        // MySQL的JDBC URL编写方式：jdbc:mysql://主机名称：连接端口/数据库的名称?参数=值
        // 避免中文乱码要指定useUnicode和characterEncoding
        // 执行数据库操作之前要在数据库管理系统上创建一个数据库，名字自己定，
        // 下面语句之前就要先创建javademo数据库
        String url = "jdbc:mysql://localhost:3306/region?"
                + "user=school&password=school&useUnicode=true&characterEncoding=UTF8";

        try {
            // 之所以要使用下面这条语句，是因为要使用MySQL的驱动，所以我们要把它驱动起来，
            // 可以通过Class.forName把它加载进去，也可以通过初始化来驱动起来，下面三种形式都可以
            Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
            // or:
            // com.mysql.jdbc.Driver driver = new com.mysql.jdbc.Driver();
            // or：
            // new com.mysql.jdbc.Driver();

            // 一个Connection代表一个数据库连接
            conn = DriverManager.getConnection(url);
            // Statement里面带有很多方法，比如executeUpdate可以实现插入，更新和删除等
            Statement stmt = conn.createStatement();
            // sql =
            // "create table student(NO char(20),name varchar(20),primary key(NO))";
            // int result = stmt.executeUpdate(sql);//
            // executeUpdate语句会返回一个受影响的行数，如果返回-1就没有成功
            /*
             * if (result != -1) {
             * // System.out.println("创建数据表成功");
             * // sql = "insert into student(NO,name) values('2012001','陶伟基')";
             * // result = stmt.executeUpdate(sql);
             * // sql = "insert into student(NO,name) values('2012002','周小俊')";
             * // result = stmt.executeUpdate(sql);
             * // sql = "select * from student";
             * ResultSet rs = stmt.executeQuery(sql);//
             * executeQuery会返回结果的集合，否则返回空值
             * System.out.println("学号\t姓名");
             * while (rs.next()) {
             * System.out
             * .println(rs.getString(1) + "\t" + rs.getString(2));//
             * 入如果返回的是int类型可以用getInt()
             * }
             * }
             */

            int[] array = new int[33];
            sql = "select * from t_school where regionName != '';";
            ResultSet rs = stmt.executeQuery(sql);//
            int index = 0;
            while (rs.next()) {
                array[index] = rs.getInt(1) - 1 ;
                index++;
            }
            for (int j = 0; j < array.length; j++) {
                System.out.print(" " + array[j]);
            }
            System.out.println();

/*            { 
                "regionName" : "北京",
                "school" : [ {
                    "id" : 1,
                    "name" : "北京市崇文小学"
                }]
                
            }*/
            sql = "select * from t_school";
            rs = stmt.executeQuery(sql);//
            
            int max=0, min=0;
            int calc = 0;
            int count = 0;
            int tagIndex = 0;
            boolean flag = false;
            
            
            StringBuffer sb = new StringBuffer(655351);
            sb.append(" { \"schools\":["); 
            while (rs.next()) {
                calc++;
                if(!rs.getString(2).equals("")) {
                    if(count < 31) {
                        count++;
                    }

                    
                    if(flag) { 
                        sb.append(",{\"regionName\":\"").append(rs.getString(2)).append("\",");
                        sb.append("\"school\":[");
                    } else {
                        sb.append("{\"regionName\":\"").append(rs.getString(2)).append("\",");
                        sb.append("\"school\":[");
                        flag = true;
                    }
                    
                    sb.append("{").append("\"id\":").append(rs.getInt(1)).append(",")
                    .append("\"name\":\"").append(rs.getString(3)).append("\"").append("},"); 

                }   else {
                    

                    sb.append("{").append("\"id\":").append(rs.getInt(1)).append(",")
                    .append("\"name\":\"").append(rs.getString(3)).append("\"").append("},"); 
                    
                    if(rs.getInt(1) == array[count]) {

                        // System.out.println(rs.getInt(1));
                        sb.setCharAt(sb.lastIndexOf(","), ' ');
                        sb.append("]}");
                    }
                    
                    if(rs.getInt(1) == 8627) {
                        sb.setCharAt(sb.lastIndexOf(","), ' ');
                        sb.append("]}");
                    }
                }  

                 
/*                if(!rs.getString(2).equals("") && flag ) {
                    sb.append("]");
                }

                
                if(!rs.getString(2).equals("")) {
                    if(flag) { 
                        sb.setCharAt(sb.lastIndexOf(","), ' ');
                        sb.append("]");

                        sb.append("\",regionName\":\"").append(rs.getString(2)).append("\",");
                        sb.append("\"school\":[");
                    } else {
                        sb.append("\"regionName\":\"").append(rs.getString(2)).append("\",");
                        sb.append("\"school\":[");
                    }

                }   else {
                    sb.append("{").append("\"id\":").append(rs.getInt(1)).append(",")
                    .append("\"name\":\"").append(rs.getString(3)).append("\"").append("},");                    
                }*/


            }
            sb.append(" ]} ");
            
             System.out.println(sb.toString());

        } catch (SQLException e) {
            System.out.println("MySQL操作错误");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }

    }
    
    public static String generateJsonFile() {
        return null;
    }

}