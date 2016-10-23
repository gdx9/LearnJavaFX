package com.mnr.dbjavafxproject.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class AddUserController implements Initializable{
	
	@FXML
	private Group rootGroup;
	
	@FXML
	private GridPane gridPane;
	
	@FXML
	private Text welcomeText;
	
	@FXML
	private TextField nameTF;
	
	@FXML
	private TextField emailTF;
	
	@FXML
	private TextField ageTF;
	
	@FXML
	private ComboBox<String> dbConncomboBox;
	
	@FXML
	private Button confirmButton;
	
	@FXML
	private Button checkDBDataBtn;
	
	/**
	 * Add database connection types to combobox
	 * This method is called by the FXMLLoader when initialization is complete
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		dbConncomboBox.getItems().setAll(Controller.getAllConnectionTypes());
		dbConncomboBox.getSelectionModel().selectFirst();
		dbConncomboBox.setOnAction(e->{
			System.out.println(dbConncomboBox.getValue());
		});
		
	}
	
	/**
	 * Check all textfields and add them to database
	 * @param e action event, receive automatically
	 */
	public void addUser(ActionEvent e){
		
		//add user info to db if all is ok
		if(Controller.takeUserFields(nameTF,emailTF,ageTF,dbConncomboBox)){
			//addToDB();

			//clear textfields
			nameTF.setText("");
			emailTF.setText("");
			ageTF.setText("");
			
		}else{
			System.out.println("Fill text areas correctly!");
		}
		
	}
	
	/**
	 * Change scene for checking all users in database
	 * 
	 * @param ev action event, receive automatically
	 */
	public void checkDB(ActionEvent ev){
		
		try {
			
			rootGroup.getChildren().clear();
			rootGroup.getChildren().addAll( (VBox) FXMLLoader.load(getClass().getResource("/fxml/checkDb.fxml")) );
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
