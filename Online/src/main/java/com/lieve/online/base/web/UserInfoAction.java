package com.lieve.online.base.web;

import com.lieve.online.base.entity.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


import com.lieve.online.base.entity.UserInfo;
import com.lieve.online.base.service.UserInfoService;
import java.util.Properties;
import com.lieve.online.base.common.BaseAction;
import org.springframework.stereotype.Component;

@Component("UserInfoActionTarget")
public class UserInfoAction extends BaseAction<UserInfo, UserInfoService> {

	private static final Logger log = LoggerFactory.getLogger(UserInfoAction.class);

    @Autowired
    public void setService(UserInfoService service) {
        super.setService(service);
    }

    public UserInfoAction() {
        Properties funcIdAttributes = new Properties();
        this.setFuncIdAttributes(funcIdAttributes);
    }

}