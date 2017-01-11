package com.lieve.online.base.service.gzimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lieve.online.base.common.BaseServiceImpl;
import com.lieve.online.base.dao.ParamTypeDao;
import com.lieve.online.base.entity.ParamType;
import com.lieve.online.base.service.ParamTypeService;

@Service("ParamTypeService")
public class ParamTypeServiceImpl extends BaseServiceImpl<ParamType, ParamTypeDao> implements ParamTypeService {


	@Autowired
	public void setDao(ParamTypeDao dao) {
		super.setDao(dao);
	}

	@Autowired
	public void setSentity(ParamType sentity) {
		super.setSentity(sentity);
	}

}