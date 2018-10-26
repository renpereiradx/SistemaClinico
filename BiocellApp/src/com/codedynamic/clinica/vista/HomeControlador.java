package com.codedynamic.clinica.vista;

import com.codedynamic.clinica.MainApp;
import javafx.fxml.FXML;

public class HomeControlador {

    private boolean okClicked = false;
    private String seccion;
    private MainApp mainApp;

    public boolean isOkClicked() {
        return okClicked;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML private void initializable() {}

    @FXML private void seccionPaciente() {
        okClicked = true;
        seccion = "PACIENTE";
        mainApp.getContenedorPrincipal().setCenter(null);
        mainApp.mostrarPacienteMenuLeft();
        mainApp.showPacienteDescripcion();
    }

    @FXML private void seccionTurno() {
        mainApp.getContenedorPrincipal().setCenter(null);
        mainApp.mostrarTurnoMenuLeft();
        mainApp.mostrarTurnoPrincipal();
    }
    
    @FXML private void seccionMedicos() {
    	//mainApp.getContenedorPrincipal().setCenter(null);
    	mainApp.mostrarMedicoPrincipal();
    	mainApp.mostrarMedicoMenu();
    }
    
    @FXML private void seccionProducto() {
    	//mainApp.getContenedorPrincipal().setCenter(null);
    	mainApp.mostrarProductoMenu();
    	mainApp.mostrarProductoPrincial();
    }
}