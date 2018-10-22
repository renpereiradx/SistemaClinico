package com.codedynamic.clinica.vista.medico;

import java.time.LocalDate;
import java.time.LocalTime;

import com.codedynamic.clinica.MainApp;
import com.codedynamic.clinica.dao.postgresql.PSQLDetalleAtencion;
import com.codedynamic.clinica.dao.postgresql.PSQLDiagnosticoMedico;
import com.codedynamic.clinica.modelo.DetalleAtencion;
import com.codedynamic.clinica.modelo.DiagnosticoMedico;
import com.codedynamic.clinica.modelo.Paciente;
import com.jfoenix.controls.JFXTextArea;

import javafx.fxml.FXML;

public class IndicacionesMedicasControlador {

	@FXML
	private JFXTextArea impresionDiagnostica;
	@FXML
	private JFXTextArea conducta;
	@FXML
	private JFXTextArea tratamiento;
	
	private MainApp mainApp;
	private DetalleAtencion detalleAtencion;
	private Paciente paciente;
	private DiagnosticoMedico diagnosticoMedico;
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	public void setDetalleAtencion(DetalleAtencion detalleAtencion) {
		this.detalleAtencion = detalleAtencion;
	}
	
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	
	@FXML
	private void initialize() {
		
	}
	
	private void procesarDatos() {
		PSQLDiagnosticoMedico psqlDiagnosticoMedico = new PSQLDiagnosticoMedico();
		diagnosticoMedico = new DiagnosticoMedico();
		diagnosticoMedico.setIndicacion(impresionDiagnostica.getText());
		diagnosticoMedico.setConducta(conducta.getText());
		diagnosticoMedico.setTratamiento(tratamiento.getText());
		diagnosticoMedico.setPaciente(paciente);
		psqlDiagnosticoMedico.insertar(diagnosticoMedico);
		detalleAtencion.setDiagnosticoMedico(diagnosticoMedico);
		detalleAtencion.setFecha(LocalDate.now());
		detalleAtencion.setHora(LocalTime.now());
	}
	
	@FXML
	private void guardar() {
		procesarDatos();
		PSQLDetalleAtencion psqlDetalleAtencion = new PSQLDetalleAtencion();
		psqlDetalleAtencion.insertar(detalleAtencion);
		mainApp.getContenedorPrincipal().setCenter(null);
		mainApp.mostrarMedicoPrincipal();
	}
	
	@FXML
	private void salir() {
		mainApp.getContenedorPrincipal().setCenter(null);
		mainApp.mostrarMedicoPrincipal();
	}
}
