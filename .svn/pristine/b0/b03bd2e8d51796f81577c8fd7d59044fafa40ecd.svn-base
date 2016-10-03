package org.bio.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bio.model.Operateur;
import org.bio.model.OperateurRoles;
import org.bio.model.Role;
import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("deprecation")
@Component
public class Assembler {
	
	 @Transactional(readOnly = true)
	 public User buildUserFromUserEntity(Operateur op,List<OperateurRoles> roles) {

	    String username = op.getLogin();
	    String password = op.getMotPasse();
	    boolean enabled = true;
	    boolean accountNonExpired = true;
	    boolean credentialsNonExpired = true;
	    boolean accountNonLocked = true;
	    Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
	    
	    for (OperateurRoles role: roles) {
	    	authorities.add(new GrantedAuthorityImpl(role.getRole().getNom()));
	    }

	    User user = new User(username, password, enabled,
	      accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	    return user;
	  }
	}