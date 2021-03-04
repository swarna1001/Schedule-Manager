package com.attendance;

import java.time.LocalDate;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

@SuppressWarnings("rawtypes")

public class Test extends Application
{
	Stage window;
	static TableView tableView= new TableView();
	static HBox hBox2 = new HBox();
	
	TextField timeInput, theoryInput, practicalInput;

	static VBox calendarBox = new VBox();
	Label dateLabel = new Label("Date:  ");
	Label dayLabel = new Label("Day:  ");
	DatePicker datePicker = new DatePicker();
	
	public static void main(String[] args) {
		launch(args);
	}

	@SuppressWarnings("unchecked")
	public void start(Stage primaryStage) throws Exception
	{
		window = primaryStage;
		window.setTitle("Attendance Manager");
		BorderPane layout = new BorderPane();
		
		//add and delete
		timeInput = new TextField();
		timeInput.setPromptText("Timing");
		timeInput.setMinWidth(100);
		
		theoryInput = new TextField();
		theoryInput.setPromptText("Theory");
		theoryInput.setMinWidth(200);
		
		practicalInput = new TextField();
		practicalInput.setPromptText("Practical");
		practicalInput.setMinWidth(200);
		
		// button
		Button addButton = new Button("Add");
		addButton.setOnAction(e -> addButtonClicked());
		
		Button deleteButton = new Button("Delete");
		deleteButton.setOnAction(e -> deleteButtonClicked());
		
		Button attendancePercent = new Button("Percentage Attendance");
		Button attendClass = new Button("Classes to attend");
		
		Button toSave = new Button("Save");                                                                                              // add save functionality
		
		Button toExit = new Button("Exit");
		
		// adding button in HBox at the bottom
		
		HBox hBox1 = new HBox();
		HBox hBox2 = new HBox();
		VBox vBoxAtBottom = new VBox();
		hBox1.setPadding(new Insets(20, 20, 10, 80));
		hBox1.setSpacing(10);
		hBox1.getChildren().addAll(timeInput, theoryInput, practicalInput, addButton, deleteButton);
		hBox2.getChildren().addAll(toExit, toSave);
		hBox2.setPadding(new Insets(5, 10, 20, 30));
		hBox2.setSpacing(740);
		vBoxAtBottom.getChildren().addAll(hBox1, hBox2);
		
		
		// adding computation  button in right VBox
		VBox RightvBox = new VBox();
		RightvBox.getChildren().addAll(attendancePercent, attendClass);
		RightvBox.setPadding(new Insets(20, 30, 10, 30));
		RightvBox.setSpacing(10);		
		
		ComboBox comboBox = new ComboBox<>();
	 	comboBox.getItems().addAll(
	 			"Monday",
	 			"Tuesday",
	 			"Wednesday",
	 			"Thursday",
	 			"Friday",
	 			"Saturday"
	 			);
	 	comboBox.setPromptText("Day");
	 	VBox LeftvBox1 = new VBox();
	 	LeftvBox1.getChildren().addAll(calendarBox, dayLabel, comboBox);
	 	LeftvBox1.setPadding(new Insets(10, 10, 10, 20));
	 	LeftvBox1.setSpacing(10);
	 	
	 	datePicker.setOnAction(e ->selectDate() );

	 	calendarBox.getChildren().add(dateLabel);
	 	calendarBox.getChildren().add(datePicker);
	 	calendarBox.setSpacing(10);
			
		// creating menu bar
		Menu createMenu = new Menu("Create");
		//Menu createMenu = new Menu("Create");
		// main menu bar
		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(createMenu);
				
		// adding functionality to create menu buttons
		MenuItem dailyFile = new MenuItem("Daily Schedule");
		dailyFile.setOnAction(e -> 
		{
			displayDailyFunc();
			layout.setCenter(tableView);
		}); 
		createMenu.getItems().add(dailyFile);
				
		MenuItem monthlyFile = new MenuItem("Monthly Schedule");
		monthlyFile.setOnAction(e -> new MonthlyFunction()); 
				
		createMenu.getItems().add(monthlyFile);
		
		layout.setTop(menuBar);
		layout.setCenter(tableView);
		layout.setBottom(vBoxAtBottom);
		layout.setLeft(LeftvBox1);
		layout.setRight(RightvBox);	
		
		
		Scene scene = new Scene( layout, 900, 600);
		window.setScene(scene);
		window.show();
	}

	// Functional methods
	static void displayDailyFunc()
	{
		TextField Day, Date;
		
		Day = new TextField();
		Day.setPromptText("Day");
		Day.setMinWidth(200);
		
		Date = new TextField();
		Date.setPromptText("Date");
		Date.setMinWidth(100);
		
		hBox2.setPadding(new Insets(10, 10, 10, 10));
		hBox2.setSpacing(10);
		hBox2.setAlignment(Pos.TOP_CENTER);
		hBox2.getChildren().addAll(Day, Date);
		
		// create columns
		// timing column
		TableColumn <Daily, Double> timeColumn = new TableColumn<>("Timing");
		timeColumn.setMinWidth(100);
		timeColumn.setCellValueFactory(new PropertyValueFactory<Daily, Double>("timing"));
				
		// subject column
		TableColumn <Daily, String> subjectColumn = new TableColumn<>("Subject");
				
		// theory subject column
		TableColumn <Daily, String> theoryColumn = new TableColumn<>("Theory");
		theoryColumn.setMinWidth(150);
		theoryColumn.setCellValueFactory(new PropertyValueFactory<Daily, String>("theory"));
							
		// practical subject column
		TableColumn <Daily, String> practicalColumn = new TableColumn<>("Practical");
		practicalColumn.setMinWidth(150);
				
		practicalColumn.setCellValueFactory(new PropertyValueFactory<Daily, String>("practical"));
				
		// Adding columns
		subjectColumn.getColumns().addAll(theoryColumn, practicalColumn);
		tableView.getColumns().addAll(timeColumn,subjectColumn);
	}
	
	
	public void addButtonClicked()
	{
		Daily daily = new Daily();
		daily.setTiming(Double.parseDouble(timeInput.getText()));
		daily.setTheory(theoryInput.getText());
		daily.setPractical(practicalInput.getText());
		tableView.getItems().add(daily);
		timeInput.clear();
		theoryInput.clear();
		practicalInput.clear();	
	}
	
	public void deleteButtonClicked()
	{
		ObservableList <Daily> routineSelected, allRoutines;
		allRoutines = tableView.getItems();
		routineSelected = tableView.getSelectionModel().getSelectedItems();
		
		routineSelected.forEach(allRoutines:: remove);
	}
	
	public void selectDate()
	{
		LocalDate i = datePicker.getValue();
		dateLabel.setText("Date :" + i);
		datePicker.setShowWeekNumbers(true);
	}
	
}
