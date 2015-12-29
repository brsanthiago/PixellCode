package com.wordpress.pixellcode.configuration;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.BoundingBox;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import javafx.util.Callback;

public class PixellCode {
	private static Stage stg;
	private static boolean maximized = false;
	private static BoundingBox savedBounds;

	public PixellCode() {
	}

	public void initStage(Stage stage, Window lb, String load, boolean resize, StageStyle style, boolean maximized) {

		try {
			Stage st = new Stage();
			stage = (Stage) lb;
			Parent root = FXMLLoader.load(getClass().getResource(load));
			Scene scene = new Scene(root);
			st.initStyle(style);
			st.setResizable(resize);
			st.setMaximized(maximized);
			st.setTitle("PixellCode");
			st.setScene(scene);
			st.centerOnScreen();
			st.show();
			setStage(st);
			stageAction(st);
			stage.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Stage getStage() {
		return stg;
	}

	public static void setStage(Stage stage) {
		stg = stage;
	}

	public static void stageAction(Stage stage) {
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				Alert alerta = new Alert(AlertType.CONFIRMATION);
				alerta.setTitle("Atenção");
				alerta.setHeaderText("Deseja realmente fechar o sistema?");
				alerta.setContentText(null);
				alerta.initOwner(stage);

				ButtonType buttonCancel = new ButtonType("Não", ButtonData.CANCEL_CLOSE);
				ButtonType buttonOk = new ButtonType("Sim", ButtonData.OK_DONE);

				alerta.getButtonTypes().setAll(buttonCancel, buttonOk);
				alerta.setResultConverter(new Callback<ButtonType, ButtonType>() {
					@Override
					public ButtonType call(ButtonType param) {
						if (param == buttonOk) {
							System.exit(0);
						} else {
							event.consume();
						}
						return param;
					}
				});
				alerta.showAndWait();
			}
		});
	}

	public static void maximizeOrRestore() {

		if (maximized) {
			restoreSavedBounds(getStage());
			savedBounds = null;
			maximized = false;
		} else {
			ObservableList<Screen> screensForRectangle = Screen.getScreensForRectangle(getStage().getX(),
					getStage().getY(), getStage().getWidth(), getStage().getHeight());
			Screen screen = screensForRectangle.get(0);
			Rectangle2D visualBounds = screen.getVisualBounds();

			savedBounds = new BoundingBox(getStage().getX(), getStage().getY(), getStage().getWidth(),
					getStage().getHeight());

			getStage().setX(visualBounds.getMinX());
			getStage().setY(visualBounds.getMinY());
			getStage().setWidth(visualBounds.getWidth());
			getStage().setHeight(visualBounds.getHeight());
			maximized = true;
		}
	}

	public static void restoreSavedBounds(Stage stage) {
		stage.setX(savedBounds.getMinX());
		stage.setY(savedBounds.getMinY());
		stage.setWidth(savedBounds.getWidth());
		stage.setHeight(savedBounds.getHeight());
		savedBounds = null;
	}
}
