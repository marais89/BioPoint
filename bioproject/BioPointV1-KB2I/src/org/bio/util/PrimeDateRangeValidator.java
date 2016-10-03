package org.bio.util;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
 
 
import org.primefaces.component.calendar.Calendar;
 
@FacesValidator("primeDateRangeValidator")
public class PrimeDateRangeValidator implements Validator {
	FacesMessage msg = new FacesMessage("date fin doit étre aprés date debut");

	@Override
	    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
	        if (value == null) {
	            return;
	        }
	         
	        //Leave the null handling of startDate to required="true"
	        Object startDateValue = component.getAttributes().get("psstDate");
	        if (startDateValue==null) {
	            return;
	        }
	         
	        Date startDate = (Date)startDateValue;
	        Date endDate = (Date)value;
	        System.out.println(startDate+"     "+endDate);
	        if (endDate.before(startDate)) {
				throw new ValidatorException(msg);
					
	    }
	}
}