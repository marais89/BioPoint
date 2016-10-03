package org.bio.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bio.dao.MvtDao;
import org.bio.model.Mvt;
import org.bio.model.Personnel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class MvtServiceImpl implements MvtService, Serializable {
	@Autowired
private MvtDao mvtDao;
	
	public MvtDao getMvtDao() {
		return mvtDao;
	}

	public void setMvtDao(MvtDao mvtDao) {
		this.mvtDao = mvtDao;
	}

	@Override
	@Transactional
	public void insertMvt(Mvt mvt) {
		mvtDao.insertMvt(mvt);

	}

	@Override
	@Transactional
	public void updateMvt(Mvt mvt) {
		mvtDao.updateMvt(mvt);

	}

	@Override
	@Transactional
	public List<Mvt> findAllMvts() {
		
		ArrayList<Mvt> l= new ArrayList<Mvt>();
		return mvtDao.findAllMvts();
		
	}

	@Override
	@Transactional
	public void deleteMvt(Mvt mvt) {
		mvtDao.deleteMvt(mvt);

	}

	@Override
	@Transactional
	public Mvt getByid(int id) {
		
		return mvtDao.getByid(id);
	}

	@Override
	@Transactional
	public List<Mvt> findMvtByDates(Date a1, Date a2) {
		// TODO Auto-generated method stub
		return mvtDao.findMvtByDates(a1, a2);
	}
	
	@Override
	@Transactional
	public List<Mvt> findNewMvt()
	{
		return mvtDao.findNewMvt();
	}
	
	@Override
	@Transactional
	public List<Mvt> findByIdOk(int idPer,Date d)
	{
		ArrayList<Mvt> l= new ArrayList<Mvt>();
		l.addAll( mvtDao.findByIdOk(idPer, d));	
		
		ArrayList<Mvt> inter = new ArrayList<Mvt>();
		Date dt;Date t;Personnel p;
		if(l.size()>1)
		{
			dt=l.get(0).getJourLogique();	t=l.get(0).getHeure();		p=l.get(0).getPersonnel();			
		for(int i=1;i<l.size();i++)
		{		//System.out.println("Date"+d+",time"+t+"======> date"+l.get(i).getJourLogique()+"time"+l.get(i).getHeure());
			if(l.get(i).getJourLogique()==dt && l.get(i).getHeure()==t && l.get(i).getPersonnel().getIdper()==p.getIdper())
			{
				inter.add(l.get(i));
				l.remove(i);
				i--;
			}
			else
			{
				dt=l.get(i).getJourLogique();
				t=l.get(i).getHeure();
				p=l.get(i).getPersonnel();			
			}
		}
		for(int j=0;j<inter.size();j++)			
		{System.out.println("row deleted **");mvtDao.deleteMvt(inter.get(j));}
		}
		return l;
	}
	
	@Override
	@Transactional
	public List<Mvt> allDistinctMvt()
	{
		return mvtDao.allDistinctMvt();
	}

}
