package com.mnr.pomodorojavafx.controllers;

import com.mnr.pomodorojavafx.model.Attempt;
import com.mnr.pomodorojavafx.model.AttemptKind;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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
	
	private StringProperty mTimerText;
	
	public Home(){
		mTimerText = new SimpleStringProperty();
		setTimerText(0);
	}
	
	public StringProperty getmTimerTextProperty() {
		return mTimerText;
	}

	public void setTimerText(String text){
		mTimerText.set(text);
	}
	
	public void setTimerText(int sec){
		int minutes = sec / 60;
		
		int seconds = sec % 60;
		
		setTimerText(String.format("%02d:%02d", minutes, seconds));
		
	}

	public void setmTimerTextProperty(StringProperty mTimerText) {
		this.mTimerText = mTimerText;
	}

	private void prepareAttempt(AttemptKind kind){
		mCurrentAttempt = new Attempt(kind, "");
		addAttemptStyle(kind);
		title.setText(kind.getDisplayName());
		
		setTimerText(mCurrentAttempt.getRemaingSeconds());
		
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
		System.out.println("222"+mTimerText.get());
		
	}
	
	
}
