/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlkzzz.jeesite.common.persistence.Page;
import com.tlkzzz.jeesite.common.service.CrudService;
import com.tlkzzz.jeesite.modules.ck.entity.CJdlddk;
import com.tlkzzz.jeesite.modules.ck.dao.CJdlddkDao;

/**
 * 进店离店打卡Service
 * @author szx
 * @version 2017-06-20
 */
@Service
@Transactional(readOnly = true)
public class CJdlddkService extends CrudService<CJdlddkDao, CJdlddk> {

	public CJdlddk get(String id) {
		return super.get(id);
	}
	
	public List<CJdlddk> findList(CJdlddk cJdlddk) {
		return super.findList(cJdlddk);
	}

	public List<CJdlddk> dkfindList(CJdlddk cJdlddk) {
		return dao.dkfindList(cJdlddk);
	}

	public List<CJdlddk> fyfindList(CJdlddk cJdlddk) {
		return dao.fyfindList(cJdlddk);
	}

	public Page<CJdlddk> findPage(Page<CJdlddk> page, CJdlddk cJdlddk) {
		return super.findPage(page, cJdlddk);
	}
	
	@Transactional(readOnly = false)
	public void save(CJdlddk cJdlddk) {
		super.save(cJdlddk);
	}

	@Transactional(readOnly = false)
	public void ldupdate(CJdlddk cJdlddk) {
		dao.ldupdate(cJdlddk);
	}

	@Transactional(readOnly = false)
	public void delete(CJdlddk cJdlddk) {
		super.delete(cJdlddk);
	}
	
}