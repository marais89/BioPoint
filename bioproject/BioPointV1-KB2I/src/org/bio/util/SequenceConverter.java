package org.bio.util;

import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.WeakHashMap;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.bio.model.Sequence;

@FacesConverter(value = "sequenceConverter")
public class SequenceConverter implements Converter {

    private static Map<Sequence, String> entities = new WeakHashMap<Sequence, String>();

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object entity) {
        synchronized (entities) {
            if (!entities.containsKey(entity)) {
                String uuid = UUID.randomUUID().toString();
                entities.put((Sequence) entity, uuid);
                return uuid;
            } else {
                return entities.get(entity);
            }
        }
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String uuid) {
        for (Entry<Sequence, String> entry : entities.entrySet()) {
            if (entry.getValue().equals(uuid)) {
                return entry.getKey();
            }
        }
        return null;
    }

}