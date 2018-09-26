package com.codedynamic.clinica.modelo;

public class Anamnesis {
	private short idAnamnesis;
	private String antecedenteEnfermedad;
	private String medicamentoActual;
	private Paciente paciente;
		
	public Anamnesis() {
		super();
	}

	public Anamnesis(short idAnamnesis, String antecedenteEnfermedad, String medicamentoActual) {
		super();
		this.idAnamnesis = idAnamnesis;
		this.antecedenteEnfermedad = antecedenteEnfermedad;
		this.medicamentoActual = medicamentoActual;
	}

	public short getIdAnamnesis() {
		return idAnamnesis;
	}

	public void setIdAnamnesis(short idAnamnesis) {
		this.idAnamnesis = idAnamnesis;
	}

	public String getAntecedenteEnfermedad() {
		return antecedenteEnfermedad;
	}

	public void setAntecedenteEnfermedad(String antecedenteEnfermedad) {
		this.antecedenteEnfermedad = antecedenteEnfermedad;
	}

	public String getMedicamentoActual() {
		return medicamentoActual;
	}

	public void setMedicamentoActual(String medicamentoActual) {
		this.medicamentoActual = medicamentoActual;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	
}
