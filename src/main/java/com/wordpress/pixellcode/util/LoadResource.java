package com.wordpress.pixellcode.util;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LoadResource {

	protected static String FXML_PATH = "/fxml/";
	protected static String IMG_PATH = "/image/";

	public static String NOTIFICATION_FXML = FXML_PATH + "Notification.fxml";
	public static String SPLASH_FXML = FXML_PATH + "Splash.fxml";
	public static String ACTION_BAR = FXML_PATH + "ActionBar.fxml";

	public static String CLOSE_HOVER = IMG_PATH + "close-hover.png";

	public static ImageView ERROR_ICON = new ImageView(getImage(CLOSE_HOVER));

	protected static Image getImage(String url) {
		return new Image(url);
	}

}
