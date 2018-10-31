package com.codedynamic.clinica.vista.proveedores;

import java.util.List;

import com.codedynamic.clinica.dao.postgresql.PSQLProveedor;
import com.codedynamic.clinica.dao.postgresql.PSQLTelefonoProveedor;
import com.codedynamic.clinica.modelo.Proveedor;
import com.codedynamic.clinica.modelo.TelefonoProveedor;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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
	
	private Proveedor proveedor;
	private PSQLProveedor psqlProveedor;
	private PSQLTelefonoProveedor psqlTelefono;
	
	
	@FXML
	private void initialize() {
		mostrarTabla();
	}
	
	@FXML
	private void nuevoProveedor() {
		
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
			mostrarDatosTelefono(proveedor);
		} else {
			codigoLabel.setText("");
			nombreLabel.setText("");
			rucLabel.setText("");
			direccionLabel.setText("");
			telefonoLabel.setText("");
		}
	}
	
	private void mostrarDatosTelefono(Proveedor proveedor) {
		psqlTelefono = new PSQLTelefonoProveedor();
		List<TelefonoProveedor> telefonoProveedors = psqlTelefono.obtenerLista((short) proveedor.getIdProveedor());
		String listaTelefono = "";
		if (!telefonoProveedors.isEmpty()) {
			for (TelefonoProveedor proveedor2 : telefonoProveedors) {
				listaTelefono += proveedor2.getTelefono() + "   ";
			} 
		}
		telefonoLabel.setText(listaTelefono);
	}
	
}
