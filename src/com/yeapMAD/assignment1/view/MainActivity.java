package com.yeapMAD.assignment1.view;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import android.app.ActionBar;
import android.app.ActionBar.OnNavigationListener;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.yeapMAD.assignment1.R;
import com.yeapMAD.assignment1.controllers.ChangeMonthButtonListener;
import com.yeapMAD.assignment1.controllers.MonthViewClickListener;
import com.yeapMAD.assignment1.model.DataEngine;
import com.yeapMAD.assignment1.model.PlannedEvent;

public class MainActivity extends Activity
{
	private EventsAgendaAdapter agendaAdapter;
	private EventsMonthAdapter monthAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		agendaAdapter = new EventsAgendaAdapter(this, DataEngine.getEvents());
		monthAdapter = null;

		SpinnerAdapter spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.string_array_display_modes,
				android.R.layout.simple_dropdown_item_1line);

		// Put in separate class?
		OnNavigationListener onNavListener = new OnNavigationListener()
		{
			@SuppressWarnings("unused")
			String[] strings = getResources().getStringArray(R.array.string_array_display_modes);

			@Override
			public boolean onNavigationItemSelected(int itemPosition, long itemId)
			{
				switch (itemPosition) // Maybe an enum or private keys could be used here instead of hard coding
				{
				case 0:
					launchAgendaView();
					break;
				case 1:
					launchMonthView();
					break;
				default:
					launchAgendaView();
				}

				return true;
			}
		};

		ActionBar actionBar = getActionBar();
		actionBar.setListNavigationCallbacks(spinnerAdapter, onNavListener);
		actionBar.setNavigationMode(android.app.ActionBar.NAVIGATION_MODE_LIST);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		agendaAdapter.updateEvents(DataEngine.getEvents());
		if (monthAdapter != null)
		{
			monthAdapter.updateEvents(DataEngine.getEvents());
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		int id = item.getItemId();
		if (id == R.id.action_add)
		{
			return openEventEditor();
		}
		return super.onOptionsItemSelected(item);
	}

	private boolean openEventEditor()
	{
		Intent intent = new Intent(this, EventEditor.class);
		startActivity(intent);

		return true;
	}

	private void launchAgendaView()
	{
		setContentView(R.layout.activity_main_agenda);
		ListView agendaView = (ListView) findViewById(R.id.events_agenda_list);

		if (agendaAdapter == null) // This check probably isn't necessary
		{
			agendaAdapter = new EventsAgendaAdapter(this, DataEngine.getEvents());
		}

		agendaView.setAdapter(agendaAdapter);
		agendaView.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				Toast.makeText(parent.getContext(), "Pressed Item" + ((Integer) position).toString(),
						Toast.LENGTH_SHORT).show();
			}
		});
	}

	// This view should really have its own manager to manage the different months etc.
	private void launchMonthView()
	{
		setContentView(R.layout.activity_main_month);
		GridView monthView = (GridView) findViewById(R.id.events_month_list);
		ListView dateList = (ListView) findViewById(R.id.events_month_date_list);
		EventsDateListAdapter dateListAdapter = new EventsDateListAdapter(this, new ArrayList<PlannedEvent>());
		dateList.setAdapter(dateListAdapter);
		
		if (monthAdapter == null)
		{
			monthAdapter = new EventsMonthAdapter(getBaseContext(), DataEngine.getEvents());
		}
		
		monthView.setAdapter(monthAdapter);
		monthView.setOnItemClickListener(new MonthViewClickListener(dateListAdapter, monthAdapter));	
		
		ChangeMonthButtonListener buttonListener = new ChangeMonthButtonListener(monthAdapter);
		Button nextButton = (Button) findViewById(R.id.events_month_next_button);
		Button prevButton = (Button) findViewById(R.id.events_month_previous_button);
		nextButton.setOnClickListener(buttonListener);
		prevButton.setOnClickListener(buttonListener);
	}
}
