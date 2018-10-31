package com.codedynamic.clinica;

import javafx.application.Application;
import javafx.stage.Stage;

import com.codedynamic.clinica.dao.postgresql.Autenticacion;
import com.codedynamic.clinica.modelo.*;
import com.codedynamic.clinica.vista.*;
import com.codedynamic.clinica.vista.medico.IndicacionesMedicasControlador;
import com.codedynamic.clinica.vista.medico.MedicoMenuControlador;
import com.codedynamic.clinica.vista.medico.MedicoPrincipalControlador;
import com.codedynamic.clinica.vista.medico.RegistroMedicosControlador;
import com.codedynamic.clinica.vista.paciente.PacienteDescripcionControlador;
import com.codedynamic.clinica.vista.paciente.PacienteMenuLeftControlador;
import com.codedynamic.clinica.vista.paciente.PacienteRegistroControlador;
import com.codedynamic.clinica.vista.paciente.antecedente.AntecedenteFamiliarControlador;
import com.codedynamic.clinica.vista.paciente.antecedente.AntecedenteFemeninoControlador;
import com.codedynamic.clinica.vista.paciente.antecedente.AntecedenteGinecControlador;
import com.codedynamic.clinica.vista.paciente.antecedente.AntecedenteMasculinoControlador;
import com.codedynamic.clinica.vista.paciente.antecedente.AntecedenteMasculinoDialogoControlador;
import com.codedynamic.clinica.vista.paciente.antecedente.AntecedentePersonalControlador;
import com.codedynamic.clinica.vista.paciente.antecedente.AntecedentePrincipalControlador;
import com.codedynamic.clinica.vista.paciente.antecedente.AntecedenteQuirurgicoControlador;
import com.codedynamic.clinica.vista.productos.ProductoMenuControlador;
import com.codedynamic.clinica.vista.productos.ProductoPrincipalControlador;
import com.codedynamic.clinica.vista.productos.ProductoRegistroControlador;
import com.codedynamic.clinica.vista.turno.SolicitudTurnoControlador;
import com.codedynamic.clinica.vista.turno.SolicitudTurnoFechaControlador;
import com.codedynamic.clinica.vista.turno.TurnoDescripcionControlador;
import com.codedynamic.clinica.vista.turno.TurnoMenuLeftControlador;
import com.codedynamic.clinica.vista.turno.TurnoPrincipalControlador;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;

import java.io.IOException;

