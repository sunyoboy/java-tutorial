package com.lieve.online.base.service.gzimpl;

import com.lieve.online.base.common.BaseServiceImpl;
import com.lieve.online.base.common.QueryResult;
import com.lieve.online.common.Util;
import com.lieve.online.base.dao.UserInfoDao;
import com.lieve.online.base.entity.UserInfo;
import com.lieve.online.base.service.SysRoleService;
import com.lieve.online.base.service.UserInfoService;
import com.lieve.online.common.LogicTools;
import org.guzz.orm.se.Terms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service("userInfoService")
public class UserInfoServiceImpl extends BaseServiceImpl<UserInfo, UserInfoDao> implements UserInfoService {

	@Autowired
	private SysRoleService sysRoleService;

	public void setSysRoleService(SysRoleService sysRoleService) {
		this.sysRoleService = sysRoleService;
	}

	@Autowired
	public void setDao(UserInfoDao dao) {
		super.setDao(dao);
	}

	@Autowired
	public void setSentity(UserInfo sentity) {
		super.setSentity(sentity);
	}

	public UserInfo findByUsername(String username) {
		List<UserInfo> userInfoList = super.queryByProperty(new UserInfo(), "loginName", username, "");
		if(userInfoList.size() == 0) {
			return null;
		}
		return userInfoList.get(0);
	}

	/**
	 * 根据用户名查找其角色
	 * @param username
	 * @return
	 */
	public Set<String> findRoles(String username) {
		UserInfo userInfo =findByUsername(username);
		if(userInfo == null) {
			return Collections.EMPTY_SET;
		}
		return sysRoleService.findRoles(userInfo.getRoleNo());
	}

	/**
	 * 根据用户名查找其权限
	 * @param username
	 * @return
	 */
	public Set<String> findPermissions(String username) {
/*		UserInfo userInfo =findByUsername(username);
		if(userInfo == null) {
			return Collections.EMPTY_SET;
		}
		return sysRoleService.findPermissions(userInfo.getRoleNo());*/

		return null;
	}

	@Override
	public QueryResult query(int pageNo, int pageSize, UserInfo entity, int orderFieldId, int orderMthodFlag) {

		QueryResult result=null;

		StringBuilder fromWhere=new StringBuilder(" from base_userinfo bui where 1=1 ");

		if(entity!=null)
		{
			//SQL 查询
			boolean selectByStatusFlag=false;

			Map params=new HashMap();

			if(!Util.isNull(entity.getLoginName()) )
			{
				fromWhere.append(" and bui.loginName like :loginName ");
				params.put("loginName", "%"+entity.getLoginName()+"%");
			}

			if(!Util.isNull(entity.getRealName()) )
			{
				fromWhere.append(" and bui.realName like :realName ");
				params.put("realName", "%"+entity.getRealName()+"%");
			}

			if(entity.getRoleNo()>0)
			{
				fromWhere.append(" and bui.roleNo=:roleNo ");
				params.put("roleNo", entity.getRoleNo());
			}

			if(entity.getSex()>0) {
				fromWhere.append(" and bui.sex=:sex");
				params.put("sex", entity.getSex());
			}

			if(entity.getUserStatus()>-1)
			{
				fromWhere.append(" and bui.userStatus=:userStatus ");
				params.put("userStatus", entity.getUserStatus());
				selectByStatusFlag=true;
			}

			if(entity.getType()>0)
			{
				fromWhere.append(" and bui.type=:type ");
				params.put("type", entity.getType());
			}

			StringBuilder queryData=new StringBuilder(" select bui.* ").append(fromWhere).append(" order by ").append(this.getOrderBy("bui", orderFieldId, orderMthodFlag));

			StringBuilder queryCount=new StringBuilder(" select count(bui.userNo) ").append(UserInfo.totalNumPropertyName).append(fromWhere);

			result=this.dao.query(UserInfo.class, queryData, queryCount, params, new UserInfo(), pageNo, pageSize);

		}else{
			result=this.dao.query(UserInfo.class,new UserInfo(), null, pageNo, pageSize, this.getOrderBy(null, orderFieldId, orderMthodFlag));
		}

		return result;

	}

	private String getOrderBy(String alias,int orderFieldId, int orderMthodFlag )
	{
		boolean orderByUserNoFlag=false;

		StringBuilder orderBy=new StringBuilder();
		if(LogicTools.isNull(alias))
		{
			alias=" ";
		}else{
			alias=" "+alias.trim()+".";
		}

		if(orderFieldId==2)
		{
			orderBy.append(alias).append("loginName ");
		}else if(orderFieldId==3)
		{
			orderBy.append(alias).append("realName ");
		}else if(orderFieldId==4)
		{
			orderBy.append(alias).append("roleNo ");
		} else if(orderFieldId==6)
		{
			orderBy.append(alias).append("userStatus ");
		}else
		{
			orderBy.append(alias).append("userNo ");

			orderByUserNoFlag=true;
		}

		if(orderMthodFlag!=1)
		{
			orderBy.append(" desc ");
		}else{
			orderBy.append(" asc ");
		}
		if(!orderByUserNoFlag)
		{
			orderBy.append(" ,").append(alias).append("userNo asc ");
		}

		return orderBy.toString();
	}

	public void create(UserInfo userInfo) {
		userInfo.setRoleNo(1);
		userInfo.setGroupNo(1);
		Date date = new Date();
		Timestamp timestamp = new Timestamp(date.getTime());
		userInfo.setCreateTime(timestamp);
		userInfo.setSumOnline(0);
		super.create(userInfo);
	}

	@Override
	public UserInfo getByLoginName(String loginName) {
		List conditions=new ArrayList();
		conditions.add(Terms.eq("loginName", loginName));
		UserInfo userInfo=(UserInfo)this.dao.getFirstObject(UserInfo.class,new UserInfo(), null, conditions);//.getFirstRecord(hql, loginName);
		return userInfo;
	}

}