package com.codedynamic.clinica.modelo;

import java.util.List;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Proveedor {
	private IntegerProperty idProveedor;
	private StringProperty nombre;
	private StringProperty ruc;
	private StringProperty direccion;
	private ObservableList<TelefonoProveedor> telefonoProveedor = FXCollections.observableArrayList();
	
	public Proveedor() {
		this((short) 0, null, null, null);
	}

	public Proveedor(short idProveedor, String nombre, String ruc, String direccion) {
		super();
		this.idProveedor = new SimpleIntegerProperty(idProveedor);
		this.nombre = new SimpleStringProperty(nombre);
		this.ruc = new SimpleStringProperty(ruc);
		this.direccion = new SimpleStringProperty(direccion);
	}

	public final IntegerProperty idProveedorProperty() {
		return this.idProveedor;
	}

	public final int getIdProveedor() {
		return this.idProveedorProperty().get();
	}
	
	public final void setIdProveedor(final int idProveedor) {
		this.idProveedorProperty().set(idProveedor);
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
	
	public final StringProperty rucProperty() {
		return this.ruc;
	}
	
	public final String getRuc() {
		return this.rucProperty().get();
	}
	
	public final void setRuc(final String ruc) {
		this.rucProperty().set(ruc);
	}
	
	public final StringProperty direccionProperty() {
		return this.direccion;
	}
	
	public final String getDireccion() {
		return this.direccionProperty().get();
	}
	
	public final void setDireccion(final String direccion) {
		this.direccionProperty().set(direccion);
	}

	public ObservableList<TelefonoProveedor> getTelefonoProveedor() {
		return telefonoProveedor;
	}

	public void setTelefonoProveedor(TelefonoProveedor telefonoProveedor) {
		this.telefonoProveedor.add(telefonoProveedor);
	}
		
}