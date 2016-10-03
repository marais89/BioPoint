package org.bio.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.bio.dao.FerieDao;
import org.bio.model.Ferie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class FerieServiceImpl implements FerieService,Serializable{
	@Autowired
private FerieDao feriedao;
	@Override
	@Transactional
	public void insertFerie(Ferie ferie) {
		feriedao.insertFerie(ferie);
		
	}

	@Override
	@Transactional
	public void updateFerie(Ferie ferie) {
		feriedao.updateFerie(ferie);
		
	}

	@Override
	@Transactional
	public List<Ferie> findAllFeries() {
		return feriedao.findAllFeries();
	}

	@Override
	@Transactional
	public void deleteFerie(Ferie ferie) {
		feriedao.deleteFerie(ferie);
		
	}

	@Override
	@Transactional
	public Ferie getByid(int id) {
		return feriedao.getByid(id);
	}

	public FerieDao getFeriedao() {
		return feriedao;
	}

	public void setFeriedao(FerieDao feriedao) {
		this.feriedao = feriedao;
	}

	@Override
	@Transactional
	public boolean isFerie(Date d) {
		// TODO Auto-generated method stub
		return feriedao.isFerie(d)
				;
	}

}
