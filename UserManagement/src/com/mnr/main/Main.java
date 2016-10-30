package com.mnr.main;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application{


	@Override
	public void start(Stage primaryStage) throws Exception {
		
		primaryStage.setTitle("All works");
		//Group root = new Group();
		BorderPane root= new BorderPane() ;
		primaryStage.setScene(new Scene(root,320,300));
		
		/*GridPane gridPane = new GridPane();
		
		Button confirmButton = new Button("OK");
		confirmButton.setTooltip(new Tooltip("OK button"));
		
		gridPane.add(confirmButton, 0,0);
		
		root.getChildren().add(gridPane);*/
		
		MenuBar mb = new MenuBar();
		
		Menu fileMenu = new Menu("File");
		//ImageView iv = new ImageView("a.jpg");//add image
		//MenuItem open = new MenuItem("Open",iv);
		MenuItem open = new MenuItem("Open");
		MenuItem close = new MenuItem("Close");
		Menu lH = new Menu("choose");
		RadioMenuItem high = new RadioMenuItem("High");
		RadioMenuItem low = new RadioMenuItem("Low");
		ToggleGroup tg = new ToggleGroup();
		high.setToggleGroup(tg);
		low.setToggleGroup(tg);
		high.setSelected(true);
		lH.getItems().addAll(high,low);
		
		fileMenu.getItems().addAll(open,new SeparatorMenuItem(), close,
				lH);
		
		mb.getMenus().add(fileMenu);
		
		EventHandler<ActionEvent> mEventHandler =
			new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					String name = ((MenuItem)event.getTarget()).getText();
					
					if(name.equals("Exite"))
						Platform.exit();
					
				}
		};
		
		root.setTop(mb);
		root.setCenter(new Label("AAA"));
		
		
		
		primaryStage.show();
		primaryStage.setOnCloseRequest((e)->{
			System.out.println("oks");
			Platform.exit();
			System.exit(0);
		});
//		
//		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
//			@Override
//			public void handle(WindowEvent event) {
//				System.out.println("oks");
//				Platform.exit();
//				System.exit(0);
//			}
//		});
		
	}

	public static void main(String[] args) {
		launch(args);
	}
	
}
