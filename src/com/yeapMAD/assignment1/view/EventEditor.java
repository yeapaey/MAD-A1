package com.yeapMAD.assignment1.view;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.yeapMAD.assignment1.R;
import com.yeapMAD.assignment1.model.DataEngine;
import com.yeapMAD.assignment1.model.PlannedEvent;

public class EventEditor extends Activity
{
	public static final String PASSED_STATE = "com.yeapMAD.assignment1.EventEditor.statePassed";
	private PlannedEvent newEvent;
	private DateFormat timeFormatter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event_editor);
		newEvent = new PlannedEvent("", "", GregorianCalendar.getInstance());
		timeFormatter = new SimpleDateFormat("hh:mm a");
		// Check if bundle has saved state and load if true, otherwise use defaults
		Intent intent = getIntent();
		PlannedEvent event = (PlannedEvent) intent.getSerializableExtra(PASSED_STATE);
		
		if (event != null)
		{
			// Put all this stuff in a method
			EditText title = (EditText) findViewById(R.id.event_title);
			EditText venue = (EditText) findViewById(R.id.event_venue);
			EditText note = (EditText) findViewById(R.id.event_note);
			DatePicker date = (DatePicker) findViewById(R.id.event_edit_date);
			Calendar cal = event.getCalendar();
			
			// Need to update newEvent with loaded data
			
			title.setText(event.getTitle());
			venue.setText(event.getStrAddress());
			note.setText(event.getNote());
			date.updateDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
		}
		else 
		{	// Probably extract the listener into a controller, either one for each button or
			// one controller to handle both buttons.  Constructor should take the calendar to be picked.
			Button startTime = (Button) findViewById(R.id.event_edit_start_time);
			startTime.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View view)
				{
					showTimePickerDialog(view);
				}
			});
			
			startTime.setText(timeFormatter.format(newEvent.getStartTime().getTime()));
		}
		
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
	
	
	public void showTimePickerDialog(View view)
	{
		DialogFragment newFragment = new TimePickerFragment(newEvent.getStartTime());
		newFragment.show(getFragmentManager(), "timePicker");
	}

	// Should this go in an external controller???
	public void onDoneClick(View view)
	{
		TextView title = (TextView) findViewById(R.id.event_title);
		TextView note = (TextView) findViewById(R.id.event_note);
		EditText venue = (EditText) findViewById(R.id.event_venue);
		DatePicker datePicker = (DatePicker) findViewById(R.id.event_edit_date);
		int year = datePicker.getYear();
		int day = datePicker.getDayOfMonth();
		int month = datePicker.getMonth();

		System.out.println("onDoneClick... *****************************************");

		Geocoder geocoder = new Geocoder(getBaseContext());

		String strVenue = venue.getText().toString();
		List<Address> addy = new ArrayList<Address>();
		try
		{
			addy = geocoder.getFromLocationName(strVenue, 1);

			System.out.println("Addy has " + addy.size() + " elements *****************************************");

			// System.out.printf("%s: Lat = %l Long = %l ********************************\n ", s,
			// addy.get(0).getLatitude(), addy.get(0).getLongitude());
		}
		catch (IOException e)
		{
			System.out.println("Exception caught! ******************************************");
			e.printStackTrace();
		}

		DataEngine.addEvent(title.getText().toString(), note.getText().toString(), new GregorianCalendar(year, month,
				day), strVenue, (addy.size() > 0 ? addy.get(0) : null));

	}

	public PlannedEvent getNewEvent()
	{
		return newEvent;
	}
	
	

}
