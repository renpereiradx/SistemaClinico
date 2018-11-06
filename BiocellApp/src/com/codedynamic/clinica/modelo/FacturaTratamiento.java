package com.codedynamic.clinica.modelo;

import java.time.LocalDate;
import java.time.LocalTime;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FacturaTratamiento {

	private IntegerProperty idFacturaT;
	private Paciente paciente;
	private Usuario usuario;
	private ObjectProperty<LocalDate> fecha;
	private ObjectProperty<LocalTime> hora;
	private StringProperty detalle;
	private IntegerProperty total;
	private StringProperty estado;
	
	public FacturaTratamiento() {
		this((short) 0, null, null, null, null, null, 0, null);
	}
	
	public FacturaTratamiento(short idFactura, Paciente paciente, Usuario usuario, LocalDate fecha, LocalTime hora,
			String detalle, int total, String estado) {
		this.idFacturaT = new SimpleIntegerProperty(idFactura);
		this.paciente = paciente;
		this.usuario = usuario;
		this.fecha = new SimpleObjectProperty<>(fecha);
		this.hora = new SimpleObjectProperty<>(hora);
		this.detalle = new SimpleStringProperty(detalle);
		this.total = new SimpleIntegerProperty(total);
		this.estado = new SimpleStringProperty(estado);
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public final IntegerProperty idFacturaTProperty() {
		return this.idFacturaT;
	}
	
	public final int getIdFacturaT() {
		return this.idFacturaTProperty().get();
	}
	
	public final void setIdFacturaT(final int idFacturaT) {
		this.idFacturaTProperty().set(idFacturaT);
	}
	
	public final ObjectProperty<LocalDate> fechaProperty() {
		return this.fecha;
	}
	
	public final LocalDate getFecha() {
		return this.fechaProperty().get();
	}
	
	public final void setFecha(final LocalDate fecha) {
		this.fechaProperty().set(fecha);
	}
	
	public final ObjectProperty<LocalTime> horaProperty() {
		return this.hora;
	}
	
	public final LocalTime getHora() {
		return this.horaProperty().get();
	}	

	public final void setHora(final LocalTime hora) {
		this.horaProperty().set(hora);
	}
	
	public final StringProperty detalleProperty() {
		return this.detalle;
	}
	
	public final String getDetalle() {
		return this.detalleProperty().get();
	}
	
	public final void setDetalle(final String detalle) {
		this.detalleProperty().set(detalle);
	}
	
	public final IntegerProperty totalProperty() {
		return this.total;
	}
	

	public final int getTotal() {
		return this.totalProperty().get();
	}

	public final void setTotal(final int total) {
		this.totalProperty().set(total);
	}
	
	public final StringProperty estadoProperty() {
		return this.estado;
	}
	
	public final String getEstado() {
		return this.estadoProperty().get();
	}
	
	public final void setEstado(final String estado) {
		this.estadoProperty().set(estado);
	}
}	