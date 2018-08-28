package com.codedynamic.clinica.vista.paciente;

import java.util.List;

import com.codedynamic.clinica.MainApp;
import com.codedynamic.clinica.dao.postgresql.PSQLPaciente;
import com.codedynamic.clinica.excepciones.ExcepcionGeneral;
import com.codedynamic.clinica.modelo.Paciente;
import com.codedynamic.clinica.utilidades.UtilidadFecha;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.StageStyle;

public class PacienteDescripcionControlador {
    @FXML private TableView<Paciente> pacienteTableView;
    @FXML private TableColumn<Paciente, String> nombreTableColumn;
    @FXML private TableColumn<Paciente, String> apellidoTableColumn;
    @FXML private Label codigoLabel;
    @FXML private Label nombreLabel;
    @FXML private Label apellidoLabel;
    @FXML private Label cedulaLabel;
    @FXML private Label nroTelefonoLabel;
    @FXML private Label correoLabel;
    @FXML private Label fechaNacimientoLabel;
    @FXML private Label departamentoLabel;
    @FXML private Label ciudadLabel;
    @FXML private Label barrioLabel;
    @FXML private Label profesionLabel;
    @FXML private Label generoLabel;
    @FXML private Label hijosLabel;
    @FXML private Label nombrePadreLabel;
    @FXML private Label nombreMadreLabel;
    @FXML private Label estadoCivilLabel;
    @FXML private Label gradoInstruccionLabel;
    @FXML private Label personaResponsableLabel;
    @FXML private Label etniaLabel;
    @FXML private JFXTextField buscadorField;
    @FXML private Label mensajeNuevoPac;
    
    private ObservableList<Paciente> lista;
    private MainApp mainApp;
    private PSQLPaciente psqlPaciente;

    public PacienteDescripcionControlador(){}
    
    public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

    @FXML private void initialize() {
        mostrarDetallesPaciente(null);
        mostrarPacientes();
    }

