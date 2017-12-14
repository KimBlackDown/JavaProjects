package pe.edu.cibertec.recursos;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="redireccionarJSF")
@SessionScoped
public class Redireccionar implements Serializable{

	public String irPedidosPendientes(){
		
		return "InicioAdmin";
	}
	
	public String irPedidosEnProceso(){
		
		return "PedidoEnProceso";
	}
	
	
}
