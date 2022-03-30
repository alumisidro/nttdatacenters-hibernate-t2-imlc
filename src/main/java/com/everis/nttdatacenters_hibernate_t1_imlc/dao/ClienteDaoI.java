package com.everis.nttdatacenters_hibernate_t1_imlc.dao;

import java.util.List;

import com.everis.nttdatacenters_hibernate_t1_imlc.entities.Cliente;


public interface ClienteDaoI extends CommonDaoI<Cliente> {

	// Obtener clientes con el nombre
	public List<Cliente> buscarClientePorNombre(final String nombre, final String primerApellido, final String segundoApellido);

}
