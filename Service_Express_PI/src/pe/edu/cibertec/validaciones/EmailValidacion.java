package pe.edu.cibertec.validaciones;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.primefaces.validate.ClientValidator;

import pe.edu.cibertec.beans.Cliente;
import pe.edu.cibertec.managedBean.ClienteMB;

@FacesValidator("custon.emailValidacion")
public class EmailValidacion implements Validator, ClientValidator {

	 	private List<Cliente>lstCliente=new ArrayList<>();
	 	
	 	ClienteMB clienteMB= new ClienteMB();
	 	
		private Pattern pattern;
	
	    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
	                                                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	  
	    public EmailValidacion() {
	        pattern = Pattern.compile(EMAIL_PATTERN);
	        lstCliente=clienteMB.getAllClientes();
	    }
	 
	    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
	        if(value == null) {
	            return;
	        }
	         
	        
	        if(pattern.matcher(value.toString()).matches()) {
	        	
		        for (Cliente cliente : lstCliente) {
					
		        	if(cliente.getEmail().equals(value.toString()) ){
		        		 throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validation Error", 
			                        value + " Email en uso;"));
		        	}
		        	
		        	
				}
		        	            
	        }else{
	        	
	        	
	        	throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validation Error", 
                        value + " Email no valido"));
	        	
	        }
	        
	        
	        
	    }
	 
	
	    public String getValidatorId() {
	        return "custom.emailValidator";
	    }

		@Override
		public Map<String, Object> getMetadata() {
			// TODO Auto-generated method stub
			return null;
		}
	
}
