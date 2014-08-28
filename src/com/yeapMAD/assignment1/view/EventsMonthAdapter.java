package com.yeapMAD.assignment1.view;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yeapMAD.assignment1.R;
import com.yeapMAD.assignment1.model.DataEngine;
import com.yeapMAD.assignment1.model.PlannedEvent;

public class EventsMonthAdapter extends BaseAdapter
{

	private Context context;
	private int year;
	private int month;
	private Calendar present;
	private SortedMap<Integer, ArrayList<PlannedEvent>> monthGrid; // Using Calendar for key WAS problematic

	public EventsMonthAdapter(Context context, Collection<PlannedEvent> events)
	{
		super();
		this.context = context;
		monthGrid = new TreeMap<Integer, ArrayList<PlannedEvent>>();
		setCurrentDate(GregorianCalendar.getInstance());
		updateEvents(events); // Populate dates with appropriate events
	}

	
	public void setCurrentDate(Calendar newDate)
	{
		present = newDate;
		year = present.get(Calendar.YEAR);
		month = present.get(Calendar.MONTH);
		
		updateEvents(DataEngine.getEvents());
	}
	
	public Calendar getCurrentDate()
	{
		return present; // would be better to make this immutable
	}
	// Assign each event to the day of the month if appropriate
	// This is inefficient as each update requires the whole list be processed again
	// Need to add sort of date's event list after update?? In date order for list view in bottom portion
	public void updateEvents(Collection<PlannedEvent> events)
	{
		monthGrid.clear();
		
		for (int i = 1; i <= present.getActualMaximum(Calendar.DAY_OF_MONTH); ++i) // Make an entry for each day of the month
		{
			monthGrid.put(i, new ArrayList<PlannedEvent>());
		}
		
		for (PlannedEvent event : events)
		{
			Calendar eCal = event.getCalendar();
			if (eCal.get(Calendar.YEAR) == year && eCal.get(Calendar.MONTH) == month)
			{
				List<PlannedEvent> list = monthGrid.get(eCal.get(Calendar.DAY_OF_MONTH));
				list.add(event);
				// Sort list here
			}
		}

		notifyDataSetChanged();
	}

	@Override
	public int getCount()
	{
		return monthGrid.size();
	}

	@Override
	public Object getItem(int position)
	{
		
		return monthGrid.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		if (convertView == null)
		{
			convertView = LayoutInflater.from(context).inflate(R.layout.event_month_square, parent, false);
		}

		TextView dayNum = (TextView) convertView.findViewById(R.id.event_month_dayNum);
		TextView eventBool = (TextView) convertView.findViewById(R.id.event_month_eventBool);

		Integer objPos = ++position;
		dayNum.setText(objPos.toString());
		eventBool.setText(monthGrid.get(objPos).isEmpty() ? "" : "*");

		return convertView;
	}
}
