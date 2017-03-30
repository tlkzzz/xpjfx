/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.ck.service;

import java.util.ArrayList;
import java.util.List;

import com.tlkzzz.jeesite.common.utils.StringUtils;
import com.tlkzzz.jeesite.modules.ck.entity.CSclass;
import com.tlkzzz.jeesite.modules.ck.entity.CSupplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlkzzz.jeesite.common.persistence.Page;
import com.tlkzzz.jeesite.common.service.CrudService;
import com.tlkzzz.jeesite.modules.ck.entity.CStore;
import com.tlkzzz.jeesite.modules.ck.dao.CStoreDao;

/**
 * 客户表Service
 * @author xrc
 * @version 2017-03-13
 */
@Service
@Transactional(readOnly = true)
public class CStoreService extends CrudService<CStoreDao, CStore> {

	@Autowired
	private CSclassService cSclassService;

	public CStore get(String id) {
		return super.get(id);
	}

	public List<CStore> findList(CStore cStore) {
		return super.findList(cStore);
	}

	public Page<CStore> findPage(Page<CStore> page, CStore cStore) {
		return super.findPage(page, cStore);
	}

	@Transactional(readOnly = false)
	public void save(CStore cStore) {
		super.save(cStore);
	}

	@Transactional(readOnly = false)
	public void delete(CStore cStore) {
		super.delete(cStore);
	}

	public Page<CStore> findUser(Page<CStore> page, CStore cStore) {
		// 生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
		//tCmMsg.getSqlMap().put("dsf", dataScopeFilter(tCmMsg.getCurrentUse
		// r(), "o", "a"));
		// 设置分页参数
		cStore.setPage(page);
		List<CStore> list = dao.findList(cStore);
		page.setList((list));
		return page;
	}

	public List<CStore> listHandle(List<CStore> list) {
		List<CStore> cStoreList =  new ArrayList<CStore>();
		if(list!=null) {
			for (int i = 0; i < list.size(); i++) {
				CStore cStore = list.get(i);
					CSclass cs = cSclassService.get(cStore.getSclass());
					if (cs != null) {
						cStore.setKhclass(cs.getName());
					}
				cStoreList.add(cStore);
			}
		}
		return cStoreList;
	}
}

