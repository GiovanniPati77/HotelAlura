package com.java.hotel.productoDao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.java.hotel.clientes.Huesped;
import com.java.hotel.clientes.LoginUsuario;
import com.java.hotel.clientes.ReservaCliente;

public class Conexion {
	
	public static Session openConexion() {
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(LoginUsuario.class).addAnnotatedClass(ReservaCliente.class)
				.addAnnotatedClass(Huesped.class).buildSessionFactory();
		
		Session session = sessionFactory.openSession();
		
		return session;
	}
	
	public static SessionFactory closeSession() {
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(LoginUsuario.class).addAnnotatedClass(ReservaCliente.class)
				.addAnnotatedClass(Huesped.class).buildSessionFactory();
		return sessionFactory;
	}
}
