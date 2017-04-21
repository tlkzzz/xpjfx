/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.dao;

import com.tlkzzz.jeesite.common.persistence.CrudDao;
import com.tlkzzz.jeesite.common.persistence.annotation.MyBatisDao;
import com.tlkzzz.jeesite.modules.ck.entity.CHouseComponentCheck;

import java.util.List;

/**
 * 分量盘点DAO接口
 * @author xrc
 * @version 2017-04-12
 */
@MyBatisDao
public interface CHouseComponentCheckDao extends CrudDao<CHouseComponentCheck> {
    /**
     * 通过仓库ID查询分量盘点对象列表
     * @param cHouseComponentCheck
     * @return
     */
    public List<CHouseComponentCheck> getByHouse(CHouseComponentCheck cHouseComponentCheck);

    /**
     * 通过仓库ID修改所有该仓库的分量盘点状态
     * @param cHouseComponentCheck
     */
    public void updateStateByHouse(CHouseComponentCheck cHouseComponentCheck);
}