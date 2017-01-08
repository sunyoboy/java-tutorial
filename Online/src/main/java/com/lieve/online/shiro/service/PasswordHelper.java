package com.lieve.online.shiro.service;

import com.sjdd.base.entity.UserInfo;
import com.lieve.online.shiro.entity.User;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
public class PasswordHelper {

    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

    private String algorithmName = "md5";
    private final int hashIterations = 1;

    public void encryptPassword(User user) {

        // user.setSalt(randomNumberGenerator.nextBytes().toHex());

        String newPassword = new SimpleHash(
                algorithmName,
                user.getPassword(),
                ByteSource.Util.bytes(user.getCredentialsSalt()),
                1).toHex();

        user.setPassword(newPassword);
    }

    public void encryptPassword(UserInfo userInfo) {

        // user.setSalt(randomNumberGenerator.nextBytes().toHex());

        String newPassword = new SimpleHash(
                algorithmName,
                userInfo.getPassword(),
                ByteSource.Util.bytes(""),
                1).toHex();

        userInfo.setPassword(newPassword);
    }
}
