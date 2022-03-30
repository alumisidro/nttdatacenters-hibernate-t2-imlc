package com.everis.nttdatacenters_hibernate_t1_imlc.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "CONTRATOS")
public class Contrato implements Serializable {

	private static final long serialVersionUID = 1L;

	// ID
	private Long idContrato;

	// Fecha inicial
	private Date fechaInicio;

	// Fecha final
	private Date fechaFin;

	// Precio mensual
	private Double precioMensual;

	// Cliente asociado
	private Cliente cliente;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_CONTRATO")
	public Long getIdContrato() {
		return idContrato;
	}

	public void setIdContrato(Long contractId) {
		this.idContrato = contractId;
	}

	@Column(name = "FECHA_INICIO", nullable = false)
	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date startDate) {
		this.fechaInicio = startDate;
	}

	@Column(name = "FECHA_FIN", nullable = true)
	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date endDate) {
		this.fechaFin = endDate;
	}

	@Column(name = "PRECIO_MENSUAL", nullable = true)
	public Double getPrecioMensual() {
		return precioMensual;
	}

	public void setPrecioMensual(Double mensualPrice) {
		this.precioMensual = mensualPrice;
	}
	
	@ManyToOne
	@JoinColumn(name = "ID_CLIENTE")
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Transient
	public Class<?> getClase() {
		return Contrato.class;
	}

}
