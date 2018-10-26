package com.codedynamic.clinica.vista.productos;

import com.codedynamic.clinica.MainApp;
import com.codedynamic.clinica.dao.postgresql.PSQLProducto;
import com.codedynamic.clinica.modelo.Producto;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ProductoPrincipalControlador {
	
	@FXML private TableView<Producto> tablaProducto;
	@FXML private TableColumn<Producto, Number> columnaCodigo;
	@FXML private TableColumn<Producto, String> columnaNombre;
	@FXML private JFXTextField nombreProductoTextField;
	@FXML private Label codigoLabel;
	@FXML private Label nombreLabel;
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
	
	@FXML private void nuevoProducto() {
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
	
	@FXML private void buscarProductoNombre() {
		psqlProducto = new PSQLProducto();
		lista.addAll(psqlProducto.obtenerProductoNombre(nombreProductoTextField.getText()));
		if (!(nombreProductoTextField.getText().length() == 0 || nombreProductoTextField.getText() == null)) {
			if (!lista.isEmpty()) {
				tablaProducto.setItems(lista);
			} else {
				nombreProductoTextField.setText("Digite Nombre del Producto");
			}
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
			descripcionLabel.setText(producto.getDescripcion());
			tipoLabel.setText(producto.getCategoriaProducto().getCategoria());
			stockLabel.setText(Integer.toString(producto.getStock()));
		} else {
			codigoLabel.setText("");
			nombreLabel.setText("");
			descripcionLabel.setText("");
			tipoLabel.setText("");
			stockLabel.setText("");
		}
	}
}
