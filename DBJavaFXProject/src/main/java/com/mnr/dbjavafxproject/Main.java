package com.mnr.dbjavafxproject;

import com.mnr.dbjavafxproject.view.WindowManipulator;

import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		WindowManipulator wm = new WindowManipulator(primaryStage);
		wm.drawMainScene();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
