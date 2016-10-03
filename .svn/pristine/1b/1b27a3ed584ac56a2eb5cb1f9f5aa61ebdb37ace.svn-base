package org.bio.service;

import java.io.Serializable;
import java.util.List;

import org.bio.dao.GroupOrderDao;
import org.bio.model.GroupOrder;
import org.bio.model.Rapport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GroupOrderServiceImpl implements GroupOrderService, Serializable {

	@Autowired
	private GroupOrderDao groupOrderDao;
	public GroupOrderDao getGroupOrderDao() {
		return groupOrderDao;
	}

	public void setGroupOrderDao(GroupOrderDao groupOrderDao) {
		this.groupOrderDao = groupOrderDao;
	}

	@Override
	@Transactional
	public void insertGroupOrder(GroupOrder go) {
		groupOrderDao.insertGroupOrder(go);

	}

	@Override
	@Transactional
	public void updateGroupOrder(GroupOrder go) {
		groupOrderDao.updateGroupOrder(go);

	}

	@Override
	@Transactional
	public List<GroupOrder> findAllGroupOrder() {		
		return groupOrderDao.findAllGroupOrder();
	}

	@Override
	@Transactional
	public void deleteGroupOrder(GroupOrder go) {
		groupOrderDao.deleteGroupOrder(go);

	}

	@Override
	@Transactional
	public GroupOrder getByid(int id) {
		return groupOrderDao.getByid(id);
	}
	
	@Override
	@Transactional
	public List<GroupOrder> findByRapport(Rapport rapport)
	{
		return groupOrderDao.getByRapport(rapport);
	}

}
