/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.sys.service;

import java.util.List;
import java.util.Set;

import com.google.common.collect.Sets;
import com.tlkzzz.jeesite.common.utils.StringUtils;
import com.tlkzzz.jeesite.modules.sys.utils.UserUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlkzzz.jeesite.common.service.TreeService;
import com.tlkzzz.jeesite.modules.sys.dao.AreaDao;
import com.tlkzzz.jeesite.modules.sys.entity.Area;

/**
 * 区域Service
 * @author tlkzzz
 * @version 2014-05-16
 */
@Service
@Transactional(readOnly = true)
public class AreaService extends TreeService<AreaDao, Area> {

	public List<Area> findAll(){
		return UserUtils.getAreaList();
	}
	public List<Area> findListByParent(Area area){
		return dao.findListByParent(area);
	}

	@Transactional(readOnly = false)
	public void save(Area area) {
		super.save(area);
		UserUtils.removeCache(UserUtils.CACHE_AREA_LIST);
	}
	
	@Transactional(readOnly = false)
	public void delete(Area area) {
		super.delete(area);
		UserUtils.removeCache(UserUtils.CACHE_AREA_LIST);
	}

	public List<Area> findAreaTree(Area area){
		List<Area> 	list = findAll();
		// 将没有父节点的节点，找到父节点
		Set<String> parentIdSet = Sets.newHashSet();
		for (Area e : list){
			if (e.getParent()!=null && StringUtils.isNotBlank(e.getParent().getId())){
				boolean isExistParent = false;
				for (Area e2 : list){
					if (e.getParent().getId().equals(e2.getId())){
						isExistParent = true;
						break;
					}
				}
				if (!isExistParent){
					parentIdSet.add(e.getParent().getId());
				}
			}
		}
		return list;
	}

}
