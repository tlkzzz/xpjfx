/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlkzzz.jeesite.common.persistence.Page;
import com.tlkzzz.jeesite.common.service.CrudService;
import com.tlkzzz.jeesite.common.utils.StringUtils;
import com.tlkzzz.jeesite.modules.ck.entity.CCarUser;
import com.tlkzzz.jeesite.modules.ck.dao.CCarUserDao;

/**
 * 车辆人员表生成Service
 * @author xrc
 * @version 2017-03-13
 */
@Service
@Transactional(readOnly = true)
public class CCarUserService extends CrudService<CCarUserDao, CCarUser> {

	
	public CCarUser get(String id) {
		CCarUser cCarUser = super.get(id);
		return cCarUser;
	}
	
	public List<CCarUser> findList(CCarUser cCarUser) {
		return super.findList(cCarUser);
	}
	
	public Page<CCarUser> findPage(Page<CCarUser> page, CCarUser cCarUser) {
		return super.findPage(page, cCarUser);
	}
	
	@Transactional(readOnly = false)
	public void save(CCarUser cCarUser) {
		super.save(cCarUser);
	}
	
	@Transactional(readOnly = false)
	public void delete(CCarUser cCarUser) {
		super.delete(cCarUser);
	}
	
}