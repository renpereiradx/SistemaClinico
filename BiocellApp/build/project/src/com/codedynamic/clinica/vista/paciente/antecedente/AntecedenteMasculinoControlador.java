package com.codedynamic.clinica.vista.paciente.antecedente;

import com.codedynamic.clinica.MainApp;
import com.codedynamic.clinica.dao.postgresql.PSQLAntecedenteFamiliar;
import com.codedynamic.clinica.dao.postgresql.PSQLAntecedenteMasculino;
import com.codedynamic.clinica.dao.postgresql.PSQLAntecedentePersonal;
import com.codedynamic.clinica.dao.postgresql.PSQLAntecedenteQuirurgico;
import com.codedynamic.clinica.modelo.*;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AntecedenteMasculinoControlador {
    @FXML private JFXTextArea antecedentePersonalTextArea;
    // Atributos relacionados a las Internaciones-Antecedentes Quirurgicos
    @FXML private JFXTextArea antecedenteQuirurgicoTextArea;
    @FXML private JFXTextArea dxTextArea;
    @FXML private JFXTextArea otrosTexArea;
    // Atributos - Antecedentes Familiares
    @FXML private Label padreLabel;
    @FXML private Label madreLabel;
    @FXML private Label hijosLabel;
    @FXML private Label hermanosLabel;
    @FXML private Label abuelosPaternosLabel;
    @FXML private Label abuelosMaternosLabel;
    @FXML private Label vacunacionLabel;
    // Antecedentes Masculinos;
    @FXML private Label inicioSexualActiva;
    @FXML private Label nroParejaLabel;
    @FXML private Label cirugiaAparatoGenital;
    @FXML private JFXTextField rastreoTextField;
    @FXML private JFXTextField estTextField;

    private MainApp mainApp;
    private boolean okClicked = false;
    private Paciente paciente;
    private PSQLAntecedentePersonal psqlAntecedentePersonal;
    private AntecedentePersonal antecedentePersonal;
    private PSQLAntecedenteQuirurgico psqlAntecedenteQuirurgico;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML private void initialize() {}

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
        llenarAntecedentePersonal(paciente);
        llenarAntecedenteQuirurgico(paciente);
        llenarAntecedenteFamiliar(paciente);
        llenarAntecedenteMasculino(paciente);    }

    private void llenarAntecedentePersonal(Paciente paciente) {
        psqlAntecedentePersonal = new PSQLAntecedentePersonal();
        antecedentePersonal = psqlAntecedentePersonal.obtenerPorIDPaciente(paciente);
        String mensaje = "";
        if (antecedentePersonal != null) {
            for (Enfermedad enfermedad : antecedentePersonal.getListaEnfermedades()) {
                mensaje += enfermedad.getEnfermedad() + " - ";
            }
        }
        antecedentePersonalTextArea.setText(mensaje);
    }

    @FXML
    private void addAntecedentePersonal() {
        boolean okClicked = mainApp.mostrarDialogoAntecedentePersonal(paciente);
        if (okClicked) {
            llenarAntecedentePersonal(paciente);
        }
    }

    private void llenarAntecedenteQuirurgico(Paciente paciente) {
        psqlAntecedenteQuirurgico = new PSQLAntecedenteQuirurgico();
        AntecedenteQuirurgico antecedenteQuirurgicoTemp = psqlAntecedenteQuirurgico.obtenerPorID(paciente.getId_paciente());
        if (antecedenteQuirurgicoTemp != null) {
            antecedenteQuirurgicoTextArea.setText(antecedenteQuirurgicoTemp.getInternaciones());
            dxTextArea.setText(antecedenteQuirurgicoTemp.getDx());
            otrosTexArea.setText(antecedenteQuirurgicoTemp.getOtros());
        }
    }

    @FXML
    private void addAntecedenteQuirurgico() {
        boolean okClicked = mainApp.mostrarDialogoAntecedenteQuirurgico(paciente);
        if (okClicked) {
            llenarAntecedenteQuirurgico(paciente);
        }
    }

    private void llenarAntecedenteFamiliar(Paciente paciente) {
        PSQLAntecedenteFamiliar psqlAntecedenteFamiliar = new PSQLAntecedenteFamiliar();
        AntecedenteFamiliar antecedenteFamiliar = psqlAntecedenteFamiliar.obtenerPorID(paciente);
        if (antecedenteFamiliar != null) {
            padreLabel.setText(antecedenteFamiliar.getPadre());
            madreLabel.setText(antecedenteFamiliar.getMadre());
            hijosLabel.setText(antecedenteFamiliar.getHijos());
            hermanosLabel.setText(antecedenteFamiliar.getHermanos());
            abuelosPaternosLabel.setText(antecedenteFamiliar.getAbuelosPaternos());
            abuelosMaternosLabel.setText(antecedenteFamiliar.getAbuelosMaternos());
            vacunacionLabel.setText(antecedenteFamiliar.getVacunacion());
        } else {
            padreLabel.setText("");
            madreLabel.setText("");
            hijosLabel.setText("");
            hermanosLabel.setText("");
            abuelosMaternosLabel.setText("");
            abuelosPaternosLabel.setText("");
            vacunacionLabel.setText("");
        }
    }

    @FXML
    private void addAntecedenteFamiliar() {
        boolean okClicked = mainApp.mostrarDialogoAntecedenteFamiliar(paciente);
        if (okClicked) {
            llenarAntecedenteFamiliar(paciente);
        }
    }

    private void llenarAntecedenteMasculino(Paciente paciente){
    	PSQLAntecedenteMasculino psqlAntecedenteMasculino = new PSQLAntecedenteMasculino();
    	AntecedenteMasculino antecedenteMasculinoTemp = psqlAntecedenteMasculino.obtenerPorID(paciente);
    	if (antecedenteMasculinoTemp != null) {
			inicioSexualActiva.setText(antecedenteMasculinoTemp.getInicioSexual());
			nroParejaLabel.setText(String.valueOf(antecedenteMasculinoTemp.getNroPareja()));
			cirugiaAparatoGenital.setText(antecedenteMasculinoTemp.getCirugiaGenital());
			rastreoTextField.setText(antecedenteMasculinoTemp.getRastreoPostrata());
			estTextField.setText(antecedenteMasculinoTemp.getEst());
		} else {
			inicioSexualActiva.setText("");
			nroParejaLabel.setText("");
			cirugiaAparatoGenital.setText("");
			estTextField.setText("");
		}
    }

    @FXML
    private void addAntecedenteMasculino() {
    	boolean okClicked = mainApp.mostrarDialogoAntecedenteMasculino(paciente);
    	if (okClicked) {
			llenarAntecedenteMasculino(paciente);
		}
    }
    
}

