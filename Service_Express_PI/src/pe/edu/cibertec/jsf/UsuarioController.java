package pe.edu.cibertec.jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import pe.edu.cibertec.beans.Cliente;
import pe.edu.cibertec.beans.Distrito;
import pe.edu.cibertec.beans.Pedido;
import pe.edu.cibertec.beans.Usuario;
import pe.edu.cibertec.managedBean.ClienteMB;
import pe.edu.cibertec.managedBean.PedidoMB;
import pe.edu.cibertec.managedBean.UsuarioMB;
import pe.edu.cibertec.recursos.MensajeEmergente;
import pe.edu.cibertec.recursos.Variables;
import pe.edu.cibertec.temas.CambiadorTemas;

@ManagedBean(name="usuarioJSF")
@SessionScoped
public class UsuarioController implements Serializable{
	

	CambiadorTemas tem= new CambiadorTemas();
	
	public  String TITLE_PAGE;
	private Boolean estadoBotonAgregar;
	private Boolean estadoBotonActualizar;
	private String usuario;
	private String password;
	private String nombreParam="";
	private String descripcion;
	public List<Usuario>lstUsuario;
	private int idUsuario;
	private Pedido pedido;
	
	public List<Usuario>lstCourier;
	public List<Cliente>lstCliente;

	MensajeEmergente mensajeEmergente=new MensajeEmergente();
	Usuario usuBean=new Usuario();
	UsuarioMB usuarioMB=new UsuarioMB();
	List<Distrito>lstDistrito=new ArrayList<>();
	List<Pedido>lstPedidos=new ArrayList<>();
	PedidoMB pedidoMB=new PedidoMB();
	ClienteMB clienteMB= new ClienteMB();
	Cliente cliente= new Cliente();
	
	
	public UsuarioController(){
		tem.setTema("black-tie");
		
		usuBean=new Usuario();
		 clienteMB= new ClienteMB();
		lstUsuario=usuarioMB.getAllUsers();
		lstCourier = usuarioMB.getCourierActivo();
		pedido= new Pedido();
		System.out.println(lstPedidos.size());
		
	}
	
	
	public String redirecCourierVerPedido(){
	
		tem.setTema("blitzer");
		
		
		return "CourierVerPedido?faces-redirect=true?transition=flow";
	}
	
	public String actionCourierActulizarPedido(){
		tem.setTema("blitzer");

		pedidoMB.update(pedido);
		
		System.out.println(pedido.getEstado());
		
		return "CourierPedidoPendientes?faces-redirect=true";
		
	}
	
	public String redirecCourierActualizarPedido(){
		tem.setTema("blitzer");

		
		return "CourierActualizarPedido?faces-redirect=true";
		
	}
	
	
	public String actionCourierAceptarPedido(){
		tem.setTema("blitzer");

		Usuario  usuario = new Usuario();
		  FacesContext context=FacesContext.getCurrentInstance();
			
		   HttpSession session=(HttpSession) context.getExternalContext().getSession(false);
     	usuario =  (Usuario) session.getAttribute("courierSession");
		
		pedido.setEstado("ASIGNADO");
		pedido.setCod_usuario(usuario);
		
		pedidoMB.update(pedido);
		
		lstPedidos = pedidoMB.getAllPedidosProcesoCourier(usuario.getCod_usuario());
		return "CourierPedidoPendientes?faces-redirect=true";
	}
	
	
	public String redirecCourierAceptarPedido(){
		tem.setTema("blitzer");

		return "CourierAceptarPedido?faces-redirect=true";
	}
	
	
	
	public String redirectCourierPedidosRecientes(){
		
		tem.setTema("blitzer");

		lstPedidos = pedidoMB.getAllPedidosPendientesAdmin();
		
		return "CourierPedidoRecientes?faces-redirect=true";
	}
	
	
	public String redirectCourierPedidosPendientes(){
		tem.setTema("blitzer");

		Usuario  usuario = new Usuario();
		  FacesContext context=FacesContext.getCurrentInstance();
			
		   HttpSession session=(HttpSession) context.getExternalContext().getSession(false);
       	usuario =  (Usuario) session.getAttribute("courierSession");
       	
		
		lstPedidos = pedidoMB.getAllPedidosProcesoCourier(usuario.getCod_usuario());
		
		return "CourierPedidoPendientes?faces-redirect=true";
	}
	
	public String redirectCourierPedidosAtendidos(){
		tem.setTema("blitzer");

		Usuario  usuario = new Usuario();
		  FacesContext context=FacesContext.getCurrentInstance();
			
		   HttpSession session=(HttpSession) context.getExternalContext().getSession(false);
     	usuario =  (Usuario) session.getAttribute("courierSession");
     	
		lstPedidos = pedidoMB.getAllPedidosCompletadosCourier(usuario.getCod_usuario());
		
		return "CourierPedidoAtendidos?faces-redirect=true";
	}
	
	
	
	
	
	
	
