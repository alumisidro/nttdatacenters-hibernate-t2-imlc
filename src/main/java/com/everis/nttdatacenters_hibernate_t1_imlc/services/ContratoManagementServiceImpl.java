package com.everis.nttdatacenters_hibernate_t1_imlc.services;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.everis.nttdatacenters_hibernate_t1_imlc.dao.ContratoDaoI;
import com.everis.nttdatacenters_hibernate_t1_imlc.dao.ContratoDaoImpl;
import com.everis.nttdatacenters_hibernate_t1_imlc.entities.Contrato;
import com.everis.nttdatacenters_hibernate_t1_imlc.entities.Cliente;

public class ContratoManagementServiceImpl implements ContratoManagementServiceI {
	
	
	private ContratoDaoI contratoDao;

	// Constructor
	public ContratoManagementServiceImpl(final Session session) {
		this.contratoDao = new ContratoDaoImpl(session);
	}

	@Override
	public void insertarContrato(final Contrato nuevoContrato) {

		// Verificar que no sea nulo pero que no tenga ID
		if (nuevoContrato != null && nuevoContrato.getIdContrato() == null) {

			// Insertar contrato
			contratoDao.insert(nuevoContrato);
		}

	}

	@Override
	public void actualizarContrato(final Contrato contratoModificado) {

		// Verificar que no sea nulo pero que no tenga ID
		if (contratoModificado != null && contratoModificado.getIdContrato() != null) {

			// Actualizar contrato
			contratoDao.update(contratoModificado);
		}

	}

	@Override
	public void eliminarContrato(final Contrato deletedContract) {

		// Verificar que no sea nulo pero que no tenga ID
		if (deletedContract != null && deletedContract.getIdContrato() != null) {

			// Eliminar contrato
			contratoDao.delete(deletedContract);
		}

	}

	@Override
	public Contrato searchById(final Long contractId) {

		// Resultado
		Contrato contract = null;

		// Comprobar que el ID (de contrato) no es nulo
		if (contractId != null) {

			// Obtener contrato por ID
			contract = contratoDao.searchById(contractId);
		}

		return contract;
	}

	@Override
	public List<Contrato> searchByCustomer(final Cliente customer) {

		// Resultado
		List<Contrato> contractsList = new ArrayList<Contrato>();

		// Comprobar que el cliente no es nulo
		if (customer != null) {

			// Obtener contratos del cliente
			contractsList = contratoDao.buscarPorCliente(customer);
		}

		return contractsList;
	}

	@Override
	public List<Contrato> searchAll() {

		// Resultado
		List<Contrato> contractsList = new ArrayList<Contrato>();

		// Obtener todos los contratos
		contractsList = contratoDao.searchAll();

		return contractsList;
	}

}
