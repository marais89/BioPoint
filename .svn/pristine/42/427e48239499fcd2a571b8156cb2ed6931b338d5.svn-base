package org.bio.web;


import org.primefaces.component.commandlink.CommandLink;
import org.primefaces.util.SecurityUtils;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
public class MyAuthSuccessHandler 
       extends SavedRequestAwareAuthenticationSuccessHandler {
	
    private CommandLink li;

    @Override
    protected String determineTargetUrl(HttpServletRequest request,
                                            HttpServletResponse response) {

        boolean hasAdmin = SecurityUtils.ifAllGranted("ROLE_ADMIN");
        boolean hasClient = SecurityUtils.ifAllGranted("ROLE_CLIENT");
        
       
        if(hasAdmin){
            return "/pages/personel.xhtml";
           
        }else if (hasClient){
            return "/index.jsf";
    
        
        }else{
            return super.determineTargetUrl(request, response);
        }
    }
 
    private String userUrl;
    private String adminUrl;
     
    public void setUserUrl(String userUrl){
        this.userUrl = userUrl;
    }
     
    public void setAdminUrl(String adminUrl){
        this.adminUrl = adminUrl;
    }

	public CommandLink getLi() {
		return li;
	}

	public void setLi(CommandLink li) {
		this.li = li;
	}
}






