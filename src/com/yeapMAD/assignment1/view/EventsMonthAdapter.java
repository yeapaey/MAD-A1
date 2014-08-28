package com.yeapMAD.assignment1.view;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
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
import com.yeapMAD.assignment1.model.PlannedEvent;

public class EventsMonthAdapter extends BaseAdapter
{

	private Context context;
	private int year;
	private int month;
	private Calendar present;
	private SortedMap<Calendar, ArrayList<PlannedEvent>> monthGrid; // NB Using Calendar as key may be problematic

	public EventsMonthAdapter(Context context, Collection<PlannedEvent> events)
	{
		super();
		this.context = context;
		present = GregorianCalendar.getInstance();
		year = present.get(Calendar.YEAR);
		month = present.get(Calendar.MONTH);
		int monthLen = present.getActualMaximum(Calendar.DAY_OF_MONTH);
		monthGrid = new TreeMap<Calendar, ArrayList<PlannedEvent>>();

		for (int i = 1; i <= monthLen; ++i) // Make an entry for each day of the month
		{
			monthGrid.put(new GregorianCalendar(year, month, i), new ArrayList<PlannedEvent>());
		}

		updateEvents(events); // Populate dates with appropriate events
	}

	// Assign each event to the day of the month if appropriate
	// This is inefficient as each update requires the whole list be processed again
	// Need to add sort of date's event list after update?? In date order for list view in bottom portion
	public void updateEvents(Collection<PlannedEvent> events)
	{
		for (PlannedEvent event : events)
		{
			Calendar eCal = event.getCalendar();
			if (eCal.get(Calendar.YEAR) == year && eCal.get(Calendar.MONTH) == month)
			{
				List<PlannedEvent> list = monthGrid.get(new GregorianCalendar(year, month, eCal
						.get(Calendar.DAY_OF_MONTH)));
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

		// Using POSITION is potentially a problem, because it doesn't necessarily match up with key values
		Integer objPos = ++position;
		dayNum.setText(objPos.toString());
		eventBool.setText(monthGrid.get(new GregorianCalendar(year, month, objPos)).isEmpty() ? "" : "*");

		return convertView;
	}
}
