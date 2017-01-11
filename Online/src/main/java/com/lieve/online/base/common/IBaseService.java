package com.lieve.online.base.common;

import java.util.List;



public abstract interface IBaseService<T ,D extends IBaseDao> {
    
	public void create(T entity);
	
	public void delete(int no);
	
	public void batchDelete(int[] no);
	
	public void update(T entity); 
	
	public QueryResult query(int pageNo, int pageSize, T entity, int orderFieldId, int orderMthodFlag);
	
	public boolean checkName(String name, long no);
	
	public D  getDao();
	
	public void setDao(D dao);
	
	public T getById(long id);
	
	public List getAll(String orderBy);
	
	public List queryByProperty(T clazz, String property, long propertyValue, String orderProperty);
	
	public List queryByProperty(T clazz, String property, String propertyValue, String orderProperty);

}
