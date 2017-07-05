/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.service;

import java.util.List;

import com.tlkzzz.jeesite.modules.ck.dao.CCgzbinfoDao;
import com.tlkzzz.jeesite.modules.ck.entity.CCgzbinfo;
import com.tlkzzz.jeesite.modules.ck.entity.CGoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlkzzz.jeesite.common.persistence.Page;
import com.tlkzzz.jeesite.common.service.CrudService;
import com.tlkzzz.jeesite.modules.ck.entity.CCgsqRecord;
import com.tlkzzz.jeesite.modules.ck.dao.CCgsqRecordDao;

/**
 * 订单申请记录表Service
 * @author xlc
 * @version 2017-06-28
 */
@Service
@Transactional(readOnly = true)
public class CCgsqRecordService extends CrudService<CCgsqRecordDao, CCgsqRecord> {


	@Autowired
	private CCgzbinfoDao cCgzbinfoDao;

	public CCgsqRecord get(String id) {
		return super.get(id);
	}

	public CCgsqRecord getid(String goodsid) {

		return dao.getid(goodsid);
	}
	
	public List<CCgsqRecord> findList(CCgsqRecord cCgsqRecord) {
		return super.findList(cCgsqRecord);
	}

	public List<CCgsqRecord> listgoods(CCgsqRecord cCgsqRecord) {

		return dao.listgoods(cCgsqRecord);
	}
	public Page<CCgsqRecord> findPage(Page<CCgsqRecord> page, CCgsqRecord cCgsqRecord) {
		return super.findPage(page, cCgsqRecord);
	}
	
	@Transactional(readOnly = false)
	public void save(CCgsqRecord cCgsqRecord) {
		super.save(cCgsqRecord);
	}

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void savelist(CCgsqRecord cCgsqRecord) {
		super.save(cCgsqRecord);
		CCgzbinfo cCgzbinfo = cCgzbinfoDao.getZbByGoods(cCgsqRecord.getGoods().getId());
		if(cCgzbinfo==null) {
			cCgzbinfo =new CCgzbinfo();
			cCgzbinfo.preInsert();
			cCgzbinfo.setGoods(cCgsqRecord.getGoods());
			cCgzbinfo.setNub(String.valueOf(cCgsqRecord.getNub()));
			cCgzbinfo.setRemarks(cCgsqRecord.getRemarks());
			cCgzbinfoDao.insert(cCgzbinfo);
		}else {
			cCgzbinfo.preUpdate();
			cCgzbinfo.setGoods(cCgsqRecord.getGoods());
			cCgzbinfo.setNub(String.valueOf(cCgsqRecord.getNub()+Integer.parseInt(cCgzbinfo.getNub())));
			cCgzbinfo.setRemarks(cCgsqRecord.getRemarks());
			cCgzbinfoDao.update(cCgzbinfo);
		}
	}

	@Transactional(readOnly = false)
	public void delete(CCgsqRecord cCgsqRecord) {
		super.delete(cCgsqRecord);
	}
	
}