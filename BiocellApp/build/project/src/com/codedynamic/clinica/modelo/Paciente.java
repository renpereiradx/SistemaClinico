package com.codedynamic.clinica.modelo;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Paciente {
	private short id_paciente;
    private StringProperty nombres;
    private StringProperty apellidos;
    private StringProperty cedula;
    private StringProperty nroTelefono;
    private StringProperty correo;
    private ObjectProperty<LocalDate> fecha_nacimiento;
    private StringProperty departamento;
    private StringProperty ciudad;
    private StringProperty barrio;
    private StringProperty profesion;
    private StringProperty sexo;
    private IntegerProperty hijos;
    private StringProperty nombrePadre;
    private StringProperty nombreMadre;
    private StringProperty gradoInstruccion;
    private StringProperty estadoCivil;
    private StringProperty personaResponsable;
    private StringProperty etnia;
    private short id_usuario;

    public Paciente(short id_paciente, String nombre, String apellidos) {
    	this.id_paciente = id_paciente;
    	this.nombres = new SimpleStringProperty(nombre);
    	this.apellidos = new SimpleStringProperty(apellidos);
    }
    
    public Paciente() {
        this((short)0, null, null, null, null, null, null, null, null, null,
                null, null, 0, (short)0, null, null, null, null, null, null);
    }

    public Paciente(short id_paciente, String nombres, String apellidos, String cedula, String nroTelefono, String correo, LocalDate fecha_nacimiento, 
    		String departamento, String ciudad, String barrio, String profesion, String sexo, int hijos, short id_usuario, String nombrePadre, 
    		String nombreApellido, String gradoInstruccion, String estadoCivil,String personaResponsable, String etnia) {
        this.id_paciente = id_paciente;
        this.nombres = new SimpleStringProperty(nombres);
        this.apellidos = new SimpleStringProperty(apellidos);
        this.cedula = new SimpleStringProperty(cedula);
        this.nroTelefono = new SimpleStringProperty(nroTelefono);
        this.correo = new SimpleStringProperty(correo);
        this.fecha_nacimiento = new SimpleObjectProperty<LocalDate>(fecha_nacimiento);
        this.departamento = new SimpleStringProperty(departamento);
        this.ciudad = new SimpleStringProperty(ciudad);
        this.barrio = new SimpleStringProperty(barrio);
        this.profesion = new SimpleStringProperty(profesion);
        this.sexo = new SimpleStringProperty(sexo);
        this.hijos = new SimpleIntegerProperty(hijos);
        this.id_usuario = id_usuario;
        this.nombrePadre = new SimpleStringProperty(nombrePadre);
        this.nombreMadre = new SimpleStringProperty(nombreApellido);
        this.gradoInstruccion = new SimpleStringProperty(gradoInstruccion);
        this.estadoCivil = new SimpleStringProperty(estadoCivil);
        this.personaResponsable = new SimpleStringProperty(personaResponsable);
        this.etnia = new SimpleStringProperty(etnia);
    }

    public String getNombrePadre() {
        return nombrePadre.get();
    }

    public StringProperty nombrePadreProperty() {
        return nombrePadre;
    }

    public void setNombrePadre(String nombrePadre) {
        this.nombrePadre.set(nombrePadre);
    }

    public String getNombreMadre() {
        return nombreMadre.get();
    }

    public StringProperty nombreMadreProperty() {
        return nombreMadre;
    }

    public void setNombreMadre(String nombreMadre) {
        this.nombreMadre.set(nombreMadre);
    }

    public String getGradoInstruccion() {
        return gradoInstruccion.get();
    }

    public StringProperty gradoInstruccionProperty() {
        return gradoInstruccion;
    }

    public void setGradoInstruccion(String gradoInstruccion) {
        this.gradoInstruccion.set(gradoInstruccion);
    }

    public String getEstadoCivil() {
        return estadoCivil.get();
    }

    public StringProperty estadoCivilProperty() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil.set(estadoCivil);
    }

    public String getPersonaResponsable() {
        return personaResponsable.get();
    }

    public StringProperty personaResponsableProperty() {
        return personaResponsable;
    }

    public void setPersonaResponsable(String personaResponsable) {
        this.personaResponsable.set(personaResponsable);
    }

    public String getEtnia() {
        return etnia.get();
    }

    public StringProperty etniaProperty() {
        return etnia;
    }

    public void setEtnia(String etnia) {
        this.etnia.set(etnia);
    }

    public short getId_paciente() {
        return id_paciente;
    }

    public void setId_paciente(short id_paciente) {
        this.id_paciente = id_paciente;
    }

    public String getNombres() {
        return nombres.get();
    }

    public StringProperty nombresProperty() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres.set(nombres);
    }

    public String getApellidos() {
        return apellidos.get();
    }

    public StringProperty apellidosProperty() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos.set(apellidos);
    }

    public String getCedula() {
        return cedula.get();
    }

    public StringProperty cedulaProperty() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula.set(cedula);
    }

    public String getCorreo() {
        return correo.get();
    }

    public StringProperty correoProperty() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo.set(correo);
    }

    public LocalDate getFecha_nacimiento() {
        return fecha_nacimiento.get();
    }

    public ObjectProperty<LocalDate> fecha_nacimientoProperty() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
        this.fecha_nacimiento.set(fecha_nacimiento);
    }

    public String getDepartamento() {
        return departamento.get();
    }

    public StringProperty departamentoProperty() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento.set(departamento);
    }

    public String getCiudad() {
        return ciudad.get();
    }

    public StringProperty ciudadProperty() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad.set(ciudad);
    }

    public String getBarrio() {
        return barrio.get();
    }

    public StringProperty barrioProperty() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio.set(barrio);
    }

    public String getProfesion() {
        return profesion.get();
    }

    public StringProperty profesionProperty() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion.set(profesion);
    }

    public String getSexo() {
        return sexo.get();
    }

    public StringProperty sexoProperty() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo.set(sexo);
    }

    public int getHijos() {
        return hijos.get();
    }

    public IntegerProperty hijosProperty() { return hijos; }

    public void setHijos(int hijos) {
        this.hijos.set(hijos);
    }

    public short getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(short id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNroTelefono() {
        return nroTelefono.get();
    }

    public StringProperty nroTelefonoProperty() {
        return nroTelefono;
    }

    public void setNroTelefono(String nroTelefono) {
        this.nroTelefono.set(nroTelefono);
    }

    @Override
    public String toString() {
        return "Codigo:  " + id_paciente +
                "| " + nombres.get() +
                " " + apellidos.get() +
                " | C.I. :" + cedula.get();
    }
}
