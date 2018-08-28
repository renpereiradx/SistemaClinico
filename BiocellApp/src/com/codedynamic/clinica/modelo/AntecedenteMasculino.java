package com.codedynamic.clinica.modelo;

public class AntecedenteMasculino {
	private String inicioSexual;
	private short nroPareja;
	private String cirugiaGenital;
	private String est;
	private String rastreoPostrata;
	private Paciente paciente;
	
	public AntecedenteMasculino() {
		this(null, (short) 0, null, null, null, null);
	}

	public AntecedenteMasculino(String inicioSexual, short nroPareja, String cirugiaGenital, String est,
			String rastreoPostrata, Paciente paciente) {
		this.inicioSexual = inicioSexual;
		this.nroPareja = nroPareja;
		this.cirugiaGenital = cirugiaGenital;
		this.est = est;
		this.rastreoPostrata = rastreoPostrata;
		this.paciente = paciente;
	}	
	
	public String getInicioSexual() {
		return inicioSexual;
	}
	public void setInicioSexual(String inicioSexual) {
		this.inicioSexual = inicioSexual;
	}
	public short getNroPareja() {
		return nroPareja;
	}
	public void setNroPareja(short nroPareja) {
		this.nroPareja = nroPareja;
	}
	public String getCirugiaGenital() {
		return cirugiaGenital;
	}
	public void setCirugiaGenital(String cirugiaGenital) {
		this.cirugiaGenital = cirugiaGenital;
	}
	public String getEst() {
		return est;
	}
	public void setEst(String est) {
		this.est = est;
	}
	public String getRastreoPostrata() {
		return rastreoPostrata;
	}
	public void setRastreoPostrata(String rastreoPostrata) {
		this.rastreoPostrata = rastreoPostrata;
	}
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

}
