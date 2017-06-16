/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlkzzz.jeesite.common.persistence.Page;
import com.tlkzzz.jeesite.common.service.CrudService;
import com.tlkzzz.jeesite.modules.ck.entity.CXsddls;
import com.tlkzzz.jeesite.modules.ck.dao.CXsddlsDao;

/**
 * 销售订单临时表Service
 * @author szx
 * @version 2017-05-16
 */
@Service
@Transactional(readOnly = true)
public class CXsddlsService extends CrudService<CXsddlsDao, CXsddls> {

	public CXsddls get(String id) {
		return super.get(id);
	}
	
	public List<CXsddls> findList(CXsddls cXsddls) {
		return super.findList(cXsddls);
	}
	
	public Page<CXsddls> findPage(Page<CXsddls> page, CXsddls cXsddls) {
		return super.findPage(page, cXsddls);
	}
	
	@Transactional(readOnly = false)
	public void save(CXsddls cXsddls) {
		super.save(cXsddls);
	}
	
	@Transactional(readOnly = false)
	public void delete(CXsddls cXsddls) {
		super.delete(cXsddls);
	}

	@Transactional(readOnly = false)
	public void stateUpdate(CXsddls cXsddls){dao.stateUpdate(cXsddls);}

	public List<CXsddls> fyfindList(CXsddls cXsddls){return dao.fyfindList(cXsddls);}
}