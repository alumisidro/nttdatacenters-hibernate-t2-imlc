package com.everis.nttdatacenters_hibernate_t1_imlc.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;

import com.everis.nttdatacenters_hibernate_t1_imlc.dao.ClienteDaoI;
import com.everis.nttdatacenters_hibernate_t1_imlc.dao.ClienteDaoImpl;
import com.everis.nttdatacenters_hibernate_t1_imlc.entities.Cliente;


public class ClienteManagementServiceImpl implements ClienteManagementServiceI {

	private ClienteDaoI clienteDao;

	// Constructor
	public ClienteManagementServiceImpl(final Session session) {
		this.clienteDao = new ClienteDaoImpl(session);
	}

	@Override
	public void insertNewCustomer(final Cliente newCustomer) {

		// Verificar que no sea nulo pero que no tenga ID
		if (newCustomer != null && newCustomer.getIdCliente() == null) {

			// Insertar cliente
			clienteDao.insert(newCustomer);
		}

	}

	@Override
	public void updateCustomer(final Cliente updatedCustomer) {

		// Verificar que no sea nulo pero que no tenga ID
		if (updatedCustomer != null && updatedCustomer.getIdCliente() != null) {

			// Actualizar cliente
			clienteDao.update(updatedCustomer);
		}

	}

	@Override
	public void deleteCustomer(final Cliente deletedCustomer) {

		// Verificar que no sea nulo pero que no tenga ID
		if (deletedCustomer != null && deletedCustomer.getIdCliente() != null) {

			// Eliminar cliente
			clienteDao.delete(deletedCustomer);
		}

	}

	@Override
	public Cliente searchById(final Long idCliente) {

		// Resultado
		Cliente cliente = null;

		// Verificar que no sea nulo
		if (idCliente != null) {

			// Obtener cliente por ID
			cliente = clienteDao.searchById(idCliente);
		}

		return cliente;
	}

	@Override
	public List<Cliente> buscarPorNombreCompleto(final String name, final String surname1, final String surname2) {

		// Resultado
		List<Cliente> listaClientes = new ArrayList<Cliente>();

		// Verificar que no esté en blanco algún parámetro
		if (StringUtils.isNotBlank(name) && StringUtils.isNotBlank(surname1) && StringUtils.isNotBlank(surname2)) {

			// Obtener cliente por nombre
			listaClientes = clienteDao.buscarClientePorNombre(name, surname1, surname2);
		}

		return listaClientes;
	}

	@Override
	public List<Cliente> searchAll() {

		// Resultado
		List<Cliente> listaClientes = new ArrayList<Cliente>();

		// Obtener todos los clientes
		listaClientes = clienteDao.searchAll();

		return listaClientes;
	}

}
