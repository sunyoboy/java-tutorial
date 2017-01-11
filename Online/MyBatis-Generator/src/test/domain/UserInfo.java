package test.domain;

import java.util.Date;

public class UserInfo {
    private Integer userno;

    private String loginname;

    private String password;

    private String sessionkey;

    private Integer type;

    private Integer roleno;

    private Integer groupno;

    private Integer userstatus;

    private String realname;

    private Integer sex;

    private String mobilephone;

    private String phoneactivecode;

    private Integer phoneactivestatus;

    private String email;

    private String emailactivecode;

    private Integer emailactivestatus;

    private String qq;

    private String wechat;

    private String sysskin;

    private String photo;

    private String comments;

    private Date createtime;

    private Date lastmodified;

    private Date lastlogin;

    private String lastip;

    private String lastregion;

    private Date lastlogout;

    private Integer sumonline;

    public Integer getUserno() {
        return userno;
    }

    public void setUserno(Integer userno) {
        this.userno = userno;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname == null ? null : loginname.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSessionkey() {
        return sessionkey;
    }

    public void setSessionkey(String sessionkey) {
        this.sessionkey = sessionkey == null ? null : sessionkey.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getRoleno() {
        return roleno;
    }

    public void setRoleno(Integer roleno) {
        this.roleno = roleno;
    }

    public Integer getGroupno() {
        return groupno;
    }

    public void setGroupno(Integer groupno) {
        this.groupno = groupno;
    }

    public Integer getUserstatus() {
        return userstatus;
    }

    public void setUserstatus(Integer userstatus) {
        this.userstatus = userstatus;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone == null ? null : mobilephone.trim();
    }

    public String getPhoneactivecode() {
        return phoneactivecode;
    }

    public void setPhoneactivecode(String phoneactivecode) {
        this.phoneactivecode = phoneactivecode == null ? null : phoneactivecode.trim();
    }

    public Integer getPhoneactivestatus() {
        return phoneactivestatus;
    }

    public void setPhoneactivestatus(Integer phoneactivestatus) {
        this.phoneactivestatus = phoneactivestatus;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getEmailactivecode() {
        return emailactivecode;
    }

    public void setEmailactivecode(String emailactivecode) {
        this.emailactivecode = emailactivecode == null ? null : emailactivecode.trim();
    }

    public Integer getEmailactivestatus() {
        return emailactivestatus;
    }

    public void setEmailactivestatus(Integer emailactivestatus) {
        this.emailactivestatus = emailactivestatus;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat == null ? null : wechat.trim();
    }

    public String getSysskin() {
        return sysskin;
    }

    public void setSysskin(String sysskin) {
        this.sysskin = sysskin == null ? null : sysskin.trim();
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo == null ? null : photo.trim();
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments == null ? null : comments.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getLastmodified() {
        return lastmodified;
    }

    public void setLastmodified(Date lastmodified) {
        this.lastmodified = lastmodified;
    }

    public Date getLastlogin() {
        return lastlogin;
    }

    public void setLastlogin(Date lastlogin) {
        this.lastlogin = lastlogin;
    }

    public String getLastip() {
        return lastip;
    }

    public void setLastip(String lastip) {
        this.lastip = lastip == null ? null : lastip.trim();
    }

    public String getLastregion() {
        return lastregion;
    }

    public void setLastregion(String lastregion) {
        this.lastregion = lastregion == null ? null : lastregion.trim();
    }

    public Date getLastlogout() {
        return lastlogout;
    }

    public void setLastlogout(Date lastlogout) {
        this.lastlogout = lastlogout;
    }

    public Integer getSumonline() {
        return sumonline;
    }

    public void setSumonline(Integer sumonline) {
        this.sumonline = sumonline;
    }
}