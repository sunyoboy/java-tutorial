package com.lieve.online.base.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


import com.lieve.online.base.entity.SysParam;
import com.lieve.online.base.service.SysParamService;
import java.util.Properties;
import com.lieve.online.base.common.BaseAction;
import org.springframework.stereotype.Component;

@Component("SysParamActionTarget")
public class SysParamAction extends BaseAction<SysParam, SysParamService> {

	private static final Logger log = LoggerFactory.getLogger(SysParamAction.class);

    @Autowired
    public void setService(SysParamService service) {
        super.setService(service);
    }

    public SysParamAction() {
        Properties funcIdAttributes = new Properties();
        this.setFuncIdAttributes(funcIdAttributes);
    }

}