package org.bio.dao;

import java.util.List;

import org.bio.model.Page;;

public interface PageDao {
	public void insertPage(Page page);
	public void updatePage(Page page);
	public List<Page> findAllPage();
	public void deletePage(Page page);
	public Page getByid(int  id);
}
