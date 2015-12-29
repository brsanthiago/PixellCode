package com.wordpress.pixellcode.notification;

import java.io.IOException;

import com.wordpress.pixellcode.notification.PopOver.ArrowLocation;
import com.wordpress.pixellcode.util.LoadResource;

import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;

public class LoadNode {
	private static HBox node;
	private static PopOver popOver;
	private static AnchorPane anchor;

	public static void showPopOver(Node node, String msg) {
		popOver = new PopOver();
		try {
			popOver.setContentNode(getContent(msg));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		popOver.setArrowLocation(ArrowLocation.LEFT_CENTER);
		popOver.setAutoHide(true);
		popOver.setHideOnEscape(true);
		popOver.setArrowSize(8);
		popOver.setCornerRadius(0);
		popOver.show(node);
	}

	protected static Node getContent(String msg) {
		msg += "   ";
		node = new HBox();
		anchor = new AnchorPane();
		ImageView img = new ImageView(new Image("/image/info.png"));
		img.setFitWidth(40);
		img.setFitHeight(30);
		img.setSmooth(true);
		img.setPreserveRatio(true);
		Label label = new Label(msg, img);
		label.setLayoutX(5);
		label.setPrefHeight(40);
		label.setCenterShape(true);
		label.setFont(Font.font(15));
		label.alignmentProperty().setValue(Pos.BASELINE_CENTER);
		anchor.setPrefHeight(40);
		anchor.getChildren().add(label);
		node.getChildren().add(anchor);
		return node;
	}

}
