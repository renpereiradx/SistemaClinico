package com.codedynamic.clinica.dao.postgresql;

import com.codedynamic.clinica.dao.interfaces.UsuarioDAO;
import com.codedynamic.clinica.modelo.Usuario;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class PSQLUsuario implements UsuarioDAO {
    private static final String INSERTAR = "INSERT INTO usuarios(nombre, usuario, clave, id_perfil) VALUES(?, ?, md5(?), ? RETURNING idUsuario";
    private static final String MODIFICAR = "UPDATE usuarios SET nombre = ?, usuario = ?, clave = md5(?), codigo_perfil = ?"
            + " WHERE idUsuario = ?";
    private static final String ELIMINAR = "DELETE FROM usuarios WHERE idUsuario = ?";
    private static final String OBTENERPORID = "SELECT  id_usuario, nombre, usuario, clave, id_perfil FROM usuarios WHERE idUsuario = ?";
    private static final String OBTENER = "SELECT id_usuario, nombre, usuario, clave, id_perfil FROM usuarios";
    private static final String OBTENERUSUARIO = "SELECT id_usuario, nombre, usuario, clave, id_perfil FROM usuarios "
            + "WHERE usuario = ?";

    private Connection conexion;
    private PreparedStatement sentencia;
    private ResultSet resultado;

    @Override
    public void insertar(Usuario o) {

    }

    @Override
    public void modificar(Usuario o) {

    }

    @Override
    public void eliminar(Usuario o) {

    }

    @Override
    public Usuario obtenerPorID(Short integer) {
        return null;
    }

    @Override
    public List<Usuario> listar() {
        return null;
    }

}

