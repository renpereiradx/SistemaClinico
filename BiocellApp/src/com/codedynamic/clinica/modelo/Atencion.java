package com.codedynamic.clinica.modelo;

public class Atencion {
	private short idAtencion;
	private Turno turno;
	private short idTipoAtencion;
	private Usuario usuario;
	private String motivo;
	
	public short getIdAtencion() {
		return idAtencion;
	}
	public void setIdAtencion(short idAtencion) {
		this.idAtencion = idAtencion;
	}
	public Turno getTurno() {
		return turno;
	}
	public void setTurno(Turno turno) {
		this.turno = turno;
	}
	public short getIdTipoAtencion() {
		return idTipoAtencion;
	}
	public void setIdTipoAtencion(short idTipoAtencion) {
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
	
	@Override
	public String toString() {
		return "Atencion [idAtencion=" + idAtencion + ", turno=" + turno + ", idTipoAtencion=" + idTipoAtencion
				+ ", usuario=" + usuario + ", motivo=" + motivo + "]";
	}
}
