package org.bio.dao;

import java.io.Serializable;
import java.util.List;

import org.bio.model.Operateur;
import org.bio.model.Sequence;
import org.bio.model.SequenceDetail;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class DetailSequenceDaoImpl implements DetailSequenceDao, Serializable {
	@Autowired
private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void insertDetailSequence(SequenceDetail detailSequence) {
		
getSessionFactory().getCurrentSession().save(detailSequence);
	}

	@Override
	public void updateDetailSequence(SequenceDetail detailSequence) {
		getSessionFactory().getCurrentSession().update(detailSequence);

	}

	@Override
	public List<SequenceDetail> findAllDetailSequences() {
		
		return getSessionFactory().getCurrentSession().createQuery("from DetailSequence").list();
	}

	@Override
	public void deleteDetailSequence(SequenceDetail detailSequence) {
		getSessionFactory().getCurrentSession().delete(detailSequence);

	}

	@Override
	public SequenceDetail getByid(int id) {
		// TODO Auto-generated method stub
		return (SequenceDetail) getSessionFactory().getCurrentSession().get(SequenceDetail.class, id);
	}
	
	
	@Override
	public List<SequenceDetail> findByIdseq(Sequence ids)
	{
		Query query=getSessionFactory().getCurrentSession().createQuery("from SequenceDetail where idseq =:IdSeq").setParameter("IdSeq", ids);
		return query.list();
	}

}
