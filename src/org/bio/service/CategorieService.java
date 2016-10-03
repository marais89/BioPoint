package org.bio.service;

import java.util.List;

import org.bio.model.Categorie;

public interface CategorieService {
	public void insertCategorie(Categorie categorie);
	public void updateCategorie(Categorie categorie);
	public List<Categorie> findAllCategories();
	public void deleteCategorie(Categorie categorie);
	public Categorie getByid(int  id);

}
