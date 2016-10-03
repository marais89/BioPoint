package org.bio.service;

import java.io.Serializable;
import java.util.List;

import org.bio.dao.PageDao;
import org.bio.model.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class PageServiceImpl implements PageService, Serializable {
	@Autowired
private PageDao pageDao;
	
	public PageDao getPageDao() {
		return pageDao;
	}

	public void setPageDao(PageDao pageDao) {
		this.pageDao = pageDao;
	}

	@Override
	@Transactional
	public void insertPage(Page page) {
		pageDao.insertPage(page);

	}

	@Override
	@Transactional
	public void updatePage(Page page) {
		pageDao.updatePage(page);
	}

	@Override
	@Transactional
	public List<Page> findAllPages() {
		
		return pageDao.findAllPage();
	}

	@Override
	@Transactional
	public void deletePage(Page page) {
		pageDao.deletePage(page);

	}

	@Override
	@Transactional
	public Page getByid(int id) {
		
		return pageDao.getByid(id);
	}

}
