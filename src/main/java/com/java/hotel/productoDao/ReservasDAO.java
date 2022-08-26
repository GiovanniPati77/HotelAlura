package com.java.hotel.productoDao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.java.hotel.clientes.Huesped;
import com.java.hotel.clientes.LoginUsuario;
import com.java.hotel.clientes.ReservaCliente;

public class ReservasDAO {
	Session openSession = Conexion.openConexion();
	SessionFactory closeSession = Conexion.closeSession();

	public String ingresarReserva(ReservaCliente reservaCliente, int id) {

		try {
			reservaCliente = new ReservaCliente(reservaCliente.getFechaEntrada(), reservaCliente.getFechaSalida(),
					reservaCliente.getValor(), reservaCliente.getFormaPago());
			LoginUsuario loginUsuario = openSession.get(LoginUsuario.class, id);
			openSession.beginTransaction();
			loginUsuario.agregarCliente(reservaCliente);
			loginUsuario.getReservaList().add(reservaCliente);
			openSession.saveOrUpdate(reservaCliente);
			openSession.getTransaction().commit();
			return "Reserva exitosa";

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			closeSession.close();
		}

		return "Error reserva";
	}

	public List<Object[]> listarReservaString(ReservaCliente reservaCliente, int id) {

		List<Object[]> query = null;
		try {
			openSession.beginTransaction();

			query = openSession.createNativeQuery(
					"select id_reservas,fecha_entrada,fecha_Salida,valor,forma_pago from reservas where id_login = " + id + " ")
					.list();

			openSession.getTransaction().commit();
			closeSession.close();
			return query;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return query;

	}

	public String ActualizarReserva(int id, String fechaEntrada, String FechaSalida, String valor, String formaPago) {
		try {
			openSession.beginTransaction();
			ReservaCliente reservaCliente = openSession.get(ReservaCliente.class, id);
			reservaCliente.setFechaEntrada(fechaEntrada);
			reservaCliente.setFechaSalida(FechaSalida);
			reservaCliente.setValor(valor);
			reservaCliente.setFormaPago(formaPago);
			openSession.getTransaction().commit();
			return "Actualizacion exitosa";

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			closeSession.close();
		}

		return "Error al Actualizar";
	}
	
	
	public String deleteReserva(int id) {
		try {
			openSession.beginTransaction();
			ReservaCliente reservaCliente = openSession.get(ReservaCliente.class, id);
			openSession.delete(reservaCliente);
			openSession.getTransaction().commit();
			return "Eliminacion exitosa";
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeSession.close();
		}
		return "Error";
	}

}
