package org.bio.util;

import java.util.Map;

import org.bio.model.Categorie;
import org.bio.model.Mvt;
import org.hibernate.HibernateException;
import org.hibernate.event.spi.PersistEvent;
import org.hibernate.event.spi.PersistEventListener;
import org.hibernate.event.spi.PostDeleteEvent;
import org.hibernate.event.spi.PostDeleteEventListener;
import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PostInsertEventListener;
import org.hibernate.event.spi.PostUpdateEvent;
import org.hibernate.event.spi.PostUpdateEventListener;
import org.springframework.stereotype.Component;

public class InsertListner implements PostInsertEventListener,PostUpdateEventListener,PostDeleteEventListener,PersistEventListener {

/*	@Override
    public void onPersist(PersistEvent event) throws HibernateException {

        if(event.getObject() instanceof Mvt) {
            Mvt entity = (Mvt) event.getObject();
            // do something with entity
        }
    }

	@Override
	public void onPersist(PersistEvent arg0, Map arg1)
			throws HibernateException {
		// TODO Auto-generated method stub
		
	}*/

	@Override
	public void onPostDelete(PostDeleteEvent event) {
		// TODO Auto-generated method stub

        if(event.getEntity() instanceof Categorie) {
        	System.out.println("Delete");
        }
	}

	@Override
	public void onPostUpdate(PostUpdateEvent event) {
		// TODO Auto-generated method stub
		 if(event.getEntity() instanceof Categorie) {
	        	System.out.println("update");
	        }
	}

	@Override
	public void onPostInsert(PostInsertEvent event) {
		// TODO Auto-generated method stub
		 if(event.getEntity() instanceof Categorie) {
	        	System.out.println("Insert");
	        }
	}

	@Override
	public void onPersist(PersistEvent event) throws HibernateException {
		// TODO Auto-generated method stub
		 if(event.getObject() instanceof Categorie) {
	        	System.out.println("Insert");
	        }
		
	}

	@Override
	public void onPersist(PersistEvent arg0, Map arg1)
			throws HibernateException {
		// TODO Auto-generated method stub
		
	}

	

	
	

}