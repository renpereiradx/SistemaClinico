package com.codedynamic.clinica.vista.productos;

import com.codedynamic.clinica.MainApp;

import javafx.fxml.FXML;

public class ProductoMenuControlador {

	private MainApp mainApp;
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	@FXML
	private void initialize() {}

	@FXML
	private void mostrarAdminProducto() {
		mainApp.mostrarProductoPrincial();
	}
	
	@FXML
	private void mostrarPaginaPrincipal() {
		mainApp.getContenedorPrincipal().setLeft(null);
		mainApp.getContenedorPrincipal().setCenter(null);
		mainApp.showHome();
	}
}
