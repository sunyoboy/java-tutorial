package com.lieve.online.base.common;
 

public abstract class ServiceAction <S extends IBaseService>  extends  com.lieve.online.base.common.LogAction{

    protected S service;

    public S getService() {
	return service;
    }

    public void setService(S service) {
	this.service = service;
    }
}
