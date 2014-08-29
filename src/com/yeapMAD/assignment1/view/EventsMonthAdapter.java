package com.yeapMAD.assignment1.view;

<<<<<<< HEAD
=======
import java.text.SimpleDateFormat;
>>>>>>> origin/MonthView
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

<<<<<<< HEAD
import android.content.Context;
=======
import android.annotation.SuppressLint;
import android.app.Activity;
>>>>>>> origin/MonthView
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
<<<<<<< HEAD
	private Context context;
=======
	private Activity activity;
>>>>>>> origin/MonthView
	private int year;
	private int month;
	private Calendar present;
	private SortedMap<Integer, ArrayList<PlannedEvent>> monthGrid; // Using Calendar for key WAS problematic

<<<<<<< HEAD
	public EventsMonthAdapter(Context context, Collection<PlannedEvent> events)
	{
		super();
		this.context = context;
		monthGrid = new TreeMap<Integer, ArrayList<PlannedEvent>>();
		setCurrentDate(GregorianCalendar.getInstance());
		updateEvents(events); // Populate dates with appropriate events
=======
	public EventsMonthAdapter(Activity activity, Collection<PlannedEvent> events)
	{
		super();
		this.activity = activity;
		monthGrid = new TreeMap<Integer, ArrayList<PlannedEvent>>();
		setCurrentDate(GregorianCalendar.getInstance());
>>>>>>> origin/MonthView
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
<<<<<<< HEAD
	// Assign each event to the day of the month if appropriate
	// This is inefficient as each update requires the whole list be processed again
	// Need to add sort of date's event list after update?? In date order for list view in bottom portion
=======

	// Assign each event to the day of the month if appropriate
	@SuppressLint("SimpleDateFormat")
>>>>>>> origin/MonthView
	public void updateEvents(Collection<PlannedEvent> events)
	{
		monthGrid.clear();
		
		for (int i = 1; i <= present.getActualMaximum(Calendar.DAY_OF_MONTH); ++i) // Make an entry for each day of the month
		{
			monthGrid.put(i, new ArrayList<PlannedEvent>());
		}
		
		for (PlannedEvent event : events)
		{
			Calendar eCal = event.getDate();
			if (eCal.get(Calendar.YEAR) == year && eCal.get(Calendar.MONTH) == month)
			{
				List<PlannedEvent> list = monthGrid.get(eCal.get(Calendar.DAY_OF_MONTH));
				list.add(event);
				// Sort list here
			}
		}
<<<<<<< HEAD
=======
		TextView month = (TextView) activity.findViewById(R.id.event_current_month);
		SimpleDateFormat formatter = new SimpleDateFormat("MMMM");
		month.setText(formatter.format(present.getTime()));
>>>>>>> origin/MonthView

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
<<<<<<< HEAD
		
		return monthGrid.get(position);
=======
		return monthGrid.get(++position);
>>>>>>> origin/MonthView
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
<<<<<<< HEAD
			convertView = LayoutInflater.from(context).inflate(R.layout.event_month_square, parent, false);
=======
			convertView = LayoutInflater.from(activity.getBaseContext()).inflate(R.layout.event_month_square, parent,
					false);
>>>>>>> origin/MonthView
		}

		TextView dayNum = (TextView) convertView.findViewById(R.id.event_month_dayNum);
		TextView eventBool = (TextView) convertView.findViewById(R.id.event_month_eventBool);

<<<<<<< HEAD
		Integer objPos = ++position;
		dayNum.setText(objPos.toString());
		eventBool.setText(monthGrid.get(objPos).isEmpty() ? "" : "*");

		return convertView;
	}
=======
		Integer day = position + 1;
		dayNum.setText(day.toString());
		eventBool.setText(monthGrid.get(day).isEmpty() ? "" : "*");

		return convertView;
	}

	public int getYear()
	{
		return year;
	}

	public int getMonth()
	{
		return month;
	}

>>>>>>> origin/MonthView
}
