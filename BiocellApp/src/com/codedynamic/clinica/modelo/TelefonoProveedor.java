package com.codedynamic.clinica.modelo;

public class TelefonoProveedor {
	private String telefono;
	private Proveedor proveedor;
	
	public TelefonoProveedor() {}

	public TelefonoProveedor(String telefono, Proveedor proveedor) {
		super();
		this.telefono = telefono;
		this.proveedor = proveedor;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
}
