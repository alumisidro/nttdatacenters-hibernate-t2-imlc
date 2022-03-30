package com.everis.nttdatacenters_hibernate_t1_imlc;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Util {

	// Factoría de sesiones
	private static final SessionFactory SESSION_FACTORY;

	// Generación de factoría de sesiones.
	static {

		try {

			// Generación de configuración
			SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
			
		} catch (final Throwable ex) {

			// Error de inicialización
			System.err.println("[ERROR] Configuración de Hibernate - " + ex);
			throw new ExceptionInInitializerError();
		}

	}

	// Retorna la factoría de sesiones.
	public static SessionFactory getSessionFactory() {
		return SESSION_FACTORY;
	}

}
