package org.bio.dao;

import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bio.model.Batch;
import org.bio.model.BatchId;
import org.bio.model.Mvt;
import org.bio.model.Personnel;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class MvtDaoimpl implements MvtDao, Serializable {
	@Autowired
private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void insertMvt(Mvt m) {
		getSessionFactory().getCurrentSession().save(m);
//		System.out.println(m);
//		Batch batch= new Batch();
//		System.out.println("3");
//		batch.setEtat(0);
//		System.out.println("4");
//		batch.setPersonnel(m.getPersonnel());
//		//batch.setId(new BatchId(m.getId().getIdper(),m.getJourLogique()));
//		batch.setJour(m.getJourLogique());
//		batch.setIdper(m.getId().getIdper());
//		getSessionFactory().getCurrentSession().save(batch);

		
	}

	@Override
	public void updateMvt(Mvt m) {
		getSessionFactory().getCurrentSession().saveOrUpdate(m);
//		System.out.println(m);
//		Batch batch= new Batch();
//		System.out.println("3");
//		batch.setEtat(0);
//		System.out.println("4");
//		batch.setPersonnel(m.getPersonnel());
//		//batch.setId(new BatchId(m.getId().getIdper(),m.getJourLogique()));
//		batch.setJour(m.getJourLogique());
//		batch.setIdper(m.getId().getIdper());
//getSessionFactory().getCurrentSession().save(batch);
	}

	@Override
	public List<Mvt> findAllMvts() {

		return getSessionFactory().getCurrentSession().createQuery("from Mvt ORDER BY jourLogique asc ,heure asc").list();
		
			
	}
	
	@Override
	public List<Mvt> allDistinctMvt() 
	{		
		return getSessionFactory().getCurrentSession().createQuery("select * from (select *,row_number() over (GROUP BY jourLogique) as seqnum from mvt ) where seqnum = 1;").list();
	}

	@Override
	public void deleteMvt(Mvt mvt) {
		getSessionFactory().getCurrentSession().delete(mvt);
	}

	@Override
	public Mvt getByid(int id) {		
		return (Mvt) getSessionFactory().getCurrentSession().get(Mvt.class, id);
	}

	@Override
	public List<Mvt> findMvtByDates(Date a1, Date a2) {
		// TODO Auto-generated method stub
		return getSessionFactory().getCurrentSession().createQuery("from Mvt where jourLogique between :deb and :fin ORDER BY jourLogique asc ,heure asc").setDate("deb", a1).setDate("fin", a2).list();
	}
	
	@Override
	public List<Mvt> findNewMvt()
	{
		return getSessionFactory().getCurrentSession().createQuery("from Mvt where etat ='new' order by jourLogique asc ").list();
	}
	
	@Override
	public List<Mvt> findByIdOk(int idPer,Date d)
	{
		return getSessionFactory().getCurrentSession().createQuery("from Mvt as m where m.jourLogique=:d and m.id.idper=:idPer order by jourLogique asc,heure asc ").setInteger("idPer", idPer).setDate("d", d).list();
		
	}

}
