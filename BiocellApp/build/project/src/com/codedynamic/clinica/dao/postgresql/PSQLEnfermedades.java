package com.codedynamic.clinica.dao.postgresql;

import com.codedynamic.clinica.dao.interfaces.EnfermedadesDAO;
import com.codedynamic.clinica.excepciones.ExcepcionGeneral;
import com.codedynamic.clinica.modelo.Enfermedad;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PSQLEnfermedades implements EnfermedadesDAO {

    private final String INSERTAR = "INSERT INTO antecedentes_enfermedades(enfermedad) values(?) RETURNING codigo";
    private final String MODIFICAR = "UPDATE antecedentes_enfermedades SET enfermedad = ? WHERE codigo = ?";
    private final String ELIMINAR = "DELETE FROM antecedentes_enfermedades WHERE codigo = ?";
    private final String OBTENERPORID = "SELECT codigo, enfermedad FROM antecedentes_enfermedades WHERE codigo = ?";
    private final String LISTAR = "SELECT codigo, enfermedad FROM antecedentes_enfermedades";

    private Connection conexion;
    private PreparedStatement sentencia;
    private ResultSet resultado;

    @Override
    public void insertar(Enfermedad o) throws ExcepcionGeneral {
        try {
            conexion = new PSQLConexion().conectar();
            sentencia = conexion.prepareStatement(INSERTAR);
            sentencia.setString(1, o.getEnfermedad());
            resultado = sentencia.executeQuery();
            if (resultado.next()) {
                o.setCodigo(resultado.getShort(1));
            } else {
                throw new ExcepcionGeneral("No se inserto ningun registro");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexiones();
        }
    }

    @Override
    public void modificar(Enfermedad o) throws ExcepcionGeneral{
        try {
            conexion = new PSQLConexion().conectar();
            sentencia = conexion.prepareStatement(MODIFICAR);
            sentencia.setShort(1, o.getCodigo());
            if (sentencia.executeUpdate() == 0) {
                throw new ExcepcionGeneral("No se modifico ningun registro");
            }
        } catch (SQLException e) {
            throw new ExcepcionGeneral(e.getMessage());
        } finally {
            cerrarConexiones();
        }
    }

    @Override
    public void eliminar(Enfermedad o) {

    }

    @Override
    public Enfermedad obtenerPorID(Short aShort) throws ExcepcionGeneral {
        Enfermedad enfermedades = null;
        try {
            conexion = new PSQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENERPORID);
            sentencia.setShort(1, aShort);
            resultado = sentencia.executeQuery();
            if (resultado.next()) {
                enfermedades = convertir(resultado);
            }
        } catch (SQLException e) {
            throw new ExcepcionGeneral(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return enfermedades;
    }

    public List<Enfermedad> listarEnfermedades() throws ExcepcionGeneral {
        List<Enfermedad> lista = new ArrayList<>();
        try {
            conexion = new PSQLConexion().conectar();
            sentencia = conexion.prepareStatement(LISTAR);
            resultado = sentencia.executeQuery();
            while (resultado.next()) {
                Enfermedad enfermedad;
                enfermedad = convertir(resultado);
                lista.add(enfermedad);
            }
        } catch (SQLException e) {
            throw new ExcepcionGeneral(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return lista;
    }

    public Enfermedad convertir(ResultSet resultado) throws SQLException {
        Enfermedad enfermedades = new Enfermedad();
        enfermedades.setCodigo(resultado.getShort("codigo"));
        enfermedades.setEnfermedad(resultado.getString("enfermedad"));
        return enfermedades;
    }

    private void cerrarConexiones() {
        try {
            if (resultado != null) {
                resultado.close();
            }
            if (sentencia != null) {
                sentencia.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

