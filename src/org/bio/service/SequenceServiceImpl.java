package org.bio.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.bio.dao.SequenceDao;
import org.bio.model.Personnel;
import org.bio.model.Sequence;
import org.bio.model.SequenceDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class SequenceServiceImpl implements SequenceService, Serializable {
	@Autowired
private SequenceDao sequenceDao;
	
	public SequenceDao getSequenceDao() {
		return sequenceDao;
	}

	public void setSequenceDao(SequenceDao sequenceDao) {
		this.sequenceDao = sequenceDao;
	}

	@Override
	@Transactional
	public void insertSequence(Sequence sequence) {
		sequenceDao.insertSequence(sequence);

	}

	@Override
	@Transactional
	public void updateSequence(Sequence sequence) {
	sequenceDao.updateSequence(sequence);
	}

	@Override
	@Transactional
	public List<Sequence> findAllSequences() {
		
		return sequenceDao.findAllSequences();
	}
	
	@Override
	@Transactional
	public List<Sequence> findSequence(String s) {
		
		return sequenceDao.findSequence(s);
	}

	@Override
	@Transactional
	public void deleteSequence(Sequence sequence) {
		sequenceDao.deleteSequence(sequence);

	}

	@Override
	@Transactional
	public Sequence getByid(int id) {
		
		return sequenceDao.getByid(id);
	}

	@Override
	@Transactional
	public Sequence getCurrentSequence(Personnel p) {
		// TODO Auto-generated method stub
		return sequenceDao.getCurrentSequence(p);
	}
	@Override
	@Transactional
	public SequenceDetail getjourDetail(Sequence s, int jour) {
		// TODO Auto-generated method stub
		return sequenceDao.getjourDetail(s, jour);
	}

	@Override
	@Transactional
	public List<Sequence> findAllDaySequence() {
		// TODO Auto-generated method stub
		return sequenceDao.findAllDaySequence();
	}

	@Override
	@Transactional
	public Sequence findCurrentDaySequence(Personnel p) {
		// TODO Auto-generated method stub
		return sequenceDao.findCurrentDaySequence(p);
	}

	@Override
	@Transactional
	public List<SequenceDetail> findAllDayDetail(Sequence s) {
		// TODO Auto-generated method stub
		return sequenceDao.findAllDayDetail(s);
	}

	@Override
	@Transactional
	public Sequence getcurrentSequencebyDay(Date d, Personnel p) {
		// TODO Auto-generated method stub
		return sequenceDao.getcurrentSequencebyDay(d, p)
				;
	}
	
	@Override
	@Transactional
	public Sequence findCurrentSequenceByDate(Date d, Personnel p)
	{
				return sequenceDao.findCurrentSequenceByDate(d,p);
						
	}

}
