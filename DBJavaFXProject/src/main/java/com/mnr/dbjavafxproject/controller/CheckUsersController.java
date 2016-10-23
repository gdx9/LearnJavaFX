package com.mnr.dbjavafxproject.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.mnr.dbjavafxproject.entities.User;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class CheckUsersController implements Initializable{
	
	@FXML
	private Group rootGroup;
	
	@FXML
	private VBox vBox;
	
	@FXML
	private HBox hBox;
	
	@FXML
	private Button goBackButton;
	
	@FXML
	private Button reloadButton;
	
	@FXML
	private ScrollPane scrollPane;
	
	@FXML
	private GridPane gridPane;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		addUsersFromDatabase();
		
	}
	
	public void addUser(ActionEvent ev){
		
		//rootGroup.getChildren().clear();
		System.out.println("aaa");
		try {
			
			rootGroup = FXMLLoader.load(getClass().getResource("/fxml/addNewUser.fxml"));
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
	}
	
	public void reloadUsers(ActionEvent ev){
		
		addUsersFromDatabase();
		
	}
	
	private void addUsersFromDatabase(){
		
		// and add rows from database to GridPane after ui finished
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				try{
					//take dbdata
					List<User> aList = Controller.getAllUsersDBInfo();
					
					int i = 0;
					for (User user : aList) {
						gridPane.add(new Text(user.getId()+""), 0, i);
						gridPane.add(new Text(user.getName()+""), 1, i);
						gridPane.add(new Text(user.getEmail()+""), 2, i);
						gridPane.add(new Text(user.getAge()+""), 3, i);

						i++;
					}
					
				}catch (Exception e) {
					e.printStackTrace();
					System.out.println("db thread cancelled");
				}
			}
		});
	}

}
