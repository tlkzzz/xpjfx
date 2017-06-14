/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.p.entity;

import com.tlkzzz.jeesite.modules.ck.entity.CGoods;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.tlkzzz.jeesite.common.persistence.DataEntity;

/**
 * 商品信息附加表Entity
 * @author xrc
 * @version 2017-05-31
 */
public class ShopGoods extends DataEntity<ShopGoods> {
	
	private static final long serialVersionUID = 1L;
	private String goodsSubtitle;		// 商品副标题
	private String shopId;		// 店铺id
	private String shopName;		// 商城商品名称
	private String goodsImage;		// 商品默认封面图片
	private String goodsImageMore;		// 商品多图
	private String goodsShow;		// 商品上架1上架0下架2定时上架
	private String goodsClick;		// 商品浏览数
	private String goodsState;		// 商品状态，0开启，1违规下架
	private String goodsCommend;		// 商品推荐
	private String goodsBurden;		// 配料列表
	private String goodsPlace;		// 产地
	private String goodsOriginPlace;		// 原料产地
	private String goodsShelfLife;		// 保质期
	private String goodsKeywords;		// 商品关键字
	private String goodsDescription;		// 商品描述
	private String goodsBody;		// 商品详细内容
	private String goodsAttr;		// 商品属性
	private String goodsSpec;		// 商品规格
	private String goodsColImg;		// 颜色自定义图片
	private Date startTime;		// 促销开始时间
	private Date endTime;		// 促销结束时间
	private String goodsForm;		// 商品类型,1为全新、2为二手
	private String transportId;		// 运费模板ID，不使用运费模板值为NULL
	private Double pyPrice;		// 平邮
	private Double kdPrice;		// 快递
	private Double esPrice;		// EMS
	private String goodsCloseReason;		// 商品违规下架原因
	private String goodsStoreState;		// 商品所在店铺状态 0开启 1关闭
	private String commentnum;		// 评论次数
	private String salenum;		// 售出数量
	private String goodsCollect;		// 商品收藏数量
	private String goodsTransfeeCharge;		// 商品运费承担方式 默认 0为买家承担 1为卖家承担
	private String storeClassId;		// 店铺自定义分类id
	private Double goodsMarketPrice;		// 市场价
	private Double goodsStorePrice;		// 商品店铺价格(促销价)
	private Double goodsCostPrice;		// 成本价
	private String staticUrl;		// 静态url
	private String dynameicUrl;		// 动态url
	private String realUrl;		// 真实url
	private String isCode;		// 是否删除 0:需要发码  1:不需要发码
	private String goodsCommissionRate;		// 佣金比例
	private String goodsCloseInfo;		// 拒绝信息:产品未通过审核时的信息

	private CGoods cGoods;			//分销商品信息
	
	public ShopGoods() {
		super();
	}

	public ShopGoods(String id){
		super(id);
	}

	@Length(min=0, max=200, message="商品副标题长度必须介于 0 和 200 之间")
	public String getGoodsSubtitle() {
		return goodsSubtitle;
	}

	public void setGoodsSubtitle(String goodsSubtitle) {
		this.goodsSubtitle = goodsSubtitle;
	}
	
	@Length(min=0, max=64, message="店铺id长度必须介于 0 和 64 之间")
	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	
	@Length(min=0, max=100, message="商城商品名称长度必须介于 0 和 100 之间")
	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	
	@Length(min=0, max=100, message="商品默认封面图片长度必须介于 0 和 100 之间")
	public String getGoodsImage() {
		return goodsImage;
	}

	public void setGoodsImage(String goodsImage) {
		this.goodsImage = goodsImage;
	}
	
	public String getGoodsImageMore() {
		return goodsImageMore;
	}

	public void setGoodsImageMore(String goodsImageMore) {
		this.goodsImageMore = goodsImageMore;
	}
	
	@Length(min=1, max=1, message="商品上架1上架0下架2定时上架长度必须介于 1 和 1 之间")
	public String getGoodsShow() {
		return goodsShow;
	}

	public void setGoodsShow(String goodsShow) {
		this.goodsShow = goodsShow;
	}
	
	@Length(min=1, max=11, message="商品浏览数长度必须介于 1 和 11 之间")
	public String getGoodsClick() {
		return goodsClick;
	}

