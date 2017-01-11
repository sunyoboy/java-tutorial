package com.lieve.online.base.service.gzimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lieve.online.base.common.BaseServiceImpl;
import com.lieve.online.base.dao.SysParamDao;
import com.lieve.online.base.entity.SysParam;
import com.lieve.online.base.service.SysParamService;

@Service("SysParamService")
public class SysParamServiceImpl extends BaseServiceImpl<SysParam, SysParamDao> implements SysParamService {


	@Autowired
	public void setDao(SysParamDao dao) {
		super.setDao(dao);
	}

	@Autowired
	public void setSentity(SysParam sentity) {
		super.setSentity(sentity);
	}

}