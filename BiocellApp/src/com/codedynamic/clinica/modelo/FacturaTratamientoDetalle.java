package com.codedynamic.clinica.modelo;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class FacturaTratamientoDetalle {

	private IntegerProperty idDetalleFactura;
	private FacturaTratamiento facturaTratamiento;
	private Tratamiento tratamiento;
	private IntegerProperty sesiones;
	private IntegerProperty precio;
	
	public FacturaTratamientoDetalle() {
		this(null, null, null, null, null);
	}

	public FacturaTratamientoDetalle(Short idDetalleFactura, FacturaTratamiento facturaTratamiento,
			Tratamiento tratamiento, Short sesiones, Integer precio) {
		this.idDetalleFactura = new SimpleIntegerProperty(idDetalleFactura);
		this.facturaTratamiento = facturaTratamiento;
		this.tratamiento = tratamiento;
		this.sesiones = new SimpleIntegerProperty(sesiones);
		this.precio = new SimpleIntegerProperty(precio);
	}

	public FacturaTratamiento getFacturaTratamiento() {
		return facturaTratamiento;
	}

	public void setFacturaTratamiento(FacturaTratamiento facturaTratamiento) {
		this.facturaTratamiento = facturaTratamiento;
	}

	public Tratamiento getTratamiento() {
		return tratamiento;
	}

	public void setTratamiento(Tratamiento tratamiento) {
		this.tratamiento = tratamiento;
	}

	public final IntegerProperty idDetalleFacturaProperty() {
		return this.idDetalleFactura;
	}
	
	public final int getIdDetalleFactura() {
		return this.idDetalleFacturaProperty().get();
	}
	
	public final void setIdDetalleFactura(final int idDetalleFactura) {
		this.idDetalleFacturaProperty().set(idDetalleFactura);
	}
	
	public final IntegerProperty sesionesProperty() {
		return this.sesiones;
	}
	
	public final int getSesiones() {
		return this.sesionesProperty().get();
	}
	
	public final void setSesiones(final int sesiones) {
		this.sesionesProperty().set(sesiones);
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
