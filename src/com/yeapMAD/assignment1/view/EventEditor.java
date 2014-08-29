package com.yeapMAD.assignment1.view;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.yeapMAD.assignment1.R;
import com.yeapMAD.assignment1.controllers.DatePickerButtonListener;
import com.yeapMAD.assignment1.controllers.TimePickerButtonListener;
import com.yeapMAD.assignment1.model.DataEngine;
import com.yeapMAD.assignment1.model.PlannedEvent;

public class EventEditor extends Activity
{
	public static final String PASSED_STATE = "com.yeapMAD.assignment1.EventEditor.statePassed";
	private PlannedEvent newEvent;
	private DateFormat timeFormatter;
	private DateFormat dateFormatter;
	private int defaultEventLength;
	
	@SuppressLint("SimpleDateFormat")
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event_editor);
		timeFormatter = new SimpleDateFormat("hh:mm a");
		dateFormatter = new SimpleDateFormat("dd MMM yyyy");
		defaultEventLength = 60;

		// Check if bundle has saved state and load if true, otherwise use defaults
		Intent intent = getIntent();
		PlannedEvent prevState = (PlannedEvent) intent.getSerializableExtra(PASSED_STATE);
		
		if (prevState != null)
		{
			newEvent = prevState;

			EditText title = (EditText) findViewById(R.id.event_title);
			EditText venue = (EditText) findViewById(R.id.event_venue);
			EditText note = (EditText) findViewById(R.id.event_note);
			title.setText(newEvent.getTitle());
			venue.setText(newEvent.getVenue());
			note.setText(newEvent.getNote());
		}
		else 
		{
			newEvent = new PlannedEvent(GregorianCalendar.getInstance());
			// Set a suitable default start and end time
			int startMinute = newEvent.getStartTime().get(Calendar.MINUTE);
			int offset;
			if (startMinute <= 30)
			{
				offset = 30 - startMinute;
			}
			else
			{
				offset = 60 - startMinute;
			}
			offsetTime(newEvent.getStartTime(), offset);
			offsetTime(newEvent.getEndTime(), offset + defaultEventLength);
		}

		// All this needs to happen regardless of state loaded or not
		Button datePicker = (Button) findViewById(R.id.event_edit_date);
		datePicker.setOnClickListener(new DatePickerButtonListener(this, newEvent.getDate(), dateFormatter));
		datePicker.setText(dateFormatter.format(newEvent.getDate().getTime()));

		Button startTime = (Button) findViewById(R.id.event_edit_start_time);
		startTime.setOnClickListener(new TimePickerButtonListener(this, newEvent.getStartTime(), timeFormatter));
		startTime.setText(timeFormatter.format(newEvent.getStartTime().getTime()));

		Button endTime = (Button) findViewById(R.id.event_edit_end_time);
		endTime.setOnClickListener(new TimePickerButtonListener(this, newEvent.getEndTime(), timeFormatter));
		endTime.setText(timeFormatter.format(newEvent.getEndTime().getTime()));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.event_editor, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		int id = item.getItemId();
		switch (id)
		{
		case R.id.action_event_edit_cancel:
			break;
		case R.id.action_event_edit_done:
			onDoneClick(getCurrentFocus());
			break;
		default:
			return super.onOptionsItemSelected(item);
		}

		finish();
		return true;
	}
	
	
	public void onDoneClick(View view)
	{
		TextView title = (TextView) findViewById(R.id.event_title);
		TextView note = (TextView) findViewById(R.id.event_note);
		EditText venue = (EditText) findViewById(R.id.event_venue);

		newEvent.setTitle(title.getText().toString());
		newEvent.setVenue(venue.getText().toString());
		newEvent.setNote(note.getText().toString());

		// Geocoder geocoder = new Geocoder(getBaseContext());
		//
		// List<Address> addresses = new ArrayList<Address>();
		// try
		// {
		// addresses = geocoder.getFromLocationName(newEvent.getVenue(), 1);
		// if (!addresses.isEmpty())
		// {
		// newEvent.setAddress(addresses.get(0));
		// }
		// }
		// catch (IOException e)
		// {
		// System.out.println("Exception caught! ******************************************");
		// e.printStackTrace();
		// }

		DataEngine.addEvent(newEvent);
	}

	public void offsetTime(Calendar cal, int mins)
	{
		int result = cal.get(Calendar.MINUTE) + mins;
		int hourRoll = (result) / 60;
		if (result < 0) // I think this works
		{
			--hourRoll;
		}
		cal.roll(Calendar.MINUTE, mins);
		cal.roll(Calendar.HOUR_OF_DAY, hourRoll);
	}

	public PlannedEvent getNewEvent()
	{
		return newEvent;
	}
}