public class MainApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private Stage primaryStage;
    private Usuario usuarioLoggeado;
    private BorderPane contenedorPrincipal;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Sistema Clinico");
        iniciarLogin();
    }

    public void iniciarLogin() {
            try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("vista/Login.fxml"));
            AnchorPane showLogin = loader.load();
            Scene scene = new Scene(showLogin);
            primaryStage.setScene(scene);
            LoginControlador controlador = loader.getController();
            controlador.setAppPrincipal(this);
            controlador.setLoginStage(primaryStage);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void iniciarContenedorPrincipal() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("vista/ContenedorPrincipal.fxml"));
            contenedorPrincipal = loader.load();
            Scene scene = new Scene(contenedorPrincipal);
            primaryStage.setScene(scene);
            primaryStage.show();
            showHome();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showHome() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("vista/Home.fxml"));
            BorderPane homePane = loader.load();
            contenedorPrincipal.setCenter(homePane);
            HomeControlador controlador = loader.getController();
            controlador.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean usuarioLogin(String usuario, String clave) {
        Autenticacion at = new Autenticacion();
        if (at.confirmarUsuario(usuario, clave) != null) {
            usuarioLoggeado = at.confirmarUsuario(usuario, clave);
            iniciarContenedorPrincipal();
            return true;
        } else {
            return false;
        }
    }

    public void showPacienteDescripcion() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("vista/paciente/PacienteDescripcion.fxml"));
            BorderPane pacientePane = loader.load();
            contenedorPrincipal.setCenter(pacientePane);
            PacienteDescripcionControlador controlador = loader.getController();
            controlador.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean mostrarDialogoRegistroPaciente(Paciente paciente) {
    	try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("vista/paciente/PacienteRegistro.fxml"));
			AnchorPane pageDialog = loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Registro de Pacientes");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(pageDialog);
			dialogStage.setScene(scene);
			PacienteRegistroControlador controlador = loader.getController();
			controlador.setStage(dialogStage);
			controlador.setPaciente(paciente);
			controlador.setMainApp(this);
			dialogStage.showAndWait();
			return controlador.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
    }
    
    public void mostrarPacienteMenuLeft() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("vista/paciente/PacienteMenuLeft.fxml"));
            AnchorPane menuLeftPane = loader.load();
            contenedorPrincipal.setLeft(menuLeftPane);
            PacienteMenuLeftControlador controlador = loader.getController();
            controlador.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarAntecedentePrincipal() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("vista/paciente/antecedente/AntecedentePrincipal.fxml"));
            AnchorPane pane = loader.load();
            contenedorPrincipal.setCenter(pane);
            AntecedentePrincipalControlador controlador = loader.getController();
            controlador.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarAntecedenteFemenino(Paciente paciente) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("vista/paciente/antecedente/AntecedenteFemenino.fxml"));
            AnchorPane dialogoPane = loader.load();
            contenedorPrincipal.setCenter(dialogoPane);
            AntecedenteFemeninoControlador controlador = loader.getController();
            controlador.setMainApp(this);
            controlador.setPaciente(paciente);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarAntecedenteMasculino(Paciente paciente) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("vista/paciente/antecedente/AntecedenteMasculino.fxml"));
            AnchorPane masculinoPane = loader.load();
            contenedorPrincipal.setCenter(masculinoPane);
            AntecedenteMasculinoControlador controlador = loader.getController();
            controlador.setMainApp(this);
            controlador.setPaciente(paciente);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean mostrarDialogoAntecedentePersonal(Paciente paciente) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("vista/paciente/antecedente/AntecedentePersonal.fxml"));
            AnchorPane panel = loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Antecedente Personal");
            dialogStage.initOwner(primaryStage);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(panel);
            dialogStage.setScene(scene);
            AntecedentePersonalControlador controlador = loader.getController();
            controlador.setStage(dialogStage);
            controlador.setPaciente(paciente);
            dialogStage.showAndWait();
            return controlador.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean mostrarDialogoAntecedenteQuirurgico(Paciente paciente) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("vista/paciente/antecedente/AntecedenteQuirurgico.fxml"));
            AnchorPane dialogoPane = loader.load();
            Stage dialogoStage = new Stage();
            dialogoStage.setTitle("Antecedentes Quirurgicos-Internaciones");
            dialogoStage.initOwner(primaryStage);
            dialogoStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(dialogoPane);
            dialogoStage.setScene(scene);
            AntecedenteQuirurgicoControlador controlador = loader.getController();
            controlador.setPaciente(paciente);
            controlador.setStage(dialogoStage);
            dialogoStage.showAndWait();
            return controlador.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean mostrarDialogoAntecedenteFamiliar(Paciente paciente) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("vista/paciente/antecedente/AntecedenteFamiliar.fxml"));
            AnchorPane dialogoPane = loader.load();
            Stage dialogoStage = new Stage();
            dialogoStage.setTitle("Antecedente Familiar");
            dialogoStage.initOwner(primaryStage);
            dialogoStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(dialogoPane);
            dialogoStage.setScene(scene);
            AntecedenteFamiliarControlador controlador = loader.getController();
            controlador.setStage(dialogoStage);
            controlador.setPaciente(paciente);
            dialogoStage.showAndWait();
            return controlador.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean mostrarDialogoAntecedenteGinec(Paciente paciente) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("vista/paciente/antecedente/AntecedenteGinec.fxml"));
            AnchorPane dialogoPane = loader.load();
            Stage dialogoStage = new Stage();
            dialogoStage.setTitle("Antecedente Femenino");
            dialogoStage.initOwner(primaryStage);
            dialogoStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(dialogoPane);
            dialogoStage.setScene(scene);
            AntecedenteGinecControlador controlador = loader.getController();
            controlador.setPaciente(paciente);
            controlador.setStage(dialogoStage);
            dialogoStage.showAndWait();
            return controlador.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean mostrarDialogoAntecedenteMasculino(Paciente paciente) {
    	try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("vista/paciente/antecedente/AntecedenteMasculinoDialogo.fxml"));
			AnchorPane dialogoPane = loader.load();
			Stage dialogoStage = new Stage();
			dialogoStage.setTitle("Antecedente Masculino");
			dialogoStage.initOwner(primaryStage);
			dialogoStage.initModality(Modality.WINDOW_MODAL);
			Scene scene = new Scene(dialogoPane);
			dialogoStage.setScene(scene);
			AntecedenteMasculinoDialogoControlador controlador = loader.getController();
			controlador.setStage(dialogoStage);
			controlador.setPaciente(paciente);
			dialogoStage.showAndWait();
			return controlador.isOkClicked();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
    }
    
    public void mostrarTurnoPrincipal() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("vista/turno/TurnoPrincipal.fxml"));
            AnchorPane turnoPrincipal = loader.load();
            contenedorPrincipal.setCenter(turnoPrincipal);
            TurnoPrincipalControlador controlador = loader.getController();
            controlador.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarTurnoMenuLeft() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("vista/turno/TurnoMenuLeft.fxml"));
            AnchorPane menuLeftPane = loader.load();
            contenedorPrincipal.setLeft(menuLeftPane);
            TurnoMenuLeftControlador controlador = loader.getController();
            controlador.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean mostrarDialogSolicitudTurno(Turno turno) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("vista/turno/SolicitudTurno.fxml"));
            AnchorPane panelTurno = loader.load();
            Stage stageTurno = new Stage();
            stageTurno.setTitle("Nuevo Turno");
            stageTurno.initOwner(primaryStage);
            Scene sceneTurno = new Scene(panelTurno);
            stageTurno.setScene(sceneTurno);
            SolicitudTurnoControlador controlador = loader.getController();
            controlador.setStage(stageTurno);
            controlador.setTurno(turno);
            stageTurno.showAndWait();
            return controlador.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean mostrarDialogoTurnoFecha(Turno turno) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("vista/turno/SolicitudTurnoFecha.fxml"));
            AnchorPane dialogFechaTurno = loader.load();
            Stage stageTurnoFecha = new Stage();
            stageTurnoFecha.setTitle("Fecha Turno");
            stageTurnoFecha.initOwner(primaryStage);
            Scene scene = new Scene(dialogFechaTurno);
            stageTurnoFecha.setScene(scene);
            SolicitudTurnoFechaControlador controlador = loader.getController();
            controlador.setStage(stageTurnoFecha);
            controlador.setTurno(turno);
            stageTurnoFecha.showAndWait();
            return controlador.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public void mostrarDialogoTurnoDescripcion(Turno turno) {
    	try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("vista/turno/TurnoDescripcion.fxml"));
			AnchorPane dialogoPane = loader.load();
			Stage dialogoStage = new Stage();
			dialogoStage.setTitle("Descripcion");
			dialogoStage.initOwner(primaryStage);
			Scene scene = new Scene(dialogoPane);
			dialogoStage.setScene(scene);
			TurnoDescripcionControlador controlador = loader.getController();
			controlador.setStage(dialogoStage);
			controlador.setTurno(turno);
			dialogoStage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public void mostrarMedicoPrincipal() {
    	try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("vista/medico/MedicoPrincipal.fxml"));
			AnchorPane medicoPane = loader.load();
			contenedorPrincipal.setCenter(medicoPane);
			MedicoPrincipalControlador controlador = loader.getController();
			controlador.setMainApp(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public void mostrarMedicoMenu() {
    	try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("vista/medico/MedicoMenu.fxml"));
			AnchorPane menuPane = loader.load();
			contenedorPrincipal.setLeft(menuPane);
			MedicoMenuControlador controlador = loader.getController();
			controlador.setMainApp(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public void mostrarRegistroDatosMedicos(Atencion atencion, Paciente paciente) {
    	try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("vista/medico/RegistroDeDatosMedicos.fxml"));
			AnchorPane registroPane = loader.load();
			contenedorPrincipal.setCenter(registroPane);
			RegistroMedicosControlador controlador = loader.getController();
			controlador.setMainApp(this);
			controlador.setAtencion(atencion);
			controlador.setPaciente(paciente);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public void mostrarIndicacionesMedicas(DetalleAtencion detalleAtencion, Paciente paciente) {
    	try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("vista/medico/IndicacionesMedicas.fxml"));
			AnchorPane indicacionesPane = loader.load();
			contenedorPrincipal.setCenter(indicacionesPane);
			IndicacionesMedicasControlador controlador = loader.getController();
			controlador.setDetalleAtencion(detalleAtencion);
			controlador.setMainApp(this);
			controlador.setPaciente(paciente);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public void mostrarProductoPrincial() {
    	try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("vista/productos/ProductoPrincipal.fxml"));
			AnchorPane productoPane = loader.load();
			contenedorPrincipal.setCenter(productoPane);
			ProductoPrincipalControlador controlador = loader.getController();
			controlador.setMainApp(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public void mostrarProductoMenu() {
    	try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("vista/productos/ProductoMenu.fxml"));
			AnchorPane menuPane = loader.load();
			contenedorPrincipal.setLeft(menuPane);
			ProductoMenuControlador controlador = loader.getController();
			controlador.setMainApp(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public boolean mostrarProductoRegistro(Producto producto) {
    	try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("vista/productos/ProductoRegistro.fxml"));
			AnchorPane registroPane = loader.load();
			Stage registroStage = new Stage();
			registroStage.setTitle("Registro de Productos");
			registroStage.initOwner(primaryStage);
			Scene scene = new Scene(registroPane);
			registroStage.setScene(scene);
			ProductoRegistroControlador controlador = loader.getController();
			controlador.setMainApp(this);
			controlador.setProducto(producto);
			controlador.setStage(registroStage);
			registroStage.showAndWait();
			return controlador.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
    }
    
    public void mostrarProveedorPrincipal() {
    	try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("vista/proveedores/ProveedorPrincipal.fxml"));
			AnchorPane proveedorPane = loader.load();
			contenedorPrincipal.setCenter(proveedorPane);	
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    

    public BorderPane getContenedorPrincipal() {
        return contenedorPrincipal;
    }

    public Usuario getUsuarioLoggeado() {
        return usuarioLoggeado;
    }
}