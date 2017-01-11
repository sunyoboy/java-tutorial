package com.lieve.online.base.entity;

import com.lieve.online.base.common.BaseBean ;

import org.springframework.stereotype.Component;

@Component
public class ParamType extends BaseBean  implements java.io.Serializable {

	private int typeID;

	private String typeName;

	private String comments;


	public void setTypeID(int typeID) {
		this.typeID = typeID;
	}

	public int getTypeID() {
		return this.typeID;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getTypeName() {
		return this.typeName;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getComments() {
		return this.comments;
	}


}