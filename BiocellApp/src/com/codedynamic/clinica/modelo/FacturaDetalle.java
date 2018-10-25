package com.codedynamic.clinica.modelo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class FacturaDetalle {

	private IntegerProperty idDetalle;
	private ObjectProperty<Factura> factura;
	private ObjectProperty<Producto> producto;
	private IntegerProperty cantidad;
	private IntegerProperty precio;
	
	public FacturaDetalle() {}
	
	public FacturaDetalle(short idDetalle, Factura factura, Producto producto, short cantidad, int precio) {
		this.idDetalle = new SimpleIntegerProperty(idDetalle);
		this.factura = new SimpleObjectProperty<>(factura);
		this.producto = new SimpleObjectProperty<>(producto);
		this.cantidad = new SimpleIntegerProperty(cantidad);
		this.precio = new SimpleIntegerProperty(precio);
	}

	public final IntegerProperty idDetalleProperty() {
		return this.idDetalle;
	}

	public final int getIdDetalle() {
		return this.idDetalleProperty().get();
	}

	public final void setIdDetalle(final int idDetalle) {
		this.idDetalleProperty().set(idDetalle);
	}

	public final ObjectProperty<Factura> facturaProperty() {
		return this.factura;
	}

	public final Factura getFactura() {
		return this.facturaProperty().get();
	}

	public final void setFactura(final Factura factura) {
		this.facturaProperty().set(factura);
	}

	public final ObjectProperty<Producto> productoProperty() {
		return this.producto;
	}

	public final Producto getProducto() {
		return this.productoProperty().get();
	}

	public final void setProducto(final Producto producto) {
		this.productoProperty().set(producto);
	}

	public final IntegerProperty cantidadProperty() {
		return this.cantidad;
	}

	public final int getCantidad() {
		return this.cantidadProperty().get();
	}

	public final void setCantidad(final int cantidad) {
		this.cantidadProperty().set(cantidad);
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
	
}
