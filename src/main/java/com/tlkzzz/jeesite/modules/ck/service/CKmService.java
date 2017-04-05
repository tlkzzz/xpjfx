/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlkzzz.jeesite.common.service.TreeService;
import com.tlkzzz.jeesite.common.utils.StringUtils;
import com.tlkzzz.jeesite.modules.ck.entity.CKm;
import com.tlkzzz.jeesite.modules.ck.dao.CKmDao;

/**
 * 科目类别表Service
 * @author szx
 * @version 2017-04-05
 */
@Service
@Transactional(readOnly = true)
public class CKmService extends TreeService<CKmDao, CKm> {

	public CKm get(String id) {
		return super.get(id);
	}
	
	public List<CKm> findList(CKm cKm) {
		if (StringUtils.isNotBlank(cKm.getParentIds())){
			cKm.setParentIds(","+cKm.getParentIds()+",");
		}
		return super.findList(cKm);
	}
	
	@Transactional(readOnly = false)
	public void save(CKm cKm) {
		super.save(cKm);
	}
	
	@Transactional(readOnly = false)
	public void delete(CKm cKm) {
		super.delete(cKm);
	}
	
}