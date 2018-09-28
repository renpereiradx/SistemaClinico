package com.codedynamic.clinica.dao.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.codedynamic.clinica.dao.interfaces.DiagnosticoMedicoDAO;
import com.codedynamic.clinica.excepciones.ExcepcionGeneral;
import com.codedynamic.clinica.modelo.DiagnosticoMedico;

public class PSQLDiagnosticoMedico implements DiagnosticoMedicoDAO {
	private final String INSERTAR = "INSERT INTO diagnostico_medico(detalle, id_paciente, conducta) VALUES(?, ?, ?)";
	private final String MODIFICAR = "UPDATE diagnostico_medico SET detalle = ?, id_paciente = ?, conducta = ? WHERE id_dm = ?";
	private final String ELIMINAR = "DELETE FROM diagnostico_medico WHERE id_dm = ?";
	private final String OBTENERPORID = "SELECT id_dm, detalle, id_paciente, conducta FROM diagnostico_medico WHERE id_dm = ?";
	
	private Connection conexion;
	private PreparedStatement sentencia;
	private ResultSet resultado;
	
	@Override
	public void insertar(DiagnosticoMedico o) throws ExcepcionGeneral {
		try {
			conexion = new PSQLConexion().conectar();
			sentencia = conexion.prepareStatement(INSERTAR);
			sentencia.setString(1, o.getDetalle());
			sentencia.setShort(2, o.getPaciente().getId_paciente());
			sentencia.setString(3, o.getConducta());
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
	public void modificar(DiagnosticoMedico o) throws ExcepcionGeneral {
		try {
			conexion = new PSQLConexion().conectar();
			sentencia = conexion.prepareStatement(MODIFICAR);
			sentencia.setString(1, o.getDetalle());
			sentencia.setShort(2, o.getPaciente().getId_paciente());
			sentencia.setString(3, o.getConducta());
			sentencia.setShort(4, o.getIdDM());
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
	public void eliminar(DiagnosticoMedico o) throws ExcepcionGeneral {
		try {
			conexion = new PSQLConexion().conectar();
			sentencia = conexion.prepareStatement(ELIMINAR);
			sentencia.setShort(1, o.getIdDM());
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
	public DiagnosticoMedico obtenerPorID(Short k) throws ExcepcionGeneral {
		DiagnosticoMedico diagnosticoMedico = null;
		try {
			conexion = new PSQLConexion().conectar();
			sentencia = conexion.prepareStatement(OBTENERPORID);
			sentencia.setShort(1, k);
			resultado = sentencia.executeQuery();
			if (resultado.next()) {
				diagnosticoMedico = convertir(resultado);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return diagnosticoMedico;
	}
	
	private DiagnosticoMedico convertir(ResultSet rs) throws SQLException {
		DiagnosticoMedico diagnosticoMedico = new DiagnosticoMedico();
		PSQLPaciente paciente = new PSQLPaciente();
		diagnosticoMedico.setIdDM(rs.getShort("id_dm"));
		diagnosticoMedico.setDetalle(rs.getString("detalle"));
		diagnosticoMedico.setPaciente(paciente.obtenerPorID(rs.getShort("id_paciente")));
		diagnosticoMedico.setConducta(rs.getString("conducta"));
		return diagnosticoMedico;
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
