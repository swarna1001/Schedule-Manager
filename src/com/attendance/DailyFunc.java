package com.attendance;


import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.ObservableList;
import javafx.geometry.*;
import java.io.*;
import java.util.*;

import java.time.*;

public class DailyFunc 
{
	static TableView<Daily> tableView= new TableView<Daily>();
	static HBox hBox2 = new HBox();
	static VBox calendarBox = new VBox();
	Label dateLabel = new Label("Date:  ");
	Label dayLabel = new Label("Day:  ");
	DatePicker datePicker = new DatePicker();
	TextField timeInput, theoryInput, practicalInput;
	Hashtable <String, Integer> hashData = new Hashtable<String, Integer>();
	Hashtable <String, Integer> hashDataCheck = new Hashtable<String, Integer>();
	Hashtable <String, Integer> finalHash = new Hashtable<String, Integer>();
	
	public static Stack <Integer> stackIndex = new  Stack <Integer>();
	public static Stack <String> stackData = new Stack <String>();
	public static Stack <String> finalStack = new Stack <String>();
	public int okPressedCount = 0;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	DailyFunc()
	{
		Stage subStage = new Stage();
		subStage.setHeight(630);
		subStage.setWidth(910);
		BorderPane layout = new BorderPane();
		
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
		
		//tableView.setEditable(true);
				
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
		
		tableView.getSelectionModel().setCellSelectionEnabled(true);
		tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		// button
		Button addButton = new Button("Add");
		addButton.setOnAction(e -> addButtonClicked());
				
		Button deleteButton = new Button("Delete");
		deleteButton.setOnAction(e -> deleteButtonClicked());
				
		Button attendancePercent = new Button("Percentage Attendance");
		attendancePercent.setOnAction(e -> attendancePercentage());
				
		Button attendClass = new Button("Classes to attend");
		
		Button ok = new Button("ok");
		ok.setOnAction(e -> saveData());
				
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
		RightvBox.getChildren().addAll(attendancePercent, attendClass, ok);
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
		
		layout.setCenter(tableView);
		layout.setBottom(vBoxAtBottom);
		layout.setLeft(LeftvBox1);
		layout.setRight(RightvBox);	
		
		Scene dailyScene = new Scene(layout);		
		
		subStage.setTitle("Attendance Manager");
		subStage.setScene(dailyScene);
		subStage.show();

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
	
	@SuppressWarnings("rawtypes")
	public  int saveData( ) 
	{
		TablePosition pos = tableView.getSelectionModel().getSelectedCells().get(0);
		int row = pos.getRow();
		Daily daily = tableView.getItems().get(row);
		TableColumn col = pos.getTableColumn();
		String data = (String) col.getCellObservableValue(daily).getValue();	
		Object object = tableView.getSelectionModel().selectedItemProperty().get();
		int index = tableView.getSelectionModel().selectedIndexProperty().get();
		String indexString = Integer.toString(index);
		
		String newData = data.concat(indexString) ;                    // add data and index as a string and replace the whitespace 
		newData = newData.replaceAll(" ", "");
		
		if (finalStack.isEmpty() == true)
		{
			finalStack.push(newData);
			System.out.println(finalStack);	
		}
		else
		{
			int finalStackLength = finalStack.size();
					if (finalStack.contains(newData))
					{
						AlertBoxOne.displayAlertBox("Subject already selected!");
					}				
					else
					{
						for (int i = 1; i<= finalStackLength; i++)
						{
						finalStack.push(newData);		
						System.out.println(finalStack);		
						break;
						}
					}
		}
		int finalSize = finalStack.size(); 
		if (finalSize > 0){
			return finalSize +1;
		}
		else {
			finalStack = null;
			finalSize = 0;
			return finalSize+ 1;
		}
		
		
}
	

		
	
	public  void attendancePercentage() 
	{
		Object object = tableView.getSelectionModel().selectedItemProperty().get();
		int index = tableView.getSelectionModel().selectedIndexProperty().get();
		tableView.getSelectionModel().clearAndSelect(index);
	
		System.out.println("gygghgh");
		
		
		int attendedClasses = saveData();
		if (attendedClasses == 0){
			AlertBoxOne.displayAlertBox("Select the attendd classes!");
		}
		//System.out.println(attendedClasses);
		//if (attendedClasses ==0){
			//AlertBoxOne.displayAlertBox("Select the attendd classes!");

		//}
		
		
			
		}	
	}
	
	
	
	

	






