package org.bio.service;

import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import org.hibernate.SessionFactory;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.hibernate.validator.HibernateValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.event.spi.PostDeleteEventListener;
import org.hibernate.event.spi.PostInsertEventListener;
import org.hibernate.event.spi.PostUpdateEventListener;
import org.hibernate.event.spi.PreDeleteEventListener;
import org.hibernate.event.spi.PreInsertEventListener;
import org.hibernate.event.spi.PreUpdateEventListener;

@Service
public class HibernateSpringIntegrator {


    @Autowired
    private SessionFactory entityManagerFactory;

    @Autowired
    private HibernateSpringIntegratorRegistry hibernateSpringIntegratorRegistry;

    @PostConstruct
    public void registerListeners() {

        EventListenerRegistry listenerRegistry = ((SessionFactoryImplementor) entityManagerFactory).getServiceRegistry().getService(
                EventListenerRegistry.class);
        List<HibernateEventListener> eventListeners = hibernateSpringIntegratorRegistry
                .getHibernateEventListeners();
        for (HibernateEventListener hel : eventListeners) {
            if (PreInsertEventListener.class.isAssignableFrom(hel.getClass())) {
                listenerRegistry.appendListeners(EventType.PRE_INSERT,
                        (PreInsertEventListener) hel);
            }
            if (PreUpdateEventListener.class.isAssignableFrom(hel.getClass())) {
                listenerRegistry.appendListeners(EventType.PRE_UPDATE,
                        (PreUpdateEventListener) hel);
            }
            if (PreDeleteEventListener.class.isAssignableFrom(hel.getClass())) {
                listenerRegistry.appendListeners(EventType.PRE_DELETE,
                        (PreDeleteEventListener) hel);
            }
            if (PostInsertEventListener.class.isAssignableFrom(hel.getClass())) {
                listenerRegistry.appendListeners(EventType.POST_INSERT,
                        (PostInsertEventListener) hel);
            }
            if (PostUpdateEventListener.class.isAssignableFrom(hel.getClass())) {
                listenerRegistry.appendListeners(EventType.POST_UPDATE,
                        (PostUpdateEventListener) hel);
            }
            if (PostDeleteEventListener.class.isAssignableFrom(hel.getClass())) {
                listenerRegistry.appendListeners(EventType.POST_DELETE,
                        (PostDeleteEventListener) hel);
            }
            // Currently we do not need other types of eventListeners. Else this method needs to be extended.
        }
    }

	public SessionFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}

	public void setEntityManagerFactory(SessionFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	public HibernateSpringIntegratorRegistry getHibernateSpringIntegratorRegistry() {
		return hibernateSpringIntegratorRegistry;
	}

	public void setHibernateSpringIntegratorRegistry(
			HibernateSpringIntegratorRegistry hibernateSpringIntegratorRegistry) {
		this.hibernateSpringIntegratorRegistry = hibernateSpringIntegratorRegistry;
	}
}