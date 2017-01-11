package com.lieve.online.base.entity;

import com.lieve.online.base.common.BaseBean ;

import org.springframework.stereotype.Component;

@Component
public class SysRole extends BaseBean  implements java.io.Serializable {

	public static int DefaultFlag_No=0;

	public static int DefaultFlag_Yes=1;

	private int roleNo;

	private String roleName;

	private String roleRight;

	private int defaultFlag;

	private String comments;

	private int createUser;

	private String createTime;


	public void setRoleNo(int roleNo) {
		this.roleNo = roleNo;
	}

	public int getRoleNo() {
		return this.roleNo;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleRight(String roleRight) {
		this.roleRight = roleRight;
	}

	public String getRoleRight() {
		return this.roleRight;
	}

	public void setDefaultFlag(int defaultFlag) {
		this.defaultFlag = defaultFlag;
	}

	public int getDefaultFlag() {
		return this.defaultFlag;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getComments() {
		return this.comments;
	}

	public void setCreateUser(int createUser) {
		this.createUser = createUser;
	}

	public int getCreateUser() {
		return this.createUser;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreateTime() {
		return this.createTime;
	}


}