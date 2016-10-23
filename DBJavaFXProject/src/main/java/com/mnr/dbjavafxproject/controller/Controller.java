package com.mnr.dbjavafxproject.controller;


import java.util.List;

import com.mnr.dbjavafxproject.entities.User;
import com.mnr.dbjavafxproject.model.ConnType;
import com.mnr.dbjavafxproject.model.DatabaseHelper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class Controller {
	
	/**
	 * Return ObservableList of connection types
	 * 
	 * @return {@link ObservableList} of Strings with database connection types
	 * 
	 * @see ConnType
	 */
	public static final ObservableList<String> getAllConnectionTypes(){
		ObservableList<String> items = FXCollections.observableArrayList(
				ConnType.JDBC+"",
				ConnType.HIBERNATE+"");
		return items;
	}
	
	public static void readToDB(){
		
	}
	
	/**
	 * Check is all user info textfields have correct text inside,
	 * check connection type {@link ComboBox}
	 * and call {@link DatabaseHelper} method to write them into database
	 * 
	 * @param nameTF user name textfield
	 * @param emailTF user email textfield
	 * @param ageTF user age textfield
	 * @param methodBox connection type {@link ComboBox}
	 * 
	 * @return true if all textfields have correct filled data,
	 * false if field are empty or have incorrect data
	 */
	public static boolean takeUserFields(TextField nameTF, TextField emailTF, TextField ageTF, ComboBox<String> methodBox){
		
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
				ConnType conMethod = ConnType.valueOf(methodBox.getValue());// return enum type
				DatabaseHelper.addToDB(name, email, Integer.parseInt(age), conMethod);
				
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
	 * Call {@link DatabaseHelper} method to get list of {@link User}'s
	 * Remember that if we add data with hibernate, we should read data with hibernate.
	 * We can't see at jdbc added data if we opened hubernate connection!
	 * 
	 * @return list of {@link User}'s
	 */
	public static List<User> getAllUsersDBInfo(){
		
		return DatabaseHelper.getAllUsersFromDB();
		
	}

}
