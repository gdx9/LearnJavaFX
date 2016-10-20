package com.mnr.pomodorojavafx.controllers;

import com.mnr.pomodorojavafx.model.Attempt;
import com.mnr.pomodorojavafx.model.AttemptKind;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;

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
	
	private Timeline timeline;
	
	private final AudioClip mApplause;
	
	public Home(){
		mTimerText = new SimpleStringProperty();
		setTimerText(0);
		mApplause = new AudioClip(getClass().getResource("/sounds/applause.mp3").toExternalForm());
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
		reset();
		mCurrentAttempt = new Attempt(kind, "");
		addAttemptStyle(kind);
		title.setText(kind.getDisplayName());
		setTimerText(mCurrentAttempt.getRemaingSeconds());
		// TODO:csd This is creating multiple timelines. Fix this
		timeline = new Timeline();
		timeline.setCycleCount(kind.getTotalSeconds());
		timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), e->{
			mCurrentAttempt.tick();
			setTimerText(mCurrentAttempt.getRemaingSeconds());
		}));
		timeline.setOnFinished(e->{
			saveCurrentAttempt();
			mApplause.play();
			prepareAttempt(mCurrentAttempt.getKind() == AttemptKind.FOCUS ?
                    AttemptKind.BREAK : AttemptKind.FOCUS);
		});
	}
	
	private void saveCurrentAttempt(){
		mCurrentAttempt.setMessage(message.getText());
		mCurrentAttempt.save();
	}
	
	private void reset(){
		clearAttemptStyles();
		if(timeline != null && timeline.getStatus()	== Animation.Status.RUNNING){
			timeline.stop();
		}
	}
	
	public void playTimer(){
		container.getStyleClass().add("playing"); // class="playing"
		timeline.play();
	}
	
	public void pauseTimer(){
		container.getStyleClass().remove("playing");
		timeline.pause();
	}
	
	private void addAttemptStyle(AttemptKind kind){
		container.getStyleClass().remove("playing");
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
	
	public void handleRestart(ActionEvent ae){
		prepareAttempt(AttemptKind.FOCUS);
		playTimer();
		
	}
	
	public void handlePlay(ActionEvent ae){
		if(mCurrentAttempt == null){
			handleRestart(ae);
		}else{
			playTimer();
		}
	}
	
	public void handlePause(ActionEvent ae){
		pauseTimer(); 
	}
	
}
