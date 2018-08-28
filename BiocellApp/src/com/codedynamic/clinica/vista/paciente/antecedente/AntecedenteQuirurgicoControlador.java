package com.codedynamic.clinica.vista.paciente.antecedente;

import com.codedynamic.clinica.dao.postgresql.PSQLAntecedenteQuirurgico;
import com.codedynamic.clinica.excepciones.ExcepcionGeneral;
import com.codedynamic.clinica.modelo.AntecedenteQuirurgico;
import com.codedynamic.clinica.modelo.Paciente;
import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AntecedenteQuirurgicoControlador {
    @FXML private JFXTextArea internacionesTextArea;
    @FXML private JFXTextArea dxTextArea;
    @FXML private JFXTextArea otrosTextArea;

    private Stage stage;
    private Paciente paciente;
    private boolean okClicked = false;
    private PSQLAntecedenteQuirurgico psqlAntecedenteQuirurgico;

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML private void initialize() {}

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
        cargarDatos();
    }

    private void cargarDatos() {
        psqlAntecedenteQuirurgico = new PSQLAntecedenteQuirurgico();
        AntecedenteQuirurgico antecedenteQuirurgicoTemp = psqlAntecedenteQuirurgico.obtenerPorID(paciente.getId_paciente());
        if (antecedenteQuirurgicoTemp != null) {
            internacionesTextArea.setText(antecedenteQuirurgicoTemp.getInternaciones());
            dxTextArea.setText(antecedenteQuirurgicoTemp.getDx());
            otrosTextArea.setText(antecedenteQuirurgicoTemp.getOtros());
        } else {
            internacionesTextArea.setText("");
            dxTextArea.setText("");
            otrosTextArea.setText("");
        }
    }

    @FXML
    private void guardar() {
        psqlAntecedenteQuirurgico = new PSQLAntecedenteQuirurgico();
        AntecedenteQuirurgico antecedenteQuirurgicoTemp = psqlAntecedenteQuirurgico.obtenerPorID(paciente.getId_paciente());
        if (antecedenteQuirurgicoTemp == null) {
            antecedenteQuirurgicoTemp = new AntecedenteQuirurgico();
            antecedenteQuirurgicoTemp.setInternaciones(internacionesTextArea.getText());
            antecedenteQuirurgicoTemp.setDx(dxTextArea.getText());
            antecedenteQuirurgicoTemp.setOtros(otrosTextArea.getText());
            antecedenteQuirurgicoTemp.setPaciente(paciente);
            psqlAntecedenteQuirurgico.insertar(antecedenteQuirurgicoTemp);
            okClicked = true;
            stage.close();
        } else {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setHeight(200);
            alerta.setHeaderText("ERROR AL INTENTAR GUARDAR");
            alerta.setContentText("No se puede guardar dos veces, si necesita realizar cambios, pruebe con el boton MODIFICAR");
            alerta.initStyle(StageStyle.UTILITY);
            alerta.showAndWait();
        }
    }

    @FXML
    private void modificar() {
        psqlAntecedenteQuirurgico = new PSQLAntecedenteQuirurgico();
        AntecedenteQuirurgico antecedenteQuirurgicoTemp = psqlAntecedenteQuirurgico.obtenerPorID(paciente.getId_paciente());
        if (antecedenteQuirurgicoTemp != null) {
            antecedenteQuirurgicoTemp.setInternaciones(internacionesTextArea.getText());
            antecedenteQuirurgicoTemp.setDx(dxTextArea.getText());
            antecedenteQuirurgicoTemp.setOtros(otrosTextArea.getText());
            psqlAntecedenteQuirurgico.modificar(antecedenteQuirurgicoTemp);
            okClicked = true;
            stage.close();
        } else {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setHeight(200);
            alerta.setHeaderText("ERROR AL INTENTAR MODIFICAR");
            alerta.setContentText("No se puede modificar, el paciente " + paciente.getNombres() + " " + paciente.getApellidos() + "aun no ha registrado estos campos");
            alerta.initStyle(StageStyle.UTILITY);
            alerta.showAndWait();
        }
    }

    @FXML
    private void eliminar() {
        psqlAntecedenteQuirurgico = new PSQLAntecedenteQuirurgico();
        AntecedenteQuirurgico antecedenteQuirurgicoTemp = psqlAntecedenteQuirurgico.obtenerPorID(paciente.getId_paciente());
            if (antecedenteQuirurgicoTemp != null) {
                try {
                    psqlAntecedenteQuirurgico.eliminar(antecedenteQuirurgicoTemp);
                    okClicked = true;
                    stage.close();
                } catch (ExcepcionGeneral excepcionGeneral) {
                    Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setTitle("Error");
                    alerta.setHeight(200);
                    alerta.setHeaderText("ERROR AL INTENTAR ELIMINAR");
                    alerta.setContentText("No se puede eliminar" + excepcionGeneral.getMessage());
                    alerta.initStyle(StageStyle.UTILITY);
                    alerta.showAndWait();
                }
            }
    }

    @FXML
    private void salir() {
        okClicked = true;
        stage.close();
    }
}
