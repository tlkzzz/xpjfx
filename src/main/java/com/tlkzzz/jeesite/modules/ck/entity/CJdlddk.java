/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.tlkzzz.jeesite.common.persistence.DataEntity;

/**
 * 进店离店打卡Entity
 * @author szx
 * @version 2017-06-20
 */
public class CJdlddk extends DataEntity<CJdlddk> {
	
	private static final long serialVersionUID = 1L;
	private String userid;		// 用户ID
	private Date dkdate;		// 进店时间
	private String status;		// 状态
	private String jdjd;      //进店经度
	private String jdwd;		//进店纬度
	private String ldjd;		//离店经度
	private String ldwd;		//离店纬度
	private Date ldDate;		//离店时间
	private String storeId;    //客户id
	private CStore cStore;		//客户实体
	private int fybs; 			//分页标识
	
	public CJdlddk() {
		super();
	}

	public CJdlddk(String id){
		super(id);
	}

	@Length(min=0, max=64, message="用户ID长度必须介于 0 和 64 之间")
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDkdate() {
		return dkdate;
	}

	public void setDkdate(Date dkdate) {
		this.dkdate = dkdate;
	}
	
	@Length(min=0, max=1, message="状态长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getJdjd() {
		return jdjd;
	}

	public void setJdjd(String jdjd) {
		this.jdjd = jdjd;
	}

	public String getJdwd() {
		return jdwd;
	}

	public void setJdwd(String jdwd) {
		this.jdwd = jdwd;
	}

	public String getLdjd() {
		return ldjd;
	}

	public void setLdjd(String ldjd) {
		this.ldjd = ldjd;
	}

	public String getLdwd() {
		return ldwd;
	}

	public void setLdwd(String ldwd) {
		this.ldwd = ldwd;
	}

	public Date getLdDate() {
		return ldDate;
	}

	public void setLdDate(Date ldDate) {
		this.ldDate = ldDate;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public CStore getcStore() {
		return cStore;
	}

	public void setcStore(CStore cStore) {
		this.cStore = cStore;
	}

	public int getFybs() {
		return fybs;
	}

	public void setFybs(int fybs) {
		this.fybs = fybs;
	}
}