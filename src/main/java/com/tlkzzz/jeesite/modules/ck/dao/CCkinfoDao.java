/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.dao;

import com.tlkzzz.jeesite.common.persistence.CrudDao;
import com.tlkzzz.jeesite.common.persistence.annotation.MyBatisDao;
import com.tlkzzz.jeesite.modules.ck.entity.CCkinfo;

import java.util.List;

/**
 * 出库信息DAO接口
 * @author xrc
 * @version 2017-03-21
 */
@MyBatisDao
public interface CCkinfoDao extends CrudDao<CCkinfo> {
    public List<CCkinfo> findListStore(CCkinfo cCkinfo);

    /*
    品牌
     */

    public List<CCkinfo> findListBands(CCkinfo cCkinfo);


    public List<CCkinfo> lrlist(CCkinfo cCkinfo);
}