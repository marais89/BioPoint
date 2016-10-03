package org.bio.dao;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.bio.model.Affiliation;
import org.bio.model.Personnel;
import org.bio.model.Pointage;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class PointageDaoImpl implements PointageDao, Serializable {
	@Autowired
	
private SessionFactory sessionFactory;
	
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void insertPointage(Pointage pointage) {
		
		getSessionFactory().getCurrentSession().saveOrUpdate(pointage);

	}

	@Override
	public void updatePointage(Pointage pointage) {
		getSessionFactory().getCurrentSession().update(pointage);

	}

	@Override
	public List<Pointage> findAllPointages() {
	
		return getSessionFactory().getCurrentSession().createQuery("from Pointage").list();
	}

	@Override
	public void deletePointage(Date d,Date d2) {
		 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	String deb=dateFormat.format(d);
	String fin = dateFormat.format(d2);
	System.out.println("Date 1"+deb+" Date "+fin);

		getSessionFactory().getCurrentSession().createQuery("delete from Pointage where datePointage between :deb and :end")
		.setParameter("deb", deb).setParameter("end", fin);

	}

	@Override
	public Pointage getByid(int id) {
	
		return (Pointage) getSessionFactory().getCurrentSession().get(Pointage.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pointage> FindPointageForDay(Date d, Personnel p) {
		// TODO Auto-generated method stub
		return getSessionFactory().getCurrentSession().createQuery("from Pointage where personnel=:pers and Date(:jour)=datePointage")
				.setEntity("pers", p).setDate("jour", d).list();
	}
	@Override
	public List<Pointage> findPointages(Date d1, Date d2, Affiliation a) {
		// TODO Auto-generated method stub
		return getSessionFactory().getCurrentSession().createQuery("select p from Pointage as p, Historique as h where h.affiliation=:ida and p.id.idper=h.personnel.idper and p.id.jour between DATE(:deb) and DATE(:fin)").setEntity("ida",a).setDate("deb", d1).setDate("fin", d2).list();
	}

	@Override
	public Long findPointagesAnomalie() {
		// TODO Auto-generated method stub
		return (Long) getSessionFactory().getCurrentSession().createQuery("select count(p) from Pointage as p where anomalie=1").uniqueResult();
	}
	
	@Override
	public void deleteById(int idPer,Date d)
	{ System.out.println("AVANT SUPPRISSION !!!");
		getSessionFactory().getCurrentSession().createQuery("delete From Pointage as p where p.id.idper =:per and p.id.jour=:d ").setInteger("per",idPer).setDate("d", d);
		System.out.println("APRES SUPPRISSION !!!");
	}
	
}
