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
	@NamedQuery(name="Cliente.getAllClientes",query="SELECT c FROM Cliente c"),

	@NamedQuery(name="Cliente.getClienteById",query="SELECT c FROM Cliente c where c.cod_cliente=:_cli"),

	@NamedQuery(name="Cliente.loginCliente",query="SELECT c FROM Cliente c where c.email= :usuCli and c.contra = :conCli"),

	@NamedQuery(name="Cliente.getClienteByName",query="SELECT c FROM Cliente c where c.nombre = :name ")
	
	
	
})



@Table(name="tb_cliente")
public class Cliente implements Serializable{

	@Id
	@GeneratedValue
	private int cod_cliente;
	
	@Column(name="nom_cliente" ,length=40)
	private String nombre;
	
	@Column(name="ape_cliente" ,length=50)
	private String apellido;
	
	@Column(name="email_cliente" ,length=60)
	private String email;
	
	@Column(name="cel_cliente" ,length=60)
	private String celular;
	
	@Column(name="con_cliente" ,length=60)
	private String contra;
	
	
	@Column(name="foto" ,length=60)
	private String foto;
	
	

	

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



	
	
	
}
