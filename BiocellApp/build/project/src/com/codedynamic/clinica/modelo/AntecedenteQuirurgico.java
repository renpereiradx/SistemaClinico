package com.codedynamic.clinica.modelo;

public class AntecedenteQuirurgico {
    private String internaciones;
    private String dx;
    private String otros;
    private Paciente paciente;

    public AntecedenteQuirurgico() {
        this(null, null, null, null);
    }

    public AntecedenteQuirurgico(String internaciones, String dx, String otros, Paciente paciente) {
        this.internaciones = internaciones;
        this.dx = dx;
        this.otros = otros;
        this.paciente = paciente;
    }

    public String getInternaciones() {
        return internaciones;
    }

    public void setInternaciones(String internaciones) {
        this.internaciones = internaciones;
    }

    public String getDx() {
        return dx;
    }

    public void setDx(String dx) {
        this.dx = dx;
    }

    public String getOtros() {
        return otros;
    }

    public void setOtros(String otros) {
        this.otros = otros;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
}
