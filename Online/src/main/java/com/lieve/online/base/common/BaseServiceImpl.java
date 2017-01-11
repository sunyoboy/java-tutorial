package com.lieve.online.base.common;

import com.lieve.online.base.entity.SysRole;
import com.lieve.online.common.Util;
import org.guzz.orm.Business;
import org.guzz.orm.ObjectMapping;
import org.guzz.orm.se.Terms;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


/**
 * @author zhenjw
 * @createDate 2009-12-3 下午12:59:08
 * @since 1.0
 * @version 1.0
 */
public abstract class BaseServiceImpl<T extends BaseBean, V extends IBaseDao> extends BaseErrorLog 
		implements IBaseService<T, V> { 

	protected V dao;

	protected T sentity;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lieve.online.ddpms.service.IBaseService#batchDelete(long[])
	 */
	public void batchDelete(int[] no) {
		// TODO Auto-generated method stub
		this.batchDelete("no", no);
	}
	
	public void batchDelete(String keyName,int[] no) {
		// TODO Auto-generated method stub
		this.sentity.setDbOperateFlag(SysRole.dbOperateFlag_Write);		
		this.dao.batchDelete(sentity.getClass(), sentity, keyName, no);
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lieve.online.ddpms.service.IBaseService#checkName(java.lang.String,
	 * long)
	 */
	public boolean checkName(String name, long id) {
		// TODO Auto-generated method stub
		throw new ServiceException("检查同名的功能没有实现！");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lieve.online.ddpms.service.IBaseService#create(java.lang.Object)
	 */
	public void create(T entity) {
		// TODO Auto-generated method stub
		entity.setDbOperateFlag(BaseBean.dbOperateFlag_Write);
		this.dao.save(entity);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lieve.online.ddpms.service.IBaseService#delete(long)
	 */
	public void delete(int no) {
		// TODO Auto-generated method stub
		sentity.setDbOperateFlag(BaseBean.dbOperateFlag_Write);
		this.dao.delete(sentity, no);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lieve.online.ddpms.service.IBaseService#getDao(java.lang.Object)
	 */
	public V getDao() {
		// TODO Auto-generated method stub
		return dao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lieve.online.ddpms.service.IBaseService#setDao(java.lang.Object)
	 */
	public void setDao(V dao) {
		// TODO Auto-generated method stub
		this.dao = dao;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lieve.online.ddpms.service.IBaseService#update(java.lang.Object)
	 */
	public void update(T entity) {
		// TODO Auto-generated method stub
		entity.setDbOperateFlag(BaseBean.dbOperateFlag_Write);
		this.dao.update(entity);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lieve.online.base.service.IBaseService#getById(long)
	 */
	public T getById(long id) {
		// TODO Auto-generated method stub
		// return (T)this.dao.getByPk(sentity.getClass(),id);
		return (T) this.dao.getForRead(sentity.getClass(), id);

	}

	/**
	 * @return T the sentity
	 */
	public T getSentity() {
		return sentity;
	}

	/**
	 * @param sentity
	 *            the sentity to set
	 */
	public void setSentity(T sentity) {
		this.sentity = sentity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lieve.online.base.service.IBaseService#query(int, int, java.lang.Object,
	 * int, int) 通过反射机制找到实体类对应的属性列表，然后遍历，找到有对应数据库表列的属性，
	 * 如果是>0的整数，或者是非空的字符串，就放到对应的查询条件中 通过实体类对应的配置信息找到对应的主键属性，做为排序的列，默认按降序排序
	 */
	public QueryResult query(int pageNo, int pageSize, T entity,
			int orderFieldId, int orderMethodFlag) {

		List conditions = new ArrayList();
		Class beanClass = sentity.getClass();
		String pkPropName = " no ";
		String orderBy = null;
		try {
			BeanInfo bi = Introspector.getBeanInfo(beanClass);
			PropertyDescriptor[] pd = bi.getPropertyDescriptors();
			String propertyName = null;
			Object value = null;
			Method readMethod = null;

			String businessName = beanClass.getSimpleName();
			businessName = businessName.substring(0, 1).toLowerCase()
					+ businessName.substring(1);
			Business business = dao.getGuzzContext().getBusiness(businessName);
			ObjectMapping objectMapping = business.getMapping();
			pkPropName = business.getTable().getPKPropName();

			if (entity != null) {

				for (PropertyDescriptor pdt : pd) {
					readMethod = pdt.getReadMethod();
					propertyName = pdt.getDisplayName();

					if (objectMapping.getColNameByPropNameForSQL(propertyName) != null) {
						value = readMethod.invoke(entity, null);

						if (value instanceof Integer) {
							// System.out.println(" type is int");
							conditions.add(Terms.eq(propertyName, value));

						} else if (value instanceof String) {
							if (!Util.isNull((String) value)) {
								conditions.add(Terms.like(propertyName, "%"
										+ (String) value + "%", true));
							}
						}
					}

				}

				orderBy = this.queryJudgeSpecialProperty(conditions, entity,
						orderFieldId, orderMethodFlag);

			}

			if (Util.isNull(orderBy)) {
				orderBy = pkPropName
						+ (orderMethodFlag == 1 ? " asc " : " desc ");
			}

		} catch (Exception e) {
			throw new ServiceException("默认的查询功能出错了！", e);
		}

		sentity.setDbOperateFlag(BaseBean.dbOperateFlag_Read);
		return this.getDao().query(beanClass, sentity, conditions, pageNo,
				pageSize, orderBy);

	}

	protected String queryJudgeSpecialProperty(List conditions, T entity,
			int orderFieldId, int orderMthodFlag) {
		return null;
	}

	@Override
	public List getAll(String orderBy) {
		// TODO Auto-generated method stub
		sentity.setDbOperateFlag(BaseBean.dbOperateFlag_Read);
		return this.dao.query(sentity.getClass(), sentity, orderBy, null);
	}
	
	
	public List queryByProperty(T clazz, String property, long propertyValue, String orderProperty) {
		sentity.setDbOperateFlag(BaseBean.dbOperateFlag_Read);
		List conditions=new ArrayList();
		if(property != null && propertyValue != 0) {
			conditions.add(Terms.eq(property, propertyValue));
		}
		
		String orderBy = " ";
		if(orderProperty != null && !orderProperty.equals("")) {
			orderBy = orderProperty + " desc";
		}
		return this.dao.query(sentity.getClass(), sentity, orderBy, conditions);
	}
	
	public List queryByProperty(T clazz, String property, String propertyValue, String orderProperty) {
		sentity.setDbOperateFlag(BaseBean.dbOperateFlag_Read);
		List conditions=new ArrayList();
		if(property != null && !propertyValue.equals("")) {
			conditions.add(Terms.eq(property, propertyValue));
		}
		
		String orderBy = " ";
		if(orderProperty != null && !orderProperty.equals("")) {
			orderBy = orderProperty + " desc";
		}
		return this.dao.query(sentity.getClass(), sentity, orderBy, conditions);
	}	
}
