package com.codedynamic.clinica.dao.postgresql;

import com.codedynamic.clinica.excepciones.ExcepcionGeneral;
import com.codedynamic.clinica.modelo.AntecedenteFemenino;
import com.codedynamic.clinica.modelo.Paciente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PSQLAntecedenteFemenino {
    private final String INSERTAR = "INSERT INTO antecedentes_femeninos(edad_menarca, edad_climaterio, menopausia, fum, terapia_anticonceptiva, " +
            "inicio_sexual, nro_pareja, cirugia_pelvania, pap, fecha, mamografia_ecografia, est, id_paciente) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String MODIFICAR = "UPDATE antecedentes_femeninos SET edad_menarca = ?, edad_climaterio = ?, menopausia = ?, fum = ?, " +
            "terapia_anticonceptiva = ?, inicio_sexual = ?, nro_pareja = ?, cirugia_pelvania = ?, pap = ?, fecha = ?, mamografia_ecografia = ?, " +
            "est = ? WHERE id_paciente  = ?";
    private final String ELIMINAR = "DELETE FROM antecedentes_femeninos WHERE id_paciente = ?";
    private final String OBTENERPORID = "SELECT edad_menarca, edad_climaterio, menopausia, fum, terapia_anticonceptiva, inicio_sexual, nro_pareja, " +
            "cirugia_pelvania, pap, fecha, mamografia_ecografia, est, id_paciente FROM antecedentes_femeninos WHERE id_paciente = ?";

    private Connection conexion;
    private PreparedStatement sentencia;
    private ResultSet resultado;

    public void insertar(AntecedenteFemenino a) throws ExcepcionGeneral {
        try {
            conexion = new PSQLConexion().conectar();
            sentencia = conexion.prepareStatement(INSERTAR);
            sentencia.setString(1, a.getEdadMenarca());
            sentencia.setString(2, a.getEdadClimaterio());
            sentencia.setString(3, a.getMenopausia());
            sentencia.setString(4, a.getFum());
            sentencia.setString(5, a.getTerapiaAnticonceptivo());
            sentencia.setString(6, a.getInicioSexual());
            sentencia.setString(7, a.getNroPareja());
            sentencia.setString(8, a.getCirugiaPelvania());
            sentencia.setString(9, a.getPap());
            sentencia.setString(10, a.getFecha());
            sentencia.setString(11, a.getMamografia());
            sentencia.setString(12, a.getEst());
            sentencia.setShort(13, a.getPaciente().getId_paciente());
            if (sentencia.executeUpdate() == 0) {
                throw new ExcepcionGeneral("No se inserto ningun registro");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexiones();
        }
    }

    public void modificar(AntecedenteFemenino a) throws ExcepcionGeneral {
        try {
            conexion = new PSQLConexion().conectar();
            sentencia = conexion.prepareStatement(MODIFICAR);
            sentencia.setString(1, a.getEdadMenarca());
            sentencia.setString(2, a.getEdadClimaterio());
            sentencia.setString(3, a.getMenopausia());
            sentencia.setString(4, a.getFum());
            sentencia.setString(5, a.getTerapiaAnticonceptivo());
            sentencia.setString(6, a.getInicioSexual());
            sentencia.setString(7, a.getNroPareja());
            sentencia.setString(8, a.getCirugiaPelvania());
            sentencia.setString(9, a.getPap());
            sentencia.setString(10, a.getFecha());
            sentencia.setString(11, a.getMamografia());
            sentencia.setString(12, a.getEst());
            sentencia.setShort(13, a.getPaciente().getId_paciente());
            if (sentencia.executeUpdate() == 0) {
                throw new ExcepcionGeneral("No registro ningun cambio");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexiones();
        }
    }

    public void eliminar(AntecedenteFemenino a) throws ExcepcionGeneral {
        try {
            conexion = new PSQLConexion().conectar();
            sentencia = conexion.prepareStatement(ELIMINAR);
            sentencia.setShort(1, a.getPaciente().getId_paciente());
            if (sentencia.executeUpdate() == 0) {
                throw new ExcepcionGeneral("No se pudo eliminar ningun registro");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexiones();
        }
    }

    public AntecedenteFemenino obtenerPorID(Paciente p) throws ExcepcionGeneral {
        AntecedenteFemenino antecedenteFemenino = null;
        try {
            conexion = new PSQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENERPORID);
            sentencia.setShort(1, p.getId_paciente());
            resultado = sentencia.executeQuery();
            if (resultado.next()) {
                antecedenteFemenino = convertir(resultado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexiones();
        }
        return antecedenteFemenino;
    }

    private AntecedenteFemenino convertir(ResultSet resultSet) throws SQLException {
        PSQLPaciente psqlPaciente = new PSQLPaciente();
        AntecedenteFemenino antecedenteFemenino = new AntecedenteFemenino();
        antecedenteFemenino.setEdadMenarca(resultSet.getString("edad_menarca"));
        antecedenteFemenino.setEdadClimaterio(resultSet.getString("edad_climaterio"));
        antecedenteFemenino.setMenopausia(resultSet.getString("menopausia"));
        antecedenteFemenino.setFum(resultSet.getString("fum"));
        antecedenteFemenino.setTerapiaAnticonceptivo(resultSet.getString("terapia_anticonceptiva"));
        antecedenteFemenino.setInicioSexual(resultSet.getString("inicio_sexual"));
        antecedenteFemenino.setNroPareja(resultSet.getString("nro_pareja"));
        antecedenteFemenino.setCirugiaPelvania(resultSet.getString("cirugia_pelvania"));
        antecedenteFemenino.setPap(resultSet.getString("pap"));
        antecedenteFemenino.setFecha(resultSet.getString("fecha"));
        antecedenteFemenino.setMamografia(resultSet.getString("mamografia_ecografia"));
        antecedenteFemenino.setEst(resultSet.getString("est"));
        antecedenteFemenino.setPaciente(psqlPaciente.obtenerPorID(resultSet.getShort("id_paciente")));
        return antecedenteFemenino;
    }

    private void cerrarConexiones(){
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

