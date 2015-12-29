package com.wordpress.pixellcode.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SplashApp extends Application {

	public static void main(String[] args) {
		launch(SplashApp.class, args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/fxml/Splash.fxml"));
		stage = new Stage();
		stage.setScene(new Scene(root));
		stage.initStyle(StageStyle.TRANSPARENT);
		stage.setTitle("PixellCode - Splash Screen - JavaFX");
		stage.show();
	}

}
