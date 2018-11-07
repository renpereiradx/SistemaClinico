package com.codedynamic.clinica.vista.productos;

import java.util.ArrayList;
import java.util.List;

import com.codedynamic.clinica.MainApp;
import com.codedynamic.clinica.dao.postgresql.PSQLCategoriaProducto;
import com.codedynamic.clinica.dao.postgresql.PSQLProducto;
import com.codedynamic.clinica.modelo.CategoriaProducto;
import com.codedynamic.clinica.modelo.Producto;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ProductoRegistroControlador {
	
	@FXML private JFXTextField nombreTextField;
	@FXML private JFXTextField precioTextField;
	@FXML private JFXTextField descripcionTextField;
	@FXML private JFXComboBox<String> tipoProductoCB;
	@FXML private JFXTextField stockTextField;
	
	private Producto producto;
	private Stage stage;
	private MainApp mainApp;
	private boolean okClicked = false;
	
	public boolean isOkClicked() {
		return okClicked;
	}
	
	public void setStage(Stage stage) { this.stage = stage;	}
	public void setMainApp(MainApp mainApp) { this.mainApp = mainApp; }
	
	private PSQLProducto psqlProducto;
	private PSQLCategoriaProducto psqlCategoriProducto;
	private ObservableList<String> categoriaProductoList = FXCollections.observableArrayList();
	
	@FXML private void initialize() {
		psqlCategoriProducto = new PSQLCategoriaProducto();
		//List<CategoriaProducto> listaCategoria = new ArrayList(psqlCategoriProducto.obtenerLista());
		for (CategoriaProducto categoriaProducto : psqlCategoriProducto.obtenerLista()) {
			categoriaProductoList.add(categoriaProducto.getCategoria());
		}
		tipoProductoCB.getItems().addAll(categoriaProductoList);
	}
	
	public void setProducto(Producto producto) {
		this.producto = producto;
		if (producto.getNombre() != null) {
			nombreTextField.setText(producto.getNombre());
			precioTextField.setText(Integer.toString(producto.getPrecio()));
			descripcionTextField.setText(producto.getDescripcion());
			stockTextField.setText(Integer.toString(producto.getStock()));
		}
	}
	
	@FXML
	private void aceptar() {
		PSQLCategoriaProducto psqlCategoriaProducto = new PSQLCategoriaProducto();
		if (validarDatos()) {
			producto.setNombre(nombreTextField.getText());
			if (!(precioTextField.getText().isEmpty() || precioTextField.getText() == null)) {
				producto.setPrecio(Integer.parseInt(precioTextField.getText()));
			}
			producto.setDescripcion(descripcionTextField.getText());
			if (tipoProductoCB.getSelectionModel().getSelectedItem().equals("MEDICAMENTOS")) {
				producto.setCategoriaProducto(psqlCategoriaProducto.obtenerPorID((short) 1));	
			} else if (tipoProductoCB.getSelectionModel().getSelectedItem().equals("ENFERMERIA")) {
				producto.setCategoriaProducto(psqlCategoriaProducto.obtenerPorID((short) 2));
			}
			if (!(stockTextField.getText().isEmpty() || stockTextField.getText() == null)) {
				producto.setStock(Integer.parseInt(stockTextField.getText()));
			}
			okClicked = true;
			stage.close();
		}
		
	}
	
	@FXML private void cancelar() { stage.close(); }
	
	private boolean validarDatos() {
		String error = "";
		String categoria = tipoProductoCB.getSelectionModel().getSelectedItem();
		if (nombreTextField.getText().isEmpty() || nombreTextField.getText() == null) {
			error += "Nombre de Producto vacio\n";
		}
		if (categoria == null) {
			error += "Categoria Producto no seleccionado\n";
		}
		if (error.length() == 0) {
			return true;			
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Error al procesar algunos datos");
			alert.setContentText(error);
			alert.initStyle(StageStyle.DECORATED);
			alert.showAndWait();
			return false;
		}
	}
}