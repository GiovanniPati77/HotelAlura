package com.java.hotel.clientes;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "huespedes")
public class Huesped {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_huesped")
	private int idHuesped;
	
	@Column(name = "nombre")
	private String nombreHuesped;

	@Column(name = "apellido")
	private String apellidoHuesped;

	@Column(name = "fecha_nacimiento")
	private String fechaNacimiento;

	@Column(name = "nacionalidad")
	private String nacionalidad;

	
	@Column(name = "telefono")
	private String telefono;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	@JoinColumn(name = "reserva_id")
	private LoginUsuario loginUsuario;
	
	

	public Huesped() {
		super();
	}

	public Huesped(String nombreHuesped, String apellidoHuesped, String fechaNacimiento2, String nacionalidad,
			String telefono) {
		super();
		this.nombreHuesped = nombreHuesped;
		this.apellidoHuesped = apellidoHuesped;
		this.fechaNacimiento = fechaNacimiento2;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
	}

	public int getIdHuesped() {
		return idHuesped;
	}

	public void setIdHuesped(int idHuesped) {
		this.idHuesped = idHuesped;
	}

	public String getNombreHuesped() {
		return nombreHuesped;
	}

	public void setNombreHuesped(String nombreHuesped) {
		this.nombreHuesped = nombreHuesped;
	}

	public String getApellidoHuesped() {
		return apellidoHuesped;
	}

	public void setApellidoHuesped(String apellidoHuesped) {
		this.apellidoHuesped = apellidoHuesped;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public LoginUsuario getLoginUsuario() {
		return loginUsuario;
	}

	public void setLoginUsuario(LoginUsuario loginUsuario) {
		this.loginUsuario = loginUsuario;
	}

	@Override
	public String toString() {
		return "Huesped [idHuesped=" + idHuesped + ", nombreHuesped=" + nombreHuesped + ", apellidoHuesped="
				+ apellidoHuesped + ", fechaNacimiento=" + fechaNacimiento + ", nacionalidad=" + nacionalidad
				+ ", telefono=" + telefono + ", LoginUsuario=" + loginUsuario + "]";
	}

	

}
