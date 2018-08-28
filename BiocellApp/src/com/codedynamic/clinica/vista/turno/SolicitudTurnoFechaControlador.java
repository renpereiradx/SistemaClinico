package com.codedynamic.clinica.vista.turno;

import com.codedynamic.clinica.modelo.Turno;
import com.jfoenix.controls.JFXDatePicker;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class SolicitudTurnoFechaControlador {
    @FXML private JFXDatePicker fechaDatePicker;

    private Turno turno;
    private Stage stage;
    private boolean okClicked = false;

    public void setTurno(Turno turno) {
        this.turno = turno;
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void initialize() {

    }

    @FXML
    private void selectAceptar() {
        turno.setFecha(fechaDatePicker.getValue());
        okClicked = true;
        stage.close();
    }

    @FXML
    private void selectCancelar() {
        stage.close();
    }
}
