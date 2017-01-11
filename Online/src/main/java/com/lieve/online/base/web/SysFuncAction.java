package com.lieve.online.base.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


import com.lieve.online.base.entity.SysFunc;
import com.lieve.online.base.service.SysFuncService;
import java.util.Properties;
import com.lieve.online.base.common.BaseAction;
import org.springframework.stereotype.Component;

@Component("SysFuncActionTarget")
public class SysFuncAction extends BaseAction<SysFunc, SysFuncService> {

	private static final Logger log = LoggerFactory.getLogger(SysFuncAction.class);

    @Autowired
    public void setService(SysFuncService service) {
        super.setService(service);
    }

    public SysFuncAction() {
        Properties funcIdAttributes = new Properties();
        this.setFuncIdAttributes(funcIdAttributes);
    }

}