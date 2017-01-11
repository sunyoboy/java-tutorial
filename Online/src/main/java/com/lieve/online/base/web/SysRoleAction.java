package com.lieve.online.base.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


import com.lieve.online.base.entity.SysRole;
import com.lieve.online.base.service.SysRoleService;
import java.util.Properties;
import com.lieve.online.base.common.BaseAction;
import org.springframework.stereotype.Component;

@Component("SysRoleActionTarget")
public class SysRoleAction extends BaseAction<SysRole, SysRoleService> {

	private static final Logger log = LoggerFactory.getLogger(SysRoleAction.class);

    @Autowired
    public void setService(SysRoleService service) {
        super.setService(service);
    }

    public SysRoleAction() {
        Properties funcIdAttributes = new Properties();
        this.setFuncIdAttributes(funcIdAttributes);
    }

}