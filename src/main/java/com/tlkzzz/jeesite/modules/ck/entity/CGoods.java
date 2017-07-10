/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.entity;

import com.tlkzzz.jeesite.modules.p.entity.ShopGoods;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.tlkzzz.jeesite.common.persistence.DataEntity;

/**
 * 商品生成Entity
 * @author xrc
 * @version 2017-03-13
 */
public class CGoods extends DataEntity<CGoods> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 商品名称
	private CUnit big;		// 大
	private CUnit zong;		// 中
	private CUnit small;		// 小
	private String sptp;		// 商品图片
	private String sort;		// 商品编码
	private String bzq;		// 保质期
	private String spbm;		// 商品别名
	private CBands bands;		// 品牌
	private String tm;		// 条码
	private String cd;		// 产地
	private String sccs;		// 生产厂商
	private String pzwh;		// 批准文号
	private String zjbgh;		// 质检报告号
	private String spxkz;		// 食品卫生许可证
	private String gycpxkz;		// 全国工业生产产品许可证
	private Date scxkz;		// 生产许可证有效期
	private CSupplier supplier;		// 供应商
	private String houid;		// 仓库ID
	private CGclass gclass;		// 商品分类ID
	private CUnit unit;		// 单位
	private CSpec spec;		// 规格
	private String cbj;		// 成本价
	private String sj;		// 售价
	private String yjsj;    //预警售价
	private String ckcbj;   //参考成本价

	private ShopGoods shopGoods;	//商城商品信息
	private String kykc;		//可用库存（从库存中查询）
	private String aqkc;		//安全库存（从库存中查询）


	public String getYjsj() {
		return yjsj;
	}

	public void setYjsj(String yjsj) {
		this.yjsj = yjsj;
	}

	public String getCkcbj() {
		return ckcbj;
	}

	public void setCkcbj(String ckcbj) {
		this.ckcbj = ckcbj;
	}

	public CGoods() {
		super();
	}

	public CGoods(String id){
		super(id);
	}

	@Length(min=1, max=64, message="商品名称长度必须介于 1 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public CUnit getBig() {
		return big;
	}

	public void setBig(CUnit big) {
		this.big = big;
	}
	
	public CUnit getZong() {
		return zong;
	}

	public void setZong(CUnit zong) {
		this.zong = zong;
	}
	
	public CUnit getSmall() {
		return small;
	}

	public void setSmall(CUnit small) {
		this.small = small;
	}
	
	@Length(min=0, max=640, message="商品图片长度必须介于 0 和 640 之间")
	public String getSptp() {
		return sptp;
	}

	public void setSptp(String sptp) {
		this.sptp = sptp;
	}
	
	@Length(min=0, max=64, message="商品编码长度必须介于 0 和 64 之间")
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
	
	@Length(min=0, max=64, message="保质期长度必须介于 0 和 64 之间")
	public String getBzq() {
		return bzq;
	}

	public void setBzq(String bzq) {
		this.bzq = bzq;
	}
	
	@Length(min=0, max=64, message="商品别名长度必须介于 0 和 64 之间")
	public String getSpbm() {
		return spbm;
	}

	public void setSpbm(String spbm) {
		this.spbm = spbm;
	}
	
	public CBands getBands() {
		return bands;
	}

	public void setBands(CBands bands) {
		this.bands = bands;
	}
	
	@Length(min=0, max=64, message="条码长度必须介于 0 和 64 之间")
	public String getTm() {
		return tm;
	}

	public void setTm(String tm) {
		this.tm = tm;
	}
	
	@Length(min=0, max=64, message="产地长度必须介于 0 和 64 之间")
	public String getCd() {
		return cd;
	}

	public void setCd(String cd) {
		this.cd = cd;
	}

	@Length(min=0, max=64, message="生产厂商长度必须介于 0 和 64 之间")
	public String getSccs() {
		return sccs;
	}

	public void setSccs(String sccs) {
		this.sccs = sccs;
	}
	
	@Length(min=0, max=64, message="批准文号长度必须介于 0 和 64 之间")
	public String getPzwh() {
		return pzwh;
	}

	public void setPzwh(String pzwh) {
		this.pzwh = pzwh;
	}
	
	@Length(min=0, max=64, message="质检报告号长度必须介于 0 和 64 之间")
	public String getZjbgh() {
		return zjbgh;
	}

	public void setZjbgh(String zjbgh) {
		this.zjbgh = zjbgh;
	}
	
	@Length(min=0, max=64, message="食品卫生许可证长度必须介于 0 和 64 之间")
	public String getSpxkz() {
		return spxkz;
	}

	public void setSpxkz(String spxkz) {
		this.spxkz = spxkz;
	}
	
	@Length(min=0, max=64, message="全国工业生产产品许可证长度必须介于 0 和 64 之间")
	public String getGycpxkz() {
		return gycpxkz;
	}

	public void setGycpxkz(String gycpxkz) {
		this.gycpxkz = gycpxkz;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getScxkz() {
		return scxkz;
	}

	public void setScxkz(Date scxkz) {
		this.scxkz = scxkz;
	}
	
	public CSupplier getSupplier() {
		return supplier;
	}

	public void setSupplier(CSupplier supplier) {
		this.supplier = supplier;
	}
	
	@Length(min=0, max=64, message="仓库ID长度必须介于 0 和 64 之间")
	public String getHouid() {
		return houid;
	}

	public void setHouid(String houid) {
		this.houid = houid;
	}
	
	public CGclass getGclass() {
		return gclass;
	}

	public void setGclass(CGclass gclass) {
		this.gclass = gclass;
	}
	
	public CUnit getUnit() {
		return unit;
	}

	public void setUnit(CUnit unit) {
		this.unit = unit;
	}
	
	public CSpec getSpec() {
		return spec;
	}

	public void setSpec(CSpec spec) {
		this.spec = spec;
	}
	
	public String getCbj() {
		return cbj;
	}

	public void setCbj(String cbj) {
		this.cbj = cbj;
	}
	
	public String getSj() {
		return sj;
	}

	public void setSj(String sj) {
		this.sj = sj;
	}

	public String getKykc() {
		return kykc;
	}

	public void setKykc(String kykc) {
		this.kykc = kykc;
	}

	public String getAqkc() {
		return aqkc;
	}

	public void setAqkc(String aqkc) {
		this.aqkc = aqkc;
	}

	public ShopGoods getShopGoods() {
		return shopGoods;
	}

	public void setShopGoods(ShopGoods shopGoods) {
		this.shopGoods = shopGoods;
	}
}