package org.bio.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.bio.model.Affiliation;
import org.bio.model.Exporter;
import org.bio.model.Pointage;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ExporterDaoImpl implements ExporterDao,Serializable{
@Autowired
private SessionFactory sessionFactory;

	@Override
	public void insertExporter(Exporter exporter) {
		// TODO Auto-generated method stub
		getSessionFactory().getCurrentSession().save(exporter);
	}

	@Override
	public void updateExporter(Exporter exporter) {
		// TODO Auto-generated method stub
		getSessionFactory().getCurrentSession().update(exporter);

	}

	@Override
	public List<Exporter> findAllExporters() {
		// TODO Auto-generated method stub
		return getSessionFactory().getCurrentSession().createQuery("from Exporter").list();
	}

	@Override
	public void deleteExporter(Exporter exporter) {
		// TODO Auto-generated method stub
		getSessionFactory().getCurrentSession().delete(exporter);
	}

	@Override
	public Exporter getByid(int id) {
		// TODO Auto-generated method stub
		return (Exporter) getSessionFactory().getCurrentSession().get(Exporter.class, id);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Pointage> findPointages(Date d1, Date d2, Affiliation a) {
		// TODO Auto-generated method stub
		return getSessionFactory().getCurrentSession().createQuery("select po from Pointage as po,Personnel as p,Historique as h where h.idaff=:ida"
				+ "po.id.jour between :deb and fin and po.id.idper=h.idper").setInteger("ida",a.getIdaff()).setDate("deb", d1).setDate("fin", d2).list();
	}

}
