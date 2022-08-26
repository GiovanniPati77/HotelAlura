package com.java.hotel.productoDao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.java.hotel.clientes.LoginUsuario;

public class LoginDAO {
	Session openSession = Conexion.openConexion();
	SessionFactory closeSession = Conexion.closeSession();

	public String createUser(LoginUsuario loginUsuario) {
		
		try {
			LoginUsuario usuarios = new LoginUsuario(loginUsuario.getUsuarioLogin(),
					loginUsuario.getContraseniaLogin());
			openSession.beginTransaction();
			int idGenerado = (int) openSession.save(usuarios);
			loginUsuario.setIdLogin(idGenerado);
			openSession.getTransaction().commit();
			closeSession.close();
			System.out.println("id : " + idGenerado);
			return "Usuario Creado";

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Error al registrar usuario";
	}
	
	public Boolean recuperarId(LoginUsuario loginUsuario, int id) {
		
		boolean encontrado = false;
		try {
			openSession.beginTransaction();

			List<Object[]> loginList = openSession
					.createNativeQuery("select * from login where usuario ='" + loginUsuario.getUsuarioLogin()
							+ "' AND contrasenia = '" + loginUsuario.getContraseniaLogin() + "' ;")
					.list();
			for (Object[] listObjects : loginList) {
				id = (Integer) listObjects[0];
				String usuarioString = (String) listObjects[1];
				String contraseString = (String) listObjects[2];
				loginUsuario.setIdLogin(id);
				if(id > 0) {
					encontrado = true;
				}
			}
			openSession.getTransaction().commit();
			closeSession.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encontrado;
	}
}
