package com.codedynamic.clinica.vista.proveedores;

import java.util.ArrayList;
import java.util.List;

import com.codedynamic.clinica.MainApp;
import com.codedynamic.clinica.dao.postgresql.PSQLProveedor;
import com.codedynamic.clinica.dao.postgresql.PSQLTelefonoProveedor;
import com.codedynamic.clinica.modelo.Proveedor;
import com.codedynamic.clinica.modelo.TelefonoProveedor;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.StageStyle;
import javafx.scene.control.Alert.AlertType;

public class ProveedorPrincipalControlador {
	
	@FXML
	private TableView<Proveedor> tablaProveedor;
	@FXML
	private TableColumn<Proveedor, Number> columnaCodigo;
	@FXML
	private TableColumn<Proveedor, String> columnaNombre;
	@FXML
	private Label codigoLabel;
	@FXML
	private Label nombreLabel;
	@FXML
	private Label rucLabel;
	@FXML
	private Label direccionLabel;
	@FXML
	private Label telefonoLabel;
	@FXML
	private JFXTextField nombreTextField;
	
	private PSQLProveedor psqlProveedor;
	private PSQLTelefonoProveedor psqlTelefonoProveedor;
	private MainApp mainApp;
	private ObservableList<Proveedor> listaProveedor = FXCollections.observableArrayList();
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	
	@FXML
	private void initialize() {
		mostrarDatosProveedor(null);
		mostrarTabla();
	}
	
	@FXML
	private void nuevoProveedor() {
		Proveedor proveedorTemp = new Proveedor();
		boolean okClicked = mainApp.mostrarRegistroProveedor("NUEVO", proveedorTemp);
		if (okClicked) {
			psqlProveedor = new PSQLProveedor();
			psqlProveedor.insertar(proveedorTemp);
			if (!proveedorTemp.getTelefonoProveedor().isEmpty()) {
				psqlTelefonoProveedor = new PSQLTelefonoProveedor();
				for (TelefonoProveedor proveedor : proveedorTemp.getTelefonoProveedor()) {
					psqlTelefonoProveedor.insertar(proveedor);
				}
			}
			listaProveedor.add(proveedorTemp);
			if (tablaProveedor.getSelectionModel().getSelectedIndex() < 0) {
				tablaProveedor.setItems(listaProveedor);
			}
		}
	}
	
	@FXML
	private void editarProveedor() {
		Proveedor proveedorSelec= tablaProveedor.getSelectionModel().getSelectedItem();
		if (proveedorSelec != null) {
			boolean okClicked = mainApp.mostrarRegistroProveedor("EDITAR", proveedorSelec);
			if (okClicked) {
				psqlProveedor = new PSQLProveedor();
				psqlProveedor.modificar(proveedorSelec);
				mostrarDatosProveedor(proveedorSelec);
			}
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("ATENCION");
			alert.setHeaderText("Proveedor no seleccionado");
			alert.setContentText("Selecciona un proveedor para modificarlo");
			alert.initStyle(StageStyle.DECORATED);
			alert.showAndWait();
		}
	}
	
	@FXML
	private void bucarProveedores() {
		psqlProveedor = new PSQLProveedor();
		if (!nombreTextField.getText().isEmpty()) {
			System.out.println(nombreTextField.getText());
			listaProveedor.addAll(psqlProveedor.obtenerPorNombre(nombreTextField.getText()));
			if (!listaProveedor.isEmpty()) {
				tablaProveedor.setItems(listaProveedor);
			}
		}
	}
	
	private void mostrarTabla() {
		columnaCodigo.setCellValueFactory(datoCelda -> datoCelda.getValue().idProveedorProperty());
		columnaNombre.setCellValueFactory(datoCelda -> datoCelda.getValue().nombreProperty());
		tablaProveedor.getSelectionModel().selectedItemProperty().addListener((observable, oldvalue, newvalue) -> mostrarDatosProveedor(newvalue));
	}
	
	private void mostrarDatosProveedor(Proveedor proveedor) {
		if (proveedor != null) {
			codigoLabel.setText(Integer.toString(proveedor.getIdProveedor()));
			nombreLabel.setText(proveedor.getNombre());
			rucLabel.setText(proveedor.getRuc());
			direccionLabel.setText(proveedor.getDireccion());
			if (!proveedor.getTelefonoProveedor().isEmpty()) {
				String telefono = "";
				for (TelefonoProveedor telefonoProveedor : proveedor.getTelefonoProveedor()) {
					telefono += telefonoProveedor.getTelefono() + " - ";
				}
				telefonoLabel.setText(telefono);
			} else {
				psqlTelefonoProveedor = new PSQLTelefonoProveedor();
				List<TelefonoProveedor> telefonos = psqlTelefonoProveedor.obtenerLista((short) proveedor.getIdProveedor());
				String telefono = "";
				if (!telefonos.isEmpty()) {
					for (TelefonoProveedor telefonoProveedor : telefonos) {
						telefono += telefonoProveedor.getTelefono() + " - ";
						proveedor.setTelefonoProveedor(telefonoProveedor);
					}
					telefonoLabel.setText(telefono);
				}
			}
		} else {
			codigoLabel.setText("");
			nombreLabel.setText("");
			rucLabel.setText("");
			direccionLabel.setText("");
			telefonoLabel.setText("");
		}
	}
}
