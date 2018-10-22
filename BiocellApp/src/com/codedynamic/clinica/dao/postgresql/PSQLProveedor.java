package com.codedynamic.clinica.dao.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.codedynamic.clinica.dao.interfaces.ProveedorDAO;
import com.codedynamic.clinica.excepciones.ExcepcionGeneral;
import com.codedynamic.clinica.modelo.Proveedor;

public class PSQLProveedor implements ProveedorDAO{

	private final String INSERTAR = "INSERT INTO proveedores(nombre, ruc, direccion) VALUES(?, ?, ?)";
	private final String MODIFICAR = "UPDATE proveedores SET nombre = ?, ruc = ?, direccion = ? WHERE id_proveedor = ?";
	private final String ELIMINAR = "DELETE FROM proveedores WHERE id_proveedor = ?";
	private final String OBTENERPORID = "SELECT id_proveedor, nombre, ruc, direccion FROM proveedores WHERE id_proveedor = ?";
	
	private Connection conexion;
	private PreparedStatement sentencia;
	private ResultSet resultado;
	
	@Override
	public void insertar(Proveedor o) throws ExcepcionGeneral {
		try {
			conexion = new PSQLConexion().conectar();
			sentencia = conexion.prepareStatement(INSERTAR);
			sentencia.setString(1, o.getNombre());
			sentencia.setString(2, o.getRuc());
			sentencia.setString(3, o.getDireccion());
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
	public void modificar(Proveedor o) throws ExcepcionGeneral {
		try {
			conexion = new PSQLConexion().conectar();
			sentencia = conexion.prepareStatement(MODIFICAR);
			sentencia.setString(1, o.getNombre());
			sentencia.setString(2, o.getRuc());
			sentencia.setString(3, o.getDireccion());
			sentencia.setShort(4, o.getIdProveedor());
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
	public void eliminar(Proveedor o) throws ExcepcionGeneral{
		try {
			conexion = new PSQLConexion().conectar();
			sentencia = conexion.prepareStatement(ELIMINAR);
			sentencia.setShort(1, o.getIdProveedor());
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
	public Proveedor obtenerPorID(Short k) {
		Proveedor proveedor = null;
		try {
			conexion = new PSQLConexion().conectar();
			sentencia = conexion.prepareStatement(OBTENERPORID);
			sentencia.setShort(1, k);
			resultado = sentencia.executeQuery();
			if (resultado.next()) {
				proveedor = convertir(resultado);
			} else {
				throw new ExcepcionGeneral("No se pudo obtener ningun registro");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrarConexiones();
		}
		return proveedor;
	}
	
	private Proveedor convertir(ResultSet rs) throws SQLException {
		Proveedor proveedor = new Proveedor();
		proveedor.setIdProveedor(rs.getShort("id_proveedor"));
		proveedor.setNombre(rs.getString("nombre"));
		proveedor.setRuc(rs.getString("ruc"));
		proveedor.setDireccion(rs.getString("direccion"));
		return proveedor;
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
