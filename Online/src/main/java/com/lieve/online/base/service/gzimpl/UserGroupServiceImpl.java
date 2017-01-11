package com.lieve.online.base.service.gzimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lieve.online.base.common.BaseServiceImpl;
import com.lieve.online.base.dao.UserGroupDao;
import com.lieve.online.base.entity.UserGroup;
import com.lieve.online.base.service.UserGroupService;

@Service("UserGroupService")
public class UserGroupServiceImpl extends BaseServiceImpl<UserGroup, UserGroupDao> implements UserGroupService {


	@Autowired
	public void setDao(UserGroupDao dao) {
		super.setDao(dao);
	}

	@Autowired
	public void setSentity(UserGroup sentity) {
		super.setSentity(sentity);
	}

}