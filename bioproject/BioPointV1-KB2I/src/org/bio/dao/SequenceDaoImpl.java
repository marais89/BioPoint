package org.bio.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.bio.model.Calendrier;
import org.bio.model.Categorie;
import org.bio.model.HistoriqueCat;
import org.bio.model.Personnel;
import org.bio.model.Sequence;
import org.bio.model.SequenceDetail;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class SequenceDaoImpl implements SequenceDao, Serializable {
@Autowired
private SessionFactory sessionFactory;


	public SessionFactory getSessionFactory() {
	return sessionFactory;
}

public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
}

	@Override
	public void insertSequence(Sequence sequence) {
		getSessionFactory().getCurrentSession().save(sequence);

	}

	@Override
	public void updateSequence(Sequence sequence) {
		getSessionFactory().getCurrentSession().update(sequence);
	}

	@Override
	public List<Sequence> findAllSequences() {
		
		return getSessionFactory().getCurrentSession().createQuery("from Sequence").list();
	}

	@Override
	public void deleteSequence(Sequence sequence) {
		getSessionFactory().getCurrentSession().delete(sequence);

	}

	@Override
	public Sequence getByid(int id) {
		
		return (Sequence) getSessionFactory().getCurrentSession().get(Sequence.class,id);
	}

	@Override
	public Sequence getCurrentSequence(Personnel p) {
		// TODO Auto-generated method stub
		
 return (Sequence) getSessionFactory().getCurrentSession().createQuery("select h.sequence from Sequence as s,Calendrier as c,HistoriqueCat as hi,Categorie as cat,Horaires as h,Personnel as p where  NOW() between c.dateDebut and c.dateFin  and h.categorie.idcat = cat.idcat and c.idcal=h.calendrier.idcal "
				+ "and h.sequence.idseq=s.idseq and p.idper = hi.personnel.idper and cat.idcat=hi.categorie.idcat and NOW() between hi.du and hi.au and p=:personnel")
				.setEntity("personnel", p).uniqueResult();

	}

	@Override
	public SequenceDetail getjourDetail(Sequence s, int jour) {
		// TODO Auto-generated method stub
		return (SequenceDetail) getSessionFactory().getCurrentSession().createQuery("from SequenceDetail where idseq=:seq and jour=:nbjour")
				.setEntity("seq", s).setInteger("nbjour", jour).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Sequence> findAllDaySequence() {
		// TODO Auto-generated method stub
		return getSessionFactory().getCurrentSession().createQuery("from Sequence where typeSeq='seq_jours'").list();
	}
	
	@Override
	public List<Sequence> findSequence(String s) {
		// TODO Auto-generated method stub
		return   getSessionFactory().getCurrentSession().createQuery("from Sequence as seq where seq.desigSeq=:s").setString("s",s).list();

	}

	@Override
	public Sequence findCurrentDaySequence(Personnel p) {
		// TODO Auto-generated method stub
		return (Sequence) getSessionFactory().getCurrentSession().createQuery("select h.sequence from HistoriqueCat as h where NOW() between du and au and h.sequence<> 'NULL' and h.personnel=:per")
				.setEntity("per", p).uniqueResult() ;
	}

	@Override
	public List<SequenceDetail> findAllDayDetail(Sequence s) {
		// TODO Auto-generated method stub
		return getSessionFactory().getCurrentSession().createQuery("from SequenceDetail where idseq=:seq").setEntity("seq", s).list();
	}

	@Override
	public Sequence findCurrentSequenceByDate(Date d,Personnel p) {
		// TODO Auto-generated method stub
		System.out.println("--> getcurrentsequenceday");
		return (Sequence) getSessionFactory().getCurrentSession().createQuery("select h.sequence from Sequence as s,Calendrier as c,HistoriqueCat as hi,Categorie as cat,Horaires as h,Personnel as p where  :jour between c.dateDebut and c.dateFin  and h.categorie.idcat = cat.idcat and c.idcal=h.calendrier.idcal "
				+ "and h.sequence.idseq=s.idseq and p.idper = hi.personnel.idper and cat.idcat=hi.categorie.idcat and :jour between hi.du and hi.au and p=:personnel")
				.setEntity("personnel", p).setDate("jour",d).uniqueResult();
	}
	
	@Override
	public Sequence getcurrentSequencebyDay(Date d, Personnel p)
	{System.out.println("req1");
		Categorie cat=  (Categorie) getSessionFactory().getCurrentSession().createQuery("select categorie from HistoriqueCat as hc where :jour between hc.du and hc.au and hc.personnel=:pers  ").setEntity("pers", p).setDate("jour",d).uniqueResult();
		System.out.println("req2"+cat.getDesigCat());
		Calendrier cal= (Calendrier)getSessionFactory().getCurrentSession().createQuery("select calendrier from GlobalHistorique as gh where gh.jour=:jour").setDate("jour",d).uniqueResult();
		System.out.println("req3"+cal.getDesignation());
		Sequence seq=(Sequence) getSessionFactory().getCurrentSession().createQuery("select sequence from Horaires as h where h.calendrier=:cal and h.categorie=:cat").setEntity("cal",cal).setEntity("cat",cat).uniqueResult();
		System.out.println("req4");
		return seq;
	}
	
//@Override
//	public Sequence getcurrentSequencebyDay(Date d, Personnel p)
//	{
//		return (Sequence) getSessionFactory().getCurrentSession().createQuery("select sequence from HistoriqueCat as hc, Horaires as h,GlobalHistorique as gh, Personnel as p where :jour between hc.du and hc.au  and hc.personnel=:personnel and gh.jour=:jour"
//				+ "and h.categorie=hc.categorie and h.calendrier=gh.calendrier")
//				.setEntity("personnel", p).setDate("jour",d).uniqueResult();
//	}

}
