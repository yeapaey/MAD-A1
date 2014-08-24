package com.yeapMAD.assignment1.view;

import java.util.LinkedList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yeapMAD.assignment1.R;
import com.yeapMAD.assignment1.model.PlannedEvent;

public class EventsAgendaAdapter extends BaseAdapter
{
	private LinkedList<PlannedEvent> events; // Probably change this to something more generic
	private Context context;
	
	public EventsAgendaAdapter(Context context)
	{
		this.context = context;
		events = new LinkedList<PlannedEvent>();
	}
	
	public EventsAgendaAdapter(Context context, LinkedList<PlannedEvent> events)
	{
		this.context = context;
		updateEvents(events);
	}
	
	public void updateEvents(LinkedList<PlannedEvent> events)
	{
		this.events = events;
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
		return events.get(position);
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
			convertView = LayoutInflater.from(context).inflate(R.layout.event_list_element, parent, false);
		}
		
		TextView title = (TextView) convertView.findViewById(R.id.event_list_title);
		TextView note = (TextView) convertView.findViewById(R.id.event_list_note);
		
		PlannedEvent event = events.get(position);
		title.setText(event.getTitle());
		note.setText(event.getNote());
		
		return convertView;
	}

	// Remove after testing
	public LinkedList<PlannedEvent> getEvents()
	{
		return events;
	}

	
}
