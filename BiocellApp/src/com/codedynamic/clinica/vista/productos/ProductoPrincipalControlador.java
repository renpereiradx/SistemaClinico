package com.codedynamic.clinica.vista.productos;

import com.codedynamic.clinica.MainApp;
import com.codedynamic.clinica.dao.postgresql.PSQLProducto;
import com.codedynamic.clinica.modelo.Producto;
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

public class ProductoPrincipalControlador {
	
	@FXML private TableView<Producto> tablaProducto;
	@FXML private TableColumn<Producto, Number> columnaCodigo;
	@FXML private TableColumn<Producto, String> columnaNombre;
	@FXML private JFXTextField nombreProductoTextField;
	@FXML private Label codigoLabel;
	@FXML private Label nombreLabel;
	@FXML private Label precioLabel;
	@FXML private Label descripcionLabel;
	@FXML private Label tipoLabel;
	@FXML private Label stockLabel;
	
	private MainApp mainApp;
	
	private ObservableList<Producto> lista = FXCollections.observableArrayList();
	private PSQLProducto psqlProducto;
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	@FXML private void initialize() {
		mostrarDetallesProductos(null);
		mostrarProducto();
	}
	
	@FXML
	private void nuevoProducto() {
		Producto producto = new Producto();
		psqlProducto = new PSQLProducto();
		boolean okClicked = mainApp.mostrarProductoRegistro(producto);
		if (okClicked) {
			lista.add(producto);
			psqlProducto.insertar(producto);
			if (tablaProducto.getSelectionModel().getSelectedIndex() < 0) {
				tablaProducto.setItems(lista);
			}
		}
	}
	
	@FXML
	private void editarProducto() {
		Producto producto = tablaProducto.getSelectionModel().getSelectedItem();		
		if (producto != null) {
			boolean okClicked = mainApp.mostrarProductoRegistro(producto);
			if (okClicked) {
				psqlProducto = new PSQLProducto();
				psqlProducto.modificar(producto);
				mostrarDetallesProductos(producto);
			}
		} else {
			Alert alerta = new Alert(AlertType.WARNING);
			alerta.setTitle("ATENCION");
			alerta.setHeaderText("Producto no seleccionado");
			alerta.setContentText("Favor seleccione un producto en la tabla");
			alerta.initStyle(StageStyle.UTILITY);
			alerta.showAndWait();
		}
	}
	
	@FXML
	private void eliminarProducto() {
		Producto producto = tablaProducto.getSelectionModel().getSelectedItem();
		int index = tablaProducto.getSelectionModel().getSelectedIndex();
		if (index >= 0) {
			psqlProducto = new PSQLProducto();
			psqlProducto.insertar(producto);
			tablaProducto.getItems().remove(index);
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ATENCION");
			alert.setHeaderText("Tabla Vacia");
			alert.setContentText("Ya no quedan elementos por eliminar");
			alert.initStyle(StageStyle.DECORATED);
			alert.showAndWait();
		}
	}
	
	@FXML
	private void buscarProductoNombre() {
		psqlProducto = new PSQLProducto();
		if (!nombreProductoTextField.getText().isEmpty()) {
			lista.addAll(psqlProducto.obtenerProductoNombre(nombreProductoTextField.getText()));
			if (!lista.isEmpty()) {
				tablaProducto.setItems(lista);
			}
		} else {
			nombreProductoTextField.setText("Digite Nombre del Producto");
		}
	}
	
	@FXML
	private void listarProductos() {
		psqlProducto = new PSQLProducto();
		tablaProducto.getItems().removeAll(lista);
		lista.addAll(psqlProducto.obtenerListaProducto());
		tablaProducto.setItems(lista);
	}
	
	@FXML
	private void limpiarTabla() {
		if (!tablaProducto.getItems().isEmpty()) {
			tablaProducto.getItems().removeAll(lista);
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROR");
			alert.setHeaderText("No se puede limpiar Tabla");
			alert.setContentText("Asegurese que haya 1 o mas elementos en la tabla antes de limpiar");
			alert.initStyle(StageStyle.DECORATED);
			alert.showAndWait();
		}
	}
	
	private void mostrarProducto() {
		columnaCodigo.setCellValueFactory(datoCelda -> datoCelda.getValue().idProductoProperty());
		columnaNombre.setCellValueFactory(datoCelda -> datoCelda.getValue().nombreProperty());
		tablaProducto.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> mostrarDetallesProductos(newValue));
	}
	
	private void mostrarDetallesProductos(Producto producto) {
		if (producto != null) {
			codigoLabel.setText(Integer.toString(producto.getIdProducto()));
			nombreLabel.setText(producto.getNombre());
			precioLabel.setText(Integer.toString(producto.getPrecio()));
			descripcionLabel.setText(producto.getDescripcion());
			tipoLabel.setText(producto.getCategoriaProducto().getCategoria());
			stockLabel.setText(Integer.toString(producto.getStock()));
		} else {
			codigoLabel.setText("");
			nombreLabel.setText("");
			precioLabel.setText("");
			descripcionLabel.setText("");
			tipoLabel.setText("");
			stockLabel.setText("");
		}
	}
}
