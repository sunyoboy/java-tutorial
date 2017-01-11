package com.lieve.online.base.service.gzimpl;

import com.lieve.online.base.common.BaseServiceImpl;
import com.lieve.online.base.dao.SysFuncDao;
import com.lieve.online.base.entity.SysFunc;
import com.lieve.online.base.service.SysFuncService;
import com.lieve.online.common.StringUtils;
import org.guzz.orm.se.Terms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service("SysFuncService")
public class SysFuncServiceImpl extends BaseServiceImpl<SysFunc, SysFuncDao> implements SysFuncService {


	@Autowired
	public void setDao(SysFuncDao dao) {
		super.setDao(dao);
	}

	@Autowired
	public void setSentity(SysFunc sentity) {
		super.setSentity(sentity);
	}

	public List<SysFunc> findMenus(Set<String> permissions) {
		return null;
	}

	@Override
	public List getCurrentUserMenuList(String userRight) {

		// TODO Auto-generated method stub

		List result=null;

		if( !StringUtils.isEmpty(userRight))
		{
			//对用户的权限进行处理
			int []rightNos=StringUtils.formatStringToArray(userRight);

			List conditions=new ArrayList();

			conditions.add(Terms.in("funcID", rightNos));

			conditions.add(Terms.eq("funcStatus", 1));

			conditions.add(Terms.eq("menuFlag", 1));

			result=this.dao.query(SysFunc.class, new SysFunc(), " parentID asc ,showOrder asc ,funcID asc ", conditions);

		}

		return result ;
	}
}