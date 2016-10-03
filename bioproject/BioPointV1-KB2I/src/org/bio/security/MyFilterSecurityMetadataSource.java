package org.bio.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.bio.model.Role;
import org.bio.service.RoleService;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.AntUrlPathMatcher;
import org.springframework.security.web.util.UrlMatcher;
import org.springframework.util.AntPathMatcher;

public class MyFilterSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

     // Lookup your database (or other source) using this information and populate the
        // list of attributes
    private UrlMatcher urlMatcher = new AntUrlPathMatcher();
    private RoleService roleService;
private HashMap<String,Collection<ConfigAttribute>> resourceMap;
    private void loadResourceDefine() {
    	List<Role> listroles = roleService.findAllRole();
        resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
        Collection<ConfigAttribute> atts = null;
        for(Role rescRole :listroles){
                String url = rescRole.getUrl();
                String role = rescRole.getNom();
                if(!resourceMap.containsKey(url)){
                        atts = new ArrayList<ConfigAttribute>();
                }
                ConfigAttribute ca = new SecurityConfig(role);
                atts.add(ca);
                resourceMap.put(url, atts);
        }
}

// According to a URL, Find out permission configuration of this URL.
public Collection<ConfigAttribute> getAttributes(Object object)
                throws IllegalArgumentException {
        // guess object is a URL.
        String url = ((FilterInvocation) object).getRequestUrl();
        Iterator<String> ite = resourceMap.keySet().iterator();
        while (ite.hasNext()) {
                String resURL = ite.next();
                if (urlMatcher.pathMatchesUrl(resURL, url)) {
                        return resourceMap.get(resURL);
                }
        }
        return null;
}

public boolean supports(Class<?> clazz) {
 	return FilterInvocation.class.isAssignableFrom(clazz);

}

public Collection<ConfigAttribute> getAllConfigAttributes() {
    return null;
}

public MyFilterSecurityMetadataSource(RoleService roleserv) {
	   this.roleService = roleserv;
       loadResourceDefine();
	// TODO Auto-generated constructor stub
}
  }