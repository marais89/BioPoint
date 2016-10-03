package org.bio.service;

import org.bio.dao.BatchDao;
import org.bio.model.Batch;
import org.bio.model.BatchId;
import org.bio.model.Mvt;
import org.bio.model.Parametre;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.event.spi.PostDeleteEvent;
import org.hibernate.event.spi.PostDeleteEventListener;
import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PostInsertEventListener;
import org.hibernate.event.spi.PostUpdateEvent;
import org.hibernate.event.spi.PostUpdateEventListener;
import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreInsertEventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class BatchServiceEventListener implements HibernateEventListener, 
    PostDeleteEventListener, PostInsertEventListener, PostUpdateEventListener{
@Autowired
private BatchService batchService;

	@Override
	public void onPostUpdate(PostUpdateEvent event) {
		// TODO Auto-generated method stub
		System.out.println("update "+event.getEntity().getClass().getCanonicalName());
	}

	@Override
	@Transactional
	public void onPostInsert(PostInsertEvent event) {
		// TODO Auto-generated method stub
		
		System.out.println("insert"+event.getEntity().getClass().getCanonicalName());

if(event.getEntity() instanceof Mvt)
{
	try{
//save((Mvt) event.getEntity());
	}
	catch(Exception e)
	{
		e.getStackTrace();
		System.out.println(e.getMessage());
	}
}
	}

	@Override
	public void onPostDelete(PostDeleteEvent event) {
		// TODO Auto-generated method stub
		System.out.println("delete "+event.getEntity().getClass().getCanonicalName());

	}

	public BatchService getBatchService() {
		return batchService;
	}

	public void setBatchService(BatchService batchService) {
		this.batchService = batchService;
	}



	

}
