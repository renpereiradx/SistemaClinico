package com.codedynamic.clinica.modelo;

public class Indicacion {
	private short idIndicacion;
	private String detalle;
	private String tratamiento;
	private Paciente paciente;
	
	public Indicacion() {}

	public Indicacion(short idIndicacion, String detalle, String tratamiento, Paciente paciente) {
		super();
		this.idIndicacion = idIndicacion;
		this.detalle = detalle;
		this.tratamiento = tratamiento;
		this.paciente = paciente;
	}

	public short getIdIndicacion() {
		return idIndicacion;
	}

	public void setIdIndicacion(short idIndicacion) {
		this.idIndicacion = idIndicacion;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public String getTratamiento() {
		return tratamiento;
	}

	public void setTratamiento(String tratamiento) {
		this.tratamiento = tratamiento;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
}
