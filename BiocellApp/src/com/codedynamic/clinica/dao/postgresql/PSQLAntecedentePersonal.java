package com.codedynamic.clinica.dao.postgresql;

import com.codedynamic.clinica.excepciones.ExcepcionGeneral;
import com.codedynamic.clinica.modelo.AntecedentePersonal;
import com.codedynamic.clinica.modelo.Enfermedad;
import com.codedynamic.clinica.modelo.Paciente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PSQLAntecedentePersonal {
    private final String INSERTAR = "INSERT INTO antecedentes_personales(id_enfermedad, id_paciente) values(?, ?)";
    private final String MODIFICAR = "UPDATE antecedentes_personales SET id_enfermedad = ?, id_paciente = ?";
    private final String ELIMINAR = "DELETE FROM antecedentes_personales WHERE id_enfermedad = ? AND id_paciente = ?";
    private final String OBTENERPORIDPACIENTE = "SELECT id_enfermedad, id_paciente FROM antecedentes_personales WHERE id_paciente = ?";
    private final String OBTENERPORIDS = "SELECT id_enfermedad, id_paciente FROM antecedentes_personales WHERE id_enfermedad = ? AND id_paciente = ?;";

    private Connection conexion;
    private PreparedStatement sentencia;
    private ResultSet resultado;
    private List<Enfermedad> enfermedadList = new ArrayList<>();

    public void insertar(short codigo, Paciente p) throws ExcepcionGeneral {
        try {
            conexion = new PSQLConexion().conectar();
            sentencia = conexion.prepareStatement(INSERTAR);
            sentencia.setShort(1, codigo);
            sentencia.setShort(2, p.getId_paciente());
            if (sentencia.executeUpdate() == 0) {
                throw new ExcepcionGeneral("No se inserto ningun registro");
            }
        } catch (SQLException e1) {
            e1.getMessage();
        } finally {
            cerrarConexiones();
        }
    }

    public void eliminar(Short codigo, Paciente paciente) throws ExcepcionGeneral {
        try {
            conexion = new PSQLConexion().conectar();
            sentencia = conexion.prepareStatement(ELIMINAR);
            sentencia.setShort(1, codigo);
            sentencia.setShort(2, paciente.getId_paciente());
            if (sentencia.executeUpdate() == 0) {
                throw new ExcepcionGeneral("No se elimino ningun registro");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexiones();
        }
    }

    public AntecedentePersonal obtenerPorIDPaciente(Paciente paciente) {
        AntecedentePersonal antecedentePersonal = null;
        try {
            conexion = new PSQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENERPORIDPACIENTE);
            sentencia.setShort(1, paciente.getId_paciente());
            resultado = sentencia.executeQuery();
            while (resultado.next()) {
                antecedentePersonal = convertir(resultado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }
        return antecedentePersonal;
    }

    public AntecedentePersonal obtenerPorIDS(Short codigo, Paciente paciente) {
        AntecedentePersonal antecedentePersonal = null;
        PSQLEnfermedades psqlEnfermedades = new PSQLEnfermedades();
        try {
            conexion = new PSQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENERPORIDS);
            sentencia.setShort(1, codigo);
            sentencia.setShort(2, paciente.getId_paciente());
            resultado = sentencia.executeQuery();
            while (resultado.next()) {
                antecedentePersonal = new AntecedentePersonal();
                antecedentePersonal.setPaciente(paciente);
                antecedentePersonal.setEnfermedad(psqlEnfermedades.obtenerPorID(resultado.getShort("id_enfermedad")));
                }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }
        return antecedentePersonal;
    }

    private List<Enfermedad> cargarEnfermedadPaciente(ResultSet resultSet) throws SQLException {
        PSQLEnfermedades enfermedades = new PSQLEnfermedades();
        enfermedadList.add(enfermedades.obtenerPorID(resultSet.getShort("id_enfermedad")));
        return enfermedadList;
    }

    private AntecedentePersonal convertir(ResultSet resultado) throws SQLException {
        PSQLPaciente psqlPaciente = new PSQLPaciente();
        AntecedentePersonal antecedentePersonal = new AntecedentePersonal();
        antecedentePersonal.setListaEnfermedades(cargarEnfermedadPaciente(resultado));
        antecedentePersonal.setPaciente(psqlPaciente.obtenerPorID(resultado.getShort("id_paciente")));
        return antecedentePersonal;
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

