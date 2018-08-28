package com.codedynamic.clinica.vista.paciente;

import com.codedynamic.clinica.MainApp;
import com.codedynamic.clinica.modelo.Paciente;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PacienteRegistroControlador {

    @FXML private TextField nombresField;
    @FXML private TextField apellidosField;
    @FXML private TextField cedulaField;
    @FXML private TextField nroTelefonoField;
    @FXML private TextField correoField;
    @FXML private JFXDatePicker fechaDatePicker;
    @FXML private TextField departamentoField;
    @FXML private TextField ciudadField;
    @FXML private TextField barrioField;
    @FXML private TextField profesionField;
    @FXML private TextField nombrePadreField;
    @FXML private TextField nombreMadreField;
    @FXML private TextField gradoInstruccionField;
    @FXML private TextField personaResponsableField;
    @FXML private TextField etniaField;
    @FXML private JFXRadioButton masculinoRadioButton;
    @FXML private JFXRadioButton femeninoRadioButton;
    @FXML private JFXCheckBox solteroCheckBox;
    @FXML private JFXCheckBox casadoCheckBox;
    @FXML private JFXCheckBox divorciadoCheckBox;
    @FXML private JFXCheckBox viudoCheckBox;
    @FXML private TextField hijosField;

    private Stage stage;
    private Paciente paciente;
    private MainApp mainApp;
    private boolean okClicked = false;

    @FXML
    private void initialize() {}

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

    public void setPaciente(Paciente paciente) {
    	this.paciente = paciente;
    	nombresField.setText(paciente.getNombres());
    	apellidosField.setText(paciente.getApellidos());
    	cedulaField.setText(paciente.getCedula());
    	nroTelefonoField.setText(paciente.getNroTelefono());
    	correoField.setText(paciente.getCorreo());
        fechaDatePicker.setValue(paciente.getFecha_nacimiento());
    	departamentoField.setText(paciente.getDepartamento());
    	ciudadField.setText(paciente.getCiudad());
    	barrioField.setText(paciente.getBarrio());
    	profesionField.setText(paciente.getProfesion());
        if (paciente.getSexo() != null) {
            if (paciente.getSexo().equals("M")) {
                masculinoRadioButton.setSelected(true);
                femeninoRadioButton.setSelected(false);
            } else if (paciente.getSexo().equals("F")) {
                femeninoRadioButton.setSelected(true);
                masculinoRadioButton.setSelected(false);
            }
        }
        hijosField.setText(Integer.toString(paciente.getHijos()));
    	nombrePadreField.setText(paciente.getNombrePadre());
    	nombreMadreField.setText(paciente.getNombreMadre());
        if (paciente.getEstadoCivil() != null) {
            if (paciente.getEstadoCivil().equals("S")) {
                solteroCheckBox.setSelected(true);
                casadoCheckBox.setSelected(false);
                divorciadoCheckBox.setSelected(false);
                viudoCheckBox.setSelected(false);
            } else if (paciente.getEstadoCivil().equals("C")) {
                solteroCheckBox.setSelected(false);
                casadoCheckBox.setSelected(true);
                divorciadoCheckBox.setSelected(false);
                viudoCheckBox.setSelected(false);
            } else if (paciente.getEstadoCivil().equals("D")) {
                solteroCheckBox.setSelected(false);
                casadoCheckBox.setSelected(false);
                divorciadoCheckBox.setSelected(true);
                viudoCheckBox.setSelected(false);
            } else if (paciente.getEstadoCivil().equals("V")) {
                solteroCheckBox.setSelected(false);
                casadoCheckBox.setSelected(false);
                divorciadoCheckBox.setSelected(false);
                viudoCheckBox.setSelected(true);
            }
        }
        gradoInstruccionField.setText(paciente.getGradoInstruccion());
       	personaResponsableField.setText(paciente.getPersonaResponsable());
    	etniaField.setText(paciente.getEtnia());
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML private void selectAceptar() {
        if (validarDatos()) {
            paciente.setNombres(nombresField.getText());
            paciente.setApellidos(apellidosField.getText());
            paciente.setCedula(cedulaField.getText());
            paciente.setCorreo(correoField.getText());
            paciente.setNroTelefono(nroTelefonoField.getText());
            paciente.setFecha_nacimiento(fechaDatePicker.getValue());
            paciente.setDepartamento(departamentoField.getText());
            paciente.setCiudad(ciudadField.getText());
            paciente.setBarrio(barrioField.getText());
            paciente.setProfesion(profesionField.getText());
            if (masculinoRadioButton.isSelected()) {
                paciente.setSexo("M");
            }
            if (femeninoRadioButton.isSelected()) {
                paciente.setSexo("F");
            }
			paciente.setHijos(Integer.parseInt(hijosField.getText()));
            paciente.setId_usuario(mainApp.getUsuarioLoggeado().getId());
            paciente.setNombrePadre(nombrePadreField.getText());
            paciente.setNombreMadre(nombreMadreField.getText());
            if (solteroCheckBox.isSelected()) {
                paciente.setEstadoCivil("S");
            }
            if (casadoCheckBox.isSelected()) {
                paciente.setEstadoCivil("C");
            }
            if (divorciadoCheckBox.isSelected()) {
                paciente.setEstadoCivil("D");
            }
            if (viudoCheckBox.isSelected()) {
                paciente.setEstadoCivil("V");
            }
            paciente.setGradoInstruccion(gradoInstruccionField.getText());
            paciente.setPersonaResponsable(personaResponsableField.getText());
            paciente.setEtnia(etniaField.getText());
            okClicked = true;
            stage.close();
        }
    }

    @FXML private void selectCancelar() {
        this.stage.close();
    }

    private boolean validarDatos() {
        String errorMensaje = "";

        if (nombresField.getText() == null || nombresField.getText().length() == 0) {
            errorMensaje += "Favor introducir nombre del paciente \n";
        }

        if (apellidosField.getText() == null || apellidosField.getText().length() == 0) {
            errorMensaje += "Favor introducir apellido del paciente \n";
        }

        if (cedulaField.getText() == null || cedulaField.getText().length() == 0) {
            errorMensaje += "Favor introducir numero de cedula del paciente \n";
        }

        if (nroTelefonoField.getText() == null || nroTelefonoField.getText().length() == 0) {
			errorMensaje += "Favor Introducir Numero de Telefono";
		}
        if (ciudadField.getText() == null || ciudadField.getText().length() == 0) {
            errorMensaje += "Favor introducir ciudad de residencia del paciente \n";
        }
        if (errorMensaje.length() == 0) {
            return true;
        } else {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Error de campos");
            alerta.setHeaderText("Campos invalidos");
            alerta.setContentText(errorMensaje);
            alerta.initStyle(StageStyle.UTILITY);
            alerta.showAndWait();
            return false;
        }
    }
}

