package com.java.hotel.productoDao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.java.hotel.clientes.Huesped;
import com.java.hotel.clientes.LoginUsuario;

public class HuespedDAO {
	Session openSession = Conexion.openConexion();
	SessionFactory closeSession = Conexion.closeSession();

	public String ingresarHuesped(Huesped huesped, int id) {
		try {
			huesped = new Huesped(huesped.getNombreHuesped(), huesped.getApellidoHuesped(),
					huesped.getFechaNacimiento(), huesped.getNacionalidad(), huesped.getTelefono());
			LoginUsuario loginUsuario = openSession.get(LoginUsuario.class, id);
			openSession.beginTransaction();
			loginUsuario.agregarHuesped(huesped);
			loginUsuario.getHuespedsList().add(huesped);
			openSession.save(huesped);
			openSession.getTransaction().commit();
			return "Reserva exitosa";

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			closeSession.close();
		}

		return "Error reserva";
	}

	public List<Object[]> listarHuesped(Huesped huesped, int id) {

		List<Object[]> query = null;
		try {
			openSession.beginTransaction();

			query = openSession.createNativeQuery(
					"select id_huesped,nombre,apellido,fecha_nacimiento,nacionalidad,telefono,reserva_id from huespedes where reserva_id = "
							+ id + " ")
					.list();

			openSession.getTransaction().commit();
			closeSession.close();
			return query;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return query;

	}

	public String ActualizarHuesped(int id, String nombre, String apellido, String fechaString, String nacionalidad,
			String telefono) {
		try {
			openSession.beginTransaction();
			Huesped huesped = openSession.get(Huesped.class, id);
			huesped.setNombreHuesped(nombre);
			huesped.setApellidoHuesped(apellido);
			huesped.setFechaNacimiento(fechaString);
			huesped.setNacionalidad(nacionalidad);
			huesped.setTelefono(telefono);
			openSession.getTransaction().commit();
			return "Actualizacion exitosa";

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			closeSession.close();
		}

		return "Error de Actualizacion";
	}

	public String deleteHuesped(int id) {
		try {
			openSession.beginTransaction();
			Huesped huesped = openSession.get(Huesped.class, id);
			openSession.delete(huesped);
			openSession.getTransaction().commit();
			return "Eliminacion Existosa";

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeSession.close();
		}
		return "Error al eliminar";
	}

	public List<Object[]> buscar(String nombre,int id) {
		List<Object[]> lista = null;
		try {
			openSession.beginTransaction();
			lista = openSession.createNativeQuery("select * from huespedes where nombre like '%" + nombre + "%' and reserva_id = "+ id +"").list();
			openSession.getTransaction().commit();
			return lista;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeSession.close();
		}
		return lista;
	}

}
