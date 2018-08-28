package com.codedynamic.clinica.modelo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;
import java.time.LocalTime;

public class Turno {

	private short id_turno;
	private IntegerProperty numero;
	private ObjectProperty<LocalDate> fecha;
	private ObjectProperty<LocalTime> hora;
	private String descripcion;
	private StringProperty estado;
	private Paciente paciente;

   public Turno() {
       this((short) 0, 0, null, null, null, null, null);
   }

    public Turno(short id_turno, int numero, LocalDate fecha, LocalTime hora, Paciente paciente, String descripcion, String estado) {
	    this.id_turno = id_turno;
	    this.numero = new SimpleIntegerProperty(numero);
	    this.fecha = new SimpleObjectProperty<LocalDate>(fecha);
	    this.hora = new SimpleObjectProperty<LocalTime>(hora);
		this.paciente = paciente;
		this.descripcion = descripcion;
		this.estado = new SimpleStringProperty(estado);
    }

    public short getId_turno() {
        return id_turno;
    }

    public void setId_turno(short id_turno) {
        this.id_turno = id_turno;
    }

    public int getNumero() {
        return numero.get();
    }

    public IntegerProperty numeroProperty() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero.set(numero);
    }

    public LocalDate getFecha() {
		return fecha.get();
	}

	public ObjectProperty<LocalDate> fechaProperty() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha.set(fecha);
	}

	public LocalTime getHora() {
		return hora.get();
	}

	public ObjectProperty<LocalTime> horaProperty() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora.set(hora);
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return estado.get();
	}
	
	public StringProperty estadoProperty() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado.set(estado);
	}
	
	@Override
	public String toString() {
		return "Turno{" +
				"id_turno=" + id_turno +
				", numero=" + numero +
				", fecha=" + fecha +
				", hora=" + hora +
				", paciente=" + paciente +
				'}';
	}
}
