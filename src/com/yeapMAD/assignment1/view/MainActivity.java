package com.yeapMAD.assignment1.view;

import java.util.SortedMap;

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
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.yeapMAD.assignment1.R;
import com.yeapMAD.assignment1.model.DataEngine;
import com.yeapMAD.assignment1.model.MonthDayWrapper;

public class MainActivity extends Activity
{
	private EventsAgendaAdapter agendaAdapter;
	private EventsMonthAdapter monthAdapter;
	private SortedMap<Integer, MonthDayWrapper> monthGrid;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.activity_main_agenda);

		agendaAdapter = new EventsAgendaAdapter(this, DataEngine.getEvents());
		monthAdapter = null;
		monthGrid = null;

		SpinnerAdapter spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.string_array_display_modes,
				android.R.layout.simple_dropdown_item_1line);


		OnNavigationListener onNavListener = new OnNavigationListener()
		{
			String[] strings = getResources().getStringArray(R.array.string_array_display_modes);

			@Override
			public boolean onNavigationItemSelected(int itemPosition, long itemId)
			{
				// Maybe an enum or private keys could be used here instead of hard coding
				switch (itemPosition)
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
		// Inflate the menu; this adds items to the action bar if it is present.
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

		if (agendaAdapter == null)
		{
			agendaAdapter = new EventsAgendaAdapter(this, DataEngine.getEvents());
		}

		agendaView.setAdapter(agendaAdapter);
		agendaView.setOnItemClickListener(new OnItemClickListener()
		{

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				Toast.makeText(parent.getContext(), "Pressed Item", Toast.LENGTH_SHORT);

			}
		});
		System.out.println("launchAgendaView() **********************************");
	}

	// This view should really have its own manager to manage the different months etc.
	private void launchMonthView()
	{
		setContentView(R.layout.activity_main_month);
		GridView monthView = (GridView) findViewById(R.id.events_month_list);
		
		if (monthAdapter == null)
		{
			monthAdapter = new EventsMonthAdapter(getBaseContext(), DataEngine.getEvents());
		}
		
		monthView.setAdapter(monthAdapter);

		monthView.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				Integer pos = ++position;
				Toast.makeText(parent.getContext(), "Pressed Day " + pos, Toast.LENGTH_SHORT).show();

			}

		});
		System.out.println("launchMonthView() **********************************");
	}
}
