package com.mnr.dbjavafxproject.controller;


import java.util.List;

import com.mnr.dbjavafxproject.entities.User;
import com.mnr.dbjavafxproject.model.ConnType;
import com.mnr.dbjavafxproject.model.DatabaseHelper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;

public class Controller {
	
	public static final ObservableList<String> getAllConnections(){
		ObservableList<String> items = FXCollections.observableArrayList(
				ConnType.JDBC+"",
				ConnType.HIBERNATE+"");
		return items;
	}
	
	public static void readToDB(){
		
	}
	
	/**
	 * Check all user info textfields have correct text inside
	 * and write them into database
	 * 
	 * @return true if all textfields have correct filled data,
	 * false if field are empty or have incorrect data
	 */
	public static boolean takeUserFields(TextField nameTF, TextField emailTF, TextField ageTF){
		
		String name = nameTF.getText();
		String email = emailTF.getText();
		String age = ageTF.getText();
		
		if( name.isEmpty() ){
			return false;
		}
		if( email.isEmpty() ){
			return false;
		}
		if( !age.isEmpty() ){
			try{
				//write to db
				DatabaseHelper.addToDB(name, email, Integer.parseInt(age));
				return true;
			}catch (NumberFormatException e) {
				return false;
			}catch (Exception e) {
				return false;
			}
		}

		return false;
		
	}
	
	public static List<User> getAllUsersDBInfo(){
		return DatabaseHelper.getAllUsersFromDB();
	}

}
