package com.codedynamic.clinica.modelo;

public class CategoriaProducto {
	private short idCategoria;
	private String categoria;
	private String descripcion;
	
	public CategoriaProducto() {}

	public CategoriaProducto(short idCategoria, String categoria, String descripcion) {
		super();
		this.idCategoria = idCategoria;
		this.categoria = categoria;
		this.descripcion = descripcion;
	}

	public short getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(short idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
