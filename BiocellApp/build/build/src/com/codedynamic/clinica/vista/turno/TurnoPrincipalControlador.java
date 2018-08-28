package com.codedynamic.clinica.vista.turno;

import com.codedynamic.clinica.MainApp;
import com.codedynamic.clinica.dao.postgresql.PSQLTurno;
import com.codedynamic.clinica.modelo.Turno;
import com.codedynamic.clinica.utilidades.UtilidadFecha;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.StageStyle;

import java.time.LocalDate;
import java.time.LocalTime;

public class TurnoPrincipalControlador {
    @FXML private TableView<Turno> turnoTableView;
    @FXML private TableColumn<Turno, Number> numeroTableColumn;
    @FXML private TableColumn<Turno, String> pNombreTableColumn;
    @FXML private TableColumn<Turno, String> pApellidoTableColumn;
    @FXML private TableColumn<Turno, LocalTime> horaTableColumn;
    @FXML private TableColumn<Turno, LocalDate> fechaTableColumn;
    @FXML private TableColumn<Turno, String> estadoTableColumn;


    private MainApp mainApp;
    private PSQLTurno psqlTurno;
    private ObservableList<Turno> turnos = FXCollections.observableArrayList();

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    
    public ObservableList<Turno> getTurnos() {
        return turnos;
    }

    @FXML
    private void initialize() {
        numeroTableColumn.setCellValueFactory(datoCelda -> datoCelda.getValue().numeroProperty());
        pNombreTableColumn.setCellValueFactory(datoCelda -> datoCelda.getValue().getPaciente().nombresProperty());
        pApellidoTableColumn.setCellValueFactory(datoCelda -> datoCelda.getValue().getPaciente().apellidosProperty());
        horaTableColumn.setCellValueFactory(datoCelda -> datoCelda.getValue().horaProperty());
        fechaTableColumn.setCellValueFactory(datoCelda -> datoCelda.getValue().fechaProperty());
        estadoTableColumn.setCellValueFactory(datoCelda -> datoCelda.getValue().estadoProperty());
        mostrarTurnosHoy();
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
    private void gestionarNuevoTurno() {
        Turno turnoTemp = new Turno();
        psqlTurno = new PSQLTurno();
        boolean okClicked = mainApp.mostrarDialogSolicitudTurno(turnoTemp);
        if (okClicked) {
            turnos.add(turnoTemp);
            if (turnoTableView.getSelectionModel().getSelectedIndex() < 0) {
                turnoTableView.setItems(turnos);
            }
            psqlTurno.insertar(turnoTemp);
        }
    }

    @FXML
    private void gestionarFechaTurno() {
        limpiarTablaTurnos();
        Turno turnoTemp = new Turno();
        psqlTurno = new PSQLTurno();
        boolean okClicked = mainApp.mostrarDialogoTurnoFecha(turnoTemp);
        if (okClicked) {
            String fecha = UtilidadFecha.formato(turnoTemp.getFecha());
            for (Turno turno : psqlTurno.listar()) {
                if (UtilidadFecha.formato(turno.getFecha()).equals(fecha)) {
                    turnos.add(turno);
                }
            }
            if (turnoTableView.getSelectionModel().getSelectedIndex() < 0) {
                turnoTableView.setItems(turnos);
            }
        }
    }
    
    @FXML
    private void mostrarDescripcion() {
    	Turno turnoTemp = turnoTableView.getSelectionModel().getSelectedItem();
    	int selectedIndex = turnoTableView.getSelectionModel().getSelectedIndex();
    	System.out.println(selectedIndex);
    	if (selectedIndex >= 0) {
			mainApp.mostrarDialogoTurnoDescripcion(turnoTemp);
		} else {
			Alert alerta = new Alert(AlertType.ERROR);
			alerta.setTitle("ERROR");
			alerta.setHeaderText("Error al intentar mostrar Descripcion");
			alerta.setContentText("Turno no seleccionado. Por favor seleccione un turno para mostrar");
			alerta.initModality(Modality.WINDOW_MODAL);
			alerta.initStyle(StageStyle.UTILITY);
		}
    }

    private void limpiarTablaTurnos() {
        turnoTableView.getItems().clear();
    }
}