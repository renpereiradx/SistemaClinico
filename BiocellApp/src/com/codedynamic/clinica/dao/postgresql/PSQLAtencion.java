package com.codedynamic.clinica.dao.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.codedynamic.clinica.dao.interfaces.AtencionDAO;
import com.codedynamic.clinica.excepciones.ExcepcionGeneral;
import com.codedynamic.clinica.modelo.Atencion;

public class PSQLAtencion implements AtencionDAO {

	private final String INSERTAR = "INSERT INTO atenciones(id_turno, id_tatencion, id_usuario, motivo) VALUES(?, ?, ?, ?) RETURNING id_atencion";
	private final String MODIFICAR = "UPDATE atenciones SET id_turno = ?, id_tatencion = ?, id_usuario = ?, motivo = ? WHERE id_atencion = ?";
	private final String ELIMINAR = "DELETE FROM atenciones WHERE id_atencion = ?";
	private final String OBTENERPORID = "SELECT id_atencion, id_turno, id_tatencion, id_usuario, motivo FROM atenciones WHERE id_atencion = ?";
	
	private Connection conexion;
	private PreparedStatement sentencia;
	private ResultSet resultado;
	
	@Override
	public void insertar(Atencion o) throws ExcepcionGeneral {
		try {
			conexion = new PSQLConexion().conectar();
			sentencia = conexion.prepareStatement(INSERTAR);
			sentencia.setShort(1, o.getTurno().getId_turno());
			sentencia.setShort(2, o.getIdTipoAtencion());
			sentencia.setShort(3, o.getUsuario().getId());
			sentencia.setString(4, o.getMotivo());
			resultado = sentencia.executeQuery();
			if (resultado.next()) {
				o.setIdAtencion(resultado.getShort(1));
			} else {
				throw new ExcepcionGeneral("No se pudo insertar ningun Registro");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrarConexiones();
		}
	}

	@Override
	public void modificar(Atencion o) throws ExcepcionGeneral {
		try {
			conexion = new PSQLConexion().conectar();
			sentencia = conexion.prepareStatement(MODIFICAR);
			sentencia.setShort(1, o.getTurno().getId_turno());
			sentencia.setShort(2, o.getIdTipoAtencion());
			sentencia.setShort(3, o.getUsuario().getId());
			sentencia.setString(4, o.getMotivo());
			sentencia.setShort(4, o.getIdAtencion());
			if (sentencia.executeUpdate() == 0) {
				throw new ExcepcionGeneral("No se pudo modificar ningun registro");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrarConexiones();
		}
		
	}

	@Override
	public void eliminar(Atencion o) throws ExcepcionGeneral {
		try {
			conexion = new PSQLConexion().conectar();
			sentencia = conexion.prepareStatement(ELIMINAR);
			sentencia.setShort(1, o.getIdAtencion());
			if (sentencia.executeUpdate() == 0) {
				throw new ExcepcionGeneral("No se pudo eliminar ningun Registro");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrarConexiones();
		}
	}

	@Override
	public Atencion obtenerPorID(Short k) throws ExcepcionGeneral {
		Atencion atencion = null;
		try {
			conexion = new PSQLConexion().conectar();
			sentencia = conexion.prepareStatement(OBTENERPORID);
			sentencia.setShort(1, k);
			resultado = sentencia.executeQuery();
			if (resultado.next()) {
				atencion = convertir(resultado);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrarConexiones();
		}
		return atencion;
	}
	
	private Atencion convertir(ResultSet resultado) throws SQLException {
		Atencion atencion = new Atencion();
		PSQLTurno psqlTurno = new PSQLTurno();
		PSQLUsuario psqlUsuario = new PSQLUsuario();
		atencion.setIdAtencion(resultado.getShort("id_atencion"));
		atencion.setIdTipoAtencion(resultado.getShort("id_tatencion"));
		atencion.setMotivo(resultado.getString("motivo"));
		atencion.setTurno(psqlTurno.obtenerPorID(resultado.getShort("id_turno")));
		atencion.setUsuario(psqlUsuario.obtenerPorID(resultado.getShort("id_usuario")));
		return atencion;
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
		} catch (SQLException e) {}
	}

}
