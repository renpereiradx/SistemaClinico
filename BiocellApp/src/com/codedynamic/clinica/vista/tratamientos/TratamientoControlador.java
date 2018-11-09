package com.codedynamic.clinica.vista.tratamientos;

import com.codedynamic.clinica.MainApp;
import com.codedynamic.clinica.dao.postgresql.PSQLTratamiento;
import com.codedynamic.clinica.modelo.Tratamiento;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.StageStyle;

public class TratamientoControlador {

	@FXML
	private TableView<Tratamiento> tablaTratamiento;
	@FXML
	private TableColumn<Tratamiento, Number> columnaCodigo;
	@FXML
	private TableColumn<Tratamiento, String> columnaNombre;
	@FXML
	private TableColumn<Tratamiento, Number> columnaPrecio;
	@FXML
	private TableColumn<Tratamiento, String> columnaDescripcion;
	@FXML
	private JFXTextField nombreTratamiento;
	
	private MainApp mainApp;
	private PSQLTratamiento psqlTratamiento;
	private ObservableList<Tratamiento> listaTratamientos = FXCollections.observableArrayList();
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	@FXML
	private void initialize() {
		columnaCodigo.setCellValueFactory(datoCelda -> datoCelda.getValue().idTratamientoProperty());
		columnaNombre.setCellValueFactory(datoCelda -> datoCelda.getValue().nombreProperty());
		columnaPrecio.setCellValueFactory(datoCelda -> datoCelda.getValue().precioProperty());
		columnaDescripcion.setCellValueFactory(datoCelda -> datoCelda.getValue().descripcionProperty());
	}
	
	@FXML
	private void nuevo() {
		Tratamiento tratamientoTemp = new Tratamiento();
		boolean okClicked = mainApp.mostrarRegistroTratamiento(tratamientoTemp);
		if (okClicked) {
			psqlTratamiento = new PSQLTratamiento();
			listaTratamientos.add(tratamientoTemp);
			if (tablaTratamiento.getSelectionModel().getSelectedIndex() < 0) {
				tablaTratamiento.setItems(listaTratamientos);
			}
			psqlTratamiento.insertar(tratamientoTemp);	
		}
	}
	
	@FXML
	private void editar() {
		Tratamiento tratamientoSelect = tablaTratamiento.getSelectionModel().getSelectedItem();
		if (tratamientoSelect != null) {
			boolean okClicked = mainApp.mostrarRegistroTratamiento(tratamientoSelect);
			if (okClicked) {
				psqlTratamiento = new PSQLTratamiento();
				psqlTratamiento.modificar(tratamientoSelect);
			}
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("ATENCION");
			alert.setHeaderText("Paciente no seleccionado");
			alert.setContentText("Favor seleccionar Tratamiento en la tabla");
			alert.initStyle(StageStyle.DECORATED);
			alert.showAndWait();
		}
	}
	
	@FXML
	private void eliminar() {
		int index = tablaTratamiento.getSelectionModel().getFocusedIndex();
		Tratamiento tratamiento = tablaTratamiento.getSelectionModel().getSelectedItem();
		if (index >= 0) {
			psqlTratamiento = new PSQLTratamiento();
			psqlTratamiento.eliminar(tratamiento);
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("ATENCION");
			alert.setHeaderText("Tratamiento no seleccionado");
			alert.setContentText("Favor seleccionar TRATAMIENTO en la tabla, o asegurece que la tabla no este vacia");
			alert.initStyle(StageStyle.DECORATED);
			alert.showAndWait();
		}
	}
	
	@FXML
	private void listarTratamientos() {
		psqlTratamiento = new PSQLTratamiento();
		for (Tratamiento t : psqlTratamiento.obtenerTodo()) {
			listaTratamientos.add(t);
		}
		if (tablaTratamiento.getItems().isEmpty()) {
			tablaTratamiento.setItems(listaTratamientos);
		} else {
			tablaTratamiento.getItems().removeAll(listaTratamientos);
			tablaTratamiento.setItems(listaTratamientos);
		}
	}
	
	@FXML
	private void buscar() {
		psqlTratamiento = new PSQLTratamiento();
		for (Tratamiento t : psqlTratamiento.obtenerPorNombre(nombreTratamiento.getText())) {
			listaTratamientos.add(t);
		}
		if (tablaTratamiento.getItems().isEmpty()) {
			tablaTratamiento.setItems(listaTratamientos);
		} else {
			tablaTratamiento.getItems().removeAll(listaTratamientos);
			tablaTratamiento.setItems(listaTratamientos);
		}
	}
}
