/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.service;

import java.text.DecimalFormat;
import java.util.List;

import com.tlkzzz.jeesite.common.utils.StringUtils;
import com.tlkzzz.jeesite.modules.ck.entity.CUnit;
import com.tlkzzz.jeesite.modules.sys.utils.ToolsUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlkzzz.jeesite.common.persistence.Page;
import com.tlkzzz.jeesite.common.service.CrudService;
import com.tlkzzz.jeesite.modules.ck.entity.CGoods;
import com.tlkzzz.jeesite.modules.ck.dao.CGoodsDao;

/**
 * 商品生成Service
 * @author xrc
 * @version 2017-03-13
 */
@Service
@Transactional(readOnly = true)
public class CGoodsService extends CrudService<CGoodsDao, CGoods> {

	public CGoods get(String id) {
		return super.get(id);
	}

	public List<CGoods> getsort(String sort) {
		return dao.getsort(sort);
	}

	public List<CGoods> findList(CGoods cGoods) {
		return super.findList(cGoods);
	}

	public List<CGoods> GoodsFind(CGoods cGoods) {
		return dao.GoodsFind(cGoods);
	}

	public Page<CGoods> findPage(Page<CGoods> page, CGoods cGoods) {
		page = super.findPage(page, cGoods);
		DecimalFormat df = new DecimalFormat("#.####");
		for(CGoods c:page.getList()){
			c.setUnit(new CUnit());
			String[] unit = {c.getBig().getName(),c.getZong().getName(),c.getSmall().getName()};
			c.getUnit().setName(ToolsUtils.unitTools(c.getSpec().getName(),unit));
			if(StringUtils.isNotBlank(c.getCbj()))c.setCbj(df.format(Double.parseDouble(c.getCbj())));
			if(StringUtils.isNotBlank(c.getSj()))c.setSj(df.format(Double.parseDouble(c.getSj())));
			if(StringUtils.isNotBlank(c.getYjsj()))c.setYjsj(df.format(Double.parseDouble(c.getYjsj())));
			if(StringUtils.isNotBlank(c.getCkcbj()))c.setCkcbj(df.format(Double.parseDouble(c.getCkcbj())));
		}
		return page;
	}
	
	@Transactional(readOnly = false)
	public void save(CGoods cGoods) {
		super.save(cGoods);
	}
	
	@Transactional(readOnly = false)
	public void delete(CGoods cGoods) {
		super.delete(cGoods);
	}
	
}