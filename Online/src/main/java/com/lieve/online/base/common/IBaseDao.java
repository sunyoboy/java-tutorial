package com.lieve.online.base.common;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.guzz.GuzzContext;
import org.guzz.dao.PageFlip;
import org.guzz.dao.WriteTemplate;
import org.guzz.orm.se.SearchExpression;
import org.guzz.orm.se.SearchTerm;
import org.guzz.orm.sql.BindedCompiledSQL;
import org.guzz.transaction.TransactionManager;

public abstract interface IBaseDao {

    public void save(Object o);

    public void update(Object o);

    public QueryResult query(String businessName, Object tableCondition,
                             List conditions, int pageNo, int pageSize, String orderBy);

    public QueryResult query(Class clazz, Object tableCondition,
                             List conditions, int pageNo, int pageSize, String orderBy);

    public QueryResult query(Class clazz, StringBuilder queryData,
                             StringBuilder queryCount, Map params, Object tableCondition,
                             int pageNo, int pageSize);

    public List query(Class clazz, String sql, Map params,
                      Object tableCondition, int pageNo, int pageSize);

    public long queryCount(Class clazz, String selectPhrase,
                           Object tableCondition, List<SearchTerm> list);

    public long queryCount(SearchExpression se);

    public long count(SearchExpression se);

    public void delete(Object domainObject, Object tableCondition);

    public void delete(Object domainObject);

    public Object findCell00(BindedCompiledSQL bsql, String returnType);

    public Object findCell00(String id, Map params, String returnType);

    public Object findObject(BindedCompiledSQL bsql);

    public Object findObject(SearchExpression se);

    public Object findObject(String id, Map params);

    public Object getForRead(Class domainClass, Serializable pk);

    public Object getForUpdate(Class domainClass, Serializable pk);

    public GuzzContext getGuzzContext();

    public TransactionManager getTransactionManager();

    public Serializable insert(Object domainObject, Object tableCondition);

    public Serializable insert(Object domainObject);

    public List list(BindedCompiledSQL bsql, int startPos, int maxSize);

    public List list(SearchExpression se);

    public List list(String id, Map params, int startPos, int maxSize);

    public List list(String id, Map params);

    public PageFlip page(SearchExpression se);

    public void setGuzzContext(GuzzContext guzzContext);

    public void setTransactionManager(TransactionManager transactionManager);

    public void setWriteTemplate(WriteTemplate writeTemplate);

    public void update(Object domainObject, Object tableCondition);

    public boolean equals(Object arg0);

    public int hashCode();

    public String toString();

    public void delete(Object t, int no);

    public void delete(Object t, Object tableCondition, int no);

    public void batchDelete(Class clazz, Object tableCondition, String keyName,
                            int[] no);

    public List query(Class clazz, Object tableCondition, String orderBy,
                      List conditions);

    public Object getFirstObject(Class clazz, Object tableCondition,
                                 String orderBy, List conditions);

    public int excuteUpdateSql(Object tableCondition, StringBuilder sql);
}