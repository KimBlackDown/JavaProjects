package pe.edu.cibertec.validaciones;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="validacionREGEX")
@SessionScoped
public class ValidacionREGEX {

	public String EMAIL="^[_a-z0-9-]+(.[_a-z0-9-]+)*@[a-z0-9-]+(.[a-z0-9-]+)*(.[a-z]{2,4})$";
	public String CELULAR="[9][0-9]{8}";
	public String DNI="[0-9]{8}";
	public String TEXTO_LINEAL="[a-zA-Z_0-9]+";
	
}
