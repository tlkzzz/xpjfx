/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tlkzzz/jeesite">JeeSite</a> All rights reserved.
 */
package com.tlkzzz.jeesite.modules.cms.service;

import com.tlkzzz.jeesite.modules.cms.dao.ArticleDataDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlkzzz.jeesite.common.service.CrudService;
import com.tlkzzz.jeesite.modules.cms.entity.ArticleData;

/**
 * 站点Service
 * @author tlkzzz
 * @version 2013-01-15
 */
@Service
@Transactional(readOnly = true)
public class ArticleDataService extends CrudService<ArticleDataDao, ArticleData> {

}
