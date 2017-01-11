package com.lieve.online.base.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


import com.lieve.online.base.entity.ParamType;
import com.lieve.online.base.service.ParamTypeService;
import java.util.Properties;
import com.lieve.online.base.common.BaseAction;
import org.springframework.stereotype.Component;

@Component("ParamTypeActionTarget")
public class ParamTypeAction extends BaseAction<ParamType, ParamTypeService> {

	private static final Logger log = LoggerFactory.getLogger(ParamTypeAction.class);

    @Autowired
    public void setService(ParamTypeService service) {
        super.setService(service);
    }

    public ParamTypeAction() {
        Properties funcIdAttributes = new Properties();
        this.setFuncIdAttributes(funcIdAttributes);
    }

}