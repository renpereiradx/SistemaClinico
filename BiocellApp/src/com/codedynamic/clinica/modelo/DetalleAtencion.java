package com.codedynamic.clinica.modelo;

import java.time.LocalDate;
import java.time.LocalTime;

public class DetalleAtencion {
	private short idDA;
	private Atencion atencion;
	private Usuario usuario;
	private Anamnesis anamnesis;
	private Habito habito;
	private ExamenFisico examenFisico;
	private Ippa ippa;
	private DiagnosticoMedico diagnosticoMedico;
	private LocalDate fecha;
	private LocalTime hora;
	
	public DetalleAtencion() {}

	public DetalleAtencion(short idDA, Atencion atencion, Usuario usuario, Anamnesis anamnesis, Habito habito,
			ExamenFisico examenFisico, Ippa ippa, DiagnosticoMedico diagnosticoMedico, LocalDate fecha,
			LocalTime hora) {
		super();
		this.idDA = idDA;
		this.atencion = atencion;
		this.usuario = usuario;
		this.anamnesis = anamnesis;
		this.habito = habito;
		this.examenFisico = examenFisico;
		this.ippa = ippa;
		this.diagnosticoMedico = diagnosticoMedico;
		this.fecha = fecha;
		this.hora = hora;
	}

	public short getIdDA() {
		return idDA;
	}

	public void setIdDA(short idDA) {
		this.idDA = idDA;
	}

	public Atencion getAtencion() {
		return atencion;
	}

	public void setAtencion(Atencion atencion) {
		this.atencion = atencion;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Anamnesis getAnamnesis() {
		return anamnesis;
	}

	public void setAnamnesis(Anamnesis anamnesis) {
		this.anamnesis = anamnesis;
	}

	public Habito getHabito() {
		return habito;
	}

	public void setHabito(Habito habito) {
		this.habito = habito;
	}

	public ExamenFisico getExamenFisico() {
		return examenFisico;
	}

	public void setExamenFisico(ExamenFisico examenFisico) {
		this.examenFisico = examenFisico;
	}

	public Ippa getIppa() {
		return ippa;
	}

	public void setIppa(Ippa ippa) {
		this.ippa = ippa;
	}

	public DiagnosticoMedico getDiagnosticoMedico() {
		return diagnosticoMedico;
	}

	public void setDiagnosticoMedico(DiagnosticoMedico diagnosticoMedico) {
		this.diagnosticoMedico = diagnosticoMedico;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}
}
