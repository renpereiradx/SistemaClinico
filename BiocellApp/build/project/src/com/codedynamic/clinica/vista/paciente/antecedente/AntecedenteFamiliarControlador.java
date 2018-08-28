package com.codedynamic.clinica.vista.paciente.antecedente;

import com.codedynamic.clinica.dao.postgresql.PSQLAntecedenteFamiliar;
import com.codedynamic.clinica.modelo.AntecedenteFamiliar;
import com.codedynamic.clinica.modelo.Paciente;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AntecedenteFamiliarControlador {
    @FXML private JFXTextField padreTextField;
    @FXML private JFXTextField madreTextField;
    @FXML private JFXTextField hijosTextField;
    @FXML private JFXTextField hermanosTextField;
    @FXML private JFXTextField abuelosPaternosTextField;
    @FXML private JFXTextField abuelosMaternosTextField;
    @FXML private JFXTextField vacunacionTextField;

    private Paciente paciente;
    private Stage stage;
    private boolean okClicked;
    private PSQLAntecedenteFamiliar psqlAntecedenteFamiliar;
    private AntecedenteFamiliar antecedenteFamiliar;

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML private void initialize() {}

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
        if (validacion()) {
            cargarDatos();
        }
    }

    @FXML
    private void aceptar() {
        psqlAntecedenteFamiliar = new PSQLAntecedenteFamiliar();
        AntecedenteFamiliar antecedenteFamiliarTemp = new AntecedenteFamiliar();
        if (!validacion()) {
            antecedenteFamiliarTemp.setPadre(padreTextField.getText());
            antecedenteFamiliarTemp.setMadre(madreTextField.getText());
            antecedenteFamiliarTemp.setHijos(hijosTextField.getText());
            antecedenteFamiliarTemp.setHermanos(hermanosTextField.getText());
            antecedenteFamiliarTemp.setAbuelosPaternos(abuelosPaternosTextField.getText());
            antecedenteFamiliarTemp.setAbuelosMaternos(abuelosMaternosTextField.getText());
            antecedenteFamiliarTemp.setVacunacion(vacunacionTextField.getText());
            antecedenteFamiliarTemp.setPaciente(paciente);
            psqlAntecedenteFamiliar.insertar(antecedenteFamiliarTemp);
            okClicked = true;
            stage.close();
        } else {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setHeaderText("ATENCION");
            alerta.setContentText("Paciente ya registro antecedentes, favor hacer click en MODIFICAR en caso de que necesite realizar cambios");
            alerta.initStyle(StageStyle.UTILITY);
            alerta.showAndWait();
        }
    }

    @FXML
    private void modificar() {
        psqlAntecedenteFamiliar = new PSQLAntecedenteFamiliar();
        AntecedenteFamiliar antecedenteFamiliarTemp = new AntecedenteFamiliar();
        if (validacion()) {
            antecedenteFamiliarTemp.setPadre(padreTextField.getText());
            antecedenteFamiliarTemp.setMadre(madreTextField.getText());
            antecedenteFamiliarTemp.setHijos(hijosTextField.getText());
            antecedenteFamiliarTemp.setHermanos(hermanosTextField.getText());
            antecedenteFamiliarTemp.setAbuelosPaternos(abuelosPaternosTextField.getText());
            antecedenteFamiliarTemp.setAbuelosMaternos(abuelosMaternosTextField.getText());
            antecedenteFamiliarTemp.setVacunacion(vacunacionTextField.getText());
            antecedenteFamiliarTemp.setPaciente(paciente);
            psqlAntecedenteFamiliar.modificar(antecedenteFamiliarTemp);
            okClicked = true;
            stage.close();
        } else {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setHeaderText("ATENCION");
            alerta.setContentText("Paciente ya registro antecedentes, favor hacer click en MODIFICAR en caso de que necesite realizar cambios");
            alerta.initStyle(StageStyle.UTILITY);
            alerta.showAndWait();
        }
    }

    @FXML
    private void salir() {
        stage.close();
        okClicked = true;
    }

    private void cargarDatos() {
        psqlAntecedenteFamiliar = new PSQLAntecedenteFamiliar();
        antecedenteFamiliar = psqlAntecedenteFamiliar.obtenerPorID(paciente);
        padreTextField.setText(antecedenteFamiliar.getPadre());
        madreTextField.setText(antecedenteFamiliar.getMadre());
        hijosTextField.setText(antecedenteFamiliar.getHijos());
        hermanosTextField.setText(antecedenteFamiliar.getHermanos());
        abuelosMaternosTextField.setText(antecedenteFamiliar.getAbuelosMaternos());
        abuelosPaternosTextField.setText(antecedenteFamiliar.getAbuelosPaternos());
        vacunacionTextField.setText(antecedenteFamiliar.getVacunacion());
    }

    private boolean validacion() {
        psqlAntecedenteFamiliar = new PSQLAntecedenteFamiliar();
        antecedenteFamiliar = psqlAntecedenteFamiliar.obtenerPorID(paciente);
        return antecedenteFamiliar != null;
    }
}
