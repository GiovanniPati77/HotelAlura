package com.java.hotel.clientes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "reservas")
public class ReservaCliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_reservas")
	private int idReservaCliente;

	@Column(name = "fecha_entrada")
	private String fechaEntrada;

	@Column(name = "fecha_Salida")
	private String fechaSalida;

	@Column(name = "valor")
	private String valor;

	@Column(name = "forma_pago")
	private String formaPago;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "id_login")
	private LoginUsuario loginUsuario;

	public ReservaCliente() {
		super();
	}

	public ReservaCliente(String fechaEntrada, String fechaSalida, String valor, String formaPago) {
		super();
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.valor = valor;
		this.formaPago = formaPago;
	}

	public String getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(String fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public String getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(String fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}

	public LoginUsuario getLoginUsuario() {
		return loginUsuario;
	}

	public void setLoginUsuario(LoginUsuario loginUsuario) {
		this.loginUsuario = loginUsuario;
	}

	public int getIdReservaCliente() {
		return idReservaCliente;
	}

	public void setIdReservaCliente(int idReservaCliente) {
		this.idReservaCliente = idReservaCliente;
	}

	@Override
	public String toString() {
		return "ReservaCliente [idReservaCliente=" + idReservaCliente + ", fechaEntrada=" + fechaEntrada
				+ ", fechaSalida=" + fechaSalida + ", valor=" + valor + ", formaPago=" + formaPago + "]";
	}

}