	public String redirectPrincipalAdmin(){
		
		return "HomeAdministrador?faces-redirect=true";
	}
	
	public String adminExecuteActualizarPersonal(){
		usuarioMB.actualizar(usuBean);
		
		return "AdminEditarPersonal?faces-redirect=true"; 
	}
	
	public String adminExecuteActualizarCliente(){
		
		clienteMB.actualizar(cliente);
		return "AdminEditarCliente?faces-redirect=true";
	}

	
	public String adminActualizarCliente(){
		
		return "AdminEditarCliente?faces-redirect=true";
	}
	
	public String adminActualizarPersonal(){
		System.out.println("*****************");
		
		return "AdminEditarPersonal?faces-redirect=true";
	}
	
	
	public String adminListaPersonal(){

		lstUsuario = new ArrayList<>();
		lstUsuario = usuarioMB.getAllUsers();
		
		return "AdminMantenimientoPersonal?faces-redirect=true";
	}
	
	public String adminListaClientes(){
		
		
		lstCliente = new ArrayList<>();
		lstCliente = clienteMB.getAllClientes(); 
		
		return "AdminMantenimientoCliente?faces-redirect=true";
	}


	public String cargarlistaCourier(){
		
		System.out.println("Entro a cargarListaCourier");
		lstCourier = usuarioMB.getCourierActivo();
		
		System.out.println("cantidad de courier actvos : "+lstUsuario.size());
		
		return "AdminMantenimientoCourier?faces-redirect=true";
	}
	
	public String seleccionCourier(){
		
		pedido.setCod_usuario( usuarioMB.getUserForId(idUsuario));
		
		
		return "AdminEditarDetallePedido?faces-redirect=true";
	}
	

	
	public String actualizarContra(){
		return "ListaUsuarios";
	}

	
	public String actionActualizarEmpleado(){
		
		usuarioMB.actualizar(usuBean);
		
		lstPedidos=pedidoMB.getAllPedidos();
		
		return "ListaUsuarios?faces-redirect=true";
	}
	
	public String actionAgregarEmpleado(){
		System.out.println("Entro");
		if(usuBean !=null){
			usuarioMB.insertar(usuBean);
			System.out.println(usuBean.getNombre());
			System.out.println(usuBean.getApellido());
			System.out.println(usuBean.getCelular());
			System.out.println(usuBean.getContra());
			System.out.println(usuBean.getTipo());
			System.out.println(usuBean.getEmail());
			return "ListaUsuario?faces-redirect=true";
		}else{
			System.out.println("No entro");
			return "";
		}
	}
	
	public String redirecAgregarEmpleado(){
		TITLE_PAGE="Agregar Empleado";
		estadoBotonAgregar=false;
		estadoBotonActualizar=true;
		usuBean=null;
		return "AgregarEmpleado?faces-redirect=true";
	}
	
	public String actionBuscarUsuario(Usuario usuario){
		System.out.println(usuario.getApellido());
		usuBean=usuario;
			
		TITLE_PAGE="Actualizar Empleado";
		estadoBotonAgregar=true;
		estadoBotonActualizar=false;
		return "AgregarEmpleado?faces-redirect=true";
	}
	
	
	
	public String actualizarPedidoPendiente(){
		
		if(pedido.getCod_usuario() != null){
		
			pedido.setEstado("ASIGNADO");
		
			pedidoMB.update(pedido);
			
		
		}else{
			pedidoMB.update(pedido);
			
		}
		
		return "AdminListaPendientes?faces-redirect=true";
	}
	
	public String cancelarPedido(){
		
			
		pedido.setEstado("CANCELADO");
		
		pedidoMB.update(pedido);
		
		return "AdminListaCancelados?faces-redirect=true";
	}
	
	public String redirecEditarPedido(){
		
		System.out.println(pedido.getDireccion_fin());
		
		return "AdminEditarDetallePedido?faces-redirect=true";
	}
	
	public String redirecVerPedido(){
		System.out.println(pedido.getDireccion_fin());
		
		return "AdminVerDetallePedido?faces-redirect=true";
	}
	
	
	
	public String redirecPedidosPendientes(){
		
		tem.setTema("black-tie");

		
		//lstPedidos = pedidoMB.getAllPedidosCompletadosAdmin();
		lstPedidos = pedidoMB.getAllPedidosPendientesAdmin();
		System.out.println("listado...: " +lstPedidos.size());
		
		return "AdminListaPendientes?faces-redirect=true";
	}
	
