package org.bio.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bio.dao.OperateurDao;
import org.bio.dao.PersonnelDao;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class PersonnelServiceImpl implements PersonnelService,Serializable {
   @Autowired
	private PersonnelDao personnelDao;
   @Autowired
   private OperateurDao oeprateurDao;
	@Override
	@Transactional
	public void insertPersonnel(Personnel personnel) {
		personnelDao.insertPersonnel(personnel);

	}

	@Override
	@Transactional
	public void updatePersonnel(Personnel personnel) {
		personnelDao.updatePersonnel(personnel);

	}

	@Override
	@Transactional
	public List<Personnel> findAllPersonnels() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
List<Affiliation> list = oeprateurDao.getByLogin(auth.getName()).getListaffiliations();
List<Personnel> lp= new ArrayList<>();
lp=personnelDao.findAllPersonnels(list);
return lp;		
	}
	
	@Override
	@Transactional
	public List<Personnel> findAllPersonnels2() {
List<Personnel> lp= new ArrayList<>();
lp=personnelDao.findAllPersonnels();
return lp;
		
	}

	@Override
	@Transactional
	public void deletePersonnel(Personnel personnel) {
		personnelDao.deletePersonnel(personnel);

	}

	@Override
	@Transactional
	public Personnel getByid(int id) {
		
		return personnelDao.getByid(id);
	}

	public PersonnelDao getPersonnelDao() {
		return personnelDao;
	}

	public void setPersonnelDao(PersonnelDao personnelDao) {
		this.personnelDao = personnelDao;
	}

	@Override
	@Transactional
	public List<Historique> listHistorique(Personnel per) {
		// TODO Auto-generated method stub
		return personnelDao.listHistorique(per);
	}

	@Override
	@Transactional
	public List<Mvt> listMvt(Personnel per) {
		// TODO Auto-generated method stub
		return personnelDao.listMvt(per);
	}

	@Override
	@Transactional
	public List<Document> findAllDocument(Personnel per) {
		// TODO Auto-generated method stub
		return personnelDao.findAllDocument(per);
	}

	@Override
	@Transactional
	public List<HistoriqueCat> findAllHistCat(Personnel per) {
		// TODO Auto-generated method stub
		return personnelDao.findAllHistCat(per);
	}

	@Override
	@Transactional
	public List<Mvt> getMvtByDay(Date d, Personnel p) {
		// TODO Auto-generated method stub
		return personnelDao.getMvtByDay(d, p);
	}

	@Override
	@Transactional
	public Conge findAutorisationByDay(Date d, Personnel p) {
		// TODO Auto-generated method stub
		return personnelDao.findAutorisationByDay(d, p);
	}

	@Override
	@Transactional
	public Conge findCongeByDay(Date d, Personnel p) {
		// TODO Auto-generated method stub
		return personnelDao.findCongeByDay(d, p);
	}

	@Override
	@Transactional
	public HistoriqueCat getcurrentHistoriqueCat(Personnel p) {
		// TODO Auto-generated method stub
		return personnelDao.getcurrentHistoriqueCat(p);
	}

	public OperateurDao getOeprateurDao() {
		return oeprateurDao;
	}

	public void setOeprateurDao(OperateurDao oeprateurDao) {
		this.oeprateurDao = oeprateurDao;
	}

	@Override
	@Transactional
	public Personnel getPersonnelByEnrollID(int id, Terminaux t) {
		// TODO Auto-generated method stub
		return personnelDao.getPersonnelByEnrollID(id, t);
	}

	@Override
	@Transactional
	public List<Terminaux> getAllPointTerminaux(Personnel p) {
		// TODO Auto-generated method stub
		return personnelDao.getAllPointTerminaux(p);
	}

	@Override
	@Transactional
	public List<PersonnelTerminal> getAllAffectedPointer(Personnel p) {
		// TODO Auto-generated method stub
		return personnelDao.getAllAffectedPointer(p)
				;
	}

	@Override
	@Transactional
	public List<Personnel> findAllWithoutPersonnels() {
		// TODO Auto-generated method stub
		return personnelDao.findAllPersonnels();
	}

	@Override
	@Transactional
	public Personnel findpersonnelByckId(int id, int deviceId) {
		// TODO Auto-generated method stub
		return personnelDao.findpersonnelByckId(id, deviceId);
	}
	@Override
	@Transactional
	public void updateconge(int x,int idper ) {
		
		 personnelDao.updateconge(x, idper);
	}

	@Override
	@Transactional
	public List<Personnel> PersonnelswithoutEmpreinte() {
		// TODO Auto-generated method stub
		return personnelDao.PersonnelswithoutEmpreinte();
	}

	@Override
	@Transactional
	public List<PersonnelTerminal> listtoupdate(Terminaux t) {
		// TODO Auto-generated method stub
		return personnelDao.listtoupdate(t);
	}

	@Override
	@Transactional
	public List<Identification> findidentification(Personnel p) {
		// TODO Auto-generated method stub
		return personnelDao.findidentification(p);
	}

	@Override
	@Transactional
	public List<PersonnelTerminal> listtodelete(Terminaux t) {
		// TODO Auto-generated method stub
		return personnelDao.listtodelete(t);
	}

	@Override
	@Transactional
	public Identification findCarteidentification(Personnel p) {
		// TODO Auto-generated method stub
		return personnelDao.findCarteidentification(p);
	}

	@Override
	@Transactional
	public Identification findPasswordidentification(Personnel p) {
		// TODO Auto-generated method stub
		return personnelDao.findPasswordidentification(p);
	}

	@Override
	@Transactional
	public Historique getcurrentHistorique(Personnel p) {
		// TODO Auto-generated method stub
		return personnelDao.getcurrentHistorique(p);
				
	}

	@Override
	@Transactional
	public Affiliation findcurrentAffiliation(Personnel p) {
		// TODO Auto-generated method stub
		return personnelDao.findcurrentAffiliation(p);
	}

	@Override
	@Transactional
	public List<Terminaux> findTerminauxNotAfficated(Personnel p) {
		// TODO Auto-generated method stub
		return personnelDao.findTerminauxNotAfficated(p);
	}
	
	@Override
	@Transactional
	public List<PersonnelTerminal> getAllPointPersonnelTerminaux(Personnel p)
	{
		return personnelDao.getAllPointPersonnelTerminaux(p);
	}

}
