package controllers;

import java.io.IOException;
import java.util.ArrayList;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Cliente {
	@FXML
	TextField textId, textNombre, textApellido, textTlf,textEmail, textPass, textDocumento;
	@FXML
	RadioButton dni, nie;
	@FXML
	Button crearBtn;

	static ClienteDAO cd = new ClienteDAO();

	public models.Cliente crearCliente() throws WrongClientCreateException {

		int id = Integer.parseInt(textId.getText());
		String nombre = textNombre.getText();
		String ape = textApellido.getText();
		String tlf = textTlf.getText();
		String email = textEmail.getText();
		String documento = textDocumento.getText();
		String pass = textPass.getText();
		TipoDocumento t = dni.isSelected() ? TipoDocumento.DNI : TipoDocumento.NIE;

		
		models.Cliente c = new models.Cliente.ClienteBuilder(id).setTipoDocumentationn(t).setDocumentation(documento).setNombre(nombre).setApellido(ape).setTelefono(tlf).
				setEmail(email).setPassword(pass).build();
		
		return c;
	}

	@FXML
	public void crearCli(ActionEvent actionEvent) throws WrongClientCreateException  {
		

		cd.save(crearCliente());
		
		for(models.Cliente c: cd.getAll()) {
			System.out.println(c.toString());
		}

		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle(" MENSAJE ");
		alert.setContentText("CLIENTE AÑADIDO CORRECTAMENTE");
		alert.showAndWait();
	}

	public void closeWindowsCliente() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuView.fxml"));
			Parent root = loader.load();
			Menu controlador = loader.getController();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
			stage.setOnCloseRequest(e -> controlador.closeWindowsMenu());
			Stage mystage = (Stage) this.crearBtn.getScene().getWindow();
			mystage.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}