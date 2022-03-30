package com.everis.nttdatacenters_hibernate_t1_imlc.services;

import java.util.List;

// Importar entidad
import com.everis.nttdatacenters_hibernate_t1_imlc.entities.Cliente;

public interface ClienteManagementServiceI {

	// Insertar nuevo cliente
	public void insertNewCustomer(final Cliente newCustomer);

	// Actualizar cliente
	public void updateCustomer(final Cliente updatedCustomer);

	// Eliminar cliente
	public void deleteCustomer(final Cliente deletedCustomer);

	// Buscar por ID
	public Cliente searchById(final Long customerId);

	// Busca por nombre completo
	public List<Cliente> buscarPorNombreCompleto(final String name, final String surname1, final String surname2);

	// Obtener todos los clientes
	public List<Cliente> searchAll();

}