    @FXML private void mostrarPacientes() {
        nombreTableColumn.setCellValueFactory(datoCelda -> datoCelda.getValue().nombresProperty());
        apellidoTableColumn.setCellValueFactory(datoCelda -> datoCelda.getValue().apellidosProperty());
        pacienteTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)
                -> mostrarDetallesPaciente(newValue));
        lista = FXCollections.observableArrayList();
    }

    @FXML private void listarPacientes() {
        try {
            psqlPaciente = new PSQLPaciente();
            lista = FXCollections.observableArrayList(psqlPaciente.listar());
            pacienteTableView.setItems(lista);
        } catch (ExcepcionGeneral e) {
            throw new ExcepcionGeneral(e.getMessage());
        }
    }
    
    @FXML private void manejarNuevoPaciente() {
    	Paciente tempPaciente = new Paciente();
    	psqlPaciente = new PSQLPaciente();
    	boolean okClicked = mainApp.mostrarDialogoRegistroPaciente(tempPaciente);
    	if (okClicked) {
			lista.add(tempPaciente);
            if (pacienteTableView.getSelectionModel().getSelectedIndex() < 0) {
                pacienteTableView.setItems(lista);
            }
			psqlPaciente.insertar(tempPaciente);
			mensajeNuevoPac.setStyle("-fx-background-color: #03c67a; -fx-fill: #fff");
			mensajeNuevoPac.setText("Usuario Registrado con el ID " + tempPaciente.getId_paciente());
		}
    }
    
    @FXML private void manejarEdicionPaciente() {
    	Paciente selecPaciente = pacienteTableView.getSelectionModel().getSelectedItem();
    	psqlPaciente = new PSQLPaciente();
    	if (selecPaciente != null) {
			boolean okClicked = mainApp.mostrarDialogoRegistroPaciente(selecPaciente);
			if (okClicked) {
				psqlPaciente.modificar(selecPaciente);
				mostrarDetallesPaciente(selecPaciente);
			} 
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Atencion");
			alert.setHeaderText("Paciente no seleccionado");
			alert.setContentText("Favor seleccionar un paciente en la tabla");
			alert.initStyle(StageStyle.UTILITY);
			alert.showAndWait();
		}
    }
    
    @FXML private void manejarEliminarPaciente() {
    	try {
			int index = pacienteTableView.getSelectionModel().getSelectedIndex();
			Paciente paciente = pacienteTableView.getSelectionModel().getSelectedItem();
			psqlPaciente = new PSQLPaciente();
			if (index >= 0) {
				psqlPaciente.eliminar(paciente);
				pacienteTableView.getItems().remove(index);
			} else {
				Alert alerta = new Alert(AlertType.ERROR);
				alerta.setTitle("Error al Eliminar");
				alerta.setHeaderText("Error al eliminar paciente");
				alerta.setHeaderText("Ya no quedan elementos que eliminar");
				alerta.showAndWait();
			} 
		} catch (ExcepcionGeneral e) {
			Alert alerta = new Alert(AlertType.INFORMATION);
			alerta.setTitle("Atencion");
			alerta.setContentText(e.getMessage());
		}
    }
    
    @FXML private void buscarPacientes() {
    	psqlPaciente = new PSQLPaciente();
    	ObservableList<Paciente> listaNombre = psqlPaciente.listarBusqueda(buscadorField.getText());
    	ObservableList<Paciente> listaApellido = FXCollections.observableArrayList(psqlPaciente.listarBusqApe(buscadorField.getText()));
    	if (!buscadorField.getText().equals(null)) {
			if (!listaNombre.isEmpty()) {
				pacienteTableView.setItems(listaNombre);
			} else if (!listaApellido.isEmpty()) {
				pacienteTableView.setItems(listaApellido);
			}
		} else {
			buscadorField.setText("Favor Introduzca nombre del paciente");
		}
    }
    
    @FXML private void cargarHome() {
    	mainApp.showHome();
    }

    private void mostrarDetallesPaciente(Paciente paciente) {
        if (paciente != null) {
            codigoLabel.setText(Integer.toString(paciente.getId_paciente()));
            nombreLabel.setText(paciente.getNombres());
            apellidoLabel.setText(paciente.getApellidos());
            cedulaLabel.setText(paciente.getCedula());
            nroTelefonoLabel.setText(paciente.getNroTelefono());
            correoLabel.setText(paciente.getCorreo());
            fechaNacimientoLabel.setText(UtilidadFecha.formato(paciente.getFecha_nacimiento()));
            departamentoLabel.setText(paciente.getDepartamento());
            ciudadLabel.setText(paciente.getCiudad());
            barrioLabel.setText(paciente.getBarrio());
            profesionLabel.setText(paciente.getProfesion());
            if (paciente.getSexo() != null) {
                if (paciente.getSexo().equals("M")) {
                    generoLabel.setText("Masculino");
                } else if (paciente.getSexo().equals("F")){
                    generoLabel.setText("Femenino");
                }
            }
            hijosLabel.setText(Integer.toString(paciente.getHijos()));
            nombrePadreLabel.setText(paciente.getNombrePadre());
            nombreMadreLabel.setText(paciente.getNombreMadre());
            if (paciente.getEstadoCivil() != null) {
                if (paciente.getEstadoCivil().equals("S")) {
                    estadoCivilLabel.setText("Soltero/a");
                } else if (paciente.getEstadoCivil().equals("C")) {
                    estadoCivilLabel.setText("Casado/a");
                }
                if (paciente.getEstadoCivil().equals("D")) {
                    estadoCivilLabel.setText("Divorciado/a");
                }
                if (paciente.getEstadoCivil().equals("V")) {
                    estadoCivilLabel.setText("Viudo/a");
                }
            } else {
                estadoCivilLabel.setText("");
            }
            gradoInstruccionLabel.setText(paciente.getGradoInstruccion());
            personaResponsableLabel.setText(paciente.getPersonaResponsable());
            etniaLabel.setText(paciente.getEtnia());
        } else {
            codigoLabel.setText("");
            nombreLabel.setText("");
            apellidoLabel.setText("");
            cedulaLabel.setText("");
            nroTelefonoLabel.setText("");
            correoLabel.setText("");
            fechaNacimientoLabel.setText("");
            departamentoLabel.setText("");
            ciudadLabel.setText("");
            barrioLabel.setText("");
            profesionLabel.setText("");
            generoLabel.setText("");
            hijosLabel.setText("");
            nombrePadreLabel.setText("");
            nombreMadreLabel.setText("");
            estadoCivilLabel.setText("");
            gradoInstruccionLabel.setText("");
            personaResponsableLabel.setText("");
            etniaLabel.setText("");
        }
    }
}

