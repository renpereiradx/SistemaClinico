package com.codedynamic.clinica.dao.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.codedynamic.clinica.dao.interfaces.TratamientoDAO;
import com.codedynamic.clinica.excepciones.ExcepcionGeneral;
import com.codedynamic.clinica.modelo.Tratamiento;

public class PSQLTratamiento implements TratamientoDAO {

	private final String INSERTAR = "INSERT INTO tratamientos(nombre, precio, descripcion) VALUES(?, ?, ?) RETURNING id_tratamiento";
	private final String MODIFICAR = "UPDATE tratamientos SET nombre = ?, precio = ?, descripcion = ? WHERE id_tratamiento = ?";
	private final String ELIMINAR = "DELETE FROM tratamientos WHERE id_tratamiento = ?";
	private final String OBTENERPORNOMBRE = "SELECT id_tratamiento, nombre, precio, descripcion FROM tratamientos WHERE nombre ILIKE ?";
	private final String OBTENERTODOS = "SELECT id_tratamiento, nombre, precio, descripcion FROM tratamientos";
	
	private Connection conexion;
	private PreparedStatement sentencia;
	private ResultSet resultado;
	
	@Override
	public void insertar(Tratamiento o) throws ExcepcionGeneral {
		try {
			conexion = new PSQLConexion().conectar();
			sentencia = conexion.prepareStatement(INSERTAR);
			sentencia.setString(1, o.getNombre());
			sentencia.setInt(2, o.getPrecio());
			sentencia.setString(3, o.getDescripcion());
			resultado = sentencia.executeQuery();
			if (resultado.next()) {
				o.setIdTratamiento(resultado.getShort(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
	}

	@Override
	public void modificar(Tratamiento o) throws ExcepcionGeneral {
		try {
			conexion = new PSQLConexion().conectar();
			sentencia = conexion.prepareStatement(MODIFICAR);
			sentencia.setString(1, o.getNombre());
			sentencia.setInt(2, o.getPrecio());
			sentencia.setString(3, o.getDescripcion());
			sentencia.setShort(4, (short) o.getIdTratamiento());
			if (sentencia.executeUpdate() == 0) {
				throw new ExcepcionGeneral("No se modifico ningun archivo");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
	}

	@Override
	public void eliminar(Tratamiento o) throws ExcepcionGeneral {
		try {
			conexion = new PSQLConexion().conectar();
			sentencia = conexion.prepareStatement(ELIMINAR);
			sentencia.setShort(1, (short) o.getIdTratamiento());
			if (sentencia.executeUpdate() == 0) {
				throw new ExcepcionGeneral("No se elimino ningun registro");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
	}

	@Override
	public Tratamiento obtenerPorID(Short k) {
		return null;
	}
	
	public List<Tratamiento> obtenerPorNombre(String nombre) {
		List<Tratamiento> listaTratamiento = new ArrayList<>();
		try {
			conexion = new PSQLConexion().conectar();
			sentencia = conexion.prepareStatement(OBTENERPORNOMBRE);
			sentencia.setString(1, "%"+nombre+"%");
			resultado = sentencia.executeQuery();
			while (resultado.next()) {
				listaTratamiento.add(convertir(resultado));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		return listaTratamiento;
	}
	
	public List<Tratamiento> obtenerTodo() {
		List<Tratamiento> listaTratamiento = new ArrayList<>();
		try {
			conexion = new PSQLConexion().conectar();
			sentencia = conexion.prepareStatement(OBTENERTODOS);
			resultado = sentencia.executeQuery();
			while (resultado.next()) {
				listaTratamiento.add(convertir(resultado));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		return listaTratamiento;
	}
	
	private Tratamiento convertir(ResultSet rs) throws SQLException {
		Tratamiento tratamiento = new Tratamiento();
		tratamiento.setIdTratamiento(rs.getShort("id_tratamiento"));
		tratamiento.setNombre(rs.getString("nombre"));
		tratamiento.setPrecio(rs.getInt("precio"));
		tratamiento.setDescripcion(rs.getString("descripcion"));
		return tratamiento;
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
