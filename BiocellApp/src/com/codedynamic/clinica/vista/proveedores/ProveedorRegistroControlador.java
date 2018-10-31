package com.codedynamic.clinica.vista.proveedores;

import com.codedynamic.clinica.MainApp;
import com.codedynamic.clinica.dao.postgresql.PSQLTelefonoProveedor;
import com.codedynamic.clinica.modelo.Proveedor;
import com.codedynamic.clinica.modelo.TelefonoProveedor;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
	private JFXTextField direccionField;
	@FXML
	private JFXTextField telefonoField;
	@FXML
	private JFXListView<TelefonoProveedor> listaTelefono;
	
	private Proveedor proveedor;
	private MainApp mainApp;
	private Stage stage;
	private boolean okClicked = false;
	private String accion;
	private PSQLTelefonoProveedor psqlTelefonoProveedor;
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	public boolean isOkClicked() {
		return okClicked;
	}
	
	public void setProveedor(Proveedor proveedor, String accion) {
		this.proveedor = proveedor;
		this.accion = accion;
		if (accion.equals("EDITAR")) {
			nombreField.setText(proveedor.getNombre());
			rucField.setText(proveedor.getRuc());
			direccionField.setText(proveedor.getDireccion());
			psqlTelefonoProveedor = new PSQLTelefonoProveedor();
			listaTelefono.setItems(FXCollections
					.observableArrayList(psqlTelefonoProveedor.obtenerPorID((short) proveedor.getIdProveedor())));
		}
	}
	
	@FXML
	private void initialize() {
		
	}
	
	@FXML
	private void aceptar() {
		if (accion.equals("NUEVO")) {
			proveedor.setNombre(nombreField.getText());
			proveedor.setRuc(rucField.getText());
			proveedor.setDireccion(direccionField.getText());
			proveedor.setTelefonoProveedor(listaTelefono.getItems());
			okClicked = true;
			stage.close();
		}
	}
	
	private void insertarTelefono() {
		
	}
	
	private boolean validacion() {
		String error = "";
		if (nombreField.getText().isEmpty() || nombreField.getText() == null) {
			error += "Nombre de Proveedor vacio \n";
		}
		if (error.length() == 0) {
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
