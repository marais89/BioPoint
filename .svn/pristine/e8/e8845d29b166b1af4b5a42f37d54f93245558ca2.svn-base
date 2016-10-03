package org.bio.dao;

import java.io.Serializable;
import java.util.List;

import org.bio.model.GroupOrder;
import org.bio.model.Rapport;
import org.bio.model.RelationRapport;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GroupOrderDaoImpl implements GroupOrderDao, Serializable {

	@Autowired
	private SessionFactory sessionFactory;


		public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void insertGroupOrder(GroupOrder gr) {
		getSessionFactory().getCurrentSession().save(gr);

	}

	@Override
	public void updateGroupOrder(GroupOrder gr) {
		getSessionFactory().getCurrentSession().update(gr);

	}

	@Override
	public List<GroupOrder> findAllGroupOrder() {
		return getSessionFactory().getCurrentSession().createQuery("from  groupOrder").list();
	}

	@Override
	public void deleteGroupOrder(GroupOrder gr) {
		getSessionFactory().getCurrentSession().delete(gr);


	}

	@Override
	public GroupOrder getByid(int id) {
		return (GroupOrder) getSessionFactory().getCurrentSession().get(GroupOrder.class,id);
	}
	
	@Override
	public List<GroupOrder> getByRapport(Rapport rap)
	{
		return   getSessionFactory().getCurrentSession().createQuery("from GroupOrder as gr where gr.rapport=:r").setEntity("r",rap).list();
	}

}
