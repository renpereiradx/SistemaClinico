package com.codedynamic.clinica.modelo;

import java.time.LocalDate;
import java.time.LocalTime;

public class ModoPagoTratamiento {

	private short numPago;
	private FacturaTratamiento idFacturaT;
	private int monto;
	private LocalDate fecha;
	private LocalTime hora;
	
	public ModoPagoTratamiento() {}

	public ModoPagoTratamiento(short numPago, FacturaTratamiento idFacturaT, int monto, LocalDate fecha,
			LocalTime hora) {
		super();
		this.numPago = numPago;
		this.idFacturaT = idFacturaT;
		this.monto = monto;
		this.fecha = fecha;
		this.hora = hora;
	}

	public short getNumPago() {
		return numPago;
	}

	public void setNumPago(short numPago) {
		this.numPago = numPago;
	}

	public FacturaTratamiento getIdFacturaT() {
		return idFacturaT;
	}

	public void setIdFacturaT(FacturaTratamiento idFacturaT) {
		this.idFacturaT = idFacturaT;
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
}
