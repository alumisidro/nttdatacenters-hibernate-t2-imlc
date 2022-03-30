package com.everis.nttdatacenters_hibernate_t1_imlc.dao;

import java.util.List;

import com.everis.nttdatacenters_hibernate_t1_imlc.entities.Contrato;
import com.everis.nttdatacenters_hibernate_t1_imlc.entities.Cliente;

public interface ContratoDaoI extends CommonDaoI<Contrato> {

	// Obtener contratos de un cliente
	public List<Contrato> buscarPorCliente(final Cliente cliente);

}
