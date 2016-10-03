package org.bio.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HibernateSpringIntegratorRegistry {

    @Autowired(required = false)
    private List<HibernateEventListener> hibernateEventListeners;

    public List<HibernateEventListener> getHibernateEventListeners() {
        if (hibernateEventListeners == null) {
            return Collections.emptyList();
        }
        return hibernateEventListeners;
    }
}