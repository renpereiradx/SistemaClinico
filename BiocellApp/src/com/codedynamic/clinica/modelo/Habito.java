package com.codedynamic.clinica.modelo;

public class Habito {
	private short idHabito;
	private String alimentario;
	private String intestinal;
	private String urinario;
	private String suenho;
	private String actividadFisica;
	private String factorRiesgo;
	private String ambiental;
	private String toxico;
	private String laboral;
	private Paciente paciente;
	
	public Habito() {
		super();
	}
	
	public Habito(short idHabito, String alimentario, String intestinal, String urinario, String suenho,
			String actividadFisica, String factorRiesgo, String ambiental, String toxico, String laboral,
			Paciente paciente) {
		super();
		this.idHabito = idHabito;
		this.alimentario = alimentario;
		this.intestinal = intestinal;
		this.urinario = urinario;
		this.suenho = suenho;
		this.actividadFisica = actividadFisica;
		this.factorRiesgo = factorRiesgo;
		this.ambiental = ambiental;
		this.toxico = toxico;
		this.laboral = laboral;
		this.paciente = paciente;
	}

	public short getIdHabito() {
		return idHabito;
	}

	public void setIdHabito(short idHabito) {
		this.idHabito = idHabito;
	}

	public String getAlimentario() {
		return alimentario;
	}

	public void setAlimentario(String alimentario) {
		this.alimentario = alimentario;
	}

	public String getIntestinal() {
		return intestinal;
	}

	public void setIntestinal(String intestinal) {
		this.intestinal = intestinal;
	}

	public String getUrinario() {
		return urinario;
	}

	public void setUrinario(String urinario) {
		this.urinario = urinario;
	}

	public String getSuenho() {
		return suenho;
	}

	public void setSuenho(String suenho) {
		this.suenho = suenho;
	}

	public String getActividadFisica() {
		return actividadFisica;
	}

	public void setActividadFisica(String actividadFisica) {
		this.actividadFisica = actividadFisica;
	}

	public String getFactorRiesgo() {
		return factorRiesgo;
	}

	public void setFactorRiesgo(String factorRiesgo) {
		this.factorRiesgo = factorRiesgo;
	}

	public String getAmbiental() {
		return ambiental;
	}

	public void setAmbiental(String ambiental) {
		this.ambiental = ambiental;
	}

	public String getToxico() {
		return toxico;
	}

	public void setToxico(String toxico) {
		this.toxico = toxico;
	}

	public String getLaboral() {
		return laboral;
	}

	public void setLaboral(String laboral) {
		this.laboral = laboral;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
}
