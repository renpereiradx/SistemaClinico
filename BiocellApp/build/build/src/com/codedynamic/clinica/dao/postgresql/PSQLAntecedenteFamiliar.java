package com.codedynamic.clinica.dao.postgresql;

import com.codedynamic.clinica.excepciones.ExcepcionGeneral;
import com.codedynamic.clinica.modelo.AntecedenteFamiliar;
import com.codedynamic.clinica.modelo.Paciente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PSQLAntecedenteFamiliar {
    private final String INSERTAR = "INSERT INTO antecedentes_familiares(padre, madre, hijos, hermanos, abuelos_paternos, abuelos_maternos, " +
            "vacunacion, id_paciente) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
    private final String MODIFICAR = "UPDATE antecedentes_familiares SET padre = ?, madre = ?, hijos = ?, hermanos = ?, abuelos_paternos = ?, " +
            "abuelos_maternos = ?, vacunacion = ? WHERE id_paciente = ?";
    private final String ELIMINAR = "DELETE FROM antecedentes_familiares WHERE id_paciente = ?";
    private final String OBTENERPORID = "SELECT padre, madre, hijos, hermanos, abuelos_paternos, abuelos_maternos, vacunacion, id_paciente " +
            "FROM antecedentes_familiares WHERE id_paciente = ?";

    private Connection conexion;
    private PreparedStatement sentencia;
    private ResultSet resultado;

    public void insertar(AntecedenteFamiliar a) throws ExcepcionGeneral {
        try {
            conexion = new PSQLConexion().conectar();
            sentencia = conexion.prepareStatement(INSERTAR);
            sentencia.setString(1, a.getPadre());
            sentencia.setString(2, a.getMadre());
            sentencia.setString(3, a.getHijos());
            sentencia.setString(4, a.getHermanos());
            sentencia.setString(5, a.getAbuelosPaternos());
            sentencia.setString(6, a.getAbuelosMaternos());
            sentencia.setString(7, a.getVacunacion());
            sentencia.setShort(8, a.getPaciente().getId_paciente());
            if (sentencia.executeUpdate() == 0) {
                throw new ExcepcionGeneral("No se inserto ningun registro");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexiones();
        }
    }

    public void modificar(AntecedenteFamiliar a) throws ExcepcionGeneral {
        try {
            conexion = new PSQLConexion().conectar();
            sentencia = conexion.prepareStatement(MODIFICAR);
            sentencia.setString(1, a.getPadre());
            sentencia.setString(2, a.getMadre());
            sentencia.setString(3, a.getHijos());
            sentencia.setString(4, a.getHermanos());
            sentencia.setString(5, a.getAbuelosPaternos());
            sentencia.setString(6, a.getAbuelosMaternos());
            sentencia.setString(7, a.getVacunacion());
            sentencia.setShort(8, a.getPaciente().getId_paciente());
            if (sentencia.executeUpdate() == 0) {
                throw new ExcepcionGeneral("No se modifico ningun registro");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexiones();
        }
    }

    public void eliminar(AntecedenteFamiliar a) throws ExcepcionGeneral {
        try {
            conexion = new PSQLConexion().conectar();
            sentencia = conexion.prepareStatement(ELIMINAR);
            sentencia.setShort(1, a.getPaciente().getId_paciente());
            if (sentencia.executeUpdate() == 0) {
                throw new ExcepcionGeneral("No se elimino ningun registro");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexiones();
        }
    }

    public AntecedenteFamiliar obtenerPorID(Paciente paciente) throws ExcepcionGeneral {
        AntecedenteFamiliar antecedenteFamiliar = null;
        try {
            conexion = new PSQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENERPORID);
            sentencia.setShort(1, paciente.getId_paciente());
            resultado = sentencia.executeQuery();
            if (resultado.next()) {
                antecedenteFamiliar = convertir(resultado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexiones();
        }
        return antecedenteFamiliar;
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

    private AntecedenteFamiliar convertir(ResultSet resultSet) throws SQLException {
        AntecedenteFamiliar antecedenteFamiliar = new AntecedenteFamiliar();
        PSQLPaciente psqlPaciente = new PSQLPaciente();
        antecedenteFamiliar.setPadre(resultSet.getString("padre"));
        antecedenteFamiliar.setMadre(resultSet.getString("madre"));
        antecedenteFamiliar.setHijos(resultSet.getString("hijos"));
        antecedenteFamiliar.setHermanos(resultSet.getString("hermanos"));
        antecedenteFamiliar.setAbuelosPaternos(resultSet.getString("abuelos_paternos"));
        antecedenteFamiliar.setAbuelosMaternos(resultSet.getString("abuelos_maternos"));
        antecedenteFamiliar.setVacunacion(resultSet.getString("vacunacion"));
        antecedenteFamiliar.setPaciente(psqlPaciente.obtenerPorID(resultSet.getShort("id_paciente")));
        return antecedenteFamiliar;
    }
}
