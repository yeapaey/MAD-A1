package com.yeapMAD.assignment1.model;

import java.util.LinkedList;

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

	public static void addEvent(PlannedEvent newEvent)
	{
		int iter;
		for (iter = 0; newEvent.getDate().after(events.get(iter).getDate()); ++iter)
		{
		}

		if (iter == events.size())
		{
			events.addLast(newEvent);
		}
		else
		{
			events.add(iter, newEvent);
		}
	}

	public static LinkedList<PlannedEvent> getEvents()
	{
		return events;
	}
}
