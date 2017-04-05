/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.cw.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlkzzz.jeesite.common.persistence.Page;
import com.tlkzzz.jeesite.common.service.CrudService;
import com.tlkzzz.jeesite.modules.cw.entity.FFixedAssets;
import com.tlkzzz.jeesite.modules.cw.dao.FFixedAssetsDao;

/**
 * 固定资产登记Service
 * @author xrc
 * @version 2017-04-05
 */
@Service
@Transactional(readOnly = true)
public class FFixedAssetsService extends CrudService<FFixedAssetsDao, FFixedAssets> {

	public FFixedAssets get(String id) {
		return super.get(id);
	}
	
	public List<FFixedAssets> findList(FFixedAssets fFixedAssets) {
		return super.findList(fFixedAssets);
	}
	
	public Page<FFixedAssets> findPage(Page<FFixedAssets> page, FFixedAssets fFixedAssets) {
		return super.findPage(page, fFixedAssets);
	}
	
	@Transactional(readOnly = false)
	public void save(FFixedAssets fFixedAssets) {
		super.save(fFixedAssets);
	}
	
	@Transactional(readOnly = false)
	public void delete(FFixedAssets fFixedAssets) {
		super.delete(fFixedAssets);
	}
	
}