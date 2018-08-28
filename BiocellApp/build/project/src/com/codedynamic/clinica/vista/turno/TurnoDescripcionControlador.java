package com.codedynamic.clinica.vista.turno;

import com.codedynamic.clinica.dao.postgresql.PSQLTurno;
import com.codedynamic.clinica.modelo.Turno;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class TurnoDescripcionControlador {
	@FXML TextArea descripcionTextArea;
	
	private Stage stage;
	private Turno turno;
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	public void setTurno(Turno turno) {
		this.turno = turno;
		cargarDescripcion(turno);
	}
	
	@FXML private void initialize() {}
	
	private void cargarDescripcion(Turno turno) {
		PSQLTurno psqlTurno = new PSQLTurno();
		Turno turnoTemp = psqlTurno.obtenerPorID(turno.getId_turno());
		if (turnoTemp.getDescripcion() != null) {
			descripcionTextArea.setText(turnoTemp.getDescripcion());
		} else {
			descripcionTextArea.setText("Sin informacion");
		}
	}
	
	@FXML
	private void salir() {
		stage.close();
	}
}
