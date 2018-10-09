package com.codedynamic.clinica.vista.medico;

import com.codedynamic.clinica.MainApp;
import com.codedynamic.clinica.dao.postgresql.PSQLAnamnesis;
import com.codedynamic.clinica.dao.postgresql.PSQLExamenFisico;
import com.codedynamic.clinica.dao.postgresql.PSQLHabito;
import com.codedynamic.clinica.dao.postgresql.PSQLIppa;
import com.codedynamic.clinica.modelo.Anamnesis;
import com.codedynamic.clinica.modelo.Atencion;
import com.codedynamic.clinica.modelo.DetalleAtencion;
import com.codedynamic.clinica.modelo.ExamenFisico;
import com.codedynamic.clinica.modelo.Habito;
import com.codedynamic.clinica.modelo.Ippa;
import com.codedynamic.clinica.modelo.Paciente;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
	private Paciente paciente;
	private DetalleAtencion detalleAtencion = new DetalleAtencion();
	
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	public void setAtencion(Atencion atencion) {
		this.atencion = atencion;
		detalleAtencion.setAtencion(atencion);
	}
	
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
		pacienteLabel.setText(paciente.getNombres() + " " + paciente.getApellidos());
	}
	
	@FXML
	private void initialize() {
		
	}
	
	private void procesarAnamnesis() {
		Anamnesis anamnesis = new Anamnesis();
		PSQLAnamnesis psqlAnamnesis = new PSQLAnamnesis();
		anamnesis.setAntecedenteEnfermedad(antecedenteEATextArea.getText());
		anamnesis.setMedicamentoActual(medicamentoActualTextArea.getText());
		anamnesis.setPaciente(paciente);
		psqlAnamnesis.insertar(anamnesis);
		detalleAtencion.setAnamnesis(anamnesis);
	}
	
	private void procesarHabitos() {
		Habito habito = new Habito();
		PSQLHabito psqlHabito = new PSQLHabito();
		habito.setAlimentario(alimentariosTextField.getText());
		habito.setIntestinal(intestinalTextField.getText());
		habito.setUrinario(urinarioTextField.getText());
		habito.setSuenho(suenoTextField.getText());
		habito.setActividadFisica(actividadFisicaTextField.getText());
		habito.setFactorRiesgo(factorRiesgoTextField.getText());
		habito.setAmbiental(ambientalTextField.getText());
		habito.setToxico(toxicoDependTextField.getText());
		habito.setLaboral(laboralTextField.getText());
		habito.setPaciente(paciente);
		psqlHabito.insertar(habito);
		detalleAtencion.setHabito(habito);
	}
	
	private void procesarExamenFisico() {
		ExamenFisico examenFisico = new ExamenFisico();
		PSQLExamenFisico psqlExamenFisico = new PSQLExamenFisico();
		examenFisico.setPa(paTextField1.getText() + " " + paTextField2.getText());
		examenFisico.setFc(fcTextField.getText());
		examenFisico.setTalla(tallaTextField.getText());
		examenFisico.setGlicemia(glicemiaTextField.getText());
		examenFisico.setPeso(pesoTextField.getText());
		examenFisico.setFr(frTextField.getText());
		examenFisico.setImc(imcTextField.getText());
		examenFisico.setSpo(spo2TextField.getText());
		examenFisico.setAltura(alturaTextField.getText());
		examenFisico.settAxilar(tAxilarTextField.getText());
		examenFisico.setCa(caTextField.getText());
		examenFisico.setPaciente(paciente);
		psqlExamenFisico.insertar(examenFisico);
		detalleAtencion.setExamenFisico(examenFisico);
	}
	
	private void procesarIppa() {
		Ippa ippa = new Ippa();
		PSQLIppa psqlIppa = new PSQLIppa();
		ippa.setCabeza(cabezaTextField.getText());
		ippa.setTorax(toraxTextField.getText());
		ippa.setAbdomen(abdomenTextField.getText());
		ippa.setPiel(pielTextField.getText());
		ippa.setSnc(sncTextField.getText());
		ippa.setCuello(cuelloTextField.getText());
		ippa.setAparatoCV(aparatoTextField.getText());
		ippa.setAparatoG(aparatoGenitTextField.getText());
		ippa.setSistema(sistemaOsteoTextField.getText());
		ippa.setPsquiatrico(psiquiatricoTextField.getText());
		ippa.setPaciente(paciente);
		psqlIppa.insertar(ippa);
		detalleAtencion.setIppa(ippa);
	}
	
	@FXML
	private void siguiente() {
		if (validacion()) {
			procesarAnamnesis();
			procesarHabitos();
			procesarExamenFisico();
			procesarIppa();
			mainApp.getContenedorPrincipal().setCenter(null);
			mainApp.mostrarIndicacionesMedicas(detalleAtencion, paciente);
		}
	}
	
	private boolean validacion() {
		String error = "";
		if (paTextField1.getText().length() == 0 || paTextField1.getText() == null) {
			error += "Favor completar Presion Arterial\n";
		}
		if (fcTextField.getText() == null || fcTextField.getText().length() == 0) {
			error += "Favor completar Frecuencia Cardiaca\n";
		}
		if (spo2TextField.getText() == null || spo2TextField.getText().length() == 0) {
			error += "Favor completar SP02\n";
		}
		if (error.length() == 0) {
			return true;
		} else {
			Alert alerta = new Alert(AlertType.ERROR);
			alerta.setTitle("ERROR");
			alerta.setHeaderText("Parametros Impresindibles");
			alerta.setContentText("Favor completar los parametros indicados a continuacion\n" + error);
			return false;
		}
	}
}
