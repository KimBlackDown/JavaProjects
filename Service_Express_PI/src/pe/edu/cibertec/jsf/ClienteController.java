package pe.edu.cibertec.jsf;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;


import pe.edu.cibertec.beans.Cliente;
import pe.edu.cibertec.managedBean.ClienteMB;
import pe.edu.cibertec.recursos.MensajeEmergente;
import pe.edu.cibertec.temas.CambiadorTemas;

import javax.servlet.http.HttpSession;

import email.bean.MailBean;
import email.utils.EmailUtils;



@ManagedBean(name="clienteJSF")
@SessionScoped
public class ClienteController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private boolean visible=false;
	
	
	String TITLE_PAGE="";
	private Boolean estadoBotonAgregar;
	private Boolean estadoBotonActualizar;
	private String saludos;
	
	ClienteMB clienteMB=new ClienteMB();
	MensajeEmergente mensajeEmergente=new MensajeEmergente();
	Cliente clienteSession=new Cliente();

	Cliente cliente;
	String usuario;
	String contraseña;
	
	private int cod_cliente;
	private String nombre;
	private String apellido;
	private String email;
	private String celular;
	private String contra;
	private String foto;
	private String nombreParam;
	
	List<Cliente>lstCliente;
	
	
	/*
	 * METODOS DEL ADMINISTRADOR
	 */
	/************************************************/	
	
	public ClienteController(){
		CambiadorTemas.tema = "bluesky";
		
		visible=false;
		cliente=new Cliente();
		lstCliente =clienteMB.getAllClientes();
		
	}
		
	public String actionAgregarCliente(){
		
		cliente.setApellido(apellido);
		cliente.setCelular(celular);
		cliente.setEmail(email);
		cliente.setNombre(nombre);
		
		clienteMB.insertar(cliente);
		
		lstCliente =clienteMB.getAllClientes();
		System.out.println("Cliente Agregado");
		
		return "ListaClientes";
	}
	
	public String actionActualizarCliente(){
		Cliente obj=clienteMB.buscarCliente(cod_cliente);

		obj.setApellido(apellido);
		obj.setCelular(celular);
		obj.setEmail(email);
		obj.setNombre(nombre);
		
		clienteMB.actualizar(obj);
		
		lstCliente =clienteMB.getAllClientes();
		
		System.out.println("Cliente Actualizado:Cod "+ obj.getCod_cliente());
		
		return "ListaClientes";
	}
	
	public String eventoVerCliente(){
		TITLE_PAGE="Actualizar Cliente";
		System.out.println(cod_cliente+"");
		
		Cliente obj=clienteMB.buscarCliente(cod_cliente);
		nombre=obj.getNombre();
		apellido=obj.getApellido();
		email=obj.getEmail();
		celular=obj.getCelular();
		
		System.out.println(obj.getApellido() +" "+ obj.getCelular());
		
		return "AgregarCliente";
	}

	public String redirecAgregarCliente(){

		estadoBotonAgregar=false;
		estadoBotonActualizar=true;
		cliente=new Cliente();
		TITLE_PAGE="Agregar Cliente";
		System.out.println("asds");
		return "AgregarCliente.xhtml";
	}
		
	public String actionBuscarCiente(){
		
		if(!nombreParam.equals("") && nombreParam!=null){
			
			lstCliente=clienteMB.getClienteByName(nombreParam);
			
			System.out.println(lstCliente.size());
			if(lstCliente.size()>0){
				
				mensajeEmergente.addMessageInfo("Coincidencia(s) encontrada(s)!!");
			}else{
				lstCliente =clienteMB.getAllClientes();
				mensajeEmergente.addMessageInfo("Coincidencia(s) encontrada(s)!! Listando todo");
			}
			
		}else{
			
			mensajeEmergente.addMessageWarn("Listando Todo!!");
			
			lstCliente =clienteMB.getAllClientes();
		}
		
		
		return "ListaClientes";
	}
	
	/************************************************/	
	
	
	

	/*
	 * METODOS DEL CLIENTE
	 */
	/************************************************/	

	
	public void eventoLimpiarFormulario(){
		System.out.println("Entro a limpiar");
		
		this.apellido="";
		this.nombre="";
		this.celular="";
		this.contra="";
		this.email="";
		
		
	}

	public String actualizarCliente(){
		
		
		   FacesContext context=FacesContext.getCurrentInstance();
			
		   HttpSession session=(HttpSession) context.getExternalContext().getSession(false);
        	clienteSession =  (Cliente) session.getAttribute("clienteSessionA");
        	 
	       clienteSession.setNombre(nombre);
	       clienteSession.setApellido(apellido);
	       clienteSession.setCelular(celular);
	       clienteSession.setEmail(email);
		   
	       clienteMB.actualizar(clienteSession);
	       
	       session.setAttribute("clienteSessionA",clienteSession);
		

	   	System.out.println("Actualizo");
	       
	    clienteSession =  (Cliente) session.getAttribute("clienteSessionA");
     	
	   		apellido=clienteSession.getApellido();
		   nombre=clienteSession.getNombre();
		   celular =clienteSession.getCelular();
		   email=clienteSession.getEmail();
		
		    
	        context.addMessage(null, new FacesMessage("Successful",  "Actualizacion Correcta") );
	     
	       
		return "ClientePerfil?faces-redirect=true";
	}
	
	public String cargarPerfil(){
	
		   FacesContext context=FacesContext.getCurrentInstance();
		   HttpSession session=(HttpSession) context.getExternalContext().getSession(false);
     	
		   clienteSession =  (Cliente) session.getAttribute("clienteSessionA");
     	
		   apellido=clienteSession.getApellido();
		   nombre=clienteSession.getNombre();
		   celular =clienteSession.getCelular();
		   email=clienteSession.getEmail();
		
		return "ClientePerfil?faces-redirect=true";
	}
	
	public String registroCliente(){
		System.out.println("Entro a Registrar Cliente");
		
		lstCliente=clienteMB.getAllClientes();
		
			cliente = new Cliente();
			cliente.setApellido(apellido);
			cliente.setNombre(nombre);
			cliente.setCelular(celular);
			cliente.setContra(contra);
			cliente.setEmail(email);
			
			clienteMB.insertar(cliente);
		
			EmailUtils emailUtils= new EmailUtils();
			
//
//			int a= emailUtils.enviarCorreo(new MailBean("serviceexpressperu@gmail.com", 
//					cliente.getEmail(), 
//					"REGISTRO SERVICE EXPREES", 
//					"Gracias por Registrarte, puedes acceder a todos los beneficion de Service Express para envios con mayor seguridad y confianza.", 
//					"serviceexpress2017" ));
//			
//			if(a==1){
//				System.out.println("Se envio correctamente mensaje al email");
//				
//			}else{
//				System.out.println("Problemas de envio de mensaje al email");
//				
//			}
			
			visible = true;
			
			eventoLimpiarFormulario();
			
			mensajeEmergente.addMessageInfo("Registro Exitoso inicie Session");
		
    	return "ClienteRegistro?faces-redirect=false";
	}

	

	public String login(){
		
		System.out.println("Entro a Login");
		
			int hora = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
			
	        if (hora >6 && hora < 12 ) {
	            saludos=  "Buenos Dias";
	        }
	        if (hora > 12 && hora < 18) { //aquí en Lima, a partir de las 7pm ya es oscuro
	        	saludos= "Buenas Tardes";
	        }
	        if (hora > 18 && hora < 24) {
	        	saludos= "Buenas Noches";
	        }
	        if (hora > 00 && hora < 6) {
	        	saludos= "Buenas Madrugadas";
	        }
	        FacesContext context=FacesContext.getCurrentInstance();
	        	
	        	try{
	         		clienteSession = clienteMB.loginCliente(usuario, contraseña);
		        	
		        	if(clienteSession != null){
		
    		        		HttpSession session=(HttpSession) context.getExternalContext().getSession(false);
			             	  session.setAttribute("clienteSessionA",clienteSession);
	        				
			             	  	return "HomeCliente?faces-redirect=true";	
		        	}
		        	
	        	}catch(Exception e){
	        		System.out.println("Cliente no encontrado");
	        		mensajeEmergente.addMessageError("Contraseña Invalida");
		 	        
	        	}
	        	
	        	 
        		return "LoginCliente?faces-redirect=false";
	}
	
	public String cerrarSesion() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		
		System.out.println("Cerro Session");
		return "index?faces-redirect=true";
	}
	
	
	
	/************************************************/	

	
	public Cliente getClienteSession() {
		return clienteSession;
	}

	public void setClienteSession(Cliente clienteSession) {
		this.clienteSession = clienteSession;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public String getSaludos() {
		return saludos;
	}
	public void setSaludos(String saludos) {
		this.saludos = saludos;
	}
	public int getCod_cliente() {
		return cod_cliente;
	}
	public void setCod_cliente(int cod_cliente) {
		this.cod_cliente = cod_cliente;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getContra() {
		return contra;
	}
	public void setContra(String contra) {
		this.contra = contra;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getContraseña() {
		return contraseña;
	}
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	public List<Cliente> getLstCliente() {
		return lstCliente;
	}
	public void setLstCliente(List<Cliente> lstCliente) {
		this.lstCliente = lstCliente;
	}
	public String getNombreParam() {
		return nombreParam;
	}
	public void setNombreParam(String nombreParam) {
		this.nombreParam = nombreParam;
	}
	public Boolean getEstadoBotonAgregar() {
		return estadoBotonAgregar;
	}
	public void setEstadoBotonAgregar(Boolean estadoBotonAgregar) {
		this.estadoBotonAgregar = estadoBotonAgregar;
	}
	public Boolean getEstadoBotonActualizar() {
		return estadoBotonActualizar;
	}
	public void setEstadoBotonActualizar(Boolean estadoBotonActualizar) {
		this.estadoBotonActualizar = estadoBotonActualizar;
	}
	public String getTITLE_PAGE() {
		return TITLE_PAGE;
	}
	public void setTITLE_PAGE(String tITLE_PAGE) {
		TITLE_PAGE = tITLE_PAGE;
	}
	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
}
