/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.entity;

import com.tlkzzz.jeesite.common.utils.excel.annotation.ExcelField;
import org.hibernate.validator.constraints.Length;
import com.tlkzzz.jeesite.modules.sys.entity.Area;

import com.tlkzzz.jeesite.common.persistence.DataEntity;

/**
 * 客户表Entity
 * @author xrc
 * @version 2017-03-13
 */
public class CStore extends DataEntity<CStore> {
	
	private static final long serialVersionUID = 1L;
	private String dpmc;		// 店铺名称
	private String khjc;		// 客户简称
	private String lxr;		// 联系人
	private String dz;		// 地址
	private String yb;		// 邮编
	private String dh;		// 电话
	private String phone;		// 手机
	private String email;		// 电子邮件
	private String jfxs;		// 积分系数
	private String khh;		// 开户行名称
	private String khhzh;		// 开户行账号
	private String khr;		// 开户人
	private String yyzz;		// 营业执照
	private String sfzz;		// 身份证照
	private String jd;		// 经度
	private String wd;		// 纬度
	private String mdtp;		// 门店图片
	private CSclass sclass;		// 客户分类
	private Area area;		// 区域主键
	private String name;		// 客户名称
	private String state;		//客户审核状态
	private String khclass;    //客户分类
	private String cgy;        //采购员
	private String cgydh;      //采购员联系方式
	private String  hzzq;      //回执周期
	private String weixin;     //微信
	private String qq;         //QQ
	private String edu;        //额度
	private String xsqd;        //销售渠道
	private String jhqd;         //进货渠道
	private String quota;       //限额    shizx 新增字段，用于设定客户欠款额度


	public String getCgy() {
		return cgy;
	}
//shizx 新增get方法
	public String getQuota() {
		return quota;
	}
//shizx 新增set方法
	public void setQuota(String quota) {
		this.quota = quota;
	}

	public void setCgy(String cgy) {
		this.cgy = cgy;
	}

	public String getHzzq() {
		return hzzq;
	}


	public void setHzzq(String hzzq) {
		this.hzzq = hzzq;
	}

	public String getCgydh() {
		return cgydh;
	}

	public void setCgydh(String cgydh) {
		this.cgydh = cgydh;
	}

	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getEdu() {
		return edu;
	}

	public void setEdu(String edu) {
		this.edu = edu;
	}


	public String getXsqd() {
		return xsqd;
	}

	public void setXsqd(String xsqd) {
		this.xsqd = xsqd;
	}

	public String getJhqd() {
		return jhqd;
	}

	public void setJhqd(String jhqd) {
		this.jhqd = jhqd;
	}





	public CStore() {
		super();
	}

	public CStore(String id){
		super(id);
	}


	@ExcelField(title="客户分类",value = "sclass.name", align=2, sort=60)
	public String getKhclass()
	{
		return  khclass;
	}
	public  void setKhclass(String khclass){
		this.khclass=khclass;
	}
	@ExcelField(title="店称铺名", align=2, sort=25)
	@Length(min=0, max=64, message="店称铺名长度必须介于 0 和 64 之间")
	public String getDpmc() {
		return dpmc;
	}

	public void setDpmc(String dpmc) {
		this.dpmc = dpmc;
	}

	@Length(min=0, max=64, message="客户简称长度必须介于 0 和 64 之间")
	public String getKhjc() {
		return khjc;
	}

	public void setKhjc(String khjc) {
		this.khjc = khjc;
	}

	@ExcelField(title="联系人", align=2, sort=35)
	@Length(min=0, max=64, message="联系人长度必须介于 0 和 64 之间")
	public String getLxr() {
		return lxr;
	}

	public void setLxr(String lxr) {
		this.lxr = lxr;
	}

	@ExcelField(title="详细地址", align=2, sort=30)
	@Length(min=0, max=64, message="地址长度必须介于 0 和 64 之间")
	public String getDz() {
		return dz;
	}

	public void setDz(String dz) {
		this.dz = dz;
	}

	@ExcelField(title="邮编", align=2, sort=40)
	@Length(min=0, max=64, message="邮编长度必须介于 0 和 64 之间")
	public String getYb() {
		return yb;
	}

	public void setYb(String yb) {
		this.yb = yb;
	}
	
	@Length(min=0, max=64, message="电话长度必须介于 0 和 64 之间")
	public String getDh() {
		return dh;
	}

	public void setDh(String dh) {
		this.dh = dh;
	}
	
	@Length(min=0, max=11, message="手机长度必须介于 0 和 11 之间")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@ExcelField(title="电子邮件", align=2, sort=45)
	@Length(min=0, max=64, message="电子邮件长度必须介于 0 和 64 之间")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@ExcelField(title="积分系数", align=2, sort=50)
	@Length(min=0, max=9, message="积分系数长度必须介于 0 和 9 之间")
	public String getJfxs() {
		return jfxs;
	}

	public void setJfxs(String jfxs) {
		this.jfxs = jfxs;
	}
	
	@Length(min=0, max=64, message="开户行名称长度必须介于 0 和 64 之间")
	public String getKhh() {
		return khh;
	}

	public void setKhh(String khh) {
		this.khh = khh;
	}
	
	@Length(min=0, max=64, message="开户行账号长度必须介于 0 和 64 之间")
	public String getKhhzh() {
		return khhzh;
	}

	public void setKhhzh(String khhzh) {
		this.khhzh = khhzh;
	}
	
	@Length(min=0, max=64, message="开户人长度必须介于 0 和 64 之间")
	public String getKhr() {
		return khr;
	}

	public void setKhr(String khr) {
		this.khr = khr;
	}
	
	@Length(min=0, max=640, message="营业执照长度必须介于 0 和 64 之间")
	public String getYyzz() {
		return yyzz;
	}

	public void setYyzz(String yyzz) {
		this.yyzz = yyzz;
	}
	
	@Length(min=0, max=640, message="身份证照长度必须介于 0 和 64 之间")
	public String getSfzz() {
		return sfzz;
	}

	public void setSfzz(String sfzz) {
		this.sfzz = sfzz;
	}
	
	@Length(min=0, max=64, message="经度长度必须介于 0 和 64 之间")
	public String getJd() {
		return jd;
	}

	public void setJd(String jd) {
		this.jd = jd;
	}
	
	@Length(min=0, max=64, message="纬度长度必须介于 0 和 64 之间")
	public String getWd() {
		return wd;
	}

	public void setWd(String wd) {
		this.wd = wd;
	}
	
	@Length(min=0, max=640, message="门店图片长度必须介于 0 和 64 之间")
	public String getMdtp() {
		return mdtp;
	}

	public void setMdtp(String mdtp) {
		this.mdtp = mdtp;
	}


	public CSclass getSclass() {
		return sclass;
	}

	public void setSclass(CSclass sclass) {
		this.sclass = sclass;
	}
	
	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	@ExcelField(title="客户名称", align=2, sort=20)
	@Length(min=1, max=64, message="客户名称长度必须介于 1 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ExcelField(title="审核状态", align=2, sort=55)
	@Length(min=0, max=1, message="客户审核状态长度必须介于 0 和 1 之间")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}