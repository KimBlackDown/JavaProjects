package pe.edu.cibertec.recursos;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="variablesJSF")
@SessionScoped
public class Variables implements Serializable{

	private  String TITLE_PAGE;

	public  String getTITLE_PAGE() {
		return TITLE_PAGE;
	}

	public  void setTITLE_PAGE(String tITLE_PAGE) {
		TITLE_PAGE = tITLE_PAGE;
	}

	
	
	
	
	
}
