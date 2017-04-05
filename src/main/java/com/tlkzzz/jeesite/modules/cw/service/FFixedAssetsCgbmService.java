/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.cw.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlkzzz.jeesite.common.persistence.Page;
import com.tlkzzz.jeesite.common.service.CrudService;
import com.tlkzzz.jeesite.modules.cw.entity.FFixedAssetsCgbm;
import com.tlkzzz.jeesite.modules.cw.dao.FFixedAssetsCgbmDao;

/**
 * 固定资产采购变卖Service
 * @author xrc
 * @version 2017-04-05
 */
@Service
@Transactional(readOnly = true)
public class FFixedAssetsCgbmService extends CrudService<FFixedAssetsCgbmDao, FFixedAssetsCgbm> {

	public FFixedAssetsCgbm get(String id) {
		return super.get(id);
	}
	
	public List<FFixedAssetsCgbm> findList(FFixedAssetsCgbm fFixedAssetsCgbm) {
		return super.findList(fFixedAssetsCgbm);
	}
	
	public Page<FFixedAssetsCgbm> findPage(Page<FFixedAssetsCgbm> page, FFixedAssetsCgbm fFixedAssetsCgbm) {
		return super.findPage(page, fFixedAssetsCgbm);
	}
	
	@Transactional(readOnly = false)
	public void save(FFixedAssetsCgbm fFixedAssetsCgbm) {
		super.save(fFixedAssetsCgbm);
	}
	
	@Transactional(readOnly = false)
	public void delete(FFixedAssetsCgbm fFixedAssetsCgbm) {
		super.delete(fFixedAssetsCgbm);
	}
	
}