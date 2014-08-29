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
		int i, j, month;
		for (month = 0; month < 12; ++month)
		{
			j = 1;
			for (i = 1, j = 1; i <= 30; ++i)
			{
				if (i % 2 != 0)
				{
					collection.add(new PlannedEvent("Month " + (month + 1) + " Event " + j++, "VENue",
							new GregorianCalendar(2014, month, i, 12, 0), new GregorianCalendar(2014, month, i, 12, 0),
							""));
				}
			}
		}
		
	}
}
