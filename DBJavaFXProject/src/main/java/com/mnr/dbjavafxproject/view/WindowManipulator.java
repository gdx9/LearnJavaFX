package com.mnr.dbjavafxproject.view;

import java.util.List;

import com.mnr.dbjavafxproject.controller.Controller;
import com.mnr.dbjavafxproject.entities.User;
import com.mnr.dbjavafxproject.model.DatabaseHelper;
import com.mnr.dbjavafxproject.model.JDBCHelper;

import javafx.geometry.HPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class WindowManipulator {
	
	private Stage primaryStage;
	private Group root;
	private Scene windowScene;
	
	public WindowManipulator(Stage primaryStage) {
		
		this.primaryStage = primaryStage;
		root = new Group();
		this.windowScene = new Scene(root,280,300);
		
		primaryStage.setScene(windowScene);
		primaryStage.show();
		
	}

	public void drawMainScene(){
		
		try {
			
			root.getChildren().clear();
			
			String titleText = "Add to db";
			
			GridPane gridPane = new GridPane();
			
			Text welcomeText = new Text(titleText);
			
			Text nameText = new Text("Name:");
			TextField nameTF = new TextField();			
			Text emailText = new Text("Email:");
			TextField emailTF = new TextField();
			Text birthText = new Text("Age:");
			TextField ageTF = new TextField();
			
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
			
			ComboBox<String> comboBox = new ComboBox<>(Controller.getAllConnections());//getItems().addAll(items)
			comboBox.getSelectionModel().selectFirst();
			comboBox.setOnAction(e->{
				System.out.println(comboBox.getValue());
			});
			gridPane.add(comboBox, 0, 6);
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
				if(Controller.takeUserFields(nameTF,emailTF,ageTF)){
					//addToDB();

					nameTF.setText("");
					emailTF.setText("");
					ageTF.setText("");
				}else{
					System.out.println("fill text areas correctly!");
				}
			});
			checkDBDataBtn.setOnAction(e->{
				checkDB();
				primaryStage.setTitle("DB");
			});
			
			root.getChildren().add(gridPane);
			
			JDBCHelper.writeToDB("Bill", "good",12);
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}

	}
	
	
	
	
	
	/**
	 * Clear the root group, get all Users from Database and draw them all into Text fields
	 * 
	 */
	private void checkDB(){
		
		root.getChildren().clear();
		Button goBackButton = new Button("Back");
		goBackButton.setOnAction(e->{
			drawMainScene();
		});
		Button reloadButton = new Button("reload");
		reloadButton.setOnAction(e->{
			checkDB();
		});
		
		VBox vBox = new VBox();
		
		HBox hBox = new HBox();
		
		hBox.getChildren().addAll(goBackButton, reloadButton);
		vBox.getChildren().add(hBox);
		
		GridPane grid = new GridPane();
		
		List<User> aList = DatabaseHelper.getAllUsersFromDB();
		
		int i = 0;
		for (User user : aList) {
			grid.add(new Text(user.getId()+""), 0, i);
			grid.add(new Text(user.getName()+""), 1, i);
			grid.add(new Text(user.getEmail()+""), 2, i);
			grid.add(new Text(user.getAge()+""), 3, i);

			i++;
		}
		
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setPrefSize(280, 100);
		scrollPane.setContent(grid);
		
		vBox.getChildren().add(scrollPane);

		root.getChildren().add(vBox);
		System.out.println(aList.size());
		
	}
	
	
}
