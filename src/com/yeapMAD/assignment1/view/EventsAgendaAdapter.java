package com.yeapMAD.assignment1.view;

<<<<<<< HEAD
import java.text.DateFormat;
import java.util.LinkedList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
=======
import java.text.SimpleDateFormat;
import java.util.Collection;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
>>>>>>> origin/MonthView
import android.widget.TextView;

import com.yeapMAD.assignment1.R;
import com.yeapMAD.assignment1.model.PlannedEvent;

<<<<<<< HEAD
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
=======
public class EventsAgendaAdapter extends AbstractAdapter
{
	private Activity activity;

	public EventsAgendaAdapter(Activity activity, Collection<PlannedEvent> collection)
	{
		super(activity, collection);
		this.activity = activity;
>>>>>>> origin/MonthView
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		if (convertView == null)
		{
<<<<<<< HEAD
			convertView = LayoutInflater.from(context).inflate(R.layout.event_list_element, parent, false);
=======
			convertView = LayoutInflater.from(activity).inflate(R.layout.event_list_element, parent, false);
>>>>>>> origin/MonthView
		}

		TextView title = (TextView) convertView.findViewById(R.id.event_list_title);
		TextView date = (TextView) convertView.findViewById(R.id.event_list_date);
<<<<<<< HEAD
		TextView note = (TextView) convertView.findViewById(R.id.event_list_note);
		TextView venue = (TextView) convertView.findViewById(R.id.event_list_venue);
		TextView lat = (TextView) convertView.findViewById(R.id.event_list_lat);
		TextView lon = (TextView) convertView.findViewById(R.id.event_list_long);
		DateFormat dFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);

		PlannedEvent event = events.get(position);
		title.setText(event.getTitle());
		date.setText(dFormat.format(event.getDate().getTime()));
=======
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
>>>>>>> origin/MonthView

		note.setText(event.getNote());
		venue.setText(event.getVenue());

<<<<<<< HEAD
		if (event.getAddress().hasLatitude() && event.getAddress().hasLongitude())
		{
			Double latD = event.getAddress().getLatitude();
			Double lonD = event.getAddress().getLongitude();
			lat.setText(latD.toString());
			lon.setText(lonD.toString());
		}

=======
>>>>>>> origin/MonthView
		return convertView;
	}

}
