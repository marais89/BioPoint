package org.bio.service;
import java.io.Serializable;
import java.util.List;

import org.bio.dao.CategorieDao;
import org.bio.model.Categorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class CategorieServiceImpl implements CategorieService, Serializable {
	
@Autowired
private CategorieDao categorieDao;

public CategorieDao getCategorieDao() {
	return categorieDao;
}

public void setCategorieDao(CategorieDao categorieDao) {
	this.categorieDao = categorieDao;
}
@Override
@Transactional
public void insertCategorie(Categorie categorie){
   categorieDao.insertCategorie(categorie);

}
@Override
@Transactional
public void updateCategorie(Categorie categorie) {
	categorieDao.updateCategorie(categorie);

}
@Override
@Transactional
public List<Categorie> findAllCategories() {
	return categorieDao.findAllCategories();
}

@Override
@Transactional
public void deleteCategorie(Categorie categorie) {
	categorieDao.deleteCategorie(categorie);

}
@Override
@Transactional
public Categorie getByid(int id) {
	
	return categorieDao.getByid(id);
}


}
