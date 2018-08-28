package com.codedynamic.clinica.dao.postgresql;

import com.codedynamic.clinica.excepciones.ExcepcionGeneral;
import com.codedynamic.clinica.modelo.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Autenticacion {
    private static final String OBTENERUSUARIO = "SELECT id_usuario, nombre, usuario, clave, codigo_perfil "
            + "FROM usuarios WHERE usuario = ? AND clave = md5(?)";
    Usuario usuario;
    Connection conexion;
    PreparedStatement sentencia;
    ResultSet resultado;

    public Usuario confirmarUsuario(String user, String clave) throws ExcepcionGeneral {
        usuario = null;
        try {
            conexion = new PSQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENERUSUARIO);
            sentencia.setString(1, user);
            sentencia.setString(2, clave);
            resultado = sentencia.executeQuery();

            if (resultado.next()) {
                usuario = convertir(resultado);
            }
            return usuario;
        } catch (SQLException e) {
            throw new ExcepcionGeneral(e.getMessage());
        } finally {
            try {
                if (conexion != null) {
                    conexion.close();
                }
                if (sentencia != null) {
                    sentencia.close();
                }
                if (resultado != null) {
                    resultado.close();
                }
            } catch (SQLException e) { e.printStackTrace();}
        }
    }

    public Usuario convertir(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setId(rs.getShort("id_usuario"));
        usuario.setNombre(rs.getString("nombre"));
        usuario.setUsuario(rs.getString("usuario"));
        usuario.setClave(rs.getString("clave"));
        usuario.setCodigo_perfil(rs.getShort("codigo_perfil"));
        return usuario;
    }
}