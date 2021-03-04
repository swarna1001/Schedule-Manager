
package com.attendance;

// ON HOLD
// three stages in main class, MonthlyFunction, DailyFunc
// One alert box - AlertBoxOne
// two data providers Daily and Monthly
// couldn't enable multiple selection - no need, working with single selection. multiple selection can be done by pressing ctrl
// can't display calendar in the date column for each row in monthly function
// last progress made on 2 July, 2020 - DailyFunc

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;


@SuppressWarnings("rawtypes")

public class Main extends Application
{
	Stage window;
	Label mainLabel = new Label("Attendance Manager");
	
	public static void main(String[] args) {
		launch(args);
	}

	@SuppressWarnings("unchecked")
	public void start(Stage primaryStage) throws Exception
	{
		window = primaryStage;
		window.setTitle("Attendance Manager");
		BorderPane layout = new BorderPane();
		HBox mainhbox = new HBox();
		
		mainhbox.setPadding(new Insets(190, 10, 10, 10));
		mainhbox.setAlignment(Pos.TOP_CENTER);
		mainhbox.getChildren().add(mainLabel);
		
		// creating menu bar
		Menu createMenu = new Menu("Create");
		// main menu bar
		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(createMenu);
						
		// adding functionality to create menu buttons
		MenuItem dailyFile = new MenuItem("Daily Schedule");
		dailyFile.setOnAction(e -> new DailyFunc());
		
		MenuItem monthlyFile = new MenuItem("Monthly Schedule");	
		monthlyFile.setOnAction(e -> new MonthlyFunction()); 
		
		createMenu.getItems().addAll(dailyFile, monthlyFile);


		layout.setCenter(mainhbox);
		layout.setTop(menuBar);
		Scene scene = new Scene( layout, 900, 600);
		window.setScene(scene);
		window.show();
		
	}
	
}

