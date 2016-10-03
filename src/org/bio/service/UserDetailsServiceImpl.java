package org.bio.service;

import java.io.Serializable;








import org.bio.dao.OperateurDao;
import org.bio.model.Operateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;    
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UserDetailsServiceImpl implements UserDetailsService,Serializable {

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
@Autowired 
private OperateurDao operateurDao;
  
public Assembler getAssembler() {
	return assembler;
}

public void setAssembler(Assembler assembler) {
	this.assembler = assembler;
}

@Autowired 
private Assembler assembler;

  @Transactional(readOnly = true)
  public UserDetails loadUserByUsername(String login)  throws UsernameNotFoundException, DataAccessException {

  Operateur op = operateurDao.getByLogin(login);
    if (op == null)
      throw new UsernameNotFoundException("user not found");
    op.setListroles(operateurDao.getopRoles(op));
    return assembler.buildUserFromUserEntity(op,getOperateurDao().getopRoles(op));
  }

public OperateurDao getOperateurDao() {
	return operateurDao;
}

public void setOperateurDao(OperateurDao operateurDao) {
	this.operateurDao = operateurDao;
}
}