	public void setGoodsClick(String goodsClick) {
		this.goodsClick = goodsClick;
	}
	
	@Length(min=1, max=1, message="商品状态，0开启，1违规下架长度必须介于 1 和 1 之间")
	public String getGoodsState() {
		return goodsState;
	}

	public void setGoodsState(String goodsState) {
		this.goodsState = goodsState;
	}
	
	@Length(min=1, max=1, message="商品推荐长度必须介于 1 和 1 之间")
	public String getGoodsCommend() {
		return goodsCommend;
	}

	public void setGoodsCommend(String goodsCommend) {
		this.goodsCommend = goodsCommend;
	}
	
	@Length(min=0, max=255, message="配料列表长度必须介于 0 和 255 之间")
	public String getGoodsBurden() {
		return goodsBurden;
	}

	public void setGoodsBurden(String goodsBurden) {
		this.goodsBurden = goodsBurden;
	}
	
	@Length(min=0, max=255, message="产地长度必须介于 0 和 255 之间")
	public String getGoodsPlace() {
		return goodsPlace;
	}

	public void setGoodsPlace(String goodsPlace) {
		this.goodsPlace = goodsPlace;
	}
	
	@Length(min=0, max=255, message="原料产地长度必须介于 0 和 255 之间")
	public String getGoodsOriginPlace() {
		return goodsOriginPlace;
	}

	public void setGoodsOriginPlace(String goodsOriginPlace) {
		this.goodsOriginPlace = goodsOriginPlace;
	}
	
	@Length(min=0, max=11, message="保质期长度必须介于 0 和 11 之间")
	public String getGoodsShelfLife() {
		return goodsShelfLife;
	}

	public void setGoodsShelfLife(String goodsShelfLife) {
		this.goodsShelfLife = goodsShelfLife;
	}
	
	@Length(min=0, max=255, message="商品关键字长度必须介于 0 和 255 之间")
	public String getGoodsKeywords() {
		return goodsKeywords;
	}

	public void setGoodsKeywords(String goodsKeywords) {
		this.goodsKeywords = goodsKeywords;
	}
	
	@Length(min=0, max=255, message="商品描述长度必须介于 0 和 255 之间")
	public String getGoodsDescription() {
		return goodsDescription;
	}

	public void setGoodsDescription(String goodsDescription) {
		this.goodsDescription = goodsDescription;
	}
	
	public String getGoodsBody() {
		return goodsBody;
	}

	public void setGoodsBody(String goodsBody) {
		this.goodsBody = goodsBody;
	}
	
	public String getGoodsAttr() {
		return goodsAttr;
	}

	public void setGoodsAttr(String goodsAttr) {
		this.goodsAttr = goodsAttr;
	}
	
	public String getGoodsSpec() {
		return goodsSpec;
	}

	public void setGoodsSpec(String goodsSpec) {
		this.goodsSpec = goodsSpec;
	}
	
	public String getGoodsColImg() {
		return goodsColImg;
	}

