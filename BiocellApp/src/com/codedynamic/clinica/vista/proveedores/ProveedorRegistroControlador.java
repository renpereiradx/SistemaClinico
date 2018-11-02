package com.codedynamic.clinica.vista.proveedores;

import com.codedynamic.clinica.dao.postgresql.PSQLTelefonoProveedor;
import com.codedynamic.clinica.modelo.Proveedor;
import com.codedynamic.clinica.modelo.TelefonoProveedor;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
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
	private Stage stage;
	private boolean okClicked = false;
	private String accion;
	private PSQLTelefonoProveedor psqlTelefonoProveedor;
	private ObservableList<TelefonoProveedor> telefonoLista = FXCollections.observableArrayList();
	
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
			telefonoLista.addAll(proveedor.getTelefonoProveedor());
			cargarListaTelefono();
		}
	}
	
	@FXML
	private void initialize() {
		listaTelefono.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent click) {
				if (!listaTelefono.getItems().isEmpty()) {
					if (click.getClickCount() == 2) {
						TelefonoProveedor telefonoProveedor = listaTelefono.getSelectionModel().getSelectedItem();
						telefonoField.setText("");
						telefonoField.setText(telefonoProveedor.getTelefono());
					} 
				} else {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("ATENCION");
					alert.setHeaderText("ATENCION");
					alert.setContentText("Asegurese de que la tabla este cargada antes de hacer doble click");
					alert.initStyle(StageStyle.DECORATED);
					alert.showAndWait();
				}
			}
		});
	}
	
	@FXML
	private void aceptar() {
		if (validacion()) {
			proveedor.setNombre(nombreField.getText());
			proveedor.setRuc(rucField.getText());
			proveedor.setDireccion(direccionField.getText());
			okClicked = true;
			stage.close();
		}
	}

	@FXML
	private void cancelar() {
		stage.close();
	}
	
	@FXML
	private void insertarTelefono() {
		if (telefonoField.getText().length() >= 6) {
			TelefonoProveedor telefonoProveedor = new TelefonoProveedor();
			telefonoProveedor.setProveedor(proveedor);
			telefonoProveedor.setTelefono(telefonoField.getText());
			if (accion.equals("NUEVO")) {
				proveedor.setTelefonoProveedor(telefonoProveedor);
				telefonoLista.add(telefonoProveedor);
				cargarListaTelefono();
			} else {
				psqlTelefonoProveedor = new PSQLTelefonoProveedor();
				psqlTelefonoProveedor.insertar(telefonoProveedor);
				proveedor.setTelefonoProveedor(telefonoProveedor);
				telefonoLista.add(telefonoProveedor);
			}
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("ATENCION");
			alert.setContentText("Favor introducir numero de telefono, correctamente");
			alert.setHeaderText("Cuidado con el numero de telefono");
			alert.initStyle(StageStyle.DECORATED);
			alert.showAndWait();
		}
	}
	
	@FXML
	private void modificarTelefono() {
		TelefonoProveedor telefonoProveedor = listaTelefono.getSelectionModel().getSelectedItem();
		psqlTelefonoProveedor = new PSQLTelefonoProveedor();
		if (telefonoProveedor != null) {
			telefonoLista.remove(telefonoProveedor);
			psqlTelefonoProveedor.modificarTelefono(telefonoField.getText(), telefonoProveedor);	
			telefonoLista.add(telefonoProveedor);
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("ATENCION");
			alert.setHeaderText("Inconveniente al editar");
			alert.setContentText("No se ha seleccionado un telefono en la lista");
			alert.initStyle(StageStyle.DECORATED);
			alert.showAndWait();
		}
	}
	
	@FXML
	private void eliminarTelefono() {
		psqlTelefonoProveedor = new PSQLTelefonoProveedor();
		int index = listaTelefono.getSelectionModel().getSelectedIndex();
		TelefonoProveedor telefonoProveedor = listaTelefono.getSelectionModel().getSelectedItem();
		if (index >= 0) {
			if (accion.equals("EDITAR")) {
				psqlTelefonoProveedor.eliminar(telefonoProveedor);
			}
			telefonoLista.remove(telefonoProveedor);
			proveedor.getTelefonoProveedor().remove(telefonoProveedor);
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("ATENCION");
			alert.setHeaderText("ATENCION");
			alert.setContentText("Asegurese de seleccionar un elemento");
			alert.initStyle(StageStyle.DECORATED);
			alert.showAndWait();
		}
	}
	
	private void cargarListaTelefono() {
		if (!telefonoLista.isEmpty()) {
			listaTelefono.setItems(telefonoLista);
		}
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
