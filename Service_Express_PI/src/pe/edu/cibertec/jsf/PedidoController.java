package pe.edu.cibertec.jsf;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import pe.edu.cibertec.managedBean.DistritoMB;
import pe.edu.cibertec.managedBean.PedidoMB;
import pe.edu.cibertec.recursos.ExportarExcel;
import pe.edu.cibertec.recursos.MensajeEmergente;
import pe.edu.cibertec.temas.CambiadorTemas;

@ManagedBean(name="pedidoJSF")
@SessionScoped
public class PedidoController implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Pedido pedido;
	Cliente cliente ;
	List<Pedido>lstPedido;
	List<Pedido>lstPedidoCancelados;
	List<Pedido>lstPedidoCompletados;
	
	
	
	List<Distrito>lstDistrito;
	DistritoMB distritoMB =new DistritoMB();
	PedidoMB pedidoMB=new PedidoMB();
	ClienteMB clienteMB=new ClienteMB();

	private int codigo;
	private String direccion_ini;
	private int distrito1;
	private String remitente;
	private String celular1;
	private String direccion_fin;
	private int distrito2;
	private String receptor;
	private String celular2;
	private String instrucciones;
	private int documento_peq = 0;
	private int documento_med = 0;
	private int documento_gra = 0;
	private String kilometros;
	private String tiempo;
	private double precio;
	private String fechaRegistro;
	private String estado;
	private int pago;
	private double soles;
	
	CambiadorTemas tem= new CambiadorTemas();
	
	//obengo solo e id para traer toda la entidad
	private int  cod_cliente;
	private Usuario cod_usuario;
	
	public String exportarExcel(){
		 FacesContext context=FacesContext.getCurrentInstance();
			
			
		System.out.println("Entro a exportar");
		ExportarExcel ee= new ExportarExcel();
		
		if(lstPedido!=null){
			try {
				ee.exportarProducto(lstPedido);
				
				
				context.addMessage(null, new FacesMessage("Exito!!",  "Datos de lista Exportada") );
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			context.addMessage (null, new FacesMessage("Informacion!!",  "No hay datos en la lista") );
			
			
		}
		
		
		
		return "";
	}
	
	public PedidoController(){
		tem.setTema("blitzer");
		
		lstDistrito=distritoMB.getAllDistritos();
		lstPedidoCancelados= new ArrayList<>();
		lstPedidoCompletados= new ArrayList<>();
		limpiarPedido();
		
	}
	
	public String redirectHome(){
		limpiarPedido();
		return "HomeCliente?faces-redirect=true";
	}
	
	public String verActualizar(){
		
		tem.setTema("bluesky");
		
		System.out.println("Entro a Ver paraActualizar");
		
		pedido = pedidoMB.buscarId(codigo);
		
		direccion_ini=pedido.getDireccion_ini();
		distrito1=pedido.getDistrito1().getCod_distrito();
		remitente=pedido.getRemitente();
		celular1=pedido.getCelular1();
		
		direccion_fin=pedido.getDireccion_fin();
		distrito2=pedido.getDistrito2().getCod_distrito();
		receptor=pedido.getReceptor();
		celular2=pedido.getCelular2();
		
		instrucciones=pedido.getInstrucciones();
		documento_peq=pedido.getDocumento_peq();
		documento_gra=pedido.getDocumento_gra();
		documento_med=pedido.getDocumento_med();
		kilometros=pedido.getKilometros();
		tiempo=pedido.getTiempo();
		precio=pedido.getPrecio();
		soles=pedido.getSoles();
		
		
		
		return "ClienteActualizarPedido?faces-redirect=true";
	}
	
	
	public String verDetallePedido(){
		tem.setTema("bluesky");
		System.out.println("Entro a ver detalle de pedido");
		
		pedido = pedidoMB.buscarId(codigo);
		
		System.out.println(pedido.getDireccion_fin());
		
		return "ClienteDetallePedido?faces-redirect=true";
	}
	
	public String cargarSeguimientoA(){

		tem.setTema("bluesky");
		System.out.println("Entro a Cargar Seguimiento");
		
		Cliente clienteSession= new Cliente();
		   FacesContext context=FacesContext.getCurrentInstance();
			
		   HttpSession session=(HttpSession) context.getExternalContext().getSession(false);
		   clienteSession =  (Cliente) session.getAttribute("clienteSessionA");
		
		lstPedido =pedidoMB.getAllPedidosForCliente(clienteSession.getCod_cliente());
		
		
		
		
		return "ClienteSeguimiento?faces-redirect=true";
	}

	public String cargarHistorialA(){
		
		System.out.println("Entro a Cargar Historial");
		
			Cliente clienteSession= new Cliente();
			FacesContext context=FacesContext.getCurrentInstance();
			
			HttpSession session=(HttpSession) context.getExternalContext().getSession(false);
			clienteSession =  (Cliente) session.getAttribute("clienteSessionA");

			lstPedidoCancelados = pedidoMB.getAllPedidosCancelados(clienteSession.getCod_cliente());
			lstPedidoCompletados = pedidoMB.getAllPedidosCompletados(clienteSession.getCod_cliente());
			tem.setTema("bluesky");
		
		return "ClienteHistoria?faces-redirect=true";
	}

	
	public String cancelarPedido(){
		 FacesContext context=FacesContext.getCurrentInstance();
		
		System.out.println("Entro a Cancelar");
		
		pedido=  pedidoMB.buscarId(codigo);
		
		if( pedido.getEstado().equals("POR CONFIRMAR")){
		
		pedido.setEstado("CANCELADO");
		   
		   
		pedidoMB.update(pedido);
			
		context.addMessage(null, new FacesMessage("Exito!!",  "Datos Actualizados") );
		   
		
		return "ClienteSeguimiento";

		
		}else{
			
			context.addMessage(null, new FacesMessage("Mensaje",  "No se puede actualizar los porque esta asignado") );
			  
			return "ClienteSeguimiento";
			
		}
	}
	
	
	public String actualizarPedido(){
		tem.setTema("bluesky");
		
		
		System.out.println("Entro a Actualizar");
		
		Cliente clienteSession= new Cliente();
	   FacesContext context=FacesContext.getCurrentInstance();
		
	   HttpSession session=(HttpSession) context.getExternalContext().getSession(false);
	   clienteSession =  (Cliente) session.getAttribute("clienteSessionA");
	   		
	   
	   		pedido=  pedidoMB.buscarId(codigo);
	   		

	   		
	   		if(pedido.getEstado().equals("POR CONFIRMAR")){
	   		
					 pedido.setCod_cliente(clienteSession);
					 pedido.setCelular1(celular1);
					 pedido.setCelular2(celular2);
					 pedido.setDireccion_fin(direccion_fin);
					 pedido.setDireccion_ini(direccion_ini);
					 pedido.setDistrito1(distritoMB.getDistritoById(distrito1));
					 pedido.setDistrito2(distritoMB.getDistritoById(distrito2));
					 pedido.setDocumento_gra(documento_gra);
					 pedido.setDocumento_med(documento_med);
					 pedido.setDocumento_peq(documento_peq);
					 pedido.setEstado("POR CONFIRMAR");
					 pedido.setInstrucciones(instrucciones);
					 pedido.setKilometros(kilometros);
					 pedido.setPrecio(precio);
					 pedido.setReceptor(receptor);
					 pedido.setRemitente(remitente);
					 pedido.setTiempo(tiempo);
					 
					 if(pago==2){
						 pedido.setPago("Soles");
						 pedido.setSoles(soles);
					 }
					 if(pago==1){
						 pedido.setPago("Tarjeta");
						 pedido.setSoles(0.0);
					 }
					 		
					 if(pedido!=null){
					
						 
						 System.out.println("  "+clienteSession.getCod_cliente());
				         
					       
						pedidoMB.update(pedido);
						
						limpiarPedido();
						
						System.out.println(pedido.getDireccion_fin());
						
						
						 context.addMessage(null, new FacesMessage("Exito!!",  "Tu registro fue : " + " Exitoso") );
					       
						  MensajeEmergente a= new MensajeEmergente();
					        a.addMessageInfo("asdhabsdjhabsdjhbaj");
					        
						System.out.println("Pedido Actualizado");
						limpiarPedido();
					
						lstPedido=pedidoMB.getAllPedidosForCliente(clienteSession.getCod_cliente());
						
					 }
					 
					 return "ClienteSeguimiento?faces-redirect=true";
	   		}else{
	   			
	   			System.out.println("esta en otro estado");
	   			context.addMessage(null, new FacesMessage("Mensaje: ",  "Pedido no Actualizado: El pedido ya esta asignado") );
		        
			
		        MensajeEmergente a= new MensajeEmergente();
		        a.addMessageInfo("asdhabsdjhabsdjhbaj");

		   		return "ClienteActualizarPedido?faces-redirect=true"; 	
	   		}
	   		
	   		
	   		
	   		//return "ClienteActualizarPedido?faces-redirect=true"; 		
	//return "LoginCliente?faces-redirect=true";
}

	
	
	public String registrarPedido(){

		System.out.println("Entro registro pedido");
			Cliente clienteSession= new Cliente();
		   FacesContext context=FacesContext.getCurrentInstance();
			
		   HttpSession session=(HttpSession) context.getExternalContext().getSession(false);
		   clienteSession =  (Cliente) session.getAttribute("clienteSessionA");
		   	
		   
		   Date curDate = new Date();
		   
		   
		   
		   if(clienteSession != null){
	
				 pedido=new Pedido();
				 pedido.setCod_cliente(clienteSession);
				 pedido.setCelular1(celular1);
				 pedido.setCelular2(celular2);
				 pedido.setDireccion_fin(direccion_fin);
				 pedido.setDireccion_ini(direccion_ini);
				 pedido.setDistrito1(distritoMB.getDistritoById(distrito1));
				 pedido.setDistrito2(distritoMB.getDistritoById(distrito2));
				 pedido.setDocumento_gra(documento_gra);
				 pedido.setDocumento_med(documento_med);
				 pedido.setDocumento_peq(documento_peq);
				 pedido.setEstado("POR CONFIRMAR");
				 pedido.setInstrucciones(instrucciones);
				 pedido.setKilometros(kilometros);
				 pedido.setPrecio(precio);
				 pedido.setReceptor(receptor);
				 pedido.setRemitente(remitente);
				 pedido.setTiempo(tiempo);
				 pedido.setFechaRegistro(curDate);
				 
				 if(pago==2){
					 pedido.setPago("Soles");
					 pedido.setSoles(soles);
				 }else{
					 pedido.setPago("Tarjeta");
					 pedido.setSoles(0.0);
				 }
				 
				 System.out.println("  "+clienteSession.getCod_cliente());
				 
				 if(documento_gra > 0  || documento_med > 0 || documento_peq > 0){
					 
//					 FacesContext context = FacesContext.getCurrentInstance();
			         
				        context.addMessage(null, new FacesMessage("Mensaje",  "Registro Exitosos") );
				        context.addMessage(null, new FacesMessage("Mensaje", "Su Registro puede ver el en seguimiento"));
				        
					
					pedidoMB.insertar(pedido);
					limpiarPedido();
					System.out.println(pedido.getDireccion_fin());
					
					limpiarPedido();
					System.out.println("Pedido Insertado");
					return "HomeCliente";
					 
					 
				 }else{
					 
					  context.addMessage(null, new FacesMessage("Mensaje",  "Cantidad de Paquetes no especificado  " ) );
				        
					
					 
					 
					 return "HomeCliente";
				 }
				 /*
				 if(pedido!=null){
					
				
				}*/
				
		   }
		   
		
		return "LoginCliente?faces-redirect=true";
	}
	
	
	public void limpiarPedido(){
		celular1="";
		celular2="";
		direccion_fin="";
		direccion_ini="";
		distrito1=0;
		distrito2=0;
		documento_gra=0;
		documento_med=0;
		documento_peq=0;
		instrucciones ="";
		kilometros="";
		precio=0;
		receptor="";
		remitente="";
		tiempo="";
		pago = 0;
		 soles = 0;
		
		
	}
	
	
	
	
	
	

	
	



	public int getCodigo() {
		return codigo;
	}




	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}




	public String getDireccion_ini() {
		return direccion_ini;
	}




	public void setDireccion_ini(String direccion_ini) {
		this.direccion_ini = direccion_ini;
	}




	public String getRemitente() {
		return remitente;
	}




	public void setRemitente(String remitente) {
		this.remitente = remitente;
	}




	public String getCelular1() {
		return celular1;
	}




	public void setCelular1(String celular1) {
		this.celular1 = celular1;
	}




	public String getDireccion_fin() {
		return direccion_fin;
	}




	public void setDireccion_fin(String direccion_fin) {
		this.direccion_fin = direccion_fin;
	}




	public String getReceptor() {
		return receptor;
	}




	public void setReceptor(String receptor) {
		this.receptor = receptor;
	}




	public String getCelular2() {
		return celular2;
	}




	public void setCelular2(String celular2) {
		this.celular2 = celular2;
	}




	public String getInstrucciones() {
		return instrucciones;
	}




	public void setInstrucciones(String instrucciones) {
		this.instrucciones = instrucciones;
	}




	public int getDocumento_peq() {
		return documento_peq;
	}




	public void setDocumento_peq(int documento_peq) {
		this.documento_peq = documento_peq;
	}




	public int getDocumento_med() {
		return documento_med;
	}




	public void setDocumento_med(int documento_med) {
		this.documento_med = documento_med;
	}




	public int getDocumento_gra() {
		return documento_gra;
	}




	public void setDocumento_gra(int documento_gra) {
		this.documento_gra = documento_gra;
	}




	public String getKilometros() {
		return kilometros;
	}




	public void setKilometros(String kilometros) {
		this.kilometros = kilometros;
	}




	public String getTiempo() {
		return tiempo;
	}




	public void setTiempo(String tiempo) {
		this.tiempo = tiempo;
	}




	public double getPrecio() {
		return precio;
	}




	public void setPrecio(double precio) {
		this.precio = precio;
	}




	public String getFechaRegistro() {
		return fechaRegistro;
	}




	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}




	public String getEstado() {
		return estado;
	}




	public void setEstado(String estado) {
		this.estado = estado;
	}




	



	public int getCod_cliente() {
		return cod_cliente;
	}




	public void setCod_cliente(int cod_cliente) {
		this.cod_cliente = cod_cliente;
	}




	public Usuario getCod_usuario() {
		return cod_usuario;
	}




	public void setCod_usuario(Usuario cod_usuario) {
		this.cod_usuario = cod_usuario;
	}




	public int getDistrito1() {
		return distrito1;
	}




	public void setDistrito1(int distrito1) {
		this.distrito1 = distrito1;
	}




	public int getDistrito2() {
		return distrito2;
	}




	public void setDistrito2(int distrito2) {
		this.distrito2 = distrito2;
	}




	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public List<Pedido> getLstPedido() {
		return lstPedido;
	}

	public void setLstPedido(List<Pedido> lstPedido) {
		this.lstPedido = lstPedido;
	}

	public List<Distrito> getLstDistrito() {
		return lstDistrito;
	}

	public void setLstDistrito(List<Distrito> lstDistrito) {
		this.lstDistrito = lstDistrito;
	}
	public Cliente getCliente() {
		return cliente;
	}




	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}




	
	public int getPago() {
		return pago;
	}


	public void setPago(int pago) {
		this.pago = pago;
	}


	public double getSoles() {
		return soles;
	}


	public void setSoles(double soles) {
		this.soles = soles;
	}


	public List<Pedido> getLstPedidoCancelados() {
		return lstPedidoCancelados;
	}


	public void setLstPedidoCancelados(List<Pedido> lstPedidoCancelados) {
		this.lstPedidoCancelados = lstPedidoCancelados;
	}


	public List<Pedido> getLstPedidoCompletados() {
		return lstPedidoCompletados;
	}


	public void setLstPedidoCompletados(List<Pedido> lstPedidoCompletados) {
		this.lstPedidoCompletados = lstPedidoCompletados;
	}


	
	
}
