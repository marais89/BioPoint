package org.bio.dao;

import java.util.List;

import org.bio.model.Identification;;

public interface IdentificationDao {
	
	public void insertIdentification(Identification identification);
	public void updateIdentification(Identification identification);
	public List<Identification> findAllIdentification();
	public void deleteIdentification(Identification identification);
	public Identification getByid(int  id);

}
