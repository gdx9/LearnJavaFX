package com.mnr.pomodorojavafx.controllers;

import com.mnr.pomodorojavafx.model.Attempt;
import com.mnr.pomodorojavafx.model.AttemptKind;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class Home {
	
	@FXML
	private VBox container; // fx:id container variable in home.fxml file
	
	@FXML
	private Label title;
	
	@FXML
	TextArea message;
	
	@FXML
	Button DEBUG;
	
	@FXML
	Label time;
	
	private Attempt mCurrentAttempt;
	
	private void prepareAttempt(AttemptKind kind){
		mCurrentAttempt = new Attempt(kind, "");
		addAttemptStyle(kind);
		title.setText(kind.getDisplayName());
		
	}
	
	private void addAttemptStyle(AttemptKind kind){
		container.getStyleClass().add(kind.toString().toLowerCase());
	}

	private void clearAttemptStyles(){
		for(AttemptKind kind : AttemptKind.values()){
			container.getStyleClass().remove(kind.toString().toLowerCase());	
		}
	}
	
	public void DEBUG(ActionEvent ae){
		System.out.println("222");
	}
	
	
}
