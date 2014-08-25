package com.yeapMAD.assignment1.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import android.app.Activity;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.yeapMAD.assignment1.R;
import com.yeapMAD.assignment1.model.DataEngine;

public class EventEditor extends Activity
{

	private int count = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event_editor);
		
		// Check if bundle has saved state and load if true, otherwise use defaults
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

//	Should this go in an external controller???
	public void onDoneClick(View view)
	{
		TextView title = (TextView) findViewById(R.id.event_title);
		TextView note = (TextView) findViewById(R.id.event_note);
		EditText venue = (EditText) findViewById(R.id.event_venue);
		DatePicker datePicker = (DatePicker) findViewById(R.id.event_date);
		int year = datePicker.getYear();
		int day = datePicker.getDayOfMonth();
		int month = datePicker.getMonth();
		
		System.out.println("onDoneClick... *****************************************");
		
		Geocoder geocoder = new Geocoder(getBaseContext());
		
		String strVenue = venue.getText().toString();
		List<Address> addy = new ArrayList<Address>();
		try {
			addy = geocoder.getFromLocationName(strVenue, 1);

			System.out.println("Addy has " + addy.size() + " elements *****************************************");
			
//			System.out.printf("%s: Lat = %l Long = %l ********************************\n ", s, addy.get(0).getLatitude(), addy.get(0).getLongitude());
		} 
		catch (IOException e) {
			System.out.println("Exception caught! ******************************************");
			e.printStackTrace();
		}
		
		
		DataEngine.addEvent(title.getText().toString(), note.getText().toString(), new GregorianCalendar(year, month, day), strVenue, addy.get(0));
		

	}

}
