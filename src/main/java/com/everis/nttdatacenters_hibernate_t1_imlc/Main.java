package com.everis.nttdatacenters_hibernate_t1_imlc;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import com.everis.nttdatacenters_hibernate_t1_imlc.entities.Contrato;
import com.everis.nttdatacenters_hibernate_t1_imlc.entities.Cliente;
import com.everis.nttdatacenters_hibernate_t1_imlc.services.ContratoManagementServiceI;
import com.everis.nttdatacenters_hibernate_t1_imlc.services.ContratoManagementServiceImpl;
import com.everis.nttdatacenters_hibernate_t1_imlc.services.ClienteManagementServiceI;
import com.everis.nttdatacenters_hibernate_t1_imlc.services.ClienteManagementServiceImpl;


public class Main {

	public static void main(String[] args) {
		
		// Inicio de sesión
		final Session session = Util.getSessionFactory().openSession();

		// Inicio de servicios
		final ClienteManagementServiceI servicioCliente = new ClienteManagementServiceImpl(session);
		final ContratoManagementServiceI servicioContrato = new ContratoManagementServiceImpl(session);

		// Nuevo cliente
		Cliente cliente = new Cliente();
		cliente.setDni("123456789");
		cliente.setNombre("Isidro Manuel");
		cliente.setPrimerApellido("Linares");
		cliente.setSegundoApellido("Camarena");
		
		// Insertar cliente
		servicioCliente.insertNewCustomer(cliente);

		// Nuevo contrato
		Contrato contrato = new Contrato();
		contrato.setCliente(cliente);
		contrato.setPrecioMensual(10.50);
		contrato.setFechaInicio(new Date());
		// contrato.setFechaFin(new Date());

		// Insertar contrato
		servicioContrato.insertarContrato(contrato);

		// Obtener contratos del cliente creado
		final List<Contrato> contratosDeCliente = servicioContrato.searchByCustomer(cliente);

		// Si hay contratos...
		if (contratosDeCliente != null && contratosDeCliente.size() > 0) {
			// Imprimir uno en cada línea
			for (Contrato contract : contratosDeCliente) {
				System.out.println(contract.toString() + "\n");
			}
		}

		// Cerrar sesión
		session.close();

	}

}
