package com.codedynamic.clinica.vista;

import com.codedynamic.clinica.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginControlador {
    @FXML
    private TextField usuarioField;
    @FXML
    private PasswordField claveField;
    @FXML
    private Label mensajeLabel;
    private MainApp appPrincipal;
    private Stage loginStage;

    public LoginControlador() {}

    public void setAppPrincipal(MainApp appPrincipal) {
        this.appPrincipal = appPrincipal;
    }

    public void setLoginStage(Stage stage) {
        this.loginStage = stage;
    }

    @FXML
    private void initialize() {}

    @FXML
    public void handleOk() {
        if (appPrincipal.usuarioLogin(usuarioField.getText(), claveField.getText())) {
            mensajeLabel.setText("Bienvenido " + usuarioField.getText());
        } else {
            mensajeLabel.setText("Usuario o Clave incorrectas");
        }
    }

    @FXML
    public void handleCancel() {
        loginStage.close();
    }
}