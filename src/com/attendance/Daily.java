package com.attendance;
/* for creating schedules
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Daily 
{
	
	private double timing;
	private String theory;
	private String practical;
	private ChoiceBox day;
	


	public ChoiceBox getDay() {
		return day;
	}

	public void setDay(ChoiceBox day) {
		this.day = day;
	}

	public Daily()
	{
		this.timing = timing;
		this.theory = theory;
		this.practical = practical; 
		this.day = day;
	}
	
	public Daily(ChoiceBox day, String theory,  String practical )
	{
		this.practical = practical;
		this.day = day;
		this.theory = theory;
		
	}

	public double getTiming() {
		return timing;
	}

	public void setTiming(double timing) {
		this.timing = timing;
	}

	public String getTheory() {
		return theory;
	}

	public void setTheory(String theory) {
		this.theory = theory;
	}

	public String getPractical() {
		return practical;
	}

	public void setPractical(String practical) {
		this.practical = practical;
	}
}
