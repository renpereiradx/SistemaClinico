package com.codedynamic.clinica.vista.paciente.antecedente;

import com.codedynamic.clinica.dao.postgresql.PSQLAntecedenteMasculino;
import com.codedynamic.clinica.modelo.AntecedenteMasculino;
import com.codedynamic.clinica.modelo.Paciente;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AntecedenteMasculinoDialogoControlador {
	@FXML private JFXTextField inicioSexualTextField;
	@FXML private JFXTextField nroParejaTextField;
	@FXML private JFXTextField cirugiaAparatoGenitalTextField;
	@FXML private JFXTextArea rastreoPostrataTextField;
	@FXML private JFXTextArea estTextField;
	
	private Stage stage;
	private Paciente paciente;
	private boolean okClicked = false;
	
	public boolean isOkClicked() { return okClicked; }
	public void setStage(Stage stage) { this.stage = stage; }
	
	@FXML private void initialize(){}
	
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
		if (validacion()) {
			llenarCampos();
		}
	}
	
	private void llenarCampos() {
		PSQLAntecedenteMasculino psqlAntecedenteMasculino = new PSQLAntecedenteMasculino();
		AntecedenteMasculino antecedenteMasculino = psqlAntecedenteMasculino.obtenerPorID(paciente);
		inicioSexualTextField.setText(antecedenteMasculino.getInicioSexual());
		nroParejaTextField.setText(String.valueOf((short)antecedenteMasculino.getNroPareja()));
		cirugiaAparatoGenitalTextField.setText(antecedenteMasculino.getCirugiaGenital());
		rastreoPostrataTextField.setText(antecedenteMasculino.getRastreoPostrata());
		estTextField.setText(antecedenteMasculino.getEst());
	}
	
	@FXML
	private void aceptar() {
		PSQLAntecedenteMasculino psqlAntecedenteMasculino;
		AntecedenteMasculino antecedenteMasculino;
		if (!validacion()) {
			antecedenteMasculino = new AntecedenteMasculino();
			antecedenteMasculino.setInicioSexual(inicioSexualTextField.getText());
			antecedenteMasculino.setNroPareja((short) Integer.parseInt(nroParejaTextField.getText()));
			antecedenteMasculino.setCirugiaGenital(cirugiaAparatoGenitalTextField.getText());
			antecedenteMasculino.setRastreoPostrata(rastreoPostrataTextField.getText());
			antecedenteMasculino.setEst(estTextField.getText());
			antecedenteMasculino.setPaciente(paciente);
			psqlAntecedenteMasculino = new PSQLAntecedenteMasculino();
			psqlAntecedenteMasculino.insertar(antecedenteMasculino);
			okClicked = true;
			stage.close();
		} else {
			Alert alerta = new Alert(AlertType.ERROR);
			alerta.setTitle("ERROR");
			alerta.setHeaderText("No se pudo guardar los datos");
			alerta.setContentText(paciente.getNombres() + " " + paciente.getApellidos() + " ya esta registrado, para realizar cambios por favor utilice el boton MODIFICAR");
			alerta.initStyle(StageStyle.UTILITY);
			alerta.showAndWait();
		}
	
	}
	
	@FXML
	private void modificar() {
		PSQLAntecedenteMasculino psqlAntecedenteMasculino;
		AntecedenteMasculino antecedenteMasculino;
		if (validacion()) {
			antecedenteMasculino = new AntecedenteMasculino();
			antecedenteMasculino.setInicioSexual(inicioSexualTextField.getText());
			antecedenteMasculino.setNroPareja((short) Integer.parseInt(nroParejaTextField.getText()));
			antecedenteMasculino.setCirugiaGenital(cirugiaAparatoGenitalTextField.getText());
			antecedenteMasculino.setRastreoPostrata(rastreoPostrataTextField.getText());
			antecedenteMasculino.setEst(estTextField.getText());
			antecedenteMasculino.setPaciente(paciente);
			psqlAntecedenteMasculino = new PSQLAntecedenteMasculino();
			psqlAntecedenteMasculino.modificar(antecedenteMasculino);
			okClicked = true;
			stage.close();
		} else {
			Alert alerta = new Alert(AlertType.ERROR);
			alerta.setTitle("ERROR");
			alerta.setHeaderText("No se pudo modificar los datos");
			alerta.setContentText(paciente.getNombres() + " " + paciente.getApellidos() + " sin registro, para nuevo antecedente masculino por favor utilice el boton GUARDAR");
			alerta.initStyle(StageStyle.UTILITY);
			alerta.showAndWait();
		}
	}
	
	@FXML
	private void salir() {
		stage.close();
	}
	
	private boolean validacion() {
		PSQLAntecedenteMasculino psqlAntecedenteMasculino = new PSQLAntecedenteMasculino();
		AntecedenteMasculino antecedenteMasculino = psqlAntecedenteMasculino.obtenerPorID(paciente);
		return antecedenteMasculino != null;
	}
}
