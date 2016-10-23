package com.mnr.dbjavafxproject;

import com.mnr.dbjavafxproject.view.WindowManipulator;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		WindowManipulator wm = new WindowManipulator(primaryStage);
		wm.drawMainSceneFXML();
		
		//exit on close
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent event) {
				Platform.exit();
				System.exit(0);
			}
			
		});
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
