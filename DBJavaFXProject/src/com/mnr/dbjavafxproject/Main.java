package com.mnr.dbjavafxproject;
	
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
			
			Text birthText = new Text("Birth Date:");
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
			primaryStage.setResizable(false);
			primaryStage.setScene(new Scene(root,280,300));
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	/*
	 * Check is all fields have correct text inside
	 * 
	 * @return true if all textareas have correct filled data,
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
	
	/*
	 * add values from textareas to the DB
	 * and cleat all textfields
	 */
	private void addToDB(){
		
		System.out.println(nameTF.getText());
		System.out.println(emailTF.getText());
		System.out.println(ageTF.getText());
		
		nameTF.setText("");
		emailTF.setText("");
		ageTF.setText("");
		
	}
	
	private void checkDB(){
		root.getChildren().clear();
	}
	
}
