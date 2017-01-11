package com.lieve.online.base.entity;

import com.lieve.online.base.common.BaseBean;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class UserInfo extends BaseBean implements java.io.Serializable {

    public static final int TYPE_ADMIN = 10;

    public static final int TYPE_ANCHOR = 20; // 主播

    public static final int TYPE_AUDIENCE = 30; // 观众

    public static final String[] typeArray = new String[] { "", "管理员", "主播", "观众" };

    private String typeName;

    public String getTypeName() {

        if (this.type > 0 && this.type < this.typeArray.length) {
            this.typeName = this.typeArray[this.type];
        } else {
            typeName = "";
        }

        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public static final int SEX_MAN = 1;

    public static final int SEX_WOMAN = 2;

    public final static String[] sexArray = new String[] { "", "男", "女"};

    private String sexName;

    public String getSexName() {
        if (this.sex > 0 && this.sex < this.sexArray.length) {
            this.sexName = this.sexArray[this.sex];
        } else {
            sexName = "";
        }
        return sexName;
    }

    public void setSexName(String sexName) {
        this.sexName = sexName;
    }

    public static final int STATUS_UNACTIVE = 0; // 未开通

    public static final int STAUS_NORMAL = 1; // 正常

    public static final int STATUS_FORBIDDEN = 2; // 禁用

    public static final int STATUS_LOGOUT = 3; // 注销

    private String userStatusName;

    public final static String[] userStatusArray = new String[] {"未开通", "正常", "禁用", "注销"};

    public String getUserStatusName() {
        if (this.userStatus > 0 && this.userStatus < this.userStatusArray.length) {
            this.userStatusName = this.userStatusArray[this.userStatus];
        } else {
            userStatusName = "";
        }
        return userStatusName;
    }

    public void setUserStatusName(String userStatusName) {
        this.userStatusName = userStatusName;
    }

    private Long userNo;

    private String loginName;

    private String password;

    private String sessionKey;

    private int type;

    private int roleNo;

    private int groupNo;

    private int userStatus;

    private String realName;

    private int sex;

    private String mobilePhone;

    private String phoneActiveCode;

    private int phoneActiveStatus;

    private String email;

    private String emailActiveCode;

    private int emailActiveStatus;

    private String qQ;

    private String weChat;

    private String sysSkin;

    private String photo;

    private String comments;

    private Timestamp createTime;

    private Timestamp lastModified;

    private Timestamp lastLogin;

    private String lastIP;

    private String lastRegion;

    private Timestamp lastLogout;

    private int sumOnline;
    /*    private SysRole sysRole;

        private String roleRight;

        public SysRole getSysRole() {
            return sysRole;
        }

        public void setSysRole(SysRole sysRole) {
            if (sysRole != null) {
                this.sysRole = sysRole;
                this.roleNo = sysRole.getRoleNo();
            }
        }

        public String getRoleRight() {
            return this.sysRole.getRoleRight();
        }*/
    private String roleRight;

    public Long getUserNo() {
        return this.userNo;
    }

    public void setUserNo(Long userNo) {
        this.userNo = userNo;
    }

    public String getLoginName() {
        return this.loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSessionKey() {
        return this.sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getRoleNo() {
        return this.roleNo;
    }

    public void setRoleNo(int roleNo) {
        this.roleNo = roleNo;
    }

    public int getGroupNo() {
        return this.groupNo;
    }

    public void setGroupNo(int groupNo) {
        this.groupNo = groupNo;
    }

    public int getUserStatus() {
        return this.userStatus;
    }

    public void setUserStatus(int userStatus) {
        this.userStatus = userStatus;
    }

    public String getRealName() {
        return this.realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public int getSex() {
        return this.sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getMobilePhone() {
        return this.mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getPhoneActiveCode() {
        return this.phoneActiveCode;
    }

    public void setPhoneActiveCode(String phoneActiveCode) {
        this.phoneActiveCode = phoneActiveCode;
    }

    public int getPhoneActiveStatus() {
        return this.phoneActiveStatus;
    }

    public void setPhoneActiveStatus(int phoneActiveStatus) {
        this.phoneActiveStatus = phoneActiveStatus;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailActiveCode() {
        return this.emailActiveCode;
    }

    public void setEmailActiveCode(String emailActiveCode) {
        this.emailActiveCode = emailActiveCode;
    }

    public int getEmailActiveStatus() {
        return this.emailActiveStatus;
    }

    public void setEmailActiveStatus(int emailActiveStatus) {
        this.emailActiveStatus = emailActiveStatus;
    }

    public String getQQ() {
        return this.qQ;
    }

    public void setQQ(String qQ) {
        this.qQ = qQ;
    }

    public String getWeChat() {
        return this.weChat;
    }

    public void setWeChat(String weChat) {
        this.weChat = weChat;
    }

    public String getSysSkin() {
        return this.sysSkin;
    }

    public void setSysSkin(String sysSkin) {
        this.sysSkin = sysSkin;
    }

    public String getPhoto() {
        return this.photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Timestamp getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getLastModified() {
        return this.lastModified;
    }

    public void setLastModified(Timestamp lastModified) {
        this.lastModified = lastModified;
    }

    public Timestamp getLastLogin() {
        return this.lastLogin;
    }

    public void setLastLogin(Timestamp lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getLastIP() {
        return this.lastIP;
    }

    public void setLastIP(String lastIP) {
        this.lastIP = lastIP;
    }

    public String getLastRegion() {
        return this.lastRegion;
    }

    public void setLastRegion(String lastRegion) {
        this.lastRegion = lastRegion;
    }

    public Timestamp getLastLogout() {
        return this.lastLogout;
    }

    public void setLastLogout(Timestamp lastLogout) {
        this.lastLogout = lastLogout;
    }

    public int getSumOnline() {
        return this.sumOnline;
    }

    public void setSumOnline(int sumOnline) {
        this.sumOnline = sumOnline;
    }

    public String getCredentialsSalt() {
        return "";
    }

    public Boolean getLocked() {
        return Boolean.FALSE;
    }

    private SysRole sysRole;

    public SysRole getSysRole() {
        return sysRole;
    }

    public void setSysRole(SysRole sysRole) {
        if (sysRole != null) {
            this.roleNo = sysRole.getRoleNo();
            this.roleRight = sysRole.getRoleRight();
        }
        this.sysRole = sysRole;
    }

    public String getRoleRight() {

        if (this.sysRole != null) {
            roleRight = sysRole.getRoleRight();
        }
        return roleRight;
    }

    public void setRoleRight(String roleRight) {

        this.roleRight = roleRight;
    }

}