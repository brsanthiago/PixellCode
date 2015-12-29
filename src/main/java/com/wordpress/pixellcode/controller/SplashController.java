package com.wordpress.pixellcode.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.wordpress.pixellcode.configuration.PixellCode;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SplashController implements Initializable {
	@FXML
	private Text txtNomeApp;

	@FXML
	private ProgressBar pgbStatus;

	@FXML
	private Label lblVersao;

	private Service<Integer> service;

	private Alert alerta;

	private PixellCode app = new PixellCode();

	private Stage stage;

	public void initialize(URL location, ResourceBundle resources) {
		updateStatus();
	}

	protected void updateStatus() {
		service = new Service<Integer>() {
			@Override
			protected Task<Integer> createTask() {
				return new Task<Integer>() {
					@Override
					protected Integer call() throws Exception {
						for (int i = 0; i < 100; i++) {
							// Atualiza o Progress do Service
							updateProgress(i, 100);

							/*
							 * Ao invés de usar um Sleep, poderiamos executar um
							 * método ou criar um bloco de código validando
							 * alguma informação... Faremos isso em outros
							 * exemplos.
							 */
							Thread.sleep(50);
						}
						return 100;
					}
				};
			}
		};
		service.start();

		// Atualiza o ProgressBar de acordo com o Progress do Service
		pgbStatus.progressProperty().bind(service.progressProperty());

		// Em caso de sucesso executa essa ação
		service.setOnSucceeded(e -> {
			initLogin();
		});
		// Em caso de falha executa essa ação
		service.setOnFailed(e -> {
			alerta = new Alert(AlertType.ERROR);
			alerta.setTitle("Atenção");
			alerta.setContentText("Não foi possível realizar o carregamento do Splash.");
			alerta.setHeaderText("Erro encontrado: " + e.getEventType().getName());
			alerta.show();
		});
	}

	protected void initLogin() {
		app.initStage(stage, lblVersao.getScene().getWindow(), "/fxml/Login.fxml", false, StageStyle.TRANSPARENT,
				false);
	}
}
