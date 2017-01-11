package com.lieve.online.base.entity;

import com.lieve.online.base.common.BaseBean ;

import org.springframework.stereotype.Component;

@Component
public class SysParam extends BaseBean  implements java.io.Serializable {

	private int paramID;

	private String paramName;

	private int typeID;

	private int paramStatus;

	private String paramValue;

	private String comments;


	public void setParamID(int paramID) {
		this.paramID = paramID;
	}

	public int getParamID() {
		return this.paramID;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getParamName() {
		return this.paramName;
	}

	public void setTypeID(int typeID) {
		this.typeID = typeID;
	}

	public int getTypeID() {
		return this.typeID;
	}

	public void setParamStatus(int paramStatus) {
		this.paramStatus = paramStatus;
	}

	public int getParamStatus() {
		return this.paramStatus;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	public String getParamValue() {
		return this.paramValue;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getComments() {
		return this.comments;
	}


}