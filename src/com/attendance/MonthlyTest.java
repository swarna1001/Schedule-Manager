package com.attendance;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.application.Application;
import javafx.geometry.*;

import java.time.*;
import java.time.chrono.*;


public class MonthlyTest 
{
	 @SuppressWarnings("rawtypes")
	TableView tableView = new TableView();
	TextField Inputsub1, Inputsub2, Inputsub3, Inputsub4, Inputsub5, Inputsub6;
	Label dateLabel = new Label("Date:  ");
	Label dayLabel = new Label("Day:  ");
	DatePicker datePicker = new DatePicker();
	 
	MonthlyTest()                                                                                                                            // class constructor
	{
		Stage subStage = new Stage();
		subStage.setMaximized(true);
		BorderPane layout = new BorderPane();
		
		// create columns		
		
		// date column
		TableColumn <Monthly, String> dateColumn = new TableColumn<>("Date");
		dateColumn.setMinWidth(250);
		dateColumn.setCellValueFactory(new PropertyValueFactory<Monthly, String>("date"));
		
		// day column
		TableColumn <Monthly, ChoiceBox> dayColumn = new TableColumn<>("Day");
		dayColumn.setMinWidth(150);
		dayColumn.setCellValueFactory(new PropertyValueFactory<Monthly, ChoiceBox>("day"));		
		
		// subject column
		TableColumn <Monthly, String> subjectColumn = new TableColumn<>("SUBJECTS");
													
		// subject1 column
		TableColumn <Monthly, String> subjectColumn1 = new TableColumn<>("subject 1");
		subjectColumn1.setMinWidth(150);
		subjectColumn1.setCellValueFactory(new PropertyValueFactory<Monthly, String>("subject1"));
						
		// subject2 column
		TableColumn <Monthly, String> subjectColumn2 = new TableColumn<>("subject 2");
		subjectColumn2.setMinWidth(150);
		subjectColumn2.setCellValueFactory(new PropertyValueFactory<Monthly, String>("subject2"));
						
		// subject3 column
		TableColumn <Monthly, String> subjectColumn3 = new TableColumn<>("subject 3");
		subjectColumn3.setMinWidth(150);
		subjectColumn3.setCellValueFactory(new PropertyValueFactory<Monthly, String>("subject3"));
						
		// subject4 column
		TableColumn <Monthly, String> subjectColumn4 = new TableColumn<>("subject 4");
		subjectColumn4.setMinWidth(150);
		subjectColumn4.setCellValueFactory(new PropertyValueFactory<Monthly, String>("subject4"));
						
		// subject5 column
		TableColumn <Monthly, String> subjectColumn5 = new TableColumn<>("subject 5");
		subjectColumn5.setMinWidth(150);
		subjectColumn5.setCellValueFactory(new PropertyValueFactory<Monthly, String>("subject5"));
						
		// subject6 column
		TableColumn <Monthly, String> subjectColumn6 = new TableColumn<>("subject 6");
		subjectColumn6.setMinWidth(150);
		subjectColumn6.setCellValueFactory(new PropertyValueFactory<Monthly, String>("subject6"));		
		
		// Adding columns
		subjectColumn.getColumns().addAll(subjectColumn1, subjectColumn2, subjectColumn3, subjectColumn4, subjectColumn5, subjectColumn6);
		tableView.getColumns().addAll(dateColumn, dayColumn, subjectColumn);		
		
		
		
		//add and delete
		
		Inputsub1 = new TextField();
		Inputsub1.setPromptText("subject 1");
		Inputsub1.setMinWidth(80);
						
		Inputsub2 = new TextField();
		Inputsub2.setPromptText("subject 2");
		Inputsub2.setMinWidth(80);
						
		Inputsub3 = new TextField();
		Inputsub3.setPromptText("subject 3");
		Inputsub3.setMinWidth(80);
				
		Inputsub4 = new TextField();
		Inputsub4.setPromptText("subject 4");
		Inputsub4.setMinWidth(80);
				
		Inputsub5 = new TextField();
		Inputsub5.setPromptText("subject 5");
		Inputsub5.setMinWidth(80);
				
		Inputsub6 = new TextField();
		Inputsub6.setPromptText("subject 6");
		Inputsub6.setMinWidth(80);
				
		// add and delete buttons
		
		Button addButton = new Button("Add");
		addButton.setOnAction(e -> addButtonClicked());
		
		Button deleteButton = new Button("Delete");
        Button toSave = new Button("Save");                                                                                              // add save functionality
		
		Button toExit = new Button("Exit");
		
		//adding button in HBox
		HBox hBox1 = new HBox();
		HBox hBox2 = new HBox();
		VBox vBoxAtBottom = new VBox();
		hBox1.setPadding(new Insets(20, 80, 20, 90));
		hBox1.setSpacing(70);
	    hBox1.getChildren().addAll(Inputsub1, Inputsub2, Inputsub3, Inputsub4, Inputsub5, Inputsub6, addButton, deleteButton);
	    hBox2.getChildren().addAll(toExit, toSave);
		hBox2.setPadding(new Insets(5, 10, 20, 30));
		hBox2.setSpacing(1750);
		vBoxAtBottom.getChildren().addAll(hBox1, hBox2);		
		
		layout.setCenter(tableView);
		layout.setBottom(vBoxAtBottom);
		
		Scene monthlyScene = new Scene(layout);		
		
		
		
		
		
		
		subStage.setTitle("Attendance Manager");
		subStage.setScene(monthlyScene);
		subStage.show();
	}
	
	
	public   void addButtonClicked()
	{
		Monthly monthly = new Monthly();
		
		monthly.setSubject1(Inputsub1.getText());
		monthly.setSubject2(Inputsub2.getText());
		monthly.setSubject3(Inputsub3.getText());
		monthly.setSubject4(Inputsub4.getText());
		monthly.setSubject5(Inputsub5.getText());
		monthly.setSubject6(Inputsub6.getText());
		monthly.setDay(createBox());
		//monthly.setDate(selectDate());
				
		tableView.getItems().add(monthly);
		
		
		
		Inputsub1.clear();
		Inputsub2.clear();
		Inputsub3.clear();	
		Inputsub4.clear();
		Inputsub5.clear();
		Inputsub6.clear();
		
	}
	
	public  ChoiceBox createBox()
	{
		ChoiceBox box = new ChoiceBox();
		box.getItems().addAll("Monday", "Tuesday");
		box.setValue("Monday");
		return box;
	}
	
	
	public DatePicker selectDate()
	{
		LocalDate date = datePicker.getValue();
		dateLabel.setText("Date :" + date);
		datePicker.setShowWeekNumbers(true);
		return datePicker;
	}

		
}






