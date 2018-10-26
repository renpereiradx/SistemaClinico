package com.codedynamic.clinica.dao.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.codedynamic.clinica.dao.interfaces.CategoriaProductoDAO;
import com.codedynamic.clinica.excepciones.ExcepcionGeneral;
import com.codedynamic.clinica.modelo.CategoriaProducto;

public class PSQLCategoriaProducto implements CategoriaProductoDAO {

	private final String INSERTAR = "INSERT INTO categoria_productos(categoria, descripcion) VALUES(?, ?)";
	private final String MODIFICAR = "UPDATE categoria_productos SET categoria = ?, descripcion = ? WHERE id_categoria = ?";
	private final String ELIMINAR = "DELETE FROM categoria_productos WHERE id_categoria = ?";
	private final String OBTENERPORID = "SELECT id_categoria, categoria, descripcion FROM categoria_productos WHERE id_categoria = ?";
	private final String OBTENER = "SELECT id_categoria, categoria, descripcion FROM categoria_productos";
	
	private Connection conexion;
	private PreparedStatement sentencia;
	private ResultSet resultado;
	
	@Override
	public void insertar(CategoriaProducto o) throws ExcepcionGeneral {
		try {
			conexion = new PSQLConexion().conectar();
			sentencia = conexion.prepareStatement(INSERTAR);
			sentencia.setString(1, o.getCategoria());
			sentencia.setString(2, o.getDescripcion());
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
	public void modificar(CategoriaProducto o) throws ExcepcionGeneral {
		try {
			conexion = new PSQLConexion().conectar();
			sentencia = conexion.prepareStatement(MODIFICAR);
			sentencia.setString(1, o.getCategoria());
			sentencia.setString(2, o.getDescripcion());
			sentencia.setShort(3, o.getIdCategoria());
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
	public void eliminar(CategoriaProducto o) {
		try {
			conexion = new PSQLConexion().conectar();
			sentencia = conexion.prepareStatement(ELIMINAR);
			sentencia.setShort(1, o.getIdCategoria());
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
	public CategoriaProducto obtenerPorID(Short k) {
		CategoriaProducto categoriaProducto = null;
		try {
			conexion = new PSQLConexion().conectar();
			sentencia = conexion.prepareStatement(OBTENERPORID);
			sentencia.setShort(1, k);
			resultado = sentencia.executeQuery();
			if (resultado.next()) {
				categoriaProducto = convertir(resultado);
			} else {
				throw new ExcepcionGeneral("No se obtuvo ningun registro");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrarConexiones();
		}
		return categoriaProducto;
	}
	
	public List<CategoriaProducto> obtenerLista() {
		List<CategoriaProducto> lista = new ArrayList<>();
		try {
			conexion = new PSQLConexion().conectar();
			sentencia = conexion.prepareStatement(OBTENER);
			resultado = sentencia.executeQuery();
			while (resultado.next()) {
				lista.add(convertir(resultado));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrarConexiones();
		}
		return lista;
	}
	
	private CategoriaProducto convertir(ResultSet rs) throws SQLException {
		CategoriaProducto categoriaProducto = new CategoriaProducto();
		categoriaProducto.setIdCategoria(rs.getShort("id_categoria"));
		categoriaProducto.setCategoria(rs.getString("categoria"));
		categoriaProducto.setDescripcion(rs.getString("descripcion"));
		return categoriaProducto;
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
