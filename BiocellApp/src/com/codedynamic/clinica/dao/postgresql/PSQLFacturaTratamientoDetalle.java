package com.codedynamic.clinica.dao.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.codedynamic.clinica.excepciones.ExcepcionGeneral;
import com.codedynamic.clinica.modelo.FacturaTratamiento;
import com.codedynamic.clinica.modelo.FacturaTratamientoDetalle;
import com.codedynamic.clinica.modelo.Tratamiento;

public class PSQLFacturaTratamientoDetalle {

	private final String INSERTAR = "INSERT INTO detalle_factura_tratamiento(id_facturat, id_tratamiento, sesiones, precio";
	private final String MODIFICAR = "UPDATE detalle_factura_tratamiento SET id_facturat = ?, id_tratamiento = ?, sesiones = ?, "
			+ "precio = ? WHERE id_detallef = ?";
	private final String ELIMINAR = "DELETE FROM detalle_factura_tratamiento WHERE id_detallef = ?";
	
	private Connection conexion;
	private PreparedStatement sentencia;
	private ResultSet resultado;
	
	public void insertar(List<Tratamiento> t, FacturaTratamiento f, Short sesiones) throws ExcepcionGeneral {
		try {
			conexion = new PSQLConexion().conectar();
			sentencia = conexion.prepareStatement(INSERTAR);
			for (Tratamiento tratamiento : t) {
				FacturaTratamientoDetalle detalle = new FacturaTratamientoDetalle();
				detalle.setFacturaTratamiento(f);
				detalle.setTratamiento(tratamiento);
				detalle.setSesiones(sesiones);
				sentencia.setShort(1, (short) f.getIdFacturaT());
				sentencia.setShort(2, (short) detalle.getTratamiento().getIdTratamiento());
				sentencia.setShort(3, (short) detalle.getSesiones());
				sentencia.setInt(4, detalle.getTratamiento().getPrecio());
				f.addDetalle(detalle);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			cerrarConexiones();
		}
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
