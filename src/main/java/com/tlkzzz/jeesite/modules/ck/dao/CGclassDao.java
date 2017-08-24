/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.dao;

import com.tlkzzz.jeesite.common.persistence.TreeDao;
import com.tlkzzz.jeesite.common.persistence.annotation.MyBatisDao;
import com.tlkzzz.jeesite.modules.ck.entity.CGclass;

import java.util.List;

/**
 * 商品分类表生成DAO接口
 * @author xrc
 * @version 2017-03-13
 */
@MyBatisDao
public interface CGclassDao extends TreeDao<CGclass> {
	public List<CGclass> getcode(String code);

	/**
	 * t通过父ID查询子列表,其中sort中包含商品数量
	 * @param cGclass
	 * @return
	 */
	public List<CGclass> findListContainGoodsNum(CGclass cGclass);
}