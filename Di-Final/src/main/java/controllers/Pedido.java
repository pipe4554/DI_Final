package controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.*;
import exceptions.*;
import models.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Pedido {
	@FXML
	DatePicker fechaCreacion, fechaEntrega;
	@FXML
	TextField textCodigoPedido, textIdCliente;
	@FXML
	Button crearPedidoBtn;

	static PedidoDAO pd = new PedidoDAO();

	public models.Pedido createOrder() throws WrongOrderDayCreationException, WrongDeliveryDayCreationException{

		int codPedido = Integer.parseInt(textCodigoPedido.getText());
		int idCliente = Integer.parseInt(textIdCliente.getText());
		LocalDate fecha = fechaCreacion.getValue();
		Date fechaPedido = Date.from(fecha.atStartOfDay(ZoneId.systemDefault()).toInstant());
		LocalDate fecha2 = fechaEntrega.getValue();
		Date fechaEntrega = Date.from(fecha2.atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		models.Pedido p = new models.Pedido.PedidoBuilder(codPedido).setCod_cliente(idCliente).setFecha_creacion(fechaPedido).setFecha_entrega(fechaEntrega).build();
		
		return p;
	}

	@FXML
	public void crearPedido(ActionEvent actionEvent) throws WrongOrderDayCreationException, WrongDeliveryDayCreationException {

		pd.save(createOrder());
		
		for(models.Pedido p: pd.getAll()) {
			System.out.println(p.toString());
		}

		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle(" MENSAJE ");
		alert.setContentText("PEDIDO AÑADIDO CORRECTAMENTE");
		alert.showAndWait();

	}

	public void closeWindowsPedido() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuView.fxml"));
			Parent root = loader.load();
			Menu controlador = loader.getController();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
			stage.setOnCloseRequest(e -> controlador.closeWindowsMenu());
			Stage mystage = (Stage) this.crearPedidoBtn.getScene().getWindow();
			mystage.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}