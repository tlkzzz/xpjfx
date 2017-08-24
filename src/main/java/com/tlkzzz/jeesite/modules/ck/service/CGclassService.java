/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlkzzz.jeesite.common.service.TreeService;
import com.tlkzzz.jeesite.common.utils.StringUtils;
import com.tlkzzz.jeesite.modules.ck.entity.CGclass;
import com.tlkzzz.jeesite.modules.ck.dao.CGclassDao;

/**
 * 商品分类表生成Service
 * @author xrc
 * @version 2017-03-13
 */
@Service
@Transactional(readOnly = true)
public class CGclassService extends TreeService<CGclassDao, CGclass> {

	public CGclass get(String id) {
		return super.get(id);
	}
	public List<CGclass> getcode(String code) {
		return dao.getcode(code);
	}

	public List<CGclass> findListContainGoodsNum(CGclass cGclass) {
		return dao.findListContainGoodsNum(cGclass);
	}

	public List<CGclass> findList(CGclass cGclass) {
		if (StringUtils.isNotBlank(cGclass.getParentIds())){
			cGclass.setParentIds(","+cGclass.getParentIds()+",");
		}
		return super.findList(cGclass);
	}
	
	@Transactional(readOnly = false)
	public void save(CGclass cGclass) {
		super.save(cGclass);
	}
	
	@Transactional(readOnly = false)
	public void delete(CGclass cGclass) {
		super.delete(cGclass);
	}
	
}