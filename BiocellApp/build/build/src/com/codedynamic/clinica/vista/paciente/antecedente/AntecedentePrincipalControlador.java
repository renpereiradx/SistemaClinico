package com.codedynamic.clinica.vista.paciente.antecedente;

import com.codedynamic.clinica.MainApp;
import com.codedynamic.clinica.dao.postgresql.PSQLEnfermedades;
import com.codedynamic.clinica.dao.postgresql.PSQLPaciente;
import com.codedynamic.clinica.modelo.AntecedentePersonal;
import com.codedynamic.clinica.modelo.Paciente;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

public class AntecedentePrincipalControlador {
    @FXML private JFXTextField pacienteJfxTextField;
    @FXML private JFXListView<Paciente> listaPacienteListView;

    private MainApp mainApp;
    private AntecedentePersonal antecedentePersonal;
    private PSQLEnfermedades enfermedades;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML private void initialize(){}

    @FXML
    private void buscarPacientes() {
        PSQLPaciente psqlPaciente = new PSQLPaciente();
        ObservableList<Paciente> listaNombres = psqlPaciente.listarBusqueda(pacienteJfxTextField.getText());
        ObservableList<Paciente> listaApellidos = FXCollections.observableArrayList(psqlPaciente.listarBusqApe(pacienteJfxTextField.getText()));
        if (!listaNombres.isEmpty()) {
            listaPacienteListView.setItems(listaNombres);
        } else if (!listaApellidos.isEmpty()) {
            listaPacienteListView.setItems(listaApellidos);
        }
    }

    @FXML
    private void verAntecedente() {
        Paciente selectPaciente = listaPacienteListView.getSelectionModel().getSelectedItem();
        if (selectPaciente != null && selectPaciente.getSexo().equals("F")) {
            mainApp.getContenedorPrincipal().setCenter(null);
            mainApp.mostrarAntecedenteFemenino(selectPaciente);
        } else if (selectPaciente != null && selectPaciente.getSexo().equals("M")) {
            mainApp.getContenedorPrincipal().setCenter(null);
            mainApp.mostrarAntecedenteMasculino(selectPaciente);
        } else {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("ATENCION");
            alerta.setHeaderText("Paciente no seleccionado");
            alerta.setContentText("Por favor selecciona un paciente");
            alerta.initStyle(StageStyle.UTILITY);
            alerta.showAndWait();
        }
    }

    private void obtenerAncedentes() {
        enfermedades = new PSQLEnfermedades();
        antecedentePersonal.setListaEnfermedades(enfermedades.listarEnfermedades());
    }
}

