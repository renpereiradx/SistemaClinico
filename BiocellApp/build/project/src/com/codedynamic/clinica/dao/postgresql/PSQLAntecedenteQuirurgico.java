package com.codedynamic.clinica.dao.postgresql;

import com.codedynamic.clinica.excepciones.ExcepcionGeneral;
import com.codedynamic.clinica.modelo.AntecedenteQuirurgico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PSQLAntecedenteQuirurgico {
    private final String INSERTAR = "INSERT INTO antecedentes_quirurgicos(internaciones, dx, otros, id_paciente) VALUES(?, ?, ?, ?)";
    private final String ELIMINAR = "DELETE FROM antecedentes_quirurgicos WHERE id_paciente = ?";
    private final String MODIFICAR = "UPDATE antecedentes_quirurgicos SET internaciones = ?, dx = ?, otros = ? " +
            "WHERE id_paciente = ?";
    private final String OBTENERPORID = "SELECT internaciones, dx, otros, id_paciente FROM antecedentes_quirurgicos WHERE id_paciente = ?";

    private Connection conexion;
    private PreparedStatement sentencia;
    private ResultSet resultado;

    public void insertar(AntecedenteQuirurgico a) throws ExcepcionGeneral {
        try {
            conexion = new PSQLConexion().conectar();
            sentencia = conexion.prepareStatement(INSERTAR);
            sentencia.setString(1, a.getInternaciones());
            sentencia.setString(2, a.getDx());
            sentencia.setString(3, a.getOtros());
            sentencia.setShort(4, a.getPaciente().getId_paciente());
            if (sentencia.executeUpdate() == 0) {
                throw new ExcepcionGeneral("No se inserto ningun registro");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexiones();
        }
    }

    public void modificar(AntecedenteQuirurgico a) throws ExcepcionGeneral {
        try {
            conexion = new PSQLConexion().conectar();
            sentencia = conexion.prepareStatement(MODIFICAR);
            sentencia.setString(1, a.getInternaciones());
            sentencia.setString(2, a.getDx());
            sentencia.setString(3, a.getOtros());
            sentencia.setShort(4, a.getPaciente().getId_paciente());
            if (sentencia.executeUpdate() == 0) {
                throw new ExcepcionGeneral("No se modifico ningun registro");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexiones();
        }
    }

    public void eliminar(AntecedenteQuirurgico a) throws ExcepcionGeneral {
        try {
            conexion = new PSQLConexion().conectar();
            sentencia = conexion.prepareStatement(ELIMINAR);
            sentencia.setShort(1, a.getPaciente().getId_paciente());
            if (sentencia.executeUpdate() == 0) {
                throw new ExcepcionGeneral("No se elimino ningun Registro");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexiones();
        }
    }

    public AntecedenteQuirurgico obtenerPorID(short id) throws ExcepcionGeneral {
        AntecedenteQuirurgico antecedenteQuirurgico = null;
        try {
            conexion = new PSQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENERPORID);
            sentencia.setShort(1, id);
            resultado = sentencia.executeQuery();
            if (resultado.next()) {
                antecedenteQuirurgico = convertir(resultado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexiones();
        }
        return antecedenteQuirurgico;
    }

    private AntecedenteQuirurgico convertir(ResultSet resultSet) throws SQLException {
        PSQLPaciente psqlPaciente = new PSQLPaciente();
        AntecedenteQuirurgico antecedenteQuirurgico = new AntecedenteQuirurgico();
        antecedenteQuirurgico.setInternaciones(resultSet.getString("internaciones"));
        antecedenteQuirurgico.setDx(resultSet.getString("dx"));
        antecedenteQuirurgico.setOtros(resultSet.getString("otros"));
        antecedenteQuirurgico.setPaciente(psqlPaciente.obtenerPorID(resultSet.getShort("id_paciente")));
        return antecedenteQuirurgico;
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
        } catch (SQLException sqle) { }
    }
}

