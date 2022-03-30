package com.everis.nttdatacenters_hibernate_t1_imlc.dao;

import java.util.List;

import org.hibernate.Session;

import com.everis.nttdatacenters_hibernate_t1_imlc.entities.Cliente;


public class ClienteDaoImpl extends CommonDaoImpl<Cliente> implements ClienteDaoI {

	// Sesión de BD
	private Session session;

	// Constructor
	public ClienteDaoImpl(Session session) {
		super(session);
		this.session = session;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cliente> buscarClientePorNombre(String nombre, String primerApellido, String segundoApellido) {

		// Verificar que la sesión esté abierta
		if (!session.getTransaction().isActive()) {
			session.getTransaction().begin();
		}
		
		// Buscar cliente por nombre
		final List<Cliente> listaClientes = session
		        .createQuery(
		        		"FROM Customer WHERE nombre='" + nombre+ "' AND primerApellido='" + primerApellido + "' AND segundoApellido='" + segundoApellido + "'"
		        		).list();

		return listaClientes;
	}

}
