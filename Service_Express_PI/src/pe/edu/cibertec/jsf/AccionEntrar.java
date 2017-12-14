package pe.edu.cibertec.jsf;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.struts2.interceptor.SessionAware;

import pe.edu.cibertec.beans.Cliente;
import pe.edu.cibertec.managedBean.ClienteMB;
import pe.edu.cibertec.temas.CambiadorTemas;


@ManagedBean(name="entrarJSF")
@SessionScoped
public class AccionEntrar  implements SessionAware{
	
	private Map<String, Object> session;
	private Cliente clienteSession;
	private String mensajeError;
	

	public AccionEntrar(){
		CambiadorTemas.tema = "bluesky";
		
	}
	
	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.session=session;
	}



	public Cliente getClienteSession() {
		return clienteSession;
	}



	public void setClienteSession(Cliente clienteSession) {
		this.clienteSession = clienteSession;
	}



	public String getMensajeError() {
		return mensajeError;
	}



	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}



	public Map<String, Object> getSession() {
		return session;
	}

	
	
	
	
}
