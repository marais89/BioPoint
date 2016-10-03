package org.bio.util;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.WeakHashMap;
import java.util.Map.Entry;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.bio.model.Personnel;


@FacesConverter(value = "enseigneConverter",forClass=Personnel.class)
public class EnseigneConverter implements Converter {

private static final String key = "org.smart.util.EnseigneConverter";
private static final String empty = "";

private Map<String, Object> getViewMap(FacesContext context) {
   Map<String, Object> viewMap = context.getViewRoot().getViewMap();
   @SuppressWarnings({ "unchecked", "rawtypes" })
   Map<String, Object> idMap = (Map) viewMap.get(key);
   if (idMap == null) {
       idMap = new HashMap<String, Object>();
       viewMap.put(key, idMap);
   }
   return idMap;
}

@Override
public Object getAsObject(FacesContext context, UIComponent c, String value) {
   if (value.isEmpty()) {
       return null;
   }
   return getViewMap(context).get(value);
}

@Override
public String getAsString(FacesContext context, UIComponent c, Object value) {
   if (value == null) {
       return empty;
   }
   String id = String.valueOf(((Personnel) value).getIdper());
   getViewMap(context).put(id, value);
   return id;
}
}
