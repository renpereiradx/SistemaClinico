package com.codedynamic.clinica.vista.medico;

import com.codedynamic.clinica.MainApp;
import com.codedynamic.clinica.modelo.Atencion;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class RegistroMedicosControlador {
	// Cabecera
	@FXML
	private Label pacienteLabel;
	@FXML
	private JFXTextArea antecedenteEATextArea;
	@FXML
	private JFXTextArea medicamentoActualTextArea;
	// Habitos
	@FXML
	private JFXTextField alimentariosTextField;
	@FXML
	private JFXTextField intestinalTextField;
	@FXML
	private JFXTextField urinarioTextField;
	@FXML
	private JFXTextField suenoTextField;
	@FXML
	private JFXTextField actividadFisicaTextField;
	@FXML
	private JFXTextField factorRiesgoTextField;
	@FXML
	private JFXTextField ambientalTextField;
	@FXML
	private JFXTextField toxicoDependTextField;
	@FXML
	private JFXTextField laboralTextField;
	// Examen Fisico
	@FXML
	private JFXTextField paTextField1;
	@FXML
	private JFXTextField paTextField2;
	@FXML
	private JFXTextField pesoTextField;
	@FXML
	private JFXTextField alturaTextField;
	@FXML
	private JFXTextField fcTextField;
	@FXML
	private JFXTextField frTextField;
	@FXML
	private JFXTextField tAxilarTextField;
	@FXML
	private JFXTextField tallaTextField;
	@FXML
	private JFXTextField imcTextField;
	@FXML
	private JFXTextField caTextField;
	@FXML
	private JFXTextField glicemiaTextField;
	@FXML
	private JFXTextField spo2TextField;
	// IPPA
	@FXML
	private JFXTextField cabezaTextField;
	@FXML
	private JFXTextField cuelloTextField;
	@FXML
	private JFXTextField toraxTextField;
	@FXML
	private JFXTextField aparatoTextField;
	@FXML
	private JFXTextField abdomenTextField;
	@FXML
	private JFXTextField aparatoGenitTextField;
	@FXML
	private JFXTextField pielTextField;
	@FXML
	private JFXTextField sistemaOsteoTextField;
	@FXML
	private JFXTextField sncTextField;
	@FXML
	private JFXTextField psiquiatricoTextField;
	
	private MainApp mainApp;
	private Atencion atencion;
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	public void setAtencion(Atencion atencion) {
		this.atencion = atencion;
	}
	
	@FXML
	private void initialize() {}
}
