package com.wgl.ssm.model;

import java.util.Date;

/**
 * log.setDescription(getControllerMethodDescription(joinPoint));  
            log.setMethod((joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));  
            log.setType(0);  
            log.setIp(ip);  
            log.setExceptionCode(null);  
            log.setExceptionDetail(null);  
            log.setParams(null);  
            log.setCreateUser(manager.getAccountName());  
            log.setCreateDate(new Date());  
* @ClassName: SystemLog 
* @Description: TODO
* @author wanggl 
* @date 2017年6月15日 下午4:09:35 
* @version V1.0
 */
public class SystemLog {

	private int id;
	private String description;
	private String method;
	private int type;
	private String ip;
	private String exceptionCode;
	private String exceptionDetail;
	private String params;
	private String createUser;
	private Date createDate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getExceptionCode() {
		return exceptionCode;
	}
	public void setExceptionCode(String exceptionCode) {
		this.exceptionCode = exceptionCode;
	}
	public String getExceptionDetail() {
		return exceptionDetail;
	}
	public void setExceptionDetail(String exceptionDetail) {
		this.exceptionDetail = exceptionDetail;
	}
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
}
