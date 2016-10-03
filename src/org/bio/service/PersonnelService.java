package org.bio.service;

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

public interface PersonnelService {
	public void insertPersonnel(Personnel personnel);
	public void updatePersonnel(Personnel personnel);
	public List<Personnel> findAllPersonnels();
	public List<Personnel> findAllWithoutPersonnels();

	public void deletePersonnel(Personnel personnel);
	public Personnel getByid(int  id);
	public List<Historique>listHistorique(Personnel per);
	public List<Mvt>listMvt(Personnel per);
	public List<Document> findAllDocument(Personnel per);
	public List<HistoriqueCat> findAllHistCat(Personnel per);
	public List<Mvt> getMvtByDay(Date d,Personnel p);
	public Conge findAutorisationByDay(Date d,Personnel p);
	public Conge findCongeByDay(Date d,Personnel p);
	public HistoriqueCat getcurrentHistoriqueCat(Personnel p);
	public Personnel getPersonnelByEnrollID(int id,Terminaux t);

	public List<Terminaux> getAllPointTerminaux(Personnel p);
	public List<PersonnelTerminal> getAllAffectedPointer(Personnel p);
	public Personnel findpersonnelByckId(int id,int deviceId);
	public void updateconge(int x,int idper );
	public List<Personnel> PersonnelswithoutEmpreinte();
	public List<PersonnelTerminal> listtoupdate(Terminaux t);
	public List<Identification> findidentification(Personnel p);
	public List<PersonnelTerminal> listtodelete(Terminaux t);
	public Identification findCarteidentification(Personnel p);

	public Identification findPasswordidentification(Personnel p);
	public Historique getcurrentHistorique(Personnel p);
	public Affiliation findcurrentAffiliation(Personnel p);
	public List<Terminaux> findTerminauxNotAfficated(Personnel p);
	public List<Personnel> findAllPersonnels2() ;

	public List<PersonnelTerminal> getAllPointPersonnelTerminaux(Personnel p);

}
