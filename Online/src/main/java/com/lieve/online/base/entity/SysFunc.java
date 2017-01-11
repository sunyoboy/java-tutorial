package com.lieve.online.base.entity;

import com.lieve.online.common.BaseTreeNode;
import org.springframework.stereotype.Component;

@Component
public class SysFunc extends BaseTreeNode implements java.io.Serializable {

	private int funcID;

	private int parentID;

	private int funcStatus;

	private String funcVersion;

	private String funcName;

	private int showOrder;

	private int menuFlag;

	private String entranceURL;

	private String comments;


	public void setFuncID(int funcID) {
		this.funcID = funcID;
	}

	public int getFuncID() {
		return this.funcID;
	}

	public void setParentID(int parentID) {
		this.parentID = parentID;
	}

	public int getParentID() {
		return this.parentID;
	}

	public void setFuncStatus(int funcStatus) {
		this.funcStatus = funcStatus;
	}

	public int getFuncStatus() {
		return this.funcStatus;
	}

	public void setFuncVersion(String funcVersion) {
		this.funcVersion = funcVersion;
	}

	public String getFuncVersion() {
		return this.funcVersion;
	}

	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}

	public String getFuncName() {
		return this.funcName;
	}

	public void setShowOrder(int showOrder) {
		this.showOrder = showOrder;
	}

	public int getShowOrder() {
		return this.showOrder;
	}

	public void setMenuFlag(int menuFlag) {
		this.menuFlag = menuFlag;
	}

	public int getMenuFlag() {
		return this.menuFlag;
	}

	public void setEntranceURL(String entranceURL) {
		this.entranceURL = entranceURL;
	}

	public String getEntranceURL() {
		return this.entranceURL;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getComments() {
		return this.comments;
	}

	@Override
	public int getNo() {
		return this.funcID ;
	}

	@Override
	public int getParentNo() {
		return this.parentID ;
	}

	@Override
	public String getName() {
		return this.funcName ;
	}
}