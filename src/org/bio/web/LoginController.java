package org.bio.web;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.WebAttributes;

@ManagedBean(name="loginController")
@RequestScoped
public class LoginController implements PhaseListener {

	protected final Log logger = LogFactory.getLog(getClass());
    

	public String getlogin()
	{    
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();	      
		return auth.getName();	
	}
	
	public String  compte()
	{
		return "CreationCompte";
	}
    public String doLogin() throws ServletException, IOException {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

        RequestDispatcher dispatcher = ((ServletRequest) context.getRequest())
                .getRequestDispatcher("/j_spring_security_check");

        dispatcher.forward((ServletRequest) context.getRequest(),
                (ServletResponse) context.getResponse());

        FacesContext.getCurrentInstance().responseComplete();

        return null;
    }

    public void afterPhase(PhaseEvent event) {
    }

    /* (non-Javadoc)
     * @see javax.faces.event.PhaseListener#beforePhase(javax.faces.event.PhaseEvent)
     * 
     * Do something before rendering phase.
     */
    public void beforePhase(PhaseEvent event) {
        Exception e = (Exception) FacesContext.getCurrentInstance().
          getExternalContext().getSessionMap().get(WebAttributes.AUTHENTICATION_EXCEPTION);
 
        if (e instanceof BadCredentialsException) {
            logger.debug("Found exception in session map: "+e);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(
                    WebAttributes.AUTHENTICATION_EXCEPTION, null);
            FacesContext.getCurrentInstance().addMessage(null, 
              new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Username or password not valid.", "Username or password not valid"));
        }
    }

    /* (non-Javadoc)
     * @see javax.faces.event.PhaseListener#getPhaseId()
     * 
     * In which phase you want to interfere?
     */
    public PhaseId getPhaseId() {
        return PhaseId.RENDER_RESPONSE;
    }
}
