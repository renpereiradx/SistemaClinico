package com.codedynamic.clinica.vista.tratamientos;

import com.codedynamic.clinica.modelo.FacturaTratamientoDetalle;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class TratamientoPrincipalControlador {

	@FXML
	private TableView<FacturaTratamientoDetalle> tablaFacturaDetalle;
	@FXML
	private TableColumn<FacturaTratamientoDetalle, Number> columnaCodigo;
	@FXML
	private TableColumn<FacturaTratamientoDetalle, String> columnaNombre;
	@FXML
	private TableColumn<FacturaTratamientoDetalle, Number> columnaSesiones;
	
	
}
