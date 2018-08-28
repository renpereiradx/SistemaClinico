package com.codedynamic.clinica.modelo;

public class AntecedenteFamiliar {
	private String padre;
    private String madre;
    private String hijos;
    private String hermanos;
    private String abuelosPaternos;
    private String abuelosMaternos;
    private String vacunacion;
    private Paciente paciente;

    public AntecedenteFamiliar() {
        this(null, null, null, null, null, null, null, null);
    }

    public AntecedenteFamiliar(String padre, String madre, String hijos, String hermanos, String abuelosPaternos, String abuelosMaternos, String vacunacion, Paciente paciente) {
        this.padre = padre;
        this.madre = madre;
        this.hijos = hijos;
        this.hermanos = hermanos;
        this.abuelosPaternos = abuelosPaternos;
        this.abuelosMaternos = abuelosMaternos;
        this.vacunacion = vacunacion;
        this.paciente = paciente;
    }

    public String getPadre() {
        return padre;
    }

    public void setPadre(String padre) {
        this.padre = padre;
    }

    public String getMadre() {
        return madre;
    }

    public void setMadre(String madre) {
        this.madre = madre;
    }

    public String getHijos() {
        return hijos;
    }

    public void setHijos(String hijos) {
        this.hijos = hijos;
    }

    public String getHermanos() {
        return hermanos;
    }

    public void setHermanos(String hermanos) {
        this.hermanos = hermanos;
    }

    public String getAbuelosPaternos() {
        return abuelosPaternos;
    }

    public void setAbuelosPaternos(String abuelosPaternos) {
        this.abuelosPaternos = abuelosPaternos;
    }

    public String getAbuelosMaternos() {
        return abuelosMaternos;
    }

    public void setAbuelosMaternos(String abuelosMaternos) {
        this.abuelosMaternos = abuelosMaternos;
    }

    public String getVacunacion() {
        return vacunacion;
    }

    public void setVacunacion(String vacunacion) {
        this.vacunacion = vacunacion;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
}
