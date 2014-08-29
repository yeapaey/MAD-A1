package com.yeapMAD.assignment1.view;

import java.util.Collection;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yeapMAD.assignment1.R;
import com.yeapMAD.assignment1.model.PlannedEvent;

public class EventsDateListAdapter extends AbstractAdapter
{
	private Context context;
	private Collection<PlannedEvent> collection;

	public EventsDateListAdapter(Context context, Collection<PlannedEvent> collection)
	{
		super(context, collection);
		this.context = context;
		this.collection = collection;
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
