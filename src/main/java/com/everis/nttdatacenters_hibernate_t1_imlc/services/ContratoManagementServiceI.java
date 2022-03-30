package com.everis.nttdatacenters_hibernate_t1_imlc.services;

import java.util.List;

// Importar Entidades
import com.everis.nttdatacenters_hibernate_t1_imlc.entities.Contrato;
import com.everis.nttdatacenters_hibernate_t1_imlc.entities.Cliente;

public interface ContratoManagementServiceI {

	// Insertar nuevo contrato
	public void insertarContrato(final Contrato newContract);

	// Actualizar contrato
	public void actualizarContrato(final Contrato updatedContract);

	// Eliminar contrato
	public void eliminarContrato(final Contrato deletedContract);

	// Obtener contrato por ID
	public Contrato searchById(final Long contractId);

	// Obtener contratos de un cliente
	public List<Contrato> searchByCustomer(final Cliente customer);

	// Obtener todos los contratos
	public List<Contrato> searchAll();

}
