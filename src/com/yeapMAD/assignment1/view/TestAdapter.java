package com.yeapMAD.assignment1.view;

import java.util.LinkedList;

import com.yeapMAD.assignment1.R;
import com.yeapMAD.assignment1.model.PlannedEvent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TestAdapter extends EventsAgendaAdapter
{
	private Context context;
	
	public TestAdapter(Context context, LinkedList<PlannedEvent> events)
	{
		super(context, events);
		this.context = context;
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
		
		PlannedEvent event = getEvents().get(position);
		title.setText("Month");
		note.setText("Month");
		
		return convertView;
	}
	
}
