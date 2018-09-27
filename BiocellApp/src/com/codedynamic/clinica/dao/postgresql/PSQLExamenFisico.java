package com.codedynamic.clinica.dao.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.codedynamic.clinica.dao.interfaces.ExamenFisicoDAO;
import com.codedynamic.clinica.excepciones.ExcepcionGeneral;
import com.codedynamic.clinica.modelo.ExamenFisico;

public class PSQLExamenFisico implements ExamenFisicoDAO {
	private final String INSERTAR = "INSERT INTO examenes_fisicos(pa, peso, altura, fc, fr, t_axilar, talla, imc, ca, glicemia, spo, detalle, id_paciente) "
			+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private final String MODIFICAR = "UPDATE examenes_fisicos SET pa = ?, peso = ?, altura = ? fc = ?, fr = ?, t_axilar = ?, talla = ?, imc = ?, ca = ?, "
			+ "glicemia = ?, spo = ?, detalle = ?, id_paciente = ? WHERE id_efisico = ?";
	private final String ELIMINAR = "DELETE FROM examenes_fisicos WHERE id_efisico = ?";
	private final String OBTENERPORID = "SELECT id_efisico, pa, peso, altura, fc, fr, t_axilar, talla, imc, ca, glicemia, spo, detalle, id_paciente "
			+ "FROM examenes_fisicos WHERE id_efisico = ?";
	
	private Connection conexion;
	private PreparedStatement sentencia;
	private ResultSet  resultado;
	
	@Override
	public void insertar(ExamenFisico o) throws ExcepcionGeneral {
		try {
			conexion = new PSQLConexion().conectar();
			sentencia = conexion.prepareStatement(INSERTAR);
			sentencia.setString(1, o.getPa());
			sentencia.setString(2, o.getPeso());
			sentencia.setString(3, o.getAltura());
			sentencia.setString(4, o.getFc());
			sentencia.setString(5, o.getFr());
			sentencia.setString(6, o.gettAxilar());
			sentencia.setString(7, o.getTalla());
			sentencia.setString(8, o.getImc());
			sentencia.setString(9, o.getCa());
			sentencia.setString(10, o.getGlicemia());
			sentencia.setString(11, o.getSpo());
			sentencia.setString(12, o.getDetalle());
			sentencia.setShort(13, o.getPaciente().getId_paciente());
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
	public void modificar(ExamenFisico o) throws ExcepcionGeneral {
		try {
			conexion = new PSQLConexion().conectar();
			sentencia = conexion.prepareStatement(MODIFICAR);
			sentencia.setString(1, o.getPa());
			sentencia.setString(2, o.getPeso());
			sentencia.setString(3, o.getAltura());
			sentencia.setString(4, o.getFc());
			sentencia.setString(5, o.getFr());
			sentencia.setString(6, o.gettAxilar());
			sentencia.setString(7, o.getTalla());
			sentencia.setString(8, o.getImc());
			sentencia.setString(9, o.getCa());
			sentencia.setString(10, o.getGlicemia());
			sentencia.setString(11, o.getSpo());
			sentencia.setString(12, o.getDetalle());
			sentencia.setShort(13, o.getPaciente().getId_paciente());
			sentencia.setShort(14, o.getIdEFisico());
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
	public void eliminar(ExamenFisico o) throws ExcepcionGeneral {
		try {
			conexion = new PSQLConexion().conectar();
			sentencia = conexion.prepareStatement(ELIMINAR);
			sentencia.setShort(1, o.getIdEFisico());
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
	public ExamenFisico obtenerPorID(Short k) throws ExcepcionGeneral {
		ExamenFisico examenFisico = null;
		try {
			conexion = new PSQLConexion().conectar();
			sentencia = conexion.prepareStatement(OBTENERPORID);
			sentencia.setShort(1, k);
			resultado = sentencia.executeQuery();
			if (resultado.next()) {
				examenFisico = convertir(resultado);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			cerrarConexiones();
		}
		return examenFisico;
	}
	
	private ExamenFisico convertir(ResultSet resultado) throws SQLException {
		PSQLPaciente paciente = new PSQLPaciente();
		ExamenFisico examenFisico = new ExamenFisico();
		examenFisico.setIdEFisico(resultado.getShort("id_efisico"));
		examenFisico.setPa(resultado.getString("pa"));
		examenFisico.setPeso(resultado.getString("peso"));
		examenFisico.setAltura(resultado.getString("altura"));
		examenFisico.setFc(resultado.getString("fc"));
		examenFisico.setFr(resultado.getString("fr"));
		examenFisico.settAxilar(resultado.getString("t_axilar"));
		examenFisico.setTalla(resultado.getString("talla"));
		examenFisico.setImc(resultado.getString("imc"));
		examenFisico.setCa(resultado.getString("ca"));
		examenFisico.setGlicemia(resultado.getString("glicemia"));
		examenFisico.setSpo(resultado.getString("spo"));
		examenFisico.setDetalle(resultado.getString("detalle"));
		examenFisico.setPaciente(paciente.obtenerPorID(resultado.getShort("id_paciente")));
		return examenFisico;
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
