package com.lieve.online.base.common;

import org.guzz.exception.GuzzException;
import org.guzz.orm.AbstractShadowTableView;

public class BaseShadowTableView extends AbstractShadowTableView {

	public String toTableName(Object tableCondition) {
		// TODO Auto-generated method stub
		 if(tableCondition == null){ //强制要求必须设置表分切条件，避免编程时疏忽。
	             throw new GuzzException("null table conditon is not allowed.") ;
	     }
	     
		 BaseBean study = (BaseBean) tableCondition ; 
		 int dbflag=1;
	     return super.getConfiguredTableName();// + dbflag;//+"_"+courseNo;
	     
	}


}
