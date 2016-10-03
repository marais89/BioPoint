package org.bio.service;

import java.io.Serializable;
import java.util.List;

import org.bio.dao.DetailSequenceDao;
import org.bio.model.Sequence;
import org.bio.model.SequenceDetail;
import org.hibernate.Interceptor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.proxy.EntityNotFoundDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class SequenceDetailServiceImpl implements SequenceDetailService, Serializable {
@Autowired
private DetailSequenceDao detailSequenceDao; 

	public DetailSequenceDao getDetailSequenceDao() {
	return detailSequenceDao;
}

public void setDetailSequenceDao(DetailSequenceDao detailSequenceDao) {
	this.detailSequenceDao = detailSequenceDao;
}

	@Override
	@Transactional
	public void insertDetailSequence(SequenceDetail detailSequence) {
	detailSequenceDao.insertDetailSequence(detailSequence);

	}

	@Override
	@Transactional
	public void updateDetailSequence(SequenceDetail detailSequence) {
		detailSequenceDao.updateDetailSequence(detailSequence);

	}
	
	@Override
	@Transactional
	public List<SequenceDetail> findByIdSeq(Sequence Idsqe) 
	{				
		return detailSequenceDao.findByIdseq(Idsqe);
	}
	

	@Override
	@Transactional
	public List<SequenceDetail> findAllDetailSequence() {
		
		return detailSequenceDao.findAllDetailSequences();
	}

	@Override
	@Transactional
	public void deleteDetailSequence(SequenceDetail detailSequence) {
		detailSequenceDao.deleteDetailSequence(detailSequence);

	}

	@Override
	@Transactional
	public SequenceDetail getByid(int id) {	
		return detailSequenceDao.getByid(id);
	}

}
