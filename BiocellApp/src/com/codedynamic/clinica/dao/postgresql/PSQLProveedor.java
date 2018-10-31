package com.codedynamic.clinica.dao.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.codedynamic.clinica.dao.interfaces.ProveedorDAO;
import com.codedynamic.clinica.excepciones.ExcepcionGeneral;
import com.codedynamic.clinica.modelo.Proveedor;
import com.codedynamic.clinica.modelo.TelefonoProveedor;

import javafx.collections.FXCollections;

public class PSQLProveedor {

	private final String INSERTAR = "INSERT INTO proveedores(nombre, ruc, direccion) VALUES(?, ?, ?) RETURNING id_proveedor, nombre, ruc, direccion";
	private final String MODIFICAR = "UPDATE proveedores SET nombre = ?, ruc = ?, direccion = ? WHERE id_proveedor = ? RETURNING id_proveedor, "
			+ "nombre, ruc, direccion";
	private final String ELIMINAR = "DELETE FROM proveedores WHERE id_proveedor = ?";
	private final String OBTENERPORID = "SELECT id_proveedor, nombre, ruc, direccion FROM proveedores WHERE id_proveedor = ?";
	private final String INSERTARTELEFONO = "INSERT INTO telefono_proveedores(telefono, id_proveedor) VALUES(?, ?)";
	private final String MODIFICARTELEFONO = "UPDATE telefono_proveedores SET telefono = ? WHERE telefono = ?";
	
	private Connection conexion;
	private PreparedStatement sentencia;
	private ResultSet resultado;
	
	private PSQLTelefonoProveedor psqlTelefonoProveedor;
	
	public Proveedor insertarProveedores(List<String> telefono, Proveedor proveedor) {
		Proveedor proveedor2 = null;
		try {
			proveedor = insertarProveedor(proveedor);
			insertarTelefonoProveedor(telefono, proveedor2);
		} catch (ExcepcionGeneral e) {
			throw new ExcepcionGeneral("Error al insertar Proveedor " + e.getMessage());
		}
		return proveedor2;
	}
	
	public Proveedor modificarProveedor(List<String> telefono, Proveedor proveedor) {
		Proveedor proveedor2 = null;
		
		return proveedor2;
	}
	
	private Proveedor insertarProveedor(Proveedor pv) throws ExcepcionGeneral{
		Proveedor proveedor = null;
		try {
			conexion = new PSQLConexion().conectar();
			sentencia = conexion.prepareStatement(INSERTAR);
			sentencia.setString(1, pv.getNombre());
			sentencia.setString(2, pv.getRuc());
			sentencia.setString(3, pv.getDireccion());
			resultado = sentencia.executeQuery();
			if (resultado.next()) {
				proveedor = convertir(resultado);
			} else {
				throw new ExcepcionGeneral("No se puedo insertar Proveedor");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return proveedor;
	}
	
	private void insertarTelefonoProveedor(List<String> telefono, Proveedor proveedor) {
		try {
			conexion = new PSQLConexion().conectar();
			sentencia = conexion.prepareStatement(INSERTARTELEFONO);
			for (String telefonoTemp : telefono) {
				TelefonoProveedor telefonoProveedor = new TelefonoProveedor();
				telefonoProveedor.setTelefono(telefonoTemp);
				telefonoProveedor.setProveedor(proveedor);
				sentencia.setString(1, telefonoTemp);
				sentencia.setShort(2, (short) proveedor.getIdProveedor());
				sentencia.executeUpdate();
				proveedor.addtTelefonoProveedor(telefonoProveedor);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insertar(Proveedor proveedor) {
		try {
			conexion = new PSQLConexion().conectar();
			sentencia = conexion.prepareStatement(INSERTAR);
			sentencia.setString(1, proveedor.getNombre());
			sentencia.setString(2, proveedor.getRuc());
			sentencia.setString(3, proveedor.getDireccion());
			resultado = sentencia.executeQuery();
			if (resultado.next()) {
				proveedor.setIdProveedor(resultado.getShort(1));
			} else {
				throw new ExcepcionGeneral("No se inserto ningun registro");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrarConexiones();
		}
	}

	private void modificar(Proveedor o) throws ExcepcionGeneral {
		try {
			conexion = new PSQLConexion().conectar();
			sentencia = conexion.prepareStatement(MODIFICAR);
			sentencia.setString(1, o.getNombre());
			sentencia.setString(2, o.getRuc());
			sentencia.setString(3, o.getDireccion());
			sentencia.setShort(4, (short) o.getIdProveedor());
			if (sentencia.executeUpdate() == 0) {
				throw new ExcepcionGeneral("No se modifico ningun registro");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrarConexiones();
		}
	}

	public void eliminar(Proveedor o) throws ExcepcionGeneral{
		try {
			conexion = new PSQLConexion().conectar();
			sentencia = conexion.prepareStatement(ELIMINAR);
			sentencia.setShort(1, (short) o.getIdProveedor());
			if (sentencia.executeUpdate() == 0) {
				throw new ExcepcionGeneral("No se elimino ningun registro");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrarConexiones();
		}
	}

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
