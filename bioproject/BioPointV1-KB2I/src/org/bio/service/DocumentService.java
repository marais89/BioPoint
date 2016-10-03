package org.bio.service;

import java.util.List;

import org.bio.model.Document;

public interface DocumentService {
	public void insertDocument(Document document);
	public void updateDocument(Document document);
	public List<Document> findAllDocuments();
	public void deleteDocument(Document document);
	public Document getByid(int  id);
}
