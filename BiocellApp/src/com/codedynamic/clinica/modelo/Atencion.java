package com.codedynamic.clinica.modelo;

public class Atencion {
	private int idAtencion;
	private Turno turno;
	private int idTipoAtencion;
	private Usuario usuario;
	private String motivo;
	
	public int getIdAtencion() {
		return idAtencion;
	}
	public void setIdAtencion(int idAtencion) {
		this.idAtencion = idAtencion;
	}
	public Turno getTurno() {
		return turno;
	}
	public void setTurno(Turno turno) {
		this.turno = turno;
	}
	public int getIdTipoAtencion() {
		return idTipoAtencion;
	}
	public void setIdTipoAtencion(int idTipoAtencion) {
		this.idTipoAtencion = idTipoAtencion;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	
}
