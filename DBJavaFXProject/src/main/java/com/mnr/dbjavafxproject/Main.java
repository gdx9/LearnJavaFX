package com.mnr.dbjavafxproject;
	
import java.util.List;

import com.mnr.dbjavafxproject.entities.User;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;


public class Main extends Application {
	
	Group root;
	String titleText;
	TextField nameTF;
	TextField emailTF;
	TextField ageTF;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			titleText = "Add to db";
			
			root = new Group();
			GridPane gridPane = new GridPane();
			
			Text welcomeText = new Text(titleText);
			
			Text nameText = new Text("Name:");
			nameTF = new TextField();
			
			Text emailText = new Text("Email:");
			emailTF = new TextField();
			
			Text birthText = new Text("Age:");
			ageTF = new TextField();
			
			Button confirmButton = new Button("OK");
			
			Button checkDBDataBtn = new Button("check db");
			
			//gridPane.setGridLinesVisible(true);
			gridPane.add(welcomeText, 0, 0, 2, 1);
			gridPane.add(nameText, 0, 1);
			gridPane.add(nameTF, 1, 1);
			gridPane.add(emailText,0,2);
			gridPane.add(emailTF,1,2);
			gridPane.add(birthText,0,3);
			gridPane.add(ageTF,1,3);
			gridPane.add(confirmButton, 0, 4, 2, 1);
			gridPane.add(checkDBDataBtn, 0, 5, 2, 1);
			/*
			ToggleGroup group = new ToggleGroup();
			RadioButton rb1 = new RadioButton("b1");// text
			rb1.setUserData("value1");// value, even object
			rb1.setToggleGroup(group);
			rb1.setSelected(true);
			RadioButton rb2 = new RadioButton("b2");
			rb2.setToggleGroup(group);
			gridPane.add(rb1, 0, 6);
			gridPane.add(rb2, 0, 7);
			rb2.setOnAction(e->{
				System.out.println(rb1.getUserData());
			});
			CheckBox c1 = new CheckBox("text1");
			gridPane.add(c1, 0, 8);
			c1.setOnAction(e->{
				System.out.println(c1.isSelected());
			});
			ListView<String> listView = new ListView<>();
			ObservableList<String> items = FXCollections.observableArrayList(
					"A","B","C","D");
			listView.setItems(items);
			listView.setPrefHeight(50);
			listView.setPrefWidth(50);
			listView.setOnMouseClicked(e->{
				System.out.println("clicked: "+listView.getSelectionModel().getSelectedItem());
			});
			gridPane.add(listView, 0, 9);
			ComboBox<String> comboBox = new ComboBox<>(items);//getItems().addAll(items)
			comboBox.setOnAction(e->{
				System.out.println(comboBox.getValue());
			});
			gridPane.add(comboBox, 0, 10);
			*/
			
			
			gridPane.setHgap(10);
			gridPane.setVgap(10);
			
			// set alignments
			GridPane.setHalignment(welcomeText, HPos.CENTER);
			GridPane.setHalignment(confirmButton, HPos.CENTER);
			GridPane.setHalignment(checkDBDataBtn, HPos.RIGHT);
			
			//add listeners
			confirmButton.setOnAction(e->{
				if(checkAllFields()){
					addToDB();
				}else{
					System.out.println("fill text areas correctly!");
				}
			});
			checkDBDataBtn.setOnAction(e->{
				checkDB();
				primaryStage.setTitle("DB");
			});
			
			root.getChildren().add(gridPane);
			
			primaryStage.setTitle(titleText);
			//primaryStage.setResizable(false);
			primaryStage.setScene(new Scene(root,280,300));
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	/**
	 * Check are all textfields have correct text inside
	 * 
	 * @return true if all textfields have correct filled data,
	 * false if field are empty or have incorrect data
	 */
	private boolean checkAllFields(){
		if( nameTF.getText().isEmpty() ){
			return false;
		}
		if( emailTF.getText().isEmpty() ){
			return false;
		}
		if( !ageTF.getText().isEmpty() && ageTF.getText() != null ){
			try{
				Integer.parseInt(ageTF.getText());
				return true;
			}catch (NumberFormatException e) {
				return false;
			}catch (Exception e) {
				return false;
			}
		}

		return false;
		
	}
	
	/**
	 * Add values from textareas to the DB
	 * and cleat all textfields
	 * 
	 */
	private void addToDB(){
		
		System.out.println(nameTF.getText()+" "+emailTF.getText()+" "+ageTF.getText());
		
		DatabaseHelper.writeUserToDB(nameTF.getText(),emailTF.getText(),Integer.parseInt(ageTF.getText()));
		
		nameTF.setText("");
		emailTF.setText("");
		ageTF.setText("");
		
	}
	
	/**
	 * clear the root group, get all Users from Database and draw them all into Text fields
	 * 
	 */
	private void checkDB(){
		root.getChildren().clear();
		
		//User user = DatabaseHelper.readUserFromDB(1L);
		
		//System.out.println(user.getEmail());
		
		List<User> aList = DatabaseHelper.getAllUsersFromDB();
		
		
		
		GridPane grid = new GridPane();
		
		int i = 0;
		for (User user : aList) {
			grid.add(new Text(user.getId()+""), 0, i);
			grid.add(new Text(user.getName()+""), 1, i);
			grid.add(new Text(user.getEmail()+""), 2, i);
			grid.add(new Text(user.getAge()+""), 3, i);
			
			i++;
		}
		Button b = new Button("w");
		b.setOnAction(e->{
			checkDB();
		});
		grid.add(b,4,0);
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setContent(grid);
		scrollPane.setPrefSize(280, 300);
		//scrollPane.setFitToHeight(true);
		//scrollPane.setFitToHeight(true);
		root.getChildren().add(scrollPane);

	
		
	}
	
}
