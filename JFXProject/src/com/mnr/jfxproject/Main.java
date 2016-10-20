package com.mnr.jfxproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception{
	
//			Group root = new Group();
//			Text text = new Text("Sup!!");
//			text.setFont(new Font("Papyrus",80));
//			TextField nameField = new TextField();//textfield
//			Label label = new Label("Name: ");
//			
//			Button btn = new Button();
//			
//			GridPane grid = new GridPane();
//			grid.add(label, 0, 0);
//			grid.add(nameField, 1, 0);
//			grid.add(btn, 1, 1);
//			grid.setHgap(20);// space between elements
//			
//			grid.setGridLinesVisible(true);// make grid lines visible
//			
//			btn.setText("Sey Sup!!");
//			//btn.setOnAction(evt-> System.out.println("Btn was clicked"));// add action with Lambda
//			btn.setOnAction(evt-> System.out.printf("Btn %s!%n",
//					nameField.getText()));//get text filled in textfield
//			text.setY(50);// Y from 0 point
//			
//			//root.getChildren().add(btn);
//			//root.getChildren().add(text);
//			
//			VBox box = new VBox();
//			//box.getChildren().addAll(text, grid, btn);// fill VBOX with all created elements
//			box.getChildren().addAll(text, grid);
//			root.getChildren().add(box);
			
//			primaryStage.setTitle("Sup");
//			primaryStage.setScene(new Scene(root,300,275));
		
		Parent root = FXMLLoader.load(getClass().getResource("/resources/fxml/sample.fxml"));// connect fxml file
		
		primaryStage.setTitle("Sup");
		primaryStage.setScene(new Scene(root,300,275));
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
