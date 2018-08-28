package com.codedynamic.clinica.modelo;

public class Enfermedad {
    private short codigo;
    private String enfermedad;

    public Enfermedad() {
        this((short) 0, null);
    }

    public Enfermedad(short codigo, String enfermedad) {
        this.codigo = codigo;
        this.enfermedad = enfermedad;
    }

    public short getCodigo() {
        return codigo;
    }

    public void setCodigo(short codigo) {
        this.codigo = codigo;
    }

    public String getEnfermedad() {
        return enfermedad;
    }

    public void setEnfermedad(String enfermedad) {
        this.enfermedad = enfermedad;
    }

    @Override
    public String toString() {
        return "'" + enfermedad + '\'';
    }
}
