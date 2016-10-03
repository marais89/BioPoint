package org.bio.util;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.integrator.spi.Integrator;
import org.hibernate.metamodel.source.MetadataImplementor;
import org.hibernate.service.spi.SessionFactoryServiceRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class CustomIntegrator implements Integrator {

private InsertListner mvtlistener;
	

public InsertListner getMvtlistener() {
	return mvtlistener;
}

public void setMvtlistener(InsertListner mvtlistener) {
	this.mvtlistener = mvtlistener;
}

	@Override
	public void disintegrate(SessionFactoryImplementor arg0,
			SessionFactoryServiceRegistry arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void integrate(Configuration arg0, SessionFactoryImplementor arg1,
			SessionFactoryServiceRegistry registry) {
		// TODO Auto-generated method stub
        final EventListenerRegistry eventRegistry = registry.getService(EventListenerRegistry.class);

        eventRegistry.appendListeners(EventType.POST_INSERT, mvtlistener);

        eventRegistry.appendListeners(EventType.POST_UPDATE, mvtlistener);
        eventRegistry.appendListeners(EventType.POST_DELETE, mvtlistener);
        eventRegistry.appendListeners(EventType.PERSIST, mvtlistener);


	}

	@Override
	public void integrate(MetadataImplementor arg0,
			SessionFactoryImplementor arg1, SessionFactoryServiceRegistry registry) {
		// TODO Auto-generated method stub
        final EventListenerRegistry eventRegistry = registry.getService(EventListenerRegistry.class);

        eventRegistry.appendListeners(EventType.POST_INSERT, mvtlistener);

        eventRegistry.appendListeners(EventType.POST_UPDATE, mvtlistener);
        eventRegistry.appendListeners(EventType.POST_DELETE, mvtlistener);
	}




}