/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.test.service;

import java.util.ArrayList;
import java.util.List;

import com.tlkzzz.jeesite.common.utils.IdGen;
import com.tlkzzz.jeesite.modules.sys.utils.ToolsUtils;
import com.tlkzzz.jeesite.modules.test.entity.ShopWxgoods;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlkzzz.jeesite.common.persistence.Page;
import com.tlkzzz.jeesite.common.service.CrudService;
import com.tlkzzz.jeesite.modules.test.entity.ShopWxpacket;
import com.tlkzzz.jeesite.modules.test.dao.ShopWxpacketDao;

import javax.servlet.http.HttpServletResponse;

/**
 * 红包Service
 * @author xrc
 * @version 2017-03-20
 */
@Service
@Transactional(readOnly = true)
public class ShopWxpacketService extends CrudService<ShopWxpacketDao, ShopWxpacket> {

	public ShopWxpacket get(String id) {
		return super.get(id);
	}
	
	public List<ShopWxpacket> findList(ShopWxpacket shopWxpacket) {
		return super.findList(shopWxpacket);
	}
	
	public Page<ShopWxpacket> findPage(Page<ShopWxpacket> page, ShopWxpacket shopWxpacket) {
		return super.findPage(page, shopWxpacket);
	}

	public void exportExcel(ShopWxpacket shopWxpacket, HttpServletResponse response){
		 List<ShopWxpacket> list = super.findList(shopWxpacket);
		 List<String[]> strList = new ArrayList<String[]>();
		 String[] title = {"名称","销售区域","活动名称","红包编号","二维码链接"};
		 for(ShopWxpacket sw:list){
			String[] str = {sw.getGoods().getName(),sw.getGoods().getXsqy(),sw.getGoods().getHdmc(),
			sw.getSpbh(),"http://www.ubisp.com/weixin/authors?p="+sw.getSjzfc()};
			strList.add(str);
		 }
		 ToolsUtils.exportExcel(response,"红包列表",title,strList);
	}
	
	@Transactional(readOnly = false)
	public void save(ShopWxpacket shopWxpacket) {
		super.save(shopWxpacket);
	}

	@Transactional(readOnly = false)
	public void createSave(ShopWxgoods shopWxgoods) {
		if(shopWxgoods.getNum()>0) {
			for(int i=0;i<shopWxgoods.getNum();i++) {
				ShopWxpacket shopWxpacket = new ShopWxpacket();
				shopWxpacket.setNb("0");
				shopWxpacket.setState("0");
				shopWxpacket.setGoods(shopWxgoods);
				shopWxpacket.setCjsj(shopWxgoods.getCjsj());
				shopWxpacket.setSpbh(ToolsUtils.randomGoodsNO(7));
				shopWxpacket.setSjzfc(IdGen.randomBase62(32));
				super.save(shopWxpacket);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(ShopWxpacket shopWxpacket) {
		super.delete(shopWxpacket);
	}

	/**
	 * 通过微信红包主表ID删除红包表中所有还未扫描过的红包
	 * @param goodsID
	 */
	@Transactional(readOnly = false)
	public void deleteBygoods(String goodsID) {
		dao.deleteBygoods(goodsID);
	}
	
}