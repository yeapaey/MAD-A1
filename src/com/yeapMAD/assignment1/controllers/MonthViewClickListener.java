package com.yeapMAD.assignment1.controllers;

import java.util.ArrayList;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.yeapMAD.assignment1.model.PlannedEvent;
import com.yeapMAD.assignment1.view.AbstractAdapter;
import com.yeapMAD.assignment1.view.EventsMonthAdapter;

public class MonthViewClickListener implements OnItemClickListener
{
	private EventsMonthAdapter origin;
	private AbstractAdapter destination;
	
	public MonthViewClickListener(EventsMonthAdapter origin, AbstractAdapter destination)
	{
		this.origin = origin;
		this.destination = destination;
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id)
	{
		@SuppressWarnings("unchecked")
		ArrayList<PlannedEvent> events = (ArrayList<PlannedEvent>) origin.getItem(position);
		destination.updateEvents(events);
	}
}
