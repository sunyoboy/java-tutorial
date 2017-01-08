package com.lieve.online;

import com.lieve.online.shiro.dao.UserDao;
import com.lieve.online.shiro.entity.Article;
import com.lieve.online.shiro.entity.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * Created by DD240 on 2016/11/24.
 */
public class UserTest {

    private static SqlSessionFactory sqlSessionFactory;

    static {
        String resource = "mybatis-config.xml";
        InputStream is = UserTest.class.getClassLoader().getResourceAsStream(resource);

        sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        // sqlSessionFactory.getConfiguration().addMapper(UserDao.class);
    }
/*    @Test
    public void test() {

        SqlSession session = sqlSessionFactory.openSession();
        String statement = "com.lieve.online.shiro.mapper.User.getUser";
        User user = session.selectOne(statement, "1");
        System.out.println(user.toString());

        String insertUser = "com.lieve.online.shiro.mapper.User.getUser";
        User newUser = new User();
        newUser.setAge(23);
        newUser.setUsername("hello");
        session.insert(insertUser, newUser);

        String delete = "com.lieve.online.shiro.mapper.User.deleteUser";
        session.delete(statement, "1");
        session.commit();

        String statement2 = "com.lieve.online.shiro.mapper.User.getUser";
        User user2 = session.selectOne(statement, "1");
        System.out.println(user2.toString());
    }*/

    @Test
    public void insertUser() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            UserDao userDao = sqlSession.getMapper(UserDao.class);
            User newUser = new User();
            newUser.setAge(23);
            newUser.setUsername("hello");
            userDao.insertUser(newUser);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void deleteUser() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            UserDao userDao = sqlSession.getMapper(UserDao.class);
            userDao.deleteUser(1);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void updateUser() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            UserDao userDao = sqlSession.getMapper(UserDao.class);
            User user = userDao.selectUser(2);
            user.setAge(40);
            userDao.updateUser(user);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void selectUser() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            UserDao userDao = sqlSession.getMapper(UserDao.class);
            User user = userDao.selectUser(3);
            System.out.println(user.toString());
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void selectUsers() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            UserDao userDao = sqlSession.getMapper(UserDao.class);
            List<User> userList = userDao.selectUsers("hello");
            for(User user : userList) {
                System.out.println("user " + user.toString());
            }
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void getUserArticles() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            UserDao userDao = sqlSession.getMapper(UserDao.class);
            List<Article> articleList =  userDao.getUserArticles(2);
            for(Article article : articleList) {
                System.out.println("article " + article.getContent() + " " + article.getUser().getUsername());
            }
        } finally {
            sqlSession.close();
        }
    }
}
