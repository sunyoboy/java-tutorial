package com.lieve.online.base.common;

import java.io.Serializable;
import java.util.List;

/**
 * 带分页查询功能的API的返回结果。
 * 
 * @author zhenjw
 * 
 */
public class QueryResult extends BaseBean implements Serializable {

	/** 当前页的页码 **/
	private int pageNo;

	/** 记录总数 **/
	private int totalRow;

	/** 总的记录数 **/
	private int totalPage;

	/** 每页是示的记录数 **/
	private int pageSize;

	/** 结果记录集 **/
	private List list;


    /**
     * 功能描述: <br>
     * 得到总页数
     * 
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
	public int getTotalPage() {
		if (this.pageSize > 0 && this.totalRow > 0) {
			this.totalPage=(this.totalRow+this.pageSize-1)/this.pageSize;
		}
		return totalPage;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getTotalRow() {
		return totalRow;
	}

	public void setTotalRow(int totalRow) {
		this.totalRow = totalRow;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public QueryResult(int pageNo, int totalRow, int totalPage, int pageSize, List list) {
		super();
		this.pageNo = pageNo;
		this.totalRow = totalRow;
		this.totalPage = totalPage;
		this.pageSize = pageSize;
		this.list = list;
	} 

    public QueryResult() {

    } 

}
