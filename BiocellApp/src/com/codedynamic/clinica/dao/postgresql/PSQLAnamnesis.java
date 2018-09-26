package com.codedynamic.clinica.dao.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.codedynamic.clinica.dao.interfaces.AnamnesisDAO;
import com.codedynamic.clinica.excepciones.ExcepcionGeneral;
import com.codedynamic.clinica.modelo.Anamnesis;

public class PSQLAnamnesis implements AnamnesisDAO {
	
	private final String INSERTAR = "INSERT INTO anamnesis(antecedente_enfermedad, medicamento_actual, id_paciente) VALUES(?, ?, ?)";
	private final String MODIFICAR = "UPDATE anamnesis SET antecedente_enfermedad = ?, medicamento_actual = ?, id_paciente = ? WHERE id_anamnesis = ?";
	private final String ELIMINAR = "DELETE FROM anamnesis WHERE id_anamnesis = ?";
	private final String OBTENERPORID = "SELECT id_anamnesis, antecedente_enfermedad, medicamento_actual, id_paciente FROM anamnesis WHERE id_anamnesis = ?";
	
	private Connection conexion;
	private PreparedStatement sentencia;
	private ResultSet resultado;
	
	@Override
	public void insertar(Anamnesis o) throws ExcepcionGeneral {
		try {
			conexion = new PSQLConexion().conectar();
			sentencia = conexion.prepareStatement(INSERTAR);
			sentencia.setString(1, o.getAntecedenteEnfermedad());
			sentencia.setString(2, o.getMedicamentoActual());
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
	public void modificar(Anamnesis o) throws ExcepcionGeneral {
		try {
			conexion = new PSQLConexion().conectar();
			sentencia = conexion.prepareStatement(MODIFICAR);
			sentencia.setString(1, o.getAntecedenteEnfermedad());
			sentencia.setString(2, o.getMedicamentoActual());
			sentencia.setShort(3, o.getPaciente().getId_paciente());
			sentencia.setShort(4, o.getIdAnamnesis());
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
	public void eliminar(Anamnesis o) throws ExcepcionGeneral {
		try {
			conexion = new PSQLConexion().conectar();
			sentencia = conexion.prepareStatement(ELIMINAR);
			sentencia.setShort(1, o.getIdAnamnesis());
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
	public Anamnesis obtenerPorID(Short k) throws ExcepcionGeneral {
		Anamnesis anamnesis = null;
		try {
			conexion = new PSQLConexion().conectar();
			sentencia = conexion.prepareStatement(OBTENERPORID);
			sentencia.setShort(1, k);
			resultado = sentencia.executeQuery();
			if (resultado.next()) {
				anamnesis = convertir(resultado);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrarConexiones();
		}
		return anamnesis;
	}
	
	private Anamnesis convertir(ResultSet resultado) throws SQLException {
		PSQLPaciente psqlPaciente = new PSQLPaciente();
		Anamnesis anamnesis = new Anamnesis();
		anamnesis.setIdAnamnesis(resultado.getShort("id_anamnesis"));
		anamnesis.setAntecedenteEnfermedad(resultado.getString("antecedente_enfermedad"));
		anamnesis.setMedicamentoActual(resultado.getString("medicamento_actual"));
		anamnesis.setPaciente(psqlPaciente.obtenerPorID(resultado.getShort("id_paciente")));
		return anamnesis;
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
