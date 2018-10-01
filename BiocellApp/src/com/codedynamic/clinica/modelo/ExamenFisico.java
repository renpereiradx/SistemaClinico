package com.codedynamic.clinica.modelo;

public class ExamenFisico {
	private short idEFisico;
	private String pa;
	private String peso;
	private String altura;
	private String fc;
	private String fr;
	private String tAxilar;
	private String talla;
	private String imc;
	private String ca;
	private String glicemia;
	private String spo;
	private String detalle;
	private Paciente paciente;
	
	public ExamenFisico() {	}
	
	public ExamenFisico(short idEFisico, String pa, String peso, String altura, String fc, String fr, String tAxilar,
			String talla, String imc, String ca, String glicemia, String spo, String detalle, Paciente paciente) {
		super();
		this.idEFisico = idEFisico;
		this.pa = pa;
		this.peso = peso;
		this.altura = altura;
		this.fc = fc;
		this.fr = fr;
		this.tAxilar = tAxilar;
		this.talla = talla;
		this.imc = imc;
		this.ca = ca;
		this.glicemia = glicemia;
		this.spo = spo;
		this.detalle = detalle;
		this.paciente = paciente;
	}

	public short getIdEFisico() {
		return idEFisico;
	}

	public void setIdEFisico(short idEFisico) {
		this.idEFisico = idEFisico;
	}

	public String getPa() {
		return pa;
	}

	public void setPa(String pa) {
		this.pa = pa;
	}

	public String getPeso() {
		return peso;
	}

	public void setPeso(String peso) {
		this.peso = peso;
	}

	public String getAltura() {
		return altura;
	}

	public void setAltura(String altura) {
		this.altura = altura;
	}

	public String getFc() {
		return fc;
	}

	public void setFc(String fc) {
		this.fc = fc;
	}

	public String getFr() {
		return fr;
	}

	public void setFr(String fr) {
		this.fr = fr;
	}

	public String gettAxilar() {
		return tAxilar;
	}

	public void settAxilar(String tAxilar) {
		this.tAxilar = tAxilar;
	}

	public String getTalla() {
		return talla;
	}

	public void setTalla(String talla) {
		this.talla = talla;
	}

	public String getImc() {
		return imc;
	}

	public void setImc(String imc) {
		this.imc = imc;
	}

	public String getCa() {
		return ca;
	}

	public void setCa(String ca) {
		this.ca = ca;
	}

	public String getGlicemia() {
		return glicemia;
	}

	public void setGlicemia(String glicemia) {
		this.glicemia = glicemia;
	}

	public String getSpo() {
		return spo;
	}

	public void setSpo(String spo) {
		this.spo = spo;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}	
}
