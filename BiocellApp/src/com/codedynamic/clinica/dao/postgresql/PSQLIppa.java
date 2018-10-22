package com.codedynamic.clinica.dao.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.codedynamic.clinica.dao.interfaces.IppaDAO;
import com.codedynamic.clinica.excepciones.ExcepcionGeneral;
import com.codedynamic.clinica.modelo.Ippa;

public class PSQLIppa implements IppaDAO {
	private final String INSERTAR = "INSERT INTO ippa(cabeza, cuello, torax, aparato_cv, abdomen, aparato_g, piel, sistema, snc, psiquiatrico, id_paciente) "
			+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id_ppa";
	private final String MODIFICAR = "UPDATE ippa SET cabeza = ?, cuello = ?, torax = ?, aparato_cv = ?, abdomen = ?, aparato_g = ?, piel = ?, sistema = ?, "
			+ "snc = ?, psiquiatrico = ?, id_paciente = ? WHERE id_ppa = ?";
	private final String ELMINAR = "DELETE FROM ippa WHERE id_ppa = ?";
	private final String OBTENERPORID = "SELECT id_ppa, cabeza, cuello, torax, aparato_cv, abdomen, aparato_g, piel, sistema, snc, psiquiatrico, id_paciente "
			+ "FROM ippa WHERE id_ppa = ?";
	
	private Connection conexion;
	private PreparedStatement sentencia;
	private ResultSet resultado;
	
	@Override
	public void insertar(Ippa o) throws ExcepcionGeneral {
		try {
			conexion = new PSQLConexion().conectar();
			sentencia = conexion.prepareStatement(INSERTAR);
			sentencia.setString(1, o.getCabeza());
			sentencia.setString(2, o.getCuello());
			sentencia.setString(3, o.getTorax());
			sentencia.setString(4, o.getAparatoCV());
			sentencia.setString(5, o.getAbdomen());
			sentencia.setString(6, o.getAparatoG());
			sentencia.setString(7, o.getPiel());
			sentencia.setString(8, o.getSistema());
			sentencia.setString(9, o.getSnc());
			sentencia.setString(10, o.getPsquiatrico());
			sentencia.setShort(11, o.getPaciente().getId_paciente());
			resultado = sentencia.executeQuery();
			if (resultado.next()) {
				o.setIdPpa(resultado.getShort(1));
			} else {
				throw new ExcepcionGeneral("No se inserto ningun registro");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrarConexiones();
		}
	}

	@Override
	public void modificar(Ippa o) throws ExcepcionGeneral {
		try {
			conexion = new PSQLConexion().conectar();
			sentencia = conexion.prepareStatement(MODIFICAR);
			sentencia.setString(1, o.getCabeza());
			sentencia.setString(2, o.getCuello());
			sentencia.setString(3, o.getTorax());
			sentencia.setString(4, o.getAparatoCV());
			sentencia.setString(5, o.getAbdomen());
			sentencia.setString(6, o.getAparatoG());
			sentencia.setString(7, o.getPiel());
			sentencia.setString(8, o.getSistema());
			sentencia.setString(9, o.getSnc());
			sentencia.setString(10, o.getPsquiatrico());
			sentencia.setShort(11, o.getPaciente().getId_paciente());
			sentencia.setShort(12, o.getIdPpa());
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
	public void eliminar(Ippa o) throws ExcepcionGeneral {
		try {
			conexion = new PSQLConexion().conectar();
			sentencia = conexion.prepareStatement(ELMINAR);
			sentencia.setShort(1, o.getIdPpa());
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
	public Ippa obtenerPorID(Short k) throws ExcepcionGeneral {
		Ippa ippa = null;
		try {
			conexion = new PSQLConexion().conectar();
			sentencia = conexion.prepareStatement(OBTENERPORID);
			sentencia.setShort(1, k);
			resultado = sentencia.executeQuery();
			if (resultado.next()) {
				ippa = convertir(resultado);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrarConexiones();
		}
		return ippa;
	}
	
	private Ippa convertir(ResultSet rs) throws SQLException {
		Ippa ippa = new Ippa();
		PSQLPaciente paciente = new PSQLPaciente();
		ippa.setIdPpa(rs.getShort("id_ppa"));
		ippa.setCabeza(rs.getString("cabeza"));
		ippa.setCuello(rs.getString("cuell"));
		ippa.setTorax(rs.getString("torax"));
		ippa.setAparatoCV(rs.getString("aparato_cv"));
		ippa.setAbdomen(rs.getString("abdomen"));
		ippa.setAparatoG(rs.getString("aparato_g"));
		ippa.setPiel(rs.getString("piel"));
		ippa.setSistema(rs.getString("sistema"));
		ippa.setSnc(rs.getString("snc"));
		ippa.setPsquiatrico("psiquiatrico");
		ippa.setPaciente(paciente.obtenerPorID(rs.getShort("id_paciente")));
		return ippa;
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
