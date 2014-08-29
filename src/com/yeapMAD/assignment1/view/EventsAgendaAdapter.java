package com.yeapMAD.assignment1.view;

import java.text.SimpleDateFormat;
import java.util.Collection;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yeapMAD.assignment1.R;
import com.yeapMAD.assignment1.model.PlannedEvent;

public class EventsAgendaAdapter extends AbstractAdapter
{
	private Activity activity;

	public EventsAgendaAdapter(Activity activity, Collection<PlannedEvent> collection)
	{
		super(activity, collection);
		this.activity = activity;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		if (convertView == null)
		{
			convertView = LayoutInflater.from(activity).inflate(R.layout.event_list_element, parent, false);
		}

		TextView title = (TextView) convertView.findViewById(R.id.event_list_title);
		TextView date = (TextView) convertView.findViewById(R.id.event_list_date);
		TextView startTime = (TextView) convertView.findViewById(R.id.event_start_time);
		TextView endTime = (TextView) convertView.findViewById(R.id.event_end_time);
		TextView note = (TextView) convertView.findViewById(R.id.event_list_note);
		TextView venue = (TextView) convertView.findViewById(R.id.event_list_venue);

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy '-' ");
		SimpleDateFormat timeFormat = new SimpleDateFormat("hh':'mm aa");

		PlannedEvent event = (PlannedEvent) getItem(position);
		title.setText(event.getTitle());
		date.setText(dateFormat.format(event.getDate().getTime()));
		startTime.setText(timeFormat.format(event.getStartTime().getTime()));
		endTime.setText(timeFormat.format(event.getEndTime().getTime()));

		note.setText(event.getNote());
		venue.setText(event.getVenue());

		return convertView;
	}

}
