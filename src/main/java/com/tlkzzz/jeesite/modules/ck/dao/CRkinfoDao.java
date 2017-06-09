/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.dao;

import com.tlkzzz.jeesite.common.persistence.CrudDao;
import com.tlkzzz.jeesite.common.persistence.annotation.MyBatisDao;
import com.tlkzzz.jeesite.modules.ck.entity.CRkinfo;

import java.util.List;

/**
 * 入库记录DAO接口
 * @author xrc
 * @version 2017-03-15
 */
@MyBatisDao
public interface CRkinfoDao extends CrudDao<CRkinfo> {
    /**
     * 查询报表商品汇总数据
     * @param cRkinfo
     * @return
     */
	public List<CRkinfo> findReportList(CRkinfo cRkinfo);

	public List<CRkinfo> fyfindList(CRkinfo cRkinfo);
}