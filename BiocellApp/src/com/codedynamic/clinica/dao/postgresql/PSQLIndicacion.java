package com.codedynamic.clinica.dao.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.codedynamic.clinica.dao.interfaces.IndicacionDAO;
import com.codedynamic.clinica.excepciones.ExcepcionGeneral;
import com.codedynamic.clinica.modelo.Indicacion;
import com.codedynamic.clinica.modelo.Paciente;

public class PSQLIndicacion implements IndicacionDAO {
	private final String INSERTAR = "INSERT INTO indicaciones(detalles, tratamiento, id_paciente) VALUES(?, ?, ?)";
	private final String MODIFICAR = "UPDATE indicaciones SET detalles = ?, tratamiento = ?, id_paciente = ? WHERE id_indicacion = ?";
	private final String ELIMINAR = "DELETE FROM indicaciones WHERE id_indicacion = ?";
	private final String OBTENERPORID = "SELECT id_indicacion, detalles, tratamiento, id_paciente FROM indicaciones WHERE id_indicacion = ?";
	
	private Connection conexion;
	private PreparedStatement sentencia;
	private ResultSet resultado;
	
	@Override
	public void insertar(Indicacion o) throws ExcepcionGeneral {
		try {
			conexion = new PSQLConexion().conectar();
			sentencia = conexion.prepareStatement(INSERTAR);
			sentencia.setString(1, o.getDetalle());
			sentencia.setString(2, o.getTratamiento());
			sentencia.setShort(3, o.getPaciente().getId_paciente());
			if (sentencia.executeUpdate() == 0) {
				throw new ExcepcionGeneral("No se inserto ningun registro");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrarConexiones();
		}
	}

	@Override
	public void modificar(Indicacion o) throws ExcepcionGeneral {
		try {
			conexion = new PSQLConexion().conectar();
			sentencia = conexion.prepareStatement(MODIFICAR);
			sentencia = conexion.prepareStatement(INSERTAR);
			sentencia.setString(1, o.getDetalle());
			sentencia.setString(2, o.getTratamiento());
			sentencia.setShort(3, o.getPaciente().getId_paciente());
			sentencia.setShort(4, o.getIdIndicacion());
			if (sentencia.executeUpdate() == 0) {
				throw new ExcepcionGeneral("No se modifico ningun registro");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrarConexiones();
		}
	}

	@Override
	public void eliminar(Indicacion o) throws ExcepcionGeneral {
		try {
			conexion = new PSQLConexion().conectar();
			sentencia = conexion.prepareStatement(ELIMINAR);
			sentencia.setShort(1, o.getIdIndicacion());
			if (sentencia.executeUpdate() == 0) {
				throw new ExcepcionGeneral("No se elimino ningun registro");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrarConexiones();
		}
	}

	@Override
	public Indicacion obtenerPorID(Short k) throws ExcepcionGeneral {
		Indicacion indicacion = null;
		try {
			conexion = new PSQLConexion().conectar();
			sentencia = conexion.prepareStatement(OBTENERPORID);
			sentencia.setShort(1, k);
			resultado = sentencia.executeQuery();
			if (resultado.next()) {
				indicacion = convertir(resultado);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return indicacion;
	}
	
	private Indicacion convertir(ResultSet rs) throws SQLException {
		Indicacion indicacion = new Indicacion();
		PSQLPaciente paciente = new PSQLPaciente();
		indicacion.setIdIndicacion(rs.getShort("id_indicacion"));
		indicacion.setDetalle(rs.getString("detalles"));
		indicacion.setTratamiento(rs.getString("tratamiento"));
		indicacion.setPaciente(paciente.obtenerPorID(rs.getShort("id_paciente")));
		return indicacion;
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
