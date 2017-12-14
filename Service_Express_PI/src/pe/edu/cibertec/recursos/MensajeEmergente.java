package pe.edu.cibertec.recursos;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class MensajeEmergente {

	 public void addMessageInfo(String summary) {
	        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  "");
	        FacesContext.getCurrentInstance().addMessage(null, message);
	 }
	 
	 public void addMessageWarn(String summary) {
	        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, summary,  null);
	        FacesContext.getCurrentInstance().addMessage(null, message);
	 }
	 
	 public void addMessageFatal(String summary) {
	        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, summary,  null);
	        FacesContext.getCurrentInstance().addMessage(null, message);
	 }
	 public void addMessageError(String summary) {
	        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary,  null);
	        FacesContext.getCurrentInstance().addMessage(null, message);
	 }
	 
	
	 
	 
	 
}
