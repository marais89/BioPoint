package org.bio.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.servlet.ServletContext;

import org.bio.model.Personnel;
import org.bio.service.PersonnelService;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean
@ApplicationScoped
	public class Images {
		@ManagedProperty(value="#{personnelServiceImpl}")
		private  PersonnelService personnelService;
	   private StreamedContent image;
	    public PersonnelService getPersonnelService() {
			return personnelService;
		}

		public void setPersonnelService(PersonnelService personnelService) {
			this.personnelService = personnelService;
		}

		public StreamedContent getImage() throws IOException {
	        FacesContext context = FacesContext.getCurrentInstance();

	        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
	            // So, we're rendering the view. Return a stub StreamedContent so that it will generate right URL.
	            return new DefaultStreamedContent();
	        }
	        else {
	            // So, browser is requesting the image. Return a real StreamedContent with the image bytes.
	            String id = context.getExternalContext().getRequestParameterMap().get("id");
				Personnel p =getPersonnelService().getByid(Integer.valueOf(id));
				if(p.getPhoto()!=null)
	            return new DefaultStreamedContent(new ByteArrayInputStream(p.getPhoto()),"image/png",p.getPhotoName());
				else{
					InputStream stream = ((ServletContext) FacesContext
							.getCurrentInstance().getExternalContext().getContext())
							.getResourceAsStream("/resources/img/line.jpg");
					DefaultStreamedContent file = new DefaultStreamedContent(stream,
							"image/jpg", "avatar.jpg");
									
					return file;
				}
	        }
	    }

		public void setImage(StreamedContent image) {			
		}


}
