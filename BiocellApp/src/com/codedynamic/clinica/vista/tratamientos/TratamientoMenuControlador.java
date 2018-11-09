package com.codedynamic.clinica.vista.tratamientos;

import com.codedynamic.clinica.MainApp;

import javafx.fxml.FXML;

public class TratamientoMenuControlador {
	
	private MainApp mainApp;
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	@FXML
	private void mostrarTratamientoFactura() {
		mainApp.mostrarTratamientoFacutra();
	}

	@FXML
	private void mostrarPaginaPrincipal() {
		mainApp.getContenedorPrincipal().setLeft(null);
		mainApp.getContenedorPrincipal().setCenter(null);
		mainApp.showHome();
	}
	
	@FXML
	private void mostrarTratamientos() {
		mainApp.mostrarTratamientos();
	}
}