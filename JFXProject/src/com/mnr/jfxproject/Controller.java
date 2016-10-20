package com.mnr.jfxproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class Controller {
	
	@FXML private TextField firstName;

	public void handleSaySup(ActionEvent event){
		System.out.printf("Pressed %s! %n", firstName.getText());
	}
}
