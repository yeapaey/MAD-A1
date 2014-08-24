package com.yeapMAD.assignment1.view;

import android.app.ActionBar;
import android.app.ActionBar.OnNavigationListener;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SpinnerAdapter;

import com.yeapMAD.assignment1.R;
import com.yeapMAD.assignment1.model.DataEngine;

public class MainActivity extends Activity
{	
	private EventsAgendaAdapter agendaAdapter;
	private Activity home;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_agenda);
		
		home = this;
		agendaAdapter = new EventsAgendaAdapter(this, DataEngine.getEvents());
		
		SpinnerAdapter spinnerAdapter = ArrayAdapter.createFromResource(this, 
											R.array.string_array_display_modes, android.R.layout.simple_dropdown_item_1line);
		
		OnNavigationListener onNavListener = new OnNavigationListener()
		{
			String[] strings = getResources().getStringArray(R.array.string_array_display_modes); 
			@Override
			public boolean onNavigationItemSelected(int itemPosition, long itemId)
			{
				BaseAdapter adapter;
				switch (itemPosition)
				{
				case 0:
//					System.out.println("Agenda****************************");
					home.setContentView(R.layout.activity_main_agenda);
					ListView agendaView = (ListView) findViewById(R.id.events_agenda_list);
					adapter = agendaAdapter;
					agendaView.setAdapter(adapter);
					break;
				case 1:
//					System.out.println("Month****************************");
					home.setContentView(R.layout.activity_main_month);
					GridView monthView = (GridView) findViewById(R.id.events_month_list);
					adapter = new MonthDatesAdapter(getBaseContext(), getResources().getIntArray(R.array.integer_array_month_28));
					monthView.setAdapter(adapter);
					break;
					default:
						setContentView(R.layout.activity_main_agenda);
				}
				

				return true;
			}
		};
		
		ActionBar actionBar = getActionBar();
		actionBar.setListNavigationCallbacks(spinnerAdapter, onNavListener);
		actionBar.setNavigationMode(android.app.ActionBar.NAVIGATION_MODE_LIST);
		
		
		
		
		
//		ListView agendaView = (ListView) findViewById(R.id.events_agenda_list);
//		agendaView.setAdapter(agendaAdapter);
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
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
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
}