	public String redirecPedidosProcesos(){
		tem.setTema("black-tie");

		
		//lstPedidos = pedidoMB.getAllPedidosCompletadosAdmin();
		lstPedidos = pedidoMB.getAllPedidosProcesoAdmin();
		System.out.println("listado...: " +lstPedidos.size());
		
		return "AdminListaProceso?faces-redirect=true";
	}
	public String redirecPedidosCompletados(){
		tem.setTema("black-tie");

		
		//lstPedidos = pedidoMB.getAllPedidosCompletadosAdmin();
		lstPedidos = pedidoMB.getAllPedidosCompletadosAdmin();
		System.out.println("listado...: " +lstPedidos.size());
		
		return "AdminListaAtendidos?faces-redirect=true";
	}
	
	public String redirecPedidosCancelados(){
		tem.setTema("black-tie");

		
		//lstPedidos = pedidoMB.getAllPedidosCompletadosAdmin();
		lstPedidos = pedidoMB.getAllPedidosCanceladoAdmin();
		System.out.println("listado...: " +lstPedidos.size());
		
		return "AdminListaCancelados?faces-redirect=true";
	}
	
	
	
	
	
	
	
	public String actionBuscarEmpleado(){
		
		
		if(!nombreParam.equals("") && nombreParam!=null){
			
			lstUsuario =usuarioMB.getUserForName(nombreParam);
			
			if(lstUsuario.size()>0){
				
				mensajeEmergente.addMessageInfo("Coincidencia(s) encontrada(s)!!");
			}else{
				lstUsuario =usuarioMB.getAllUsers();
				mensajeEmergente.addMessageInfo("Coincidencia(s) encontrada(s)!! Listando todo");
			}
			
		}else{
			
			mensajeEmergente.addMessageWarn("Listando Todo!!");
			
			lstUsuario =usuarioMB.getAllUsers();
		}
		return "ListaUsuarios?faces-redirect=true";
	}
	
	public String loginUsuario(){

		usuBean=new Usuario();
		try{
				usuBean=new Usuario();
				usuBean=usuarioMB.getUserLogin(usuario, password);
				FacesContext context=FacesContext.getCurrentInstance();
				HttpSession session=(HttpSession) context.getExternalContext().getSession(false);
		    	 
				if(usuBean != null){
						
						if(usuBean.getTipo().equals("COURIER")){
						
							 session.setAttribute("courierSession",usuBean);
			             	 
							return "HomeCourier";
						}else 
						{	
						
							 session.setAttribute("adminSession",usuBean);
							return "HomeAdministrador?faces-redirect=true";
						}
				}else{
					
					FacesContext contexta=FacesContext.getCurrentInstance();
					
					contexta.getExternalContext().getFlash().setKeepMessages(true);
					    contexta.addMessage(null, new FacesMessage("Mensaje de sistema","Usuadio no encontrado"));
			
					return "LoginUsuario?faces-redirect=true";
				
				}
				
		}catch(Exception e){
			System.out.println("Entidad no encontrada");
		}		
		
		return "LoginUsuario?faces-redirect=true";
	}
	

	public String limpiarDatosForm(){
		
		usuBean=new Usuario();
		return "";
	}
	
	
	
	
	public String cerrarSesion() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		
		usuBean=new Usuario();
		System.out.println("Cerro Session usuario");
		return "LoginUsuario?faces-redirect=true";
	}
	
	
	
	
	
	
	//METODOS GET AND SET
	
	
	
	public List<Distrito> getLstDistrito() {
		return lstDistrito;
	}

	public List<Cliente> getLstCliente() {
		return lstCliente;
	}

	public void setLstCliente(List<Cliente> lstCliente) {
		this.lstCliente = lstCliente;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setLstDistrito(List<Distrito> lstDistrito) {
		this.lstDistrito = lstDistrito;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Usuario getUsuBean() {
		return usuBean;
	}

	public void setUsuBean(Usuario usuBean) {
		this.usuBean = usuBean;
	}
	
	public List<Usuario> getLstUsuario() {
		return lstUsuario;
	}

	public void setLstUsuario(List<Usuario> lstUsuario) {
		this.lstUsuario = lstUsuario;
	}

	public List<Pedido> getLstPedidos() {
		return lstPedidos;
	}

	public void setLstPedidos(List<Pedido> lstPedidos) {
		this.lstPedidos = lstPedidos;
	}
	
	public String getNombreParam() {
		return nombreParam;
	}

	public void setNombreParam(String nombreParam) {
		this.nombreParam = nombreParam;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
	

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public List<Usuario> getLstCourier() {
		return lstCourier;
	}

	public void setLstCourier(List<Usuario> lstCourier) {
		this.lstCourier = lstCourier;
	}
	
	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
}
