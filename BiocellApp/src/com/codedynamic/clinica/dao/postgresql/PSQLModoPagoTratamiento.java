package com.codedynamic.clinica.dao.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.codedynamic.clinica.dao.interfaces.ModoPagoTratamientoDAO;
import com.codedynamic.clinica.excepciones.ExcepcionGeneral;
import com.codedynamic.clinica.modelo.ModoPagoTratamiento;
import com.codedynamic.clinica.utilidades.UtilidadFecha;
import com.codedynamic.clinica.utilidades.UtilidadHora;

public class PSQLModoPagoTratamiento implements ModoPagoTratamientoDAO {

	private final String INSERTAR = "INSERT INTO modo_pago_tratamiento(id_factura, monto, fecha, hora) VALUES(?, ?, ?, ?)";
	private final String MODIFICAR = "UPDATE modo_pago_tratamiento SET id_factura = ?, monto = ?, fecha = ?, hora = ? WHERE num_pago = ?";
	private final String ELIMINAR = "DELETE FROM modo_pago_tratamiento WHERE num_pago = ?";
	private final String OBTENERPORID = "SELECT num_pago, id_factura, monto, fecha, hora FROM modo_pago_tratamiento WHERE num_pago = ?";
	
	private Connection conexion;
	private PreparedStatement sentencia;
	private ResultSet resultado;
	
	@Override
	public void insertar(ModoPagoTratamiento o) throws ExcepcionGeneral {
		try {
			conexion = new PSQLConexion().conectar();
			sentencia = conexion.prepareStatement(INSERTAR);
			sentencia.setShort(1, (short) o.getIdFacturaT().getIdFacturaT());
			sentencia.setInt(2, o.getMonto());
			sentencia.setString(3, UtilidadFecha.formato(o.getFecha()));
			sentencia.setString(4, UtilidadHora.formatoHora(o.getHora()));
			if (sentencia.executeUpdate() == 0) {
				throw new ExcepcionGeneral("No se inserto ningun registro");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
		}
		
	}

	@Override
	public void modificar(ModoPagoTratamiento o) {
		// TODO Auto-generated method stub

	}

	@Override
	public void eliminar(ModoPagoTratamiento o) {
		// TODO Auto-generated method stub

	}

	@Override
	public ModoPagoTratamiento obtenerPorID(Short k) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private ModoPagoTratamiento convertir(ResultSet rs) throws SQLException {
		ModoPagoTratamiento pagoTratamiento = new ModoPagoTratamiento();
		PSQLFacturaTratamiento tratamiento = new PSQLFacturaTratamiento();
		pagoTratamiento.setNumPago(rs.getShort("num_pago"));
		//pagoTratamiento.setIdFacturaT(tratamiento.);
		return pagoTratamiento;
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
