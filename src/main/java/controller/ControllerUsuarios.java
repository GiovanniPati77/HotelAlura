package controller;

import java.util.Date;
import java.util.List;

import com.java.hotel.clientes.Huesped;
import com.java.hotel.clientes.LoginUsuario;
import com.java.hotel.clientes.ReservaCliente;
import com.java.hotel.productoDao.LoginDAO;
import com.java.hotel.productoDao.HuespedDAO;
import com.java.hotel.productoDao.ReservasDAO;

public class ControllerUsuarios {
	private static LoginUsuario loginUsuario = new LoginUsuario();
	private static ReservaCliente reservaCliente = new ReservaCliente();
	private static Huesped huesped = new Huesped();

	public Boolean recuperarId(String usuario, String contrasenia) {
		loginUsuario.setUsuarioLogin(usuario);
		loginUsuario.setContraseniaLogin(contrasenia);
		return new LoginDAO().recuperarId(loginUsuario, loginUsuario.getIdLogin());
	}

	public void ingrearCliente(String usuario, String contrasenia) {
		loginUsuario.setUsuarioLogin(usuario);
		loginUsuario.setContraseniaLogin(contrasenia);
		String crearCliente = new LoginDAO().createUser(loginUsuario);
	}

	public void ingresarReserva(String fechaEntrada, String fechaSalida, String valor, String formaPago) {
		reservaCliente.setFechaEntrada(fechaEntrada);
		reservaCliente.setFechaSalida(fechaSalida);
		reservaCliente.setValor(valor);
		reservaCliente.setFormaPago(formaPago);
		String reservaString = new ReservasDAO().ingresarReserva(reservaCliente, loginUsuario.getIdLogin());
	}

	public void ingresarHuesped(String nombre, String apellido, String fechaNacimiento, String nacionalidad,
			String telefono) {
		huesped.setNombreHuesped(nombre);
		huesped.setApellidoHuesped(apellido);
		huesped.setFechaNacimiento(fechaNacimiento);
		huesped.setNacionalidad(nacionalidad);
		huesped.setTelefono(telefono);
		String huespedString = new HuespedDAO().ingresarHuesped(huesped, loginUsuario.getIdLogin());
	}
	
	public static java.util.List<Object[]> listarReservas() {
		return new ReservasDAO().listarReservaString(reservaCliente,
				/*loginUsuario.getIdLogin()*/ 11);

	}

	public static java.util.List<Object[]> listarHuesList() { 
		return new HuespedDAO().listarHuesped(huesped, loginUsuario.getIdLogin());
	}
	
	public static List<Object[]> buscarHuesped(String nombre){
		return new HuespedDAO().buscar(nombre, loginUsuario.getIdLogin());
	}
	
}
