package com.yeapMAD.assignment1.model;

import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observer;

import android.app.Application;

public class DataEngine extends Application 
{
	private static LinkedList<PlannedEvent> events;

	public DataEngine()
	{
		super();
		events = new LinkedList<PlannedEvent>();
		DummyData.generateEvents(events);
	}

	public static void addEvent(String title, String notes, Calendar calendar)
	{
		int i;
		for (i = 0; calendar.after(events.get(i).getCalendar()); ++i)
		{
		}
		
		if (i == events.size())
		{
			events.addLast(new PlannedEvent(title, notes, calendar));
		}
		else 
		{
			events.add(i, new PlannedEvent(title, notes, calendar));
		}
		
		
	}

	public static LinkedList<PlannedEvent> getEvents()
	{
		return events;
	}

}
