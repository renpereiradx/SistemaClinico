package com.codedynamic.clinica.modelo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Usuario {
	private short id;
    private StringProperty nombre;
    private StringProperty usuario;
    private StringProperty clave;
    private IntegerProperty codigo_perfil;

    public Usuario() {
        this((short) 0, null, null, null, 0);
    }

    public Usuario(short id, String nombre, String usuario, String clave, int codigo_perfil) {
        this.id = id;
        this.nombre = new SimpleStringProperty(nombre);
        this.usuario = new SimpleStringProperty(usuario);
        this.clave = new SimpleStringProperty(clave);
        this.codigo_perfil = new SimpleIntegerProperty(codigo_perfil);
    }

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre.get();
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public String getUsuario() {
        return usuario.get();
    }

    public StringProperty usuarioProperty() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario.set(usuario);
    }

    public String getClave() {
        return clave.get();
    }

    public StringProperty claveProperty() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave.set(clave);
    }

    public int getCodigo_perfil() {
        return codigo_perfil.get();
    }

    public IntegerProperty codigo_perfilProperty() {
        return codigo_perfil;
    }

    public void setCodigo_perfil(int codigo_perfil) {
        this.codigo_perfil.set(codigo_perfil);
    }


}
