package com.yeapMAD.assignment1.view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yeapMAD.assignment1.R;
import com.yeapMAD.assignment1.model.PlannedEvent;

public class EventsDateListAdapter extends BaseAdapter
{
	private Collection<PlannedEvent> events;
	private Context context;
	
	public EventsDateListAdapter(Context context, Collection<PlannedEvent> events)
	{
		this.context = context;
		this.events = events;
	}
	
	public void updateEvents(Collection<PlannedEvent> events)
	{
		if (events == null)
		{
			System.out.println("events was null **********************");
			events = new ArrayList<PlannedEvent>();
		}
		else 
		{
			this.events = events;
		}
	
		notifyDataSetChanged();
	}
	
	
	@Override
	public int getCount()
	{
		return events.size();
	}

	@Override
	public Object getItem(int position)
	{
		Iterator<PlannedEvent> iter = events.iterator();
		PlannedEvent event = null;
		
		if (position > getCount()) // out of bounds
		{
			System.out.println("returned a null item ***********************************************");
			return null;
		}
		
		for (int i = 0; i <= position; ++i)
		{
			System.out.println("iter.next() ***********************************************");
			event = iter.next();
		}
		
		return event;
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
			convertView = LayoutInflater.from(context).inflate(R.layout.event_date_list_element, parent, false);
		}
		
		TextView title = (TextView) convertView.findViewById(R.id.event_list_title);
		TextView venue = (TextView) convertView.findViewById(R.id.event_list_venue);
		
		PlannedEvent event = (PlannedEvent) getItem(position);
		if (event == null)
		{
			System.out.println("returned a null view ***********************************************");
			return null;
		}
		
		title.setText(event.getTitle());
		venue.setText(event.getVenue());
		
		return convertView;
	}

}