	public void setGoodsColImg(String goodsColImg) {
		this.goodsColImg = goodsColImg;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	@Length(min=1, max=1, message="商品类型,1为全新、2为二手长度必须介于 1 和 1 之间")
	public String getGoodsForm() {
		return goodsForm;
	}

	public void setGoodsForm(String goodsForm) {
		this.goodsForm = goodsForm;
	}
	
	@Length(min=0, max=64, message="运费模板ID，不使用运费模板值为NULL长度必须介于 0 和 64 之间")
	public String getTransportId() {
		return transportId;
	}

	public void setTransportId(String transportId) {
		this.transportId = transportId;
	}
	
	public Double getPyPrice() {
		return pyPrice;
	}

	public void setPyPrice(Double pyPrice) {
		this.pyPrice = pyPrice;
	}
	
	public Double getKdPrice() {
		return kdPrice;
	}

	public void setKdPrice(Double kdPrice) {
		this.kdPrice = kdPrice;
	}
	
	public Double getEsPrice() {
		return esPrice;
	}

	public void setEsPrice(Double esPrice) {
		this.esPrice = esPrice;
	}
	
	@Length(min=0, max=255, message="商品违规下架原因长度必须介于 0 和 255 之间")
	public String getGoodsCloseReason() {
		return goodsCloseReason;
	}

	public void setGoodsCloseReason(String goodsCloseReason) {
		this.goodsCloseReason = goodsCloseReason;
	}
	
	@Length(min=1, max=1, message="商品所在店铺状态 0开启 1关闭长度必须介于 1 和 1 之间")
	public String getGoodsStoreState() {
		return goodsStoreState;
	}

	public void setGoodsStoreState(String goodsStoreState) {
		this.goodsStoreState = goodsStoreState;
	}
	
	@Length(min=1, max=10, message="评论次数长度必须介于 1 和 10 之间")
	public String getCommentnum() {
		return commentnum;
	}

	public void setCommentnum(String commentnum) {
		this.commentnum = commentnum;
	}
	
	@Length(min=1, max=10, message="售出数量长度必须介于 1 和 10 之间")
	public String getSalenum() {
		return salenum;
	}

	public void setSalenum(String salenum) {
		this.salenum = salenum;
	}
	
	@Length(min=1, max=10, message="商品收藏数量长度必须介于 1 和 10 之间")
	public String getGoodsCollect() {
		return goodsCollect;
	}

	public void setGoodsCollect(String goodsCollect) {
		this.goodsCollect = goodsCollect;
	}
	
	@Length(min=1, max=1, message="商品运费承担方式 默认 0为买家承担 1为卖家承担长度必须介于 1 和 1 之间")
	public String getGoodsTransfeeCharge() {
		return goodsTransfeeCharge;
	}

	public void setGoodsTransfeeCharge(String goodsTransfeeCharge) {
		this.goodsTransfeeCharge = goodsTransfeeCharge;
	}
	
	@Length(min=0, max=64, message="店铺自定义分类id长度必须介于 0 和 64 之间")
	public String getStoreClassId() {
		return storeClassId;
	}

	public void setStoreClassId(String storeClassId) {
		this.storeClassId = storeClassId;
	}
	
	public Double getGoodsMarketPrice() {
		return goodsMarketPrice;
	}

	public void setGoodsMarketPrice(Double goodsMarketPrice) {
		this.goodsMarketPrice = goodsMarketPrice;
	}
	
	public Double getGoodsStorePrice() {
		return goodsStorePrice;
	}

	public void setGoodsStorePrice(Double goodsStorePrice) {
		this.goodsStorePrice = goodsStorePrice;
	}
	
	public Double getGoodsCostPrice() {
		return goodsCostPrice;
	}

	public void setGoodsCostPrice(Double goodsCostPrice) {
		this.goodsCostPrice = goodsCostPrice;
	}
	
	@Length(min=0, max=255, message="静态url长度必须介于 0 和 255 之间")
	public String getStaticUrl() {
		return staticUrl;
	}

	public void setStaticUrl(String staticUrl) {
		this.staticUrl = staticUrl;
	}
	
	@Length(min=0, max=255, message="动态url长度必须介于 0 和 255 之间")
	public String getDynameicUrl() {
		return dynameicUrl;
	}

	public void setDynameicUrl(String dynameicUrl) {
		this.dynameicUrl = dynameicUrl;
	}
	
	@Length(min=0, max=255, message="真实url长度必须介于 0 和 255 之间")
	public String getRealUrl() {
		return realUrl;
	}

	public void setRealUrl(String realUrl) {
		this.realUrl = realUrl;
	}
	
	@Length(min=0, max=2, message="是否删除 0:需要发码  1:不需要发码长度必须介于 0 和 2 之间")
	public String getIsCode() {
		return isCode;
	}

	public void setIsCode(String isCode) {
		this.isCode = isCode;
	}
	
	public String getGoodsCommissionRate() {
		return goodsCommissionRate;
	}

	public void setGoodsCommissionRate(String goodsCommissionRate) {
		this.goodsCommissionRate = goodsCommissionRate;
	}
	
	@Length(min=0, max=255, message="拒绝信息:产品未通过审核时的信息长度必须介于 0 和 255 之间")
	public String getGoodsCloseInfo() {
		return goodsCloseInfo;
	}

	public void setGoodsCloseInfo(String goodsCloseInfo) {
		this.goodsCloseInfo = goodsCloseInfo;
	}

	public CGoods getcGoods() {
		return cGoods;
	}

	public void setcGoods(CGoods cGoods) {
		this.cGoods = cGoods;
	}
}