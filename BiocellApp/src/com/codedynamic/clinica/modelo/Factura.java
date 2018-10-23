package com.codedynamic.clinica.modelo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Factura {

	private IntegerProperty idFactura;
	private ObjectProperty<Paciente> paciente;
	private ObjectProperty<Usuario> usuario;
	private ObjectProperty<LocalDate> fecha;
	private ObjectProperty<LocalTime> hora;
	private IntegerProperty total;
	private StringProperty estado;
	private List<FacturaDetalle> facturaDetalle = new ArrayList<>();
	
	public Factura() {}
	
	public Factura(short idFactura, Paciente paciente, Usuario usuario, LocalDate fecha, LocalTime hora, int total, String estado) {
		this.idFactura = new SimpleIntegerProperty(idFactura);
		this.paciente = new SimpleObjectProperty<>(paciente);
		this.usuario = new SimpleObjectProperty<>(usuario);
		this.fecha = new SimpleObjectProperty<>(fecha);
		this.hora = new SimpleObjectProperty<>(hora);
		this.total = new SimpleIntegerProperty(total);
		this.estado = new SimpleStringProperty(estado);
	}

	public final IntegerProperty idFacturaProperty() {
		return this.idFactura;
	}
	
	public final int getIdFactura() {
		return this.idFacturaProperty().get();
	}

	public final void setIdFactura(final int idFactura) {
		this.idFacturaProperty().set(idFactura);
	}

	public final ObjectProperty<Paciente> pacienteProperty() {
		return this.paciente;
	}

	public final Paciente getPaciente() {
		return this.pacienteProperty().get();
	}

	public final void setPaciente(final Paciente paciente) {
		this.pacienteProperty().set(paciente);
	}

	public final ObjectProperty<Usuario> usuarioProperty() {
		return this.usuario;
	}

	public final Usuario getUsuario() {
		return this.usuarioProperty().get();
	}

	public final void setUsuario(final Usuario usuario) {
		this.usuarioProperty().set(usuario);
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

	public List<FacturaDetalle> getFacturaDetalle() {
		return facturaDetalle;
	}

	public void setFacturaDetalle(FacturaDetalle facturaDetalle) {
		this.facturaDetalle.add(facturaDetalle);
	}
	
}
