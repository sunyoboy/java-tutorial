-- MySQL注释分为两类:单行注释、多行注释。
-- 单行注释有两种,格式如下:
-- 1、#... 以“#”号开头,直到该行行尾,全部都是注释内容;
-- 2、--  以“-- ”号开头,直到该行行尾,全部都是注释内容;注意:“-- ”号与注释内容之间要加有空格，否则会报错；

-- 多行注释格式如下:
-- 3、/*...*/ 该符号中间的内容全部为注释。

-- CHARACTER SET子句用于指定默认的数据库字符集。COLLATE子句用于指定默认的数据库整序。

CREATE DATABASE base DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

-- 建表customer
CREATE TABLE customer (
  id BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) DEFAULT NULL,
  contact VARCHAR(255) DEFAULT NULL,
  telephone VARCHAR(255) DEFAULT NULL,
  email VARCHAR(255) DEFAULT NULL,
  mark TEXT
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 插入数据
INSERT INTO customer VALUES (1, 'customer1', 'Jack', '13456353234', 'jack@gmail.com', null);
INSERT INTO customer VALUES (2, 'customer2', 'Tom', '16456353234', 'tom@gmail.com', null);
