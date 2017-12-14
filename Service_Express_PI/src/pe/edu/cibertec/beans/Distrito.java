package pe.edu.cibertec.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity


@NamedQueries({
	@NamedQuery(name="Distrito.getAllDistrito",query="SELECT d FROM Distrito d"),

	@NamedQuery(name="Distrito.getDistritoById",query="SELECT d FROM Distrito d where cod_distrito = :cod_dis"),
	
	
})

@Table(name="tb_distrito")
public class Distrito implements Serializable{

	@Id
	@GeneratedValue
	private int cod_distrito;
	
	@Column(name="des_distrito" , length=40, nullable=false)
	private String descripcion;

	public int getCod_distrito() {
		return cod_distrito;
	}

	public void setCod_distrito(int cod_distrito) {
		this.cod_distrito = cod_distrito;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	
	
}
