package com.codedynamic.clinica.modelo;

import java.time.LocalDate;
import java.time.LocalTime;

public class ModoPago {
	private short idModoPago;
	private String metodo;
	private int monto;
	private LocalDate fecha;
	private LocalTime hora;
	private Factura factura;
	
	public ModoPago() {}

	public ModoPago(short idModoPago, String metodo, int monto, LocalDate fecha, LocalTime hora, Factura factura) {
		super();
		this.idModoPago = idModoPago;
		this.metodo = metodo;
		this.monto = monto;
		this.fecha = fecha;
		this.hora = hora;
		this.factura = factura;
	}

	public short getIdModoPago() {
		return idModoPago;
	}

	public void setIdModoPago(short idModoPago) {
		this.idModoPago = idModoPago;
	}

	public String getMetodo() {
		return metodo;
	}

	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}

	public int getMonto() {
		return monto;
	}

	public void setMonto(int monto) {
		this.monto = monto;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}
}
