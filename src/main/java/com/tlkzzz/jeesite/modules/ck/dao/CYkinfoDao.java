/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.dao;

import com.tlkzzz.jeesite.common.persistence.CrudDao;
import com.tlkzzz.jeesite.common.persistence.annotation.MyBatisDao;
import com.tlkzzz.jeesite.modules.ck.entity.CYkinfo;

import java.util.List;

/**
 * 移库记录DAO接口
 * @author xrc
 * @version 2017-03-15
 */
@MyBatisDao
public interface CYkinfoDao extends CrudDao<CYkinfo> {

    /**
     * 查询所有订单编号列表（批次）
     * @param cYkinfo
     * @return
     */
    public List<CYkinfo> findOrderCodeList(CYkinfo cYkinfo);
    /**
     * 通过仓库商品分组查询报表信息
     * @param cYkinfo
     * @return
     */
    public List<CYkinfo> findReportList(CYkinfo cYkinfo);
/**
 * APP 分页方法
 * */
    public List<CYkinfo> fyfindList(CYkinfo cYkinfo);
}