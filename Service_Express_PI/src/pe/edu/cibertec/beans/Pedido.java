package pe.edu.cibertec.beans;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity



@NamedQueries({
	//para cliente
	@NamedQuery(name="Pedido.getAllPedidos",query="SELECT p FROM Pedido p"),
	@NamedQuery(name="Pedido.getAllPedidosForCliente",query="SELECT p FROM Pedido p where cod_cliente.cod_cliente = :cod  and    estado != 'CANCELADO' and estado != 'COMPLETADO'   "),
	@NamedQuery(name="Pedido.getAllPedidosCompletados",query="SELECT p FROM Pedido p where estado = :estado and cod_cliente.cod_cliente = :cod"),
	@NamedQuery(name="Pedido.getAllPedidosCancelados",query="SELECT p FROM Pedido p  where estado = :estado and cod_cliente.cod_cliente = :cod"),
	
	//para admin
	
	@NamedQuery(name="Pedido.getAllPedidosCanceladoAdmin",query="SELECT p FROM Pedido p where     estado = 'CANCELADO' "),
	@NamedQuery(name="Pedido.getAllPedidosCompletadosAdmin",query="SELECT p FROM Pedido p where estado = 'COMPLETADO' "),
	@NamedQuery(name="Pedido.getAllPedidosProcesoAdmin",query="SELECT p FROM Pedido p  where  estado != 'CANCELADO' and estado != 'COMPLETADO' and estado != 'POR CONFIRMAR' "),
	@NamedQuery(name="Pedido.getAllPedidosPendientesAdmin",query="SELECT p FROM Pedido p where estado = 'POR CONFIRMAR' "),
	

	//para Courier
	
		@NamedQuery(name="Pedido.getAllPedidosCanceladoCourier",query="SELECT p FROM Pedido p where     estado = 'CANCELADO' and cod_usuario.cod_usuario= :cod"),
		@NamedQuery(name="Pedido.getAllPedidosCompletadosCourier",query="SELECT p FROM Pedido p where estado = 'COMPLETADO' and cod_usuario.cod_usuario= :cod"),
		@NamedQuery(name="Pedido.getAllPedidosProcesoCourier",query="SELECT p FROM Pedido p  where  estado != 'CANCELADO' and estado != 'COMPLETADO' and estado != 'POR CONFIRMAR' and cod_usuario.cod_usuario= :cod"),
		
	
})


@Table(name="tb_pedidos")
public class Pedido implements Serializable{

	@Id 
	@GeneratedValue
	private int codigo;
	
	@Column(name="direccion_ini",length=150)
	private String direccion_ini;
	
	@ManyToOne
	@JoinColumn(name="distrito1")
	private Distrito distrito1;
	
	@Column(name="remitente",length=150)
	private String remitente;
	
	@Column(name="celular1",length=150)
	private String celular1;
	
	@Column(name="direccion_fin",length=150)
	private String direccion_fin;
	
	@ManyToOne
	@JoinColumn(name="distrito2")
	private Distrito distrito2;
	
	@Column(name="receptor",length=150)
	private String receptor;
	
	@Column(name="celular2",length=150)
	private String celular2;
	
	@Column(name="instrucciones",length=150)
	private String instrucciones;
	
	
	@Column(name="documento_peq")
	private int documento_peq;
	

	@Column(name="documento_med")
	private int documento_med;
	

	@Column(name="documento_gra")
	private int documento_gra;
	
	@Column(name="kilometros",length=150)
	private String kilometros;
	
	@Column(name="tiempo",length=150)
	private String tiempo;
	

	@Column(name="precio",precision=8,scale=2)
	private double precio;
	
//	@Column(name="fechaRegistro",length=150)
//	private String fechaRegistro;
//	
	
	@Column(name="fechaRegistro")
	@Temporal(TemporalType.TIMESTAMP) //fecha y hora
	private Date fechaRegistro;

	
	@Column(name="estado",length=150)
	private String estado;
	
	
	@ManyToOne
	@JoinColumn(name="cod_cliente")
	private Cliente cod_cliente;
	
	@ManyToOne
	@JoinColumn(name="cod_usuario")
	private Usuario cod_usuario;

	
	@Column(name="pago",length=20)
	private String pago;
	
	@Column(name="soles",precision=8,scale=2)
	private double soles;
	
	
	
	
	

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

	public Distrito getDistrito1() {
		return distrito1;
	}

	public void setDistrito1(Distrito distrito1) {
		this.distrito1 = distrito1;
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

	public Distrito getDistrito2() {
		return distrito2;
	}

	public void setDistrito2(Distrito distrito2) {
		this.distrito2 = distrito2;
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

//	public String getFechaRegistro() {
//		return fechaRegistro;
//	}
//
//	public void setFechaRegistro(String fechaRegistro) {
//		this.fechaRegistro = fechaRegistro;
//	}


	public String getEstado() {
		return estado;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Cliente getCod_cliente() {
		return cod_cliente;
	}

	public void setCod_cliente(Cliente cod_cliente) {
		this.cod_cliente = cod_cliente;
	}

	public Usuario getCod_usuario() {
		return cod_usuario;
	}

	public void setCod_usuario(Usuario cod_usuario) {
		this.cod_usuario = cod_usuario;
	}
	
	
	public String getPago() {
		return pago;
	}

	public void setPago(String pago) {
		this.pago = pago;
	}

	public double getSoles() {
		return soles;
	}

	public void setSoles(double soles) {
		this.soles = soles;
	}
	
	
}
