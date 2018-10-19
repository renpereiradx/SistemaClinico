package com.codedynamic.clinica.dao.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.codedynamic.clinica.dao.interfaces.TelefonoProveedorDAO;
import com.codedynamic.clinica.excepciones.ExcepcionGeneral;
import com.codedynamic.clinica.modelo.TelefonoProveedor;

public class PSQLTelefonoProveedor implements TelefonoProveedorDAO {

	private final String INSERTAR = "INSERT INTO telefono_proveedores(telefono, id_proveedor) VALUES(?, ?)";
	private final String MODIFICAR = "UPDATE telefono_proveedores SET telefono = ?, id_proveedor = ? WHERE id_proveedor = ?";
	private final String ELIMINAR = "DELETE FROM telefono_proveedores WHERE id_proveedor = ?";
	private final String OBTENERPORID = "SELECT telefono, id_proveedor FROM telefono_proveedores WHERE id_proveedor = ?";
	private final String OBTENERLISTA = "SELECT telefono, id_proveedor FROM telefono proveedores WHERE id_proveedores = ?";
	
	private Connection conexion;
	private PreparedStatement sentencia;
	private ResultSet resultado;
	
	@Override
	public void insertar(TelefonoProveedor o) throws ExcepcionGeneral {
		try {
			conexion = new PSQLConexion().conectar();
			sentencia = conexion.prepareStatement(INSERTAR);
			sentencia.setString(1, o.getTelefono());
			sentencia.setShort(2, o.getProveedor().getIdProveedor());
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
	public void modificar(TelefonoProveedor o) throws ExcepcionGeneral {
		try {
			conexion = new PSQLConexion().conectar();
			sentencia = conexion.prepareStatement(MODIFICAR);
			sentencia.setString(1, o.getTelefono());
			sentencia.setShort(2, o.getProveedor().getIdProveedor());
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
	public void eliminar(TelefonoProveedor o) throws ExcepcionGeneral {
		try {
			conexion = new PSQLConexion().conectar();
			sentencia = conexion.prepareStatement(ELIMINAR);
			sentencia.setShort(1, o.getProveedor().getIdProveedor());
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
	public TelefonoProveedor obtenerPorID(Short k) throws ExcepcionGeneral {
		return null;
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
