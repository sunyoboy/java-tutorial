package com.lieve.online.shiro;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.authz.permission.WildcardPermissionResolver;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.session.mgt.ServletContainerSessionManager;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * Created by DD240 on 2016/1/21.
 */
public class ShiroUtil {

    public static void main(String[] args) {

    }

    public static Subject getSubject() {
        DefaultSecurityManager securityManager = new DefaultSecurityManager();

        // 设置 authenticator
        ModularRealmAuthenticator authenticator = new ModularRealmAuthenticator();
        authenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
        securityManager.setAuthenticator(authenticator);

        // 设置authorizer
        ModularRealmAuthorizer authorizer = new ModularRealmAuthorizer();
        authorizer.setPermissionResolver(new WildcardPermissionResolver());
        securityManager.setAuthorizer(authorizer);

        JdbcRealm jdbcRealm = new JdbcRealm();
        jdbcRealm.setDataSource(getDataSource());
        jdbcRealm.setPermissionsLookupEnabled(true);

        //
        securityManager.setRealms(Arrays.asList((Realm) jdbcRealm));

        // 设置会话管理器
        ServletContainerSessionManager sessionManager = new ServletContainerSessionManager();
        securityManager.setSessionManager(sessionManager);

        // 将SecurityManager设置到SecurityUtil方便全局使用
        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();
        /*
        UsernamePasswordToken token = new UsernamePasswordToken("zhang","123");
        subject.login(token);
        Assert.assertTrue(subject.isAuthenticated());
        System.out.println("subject.isAuthenticated() : " + subject.isAuthenticated());
        */
        return subject;
    }

    public static DataSource getDataSource() {
        // 设置Realm
        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost/region");
        ds.setUsername("school");
        ds.setPassword("school");
        return ds;
    }

    public static Connection getConnection() throws SQLException {
        return getDataSource().getConnection();
    }
}
