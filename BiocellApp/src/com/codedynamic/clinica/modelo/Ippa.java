package com.codedynamic.clinica.modelo;

public class Ippa {
	private short idPpa;
	private String cabeza;
	private String cuello;
	private String torax;
	private String aparatoCV;
	private String abdomen;
	private String aparatoG;
	private String piel;
	private String sistema;
	private String snc;
	private String psquiatrico;
	private Paciente paciente;
	
	public Ippa() {}

	public Ippa(short idPpa, String cabeza, String cuello, String torax, String aparatoCV, String abdomen,
			String aparatoG, String piel, String sistema, String snc, String psquiatrico, Paciente paciente) {
		super();
		this.idPpa = idPpa;
		this.cabeza = cabeza;
		this.cuello = cuello;
		this.torax = torax;
		this.aparatoCV = aparatoCV;
		this.abdomen = abdomen;
		this.aparatoG = aparatoG;
		this.piel = piel;
		this.sistema = sistema;
		this.snc = snc;
		this.psquiatrico = psquiatrico;
		this.paciente = paciente;
	}

	public short getIdPpa() {
		return idPpa;
	}

	public void setIdPpa(short idPpa) {
		this.idPpa = idPpa;
	}

	public String getCabeza() {
		return cabeza;
	}

	public void setCabeza(String cabeza) {
		this.cabeza = cabeza;
	}

	public String getCuello() {
		return cuello;
	}

	public void setCuello(String cuello) {
		this.cuello = cuello;
	}

	public String getTorax() {
		return torax;
	}

	public void setTorax(String torax) {
		this.torax = torax;
	}

	public String getAparatoCV() {
		return aparatoCV;
	}

	public void setAparatoCV(String aparatoCV) {
		this.aparatoCV = aparatoCV;
	}

	public String getAbdomen() {
		return abdomen;
	}

	public void setAbdomen(String abdomen) {
		this.abdomen = abdomen;
	}

	public String getAparatoG() {
		return aparatoG;
	}

	public void setAparatoG(String aparatoG) {
		this.aparatoG = aparatoG;
	}

	public String getPiel() {
		return piel;
	}

	public void setPiel(String piel) {
		this.piel = piel;
	}

	public String getSistema() {
		return sistema;
	}

	public void setSistema(String sistema) {
		this.sistema = sistema;
	}

	public String getSnc() {
		return snc;
	}

	public void setSnc(String snc) {
		this.snc = snc;
	}

	public String getPsquiatrico() {
		return psquiatrico;
	}

	public void setPsquiatrico(String psquiatrico) {
		this.psquiatrico = psquiatrico;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
}
