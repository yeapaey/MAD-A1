package controllers;

import java.util.ArrayList;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

import com.yeapMAD.assignment1.model.PlannedEvent;
import com.yeapMAD.assignment1.view.EventsDateListAdapter;
import com.yeapMAD.assignment1.view.EventsMonthAdapter;

public class MonthViewClickListener implements OnItemClickListener
{
	private EventsDateListAdapter listAdapter;
	private EventsMonthAdapter monthAdapter;
	
	public MonthViewClickListener(EventsDateListAdapter listAdapter, EventsMonthAdapter monthAdapter)
	{
		this.listAdapter = listAdapter;
		this.monthAdapter = monthAdapter;
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id)
	{
		Integer pos = ++position;
		@SuppressWarnings("unchecked")
		ArrayList<PlannedEvent> events = (ArrayList<PlannedEvent>) monthAdapter.getItem(position);
		listAdapter.updateEvents(events);

		
	}

}
