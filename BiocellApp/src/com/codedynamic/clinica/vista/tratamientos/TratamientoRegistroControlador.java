package com.codedynamic.clinica.vista.tratamientos;

import com.codedynamic.clinica.modelo.Tratamiento;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.StageStyle;

public class TratamientoRegistroControlador {

	@FXML
	private JFXTextField nombreField;
	@FXML
	private JFXTextField precioField;
	@FXML
	private JFXTextField descripcionField;
	
	private Tratamiento tratamiento;
	private boolean okClicked = false;
	
	public void setTratamiento(Tratamiento tratamiento) {
		this.tratamiento = tratamiento;
		if (tratamiento != null) {
			if (!(tratamiento.getNombre().isEmpty() && tratamiento.getNombre() == null)) {
				nombreField.setText(tratamiento.getNombre());
			}
			if (tratamiento.getPrecio() != 0) {
				precioField.setText(Integer.toString(tratamiento.getPrecio()));
			}
			if (tratamiento.getDescripcion().length() > 0 && tratamiento.getDescripcion() != null) {
				descripcionField.setText(tratamiento.getDescripcion());
			}
		}
	}
	
	public boolean isOkClicked() {
		return okClicked;
	}
	
	@FXML
	private void initialize() {
		
	}
	
	@FXML
	
	private boolean validacionDatos() {
		String error = "";
		if (nombreField.getText().isEmpty() || nombreField.getText() == null) {
			error += "Favor completar el campo nombre\n";
		}
		if (precioField.getText().isEmpty() || precioField.getText() == null) {
			error += "Favor completar el campo precio\n";
		}if (error.length() == 0) {
			return true;
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Error en unos de los campos");
			alert.setContentText(error);
			alert.setTitle("ATENCION");
			alert.initStyle(StageStyle.DECORATED);
			alert.showAndWait();
			return false;
		}
	}
}