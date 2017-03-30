/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.entity;

import com.tlkzzz.jeesite.common.utils.excel.annotation.ExcelField;
import org.hibernate.validator.constraints.Length;

import com.tlkzzz.jeesite.common.persistence.DataEntity;

/**
 * 供应商表Entity
 * @author xrc
 * @version 2017-03-13
 */
public class CSupplier extends DataEntity<CSupplier> {
	
	private static final long serialVersionUID = 1L;
	private String yzbm;		// 邮政编码
	private String code;		// 供应商编码
	private String dh;		// 电话
	private String khzh;		// 开户账号
	private String sfzz;		// 身份证照
	private String yyzz;		// 营业执照
	private String gysjc;		// 供应商简称
	private String lxr;		// 联系人
	private String xxdz;		// 详细地址
	private String khh;		// 开户行
	private String khr;		// 开户人
	private String gszy;		// 公司主页
	private String email;		// 电子邮件
	private String name;		// 供应商名称
	
	public CSupplier() {
		super();
	}

	public CSupplier(String id){
		super(id);
	}

	@ExcelField(title="邮政编码", align=2, sort=20)
	@Length(min=0, max=64, message="邮政编码长度必须介于 0 和 64 之间")
	public String getYzbm() {
		return yzbm;
	}

	public void setYzbm(String yzbm) {
		this.yzbm = yzbm;
	}

	@ExcelField(title="供应商编码", align=2, sort=25)
	@Length(min=1, max=100, message="供应商编码长度必须介于 1 和 100 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@ExcelField(title="电话", align=2, sort=30)
	@Length(min=0, max=64, message="电话长度必须介于 0 和 64 之间")
	public String getDh() {
		return dh;
	}

	public void setDh(String dh) {
		this.dh = dh;
	}

	@ExcelField(title="开户账号", align=2, sort=35)
	@Length(min=0, max=64, message="开户账号长度必须介于 0 和 64 之间")
	public String getKhzh() {
		return khzh;
	}

	public void setKhzh(String khzh) {
		this.khzh = khzh;
	}
	
	@Length(min=0, max=640, message="身份证照长度必须介于 0 和 640 之间")
	public String getSfzz() {
		return sfzz;
	}

	public void setSfzz(String sfzz) {
		this.sfzz = sfzz;
	}
	
	@Length(min=0, max=640, message="营业执照长度必须介于 0 和 640 之间")
	public String getYyzz() {
		return yyzz;
	}

	public void setYyzz(String yyzz) {
		this.yyzz = yyzz;
	}

	@ExcelField(title="供应商简称", align=2, sort=40)
	@Length(min=0, max=64, message="供应商简称长度必须介于 0 和 64 之间")
	public String getGysjc() {
		return gysjc;
	}

	public void setGysjc(String gysjc) {
		this.gysjc = gysjc;
	}

	@ExcelField(title="联系人", align=2, sort=45)
	@Length(min=0, max=64, message="联系人长度必须介于 0 和 64 之间")
	public String getLxr() {
		return lxr;
	}

	public void setLxr(String lxr) {
		this.lxr = lxr;
	}

	@ExcelField(title="地址", align=2, sort=50)
	@Length(min=0, max=64, message="详细地址长度必须介于 0 和 64 之间")
	public String getXxdz() {
		return xxdz;
	}

	public void setXxdz(String xxdz) {
		this.xxdz = xxdz;
	}
	
	@Length(min=0, max=64, message="开户行长度必须介于 0 和 64 之间")
	public String getKhh() {
		return khh;
	}

	public void setKhh(String khh) {
		this.khh = khh;
	}
	
	@Length(min=0, max=64, message="开户人长度必须介于 0 和 64 之间")
	public String getKhr() {
		return khr;
	}

	public void setKhr(String khr) {
		this.khr = khr;
	}

	@ExcelField(title="公司主页", align=2, sort=55)
	@Length(min=0, max=64, message="公司主页长度必须介于 0 和 64 之间")
	public String getGszy() {
		return gszy;
	}

	public void setGszy(String gszy) {
		this.gszy = gszy;
	}

	@ExcelField(title="电子邮件", align=2, sort=60)
	@Length(min=0, max=64, message="电子邮件长度必须介于 0 和 64 之间")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@ExcelField(title="供应商名称", align=2, sort=65)
	@Length(min=1, max=64, message="供应商名称长度必须介于 1 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}