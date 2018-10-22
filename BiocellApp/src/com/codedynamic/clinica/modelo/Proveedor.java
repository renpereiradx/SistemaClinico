package com.codedynamic.clinica.modelo;

public class Proveedor {
	private short idProveedor;
	private String nombre;
	private String ruc;
	private String direccion;
	
	public Proveedor() {}

	public Proveedor(short idProveedor, String nombre, String ruc, String direccion) {
		super();
		this.idProveedor = idProveedor;
		this.nombre = nombre;
		this.ruc = ruc;
		this.direccion = direccion;
	}

	public short getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(short idProveedor) {
		this.idProveedor = idProveedor;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
}
