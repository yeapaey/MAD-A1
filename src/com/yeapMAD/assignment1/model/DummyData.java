package com.yeapMAD.assignment1.model;

import java.util.Collection;
import java.util.GregorianCalendar;

public class DummyData
{
	public DummyData()
	{
	}

	public static void generateEvents(Collection<PlannedEvent> collection)
	{
		collection.add(new PlannedEvent("Ghandi's Barmitsvah", "Bring a cap!", new GregorianCalendar(2014, 7, 14)));
		collection.add(new PlannedEvent("The Rapture", "Get lootin!", new GregorianCalendar(2014, 11, 9)));
		collection.add(new PlannedEvent("The Rapture again", "It didn't actually happen the first time",
				new GregorianCalendar(2014, 8, 19)));
		collection.add(new PlannedEvent("Hell freezes over", "Bring skates", new GregorianCalendar(2015, 1, 30)));
	}
}
