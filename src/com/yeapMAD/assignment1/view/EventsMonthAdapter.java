package com.yeapMAD.assignment1.view;

import java.util.ArrayList;
import java.util.SortedMap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yeapMAD.assignment1.R;
import com.yeapMAD.assignment1.model.MonthDayWrapper;

public class EventsMonthAdapter extends BaseAdapter
{

	private Context context;
	private ArrayList<Integer> dates;
	SortedMap<Integer, MonthDayWrapper> monthGrid;

	// private SortedMap<Integer, ArrayList<PlannedEvent>> data;
	// Don't put this here. It should go somewhere else (probably owned by the activity)

	public EventsMonthAdapter(Context context, SortedMap<Integer, MonthDayWrapper> monthGrid)
	{
		super();
		this.context = context;
		this.monthGrid = monthGrid;
// this.dates = new ArrayList<Integer>();

/*		Calendar cal = GregorianCalendar.getInstance();
		int monthLen = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		// Just creating the arrays ahead of time and referencing them when
		// needed may be quicker.
		for (int i = 1; i <= monthLen; ++i)
		{
			this.dates.add(i);
		}*/
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
		MonthDayWrapper item = monthGrid.get(++position);

		dayNum.setText(item.getKey().toString());
		eventBool.setText(item.getVal() ? "*" : "");

		return convertView;
	}

}
