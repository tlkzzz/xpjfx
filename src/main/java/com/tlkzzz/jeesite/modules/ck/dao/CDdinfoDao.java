/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.dao;

import com.tlkzzz.jeesite.common.persistence.CrudDao;
import com.tlkzzz.jeesite.common.persistence.annotation.MyBatisDao;
import com.tlkzzz.jeesite.modules.ck.entity.CDdinfo;

/**
 * 订单DAO接口
 * @author xrc
 * @version 2017-03-27
 */
@MyBatisDao
public interface CDdinfoDao extends CrudDao<CDdinfo> {
    /**
     * 保存采购统计表ID
     * @param cDdinfo
     */
    public void updateCgzbInfo(CDdinfo cDdinfo);
}