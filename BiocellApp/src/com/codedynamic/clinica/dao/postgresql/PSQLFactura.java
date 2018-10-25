package com.codedynamic.clinica.dao.postgresql;

import com.codedynamic.clinica.dao.interfaces.FacturaDAO;
import com.codedynamic.clinica.modelo.Factura;

public class PSQLFactura implements FacturaDAO {

	private final String INSERTAR = "INSERT INTO facturas(id_paciente, id_usuario, fecha, hora, total, estado) VALUES(?, ?, ?, ?, ?, ?)";
	private final String MODIFICAR = "UPDATE facturas SET id_paciente = ?, id_usuario = ?, fecha = ?, hora = ?, total = ?, estado = ? WHERE id_factura = ?";
	private final String ELIMINAR = "DELETE FROM facturas WHERE id_factura = ?";
	private final String OBTENERPORIDPACIENTE = "SELECT id_factura, id_paciente, id_usuario, fecha, hora, total, estado FROM facturas WHERE id_paciente = ?";
	
	@Override
	public void insertar(Factura o) {
		// TODO Auto-generated method stub

	}

	@Override
	public void modificar(Factura o) {
		// TODO Auto-generated method stub

	}

	@Override
	public void eliminar(Factura o) {
		// TODO Auto-generated method stub

	}

	@Override
	public Factura obtenerPorID(Integer k) {
		// TODO Auto-generated method stub
		return null;
	}

}
