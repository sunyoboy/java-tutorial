package com.lieve.online.base.common;

import com.lieve.online.base.entity.SysRole;
import org.apache.log4j.Logger;
import org.guzz.Guzz;
import org.guzz.GuzzContext;
import org.guzz.dao.GuzzBaseDao;
import org.guzz.dao.PageFlip;
import org.guzz.jdbc.ObjectBatcher;
import org.guzz.orm.se.SearchExpression;
import org.guzz.orm.se.SearchTerm;
import org.guzz.orm.se.Terms;
import org.guzz.orm.sql.CompiledSQL;
import org.guzz.transaction.ReadonlyTranSession;
import org.guzz.transaction.WriteTranSession;
import org.guzz.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author sunlj
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public abstract class BaseDao extends GuzzBaseDao implements IBaseDao {

    protected Logger logger = Logger.getLogger(this.getClass());

    protected ErrorTools Error = new ErrorTools();

    // GuzzContext guzzContext=(GuzzContext)ServiceBean.getBean("guzzContext");

    public BaseDao() {

        // this.setGuzzContext(guzzContext);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.lieve.online.base.service.IBaseDao#save(java.lang.Object)
     */
    public void save(Object o) {
        this.logger.debug("save obj=" + o);
        this.insert(o, o);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.lieve.online.base.service.IBaseDao#update(java.lang.Object)
     */
    public void update(Object o) {
        this.logger.debug("save obj=" + o);
        this.update(o, o);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.lieve.online.base.service.IBaseDao#query(java.lang.String,
     * java.lang.Object, java.util.List, int, int, java.lang.String)
     */
    public QueryResult query(String businessName, Object tableCondition,
                             List conditions, int pageNo, int pageSize, String orderBy) {
        SearchExpression se = SearchExpression.forBusiness(businessName,
                pageNo, pageSize);

        return this.query(se, tableCondition, conditions, orderBy);

    }

    private QueryResult query(SearchExpression se, Object tableCondition,
                              List conditions, String orderBy) {

        if (tableCondition != null) {
            se.setTableCondition(tableCondition);
        }
        // se.setSkipCount(skipCount) ;
        se.and(conditions);
        if (StringUtil.notEmpty(orderBy)) {
            se.setOrderBy(orderBy);
        }

        PageFlip page = this.page(se);

        return new QueryResult(page.getPageNo(), page.getTotalCount(), 0,
                page.getPageSize(), page.getElements());

    }

    /*
     * (non-Javadoc)
     *
     * @see com.lieve.online.base.service.IBaseDao#query(java.lang.Class,
     * java.lang.Object, java.util.List, int, int, java.lang.String)
     */
    public QueryResult query(Class clazz, Object tableCondition,
                             List conditions, int pageNo, int pageSize, String orderBy) {
        if (!(pageNo > 0 && pageSize > 0)) {
            pageNo = 0;
            pageSize = Integer.MAX_VALUE;
            ;
        }

        SearchExpression se = SearchExpression
                .forClass(clazz, pageNo, pageSize);

        return this.query(se, tableCondition, conditions, orderBy);

    }

    /*
     * (non-Javadoc)
     *
     * @see com.lieve.online.base.IBaseDao#query(java.lang.Class,
     * java.lang.StringBuilder, java.lang.StringBuilder, java.util.Map,
     * java.lang.Object, int, int)
     */
    @Override
    public QueryResult query(Class clazz, StringBuilder queryData,
                             StringBuilder queryCount, Map params, Object tableCondition,
                             int pageNo, int pageSize) {
        // TODO Auto-generated method stub
        StringBuilder log = new StringBuilder().append("clazz=")
                .append(clazz.getName()).append(",queryData=")
                .append(queryData).append(",queryCount=").append(queryCount)
                .append(",params=").append(params)
                .append(",tableCondition=" + tableCondition).append(",pageNo=")
                .append(pageNo).append(",pageSize=").append(pageSize);

        this.logger.debug(log.toString());

        QueryResult result = new QueryResult();

        if (queryCount.indexOf(BaseBean.totalNumPropertyName) < 0) {
            // 没有包含指定的列名，修改查询记录总数的SQL语句
            int index = queryCount.toString().toLowerCase().indexOf(" from");
            String fromWhere = queryCount.substring(index);
            String select = queryCount.substring(0, index);
            select = select.substring(0, select.lastIndexOf(")") + 1);
            queryCount = new StringBuilder(select).append(" ")
                    .append(SysRole.totalNumPropertyName).append(fromWhere);

        }
        // 先查询记录总数
        int totalNum = ((BaseBean) this.query(clazz, queryCount.toString(),
                params, tableCondition, 0, 0).get(0)).getTotalNum();
        result.setTotalRow(totalNum);
        result.setTotalNum(totalNum);
        if (pageNo > 0 && pageSize > 0) {
            result.setPageSize(pageSize);
            int totalPage = result.getTotalPage();
            if (pageNo > totalPage) {
                pageNo = totalPage;
            }
            result.setPageNo(pageNo);
        }

        List list = this.query(clazz, queryData.toString(), params,
                tableCondition, pageNo, pageSize);

        result.setList(list);

        return result;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.lieve.online.base.service.IBaseDao#query(java.lang.Class,
     * java.lang.String, java.util.Map, java.lang.Object, int, int)
     */
    public List query(Class clazz, String sql, Map params,
                      Object tableCondition, int pageNo, int pageSize) {
        CompiledSQL bsql = this.getTransactionManager().getCompiledSQLBuilder()
                .buildCompiledSQL(clazz, sql);
        int start = 0;
        int maxSize = 0;
        if (pageNo > 0 && pageSize > 0) {
            start = (pageNo - 1) * pageSize + 1;
            maxSize = pageSize;
        } else {
            // 不分页
            start = 0;
            maxSize = Integer.MAX_VALUE;
        }
        Guzz.setTableCondition(tableCondition);

        return this.list(bsql.bind(params), start, maxSize);// bsql.bind(prams);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.lieve.online.base.service.IBaseDao#queryCount(java.lang.Class,
     * java.lang.String, java.lang.Object, java.util.List)
     */
    public long queryCount(Class clazz, String selectPhrase,
                           Object tableCondition, List<SearchTerm> list) {

        SearchExpression se = SearchExpression.forClass(clazz);
        se.setCountSelectPhrase(selectPhrase);
        se.setTableCondition(tableCondition);
        se.and(list);

        return this.queryCount(se);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.lieve.online.base.service.IBaseDao#queryCount(org.guzz.orm.se.SearchExpression
     * )
     */
    public long queryCount(SearchExpression se) {
        return this.count(se);
    }

    @Override
    public void delete(Object t, int no) {
        // TODO Auto-generated method stub
        this.delete(t, t, no);
    }

    @Override
    public void delete(Object t, Object tableCondition, int no) {

        Guzz.setTableCondition(tableCondition);

        ReadonlyTranSession read = this.getGuzzContext()
                .getTransactionManager().openDelayReadTran();

        Object entity = read.findObjectByPK(t.getClass(), no);

        WriteTranSession write = this.getGuzzContext().getTransactionManager()
                .openRWTran(true);

        write.delete(entity);

    }

    public void batchDelete(Object t, int[] no) {
        // TODO Auto-generated method stub
        this.batchDelete(t, t, no);
    }

    public void batchDelete(Object t, Object tableCondition, int[] no) {

        this.batchDelete(t.getClass(), tableCondition, "no", no);
    }

    @Override
    public void batchDelete(Class clazz, Object tableCondition, String keyName,
                            int[] no) {

        SearchExpression se = SearchExpression.forLoadAll(clazz);

        se.setTableCondition(tableCondition);

        se.and(Terms.in(keyName, no));

        ReadonlyTranSession read = this.getGuzzContext()
                .getTransactionManager().openDelayReadTran();

        List list = null;

        try {

            list = read.list(se);

        } finally {
            read.close();
        }
        // Open write connections to the master db.

        WriteTranSession write = this.getGuzzContext().getTransactionManager()
                .openRWTran(false);

        boolean commitFlag = false;

        try {

            ObjectBatcher batcher = write.createObjectBatcher();

            batcher.setTableCondition(tableCondition);

            for (int i = 0, j = list == null ? 0 : list.size(); i < j; i++) {
                batcher.delete(list.get(i));
            }

            batcher.executeBatch();

            write.commit();

            commitFlag = true;

        } finally {
            if (!commitFlag) {
                try {
                    write.rollback();
                } catch (Exception e) {
                    this.logger.debug(" rollback Error", e);
                }
            }

            write.close();
        }

    }

    @Autowired
    public void setGuzzContext(GuzzContext guzzContext) {
        // TODO Auto-generated method stub
        super.setGuzzContext(guzzContext);
    }

    @Override
    public List query(Class clazz, Object tableCondition, String orderBy,
                      List conditions) {
        // TODO Auto-generated method stub
        SearchExpression se = SearchExpression.forLoadAll(clazz);
        if (tableCondition != null) {
            se.setTableCondition(tableCondition);
        }

        se.and(conditions);

        if (StringUtil.notEmpty(orderBy)) {
            se.setOrderBy(orderBy);
        }

        return this.list(se);

    }

    @Override
    public Object getFirstObject(Class clazz, Object tableCondition,
                                 String orderBy, List conditions) {
        // TODO Auto-generated method stub
        Object result = null;
        List list = this.query(clazz, tableCondition, orderBy, conditions);
        if (list != null && list.size() > 0) {
            result = list.get(0);
        }
        return result;
    }

    @Override
    public Object getForRead(Class domainClass, Serializable pk) {
        // TODO Auto-generated method stub
        Guzz.setTableCondition(new BaseBean(BaseBean.dbOperateFlag_Read));
        return super.getForRead(domainClass, pk);
    }

    @Override
    public Object getForUpdate(Class domainClass, Serializable pk) {
        // TODO Auto-generated method stub
        Guzz.setTableCondition(new BaseBean(BaseBean.dbOperateFlag_Read));
        return super.getForUpdate(domainClass, pk);
    }

    public int excuteUpdateSql(Object tableCondition, StringBuilder sql) {
        int result = 0;
        this.logger.debug("tableCondition=" + tableCondition + ",sql=" + sql);
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            Guzz.setTableCondition(tableCondition);
            conn = this.getGuzzContext().getTransactionManager()
                    .openRWTran(true)
                    .createJDBCTemplate(tableCondition.getClass())
                    .getConnection();

            ps = conn.prepareStatement(sql.toString());
            result = ps.executeUpdate();
        } catch (Exception e) {
            this.logger.debug(" excuteUpdateSql Error", e);
            Error.writeErrorMsg(logger, "操作数据库时失败", e);
        } finally {
            try {
                if (ps != null)
                    ps.close();
            } catch (Exception e) {
                this.logger.debug(e);
            }

            try {
                if (conn != null)
                    conn.close();
            } catch (Exception e) {
                this.logger.debug(e);
            }
        }

        return result;
    }
}
