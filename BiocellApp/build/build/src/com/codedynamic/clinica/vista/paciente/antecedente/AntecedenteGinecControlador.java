package com.codedynamic.clinica.vista.paciente.antecedente;

import com.codedynamic.clinica.dao.postgresql.PSQLAntecedenteFemenino;
import com.codedynamic.clinica.modelo.AntecedenteFemenino;
import com.codedynamic.clinica.modelo.Paciente;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AntecedenteGinecControlador {
    @FXML private JFXTextField edadMenarcaTextField;
    @FXML private JFXTextField edadClimaterioTextField;
    @FXML private JFXTextField menopausiaTextField;
    @FXML private JFXTextField fumTextField;
    @FXML private JFXTextField terapiaAnticonceptivaTextField;
    @FXML private JFXTextField inicioSexualTextField;
    @FXML private JFXTextField nroParejaTextField;
    @FXML private JFXTextField cirugiaPelvaniaTextField;
    @FXML private JFXTextField papTextField;
    @FXML private JFXTextField fechaTextField;
    @FXML private JFXTextField mamografiaTextField;
    @FXML private JFXTextField estJfxTextField;

    private Paciente paciente;
    private Stage stage;
    private boolean okClicked;

    public boolean isOkClicked() {
        return okClicked;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML private void initialize(){}

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
        if (validacion()) {
            llenarCampos();
        }
    }

    private void llenarCampos() {
        PSQLAntecedenteFemenino psqlAntecedenteFemenino = new PSQLAntecedenteFemenino();
        AntecedenteFemenino antecedenteFemenino = psqlAntecedenteFemenino.obtenerPorID(paciente);
        edadMenarcaTextField.setText(antecedenteFemenino.getEdadMenarca());
        edadClimaterioTextField.setText(antecedenteFemenino.getEdadClimaterio());
        menopausiaTextField.setText(antecedenteFemenino.getMenopausia());
        fumTextField.setText(antecedenteFemenino.getFum());
        terapiaAnticonceptivaTextField.setText(antecedenteFemenino.getTerapiaAnticonceptivo());
        inicioSexualTextField.setText(antecedenteFemenino.getInicioSexual());
        nroParejaTextField.setText(antecedenteFemenino.getNroPareja());
        cirugiaPelvaniaTextField.setText(antecedenteFemenino.getCirugiaPelvania());
        papTextField.setText(antecedenteFemenino.getPap());
        fechaTextField.setText(antecedenteFemenino.getFecha());
        mamografiaTextField.setText(antecedenteFemenino.getMamografia());
        estJfxTextField.setText(antecedenteFemenino.getEst());
    }

    @FXML
    private void aceptar() {
        if (!validacion()) {
            PSQLAntecedenteFemenino psqlAntecedenteFemenino = new PSQLAntecedenteFemenino();
            AntecedenteFemenino antecedenteFemenino = new AntecedenteFemenino();
            antecedenteFemenino.setEdadMenarca(edadMenarcaTextField.getText());
            antecedenteFemenino.setEdadClimaterio(edadClimaterioTextField.getText());
            antecedenteFemenino.setMenopausia(menopausiaTextField.getText());
            antecedenteFemenino.setFum(fumTextField.getText());
            antecedenteFemenino.setTerapiaAnticonceptivo(terapiaAnticonceptivaTextField.getText());
            antecedenteFemenino.setInicioSexual(inicioSexualTextField.getText());
            antecedenteFemenino.setNroPareja(nroParejaTextField.getText());
            antecedenteFemenino.setCirugiaPelvania(cirugiaPelvaniaTextField.getText());
            antecedenteFemenino.setPap(papTextField.getText());
            antecedenteFemenino.setFecha(fechaTextField.getText());
            antecedenteFemenino.setMamografia(mamografiaTextField.getText());
            antecedenteFemenino.setEst(estJfxTextField.getText());
            antecedenteFemenino.setPaciente(paciente);
            psqlAntecedenteFemenino.insertar(antecedenteFemenino);
            okClicked = true;
            stage.close();
        } else {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("ERROR");
            alerta.setHeaderText(paciente.getNombres() + " " + paciente.getApellidos() + " ya ha registrado estos campos");
            alerta.setContentText("Para realizar cambios por favor utilize el boton MODIFICAR");
            alerta.initStyle(StageStyle.UTILITY);
            alerta.showAndWait();
        }
    }

    @FXML
    private void modificar() {
        if (validacion()) {
            PSQLAntecedenteFemenino psqlAntecedenteFemenino = new PSQLAntecedenteFemenino();
            AntecedenteFemenino antecedenteFemenino = new AntecedenteFemenino();
            antecedenteFemenino.setEdadMenarca(edadMenarcaTextField.getText());
            antecedenteFemenino.setEdadClimaterio(edadClimaterioTextField.getText());
            antecedenteFemenino.setMenopausia(menopausiaTextField.getText());
            antecedenteFemenino.setFum(fumTextField.getText());
            antecedenteFemenino.setTerapiaAnticonceptivo(terapiaAnticonceptivaTextField.getText());
            antecedenteFemenino.setInicioSexual(inicioSexualTextField.getText());
            antecedenteFemenino.setNroPareja(nroParejaTextField.getText());
            antecedenteFemenino.setCirugiaPelvania(cirugiaPelvaniaTextField.getText());
            antecedenteFemenino.setPap(papTextField.getText());
            antecedenteFemenino.setFecha(fechaTextField.getText());
            antecedenteFemenino.setMamografia(mamografiaTextField.getText());
            antecedenteFemenino.setEst(estJfxTextField.getText());
            antecedenteFemenino.setPaciente(paciente);
            psqlAntecedenteFemenino.modificar(antecedenteFemenino);
            okClicked = true;
            stage.close();
        } else {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("ERROR");
            alerta.setHeaderText(paciente.getNombres() + " " + paciente.getApellidos() + " No ha registrado estos campos");
            alerta.setContentText("Para poder registrar, complete los campos y haga click en ACEPTAR");
            alerta.initStyle(StageStyle.UTILITY);
            alerta.showAndWait();
        }
    }

    @FXML
    private void salir() {
        okClicked = true;
        stage.close();
    }

    private boolean validacion() {
        PSQLAntecedenteFemenino psqlAntecedenteFemenino = new PSQLAntecedenteFemenino();
        AntecedenteFemenino antecedenteFemeninoTemp = psqlAntecedenteFemenino.obtenerPorID(paciente);
        return antecedenteFemeninoTemp != null;
    }
}

