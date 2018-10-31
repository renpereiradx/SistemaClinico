package com.codedynamic.clinica.vista.proveedores;

import com.codedynamic.clinica.MainApp;
import com.codedynamic.clinica.modelo.Proveedor;
import com.codedynamic.clinica.modelo.TelefonoProveedor;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ProveedorRegistroControlador {

	@FXML
	private JFXTextField nombreField;
	@FXML
	private JFXTextField rucField;
	@FXML
	private JFXTextField descripcionField;
	@FXML
	private JFXTextField telefonoField;
	@FXML
	private JFXListView<TelefonoProveedor> listaTelefono;
	
	private Proveedor proveedor;
	private MainApp mainApp;
	private Stage stage;
	private boolean okClicked = false;
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	public boolean isOkClicked() {
		return okClicked;
	}
	
	@FXML
	private void initialize() {
		
	}
	
	private boolean validacion() {
		String error = "";
		if (nombreField.getText().isEmpty() || nombreField.getText() == null) {
			error += "Nombre de Proveedor vacio \n";
		}
		if (error.length() > 0) {
			return true;
		} else {
			Alert alerta = new Alert(AlertType.ERROR);
			alerta.setTitle("Error");
			alerta.setHeaderText("Error al validar");
			alerta.setContentText(error);
			alerta.initStyle(StageStyle.DECORATED);
			alerta.showAndWait();
			return false;
		}
	}
}
