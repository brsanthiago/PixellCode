package com.wordpress.pixellcode.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.wordpress.pixellcode.configuration.PixellCode;
import com.wordpress.pixellcode.notification.LoadNode;
import com.wordpress.pixellcode.notification.PopOver;
import com.wordpress.pixellcode.notification.PopOver.ArrowLocation;
import com.wordpress.pixellcode.util.LoadResource;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class LoginController implements Initializable {

	@FXML
	private TextField txtLogin;

	@FXML
	private PasswordField txtPassword;

	@FXML
	private Button btnCancel;

	@FXML
	private Button btnLogin;

	@FXML
	private AnchorPane paneDrag;

	@FXML
	private Text lblTitulo;
	@FXML
	private Label lbPass, lbName;

	@FXML
	private Button minimize;

	@FXML
	private Button maximize;

	@FXML
	private Button close;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		actionButtons();
		validInput();
		btnLogin.setOnAction(e -> {
			if (txtLogin.textProperty().isEmpty().get()) {
				LoadNode.showPopOver(lbName, "Login inválido.");
			}
			if (txtPassword.textProperty().isEmpty().get()) {
				LoadNode.showPopOver(lbPass, "Senha inválida.");
			}
		});
	}

	@SuppressWarnings({ "static-access", "static-access" })
	protected void validInput() {
		txtPassword.disabledProperty().booleanExpression(txtLogin.textProperty().isEmpty());
		btnLogin.disabledProperty().booleanExpression(txtLogin.textProperty().isEmpty())
				.or(txtPassword.textProperty().isEmpty());
	}

	protected void actionButtons() {
		close.setOnAction(e -> {
			Platform.exit();
			System.exit(0);
		});
		minimize.setOnAction(e -> {
			PixellCode.getStage().setIconified(true);
		});
		maximize.setDisable(true);
		maximize.setMouseTransparent(true);
	}
}
