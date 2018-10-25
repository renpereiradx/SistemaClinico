package com.codedynamic.clinica.vista.productos;

import java.util.List;

import com.codedynamic.clinica.dao.postgresql.PSQLCategoriaProducto;
import com.codedynamic.clinica.dao.postgresql.PSQLProducto;
import com.codedynamic.clinica.modelo.CategoriaProducto;
import com.codedynamic.clinica.modelo.Producto;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;

public class ProductoRegistroControlador {
	
	@FXML private JFXTextField nombreTextField;
	@FXML private JFXTextField precioTextField;
	@FXML private JFXTextField descripcionTextField;
	@FXML private JFXComboBox<Producto> tipoProductoCB;
	@FXML private JFXTextField stockTextField;
	
	private PSQLProducto psqlProducto;
	private PSQLCategoriaProducto psqlCategoriProducto;
	
	@FXML private void initialize() {
		psqlCategoriProducto = new PSQLCategoriaProducto();
		List<String> categoriaNombre;
		
		
	}
	
	
}
