package com.codedynamic.clinica.modelo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Tratamiento {

	private IntegerProperty idTratamiento;
	private StringProperty nombre;
	private IntegerProperty precio;
	private StringProperty descripcion;
	
	public Tratamiento() {
		this((short) 0, null, 0, null);
	}
	
	public Tratamiento(short idTratamiento, String nombre, int precio, String descripcion) {
		this.idTratamiento = new SimpleIntegerProperty(idTratamiento);
		this.nombre = new SimpleStringProperty(nombre);
		this.precio = new SimpleIntegerProperty(precio);
		this.descripcion = new SimpleStringProperty(descripcion);
	}

	public final IntegerProperty idTratamientoProperty() {
		return this.idTratamiento;
	}
	
	public final int getIdTratamiento() {
		return this.idTratamientoProperty().get();
	}
	
	public final void setIdTratamiento(final int idTratamiento) {
		this.idTratamientoProperty().set(idTratamiento);
	}
	
	public final StringProperty nombreProperty() {
		return this.nombre;
	}
	
	public final String getNombre() {
		return this.nombreProperty().get();
	}
	
	public final void setNombre(final String nombre) {
		this.nombreProperty().set(nombre);
	}
	
	public final IntegerProperty precioProperty() {
		return this.precio;
	}
	
	public final int getPrecio() {
		return this.precioProperty().get();
	}

	public final void setPrecio(final int precio) {
		this.precioProperty().set(precio);
	}
	
	public final StringProperty descripcionProperty() {
		return this.descripcion;
	}
	
	public final String getDescripcion() {
		return this.descripcionProperty().get();
	}
	
	public final void setDescripcion(final String descripcion) {
		this.descripcionProperty().set(descripcion);
	}
	
}
