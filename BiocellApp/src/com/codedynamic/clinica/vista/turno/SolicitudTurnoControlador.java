package com.codedynamic.clinica.vista.turno;

import com.codedynamic.clinica.dao.postgresql.PSQLPaciente;
import com.codedynamic.clinica.modelo.Paciente;
import com.codedynamic.clinica.modelo.Turno;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalTime;

public class SolicitudTurnoControlador {
    @FXML private JFXTextField pacienteJfxTextField;
    @FXML private JFXComboBox<Paciente> pacienteComboBox;
    @FXML private JFXTextField turnoTextField;
    @FXML private JFXTextField descripcionTextField;

    private PSQLPaciente psqlPaciente;
    private Turno turno;
    private Stage stage;
    private boolean okClicked = false;

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    private void initialize() {}

    @FXML
    private void selectAceptar() {
        psqlPaciente = new PSQLPaciente();
        Paciente paciente = psqlPaciente.obtenerPorID(pacienteComboBox.getSelectionModel().getSelectedItem().getId_paciente());
        if (validarDatos()) {
            turno.setNumero(Integer.parseInt(turnoTextField.getText()));
            turno.setFecha(LocalDate.now());
            turno.setPaciente(paciente);
            turno.setHora(LocalTime.now());
            turno.setDescripcion(descripcionTextField.getText());
            okClicked = true;
            stage.close();
        }
    }

    @FXML
    private void selectCancelar() {
        this.stage.close();
    }

    private boolean validarDatos() {
        psqlPaciente = new PSQLPaciente();
        ObservableList<Paciente> listaNombre = FXCollections.observableArrayList(psqlPaciente.listarBusqueda(pacienteJfxTextField.getText()));
        ObservableList<Paciente> listaApellido = FXCollections.observableArrayList(psqlPaciente.listarBusqApe(pacienteJfxTextField.getText()));
        if (!listaNombre.isEmpty()) {
            pacienteComboBox.setItems(listaNombre);
            pacienteComboBox.show();
            return true;
        } else if (!listaApellido.isEmpty()) {
            pacienteComboBox.setItems(listaApellido);
            pacienteComboBox.show();
            return true;
        } else {
            return false;
        }

    }

    @FXML private void pacienteTextFieldEvento() {
        validarDatos();
    }
}

