package org.bio.dao;

import java.io.Serializable;
import java.util.List;

import org.bio.model.Personnel;
import org.bio.model.PersonnelTerminal;
import org.bio.model.Terminaux;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class TerminauxDaoImpl implements TerminauxDao,Serializable {
	@Autowired
private SessionFactory sessionFactory;
	@Override
	public void insertTerminal(Terminaux terminal) {
		getSessionFactory().getCurrentSession().save(terminal);

	}

	@Override
	public void updateTerminal(Terminaux terminal) {
		getSessionFactory().getCurrentSession().update(terminal);
	}

	@Override
	public List<Terminaux> findAllTerminaux() {
	
		return getSessionFactory().getCurrentSession().createQuery("from Terminaux").list();
	}

	@Override
	public void deleteTerminal(Terminaux terminal) {
		getSessionFactory().getCurrentSession().delete(terminal);
	}

	@Override
	public Terminaux getByid(int id) {

		return (Terminaux)getSessionFactory().getCurrentSession().get(Terminaux.class, id);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Terminaux> findAllActiveTerminaux() {
		// TODO Auto-generated method stub
		return getSessionFactory().getCurrentSession().createQuery("from Terminaux where actif=1").list();
	}
	
	@Override
	public void truncateAll() {
		System.out.println(" -1 TRUNCATE EXEUTER AVEC SUCCÉÉS !!!!!!");
		getSessionFactory().getCurrentSession().createSQLQuery("truncate table mvt").executeUpdate();
		getSessionFactory().getCurrentSession().createSQLQuery("truncate table lostmvt").executeUpdate();
		getSessionFactory().getCurrentSession().createSQLQuery("truncate table pointage").executeUpdate();
		System.out.println(" 2 TRUNCATE EXEUTER AVEC SUCCÉÉS !!!!!!");
	}

	@Override
	public List<Terminaux> findEnrollTerminaux() {
		// TODO Auto-generated method stub
		return getSessionFactory().getCurrentSession().createQuery("from Terminaux where actif=1 and enrollement=1").list();
	}

	@Override
	public List<Terminaux> findPointageTerminaux() {
		// TODO Auto-generated method stub
		return getSessionFactory().getCurrentSession().createQuery("from Terminaux where actif=1 and pointage=1").list();
	}

	@Override
	public PersonnelTerminal findAssociatedTerminal(Personnel p,
			Terminaux t) {
		// TODO Auto-generated method stub
		return (PersonnelTerminal) getSessionFactory().getCurrentSession().createQuery("from PersonnelTerminal as pt where pt.id.idper=:p and pt.id.idter=:t")
				.setInteger("p", p.getIdper()).setInteger("t", t.getIdter()).uniqueResult();
	}

	@Override
	public Long terminauxToupdate() {
		// TODO Auto-generated method stub
	return  (Long) getSessionFactory().getCurrentSession().createQuery("select count(p.terminaux) from PersonnelTerminal as p where p.delete=1 or uploadedP=0").uniqueResult();

	}

	

}
