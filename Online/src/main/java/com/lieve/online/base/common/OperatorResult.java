package com.lieve.online.base.common;
/**
 * 操作结果类，用于Struts在跳转页面后返回一些常用参数
 * @author laili
 *
 */
public class OperatorResult {
	
	/**
	 * 操作结果值
	 */
	private int result;
	
	/**
	 * 错误信息
	 */
	private String errCause;
	
	/**
	 * 操作种类
	 */
	private String operatorType;
	
	/**
	 * 跳转路径
	 */
	private String toPath;
	
	/**
	 * 查询类实体
	 */
	private Object queryBean;
	
	/**
	 * 结果类实体
	 */
	private Object resultBean;
	
	/**
	 * 跳转Action
	 */
	private String toAction;
	
	/**
	 * 项目名称
	 */
	private String projectPath;

	public String getProjectPath() {
		return projectPath;
	}

	public void setProjectPath(String projectPath) {
		this.projectPath = projectPath;
	}

	public String getToAction() {
		return toAction;
	}

	public void setToAction(String toAction) {
		this.toAction = toAction;
	}

	public Object getQueryBean() {
		return queryBean;
	}

	public void setQueryBean(Object queryBean) {
		this.queryBean = queryBean;
	}

	public Object getResultBean() {
		return resultBean;
	}

	public void setResultBean(Object resultBean) {
		this.resultBean = resultBean;
	}

	public OperatorResult(){
		
	}
	
	public OperatorResult(int result,String errCause){
		this.result = result;
		this.errCause = errCause;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getErrCause() {
		return errCause;
	}

	public void setErrCause(String errCause) {
		this.errCause = errCause;
	}

	public String getOperatorType() {
		return operatorType;
	}

	public void setOperatorType(String operatorType) {
		this.operatorType = operatorType;
	}
	
	public String getToPath() {
		return toPath;
	}

	public void setToPath(String toPath) {
		this.toPath = toPath;
	}
	
	public static final String[] operatorTypes = {"增加","删除","修改","查询","批量增加"};

}
