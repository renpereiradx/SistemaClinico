package com.codedynamic.clinica.dao.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.codedynamic.clinica.dao.interfaces.AtencionDAO;
import com.codedynamic.clinica.modelo.Atencion;

public class PSQLAtencion implements AtencionDAO {

	private final String INSERTAR = "INSERT INTO atenciones(id_turno, id_tatencion, id_usuario, motivo) VALUES(?, ?, ?, ?)";
	
	private Connection conexion;
	private PreparedStatement sentencia;
	private ResultSet resultado;
	
	@Override
	public void insertar(Atencion o) {
				
	}

	@Override
	public void modificar(Atencion o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminar(Atencion o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Atencion obtenerPorID(Short k) {
		// TODO Auto-generated method stub
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
		} catch (SQLException e) {}
	}

}
