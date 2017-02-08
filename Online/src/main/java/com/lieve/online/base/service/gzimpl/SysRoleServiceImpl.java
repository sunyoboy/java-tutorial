package com.lieve.online.base.service.gzimpl;

import com.lieve.online.base.common.BaseServiceImpl;
import com.lieve.online.base.common.Constant;
import com.lieve.online.base.common.QueryResult;
import com.lieve.online.base.common.Util;
import com.lieve.online.base.dao.SysRoleDao;
import com.lieve.online.base.entity.SysRole;
import com.lieve.online.base.service.SysFuncService;
import com.lieve.online.base.service.SysRoleService;
import org.guzz.orm.se.Terms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service("SysRoleService")
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole, SysRoleDao> implements SysRoleService {

	@Autowired
	private SysFuncService sysFuncService;

	@Autowired
	public void setDao(SysRoleDao dao) {
		super.setDao(dao);
	}

	@Autowired
	public void setSentity(SysRole sentity) {
		super.setSentity(sentity);
	}


	public Set<String> findRoles(int roleId) {
		return null;
	}

	public SysRole findOne(int roleId) {
		return (SysRole)this.dao.getForRead(SysRole.class, roleId);
	}

	@Override
	public Set<String> findPermissions(Long[] roleIds) {
/*		Set<Long> resourceIds = new HashSet<Long>();
		for(Long roleId : roleIds) {
			SysRole sysRole = findOne(roleId);
			if(sysRole != null) {
				resourceIds.addAll(sysRole.getResourceIds());
			}
		}
		return resourceService.findPermissions(resourceIds);*/
		return null;
	}

	private String getOrderBy(String aliasName,int orderFieldId, int orderMethodFlag)
	{
		boolean orderByNoFlag = false ;

		StringBuilder orderBy=new StringBuilder(" ");

		if(!Util.isNull(aliasName))
		{
			orderBy.append(" ").append(aliasName ).append(".");
		}

		switch (orderFieldId) {
			case 1:
				orderBy.append("roleName ") ;
				break ;
			default:
				orderBy.append("roleNo ") ;
				orderByNoFlag = true ;
		}

		if (orderMethodFlag != Constant.OrderDirectFlag_Asc) {
			orderBy.append(" desc ") ;
		}else{
			orderBy.append(" asc ") ;
		}

		if (!orderByNoFlag) {

			orderBy.append(" ,  ") ;

			if(!Util.isNull(aliasName))
			{
				orderBy.append(" ").append(aliasName ).append(".");
			}

			orderBy.append("roleNo asc");

		}

		this.logger.debug("  orderBY="+orderBy.toString());

		return orderBy.toString();

	}

	@Override
	public QueryResult query(int pageNo, int pageSize, SysRole entity,int orderFieldId, int orderMethodFlag) {

		// 平台
		//SELECT * FROM `base_sysrole` where hiberid=1;
		// 地区
		//SELECT sr.* FROM `base_sysrole`  sr  left  join  base_userInfo ui on sr.createUser=ui.userNo   where  sr.hiberid=2 and ui.regionNo=1;

		//学校
		//SELECT sr.* FROM `base_sysrole`  sr  left  join  base_userInfo ui on sr.createUser=ui.userNo   where  sr.hiberid=3 and ui.schoolNo=1;

		//conditions.add(Terms.notEq("defaultFlag", SysRole.defaultFlag_system));

		QueryResult result = new QueryResult();
		if (entity != null) {
			List conditions=new ArrayList();
			conditions.add(Terms.eq("defaultFlag", SysRole.DefaultFlag_Yes));
			if (!Util.isNull(entity.getRoleName())) {
				conditions.add(Terms.like("roleName", "%" + entity.getRoleName() + "%", true) );
			}
			result=this.dao.query(SysRole.class, this.getSentity(), conditions, pageNo, pageSize, this.getOrderBy(null, orderFieldId, orderMethodFlag));
		}
		return result;

	}

}