package com.lieve.online.base.common;

import org.guzz.connection.AbstractVirtualDBView;
import org.guzz.exception.GuzzException;

public class BaseVirtualDBView extends AbstractVirtualDBView {

	@Override
	public String getPhysicsDBGroupName(Object dbCondition) {
		// TODO Auto-generated method stub
		if(dbCondition == null){ //强制要求必须设置表分切条件，避免编程时疏忽。
            throw new GuzzException(" null db conditon is not allowed.") ;            
		}
		
		BaseBean baseBean=(BaseBean)dbCondition;
		
		int index=1;
		
		if(baseBean.getDbOperateFlag()==baseBean.dbOperateFlag_Write)
		{
			//写
			index=1;
		}else{
			//读取从库的个数
			/*int slaveNum=Util.StringToInt(ReadeSystemConfig.getsystem("slavedbnum") , -1);*/
			int slaveNum = 4;
			
			if(slaveNum>0)
			{
				//取得一个随机码			
				int random=(int)(Math.random()*100);
				index=(int)(Math.random()*100)%slaveNum+2;//从库的名称序号是从2开始的				
			}			
		}
		
		return "db"+index;
		
	}

}
