package com.everis.nttdatacenters_hibernate_t1_imlc.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "CLIENTES")
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	// Identificador
	private Long idCliente;

	// Nombre
	private String nombre;

	// Primer apellido
	private String primerApellido;

	// Segundo apellido
	private String segundoApellido;

	// Documento de identificación
	private String dni;

	// Contratos del cliente
	private List<Contrato> listaContratos;

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) // Autoincremento
	@Column(name = "ID_CLIENTE")
	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	@Column(name = "NOMBRE", nullable = false)
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String name) {
		this.nombre = name;
	}

	@Column(name = "PRIMER_APELLIDO", nullable = false)
	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String surName1) {
		this.primerApellido = surName1;
	}

	@Column(name = "SEGUNDO_APELLIDO", nullable = true)
	public String getSegundoApellido() {
		return segundoApellido;
	}
	
	public void setSegundoApellido(String surName2) {
		this.segundoApellido = surName2;
	}

	@Column(name = "DNI", nullable = false, length = 9, unique = true)
	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	// De uno a muchos (un cliente puede tener muchos contratos)
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente") // "cliente" porque es la relación "Cliente cliente" en Contrato.java
	public List<Contrato> getContractsList() {
		return listaContratos;
	}
	
	public void setContractsList(List<Contrato> listaContratos) {
		this.listaContratos = listaContratos;
	}

	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente +
				", nombre=" + nombre
				+ ", Apellido1=" + primerApellido
				+ ", Apellido2=" + segundoApellido
				+ ", DNI="
		        + dni + "]";
	}

	@Transient
	public Class<?> getClase() {
		return Cliente.class;
	}

}
