package org.bio.dao;

import java.io.Serializable;
import java.util.List;

import org.bio.model.ExportOption;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class ExportOptionDaoImpl implements ExportOptionDao,Serializable {
@Autowired
private SessionFactory sessionFactory;
	@Override
	public void insertExportOption(ExportOption ExportOption) {
		// TODO Auto-generated method stub
		getSessionFactory().getCurrentSession().save(ExportOption);
	}

	@Override
	public void updateExportOption(ExportOption ExportOption) {
		// TODO Auto-generated method stub
		getSessionFactory().getCurrentSession().saveOrUpdate(ExportOption);
	}

	@Override
	public List<ExportOption> findAllExportOptions() {
		// TODO Auto-generated method stub
		return 	getSessionFactory().getCurrentSession().createQuery("from ExportOption").list();
	}

	@Override
	public void deleteExportOption(ExportOption ExportOption) {
		// TODO Auto-generated method stub
		getSessionFactory().getCurrentSession().delete(ExportOption);
	}

	@Override
	public ExportOption getByid(int id) {
		// TODO Auto-generated method stub
		return 	(ExportOption) getSessionFactory().getCurrentSession().get(ExportOption.class, id);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
