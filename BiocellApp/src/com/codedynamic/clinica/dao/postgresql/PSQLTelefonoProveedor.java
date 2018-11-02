package com.codedynamic.clinica.dao.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.codedynamic.clinica.dao.interfaces.TelefonoProveedorDAO;
import com.codedynamic.clinica.excepciones.ExcepcionGeneral;
import com.codedynamic.clinica.modelo.TelefonoProveedor;

public class PSQLTelefonoProveedor implements TelefonoProveedorDAO {

	private final String INSERTAR = "INSERT INTO telefono_proveedores(telefono, id_proveedor) VALUES(?, ?)";
	private final String MODIFICAR = "UPDATE telefono_proveedores SET telefono = ? WHERE telefono = ? AND id_proveedor = ? RETURNING telefono";
	private final String ELIMINAR = "DELETE FROM telefono_proveedores WHERE telefono = ? AND id_proveedor = ?";
	private final String OBTENERPORID = "SELECT telefono, id_proveedor FROM telefono_proveedores WHERE id_proveedor = ?";
	
	private Connection conexion;
	private PreparedStatement sentencia;
	private ResultSet resultado;
	
	@Override
	public void insertar(TelefonoProveedor o) throws ExcepcionGeneral {
		try {
			conexion = new PSQLConexion().conectar();
			sentencia = conexion.prepareStatement(INSERTAR);
			sentencia.setString(1, o.getTelefono());
			sentencia.setShort(2, (short) o.getProveedor().getIdProveedor());
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
			sentencia.setShort(2, (short) o.getProveedor().getIdProveedor());
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
			sentencia.setString(1, o.getTelefono());
			sentencia.setShort(2, (short) o.getProveedor().getIdProveedor());
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
		TelefonoProveedor telefonoProveedor = null;
		try {
			conexion = new PSQLConexion().conectar();
			sentencia = conexion.prepareStatement(OBTENERPORID);
			sentencia.setShort(1, k);
			resultado = sentencia.executeQuery();
			if (resultado.next()) {
				telefonoProveedor = convertir(resultado);
			} else {
				throw new ExcepcionGeneral("No se obtuvo ningun registro");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrarConexiones();
		}
		return telefonoProveedor;
	}
	
	public List<TelefonoProveedor> obtenerLista(Short k) throws ExcepcionGeneral {
		List<TelefonoProveedor> telefonoProveedors = new ArrayList<>();
		try {
			conexion = new PSQLConexion().conectar();
			sentencia = conexion.prepareStatement(OBTENERPORID);
			sentencia.setShort(1, k);
			resultado = sentencia.executeQuery();
			while (resultado.next()) {
				telefonoProveedors.add(convertir(resultado));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			cerrarConexiones();
		}
		return telefonoProveedors;		
	}
	
	public void modificarTelefono(String telefono, TelefonoProveedor o) throws ExcepcionGeneral {
		try {
			conexion = new PSQLConexion().conectar();
			sentencia = conexion.prepareStatement(MODIFICAR);
			sentencia.setString(1, telefono);
			sentencia.setString(2, o.getTelefono());
			sentencia.setShort(3, (short) o.getProveedor().getIdProveedor());
			resultado = sentencia.executeQuery();
			if (resultado.next()) {
				o.setTelefono(resultado.getString(1));
			} else {
				throw new ExcepcionGeneral("No se modifico ningun registro");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrarConexiones();
		}
	}
	
	public void eliminarTelefono(TelefonoProveedor o) throws ExcepcionGeneral {
		try {
			conexion = new PSQLConexion().conectar();
			sentencia = conexion.prepareStatement(MODIFICAR);
			sentencia.setString(1, o.getTelefono());
			sentencia.setShort(2, (short) o.getProveedor().getIdProveedor());
			if (sentencia.executeUpdate() == 0) {
				throw new ExcepcionGeneral("No se modifico ningun registro");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrarConexiones();
		}
	}
	
	private TelefonoProveedor convertir(ResultSet rs) throws SQLException {
		TelefonoProveedor telefono = new TelefonoProveedor();
		PSQLProveedor proveedor = new PSQLProveedor();
		telefono.setProveedor(proveedor.obtenerPorID(rs.getShort("id_proveedor")));
		telefono.setTelefono(rs.getString("telefono"));
		return telefono;
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
