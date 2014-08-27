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

	// private SortedMap<Integer, ArrayList<PlannedEvent>> data;
	// Don't put this here. It should go somewhere else (probably owned by the activity)
	public EventsMonthAdapter(Context context, Collection<PlannedEvent> events)
	{
		super();
		this.context = context;
		present = GregorianCalendar.getInstance();
		year = present.get(Calendar.YEAR);
		month = present.get(Calendar.MONTH);
		int monthLen = present.getActualMaximum(Calendar.DAY_OF_MONTH);
		monthGrid = new TreeMap<Calendar, ArrayList<PlannedEvent>>();
		
		// Make an entry for each day of the month
		for (int i = 1; i <= monthLen; ++i)
		{
			monthGrid.put(new GregorianCalendar(year, month, i), new ArrayList<PlannedEvent>());
		}

		updateEvents(events);
		// Assign each event to the day of the month if appropriate
		// for (PlannedEvent event : events)
		// {
		// Calendar eCal = event.getCalendar();
		// if (eCal.get(Calendar.YEAR) == year && eCal.get(Calendar.MONTH) == month)
		// {
		// List<PlannedEvent> list = monthGrid.get(eCal.get(Calendar.DAY_OF_MONTH));
		// list.add(event);
		// // Sort list here
		// }
		// }

		// Calendar monthEnd = new GregorianCalendar(year, month, present.getActualMaximum(Calendar.DAY_OF_MONTH));


		// Iterator<PlannedEvent> iter = events.iterator();
		// PlannedEvent current = null;
//		boolean found = false;
//
//		while (iter.hasNext() && !found)
//		{
//			current = iter.next();
//			if (current.getCalendar().get(Calendar.YEAR) == present.get(Calendar.YEAR)
//					&& current.getCalendar().get(Calendar.MONTH) == present.get(Calendar.MONTH))
//			{
//				found = true;
//			}
//		}

		// This would actually be a good time to divide events up by day of month
// iter = 0;
// PlannedEvent current = events.get(iter);
// while (!current.getCalendar().after(monthEnd))
// {
// if (current.getCalendar().get(Calendar.MONTH) == monthEnd.get(Calendar.MONTH))
// {
// MonthDayWrapper item = new MonthDayWrapper(current.getCalendar().get(Calendar.DAY_OF_MONTH), true);
// monthGrid.put(item.getKey(), item); // This may not be right
// }
//
// ++iter;
// current = DataEngine.getEvents().get(iter);
// }
	}

	// Assign each event to the day of the month if appropriate
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
		// item = monthGrid.get(++position);

		Integer objPos = ++position;
		dayNum.setText(objPos.toString());
		eventBool.setText(monthGrid.get(new GregorianCalendar(year, month, objPos)).isEmpty() ? "" : "*");

		return convertView;
	}

}
