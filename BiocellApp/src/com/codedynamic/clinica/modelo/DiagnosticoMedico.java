package com.codedynamic.clinica.modelo;

public class DiagnosticoMedico {
	private short idDM;
	private String detalle;
	private String conducta;
	private Paciente paciente;
	private String indicacion;
	private String tratamiento;
	
	public DiagnosticoMedico() {}

	public DiagnosticoMedico(short idDM, String detalle, String conducta, Paciente paciente, String indicacion, String tratamiento) {
		super();
		this.idDM = idDM;
		this.detalle = detalle;
		this.conducta = conducta;
		this.paciente = paciente;
		this.indicacion = indicacion;
		this.tratamiento = tratamiento;
	}

	public short getIdDM() {
		return idDM;
	}

	public void setIdDM(short idDM) {
		this.idDM = idDM;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public String getConducta() {
		return conducta;
	}

	public void setConducta(String conducta) {
		this.conducta = conducta;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public String getIndicacion() {
		return indicacion;
	}

	public void setIndicacion(String indicacion) {
		this.indicacion = indicacion;
	}

	public String getTratamiento() {
		return tratamiento;
	}

	public void setTratamiento(String tratamiento) {
		this.tratamiento = tratamiento;
	}
}
