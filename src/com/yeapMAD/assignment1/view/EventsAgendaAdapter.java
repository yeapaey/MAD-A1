package com.yeapMAD.assignment1.view;

import java.text.DateFormat;
import java.util.Collection;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yeapMAD.assignment1.R;
import com.yeapMAD.assignment1.model.PlannedEvent;

public class EventsAgendaAdapter extends AbstractAdapter
{
	// private Collection<PlannedEvent> collection;
	private Context context;

	public EventsAgendaAdapter(Context context, Collection<PlannedEvent> collection)
	{
		super(context, collection);
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
		TextView date = (TextView) convertView.findViewById(R.id.event_list_date);
		TextView note = (TextView) convertView.findViewById(R.id.event_list_note);
		TextView venue = (TextView) convertView.findViewById(R.id.event_list_venue);
		// TextView lat = (TextView) convertView.findViewById(R.id.event_list_lat);
		// TextView lon = (TextView) convertView.findViewById(R.id.event_list_long);
		DateFormat dFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);

		PlannedEvent event = (PlannedEvent) getItem(position);
		title.setText(event.getTitle());
		date.setText(dFormat.format(event.getDate().getTime()));

		note.setText(event.getNote());
		venue.setText(event.getVenue());

		// if (event.getAddress().hasLatitude() && event.getAddress().hasLongitude())
		// {
		// Double latD = event.getAddress().getLatitude();
		// Double lonD = event.getAddress().getLongitude();
		// lat.setText(latD.toString());
		// lon.setText(lonD.toString());
		// }

		return convertView;
	}

}
