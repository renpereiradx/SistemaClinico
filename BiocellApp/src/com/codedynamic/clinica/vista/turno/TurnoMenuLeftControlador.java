package com.codedynamic.clinica.vista.turno;

import com.codedynamic.clinica.MainApp;
import javafx.fxml.FXML;

public class TurnoMenuLeftControlador {
    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void initialize() { }

    @FXML
    private void mostrarTurnoPrincipal() {
        mainApp.mostrarTurnoPrincipal();
    }

    @FXML
    private void mostrarHome() {
        mainApp.getContenedorPrincipal().setLeft(null);
        mainApp.getContenedorPrincipal().setCenter(null);
        mainApp.showHome();
    }
}
