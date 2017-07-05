/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.dao;

import com.tlkzzz.jeesite.common.persistence.CrudDao;
import com.tlkzzz.jeesite.common.persistence.annotation.MyBatisDao;
import com.tlkzzz.jeesite.modules.ck.entity.CCgsqRecord;
import com.tlkzzz.jeesite.modules.ck.entity.CGoods;

import java.util.List;

/**
 * 订单申请记录表DAO接口
 * @author xlc
 * @version 2017-06-28
 */
@MyBatisDao
public interface CCgsqRecordDao extends CrudDao<CCgsqRecord> {

    /**
     * 根据商品id获取全部数据
     * @param goodsid
     * @return
     */
    public CCgsqRecord getid(String goodsid);
    /**
     * 根据商品id获取全部商品
     * @param cCgsqRecord
     * @return
     */
    public List<CCgsqRecord> listgoods(CCgsqRecord cCgsqRecord);
}