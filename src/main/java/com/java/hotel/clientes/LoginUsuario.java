package com.java.hotel.clientes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
@Table(name = "login")
public class LoginUsuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int idLogin;

	@Column(name = "usuario")
	private String usuarioLogin;

	@Column(name = "contrasenia")
	private String contraseniaLogin;
	
	@OneToMany(mappedBy = "loginUsuario", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	private List<Huesped> huespedsList;

	@OneToMany(mappedBy = "loginUsuario", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	private List<ReservaCliente> reservaList;


	public void agregarHuesped(Huesped huesped) {
		if (this.huespedsList == null)
			huespedsList = new ArrayList<>();
		
		huespedsList.add(huesped);
		huesped.setLoginUsuario(this);
	}
	
	public void agregarCliente(ReservaCliente reservaClientes) {
		if (this.reservaList == null)
			reservaList = new ArrayList<>();

		reservaList.add(reservaClientes);
		reservaClientes.setLoginUsuario(this);

	}


	public LoginUsuario() {
		super();
	}

	public LoginUsuario(String usuarioLogin, String contraseniaLogin) {
		super();
		this.usuarioLogin = usuarioLogin;
		this.contraseniaLogin = contraseniaLogin;
	}

	public int getIdLogin() {
		return idLogin;
	}

	public void setIdLogin(int idLogin) {
		this.idLogin = idLogin;
	}

	public String getUsuarioLogin() {
		return usuarioLogin;
	}

	public void setUsuarioLogin(String usuarioLogin) {
		this.usuarioLogin = usuarioLogin;
	}

	public String getContraseniaLogin() {
		return contraseniaLogin;
	}

	public void setContraseniaLogin(String contraseniaLogin) {
		this.contraseniaLogin = contraseniaLogin;
	}

	public List<ReservaCliente> getReservaList() {
		return reservaList;
	}

	public void setReservaList(List<ReservaCliente> reservaList) {
		this.reservaList = reservaList;
	}
	
	public List<Huesped> getHuespedsList() {
		return huespedsList;
	}

	public void setHuespedsList(List<Huesped> huespedsList) {
		this.huespedsList = huespedsList;
	}

	@Override
	public String toString() {
		return "LoginUsuario [idLogin=" + idLogin + ", usuarioLogin=" + usuarioLogin + ", contraseniaLogin="
				+ contraseniaLogin + "]";
	}

}
