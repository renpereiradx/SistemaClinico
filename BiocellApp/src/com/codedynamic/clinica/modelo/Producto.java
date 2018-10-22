package com.codedynamic.clinica.modelo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Producto {
	private IntegerProperty idProducto;
	private StringProperty nombre;
	private StringProperty descripcion;
	private IntegerProperty precio;
	private IntegerProperty stock;
	private CategoriaProducto categoriaProducto;
	
	public Producto() {}
	
	public Producto(short idProducto, String nombre, String descripcion, int precio, short stock, CategoriaProducto categoriaProducto) {
		this.idProducto = new SimpleIntegerProperty(idProducto);
		this.nombre = new SimpleStringProperty(nombre);
		this.descripcion = new SimpleStringProperty(descripcion);
		this.precio = new SimpleIntegerProperty(precio);
		this.stock = new SimpleIntegerProperty(stock);
		this.categoriaProducto = categoriaProducto;
	}

	public final IntegerProperty idProductoProperty() {
		return this.idProducto;
	}

	public final int getIdProducto() {
		return this.idProductoProperty().get();
	}	

	public final void setIdProducto(final int idProducto) {
		this.idProductoProperty().set(idProducto);
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
	
	public final StringProperty descripcionProperty() {
		return this.descripcion;
	}
	
	public final String getDescripcion() {
		return this.descripcionProperty().get();
	}
	
	public final void setDescripcion(final String descripcion) {
		this.descripcionProperty().set(descripcion);
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
	
	public final IntegerProperty stockProperty() {
		return this.stock;
	}
	
	public final int getStock() {
		return this.stockProperty().get();
	}
	
	public final void setStock(final int stock) {
		this.stockProperty().set(stock);
	}

	public CategoriaProducto getCategoriaProducto() {
		return categoriaProducto;
	}

	public void setCategoriaProducto(CategoriaProducto categoriaProducto) {
		this.categoriaProducto = categoriaProducto;
	}
}
