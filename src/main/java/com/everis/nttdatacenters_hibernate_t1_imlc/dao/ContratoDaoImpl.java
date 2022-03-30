package com.everis.nttdatacenters_hibernate_t1_imlc.dao;

import java.util.List;

import org.hibernate.Session;

import com.everis.nttdatacenters_hibernate_t1_imlc.entities.Contrato;
import com.everis.nttdatacenters_hibernate_t1_imlc.entities.Cliente;

public class ContratoDaoImpl extends CommonDaoImpl<Contrato> implements ContratoDaoI {

	// Sesión de conexión a BD
	private Session session;

	// Constructor
	public ContratoDaoImpl(Session session) {
		super(session);
		this.session = session;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Contrato> buscarPorCliente(final Cliente cliente) {

		// Verificar sesión abierta
		if (!session.getTransaction().isActive()) {
			session.getTransaction().begin();
		}

		// Buscar contratos por cliente
		final List<Contrato> listaContratos = session.createQuery(
				"FROM Contratos WHERE cliente.idCliente=" + cliente.getIdCliente()
		).list();

		return listaContratos;
	}

}
