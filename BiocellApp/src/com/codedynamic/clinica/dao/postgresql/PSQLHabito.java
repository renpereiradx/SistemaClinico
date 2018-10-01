package com.codedynamic.clinica.dao.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.codedynamic.clinica.dao.interfaces.HabitoDAO;
import com.codedynamic.clinica.excepciones.ExcepcionGeneral;
import com.codedynamic.clinica.modelo.Habito;

public class PSQLHabito implements HabitoDAO {
	private final String INSERTAR = "INSERT INTO habitos(alimentarios, intestinal, urinario, suenho, actividad_fisica, factor_riesgo, ambiental, toxico, laboral, id_paciente) "
			+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private final String MODIFICAR = "UPDATE habitos SET alimentarios = ?, intestinal = ?, urinario = ?, suenho = ?, actividad_fisica = ?, factor_riesgo = ?, ambiental = ?, "
			+ "toxico = ?, laboral = ?, id_paciente = ? WHERE id_habitos = ?";
	private final String ELIMINAR = "DELETE FROM habitos WHERE id_habitos = ?";
	private final String OBTENERPORID = "SELECT id_habitos, alimentarios, intestinal, urinario, suenho, actividad_fisica, factor_riesgo, ambiental, toxico, laboral, id_paciente "
			+ "FROM habitos WHERE id_habitos = ?";
	
	private Connection conexion;
	private PreparedStatement sentencia;
	private ResultSet resultado;
	
	@Override
	public void insertar(Habito o) throws ExcepcionGeneral {
		try {
			conexion = new PSQLConexion().conectar();
			sentencia = conexion.prepareStatement(INSERTAR);
			sentencia.setString(1, o.getAlimentario());
			sentencia.setString(2, o.getIntestinal());
			sentencia.setString(3, o.getUrinario());
			sentencia.setString(4, o.getSuenho());
			sentencia.setString(5, o.getActividadFisica());
			sentencia.setString(6, o.getFactorRiesgo());
			sentencia.setString(7, o.getAmbiental());
			sentencia.setString(8, o.getToxico());
			sentencia.setString(9, o.getLaboral());
			sentencia.setShort(10, o.getPaciente().getId_paciente());
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
	public void modificar(Habito o) throws ExcepcionGeneral {
		try {
			conexion = new PSQLConexion().conectar();
			sentencia = conexion.prepareStatement(MODIFICAR);
			sentencia.setString(1, o.getAlimentario());
			sentencia.setString(2, o.getIntestinal());
			sentencia.setString(3, o.getUrinario());
			sentencia.setString(4, o.getSuenho());
			sentencia.setString(5, o.getActividadFisica());
			sentencia.setString(6, o.getFactorRiesgo());
			sentencia.setString(7, o.getAmbiental());
			sentencia.setString(8, o.getToxico());
			sentencia.setString(9, o.getLaboral());
			sentencia.setShort(10, o.getPaciente().getId_paciente());
			sentencia.setShort(11, o.getIdHabito());
			if (sentencia.executeUpdate()== 0) {
				throw new ExcepcionGeneral("No se modifico ningun registro");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrarConexiones();
		}
	}

	@Override
	public void eliminar(Habito o) throws ExcepcionGeneral {
		try {
			conexion = new PSQLConexion().conectar();
			sentencia = conexion.prepareStatement(ELIMINAR);
			sentencia.setShort(1, o.getIdHabito());
			if (sentencia.executeUpdate() == 0) {
				throw new ExcepcionGeneral();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrarConexiones();
		}
	}

	@Override
	public Habito obtenerPorID(Short k) throws ExcepcionGeneral{
		Habito habito = null;
		try {
			conexion = new PSQLConexion().conectar();
			sentencia = conexion.prepareStatement(OBTENERPORID);
			sentencia.setShort(1, k);
			resultado = sentencia.executeQuery();
			if (resultado.next()) {
				habito = convertir(resultado);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			cerrarConexiones();
		}
		return habito;
	}
	
	private Habito convertir(ResultSet resultado) throws SQLException {
		Habito habito = new Habito();
		PSQLPaciente psqlPaciente = new PSQLPaciente();
		habito.setIdHabito(resultado.getShort("id_habitos"));
		habito.setAlimentario(resultado.getString("alimentarios"));
		habito.setIntestinal(resultado.getString("intestinal"));
		habito.setUrinario(resultado.getString("urinario"));
		habito.setSuenho(resultado.getString("suenho"));
		habito.setActividadFisica(resultado.getString("actividad_fisica"));
		habito.setFactorRiesgo(resultado.getString("factor_riesgo"));
		habito.setAmbiental(resultado.getString("ambiental"));
		habito.setToxico(resultado.getString("toxico"));
		habito.setLaboral(resultado.getString("laboral"));
		habito.setPaciente(psqlPaciente.obtenerPorID(resultado.getShort("id_paciente")));
		return habito;
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
