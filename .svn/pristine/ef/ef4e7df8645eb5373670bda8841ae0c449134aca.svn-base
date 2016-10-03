package org.bio.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bio.model.Affiliation;
import org.bio.model.Conge;
import org.bio.model.Document;
import org.bio.model.Historique;
import org.bio.model.HistoriqueCat;
import org.bio.model.Identification;
import org.bio.model.Mvt;
import org.bio.model.Personnel;
import org.bio.model.PersonnelTerminal;
import org.bio.model.Terminaux;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class PersonnelDaoImpl implements PersonnelDao, Serializable {
	@Autowired
private SessionFactory sessionFactory;
	public SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}
	@Override
	public void insertPersonnel(Personnel personnel) {
getSessionFactory().getCurrentSession().save(personnel);

	}

	@Override
	public void updatePersonnel(Personnel personnel) {
getSessionFactory().getCurrentSession().update(personnel);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Personnel> findAllPersonnels(List<Affiliation> list) {
		List<Personnel> l=new ArrayList<Personnel>();
		Affiliation[] tab = new Affiliation[list.size()];
		for(int i=0;i<list.size();i++)
		{
			tab[i]=list.get(i);
		}
		if(tab.length>0)
 l.addAll(getSessionFactory().getCurrentSession().createQuery("select DISTINCT p from Historique as h,Personnel p where p.idper=h.personnel.idper and h.affiliation IN (:listaff)").setCacheable(true).setParameterList("listaff", tab).list());
	return l;
	}

	@Override
	public void deletePersonnel(Personnel personnel) {
getSessionFactory().getCurrentSession().delete(personnel);

	}

	@Override
	public Personnel getByid(int id) {
		
		return(Personnel) getSessionFactory().getCurrentSession().get(Personnel.class, id);
	}
	@Override
	public List<Historique> listHistorique(Personnel per) {
		// TODO Auto-generated method stub
	return   getSessionFactory().getCurrentSession().createQuery("from Historique where personnel=:per").setEntity("per", per).list();

	}
	@Override
	public List<Mvt> listMvt(Personnel per) {
		// TODO Auto-generated method stub
		return   getSessionFactory().getCurrentSession().createQuery("from Mvt where personnel=:per order by jourLogique desc").setEntity("per", per).list();

	}
	@Override
	public List<Document> findAllDocument(Personnel per) {
		// TODO Auto-generated method stub
		return   getSessionFactory().getCurrentSession().createQuery("from Document where personnel=:per").setCacheRegion("query").setCacheable(true).setEntity("per", per).list();
	}
	@Override
	public List<HistoriqueCat> findAllHistCat(Personnel per) {
		// TODO Auto-generated method stub
		return   getSessionFactory().getCurrentSession().createQuery("from HistoriqueCat where personnel=:per").setEntity("per", per).list();

	}
	@Override
	public List<Mvt> getMvtByDay(Date d, Personnel p) {
		// TODO Auto-generated method stub
		return getSessionFactory().getCurrentSession().createQuery("from Mvt where personnel=:per and jourLogique=:djour  order by heure")
				.setEntity("per", p).setDate("djour", d).list();
	}
	@SuppressWarnings("unchecked")
	@Override
	public Conge findAutorisationByDay(Date d, Personnel p) {
		// TODO Auto-generated method stub
		return (Conge) getSessionFactory().getCurrentSession().createQuery("from Conge where :jour between DATE(debut) and DATE(fin) and personnel=:personnel and type=:typeauto")
				.setString("typeauto", "Autorisation").setEntity("personnel", p).setDate("jour", d).uniqueResult();
	}
	@Override
	public Conge findCongeByDay(Date d, Personnel p) {
		// TODO Auto-generated method stub
		List<Conge> lc= new ArrayList<Conge>();
		lc.addAll(getSessionFactory().getCurrentSession().createQuery("from Conge where :jour between debut and fin and personnel=:personnel and type=:typeauto").setString("typeauto", "Congé").setEntity("personnel", p).setDate("jour", d).list());
	if(lc.size()==0){return null;}
	else{	return lc.get(0);}
	}
	@Override
	public HistoriqueCat getcurrentHistoriqueCat(Personnel p) {
		// TODO Auto-generated method stub
		return (HistoriqueCat) getSessionFactory().getCurrentSession().createQuery("from HistoriqueCat where personnel=:per and NOW() between du and au").setEntity("per",p).uniqueResult();
	}
	@Override
	public Personnel getPersonnelByEnrollID(int id,Terminaux t) {
		// TODO Auto-generated method stub

	Personnel	p=(Personnel) getSessionFactory().getCurrentSession().createQuery("select personnel from PersonnelTerminal as pt where pt.bckId=:id and pt.terminaux=:t ")
				.setInteger("id", id).setEntity("t", t).setCacheable(true).setCacheRegion("query").uniqueResult();

	return p;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Terminaux> getAllPointTerminaux(Personnel p) {
		// TODO Auto-generated method stub
		return getSessionFactory().getCurrentSession().createQuery("select pt.terminaux from PersonnelTerminal as pt where pt.delete=null and pt.id.idper=:idp")
				.setInteger("idp", p.getIdper()).list();
	}
	
	@SuppressWarnings("unchecked")
	public List<PersonnelTerminal> getAllPointPersonnelTerminaux(Personnel p) {
		// TODO Auto-generated method stub
		return getSessionFactory().getCurrentSession().createQuery("from PersonnelTerminal as pt where pt.delete=null and pt.id.idper=:idp")
				.setInteger("idp", p.getIdper()).list();
	}
	
	@Override
	public List<PersonnelTerminal> getAllAffectedPointer(Personnel p) {
		// TODO Auto-generated method stub
		return getSessionFactory().getCurrentSession().createQuery("from PersonnelTerminal as pt where pt.id.idper=:p").setInteger("p", p.getIdper()).list();
	}
	@Override
	public List<Personnel> findAllPersonnels() {
		// TODO Auto-generated method stub
		List<Personnel> lp= new ArrayList<>();
		lp=getSessionFactory().getCurrentSession().createQuery("from Personnel").list();
		return lp;
	}
	@Override
	public Personnel findpersonnelByckId(int id, int deviceId) {
		// TODO Auto-generated method stub
		return (Personnel) getSessionFactory().getCurrentSession().createQuery("select personnel from Personnel as p,Terminaux as,PersonnelTerminal as pt where pt.id.idter=t.idter and pt.id.idper=p.idper and pt.bckId=:bck"
				+ "t.deviceId=:id").uniqueResult();
	}
	@Override
	public void updateconge(int x,int idper ) {
		// TODO Auto-generated method stub
		   getSessionFactory().getCurrentSession().createQuery("UPDATE Personnel set congeAccorde=:x WHERE idper=:idper");

	}
	@Override
	public List<Personnel> PersonnelswithoutEmpreinte() {
		// TODO Auto-generated method stub
		return getSessionFactory().getCurrentSession().createQuery("from Personnel  where  bckIdEnroll is not null and idper not in (select id.idper from Identification where id.type!='Emp' and id.type!='Carte' and id.type!='Password')").list();
	}
	@Override
	public List<PersonnelTerminal> listtoupdate(Terminaux t) {
		// TODO Auto-generated method stub
		return getSessionFactory().getCurrentSession().createQuery("from PersonnelTerminal as pt where pt.id.idter=:ter and uploadedP=0")
				.setInteger("ter", t.getIdter()).list();
	}
	@Override
	public List<Identification> findidentification(Personnel p) {
		// TODO Auto-generated method stub
		return  getSessionFactory().getCurrentSession().createQuery("from Identification where id.idper=:per and typeiden='Emp'")
				.setInteger("per",p.getIdper()).list();
	}
	@Override
	public Identification findCarteidentification(Personnel p) {
		// TODO Auto-generated method stub
		return (Identification) getSessionFactory().getCurrentSession().createQuery("from Identification where id.idper=:per and typeiden='Carte'")
				.setInteger("per",p.getIdper()).uniqueResult();
	}
	@Override
	public Identification findPasswordidentification(Personnel p) {
		// TODO Auto-generated method stub
		return (Identification) getSessionFactory().getCurrentSession().createQuery("from Identification where id.idper=:per and typeiden='Password'")
				.setInteger("per",p.getIdper()).uniqueResult();
	}
	@Override
	public List<PersonnelTerminal> listtodelete(Terminaux t) {
		// TODO Auto-generated method stub
		return getSessionFactory().getCurrentSession().createQuery("from PersonnelTerminal as pt where pt.id.idter=:ter and pt.delete=1")
				.setInteger("ter", t.getIdter()).list();	}
	@Override
	public Historique getcurrentHistorique(Personnel p) {
		// TODO Auto-generated method stub
	 return (Historique) getSessionFactory().getCurrentSession().createQuery("from Historique where personnel=:per and NOW() between du and au").setEntity("per", p).uniqueResult();
	}
	@Override
	public Affiliation findcurrentAffiliation(Personnel p) {
		// TODO Auto-generated method stub
	Query q = getSessionFactory().getCurrentSession().createQuery("select hi.affiliation from Historique as hi where hi.personnel=:per and NOW()"
	 		+ " between hi.du and hi.au").setEntity("per", p);
	q.setCacheable(true);
	q.setCacheRegion("query");
		 return   (Affiliation) q.uniqueResult();
	}
	@Override
	public List<Terminaux> findTerminauxNotAfficated(Personnel p) {
		// TODO Auto-generated method stub
		return getSessionFactory().getCurrentSession().createQuery("from Terminaux where idter not in(select id.idter from PersonnelTerminal where id.idper=:pers) and pointage=1")
				.setParameter("pers", p.getIdper()).list();
	}

}
