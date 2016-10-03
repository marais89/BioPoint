package org.bio.service;

import java.io.Serializable;
import java.util.List;

import org.bio.dao.DocumentDao;
import org.bio.model.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class DocumentServiceImpl implements DocumentService,Serializable{
	@Autowired
private DocumentDao documentDao;
	@Override
	@Transactional
	public void insertDocument(Document document) {
		documentDao.insertDocument(document);
		
	}

	@Override
	@Transactional
	public void updateDocument(Document document) {
		documentDao.updateDocument(document);
	}

	@Override
	@Transactional
	public List<Document> findAllDocuments() {
		
		return documentDao.findAllDocuments();
	}

	@Override
	@Transactional
	public void deleteDocument(Document document) {
		documentDao.deleteDocument(document);
		
	}

	@Override
	@Transactional
	public Document getByid(int id) {
		return documentDao.getByid(id);
	}

	public DocumentDao getDocumentDao() {
		return documentDao;
	}

	public void setDocumentDao(DocumentDao documentDao) {
		this.documentDao = documentDao;
	}

}
