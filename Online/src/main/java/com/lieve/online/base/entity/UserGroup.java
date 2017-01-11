package com.lieve.online.base.entity;

import com.lieve.online.base.common.BaseBean ;

import org.springframework.stereotype.Component;

@Component
public class UserGroup extends BaseBean  implements java.io.Serializable {

	private int groupNo;

	private String groupName;

	private int parentNo;

	private String comments;


	public void setGroupNo(int groupNo) {
		this.groupNo = groupNo;
	}

	public int getGroupNo() {
		return this.groupNo;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupName() {
		return this.groupName;
	}

	public void setParentNo(int parentNo) {
		this.parentNo = parentNo;
	}

	public int getParentNo() {
		return this.parentNo;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getComments() {
		return this.comments;
	}


}