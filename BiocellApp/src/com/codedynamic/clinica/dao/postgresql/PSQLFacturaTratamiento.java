package com.codedynamic.clinica.dao.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.codedynamic.clinica.excepciones.ExcepcionGeneral;
import com.codedynamic.clinica.modelo.FacturaTratamiento;
import com.codedynamic.clinica.modelo.Tratamiento;
import com.codedynamic.clinica.utilidades.UtilidadFecha;
import com.codedynamic.clinica.utilidades.UtilidadHora;

public class PSQLFacturaTratamiento {
	
	private final String INSERTAR = "INSERT INTO factura_tratamientos(id_paciente, id_usuario, fecha, hora, detalles, total, estado) VALUES(?, ?, ?, ?, ?, ?, ?) "
			+ "RETURNING id_facturat, id_paciente, id_usuario, fecha, hora, detalles, total, estado";
	private final String MODIFICAR = "UPDATE factura_tratamientos SET detalles = ?, estado = ? WHERE id_facturat = ?";
	private final String OBTENERPORID = "SELECT id_facturat, id_paciente, id_usuario, fecha, hora, detalles, total, estado FROM factura_tratamientos WHERE id_factura = ?";
	
	private Connection conexion;
	private PreparedStatement sentencia;
	private ResultSet resultado;
	
	public FacturaTratamiento insertarFactura(List<Tratamiento> t, Short s, FacturaTratamiento f) {
		FacturaTratamiento facturaTratamiento = null;
		try {
			facturaTratamiento = insertar(f);
			PSQLFacturaTratamientoDetalle detalle = new PSQLFacturaTratamientoDetalle();
			detalle.insertar(t, f, s);
		} catch (ExcepcionGeneral e) {
			e.printStackTrace();
		}
		return facturaTratamiento;
	}
	
	public FacturaTratamiento insertar(FacturaTratamiento f) throws ExcepcionGeneral {
		FacturaTratamiento facturaTratamiento = null;
		try { 
			conexion = new PSQLConexion().conectar();
			sentencia = conexion.prepareStatement(INSERTAR);
			sentencia.setShort(1, f.getPaciente().getId_paciente());
			sentencia.setShort(2, f.getUsuario().getId());
			sentencia.setString(3, UtilidadFecha.formato(f.getFecha()));
			sentencia.setString(4, UtilidadHora.formatoHora(f.getHora()));
			sentencia.setString(5, f.getDetalle());
			sentencia.setInt(6, f.getTotal());
			sentencia.setString(7, f.getEstado());
			resultado = sentencia.executeQuery();
			if (resultado.next()) {
				facturaTratamiento = convertir(resultado);
			} else {
				throw new ExcepcionGeneral("No se inserto ningun registro");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		return facturaTratamiento;
	}
	
	public void modificar(FacturaTratamiento f) {
		try {
			conexion = new PSQLConexion().conectar();
			sentencia = conexion.prepareStatement(MODIFICAR);
			sentencia.setString(1, f.getDetalle());
			sentencia.setString(2, f.getEstado());
			if (sentencia.executeUpdate() == 0) {
				throw new ExcepcionGeneral("No se modifico ningun registro");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
	}
	
	public FacturaTratamiento obtenerPorID(Short k) {
		FacturaTratamiento facturaTratamiento = null;
		return facturaTratamiento;
	}
	
	private FacturaTratamiento convertir(ResultSet rs) throws SQLException {
		FacturaTratamiento facturaTratamiento = new FacturaTratamiento();
		facturaTratamiento.setIdFacturaT(rs.getShort("id_facturat"));
		return facturaTratamiento;
	}
	
	private void cerrarConexion() {
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
