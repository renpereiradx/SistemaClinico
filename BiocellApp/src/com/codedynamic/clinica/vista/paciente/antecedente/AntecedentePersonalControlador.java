package com.codedynamic.clinica.vista.paciente.antecedente;

import com.codedynamic.clinica.dao.postgresql.PSQLAntecedentePersonal;
import com.codedynamic.clinica.dao.postgresql.PSQLEnfermedades;
import com.codedynamic.clinica.excepciones.ExcepcionGeneral;
import com.codedynamic.clinica.modelo.AntecedentePersonal;
import com.codedynamic.clinica.modelo.Enfermedad;
import com.codedynamic.clinica.modelo.Paciente;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AntecedentePersonalControlador {
    @FXML private JFXCheckBox cardiovascularesCheckBox;
    @FXML private JFXCheckBox respiratoriosCheckBox;
    @FXML private JFXCheckBox digestivosCheckBox;
    @FXML private JFXCheckBox geniturinariosCheckBox;
    @FXML private JFXCheckBox endocrinosCheckBox;
    @FXML private JFXCheckBox osteoartromusculuarCheckBox;
    @FXML private JFXCheckBox hemolinfopoyeticoCheckBox;
    @FXML private JFXCheckBox neuropsquiatricosCheckBox;
    @FXML private JFXCheckBox psicologicosCheckBox;
    @FXML private JFXCheckBox audiovisualesCheckBox;
    @FXML private JFXCheckBox odontologicosCheckBox;
    @FXML private JFXCheckBox alergicosCheckBox;
    @FXML private JFXCheckBox neoplasticosCheckBox;
    @FXML private JFXCheckBox mfcCheckBox;
    @FXML private JFXCheckBox traumatismosCheckBox;
    @FXML private JFXCheckBox quemadurasCheckBox;
    @FXML private JFXCheckBox transfucionesCheckBox;
    @FXML private JFXCheckBox metabolicosCheckBox;
    @FXML private Label pacienteLabel;
    @FXML private JFXListView<Enfermedad> enfermedadListView;

    private PSQLAntecedentePersonal psqlAntecedentePersonal;
    private PSQLEnfermedades psqlEnfermedades;
    private Paciente paciente;
    private boolean okClicked = false;
    private Stage stage;
    private ObservableList<Enfermedad> enfermedadObservableList;

    @FXML private void initialize(){}

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
        pacienteLabel.setText(paciente.getNombres() + " " + paciente.getApellidos());
        cargarLista();
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void guardar() {
        psqlEnfermedades = new PSQLEnfermedades();
        psqlAntecedentePersonal = new PSQLAntecedentePersonal();
        String mensaje = "";
        AntecedentePersonal antecedentePersonalTemp;
        if (cardiovascularesCheckBox.isSelected()) {
            antecedentePersonalTemp = psqlAntecedentePersonal.obtenerPorIDS((short) 1, paciente);
            if (antecedentePersonalTemp == null) {
                psqlAntecedentePersonal.insertar((short) 1, paciente);
            } else if (antecedentePersonalTemp.getEnfermedad().getCodigo() == 1){
                mensaje += antecedentePersonalTemp.getEnfermedad().getEnfermedad() + " ya registrado \n";
            }
        }
        if (respiratoriosCheckBox.isSelected()) {
             antecedentePersonalTemp = psqlAntecedentePersonal.obtenerPorIDS((short) 2, paciente);
            if (antecedentePersonalTemp == null) {
                psqlAntecedentePersonal.insertar((short) 2, paciente);
            } else if (antecedentePersonalTemp.getEnfermedad().getCodigo() == 2){
                mensaje += antecedentePersonalTemp.getEnfermedad().getEnfermedad() + " ya registrado \n";
            }
        }
        if (digestivosCheckBox.isSelected()) {
             antecedentePersonalTemp = psqlAntecedentePersonal.obtenerPorIDS((short) 4, paciente);
            if (antecedentePersonalTemp == null) {
                psqlAntecedentePersonal.insertar((short) 4, paciente);
            } else if (antecedentePersonalTemp.getEnfermedad().getCodigo() == 4){
                mensaje += antecedentePersonalTemp.getEnfermedad().getEnfermedad() + " ya registrado \n";
            }
        }
        if (geniturinariosCheckBox.isSelected()) {
             antecedentePersonalTemp = psqlAntecedentePersonal.obtenerPorIDS((short) 5, paciente);
            if (antecedentePersonalTemp == null) {
                psqlAntecedentePersonal.insertar((short) 5, paciente);
            } else if (antecedentePersonalTemp.getEnfermedad().getCodigo() == 5){
                mensaje += antecedentePersonalTemp.getEnfermedad().getEnfermedad() + " ya registrado \n";
            }
        }
        if (osteoartromusculuarCheckBox.isSelected()) {
             antecedentePersonalTemp = psqlAntecedentePersonal.obtenerPorIDS((short) 6, paciente);
            if (antecedentePersonalTemp == null) {
                psqlAntecedentePersonal.insertar((short) 6, paciente);
            } else if (antecedentePersonalTemp.getEnfermedad().getCodigo() == 6){
                mensaje += antecedentePersonalTemp.getEnfermedad().getEnfermedad() + " ya registrado \n";
            }
        }
        if (hemolinfopoyeticoCheckBox.isSelected()) {
             antecedentePersonalTemp = psqlAntecedentePersonal.obtenerPorIDS((short) 7, paciente);
            if (antecedentePersonalTemp == null) {
                psqlAntecedentePersonal.insertar((short) 7, paciente);
            } else if (antecedentePersonalTemp.getEnfermedad().getCodigo() == 7){
                mensaje += antecedentePersonalTemp.getEnfermedad().getEnfermedad() + " ya registrado \n";
            }
        }
        if (neuropsquiatricosCheckBox.isSelected()) {
             antecedentePersonalTemp = psqlAntecedentePersonal.obtenerPorIDS((short) 8, paciente);
            if (antecedentePersonalTemp == null) {
                psqlAntecedentePersonal.insertar((short) 8, paciente);
            } else if (antecedentePersonalTemp.getEnfermedad().getCodigo() == 8){
                mensaje += antecedentePersonalTemp.getEnfermedad().getEnfermedad() + " ya registrado \n";
            }
        }
        if (psicologicosCheckBox.isSelected()) {
             antecedentePersonalTemp = psqlAntecedentePersonal.obtenerPorIDS((short) 9, paciente);
            if (antecedentePersonalTemp == null) {
                psqlAntecedentePersonal.insertar((short) 9, paciente);
            } else if (antecedentePersonalTemp.getEnfermedad().getCodigo() == 9){
                mensaje += antecedentePersonalTemp.getEnfermedad().getEnfermedad() + " ya registrado \n";
            }
        }
        if (odontologicosCheckBox.isSelected()) {
             antecedentePersonalTemp = psqlAntecedentePersonal.obtenerPorIDS((short) 10, paciente);
            if (antecedentePersonalTemp == null) {
                psqlAntecedentePersonal.insertar((short) 10, paciente);
            } else if (antecedentePersonalTemp.getEnfermedad().getCodigo() == 10){
                mensaje += antecedentePersonalTemp.getEnfermedad().getEnfermedad() + " ya registrado \n";
            }
        }
        if (alergicosCheckBox.isSelected()) {
             antecedentePersonalTemp = psqlAntecedentePersonal.obtenerPorIDS((short) 11, paciente);
            if (antecedentePersonalTemp == null) {
                psqlAntecedentePersonal.insertar((short) 11, paciente);
            } else if (antecedentePersonalTemp.getEnfermedad().getCodigo() == 11){
                mensaje += antecedentePersonalTemp.getEnfermedad().getEnfermedad() + " ya registrado \n";
            }
        }
        if (neoplasticosCheckBox.isSelected()) {
             antecedentePersonalTemp = psqlAntecedentePersonal.obtenerPorIDS((short) 12, paciente);
            if (antecedentePersonalTemp == null) {
                psqlAntecedentePersonal.insertar((short) 12, paciente);
            } else if (antecedentePersonalTemp.getEnfermedad().getCodigo() == 12){
                mensaje += antecedentePersonalTemp.getEnfermedad().getEnfermedad() + " ya registrado \n";
            }
        }
        if (mfcCheckBox.isSelected()) {
             antecedentePersonalTemp = psqlAntecedentePersonal.obtenerPorIDS((short) 13, paciente);
            if (antecedentePersonalTemp == null) {
                psqlAntecedentePersonal.insertar((short) 13, paciente);
            } else if (antecedentePersonalTemp.getEnfermedad().getCodigo() == 13){
                mensaje += antecedentePersonalTemp.getEnfermedad().getEnfermedad() + " ya registrado \n";
            }
        }
        if (traumatismosCheckBox.isSelected()) {
             antecedentePersonalTemp = psqlAntecedentePersonal.obtenerPorIDS((short) 14, paciente);
            if (antecedentePersonalTemp == null) {
                psqlAntecedentePersonal.insertar((short) 14, paciente);
            } else if (antecedentePersonalTemp.getEnfermedad().getCodigo() == 14){
                mensaje += antecedentePersonalTemp.getEnfermedad().getEnfermedad() + " ya registrado \n";
            }
        }
        if (quemadurasCheckBox.isSelected()) {
             antecedentePersonalTemp = psqlAntecedentePersonal.obtenerPorIDS((short) 15, paciente);
            if (antecedentePersonalTemp == null) {
                psqlAntecedentePersonal.insertar((short) 15, paciente);
            } else if (antecedentePersonalTemp.getEnfermedad().getCodigo() == 15){
                mensaje += antecedentePersonalTemp.getEnfermedad().getEnfermedad() + " ya registrado \n";
            }
        }
        if (transfucionesCheckBox.isSelected()) {
             antecedentePersonalTemp = psqlAntecedentePersonal.obtenerPorIDS((short) 16, paciente);
            if (antecedentePersonalTemp == null) {
                psqlAntecedentePersonal.insertar((short) 16, paciente);
            } else if (antecedentePersonalTemp.getEnfermedad().getCodigo() == 16){
                mensaje += antecedentePersonalTemp.getEnfermedad().getEnfermedad() + " ya registrado \n";
            }
        }
        if (metabolicosCheckBox.isSelected()) {
             antecedentePersonalTemp = psqlAntecedentePersonal.obtenerPorIDS((short) 17, paciente);
            if (antecedentePersonalTemp == null) {
                psqlAntecedentePersonal.insertar((short) 17, paciente);
            } else if (antecedentePersonalTemp.getEnfermedad().getCodigo() == 17){
                mensaje += antecedentePersonalTemp.getEnfermedad().getEnfermedad() + " ya registrado \n";
            }
        }
        if (endocrinosCheckBox.isSelected()) {
             antecedentePersonalTemp = psqlAntecedentePersonal.obtenerPorIDS((short) 18, paciente);
            if (antecedentePersonalTemp == null) {
                psqlAntecedentePersonal.insertar((short) 18, paciente);
            } else if (antecedentePersonalTemp.getEnfermedad().getCodigo() == 18){
                mensaje += antecedentePersonalTemp.getEnfermedad().getEnfermedad() + " ya registrado \n";
            }
        }
        if (audiovisualesCheckBox.isSelected()) {
             antecedentePersonalTemp = psqlAntecedentePersonal.obtenerPorIDS((short) 19, paciente);
            if (antecedentePersonalTemp == null) {
                psqlAntecedentePersonal.insertar((short) 19, paciente);
            } else if (antecedentePersonalTemp.getEnfermedad().getCodigo() == 19){
                mensaje += antecedentePersonalTemp.getEnfermedad().getEnfermedad() + " ya registrado \n";
            }
        }
        if (mensaje.length() > 0) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Cuidado");
            alerta.setHeaderText("Atencion!!!!");
            alerta.setContentText(mensaje);
            alerta.initStyle(StageStyle.UTILITY);
            alerta.showAndWait();
        }
        okClicked = true;
        if (mensaje.length() == 0) {
            cargarLista();
        }
    }

    @FXML
    private void eliminar() {
        int index = enfermedadListView.getSelectionModel().getSelectedIndex();
        Enfermedad enfermedad = enfermedadListView.getSelectionModel().getSelectedItem();
        psqlAntecedentePersonal = new PSQLAntecedentePersonal();
        if (index >= 0) {
            try {
                psqlAntecedentePersonal.eliminar(enfermedad.getCodigo(), paciente);
                cargarLista();
            } catch (ExcepcionGeneral excepcionGeneral) {
                excepcionGeneral.printStackTrace();
            }
        } else {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error al Eliminar");
            alerta.setHeaderText("Error al quitar Enfermedad");
            alerta.setHeaderText("Ya no quedan elementos que eliminar");
            alerta.showAndWait();
        }
    }

    @FXML
    private void salir() {
        stage.close();
        okClicked = true;
    }

    private boolean checkboxValidacion() {
        psqlEnfermedades = new PSQLEnfermedades();
        String mensaje = "";
        String cadena = "";
        AntecedentePersonal antecedentePersonalTemp = psqlAntecedentePersonal.obtenerPorIDPaciente(paciente);
        psqlAntecedentePersonal = new PSQLAntecedentePersonal();
        if (cardiovascularesCheckBox.isSelected()) {
            for (Enfermedad enfermedad : antecedentePersonalTemp.getListaEnfermedades()) {
                if (enfermedad.getCodigo() == 1) {
                    mensaje += "Enfermedad ya registrado \n";
                } else {
                    psqlAntecedentePersonal.insertar((short) 1, paciente);
                    cadena += "1-";
                }
            }
        }
        if (respiratoriosCheckBox.isSelected()) {
            for (Enfermedad enfermedad : antecedentePersonalTemp.getListaEnfermedades()) {
                if (enfermedad.getCodigo() == 2) {
                    mensaje += "Enfermedad ya registrado \n";
                } else {
                    psqlAntecedentePersonal.insertar((short) 2, paciente);
                    cadena += "2-";
                }
            }
        }
        if (digestivosCheckBox.isSelected()) {
            for (Enfermedad enfermedad : antecedentePersonalTemp.getListaEnfermedades()) {
                if (enfermedad.getCodigo() == 4) {
                    mensaje += "Enfermedad ya registrado \n";
                } else {
                    psqlAntecedentePersonal.insertar((short) 4, paciente);
                    cadena += "4-";
                }
            }
        }
        if (geniturinariosCheckBox.isSelected()) {
            for (Enfermedad enfermedad : antecedentePersonalTemp.getListaEnfermedades()) {
                if (enfermedad.getCodigo() == 5) {
                    mensaje += "Enfermedad ya registrado \n";
                } else {
                    psqlAntecedentePersonal.insertar((short) 5, paciente);
                    cadena += "5-";
                }
            }
        }
        if (osteoartromusculuarCheckBox.isSelected()) {
            for (Enfermedad enfermedad : antecedentePersonalTemp.getListaEnfermedades()) {
                if (enfermedad.getCodigo() == 6) {
                    mensaje += "Enfermedad ya registrado \n";
                } else {
                    psqlAntecedentePersonal.insertar((short) 6, paciente);
                    cadena += "6-";
                }
            }
        }
        if (hemolinfopoyeticoCheckBox.isSelected()) {
            for (Enfermedad enfermedad : antecedentePersonalTemp.getListaEnfermedades()) {
                if (enfermedad.getCodigo() == 7) {
                    mensaje += "Enfermedad ya registrado \n";
                } else {
                    psqlAntecedentePersonal.insertar((short) 7, paciente);
                    cadena += "7-";
                }
            }
        }
        if (neuropsquiatricosCheckBox.isSelected()) {
            for (Enfermedad enfermedad : antecedentePersonalTemp.getListaEnfermedades()) {
                if (enfermedad.getCodigo() == 8) {
                    mensaje += enfermedad.getEnfermedad() + " ya registrado \n";
                } else {
                    psqlAntecedentePersonal.insertar((short) 8, paciente);
                    cadena += "8-";
                }
            }
        }
        if (psicologicosCheckBox.isSelected()) {
            for (Enfermedad enfermedad : antecedentePersonalTemp.getListaEnfermedades()) {
                if (enfermedad.getCodigo() == 9) {
                    mensaje += enfermedad.getEnfermedad() + " ya registrado \n";
                } else {
                    psqlAntecedentePersonal.insertar((short) 9, paciente);
                    cadena += "9-";
                }
            }
        }
        if (odontologicosCheckBox.isSelected()) {
            for (Enfermedad enfermedad : antecedentePersonalTemp.getListaEnfermedades()) {
                if (enfermedad.getCodigo() == 10) {
                    mensaje += enfermedad.getEnfermedad() + " ya registrado \n";
                } else {
                    psqlAntecedentePersonal.insertar((short) 10, paciente);
                    cadena += "10-";
                }
            }
        }
        if (alergicosCheckBox.isSelected()) {
            for (Enfermedad enfermedad : antecedentePersonalTemp.getListaEnfermedades()) {
                if (enfermedad.getCodigo() == 11) {
                    mensaje += enfermedad.getEnfermedad() + " ya registrado \n";
                } else {
                    psqlAntecedentePersonal.insertar((short) 11, paciente);
                    cadena += "11-";
                }
            }
        }
        if (neoplasticosCheckBox.isSelected()) {
            for (Enfermedad enfermedad : antecedentePersonalTemp.getListaEnfermedades()) {
                if (enfermedad.getCodigo() == 12) {
                    mensaje += enfermedad.getEnfermedad() + " ya registrado \n";
                } else {
                    psqlAntecedentePersonal.insertar((short) 12, paciente);
                    cadena += "12-";
                }
            }
        }
        if (mfcCheckBox.isSelected()) {
            for (Enfermedad enfermedad : antecedentePersonalTemp.getListaEnfermedades()) {
                if (enfermedad.getCodigo() == 13) {
                    mensaje += enfermedad.getEnfermedad() + " ya registrado \n";
                } else {
                    psqlAntecedentePersonal.insertar((short) 13, paciente);
                    cadena += "13-";
                }
            }
        }
        if (traumatismosCheckBox.isSelected()) {
            for (Enfermedad enfermedad : antecedentePersonalTemp.getListaEnfermedades()) {
                if (enfermedad.getCodigo() == 14) {
                    mensaje += enfermedad.getEnfermedad() + " ya registrado \n";
                } else {
                    psqlAntecedentePersonal.insertar((short) 14, paciente);
                    cadena += "14-";
                }
            }
        }
        if (quemadurasCheckBox.isSelected()) {
            for (Enfermedad enfermedad : antecedentePersonalTemp.getListaEnfermedades()) {
                if (enfermedad.getCodigo() == 15) {
                    mensaje += enfermedad.getEnfermedad() + " ya registrado \n";
                } else {
                    psqlAntecedentePersonal.insertar((short) 15, paciente);
                    cadena += "15-";
                }
            }
        }
        if (transfucionesCheckBox.isSelected()) {
            for (Enfermedad enfermedad : antecedentePersonalTemp.getListaEnfermedades()) {
                if (enfermedad.getCodigo() == 16) {
                    mensaje += enfermedad.getEnfermedad() + " ya registrado \n";
                } else {
                    psqlAntecedentePersonal.insertar((short) 16, paciente);
                    cadena += "16-";
                }
            }
        }
        if (metabolicosCheckBox.isSelected()) {
            for (Enfermedad enfermedad : antecedentePersonalTemp.getListaEnfermedades()) {
                if (enfermedad.getCodigo() == 17) {
                    mensaje += enfermedad.getEnfermedad() + " ya registrado \n";
                } else {
                    psqlAntecedentePersonal.insertar((short) 17, paciente);
                    cadena += "17-";
                }
            }
        }
        if (endocrinosCheckBox.isSelected()) {
            for (Enfermedad enfermedad : antecedentePersonalTemp.getListaEnfermedades()) {
                if (enfermedad.getCodigo() == 18) {
                    mensaje += enfermedad.getEnfermedad() + " ya registrado \n";
                } else {
                    psqlAntecedentePersonal.insertar((short) 18, paciente);
                    cadena += "18-";
                }
            }
        }
        if (audiovisualesCheckBox.isSelected()) {
            for (Enfermedad enfermedad : antecedentePersonalTemp.getListaEnfermedades()) {
                if (enfermedad.getCodigo() == 19) {
                    mensaje += enfermedad.getEnfermedad() + " ya registrado \n";
                } else {
                    psqlAntecedentePersonal.insertar((short) 19, paciente);
                    cadena += "19-";
                }
            }
        }
        if (mensaje.length() == 0) {
            return true;
        } else {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setHeaderText("Cuidado error en la seleccion");
            alerta.setContentText(mensaje);
            alerta.initStyle(StageStyle.UTILITY);
            alerta.showAndWait();
            return false;
        }
    }

    private void cargarLista() {
        psqlAntecedentePersonal = new PSQLAntecedentePersonal();
        AntecedentePersonal antecedentePersonalTemp = psqlAntecedentePersonal.obtenerPorIDPaciente(paciente);
        if (antecedentePersonalTemp != null) {
            enfermedadObservableList = FXCollections.observableList(antecedentePersonalTemp.getListaEnfermedades());
            enfermedadListView.setItems(enfermedadObservableList);
        }
    }
}

