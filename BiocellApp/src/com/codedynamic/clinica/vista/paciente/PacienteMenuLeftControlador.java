package com.codedynamic.clinica.vista.paciente;

import com.codedynamic.clinica.MainApp;
import javafx.fxml.FXML;

public class PacienteMenuLeftControlador {
    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void initialize() {

    }

    @FXML
    private void mostrarDescripcion() {
        mainApp.showPacienteDescripcion();
    }

    @FXML
    private void mostrarAntecedentes() {
        mainApp.mostrarAntecedentePrincipal();
    }

    @FXML
    private void mostrarHome() {
        mainApp.getContenedorPrincipal().setLeft(null);
        mainApp.getContenedorPrincipal().setCenter(null);
        mainApp.showHome();
    }
}
