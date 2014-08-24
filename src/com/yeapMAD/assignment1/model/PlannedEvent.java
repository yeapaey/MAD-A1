package com.yeapMAD.assignment1.model;

import java.util.Calendar;

public class PlannedEvent
{
	private String title;
	private String note;
	private Calendar calendar;
	
//	public PlannedEvent(String title, Calendar calendar)
//	{
//		this.title = title;
//		notes = new String();
//		this.calendar = calendar;
//	}
	
	
	public PlannedEvent(String title, String note, Calendar calendar)
	{
		this.title = title;
		this.note = note;
		this.calendar = calendar;
	}


	public String getTitle()
	{
		return title;
	}


	public void setTitle(String title)
	{
		this.title = title;
	}


	public String getNote()
	{
		return note;
	}


	public void setNote(String notes)
	{
		this.note = notes;
	}


	public Calendar getCalendar()
	{
		return calendar;
	}


	public void setCalendar(Calendar calendar)
	{
		this.calendar = calendar;
	}
	
	
	

}
