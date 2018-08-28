package com.codedynamic.clinica.modelo;

import java.util.ArrayList;
import java.util.List;

public class AntecedentePersonal {
    private Paciente paciente;
    private Enfermedad enfermedad;
    private List<Enfermedad> listaEnfermedades = new ArrayList<>();

    public AntecedentePersonal() {
        this(null, null, null);
    }

    public AntecedentePersonal(Paciente paciente, List<Enfermedad> listaEnfermedades, Enfermedad enfermedad) {
        this.paciente = paciente;
        this.enfermedad = enfermedad;
        this.listaEnfermedades = listaEnfermedades;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public List<Enfermedad> getListaEnfermedades() {
        return listaEnfermedades;
    }

    public Enfermedad getEnfermedad() {
        return enfermedad;
    }

    public void setEnfermedad(Enfermedad enfermedad) {
        this.enfermedad = enfermedad;
    }

    public void setListaEnfermedades(List<Enfermedad> listaEnfermedades) {
        this.listaEnfermedades = listaEnfermedades;
    }
}
