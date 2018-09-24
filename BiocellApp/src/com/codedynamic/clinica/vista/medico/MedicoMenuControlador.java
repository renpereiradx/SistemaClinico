package com.codedynamic.clinica.vista.medico;

import com.codedynamic.clinica.MainApp;

import javafx.fxml.FXML;

public class MedicoMenuControlador {
	private MainApp mainApp;
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	@FXML
	private void initialize() {}

	@FXML
	private void mostrarMedicoPrincipal() {
		mainApp.mostrarMedicoPrincipal();
	}
	
	@FXML
	private void mostrarPaginaPrincipal() {
		mainApp.getContenedorPrincipal().setLeft(null);
		mainApp.getContenedorPrincipal().setCenter(null);
		mainApp.showHome();
	}
}
