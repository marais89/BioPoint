package org.bio.service;

import java.io.Serializable;
import java.util.List;

import org.bio.dao.BatchDao;
import org.bio.model.Batch;
import org.bio.model.BatchId;
import org.bio.model.Personnel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BatchServiceImpl implements Serializable,BatchService{

	@Autowired
	private BatchDao barchDao;
	@Override
	@Transactional
	public void insertBatch(Batch Batch) {
		// TODO Auto-generated method stub
		barchDao.insertBatch(Batch);
	}

	@Override
	@Transactional
	public void updateBatch(Batch Batch) {
		// TODO Auto-generated method stub
		barchDao.updateBatch(Batch);
	}
	@Transactional
	@Override
	public List<Batch> findAllBatch() {
		// TODO Auto-generated method stub
		return barchDao.findAllBatch();
	}
	@Transactional
	@Override
	public void deleteBatch(Batch Batch) {
		// TODO Auto-generated method stub
		barchDao.deleteBatch(Batch);
	}
	@Transactional
	@Override
	public Batch getByid(BatchId id) {
		// TODO Auto-generated method stub
		return barchDao.getByid(id);
	}
	@Transactional
	@Override
	public List<Personnel> findAllPersonnels() {
		// TODO Auto-generated method stub
		return null;
	}

	public BatchDao getBarchDao() {
		return barchDao;
	}

	public void setBarchDao(BatchDao barchDao) {
		this.barchDao = barchDao;
	}

	@Override
	public List<Batch> findAllBatch(int etat) {
		// TODO Auto-generated method stub
		return barchDao.findAllBatch(etat);
	}

}
