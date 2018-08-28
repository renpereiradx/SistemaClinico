package com.codedynamic.clinica.dao.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.codedynamic.clinica.excepciones.ExcepcionGeneral;
import com.codedynamic.clinica.modelo.AntecedenteMasculino;
import com.codedynamic.clinica.modelo.Paciente;

public class PSQLAntecedenteMasculino {
	private final String INSERTAR = "INSERT INTO antecedentes_masculinos(inicio_sexual, nro_pareja, cirugia_genital, est, rastreo_postrata, id_paciente) "
			+ "VALUES(?, ?, ?, ?, ?, ?)";
	private final String MODIFICAR = "UPDATE antecedentes_masculinos SET inicio_sexual = ?, nro_pareja = ?, cirugia_genital = ?, est = ?, "
			+ "rastreo_postrata = ? WHERE id_paciente = ?;";
	private final String ELIMINAR = "DELETE FROM antecedentes_masculinos WHERE id_paciente = ?";
	private final String OBTENERPORID = "SELECT inicio_sexual, nro_pareja, cirugia_genital, est, rastreo_postrata, id_paciente FROM antecedentes_masculinos "
			+ "WHERE id_paciente = ?";
	
	private Connection conexion;
	private PreparedStatement sentencia;
	private ResultSet resultado;
	
	public void insertar(AntecedenteMasculino a) throws ExcepcionGeneral {
		try {
			conexion = new PSQLConexion().conectar();
			sentencia = conexion.prepareStatement(INSERTAR);
			sentencia.setString(1, a.getInicioSexual());
			sentencia.setShort(2, a.getNroPareja());
			sentencia.setString(3, a.getCirugiaGenital());
			sentencia.setString(4, a.getEst());
			sentencia.setString(5, a.getRastreoPostrata());
			sentencia.setShort(6, a.getPaciente().getId_paciente());
			if (sentencia.executeUpdate() == 0) {
				throw new ExcepcionGeneral("No se inserto ningun registro");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrarConexiones();
		}
	}
	
	public void modificar(AntecedenteMasculino a) throws ExcepcionGeneral {
		try {
			conexion = new PSQLConexion().conectar();
			sentencia = conexion.prepareStatement(MODIFICAR);
			sentencia.setString(1, a.getInicioSexual());
			sentencia.setShort(2, a.getNroPareja());
			sentencia.setString(3, a.getCirugiaGenital());
			sentencia.setString(4, a.getEst());
			sentencia.setString(5, a.getRastreoPostrata());
			sentencia.setShort(6, a.getPaciente().getId_paciente());
			if (sentencia.executeUpdate() == 0) {
				throw new ExcepcionGeneral();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrarConexiones();
		}
	}
	
	private void eliminar(AntecedenteMasculino a) throws ExcepcionGeneral {
		try {
			conexion = new PSQLConexion().conectar();
			sentencia = conexion.prepareStatement(ELIMINAR);
			sentencia.setShort(1, a.getPaciente().getId_paciente());
			if (sentencia.executeUpdate() == 0) {
				throw new ExcepcionGeneral("No se borro ningun registro");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrarConexiones();
		}
	}
	
	public AntecedenteMasculino obtenerPorID(Paciente p) throws ExcepcionGeneral {
		AntecedenteMasculino antecedenteMasculino = null;
		try {
			conexion = new PSQLConexion().conectar();
			sentencia = conexion.prepareStatement(OBTENERPORID);
			sentencia.setShort(1, p.getId_paciente());
			resultado = sentencia.executeQuery();
			if (resultado.next()) {
				antecedenteMasculino = convertir(resultado);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return antecedenteMasculino;
	}
	
	private AntecedenteMasculino convertir(ResultSet resultSet) throws SQLException {
		AntecedenteMasculino antecedenteMasculino = new AntecedenteMasculino();
		PSQLPaciente psqlPaciente = new PSQLPaciente();
		antecedenteMasculino.setInicioSexual(resultSet.getString("inicio_sexual"));
		antecedenteMasculino.setNroPareja(resultSet.getShort("nro_pareja"));
		antecedenteMasculino.setCirugiaGenital(resultSet.getString("cirugia_genital"));
		antecedenteMasculino.setEst(resultSet.getString("est"));
		antecedenteMasculino.setRastreoPostrata(resultSet.getString("rastreo_postrata"));
		antecedenteMasculino.setPaciente(psqlPaciente.obtenerPorID(resultSet.getShort("id_paciente")));
		return antecedenteMasculino;
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
