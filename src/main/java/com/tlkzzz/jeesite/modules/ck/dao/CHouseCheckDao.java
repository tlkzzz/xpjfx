/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.dao;

import com.tlkzzz.jeesite.common.persistence.CrudDao;
import com.tlkzzz.jeesite.common.persistence.annotation.MyBatisDao;
import com.tlkzzz.jeesite.modules.ck.entity.CHouseCheck;

/**
 * 仓库总盘点DAO接口
 * @author xrc
 * @version 2017-04-12
 */
@MyBatisDao
public interface CHouseCheckDao extends CrudDao<CHouseCheck> {
    /**
     * 通过仓库ID查询总量盘点对象
     * @param cHouseCheck
     * @return
     */
    public CHouseCheck getByHouse(CHouseCheck  cHouseCheck);
}