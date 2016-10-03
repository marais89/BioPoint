package org.bio.dao;

import java.util.List;

import org.bio.model.Affiliation;
import org.bio.model.Operateur;
import org.bio.model.OperateurRoles;
import org.bio.model.Societe;

public interface OperateurDao {

	
	public void insertOperateur(Operateur u);
	public void updateOperateur(Operateur u);
	public List<Operateur> findAllOperateurs();
	public void deleteOperateur(Operateur u);
	public Operateur getByid(int  id);
	public Operateur getByLogin(String  login);
	public List<OperateurRoles>getopRoles(Operateur op,String pos);
	public List<OperateurRoles>getopRoles(Operateur op);
	public List<Affiliation>getopaffiliation(Operateur op);
	public Societe getCurrentSociete(Operateur op);
	public List<OperateurRoles> getopRoles0(Operateur op);

}
