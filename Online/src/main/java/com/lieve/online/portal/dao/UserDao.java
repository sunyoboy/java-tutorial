package com.lieve.online.portal.dao;

import com.lieve.online.portal.entity.Article;
import com.lieve.online.portal.entity.User;

import java.util.List;

/**
 * Created by DD240 on 2016/11/24.
 */
public interface UserDao {

    public void insertUser(User user);

    public void deleteUser(int id);

    public void updateUser(User user);

    public User selectUser(int id);

    public List<User> selectUsers(String username);

    public List<Article> getUserArticles(int id);
}
