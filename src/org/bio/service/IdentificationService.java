package org.bio.service;

import java.util.List;

import org.bio.model.Identification;;

public interface IdentificationService {
	public void insertIdentification(Identification identification);
	public void updateIdentification(Identification identification);
	public List<Identification> findAllIdentifications();
	public void deleteIdentification(Identification identification);
	public Identification getByid(int  id);
}
