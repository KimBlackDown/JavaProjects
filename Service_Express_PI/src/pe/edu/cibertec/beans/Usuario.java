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
	@NamedQuery(name="Usuario.login",query="SELECT u FROM Usuario u where u.email= :v_user and u.contra=:v_password"),
	@NamedQuery(name="Usuario.getAllUsers",query="SELECT u FROM Usuario u"),
	@NamedQuery(name="Usuario.getUserForName",query="SELECT u FROM Usuario u where nombre= :p_nombre"),
	@NamedQuery(name="Usuario.getCourierActivo",query="SELECT u FROM Usuario u where tipo = :p_tipo "),
	@NamedQuery(name="Usuario.getUserForId",query="SELECT u FROM Usuario u where cod_usuario = :p_id ")
	
	
	
	
	
	
})


@Table(name="tb_usuario")
public class Usuario implements Serializable {

	
	@Id
	@GeneratedValue
	private int cod_usuario;
	
	@Column(name="nom_usuario", nullable=false,length=50 )
	private String nombre;
	
	@Column(name="ape_usuario", nullable=false,length=50 )
	private String apellido;
	
	@Column(name="email_usuario", nullable=false,length=50 )
	private String email;
	
	@Column(name="cel_usuario", nullable=false,length=50 )
	private String celular;
	
	@Column(name="con_usuario", nullable=false,length=50 )
	private String contra;
	
	@Column(name="foto", nullable=false,length=50 )
	private String foto;
	
	@Column(name="tipo", nullable=false,length=50 )
	private String tipo;

	public int getCod_usuario() {
		return cod_usuario;
	}

	public void setCod_usuario(int cod_usuario) {
		this.cod_usuario = cod_usuario;
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
