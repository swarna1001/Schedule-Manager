package com.attendance;

import java.time.LocalDate;

import javafx.scene.control.ChoiceBox;

public class Day {
	private ChoiceBox day;
	private LocalDate date;
	
	public Day(ChoiceBox day)
	{
		this.day = day;
		//this.date = date;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public ChoiceBox getDay() {
		return day;
	}

	public void setDay(ChoiceBox day) {
		this.day = day;
		
	}

}
