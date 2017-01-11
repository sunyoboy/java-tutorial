package com.lieve.online.base.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


import com.lieve.online.base.entity.UserGroup;
import com.lieve.online.base.service.UserGroupService;
import java.util.Properties;
import com.lieve.online.base.common.BaseAction;
import org.springframework.stereotype.Component;

@Component("UserGroupActionTarget")
public class UserGroupAction extends BaseAction<UserGroup, UserGroupService> {

	private static final Logger log = LoggerFactory.getLogger(UserGroupAction.class);

    @Autowired
    public void setService(UserGroupService service) {
        super.setService(service);
    }

    public UserGroupAction() {
        Properties funcIdAttributes = new Properties();
        this.setFuncIdAttributes(funcIdAttributes);
    }

}