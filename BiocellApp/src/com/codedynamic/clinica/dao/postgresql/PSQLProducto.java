package com.codedynamic.clinica.dao.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.codedynamic.clinica.dao.interfaces.ProductoDAO;
import com.codedynamic.clinica.excepciones.ExcepcionGeneral;
import com.codedynamic.clinica.modelo.Producto;

public class PSQLProducto implements ProductoDAO {

	private final String INSERTAR = "INSERT INTO productos(nombre, descripcion, precio, stock, id_categoria) VALUES(?, ?, ?, ?, ?) RETURNING id_producto";
	private final String MODIFICAR = "UPDATE productos SET nombre = ?, descripcion = ?, precio = ?, stock = ?, id_categoria = ? WHERE id_producto = ?";
	private final String ELIMINAR = "DELETE FROM productos WHERE id_producto = ?";
	private final String OBTENERPORID = "SELECT id_producto, nombre, descripcion, precio, stock, id_categoria FROM productos WHERE id_producto = ?";
	private final String OBTENERLISTA = "SELECT id_producto, nombre, descripcion, precio, stock, id_categoria FROM productos";
	
	private Connection conexion;
	private PreparedStatement sentencia;
	private ResultSet resultado;
	
	@Override
	public void insertar(Producto o) throws ExcepcionGeneral {
		try {
			conexion = new PSQLConexion().conectar();
			sentencia = conexion.prepareStatement(INSERTAR);
			sentencia.setString(1, o.getNombre());
			sentencia.setString(2, o.getDescripcion());
			sentencia.setInt(3, o.getPrecio());
			sentencia.setShort(4, (short) o.getStock());
			sentencia.setShort(5, o.getCategoriaProducto().getIdCategoria());
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
	public void modificar(Producto o) throws ExcepcionGeneral {
		try {
			conexion = new PSQLConexion().conectar();
			sentencia = conexion.prepareStatement(MODIFICAR);
			sentencia.setString(1, o.getNombre());
			sentencia.setString(2, o.getDescripcion());
			sentencia.setInt(3, o.getPrecio());
			sentencia.setShort(4, (short) o.getStock());
			sentencia.setShort(5, o.getCategoriaProducto().getIdCategoria());
			sentencia.setShort(6, (short) o.getIdProducto());
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
	public void eliminar(Producto o) throws ExcepcionGeneral {
		try {
			conexion = new PSQLConexion().conectar();
			sentencia = conexion.prepareStatement(ELIMINAR);
			sentencia.setShort(1, (short) o.getIdProducto());
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
	public Producto obtenerPorID(Integer k) throws ExcepcionGeneral {
		Producto producto = null;
		try {
			conexion = new PSQLConexion().conectar();
			sentencia = conexion.prepareStatement(OBTENERPORID);
			sentencia.setShort(1, k.shortValue());
			resultado = sentencia.executeQuery();
			if (resultado.next()) {
				producto = convertir(resultado);
			} else {
				throw new ExcepcionGeneral();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrarConexiones();
		}
		return producto;
	}
	
	public List<Producto> obtenerListaProducto() throws ExcepcionGeneral {
		List<Producto> listaProducto = new ArrayList<>();
		try {
			conexion = new PSQLConexion().conectar();
			sentencia = conexion.prepareStatement(OBTENERLISTA);
			resultado = sentencia.executeQuery();
			while (resultado.next()) {
				listaProducto.add(convertir(resultado));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrarConexiones();
		}
		return listaProducto;
	}
	
	private Producto convertir(ResultSet rs) throws SQLException {
		Producto producto = new Producto();
		PSQLCategoriaProducto categoriaProducto = new PSQLCategoriaProducto();
		producto.setIdProducto((int) rs.getShort("id_producto"));
		producto.setNombre(rs.getString("nombre"));
		producto.setDescripcion(rs.getString("descripcion"));
		producto.setPrecio(rs.getInt("precio"));
		producto.setStock((int) rs.getShort("stock"));
		producto.setCategoriaProducto(categoriaProducto.obtenerPorID(rs.getShort("id_categoria")));
		return producto;
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
