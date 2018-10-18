package com.codedynamic.clinica.dao.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.codedynamic.clinica.dao.interfaces.DetalleAtencionDAO;
import com.codedynamic.clinica.excepciones.ExcepcionGeneral;
import com.codedynamic.clinica.modelo.DetalleAtencion;
import com.codedynamic.clinica.utilidades.UtilidadFecha;
import com.codedynamic.clinica.utilidades.UtilidadHora;

public class PSQLDetalleAtencion implements DetalleAtencionDAO {

	private final String INSERTAR = "INSERT INTO detalles_atenciones(id_atencion, id_usuario, id_anamnesis, id_hab, id_efisico, id_ppa, id_dm, "
			+ "fecha, hora) VALUES(?, ?, ?, ?, ?, ?, ?, to_date(?, 'DD-MM-YYYY'), to_timestamp(?, 'HH24:MI:SS')) RETURNING id_da";
	private final String MODIFICAR = "UPDATE detalles_atenciones SET d_atencion = ?, id_usuario = ?, id_anamnesis = ?, id_hab = ?, id_efisico = ?, id_ppa = ?, "
			+ "id_dm = ?, fecha = ?, hora = ? WHERE id_da = ?";
	private final String ELIMINAR = "DELETE FROM detalles_atenciones WHERE id_da = ?";
	private final String OBTENERPORID = "SELECT id_atencion, id_usuario, id_anamnesis, id_hab, id_efisico, id_ppa, id_dm, fecha, hora "
			+ "FROM detalles_atenciones WHERE id_da = ?";
	
	private Connection conexion;
	private PreparedStatement sentencia;
	private ResultSet resultado;
	
	@Override
	public void insertar(DetalleAtencion o) throws ExcepcionGeneral {
		try {
			conexion = new PSQLConexion().conectar();
			sentencia = conexion.prepareStatement(INSERTAR);
			sentencia.setShort(1, o.getAtencion().getIdAtencion());
			sentencia.setShort(2, o.getUsuario().getId());
			sentencia.setShort(3, o.getAnamnesis().getIdAnamnesis());
			sentencia.setShort(4, o.getHabito().getIdHabito());
			sentencia.setShort(5, o.getExamenFisico().getIdEFisico());
			sentencia.setShort(6, o.getIppa().getIdPpa());
			sentencia.setShort(7, o.getDiagnosticoMedico().getIdDM());
			sentencia.setString(8, UtilidadFecha.formato(o.getFecha()));
			sentencia.setString(9, UtilidadHora.formatoHora(o.getHora()));
			resultado = sentencia.executeQuery();
			if (resultado.next()) {
				o.setIdDA(resultado.getShort(1));
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
	public void modificar(DetalleAtencion o) throws ExcepcionGeneral {
		try {
			conexion = new PSQLConexion().conectar();
			sentencia = conexion.prepareStatement(MODIFICAR);
			sentencia.setShort(1, o.getAtencion().getIdAtencion());
			sentencia.setShort(2, o.getUsuario().getId());
			sentencia.setShort(3, o.getAnamnesis().getIdAnamnesis());
			sentencia.setShort(4, o.getHabito().getIdHabito());
			sentencia.setShort(5, o.getExamenFisico().getIdEFisico());
			sentencia.setShort(6, o.getIppa().getIdPpa());
			sentencia.setShort(7, o.getDiagnosticoMedico().getIdDM());
			sentencia.setString(8, UtilidadFecha.formato(o.getFecha()));
			sentencia.setString(9, UtilidadHora.formatoHora(o.getHora()));
			sentencia.setShort(10, o.getIdDA());
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
	public void eliminar(DetalleAtencion o) throws ExcepcionGeneral {
		try {
			conexion = new PSQLConexion().conectar();
			sentencia = conexion.prepareStatement(ELIMINAR);
			sentencia.setShort(1, o.getIdDA());
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
	public DetalleAtencion obtenerPorID(Short k) throws ExcepcionGeneral {
		DetalleAtencion detalleAtencion = null;
		try {
			conexion = new PSQLConexion().conectar();
			sentencia = conexion.prepareStatement(OBTENERPORID);
			sentencia.setShort(1, k);
			resultado = sentencia.executeQuery();
			if (resultado.next()) {
				detalleAtencion = convertir(resultado);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrarConexiones();
		}
		return detalleAtencion;
	}
	
	private DetalleAtencion convertir(ResultSet rs) throws SQLException {
		DetalleAtencion atencion = new DetalleAtencion();
		PSQLAtencion psqlAtencion = new PSQLAtencion();
		PSQLUsuario psqlUsuario = new PSQLUsuario();
		PSQLAnamnesis psqlAnamnesis = new PSQLAnamnesis();
		PSQLHabito psqlHabito = new PSQLHabito();
		PSQLExamenFisico psqlExamenFisico = new PSQLExamenFisico();
		PSQLIppa psqlIppa = new PSQLIppa();
		PSQLDiagnosticoMedico psqlDiagnosticoMedico = new PSQLDiagnosticoMedico();
		atencion.setIdDA(rs.getShort("id_da"));
		atencion.setAtencion(psqlAtencion.obtenerPorID(rs.getShort("id_atencion")));
		atencion.setUsuario(psqlUsuario.obtenerPorID(rs.getShort("id_usuario")));
		atencion.setAnamnesis(psqlAnamnesis.obtenerPorID(rs.getShort("id_anamnesis")));
		atencion.setHabito(psqlHabito.obtenerPorID(rs.getShort("id_hab")));
		atencion.setExamenFisico(psqlExamenFisico.obtenerPorID(rs.getShort("id_efisico")));
		atencion.setIppa(psqlIppa.obtenerPorID(rs.getShort("id_ppa")));
		atencion.setDiagnosticoMedico(psqlDiagnosticoMedico.obtenerPorID(rs.getShort("id_dm")));
		atencion.setFecha(UtilidadFecha.analizar(rs.getString("fecha")));
		atencion.setHora(UtilidadHora.analizarHora(rs.getString("hora")));
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
