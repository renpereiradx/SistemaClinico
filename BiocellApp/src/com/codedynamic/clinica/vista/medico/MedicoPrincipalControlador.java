package com.codedynamic.clinica.vista.medico;

import java.time.LocalDate;
import java.time.LocalTime;

import com.codedynamic.clinica.MainApp;
import com.codedynamic.clinica.dao.postgresql.PSQLAtencion;
import com.codedynamic.clinica.dao.postgresql.PSQLTurno;
import com.codedynamic.clinica.modelo.Atencion;
import com.codedynamic.clinica.modelo.Turno;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.StageStyle;

public class MedicoPrincipalControlador {
	
	@FXML
	private TableView<Turno> turnoTableView;
	@FXML
	private TableColumn<Turno, Number> numeroTableColumn;
	@FXML
	private TableColumn<Turno, String> pNombreTableColumn;
	@FXML
	private TableColumn<Turno, String> pApellidoTableColumn;
	@FXML
	private TableColumn<Turno, LocalTime> horaTableColumn;
	@FXML
	private TableColumn<Turno, LocalDate> fechaTableColumn;
	@FXML
	private TableColumn<Turno, String> descripcionTableColumn;
	@FXML
	private TableColumn<Turno, String> estadoTableColumn;
	@FXML
	private JFXTextField motivoField;
	@FXML
	private JFXComboBox<String> tipoAtencioComboBox;
	
	
	private MainApp mainApp;
	private PSQLTurno psqlTurno;
	private Atencion atencion;
	private ObservableList<Turno> turnos = FXCollections.observableArrayList();
	private ObservableList<String> listaTipoAtenciones = FXCollections.observableArrayList("MEDICO", "ESTETICA", "ENFERMERIA");
	
	public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    
    @FXML
    private void initialize() {
        numeroTableColumn.setCellValueFactory(datoCelda -> datoCelda.getValue().numeroProperty());
        pNombreTableColumn.setCellValueFactory(datoCelda -> datoCelda.getValue().getPaciente().nombresProperty());
        pApellidoTableColumn.setCellValueFactory(datoCelda -> datoCelda.getValue().getPaciente().apellidosProperty());
        horaTableColumn.setCellValueFactory(datoCelda -> datoCelda.getValue().horaProperty());
        fechaTableColumn.setCellValueFactory(datoCelda -> datoCelda.getValue().fechaProperty());
        descripcionTableColumn.setCellValueFactory(datoCelda -> new ReadOnlyStringWrapper(datoCelda.getValue().getDescripcion()));
        estadoTableColumn.setCellValueFactory(datoCelda -> datoCelda.getValue().estadoProperty());
        mostrarTurnosHoy();
        tipoAtencioComboBox.getItems().addAll(listaTipoAtenciones);
    }
    
    @FXML
    private void mostrarTurnosHoy() {
            limpiarTablaTurnos();
            psqlTurno = new PSQLTurno();
            int hoy = LocalDate.now().getDayOfMonth();
            int mes = LocalDate.now().getMonthValue();
            for (Turno turno : psqlTurno.listar()) {
                if (mes == turno.getFecha().getMonthValue() && hoy == turno.getFecha().getDayOfMonth()) {
                    turnos.add(turno);
                }
            }
            turnoTableView.setItems(turnos);
    }

    @FXML
    private void atenderTurno() {
    	PSQLAtencion psqlAtencion = new PSQLAtencion();
    	atencion = new Atencion();
    	int selectIndex = turnoTableView.getSelectionModel().getSelectedIndex();
    	if (motivoField.getText().length() > 0) {
			atencion.setMotivo(motivoField.getText());
		} else {
			atencion.setMotivo("No especificado");
		}
		atencion.setUsuario(mainApp.getUsuarioLoggeado());
    	if (tipoAtencioComboBox.getValue() != null) {
			if (tipoAtencioComboBox.getValue().equals("MEDICO")) {
				atencion.setIdTipoAtencion((short) 1);
			} 
		}
		if (atencion != null && selectIndex >= 0) {
        	atencion.setTurno(turnoTableView.getSelectionModel().getSelectedItem());
    		psqlAtencion.insertar(atencion);
			mainApp.getContenedorPrincipal().setCenter(null);
			mainApp.mostrarRegistroDatosMedicos(atencion);
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROR");
			alert.setHeaderText("ERROR EN REGISTRO");
			alert.setContentText("No se pudo registrar ningun dato, favor asegurese de completar los campos y seleccionar un Paciente en la tabla TURNOS");
			alert.initStyle(StageStyle.DECORATED);
			alert.showAndWait();
		}
    }
    
    private void limpiarTablaTurnos() {
        turnoTableView.getItems().clear();
    }
}
