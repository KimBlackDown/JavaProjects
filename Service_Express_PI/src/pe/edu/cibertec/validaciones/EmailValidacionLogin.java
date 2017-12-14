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

@FacesValidator("custon.emailValidacionlogin")
public class EmailValidacionLogin implements Validator, ClientValidator {

	 	

 	private List<Cliente>lstCliente=new ArrayList<>();
 	private Cliente clienteBean= new Cliente();
 	ClienteMB clienteMB= new ClienteMB();
 	
	private Pattern pattern;

    private static final String EMAIL_PATTERN = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@[a-z0-9-]+(.[a-z0-9-]+)*(.[a-z]{2,4})$";
//    		"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
//                                                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";


	    public EmailValidacionLogin() {
	        pattern = Pattern.compile(EMAIL_PATTERN);
	        clienteBean =new Cliente();
	        
	    }
	 
	    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
	        if(value == null || value.equals("")) {
	        	
	        	 throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validation Error", 
	                        value + " Campo vacio;"));
	        
	        }
	        
	  
	        	
	        if(pattern.matcher(value.toString()).matches()) {
	        
	        	lstCliente = clienteMB.getAllClientes();
	        	
	        	for (Cliente cliente : lstCliente) {
					
	        		
	        		if(cliente.getEmail().equals(value.toString()) ){
		        		return ;
	        		}
        		
				}
	        	
	    		throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validation Error",
	    				value + " Email no existe, Ingrese email existente."));
	        }else{
	        	
	        	
	        	throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validation Error", 
                        value + " Email no valido, Ingrese email valido."));
	        	
	        }
      
	        
	    }
	 


	    public String getValidatorId() {
	        return "custom.emailValidacionlogin";
	    }



		@Override
		public Map<String, Object> getMetadata() {
			// TODO Auto-generated method stub
			return null;
		}
	
}
